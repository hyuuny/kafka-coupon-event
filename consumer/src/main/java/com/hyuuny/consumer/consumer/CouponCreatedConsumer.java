package com.hyuuny.consumer.consumer;

import com.hyuuny.consumer.domain.Coupon;
import com.hyuuny.consumer.repository.CouponRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class CouponCreatedConsumer {

    private final CouponRepository couponRepository;

    public CouponCreatedConsumer(CouponRepository couponRepository) {
        this.couponRepository = couponRepository;
    }


    @KafkaListener(topics = "coupon_create", groupId = "group_1")
    public void listener(final Long userId) {
        couponRepository.save(new Coupon(userId));
    }

}
