package com.hisujung.microservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hisujung.microservice.entity.ExternalAct;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ExtActListResponseDto {

    Long id;
    String title;
    String category;
    String link;
    LocalDateTime startingDate;
    LocalDateTime deadline;
    String content;
    String host;

    int isLiked;
    int participated;

    public ExtActListResponseDto(ExternalAct entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.category = entity.getCategory();
        this.link = entity.getLink();
        this.content = entity.getContent();
        this.startingDate = entity.getStartingDate();
        this.deadline = entity.getDeadline();
        this.host = entity.getHost();
    }

    public ExtActListResponseDto(ExternalAct entity, int isLiked, int participated) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.category = entity.getCategory();
        this.link = entity.getLink();
        this.content = entity.getContent();
        this.startingDate = entity.getStartingDate();
        this.deadline = entity.getDeadline();
        this.host = entity.getHost();
        this.isLiked = isLiked;

        this.participated = participated;
    }
}
