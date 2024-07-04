package com.hisujung.microservice.entity;

import com.hisujung.microservice.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
public class ParticipateUniv extends BaseTimeEntity {

    @Id
    @Column(name = "participate_info_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String memberId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "univ_activity_id")
    private UnivActivity univActivity;

    @Builder
    public ParticipateUniv(String memberId, UnivActivity univActivity) {
        this.memberId = memberId;
        this.univActivity = univActivity;
    }
}
