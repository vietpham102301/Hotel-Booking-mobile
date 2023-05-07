package com.example.hotelbooking.hotelinformation.model;

import java.util.List;

public class CommentList {
    private List<Comments> data;
    private int totalElement;
    private int totalPage;
    private int currentPage;
    private int size;

    public CommentList(List<Comments> data, int totalElement, int totalPage, int currentPage, int size) {
        this.data = data;
        this.totalElement = totalElement;
        this.totalPage = totalPage;
        this.currentPage = currentPage;
        this.size = size;
    }

    public List<Comments> getData() {
        return data;
    }

    public void setData(List<Comments> data) {
        this.data = data;
    }

    public int getTotalElement() {
        return totalElement;
    }

    public void setTotalElement(int totalElement) {
        this.totalElement = totalElement;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
