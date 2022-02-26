package com.txtkm.txtkm.controllers;

import com.txtkm.txtkm.Login;
import com.txtkm.txtkm.database.Post;
import com.txtkm.txtkm.database.PostBuilder;
import com.txtkm.txtkm.utility.Utility;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class PostController {
    @FXML
    protected ListView<String> postList;

    protected ArrayList<Post> posts;

    @FXML
    public void initialize() {
        PostBuilder builder = new PostBuilder(new Post());
        try {
            posts = builder.getPostById(Utility.profile);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        for (Post p : posts) {
            postList.getItems().add(p.getTitle() + ":" + p.getDesc());
        }
        postList.setOnMouseClicked(mouseEvent -> {
            FXMLLoader fxmlLoader = new FXMLLoader(Login.class.getResource("accepter.fxml"));
            Stage window = (Stage) postList.getScene().getWindow();
            try {
                window.setScene(new Scene(fxmlLoader.load(), 799, 494));
                AcceptorController controller = fxmlLoader.getController();
                builder.setId(posts.get(postList.getSelectionModel().getSelectedIndex()).getId());
                controller.setPost(builder.build());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
