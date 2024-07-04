package com.hisujung.microservice.dto;

import com.hisujung.microservice.entity.UnivActivity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class UnivRecommendDto {
    Long univ_activity_id;
    String title; //제목

    @Builder
    public UnivRecommendDto(Long id, String title) {
        this.univ_activity_id = id;
        this.title = title;
    }

    public static UnivRecommendDto toDto(UnivActivity activity) {
        return UnivRecommendDto.builder()
                .id(activity.getId())
                .title(activity.getTitle())
                .build();
    }

    public static List<UnivRecommendDto> toDtoList(List<UnivActivity> activities) {
        return activities.stream()
                .map(UnivRecommendDto::toDto)
                .collect(Collectors.toList());
    }
}
