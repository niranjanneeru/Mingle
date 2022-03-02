package com.txtkm.txtkm.controllers;

import com.jfoenix.controls.JFXListView;
import com.txtkm.txtkm.Login;
import com.txtkm.txtkm.database.LoginPersistence;
import com.txtkm.txtkm.database.Post;
import com.txtkm.txtkm.database.PostBuilder;
import com.txtkm.txtkm.utility.Utility;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class FeedController {

    @FXML
    protected HBox profileBox;

    @FXML
    protected HBox postBox;

    @FXML
    protected HBox logOut;

    @FXML
    public void initialize() {
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

        postBox.setOnMouseClicked(mouseEvent -> {
            FXMLLoader fxmlLoader = new FXMLLoader(Login.class.getResource("post.fxml"));
            Stage window = (Stage) postBox.getScene().getWindow();
            try {
                window.setScene(new Scene(fxmlLoader.load(), 1000, 600));
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
    }
}
