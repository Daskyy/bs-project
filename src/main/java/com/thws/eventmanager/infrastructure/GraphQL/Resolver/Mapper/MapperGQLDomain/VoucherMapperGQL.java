package com.thws.eventmanager.infrastructure.GraphQL.Resolver.Mapper.MapperGQLDomain;

import com.thws.eventmanager.infrastructure.GraphQL.Models.VoucherGQL;
import com.thws.eventmanager.infrastructure.components.persistence.entities.VoucherEntity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class VoucherMapperGQL {
    DateTimeFormatter FORMATTER= DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    public VoucherEntity toEntity(VoucherGQL voucherGQL) {
        if (voucherGQL == null) return null;
        VoucherEntity voucher = new VoucherEntity();
        voucher.setCode(voucherGQL.getCode());
        voucher.setDiscountAmount((long)voucherGQL.getDiscountAmount());
        voucher.setCreatedAt(LocalDateTime.from(FORMATTER.parse(voucherGQL.getCreatedAt())));
        voucher.setMaxUses(voucherGQL.getMaxUses());
        voucher.setActive(voucherGQL.isActive());
        voucher.setRemainingUses(voucherGQL.getRemainingUses());
        if(voucherGQL.getId()!=null) voucher.setId(Long.parseLong(voucherGQL.getId()));

        return voucher;
    }

    public VoucherGQL toModelGQL(VoucherEntity voucher) {
        if(voucher == null) return null;
        VoucherGQL voucherGQL = new VoucherGQL();
        voucherGQL.setCode(voucher.getCode());
        voucherGQL.setDiscountAmount(voucher.getDiscountAmount().intValue());
        voucherGQL.setCreatedAt(voucher.getCreatedAt().format(FORMATTER));
        voucherGQL.setMaxUses(voucher.getMaxUses());
        voucherGQL.setActive(voucher.isActive());
        voucherGQL.setRemainingUses(voucher.getRemainingUses());
        voucherGQL.setId(String.valueOf(voucher.getId()));
        return voucherGQL;
    }
}
