package com.hisujung.microservice.service;

import com.hisujung.microservice.dto.PortfolioListResponseDto;
import com.hisujung.microservice.dto.PortfolioResponseDto;
import com.hisujung.microservice.dto.PortfolioSaveRequestDto;
import com.hisujung.microservice.dto.PortfolioUpdateRequestDto;
import com.hisujung.microservice.entity.Portfolio;
import com.hisujung.microservice.repository.PortfolioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class PortfolioService {

    private final PortfolioRepository portfolioRepository;

    @Transactional
    public Long save(String memberId, String title, String contents) {
        return portfolioRepository.save(Portfolio.builder().memberId(memberId).title(title).description(contents).build()).getId();
    }

    //임시로 Member 관련 기능 주석 처리
    @Transactional
    public Long save(String memberId, PortfolioSaveRequestDto requestDto) {

        return portfolioRepository.save(requestDto.toEntity(memberId)).getId();
    }

    @Transactional
    public Long update(Long id, PortfolioUpdateRequestDto requestDto) {
        Portfolio portfolio = portfolioRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 포트폴리오가 없습니다. id=" + id));

        portfolio.update(requestDto.getTitle(),requestDto.getDescription());

        return id;
    }

    public PortfolioResponseDto findById(Long id) {
        Portfolio entity = portfolioRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 포트폴리오가 없습니다. id" + id));

        return new PortfolioResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<PortfolioListResponseDto> findAllDescByMember(String memberId) {
        return portfolioRepository.findByMemberId(memberId).stream().map(PortfolioListResponseDto::new).collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id) {
        Portfolio portfolio = portfolioRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 포트폴리오가 없습니다. id = " + id));
        portfolioRepository.delete(portfolio);
    }
}
