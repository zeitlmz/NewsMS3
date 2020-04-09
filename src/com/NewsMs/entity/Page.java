package com.NewsMs.entity;

import java.util.List;

public class Page {
    private Integer page;
    private Integer limit;
    private Integer count;
    private List<News> data;

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
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<News> getNews() {
        return data;
    }

    public void setNews(List<News> data) {
        this.data = data;
    }
}
