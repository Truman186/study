package com.deyun.bean;

import java.util.List;

/**
 * Created by Administrator on 2020/7/10.
 */
public class Menu {
    private int id;
    private String title;
    private String path;
    private List<KidMenu> slist;
    public Menu(){}

    public Menu(String title, String path, List<KidMenu> slist) {
        this.title = title;
        this.path = path;
        this.slist = slist;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", path='" + path + '\'' +
                ", slist=" + slist +
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

    public List<KidMenu> getSlist() {
        return slist;
    }

    public void setSlist(List<KidMenu> slist) {
        this.slist = slist;
    }
}
