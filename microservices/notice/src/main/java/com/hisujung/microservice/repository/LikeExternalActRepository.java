package com.hisujung.microservice.repository;

import com.hisujung.microservice.entity.ExternalAct;
import com.hisujung.microservice.entity.LikeExternalAct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LikeExternalActRepository extends JpaRepository<LikeExternalAct, Long> {

    List<LikeExternalAct> findByMemberId(String memberId);
    @Query("SELECT l FROM LikeExternalAct l WHERE l.memberId = :m AND l.activity = :e")
    Optional<LikeExternalAct> findByMemberAndAct(@Param("m") String memberId, @Param("e") ExternalAct e);

}
