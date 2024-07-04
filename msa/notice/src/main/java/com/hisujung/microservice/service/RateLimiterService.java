package com.hisujung.microservice.service;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class RateLimiterService {

    private final Map<String, Bucket> cache = new ConcurrentHashMap<>();

    //1일에 10번 사용 가능한 요금제
    private Bandwidth getLimit() {
        return Bandwidth.builder().capacity(10L).refillIntervally(10, Duration.ofDays(1)).build();
    }

    private Bucket newBucket(String memberId) {
        return Bucket.builder().addLimit(getLimit()).build();
    }

    public Bucket resolveBucket(String memberId) {
        return cache.computeIfAbsent(memberId, this::newBucket);
    }


}
