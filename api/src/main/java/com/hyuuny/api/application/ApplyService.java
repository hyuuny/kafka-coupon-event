package com.hyuuny.api.application;

import com.hyuuny.api.producer.CouponCreateProducer;
import com.hyuuny.api.repository.AppliedUserRepository;
import com.hyuuny.api.repository.CouponCountRepository;
import org.springframework.stereotype.Service;

@Service
public class ApplyService {

    private final CouponCountRepository couponCountRepository;

    private final CouponCreateProducer couponCreateProducer;

    private final AppliedUserRepository appliedUserRepository;

    public ApplyService(CouponCountRepository couponCountRepository, CouponCreateProducer couponCreateProducer, AppliedUserRepository appliedUserRepository) {
        this.couponCountRepository = couponCountRepository;
        this.couponCreateProducer = couponCreateProducer;
        this.appliedUserRepository = appliedUserRepository;
    }

    public void apply(final Long userId) {
        Long apply = appliedUserRepository.add(userId);

        // 회원은 1개의 쿠폰만 발급 받을 수 있다.
        if (apply != 1) {
            return;
        }
        long count = couponCountRepository.increment();

        if (count > 100) {
            return;
        }
        couponCreateProducer.create(userId);
    }

}
