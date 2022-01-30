package com.txtkm.txtkm;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class LoginController {
    @FXML
    private Button closeButton;

    @FXML
    private Label loginMessageLabel;

    @FXML
    private TextField usernameTextField;

    @FXML
    private TextField passwordTextField;

    private Color red = new Color(1, 0, 0, 1);


    @FXML
    protected void onClickLoginButton(ActionEvent e) {
        String username = usernameTextField.getText().trim();
        String password = passwordTextField.getText().trim();
        if (username.isBlank() || password.isBlank()) {
            loginMessageLabel.setText("Empty Credentials");
            loginMessageLabel.setTextFill(red);
            return;
        }
    }

    @FXML
    protected void onClickCloseButton(ActionEvent e) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}