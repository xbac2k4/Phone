package com.example.dienthoai.Model;

import java.util.ArrayList;

public class ResponseData<T> {
    private T categories; // Đây là danh sách điện thoại
    private int totalPages;

    public T getCategories() {
        return categories;
    }

    public void setCategories(T categories) {
        this.categories = categories;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}
