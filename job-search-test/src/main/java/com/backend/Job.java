package com.backend;

public class Job implements com.backend.interfaces.Job {

    private String title;
    private String company;
    private String location;
    private String date;
    private String url;

    public Job(String title, String company, String location, String date) {
        this.title = title;
        this.company = company;
        this.location = location;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @Override
    public void favorite() {

    }

    @Override
    public void apply() {

    }
}
