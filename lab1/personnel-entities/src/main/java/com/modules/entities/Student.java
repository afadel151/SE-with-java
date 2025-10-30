package com.modules.entities;


public class Student {
    private static final long serialVersionUID = 1L;

    
    private String name;
    private int id;
    private String major;
    private int year;

    public Student(String name, int id, String major, int year) {
        this.name = name;
        this.id = id;
        this.major = major;
        this.year = year;
    }
    public String getName() {
        return name;
    }
    public int getId() {
        return id;
    }
    public String getMajor() {
        return major;
    }
    public int getYear() {
        return year;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setMajor(String major) {
        this.major = major;
    }
    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Student{name='" + name + "', id=" + id + ", major='" + major + "', year=" + year + '}';
    }
    
}
