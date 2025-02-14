package com.thws.eventmanager.domain.services.other;

import com.thws.eventmanager.domain.exceptions.InvalidEventException;
import com.thws.eventmanager.domain.port.in.VoucherServiceInterface;
import com.thws.eventmanager.infrastructure.components.persistence.adapter.VoucherHandler;
import com.thws.eventmanager.infrastructure.components.persistence.entities.VoucherEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

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

    @Override
    public VoucherEntity deactivateVoucher(VoucherEntity voucher) {
        try(VoucherHandler voucherHandler = new VoucherHandler()) {
            voucher.setActive(false);
            return voucherHandler.save(voucher);
        }
    }

    @Override
    public VoucherEntity activateVoucher(VoucherEntity voucher) {
        try(VoucherHandler voucherHandler = new VoucherHandler()) {
            voucher.setActive(true);
            return voucherHandler.save(voucher);
        }
    }

    @Override
    public VoucherEntity getVoucherById(long id) {
        try(VoucherHandler voucherHandler = new VoucherHandler()) {
            return voucherHandler.findById(id).orElse(null);
        }
    }

    @Override
    public VoucherEntity updateVoucher(VoucherEntity voucherEntity) {
        try(VoucherHandler voucherHandler = new VoucherHandler()) {
            return voucherHandler.save(voucherEntity);
        }
    }

    @Override
    public VoucherEntity deleteVoucher(long id) {
        try(VoucherHandler voucherHandler = new VoucherHandler()) {
            VoucherEntity voucherEntity = voucherHandler.findById(id).orElse(null);
            if(voucherEntity != null) {
                voucherHandler.deleteById(voucherEntity.getId());
            }
            return voucherEntity;
        }
    }

    @Override
    public List<VoucherEntity> getAllVouchers(List<String> criteria, List<Object> values, Integer page, Integer pageSize) {
        log.info("Criteria: " + criteria);
        log.info("Values: " + values);

        try(VoucherHandler voucherHandler = new VoucherHandler()) {
            List<String> safeCriteria = (criteria != null) ? criteria : List.of();
            List<Object> safeValues = (values != null) ? values : List.of();

            if (safeCriteria.size() != safeValues.size()) {
                throw new InvalidEventException("Criteria and values lists must have the same size.");
            }

            if (page == null || pageSize == null) {
                return voucherHandler.searchByCriteria(safeCriteria, safeValues);
            }

            return voucherHandler.searchByCriteria(safeCriteria, safeValues, page, pageSize);
        } catch (Exception e) {
            throw new InvalidEventException("Failed to get filtered addresses from database.");
        }
    }
}
