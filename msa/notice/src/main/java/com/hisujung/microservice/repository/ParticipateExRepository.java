package com.hisujung.microservice.repository;

import com.hisujung.microservice.entity.ExternalAct;
import com.hisujung.microservice.entity.ParticipateEx;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ParticipateExRepository extends JpaRepository<ParticipateEx, Long> {
    List<ParticipateEx> findByMemberId(String memberId);

    @Query("SELECT p FROM ParticipateEx p WHERE p.memberId = :memberId AND p.activity = :externalAct")
    Optional<ParticipateEx> findByMemberAndExtAct(@Param("memberId") String memberId, @Param("externalAct")ExternalAct externalAct);
}
