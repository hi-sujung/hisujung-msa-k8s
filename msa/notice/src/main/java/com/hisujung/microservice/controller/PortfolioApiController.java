package com.hisujung.microservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hisujung.microservice.ApiResponse;
import com.hisujung.microservice.dto.ActivitiesDto;
import com.hisujung.microservice.dto.PortfolioSaveRequestDto;
import com.hisujung.microservice.dto.UnivActListResponseDto;
import com.hisujung.microservice.service.ExtActService;
import com.hisujung.microservice.service.GptServiceImpl;
import com.hisujung.microservice.service.RateLimiterService;
import com.hisujung.microservice.service.UnivActService;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.ConsumptionProbe;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("notice/portfolio")
public class PortfolioApiController {

    private final GptServiceImpl gptService;
    private final RateLimiterService rateLimiterService;
    private final UnivActService univActService;
    private final ExtActService extActService;
    @Value("${portfolio.ms.url}")
    private String portfolioMsUrl;
//    @Value("spring-cloud-gateway.ms.url")
//    private final String springCloudGatewayMs;

    public List<UnivActListResponseDto> fetchNoticeCheckedList(String memberId) {
        RestTemplate restTemplate = new RestTemplate();
        String url = portfolioMsUrl+"notice/univactivity/auth/checked-list?memberId=" + memberId;

        ResponseEntity<List<UnivActListResponseDto>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<UnivActListResponseDto>>() {}
        );

        return response.getBody();
    }


    // 처리율 제한 장치 적용하려는 API
    @PostMapping(path="/auth/create-by-ai", headers = "X-Authoization-Id")
    public ApiResponse<PortfolioSaveRequestDto> createByAi(@RequestHeader("X-Authoization-Id") String memberId, @RequestParam String careerField, @RequestParam String title) throws JsonProcessingException {

        //String memberId = auth.getName();

        Bucket bucket = rateLimiterService.resolveBucket(memberId);
        ConsumptionProbe probe = bucket.tryConsumeAndReturnRemaining(1);

        long saveToken = probe.getRemainingTokens();

        if(probe.isConsumed()) {
            log.info("API Call Success");
            log.info("Available Token : {}", saveToken);

            // 변경된 부분 시작
            List<UnivActListResponseDto> noticeCheckedList = univActService.findCheckedByUser(memberId);
            //String titles = ""
            StringBuilder titles = new StringBuilder();
            for(UnivActListResponseDto dto: noticeCheckedList) {
                titles.append(dto.getTitle());
                titles.append(", ");
            }

            PortfolioSaveRequestDto result = new PortfolioSaveRequestDto(title, gptService.getAssistantMsg(titles.toString(), careerField));

            // RestTemplate을 사용하여 포트폴리오 저장 API 호출
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/json");
            headers.set("X-Authoization-Id", memberId); // 헤더에 memberId 추가

            HttpEntity<PortfolioSaveRequestDto> entity = new HttpEntity<>(result, headers);

            String saveUrl = portfolioMsUrl+ "portfolio/new?memberId=" + memberId; // 실제 URL로 변경해야 합니다.
            ResponseEntity<ApiResponse> saveResponseEntity = restTemplate.exchange(saveUrl, HttpMethod.POST, entity, ApiResponse.class);

            ApiResponse<Long> saveResponse = saveResponseEntity.getBody();
            if (saveResponse == null) {
                return (ApiResponse<PortfolioSaveRequestDto>) ApiResponse.createError("포트폴리오 업데이트에 실패했습니다.");
            }
            return ApiResponse.createSuccess(result);
        }

        long waitForRefill = probe.getNanosToWaitForRefill() / 1_000_000_000;

        log.info("TOO MANY REQUEST");
        log.info("Available Token : {}", saveToken);
        log.info("Wait Time {} Second ", waitForRefill);

        return (ApiResponse<PortfolioSaveRequestDto>) ApiResponse.createError("HttpStatus.TOO_MANY_REQUESTS");
    }

}
