package com.deyun.bean;

/**
 * Created by Administrator on 2020/7/10.
 */
//分支导航
public class KidMenu {
    private int id;
    private String title;
    private String path;
    public KidMenu(){}

    public KidMenu(String title, String path) {
        this.title = title;
        this.path = path;
    }

    @Override
    public String toString() {
        return "KidMenu{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", path='" + path + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
