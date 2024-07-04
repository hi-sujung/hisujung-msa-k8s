package com.hisujung.microservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hisujung.microservice.entity.UnivActivity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class UnivActListResponseDto {

    Long id;
    String title;
    String department;
    String content;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    LocalDateTime startingDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    LocalDateTime deadline;
    String link;

    int isLiked;
    int participated;

    public UnivActListResponseDto(UnivActivity entity) {
        this.id = entity.getId();;
        this.title = entity.getTitle();
        this.department = entity.getDepartment();
        this.content = entity.getContent();
        this.startingDate = entity.getStartingDate();
        this.deadline = entity.getDeadline();
        this.link = entity.getLink();
    }

    public UnivActListResponseDto(UnivActivity entity, int isLiked, int participated) {
        this.id = entity.getId();;
        this.title = entity.getTitle();
        this.department = entity.getDepartment();
        this.content = entity.getContent();
        this.startingDate = entity.getStartingDate();
        this.deadline = entity.getDeadline();
        this.link = entity.getLink();

        this.isLiked = isLiked;
        this.participated = participated;
    }

}
