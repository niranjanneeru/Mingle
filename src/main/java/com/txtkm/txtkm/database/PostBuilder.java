package com.txtkm.txtkm.database;

import javafx.scene.control.TableRow;

import java.lang.reflect.Array;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

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

    public boolean createPost() throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO post (author, \"desc\", title) VALUES (?,?,?)";
        PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        statement.setInt(1, post.author.id);
        statement.setString(2, post.desc);
        statement.setString(3, post.title);
//        return statement.execute();
        statement.executeUpdate();
        ResultSet rs = statement.getGeneratedKeys();
        if (rs.next()) {
            int profile = rs.getInt(1);
            sql = "INSERT INTO post_tags VALUES (?,?)";
            statement = DatabaseConnection.getConnection().prepareStatement(sql);
            for (Integer id : post.tags.keySet()) {
                statement.setInt(1, profile);
                statement.setInt(2, id);

                statement.addBatch();
            }
            statement.executeBatch();
            return true;
        }
        return false;
    }

    public ArrayList<Post> generateFeed(Profile profile) throws SQLException, ClassNotFoundException {
        StringBuilder temp = new StringBuilder("tag_id = ");
        boolean flag = true;
        for (Integer i : profile.tags.keySet()) {
            if (flag) {
                flag = false;
                temp.append(i);
                continue;
            }
            temp.append(" OR tag_id = ");
            temp.append(i);
        }
        String sql = "SELECT * FROM post JOIN profile ON post.author=profile.id JOIN usertable ON " +
                "post.author=usertable.id WHERE post.id IN(SELECT id FROM post_tags WHERE " + temp + ")";
        System.out.println(sql);
        Statement statement = DatabaseConnection.getConnection().createStatement();
        ResultSet rs = statement.executeQuery(sql);
        return processResultSetPost(rs);
    }

    public PostBuilder setId(int id) {
        this.post.id = id;
        return this;
    }

    public PostBuilder setTitle(String title) {
        this.post.title = title;
        return this;
    }

    public PostBuilder setDesc(String desc) {
        this.post.desc = desc;
        return this;
    }

    public PostBuilder setAuthor(Profile author) {
        this.post.author = author;
        return this;
    }

    public PostBuilder setTags(HashMap<Integer, String> tags) {
        this.post.tags = tags;
        return this;
    }
}
