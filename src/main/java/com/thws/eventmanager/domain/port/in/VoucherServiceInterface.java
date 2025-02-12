package com.thws.eventmanager.domain.port.in;

public interface VoucherServiceInterface {
    public long applyVoucher(String code, long orderTotal);
}
