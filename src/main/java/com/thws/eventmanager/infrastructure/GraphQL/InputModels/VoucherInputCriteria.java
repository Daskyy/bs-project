package com.thws.eventmanager.infrastructure.GraphQL.InputModels;

import java.util.ArrayList;
import java.util.List;

public class VoucherInputCriteria {
    String code;
    int discountAmount;
    int maxUses;
    int remainingUses;
    boolean active;

    public VoucherInputCriteria(){}

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(int discountAmount) {
        this.discountAmount = discountAmount;
    }

    public int getMaxUses() {
        return maxUses;
    }

    public void setMaxUses(int maxUses) {
        this.maxUses = maxUses;
    }

    public List<String> getCriteria() {
        List<String> criteria = new ArrayList<>();
        if (code != null && !code.isBlank()) {
            criteria.add("code");
        }
        if (discountAmount > 0) {
            criteria.add("discountAmount");
        }
        if (maxUses > 0) {
            criteria.add("maxUses");
        }
        if (active) {
            criteria.add("active");
        }
        if (remainingUses > 0) {
            criteria.add("remainingUses");
        }
        return criteria;
    }

    public List<Object> getValues() {
        List<Object> values = new ArrayList<>();
        if (code != null && !code.isBlank()) {
            values.add(code);
        }
        if (discountAmount > 0) {
            values.add(discountAmount);
        }
        if (maxUses > 0) {
            values.add(maxUses);
        }
        if (active) {
            values.add(active);
        }
        if (remainingUses > 0) {
            values.add(remainingUses);
        }
        return values;
    }
}
