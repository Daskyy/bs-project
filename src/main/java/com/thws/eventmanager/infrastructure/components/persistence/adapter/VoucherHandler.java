package com.thws.eventmanager.infrastructure.components.persistence.adapter;

import com.thws.eventmanager.infrastructure.components.persistence.entities.VoucherEntity;

import java.util.Optional;

public class VoucherHandler extends GenericPersistenceAdapter<VoucherEntity, Long> {
    public VoucherHandler() {
        super(VoucherEntity.class);
    }

    public Optional<VoucherEntity> findByCode(String code) {
        Optional<VoucherEntity> voucher = this.entityManager.createQuery(
                        "SELECT v FROM VoucherEntity v WHERE v.code = :code", VoucherEntity.class)
                .setParameter("code", code)
                .getResultList()
                .stream()
                .findFirst();

        if (voucher.isEmpty()) {
            System.out.println("Voucher " + code + " not found in database!");
        } else {
            System.out.println("Voucher " + code + " found! Remaining uses: " + voucher.get().getRemainingUses());
        }

        return voucher;
    }

    public void updateVoucherUsage(VoucherEntity voucher) {
        entityManager.getTransaction().begin();
        voucher.setRemainingUses(voucher.getRemainingUses() - 1);
        entityManager.merge(voucher);
        entityManager.getTransaction().commit();
    }
}
