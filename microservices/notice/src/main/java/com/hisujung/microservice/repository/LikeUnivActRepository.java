package com.hisujung.microservice.repository;

import com.hisujung.microservice.entity.LikeUnivAct;
import com.hisujung.microservice.entity.UnivActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LikeUnivActRepository extends JpaRepository<LikeUnivAct, Long> {

    List<LikeUnivAct> findByMemberId(String memberId);
    @Query("SELECT l FROM LikeUnivAct l WHERE l.memberId = :memberId AND l.univActivity = :activity")
    Optional<LikeUnivAct> findByMemberAndAct(@Param("memberId") String memberId, @Param("activity") UnivActivity activity);
}
