package com.newsms.entity;

import java.util.List;

public class Page {
    private Integer page;
    private Integer limit;
    private Integer count;
    private Object[] params;
    private Boolean isPrevious;
    private Boolean isNext;
    private List<News> data;

    public Object[] getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }

    public Boolean getPrevious() {
        return getPage() > 1;
    }

    public Boolean getNext() {
        return getPage() < getCount();
    }

    public Page() {
    }

    public Page(Integer page, Integer limit, Integer count, List<News> data) {
        this.page = page;
        this.limit = limit;
        this.count = count;
        this.data = data;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        if (page > 0) {
            this.page = page;
        }
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        if (limit > 0) {
            this.limit = limit;
        }
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        if (count > 0) {
            this.count = count % limit == 0 ? (count / limit) : (count / limit + 1);
        }else {
            this.count = 0;
        }
    }

    public List<News> getData() {
        return data;
    }

    public void setData(List<News> data) {
        this.data = data;
    }
}
