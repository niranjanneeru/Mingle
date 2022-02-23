package com.txtkm.txtkm;

import com.txtkm.txtkm.controllers.ProfileController;
import com.txtkm.txtkm.database.LoginPersistence;
import com.txtkm.txtkm.database.Profile;
import com.txtkm.txtkm.database.ProfileBuilder;
import com.txtkm.txtkm.exceptions.UserNotFoundException;
import com.txtkm.txtkm.utility.Utility;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.SQLException;

public class Login extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        String token = LoginPersistence.getPersistence().getPrefs(); // Prev Login
        boolean flag = false;
        if (token == null) {
            flag = true;
        } else {
            try {
                Utility.profile = new ProfileBuilder(new Profile()).setIdToken(token).populateData().build();
                FXMLLoader fxmlLoader = new FXMLLoader(Login.class.getResource("profile.fxml"));
                stage.setScene(new Scene(fxmlLoader.load(), 1000, 600));
                stage.show();
                ProfileController controller = fxmlLoader.getController();
                controller.setProfile(Utility.profile);
            } catch (SQLException | ClassNotFoundException | UserNotFoundException e) {
                flag = true;
            }
        }
        if (flag) {
            FXMLLoader fxmlLoader = new FXMLLoader(Login.class.getResource("login.fxml"));
//        stage.initStyle(StageStyle.UNDECORATED);
            Scene scene = new Scene(fxmlLoader.load(), 799, 494);
            stage.setTitle("Mingle");
            stage.setScene(scene);
            stage.show();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
