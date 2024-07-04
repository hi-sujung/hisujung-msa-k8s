package com.hisujung.microservice.dto;


import com.hisujung.microservice.entity.Member;
import com.hisujung.microservice.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberSignupRequestDto {

    private String email;

    private String userName;

    private String password;

    private String checkedPassword;

    private String department1;

    private String department2;

    private Role role;


    @Builder
    public Member toEntity() {
        return Member.builder()
                .email(email)
                .userName(userName)
                .password(password)
                .department1(department1)
                .department2(department2)
                .role(Role.USER)
                .build();
    }
}
