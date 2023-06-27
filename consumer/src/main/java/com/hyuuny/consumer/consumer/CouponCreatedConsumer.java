package com.hyuuny.consumer.consumer;

import com.hyuuny.consumer.domain.Coupon;
import com.hyuuny.consumer.domain.FailedEvent;
import com.hyuuny.consumer.repository.CouponRepository;
import com.hyuuny.consumer.repository.FailedEventRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class CouponCreatedConsumer {

    private final CouponRepository couponRepository;

    private final FailedEventRepository failedEventRepository;

    private final Logger logger = LoggerFactory.getLogger(CouponCreatedConsumer.class);

    public CouponCreatedConsumer(CouponRepository couponRepository, FailedEventRepository failedEventRepository) {
        this.couponRepository = couponRepository;
        this.failedEventRepository = failedEventRepository;
    }


    @KafkaListener(topics = "coupon_create", groupId = "group_1")
    public void listener(final Long userId) {
        try {
            couponRepository.save(new Coupon(userId));
        } catch (Exception e) {
            // 쿠폰 발급 실패시 FailedEvent에 userId 저장
            logger.error("failed to create coupon::" + userId);
            failedEventRepository.save(new FailedEvent(userId));
        }
    }

}
