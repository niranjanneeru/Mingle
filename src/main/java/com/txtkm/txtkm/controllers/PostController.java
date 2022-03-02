package com.txtkm.txtkm.controllers;

import com.txtkm.txtkm.Login;
import com.txtkm.txtkm.database.LoginPersistence;
import com.txtkm.txtkm.database.Post;
import com.txtkm.txtkm.database.PostBuilder;
import com.txtkm.txtkm.utility.Utility;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class PostController {
    @FXML
    protected ListView<String> postList;

    protected ArrayList<Post> posts;

    @FXML
    protected HBox logOut;

    @FXML
    protected HBox profileBox;

    @FXML
    protected HBox feedIcon;

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

        profileBox.setOnMouseClicked(mouseEvent -> {
            FXMLLoader fxmlLoader = new FXMLLoader(Login.class.getResource("profile.fxml"));
            Stage stage = (Stage) profileBox.getScene().getWindow();
            try {
                stage.setScene(new Scene(fxmlLoader.load(), 1000, 600));
            } catch (IOException e) {
                e.printStackTrace();
            }
            stage.show();
            ProfileController controller = fxmlLoader.getController();
            controller.setProfile(Utility.profile);
        });

        feedIcon.setOnMouseClicked(mouseEvent -> {
            FXMLLoader fxmlLoader = new FXMLLoader(Login.class.getResource("feed.fxml"));
            Stage window = (Stage) logOut.getScene().getWindow();
            try {
                window.setScene(new Scene(fxmlLoader.load(), 848, 546));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        logOut.setOnMouseClicked(mouseEvent -> {
            LoginPersistence.getPersistence().removePrefs();
            FXMLLoader fxmlLoader = new FXMLLoader(Login.class.getResource("login.fxml"));
            Stage window = (Stage) logOut.getScene().getWindow();
            try {
                window.setScene(new Scene(fxmlLoader.load(), 799, 494));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

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
