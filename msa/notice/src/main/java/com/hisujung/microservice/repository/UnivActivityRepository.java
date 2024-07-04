package com.hisujung.microservice.repository;

import com.hisujung.microservice.entity.UnivActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UnivActivityRepository extends JpaRepository<UnivActivity, Long> {

    //교내 공지사항 학과별로 검색
    List<UnivActivity> findByDepartmentContaining(String department);

    //교내 전체 공지사항 제목 키워드로 검색
    List<UnivActivity> findByTitleContaining(String keyword);

    //교재 공지사항 학과와 제목 키워드로 검색
    @Query("SELECT u FROM UnivActivity u WHERE u.department LIKE %:dep% AND u.title LIKE %:keyword% ORDER BY u.id DESC")
    List<UnivActivity> findByDepartmentAndTitle(@Param("dep") String department, @Param("keyword") String keyword);

    //추천시스템 ms에 보낼 교내 공지사항
    @Query("SELECT u FROM UnivActivity u WHERE (u.deadline > CURRENT_DATE AND u.department = (SELECT u.department FROM UnivActivity u WHERE u.id = :id)) OR u.id = :id ORDER BY CASE WHEN u.id = :id THEN 0 ELSE 1 END")
    List<UnivActivity> findUnivActWithOrdering(@Param("id") Long id);
}
