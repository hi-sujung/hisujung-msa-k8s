package com.hisujung.microservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hisujung.microservice.entity.LikeUnivAct;
import com.hisujung.microservice.entity.UnivActivity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hisujung.microservice.entity.LikeUnivAct;
import com.hisujung.microservice.entity.UnivActivity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Getter
public class UnivActCrawlingDto {

    String title;

    String department;

    String content;

    String startingDate;

    String deadline;

    String link;

    public UnivActivity toEntity() {

        LocalDateTime startingDateTime = parseDate(startingDate);
        LocalDateTime deadlineDateTime = parseDate(deadline);

        return UnivActivity.builder()
                .title(title)
                .department(department)
                .content(content)
                .startingDate(startingDateTime)
                .deadline(deadlineDateTime)
                .link(link).build();
    }

    private LocalDateTime parseDate(String dateStr) {
        String formattedDateStr = dateStr.replace(".", "-") + " 00:00:00";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(formattedDateStr, formatter);
    }
}



