package com.zjs.example.doubanz.bean;

import java.util.List;

public class Top250Movie {
    private int count;
    private int start;
    private List<Subjects> subjects;


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

    public void setSubjects(List<Subjects> subjects) {
        this.subjects = subjects;
    }

    public List<Subjects> getSubjects() {
        return this.subjects;
    }

}

