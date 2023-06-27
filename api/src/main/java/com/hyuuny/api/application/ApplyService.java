package com.hyuuny.api.application;

import com.hyuuny.api.domain.Coupon;
import com.hyuuny.api.producer.CouponCreateProducer;
import com.hyuuny.api.repository.CouponCountRepository;
import com.hyuuny.api.repository.CouponRepository;
import org.springframework.stereotype.Service;

@Service
public class ApplyService {

    private final CouponRepository couponRepository;

    private final CouponCountRepository couponCountRepository;

    private final CouponCreateProducer couponCreateProducer;

    public ApplyService(CouponRepository couponRepository, CouponCountRepository couponCountRepository, CouponCreateProducer couponCreateProducer) {
        this.couponRepository = couponRepository;
        this.couponCountRepository = couponCountRepository;
        this.couponCreateProducer = couponCreateProducer;
    }

    public void apply(final Long userId) {
        long count = couponCountRepository.increment();

        if (count > 100) {
            return;
        }
        couponCreateProducer.create(userId);
    }

}
