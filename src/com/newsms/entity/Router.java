package com.newsms.entity;

public class Router {
    private String url;
    private String filter;
    private String controller;

    @Override
    public String toString() {
        return "Router{" +
                "url='" + url + '\'' +
                ", filter='" + filter + '\'' +
                ", controller='" + controller + '\'' +
                '}';
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public String getController() {
        return controller;
    }

    public void setController(String controller) {
        this.controller = controller;
    }
}
