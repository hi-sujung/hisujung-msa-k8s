package com.hisujung.microservice.controller;



import ch.qos.logback.classic.Logger;
import com.hisujung.microservice.dto.LoginRequestDto;
import com.hisujung.microservice.dto.MemberSignupRequestDto;
import com.hisujung.microservice.entity.Member;
import com.hisujung.microservice.jwt.JwtTokenUtil;
//import com.hisujung.microservice.mail.MailSender;
import com.hisujung.microservice.service.MemberService;
import com.hisujung.microservice.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberApiController {
    @Autowired
    private Environment env;

    private final MemberService memberService;
    private final UserService userService;
//    private final MailSender mailSender;
    @Value("${jwt.secret}")
    private String secretKey;

    @PostMapping("/join")
    public Long join(@RequestBody MemberSignupRequestDto requestDto) throws Exception {
        return memberService.join(requestDto);
    }

//    @PostMapping("/join/mailConfirm")
//    @ResponseBody
//    public String mailConfirm(@RequestParam String email) throws Exception {
//        log.info(email);
//        String code = mailSender.send(email);
//        log.info("인증코드 : " + code);
//        return code;
//    }

//    @GetMapping("/join/verify/{key}")
//    public String getVerify(@PathVariable String key) {
//        String message;
//        try {
//            mailSender.verifyEmail(key);
//            message = "인증에 성공하였습니다.";
//        } catch (Exception e) {
//            message = "인증에 실패하였습니다.";
//        }
//        return message;
//    }


    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginRequestDto loginRequestDto) {
        Member user = userService.login(loginRequestDto);

        if (user == null) {
            return ResponseEntity.badRequest().body("로그인 아이디 또는 비밀번호가 틀렸습니다.");
        }

        long expireTimeMs = 1000 * 60 * 60 * 8; // Token 유효 시간 = 8시간
        String jwtToken = JwtTokenUtil.createToken(loginRequestDto.getEmail(), secretKey, expireTimeMs);

        // 사용자 정보를 JSON 형태로 리턴
        HashMap<String, Object> response = new HashMap<>();
        response.put("userId", user.getId());
        response.put("token", jwtToken);
        response.put("email", user.getEmail());
        response.put("username", user.getUsername());

        log.info("hihihibye");
        return ResponseEntity.ok(response);
    }

    @GetMapping("info")
    public String userInfo(Authentication auth) {

        //로그인id는 사용자의 이메일
        Member loginUser = userService.getLoginUserByLoginId(auth.getName());

        return String.format("loginId : %s\nusername : %s\nrole: %s",
                loginUser.getEmail(), loginUser.getUsername(), loginUser.getRole());

    }
}
