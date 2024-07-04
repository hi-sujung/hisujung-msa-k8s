package com.hisujung.microservice.dto;

import com.hisujung.microservice.entity.ExternalAct;
import com.hisujung.microservice.entity.UnivActivity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class ExtRecommendDto {
    Long external_act_id;
    String title; //제목

    @Builder
    public ExtRecommendDto(Long id, String title) {
        this.external_act_id = id;
        this.title = title;
    }

    public static ExtRecommendDto toDto(ExternalAct activity) {
        return ExtRecommendDto.builder()
                .id(activity.getId())
                .title(activity.getTitle())
                .build();
    }

    public static List<ExtRecommendDto> toDtoList(List<ExternalAct> activities) {
        return activities.stream()
                .map(ExtRecommendDto::toDto)
                .collect(Collectors.toList());
    }
}
