package com.hyuuny.api.application;

import com.hyuuny.api.domain.Coupon;
import com.hyuuny.api.repository.CouponCountRepository;
import com.hyuuny.api.repository.CouponRepository;
import org.springframework.stereotype.Service;

@Service
public class ApplyService {

    private final CouponRepository couponRepository;

    private final CouponCountRepository couponCountRepository;

    public ApplyService(CouponRepository couponRepository, CouponCountRepository couponCountRepository) {
        this.couponRepository = couponRepository;
        this.couponCountRepository = couponCountRepository;
    }

    public void apply(final Long userId) {
        long count = couponCountRepository.increment();

        if (count > 100) {
            return;
        }
        couponRepository.save(new Coupon(userId));
    }

}
