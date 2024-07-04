package com.hisujung.microservice.entity;

import com.hisujung.microservice.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
public class LikeExternalAct extends BaseTimeEntity {

    @Id @Column(name = "like_external_act_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //사용자 이메일
    String memberId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "external_act_id")
    private ExternalAct activity;

    @Builder
    public LikeExternalAct(String memberId, ExternalAct activity) {
        this.memberId = memberId;
        this.activity = activity;
    }

}
