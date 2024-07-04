package com.hisujung.microservice.repository;


import com.hisujung.microservice.entity.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {

    //@Query("SELECT p FROM Portfolio p WHERE p.member.id = :mid ORDER BY p.id DESC ")
    //List<Portfolio> findAllDesc(@Param("mid") Long mid);

    List<Portfolio> findByMemberId(String memberId);
}
