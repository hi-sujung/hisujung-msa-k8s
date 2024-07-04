package com.hisujung.microservice.dto;

import com.hisujung.microservice.entity.Portfolio;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PortfolioSaveRequestDto {

    private String title;
    private String description;

    @Builder
    public PortfolioSaveRequestDto(String title, String description) {
        this.title = title;
        this.description = description;
    }

    //임시로 Member 관련 코드 삭제
    public Portfolio toEntity(String memberId) {
        return Portfolio.builder()
                .title(title)
                .description(description)
                .memberId(memberId)
                .build();
    }

//    public Portfolio toEntity(Member member) {
//        return Portfolio.builder()
//                .member(member)
//                .title(title)
//                .urlLink(urlLink)
//                .description(description)
//                .build();
//    }

}
