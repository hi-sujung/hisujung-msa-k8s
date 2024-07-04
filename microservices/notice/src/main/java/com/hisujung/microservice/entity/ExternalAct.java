package com.hisujung.microservice.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hisujung.microservice.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Entity
public class ExternalAct extends BaseTimeEntity {

    @Id @Column(name = "external_act_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title; //제목

    @Column(nullable = false, length = 50)
    private String category; //카테고리

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    //@Column(nullable = false)
    private LocalDateTime startingDate; //시작일

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    //@Column(nullable = false)
    private LocalDateTime deadline; //마감일

    @Column(nullable = true)
    private String host; //주최

    @Column(nullable = false, length = 2000)
    private String content; //내용

    private String link; //링크
    
    @OneToMany(mappedBy = "activity", cascade = CascadeType.ALL)
    private List<LikeExternalAct> likeList;

    @Builder
    public ExternalAct(String title, String category, String link, LocalDateTime startingDate, LocalDateTime deadline, String content, String host) {
        this.title = title;
        this.category = category;
        this.link = link;
        this.startingDate = startingDate;
        this.deadline = deadline;
        this.content = content;
        this.host = host;

    }



}
