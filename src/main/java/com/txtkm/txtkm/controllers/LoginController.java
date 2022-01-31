package com.txtkm.txtkm.controllers;

import com.txtkm.txtkm.database.DatabaseConnection;
import com.txtkm.txtkm.database.LoginNetwork;
import com.txtkm.txtkm.utility.Utility;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;

public class LoginController {
    @FXML
    private Button closeButton;

    @FXML
    private Label loginMessageLabel;

    @FXML
    private TextField usernameTextField;

    @FXML
    private TextField passwordTextField;

    private final Color red = new Color(1, 0, 0, 1);


    @FXML
    protected void onClickLoginButton(ActionEvent e) {
        String username = usernameTextField.getText().trim();
        String password = passwordTextField.getText().trim();

        //  empty creds
        if (username.isBlank() || password.isBlank()) {
            setErrorMessage(loginMessageLabel, "Empty Credentials");
            return;
        }

        // validate email
        if (!Utility.emailPatternMatches(username)) {
            setErrorMessage(loginMessageLabel, "Email Address Invalid");
            return;
        }

        login(username, password);
    }

    private void login(String username, String password) {
        try {
            Connection connection = DatabaseConnection.getConnection();
            LoginNetwork loginNetwork = new LoginNetwork(username, password);
            switch (loginNetwork.checkValidLogin(connection)) {
                case -1:
                    setErrorMessage(loginMessageLabel, "Try Signing In");
                    break;
                case 0:
                    setErrorMessage(loginMessageLabel, "Authentication Failed");
                    break;
                case 1:
                    // login successful to next page
                    setErrorMessage(loginMessageLabel, "Successful");
            }
        } catch (ClassNotFoundException | SQLException | NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
    }

    private void setErrorMessage(Label loginMessageLabel, String email_address_invalid) {
        loginMessageLabel.setText(email_address_invalid);
        loginMessageLabel.setTextFill(red);
    }

    @FXML
    protected void onClickCloseButton(ActionEvent e) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}