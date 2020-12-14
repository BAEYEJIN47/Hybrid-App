package com.example.dit;

public class Report {

    String report;
    String name;
    String date;

    public Report(String report, String name, String date) {
        this.report = report;
        this.name = name;
        this.date = date;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
