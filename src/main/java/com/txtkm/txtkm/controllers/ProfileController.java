package com.txtkm.txtkm.controllers;

import com.txtkm.txtkm.database.Profile;
import com.txtkm.txtkm.utility.Utility;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class ProfileController {

    @FXML
    protected Label nameLabel;

//    @FXML
//    protected TextField nameTextField;

    @FXML
    protected ImageView profileImageView;

    protected void setProfile(Profile profile) {
        System.out.println(profile);
        nameLabel.setText(profile.getName());
        if (profile.getUrls() != null) {
            profileImageView.setImage(new Image(profile.getUrls().get(0)));
        }
    }

    @FXML
    protected void onClickTest(ActionEvent e) {
        System.out.println(Utility.profile);
    }
}
