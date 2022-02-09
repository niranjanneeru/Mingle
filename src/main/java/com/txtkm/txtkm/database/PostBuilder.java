package com.txtkm.txtkm.database;

import javafx.geometry.Pos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PostBuilder {
    private Post post;

    public PostBuilder(Post post) {
        this.post = post;
    }

    public ArrayList<Post> getAllPost() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM post JOIN profile ON post.author=profile.id JOIN usertable ON post.author=usertable.id";
        Statement statement = DatabaseConnection.getConnection().createStatement();
        return processResultSetPost(statement.executeQuery(sql));
    }

    public ArrayList<Post> getPostById(Profile profile) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM post JOIN profile ON post.author=profile.id JOIN usertable ON post.author=usertable.id where post.author= ?";
        PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement(sql);
        statement.setInt(1, profile.id);
        return processResultSetPost(statement.executeQuery());
    }

    private ArrayList<Post> processResultSetPost(ResultSet rs) throws SQLException, ClassNotFoundException {
        ArrayList<Post> result = new ArrayList<>();
        while (rs.next()) {
            Post post = new Post();
            post.id = rs.getInt("id");
            post.title = rs.getString("title");
            post.desc = rs.getString("desc");
            post.author = new Profile();
            post.author = new ProfileBuilder(new Profile())
                    .setId(rs.getInt("author"))
                    .setName(rs.getString("name"))
                    .setDesc(rs.getString("description"))
                    .build();
            post.populateTags();
            result.add(post);
        }
        return result;
    }
}
