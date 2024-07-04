package com.hisujung.microservice.repository;

import com.hisujung.microservice.entity.ExternalAct;
import com.hisujung.microservice.entity.UnivActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ExternalActRepository extends JpaRepository<ExternalAct, Long> {

    //대외활동 제목 키워드로 검색
    List<ExternalAct> findByTitleContaining(String title);

    //추천시스템 ms에 보낼 교외 공지사항
    @Query("SELECT e FROM ExternalAct e WHERE (e.deadline > CURRENT_DATE AND e.category = (SELECT e.category FROM ExternalAct e WHERE e.id = :id)) OR e.id = :id ORDER BY CASE WHEN e.id = :id THEN 0 ELSE 1 END")
    List<ExternalAct> findExternalActWithOrdering(@Param("id") Long id);
}
