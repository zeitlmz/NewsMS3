package com.newsms.entity;

public class Router {
    private String name;
    private Boolean hasFilter;
    private String pagePath;
    private String reqType;
    private String controller;

    @Override
    public String toString() {
        return "Router{" +
                "name='" + name + '\'' +
                ", hasFilter=" + hasFilter +
                ", pagePath='" + pagePath + '\'' +
                ", reqType='" + reqType + '\'' +
                ", controller='" + controller + '\'' +
                '}';
    }

    public String getController() {
        return controller;
    }

    public void setController(String controller) {
        this.controller = controller;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getHasFilter() {
        return hasFilter;
    }

    public void setHasFilter(Boolean hasFilter) {
        this.hasFilter = hasFilter;
    }

    public String getPagePath() {
        return pagePath;
    }

    public void setPagePath(String pagePath) {
        this.pagePath = pagePath;
    }

    public String getReqType() {
        return reqType;
    }

    public void setReqType(String reqType) {
        this.reqType = reqType;
    }
}
