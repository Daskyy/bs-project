package com.thws.eventmanager.domain.services.models;

import com.thws.eventmanager.domain.port.in.VoucherServiceInterface;
import com.thws.eventmanager.infrastructure.components.persistence.adapter.VoucherHandler;
import com.thws.eventmanager.infrastructure.components.persistence.entities.VoucherEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VoucherService implements VoucherServiceInterface {
    private static final Logger log = LoggerFactory.getLogger(VoucherService.class);

    @Override
    public long applyVoucher(String code, long orderTotal) {
        try(VoucherHandler voucherHandler = new VoucherHandler()) {
            return voucherHandler.findByCode(code)
                    .filter(voucher -> voucher.getRemainingUses() > 0) // Ensure it still has uses left
                    .map(voucher -> {
                        long discount = Math.min(voucher.getDiscountAmount(), orderTotal);
                        voucherHandler.updateVoucherUsage(voucher);
                        log.info("Applied voucher {} for discount {} EUR", code, discount / 100.0);
                        return discount;
                    })
                    .orElse(0L);
        }
    }

    @Override
    public VoucherEntity createVoucher(String code, long discountAmount, int uses) {
        try(VoucherHandler voucherHandler = new VoucherHandler()) {
            VoucherEntity voucherEntity = new VoucherEntity(code, discountAmount, uses);
            return voucherHandler.save(voucherEntity);
        }
    }
}
