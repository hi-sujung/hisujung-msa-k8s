package com.hisujung.microservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hisujung.microservice.entity.ExternalAct;
import com.hisujung.microservice.entity.LikeExternalAct;
import com.hisujung.microservice.entity.UnivActivity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Data
public class ExtActCrawlingDto {

    String title; //제목

    String category; //카테고리

    String startingDate; //시작일

    String deadline; //마감일

    String host; //주최

    String content; //내용

    String link; //링크

    public ExternalAct toEntity() {

        LocalDateTime startingDateTime = parseDate(startingDate);
        LocalDateTime deadlineDateTime = parseDate(deadline);

        return ExternalAct.builder()
                .title(title)
                .category(category)
                .startingDate(startingDateTime)
                .deadline(deadlineDateTime)
                .host(host)
                .content(content)
                .link(link).build();
    }

    private LocalDateTime parseDate(String dateStr) {
        String formattedDateStr = dateStr.replace(".", "-") + " 00:00:00";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(formattedDateStr, formatter);
    }
}
