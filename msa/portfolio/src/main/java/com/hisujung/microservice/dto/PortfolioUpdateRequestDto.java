package com.hisujung.microservice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PortfolioUpdateRequestDto {
    private String title;
    private String urlLink;
    private String description;

    @Builder
    public PortfolioUpdateRequestDto(String title, String urlLink, String description) {
        this.title = title;
        this.urlLink = urlLink;
        this.description = description;
    }
}
