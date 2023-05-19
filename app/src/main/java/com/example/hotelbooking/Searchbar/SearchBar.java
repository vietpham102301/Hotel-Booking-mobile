package com.example.hotelbooking.Searchbar;

import java.util.List;

public class SearchBar {
    private String status;
    private String message;

    public List<Listsearch> getData() {
        return data;
    }

    public void setData(List<Listsearch> data) {
        this.data = data;
    }

    private List<Listsearch> data;

    public SearchBar(String status, String message, List<Listsearch> data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
