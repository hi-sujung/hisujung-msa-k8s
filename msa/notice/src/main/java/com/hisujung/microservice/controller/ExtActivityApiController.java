package com.hisujung.microservice.controller;

import com.hisujung.microservice.dto.ExtActCrawlingDto;
import com.hisujung.microservice.dto.ExtActListResponseDto;
import com.hisujung.microservice.service.ExtActService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@RequestMapping("notice/externalact")
@RequiredArgsConstructor
@RestController
public class ExtActivityApiController {

    private final ExtActService extActService;

    @GetMapping("/")
    public List<ExtActListResponseDto> findAll() {
        return extActService.findAllByDesc();
    }

    @GetMapping("/keyword")
    public List<ExtActListResponseDto> findByTitle(@RequestParam String keyword) {
        return extActService.findByTitle(keyword);
    }


    @GetMapping(path = "/auth/id", headers = "X-Authoization-Id")
    public ExtActListResponseDto findById(@RequestHeader("X-Authoization-Id") String memberId, @RequestParam Long id) {
        return extActService.findById(memberId, id);
    }


    //====== 대외활동 좋아요 눌렀을 때 =======
    @PostMapping(path = "/auth/like", headers = "X-Authoization-Id")
    public Long saveLike(@RequestHeader("X-Authoization-Id") String memberId, @RequestParam Long actId) {
        extActService.saveLike(memberId, actId);
        return actId;
    }

    //대외활동 좋아요 삭제
    @DeleteMapping(path = "/auth/like-cancel", headers = "X-Authoization-Id")
    public Long deleteLike(@RequestHeader("X-Authoization-Id") String memberId, @RequestParam Long id) {
        extActService.deleteLike(memberId, id);
        return id;
    }

    //회원의 대외활동 좋아요 리스트 조회
    @GetMapping(path = "/auth/like-list", headers = "X-Authoization-Id")
    public List<ExtActListResponseDto> findByMember(@RequestHeader("X-Authoization-Id") String memberId) {
        return extActService.findLikedByUser(memberId);
    }

    // 대외활동 크롤링 데이터 저장
    @RabbitListener(queues = "external_act_queue")
    public void ExtProcessMessage(ExtActCrawlingDto extActCrawlingDto) {
        extActService.saveActivity(extActCrawlingDto);
    }

    //====== 대외활동 참여 체크 눌렀을 때 =======
    @PostMapping(path = "/auth/check", headers = "X-Authoization-Id")
    public Long saveCheck(@RequestHeader("X-Authoization-Id") String memberId, @RequestParam Long actId) {
        extActService.saveCheck(actId, memberId);
        return actId;
    }

    @DeleteMapping(path = "/auth/check-cancel", headers = "X-Authoization-Id")
    public Long deleteCheck(@RequestHeader("X-Authoization-Id") String memberId, @RequestParam Long id) {
        extActService.deleteCheck(memberId, id);
        return id;
    }

    @GetMapping(path = "/auth/checked-list", headers = "X-Authoization-Id")
    public List<ExtActListResponseDto> findCheckedByMember(@RequestHeader("X-Authoization-Id") String memberId) {
        return extActService.findCheckedByUser(memberId);
    }
}
