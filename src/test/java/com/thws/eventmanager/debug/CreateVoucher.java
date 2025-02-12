package com.thws.eventmanager.debug;

import com.thws.eventmanager.infrastructure.components.persistence.adapter.VoucherHandler;
import com.thws.eventmanager.infrastructure.components.persistence.entities.VoucherEntity;

public class CreateVoucher {
    public static void main(String[] args) {
        try(VoucherHandler voucherHandler = new VoucherHandler()) {
            VoucherEntity voucherEntity = new VoucherEntity("BRAUN10", 1000L, 10);
            voucherHandler.save(voucherEntity);
        }
    }
}
