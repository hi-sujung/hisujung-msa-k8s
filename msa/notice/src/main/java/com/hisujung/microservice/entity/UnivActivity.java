package com.hisujung.microservice.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hisujung.microservice.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
public class UnivActivity extends BaseTimeEntity {

    @Id @Column(name = "univ_activity_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, length = 50)
    private String department;

    @Column(nullable = false, length = 2000)
    private String content;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    //@Column(nullable = false)
    private LocalDateTime startingDate; //시작일

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    //@Column(nullable = false)
    private LocalDateTime deadline; //마감일

    @Column(nullable = false)
    private String link;

    @OneToMany(mappedBy = "univActivity", cascade = CascadeType.ALL)
    private List<LikeUnivAct> likeList = new ArrayList<>();

    @Builder
    public UnivActivity(String title, String department, String content, LocalDateTime startingDate, LocalDateTime deadline, String link) {
        this.title = title;
        this.department = department;
        this.content = content;
        this.startingDate = startingDate;
        this.deadline = deadline;
        this.link = link;
    }
}
