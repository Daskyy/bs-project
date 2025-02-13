package com.thws.eventmanager.infrastructure.GraphQL.InputModels;

public class PaginationInput {
    private Integer page;
    private Integer pageSize;

    public PaginationInput() {}

    public Integer getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
