package com.zjs.example.doubanz.bean;

public class Casts {
    private String alt;

    private Avatars avatars;

    private String name;

    private String id;

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getAlt() {
        return this.alt;
    }

    public void setAvatars(Avatars avatars) {
        this.avatars = avatars;
    }

    public Avatars getAvatars() {
        return this.avatars;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Casts{" +
                "alt='" + alt + '\'' +
                ", avatars=" + avatars +
                ", name='" + name + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
