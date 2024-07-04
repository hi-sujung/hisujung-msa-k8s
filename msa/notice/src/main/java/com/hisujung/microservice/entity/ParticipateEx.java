package com.hisujung.microservice.entity;

import com.hisujung.microservice.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
public class ParticipateEx extends BaseTimeEntity {

    @Id @Column(name = "participate_info_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String memberId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "external_act_id")
    private ExternalAct activity;

    @Builder
    public ParticipateEx(String memberId, ExternalAct activity) {
        this.memberId = memberId;
        this.activity = activity;
    }

}
