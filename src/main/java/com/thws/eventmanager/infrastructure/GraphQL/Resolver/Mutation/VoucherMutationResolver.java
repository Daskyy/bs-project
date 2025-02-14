package com.thws.eventmanager.infrastructure.GraphQL.Resolver.Mutation;

import com.thws.eventmanager.domain.services.other.VoucherService;
import com.thws.eventmanager.infrastructure.GraphQL.InputModels.VoucherInput;
import com.thws.eventmanager.infrastructure.GraphQL.Models.VoucherGQL;
import com.thws.eventmanager.infrastructure.GraphQL.Resolver.Mapper.MapperGQLDomain.VoucherMapperGQL;
import com.thws.eventmanager.infrastructure.components.persistence.entities.VoucherEntity;
import graphql.kickstart.tools.GraphQLMutationResolver;

public class VoucherMutationResolver implements GraphQLMutationResolver {
    VoucherService voucherService = new VoucherService();
    VoucherMapperGQL voucherMapperGQL = new VoucherMapperGQL();

    public VoucherGQL createVoucher(VoucherInput input) {
        VoucherEntity voucherEntity = voucherService.createVoucher(input.getCode(), input.getDiscountAmount(), input.getMaxUses());
        return voucherMapperGQL.toModelGQL(voucherEntity);
    }

    public VoucherGQL deactivateVoucher(String id) {
        VoucherEntity voucherEntity = voucherService.getVoucherById(Long.parseLong(id));
        return voucherMapperGQL.toModelGQL(voucherService.deactivateVoucher(voucherEntity));
    }

    public VoucherGQL activateVoucher(String id) {
        VoucherEntity voucherEntity = voucherService.getVoucherById(Long.parseLong(id));
        return voucherMapperGQL.toModelGQL(voucherService.activateVoucher(voucherEntity));
    }

    public VoucherGQL updateVoucher(String id, VoucherInput input) {
        VoucherEntity voucherEntity = voucherService.getVoucherById(Long.parseLong(id));
        voucherEntity.setCode(input.getCode());
        voucherEntity.setDiscountAmount((long)input.getDiscountAmount());
        voucherEntity.setMaxUses(input.getMaxUses());
        return voucherMapperGQL.toModelGQL(voucherService.updateVoucher(voucherEntity));
    }

    public VoucherGQL deleteVoucher(String id) {
        return voucherMapperGQL.toModelGQL(voucherService.deleteVoucher(Long.parseLong(id)));
    }
}
