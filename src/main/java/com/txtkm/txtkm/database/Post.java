package com.txtkm.txtkm.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class Post {
    protected int id;
    protected String title;
    protected String desc;
    protected Profile author;
    protected HashMap<Integer, String> tags;


    public void populateTags() throws SQLException, ClassNotFoundException {
        this.tags = new HashMap<>();
        String sql = "SELECT * FROM tags WHERE tag IN (SELECT tag_id FROM post_tags WHERE id = ?)";
        PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            this.tags.put(rs.getInt("tag"), rs.getString("title"));
        }
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", desc='" + desc + '\'' +
                ", author=" + author +
                ", tags=" + tags +
                '}';
    }
}
