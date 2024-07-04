package com.hisujung.microservice.repository;

import com.hisujung.microservice.entity.ParticipateEx;
import com.hisujung.microservice.entity.ParticipateUniv;
import com.hisujung.microservice.entity.UnivActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ParticipateUnivRepository extends JpaRepository<ParticipateUniv, Long> {

    List<ParticipateUniv> findByMemberId(String memberId);

    @Query("SELECT p FROM ParticipateUniv p WHERE p.memberId = :memberId AND p.univActivity = :activity")
    Optional<ParticipateUniv> findByMemberAndUnivAct(@Param("memberId") String memberId, @Param("activity") UnivActivity activity);
}
