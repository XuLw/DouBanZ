package com.zjs.example.doubanz.bean;

import java.util.List;

public class SearchMovie {
    private int count;
    private int start;
    private int total;
    private List<MovieSubjects> subjects;

//    public SearchMovie() {
//        count = start = total = 0;
//        mSubjects = new ArrayList<>();
//    }
//
//    public SearchMovie(int count, int start, int total, ArrayList<MovieSubjects> mSubjects) {
//        this.count = count;
//        this.start = start;
//        this.total = total;
//        mSubjects = mSubjects;
//    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<MovieSubjects> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<MovieSubjects> subjects) {
        subjects = subjects;
    }

    @Override
    public String toString() {
        return "SearchMovie{" +
                "count=" + count +
                ", start=" + start +
                ", total=" + total +
                ", mSubjects=" + subjects +
                '}';
    }
}
