//package com.hisujung.microservice.dto;
//
//import com.hisujung.microservice.entity.LikeUnivAct;
//import lombok.Getter;
//
//import java.time.LocalDateTime;
//
//@Getter
//public class LikeUnivActListDto {
//    Long id;
//
//    String title;
//
//    String postDepartment;
//
//    String description;
//
//    //올라온 날짜
//    LocalDateTime startDate;
//
//    //마감기한
//    LocalDateTime deadline;
//
//    String link;
//
//
//    public LikeUnivActListDto(LikeUnivAct entity) {
//        this.id = entity.getId();
//        this.title = entity.getUnivActivity().getTitle();
//        this.description = entity.getUnivActivity().getDescription();
//        this.startDate = entity.getUnivActivity().getStartDate();
//        this.deadline = entity.getUnivActivity().getDeadline();
//        this.postDepartment = entity.getUnivActivity().getPostDepartment();
//        this.link = entity.getUnivActivity().getLink();
//    }
//}
