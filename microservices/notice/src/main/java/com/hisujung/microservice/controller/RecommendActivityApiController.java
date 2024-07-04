package com.hisujung.microservice.controller;

import com.hisujung.microservice.dto.ExtRecommendDto;
import com.hisujung.microservice.dto.UnivRecommendDto;
import com.hisujung.microservice.service.ExtActService;
import com.hisujung.microservice.service.UnivActService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class RecommendActivityApiController {

    private final UnivActService univActService;
    private final ExtActService extActService;

    //교내 추천 공지사항 조회
    @GetMapping("/recommend/univ")
    public List<UnivRecommendDto> getRecommendUnivAct(@RequestParam("activity_name") Long id){
        return univActService.getRecommendUnivAct(id);
    }

    //교외 추천 공지사항 조회
    @GetMapping("/recommend/external")
    public List<ExtRecommendDto> getRecommendExtAct(@RequestParam("activity_name") Long id){
        return extActService.getRecommendExternalAct(id);
    }
}
