package com.txtkm.txtkm.controllers;

import com.gluonhq.charm.glisten.control.TextField;
import com.txtkm.txtkm.Login;
import com.txtkm.txtkm.database.Post;
import com.txtkm.txtkm.database.PostBuilder;
import com.txtkm.txtkm.utility.Utility;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class FormController {

    @FXML
    protected HBox back;

    @FXML
    protected TextField nameTextField;

    @FXML
    protected TextField descTextField;

    @FXML
    protected Button post;

    @FXML
    public void initialize() {
        back.setOnMouseClicked(mouseEvent -> {
            FXMLLoader fxmlLoader = new FXMLLoader(Login.class.getResource("feed.fxml"));
            Stage window = (Stage) back.getScene().getWindow();
            try {
                window.setScene(new Scene(fxmlLoader.load(), 848, 546));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        post.setOnMouseClicked(mouseEvent -> {
            String title = nameTextField.getText();
            String desc = descTextField.getText();
            if (title.isBlank() || desc.isBlank()) {
                return;
            }
            PostBuilder builder = new PostBuilder(new Post());
            try {
                builder.setAuthor(Utility.profile)
                        .setTitle(title)
                        .setDesc(desc)
                        .setTags(Utility.profile.getTags())
                        .createPost();
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            FXMLLoader fxmlLoader = new FXMLLoader(Login.class.getResource("feed.fxml"));
            Stage window = (Stage) back.getScene().getWindow();
            try {
                window.setScene(new Scene(fxmlLoader.load(), 848, 546));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
