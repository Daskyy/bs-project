package com.thws.eventmanager.domain.usecases;

import com.thws.eventmanager.domain.port.in.VoucherServiceInterface;
import com.thws.eventmanager.infrastructure.components.persistence.adapter.VoucherHandler;
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
}
