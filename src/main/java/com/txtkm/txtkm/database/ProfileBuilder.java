package com.txtkm.txtkm.database;

import com.txtkm.txtkm.exceptions.UserNotFoundException;

import java.sql.*;
import java.util.HashMap;

public class ProfileBuilder {
    private Profile profile;

    public ProfileBuilder(Profile profile) {
        this.profile = profile;
    }

    public Profile build() {
        return profile;
    }


    public ProfileBuilder setId(int id) {
        profile.id = id;
        return this;
    }

    public ProfileBuilder setName(String name) {
        profile.name = name;
        return this;
    }

    public ProfileBuilder setEmail(String email) {
        profile.email = email;
        return this;
    }

    public ProfileBuilder setPhone_no(String phone_no) {
        profile.phone_no = phone_no;
        return this;
    }


    public ProfileBuilder setYear(String year) {
        profile.year = year;
        return this;
    }

    public ProfileBuilder setBranch(String branch) {
        profile.branch = branch;
        return this;
    }

    public ProfileBuilder setDob(Date dob) {
        profile.dob = dob;
        return this;
    }

    public ProfileBuilder setDesc(String desc) {
        profile.desc = desc;
        return this;
    }

    public ProfileBuilder setTags(HashMap<Integer, String> tags) {
        profile.tags = tags;
        return this;
    }

    public ProfileBuilder setUrls(HashMap<Integer, String> urls) {
        profile.urls = urls;
        return this;
    }

    public ProfileBuilder populateData() throws SQLException, ClassNotFoundException, UserNotFoundException {
        assert (profile.id != null);
        populateUser();
        populateProfile();
        populateTags();
        populateImages();
        return this;
    }

    private ProfileBuilder populateImages() throws SQLException, ClassNotFoundException {
        assert (profile.id != null);

        String sql = "SELECT * FROM photos where id = ?";
        PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement(sql);
        statement.setInt(1, profile.id);
        ResultSet rs = statement.executeQuery();
        HashMap<Integer, String> links = new HashMap<>();
        int i = 0;
        while (rs.next()) {
            links.put(i, rs.getString("url"));
            i++;
        }
        profile.urls = links;

        return this;
    }

    private ProfileBuilder populateTags() throws SQLException, ClassNotFoundException {
        assert (profile.id != null);

        String sql = "SELECT * FROM tags WHERE tag IN (SELECT tag_id FROM profile_tags WHERE id = ?)";
        PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement(sql);
        statement.setInt(1, profile.id);
        ResultSet rs = statement.executeQuery();
        HashMap<Integer, String> tags = new HashMap<>();
        while (rs.next()) {
            tags.put(rs.getInt("tag"), rs.getString("title"));
        }
        profile.tags = tags;

        return this;
    }

    public ProfileBuilder populateUser() throws SQLException, ClassNotFoundException, UserNotFoundException {
        assert (profile.id != null);

        Connection connection = DatabaseConnection.getConnection();
        String sql = "SELECT * FROM usertable where id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, profile.id);
        ResultSet rs = statement.executeQuery();
        if (!rs.next()) {
            throw new UserNotFoundException();
        }
        profile.name = rs.getString("name");
        profile.email = rs.getString("email");

        return this;
    }

    public ProfileBuilder populateProfile() throws SQLException, ClassNotFoundException {
        assert (profile.id != null);

        Connection connection = DatabaseConnection.getConnection();
        String sql = "SELECT * FROM profile where id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, profile.id);
        ResultSet rs = statement.executeQuery();
        if (rs.next()) {
            profile.desc = rs.getString("description");
            profile.phone_no = rs.getString("phone_number");
            profile.year = rs.getString("year");
            profile.branch = rs.getString("branch");
            profile.dob = rs.getDate("dob");
        } else {
            profile.desc = null;
            profile.phone_no = null;
            profile.year = null;
            profile.branch = null;
            profile.dob = null;
        }

        return this;
    }

}
