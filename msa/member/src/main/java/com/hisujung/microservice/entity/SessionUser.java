package com.hisujung.microservice.entity;

import lombok.Getter;
import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    private Long id;
    private String userName;
    private String email;
    private String password;
    private String department1;
    private String department2;
    private Role role;

    public SessionUser(Member member) {
        this.id = member.getId();
        this.userName = member.getUsername();
        this.email = member.getEmail();
        this.password = member.getPassword();
        this.role = member.getRole();
    }

}
