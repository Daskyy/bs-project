package com.thws.eventmanager.domain.port.in;

import com.thws.eventmanager.infrastructure.components.persistence.entities.VoucherEntity;

import java.util.List;

public interface VoucherServiceInterface {
    public long applyVoucher(String code, long orderTotal);
    VoucherEntity createVoucher(String code, long discountAmount, int uses);

    VoucherEntity deactivateVoucher(VoucherEntity voucher);

    VoucherEntity activateVoucher(VoucherEntity voucher);

    VoucherEntity getVoucherById(long id);

    VoucherEntity updateVoucher(VoucherEntity voucherEntity);

    VoucherEntity deleteVoucher(long id);

    List<VoucherEntity> getAllVouchers(List<String> criteria, List<Object> values, Integer page, Integer pageSize);
}
