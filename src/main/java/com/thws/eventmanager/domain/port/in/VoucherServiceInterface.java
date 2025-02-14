package com.thws.eventmanager.domain.port.in;

import com.thws.eventmanager.infrastructure.components.persistence.entities.VoucherEntity;

public interface VoucherServiceInterface {
    public long applyVoucher(String code, long orderTotal);
    VoucherEntity createVoucher(String code, long discountAmount, int uses);
}
