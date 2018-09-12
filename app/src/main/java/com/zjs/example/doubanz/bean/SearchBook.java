package com.zjs.example.doubanz.bean;

import java.util.List;

public class SearchBook {

    private int count;

    private int start;

    private int total;

    private List<BookSubject> books;

    public void setCount(int count) {
        this.count = count;
    }

    public int getCount() {
        return this.count;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getStart() {
        return this.start;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotal() {
        return this.total;
    }

    public void setBooks(List<BookSubject> books) {
        this.books = books;
    }

    public List<BookSubject> getBooks() {
        return this.books;
    }

    @Override
    public String toString() {
        return "SearchBook{" +
                "count=" + count +
                ", start=" + start +
                ", total=" + total +
                ", books=" + books +
                '}';
    }
}
