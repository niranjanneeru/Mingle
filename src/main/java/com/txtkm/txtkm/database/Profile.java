package com.txtkm.txtkm.database;

import java.sql.Date;
import java.util.HashMap;

public class Profile {
    protected Integer id;
    protected String name;
    protected String email;
    protected String phone_no;
    protected String year;
    protected String branch;
    protected Date dob;
    protected String desc;
    protected HashMap<Integer, String> tags;
    protected HashMap<Integer, String> urls;

    public Profile() {
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public String getYear() {
        return year;
    }

    public String getBranch() {
        return branch;
    }

    public Date getDob() {
        return dob;
    }

    public String getDesc() {
        return desc;
    }

    public HashMap<Integer, String> getTags() {
        return tags;
    }

    public HashMap<Integer, String> getUrls() {
        return urls;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone_no='" + phone_no + '\'' +
                ", year='" + year + '\'' +
                ", branch='" + branch + '\'' +
                ", dob=" + dob +
                ", desc='" + desc + '\'' +
                ", tags=" + tags +
                ", urls=" + urls +
                '}';
    }
}
