package com.hisujung.microservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hisujung.microservice.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
public class Member extends BaseTimeEntity {

    @Id @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 45, unique = true)
    private String email;

    private String userName;

    private String password;

    private String department1;

    private String department2;

    @Enumerated(EnumType.STRING)
    private Role role;

//    @JsonIgnore
//    @OneToMany(mappedBy = "member", cascade = CascadeType.PERSIST)
//    private List<Portfolio> portfolioList = new ArrayList<>();

    public boolean checkPassword(PasswordEncoder passwordEncoder, String inputPassword) {
        return passwordEncoder.matches(inputPassword, this.password);
    }

    public String getUsername() {return userName;}

    public void addUserAuthority() {
        this.role = Role.USER;
    }

    public void encodePassword(PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(password);
    }

    @Builder
    public Member(String email, String userName, String password, String department1, String department2, Role role) {
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.department1 = department1;
        this.department2 = department2;
        this.role = role;
    }
}
