package com.hisujung.microservice.service;


import com.hisujung.microservice.dto.MemberSignupRequestDto;

public interface MemberService {
    //회원가입
    public Long signUp(MemberSignupRequestDto requestDro) throws Exception;
    public Long join(MemberSignupRequestDto requestDto) throws Exception;
    //public String login(Map<String, String> members);
}

