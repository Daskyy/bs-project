package com.thws.eventmanager.infrastructure.GraphQL.Resolver.Query;

import com.thws.eventmanager.domain.services.other.VoucherService;
import com.thws.eventmanager.infrastructure.GraphQL.InputModels.PaginationInput;
import com.thws.eventmanager.infrastructure.GraphQL.InputModels.VoucherInput;
import com.thws.eventmanager.infrastructure.GraphQL.InputModels.VoucherInputCriteria;
import com.thws.eventmanager.infrastructure.GraphQL.Models.VoucherGQL;
import com.thws.eventmanager.infrastructure.GraphQL.Resolver.Mapper.MapperGQLDomain.VoucherMapperGQL;
import com.thws.eventmanager.infrastructure.components.persistence.adapter.VoucherHandler;
import com.thws.eventmanager.infrastructure.components.persistence.entities.VoucherEntity;
import graphql.kickstart.tools.GraphQLQueryResolver;

import java.util.List;

public class VoucherQueryResolver implements GraphQLQueryResolver {
    VoucherService voucherService = new VoucherService();
    VoucherMapperGQL voucherMapperGQL = new VoucherMapperGQL();

    public VoucherGQL voucher(String id) {
        VoucherEntity voucherEntity = voucherService.getVoucherById(Long.parseLong(id));
        return voucherMapperGQL.toModelGQL(voucherEntity);
    }

    public List<VoucherGQL> vouchers(VoucherInputCriteria criteria, PaginationInput page) {
        int safePage = (page != null && page.getPage() != null) ? page.getPage() : 1;
        int safePageSize = (page != null && page.getPageSize() != null) ? page.getPageSize() : Integer.MAX_VALUE;
        if (criteria == null) {
            return voucherService.getAllVouchers(List.of(), List.of(), safePage, safePageSize).stream()
                    .map(voucherMapperGQL::toModelGQL)
                    .toList();
        }

        List<String> criteriaList = criteria.getCriteria();
        List<Object> valuesList = criteria.getValues();

        if (criteriaList.size() != valuesList.size()) {
            throw new IllegalArgumentException("Criteria and values lists must have the same size");
        } else {
            return voucherService.getAllVouchers(criteriaList, valuesList, safePage, safePageSize).stream()
                    .map(voucherMapperGQL::toModelGQL)
                    .toList();
        }
    }
}
