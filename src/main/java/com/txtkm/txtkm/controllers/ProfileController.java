package com.txtkm.txtkm.controllers;

import com.gluonhq.charm.glisten.control.TextField;
import com.txtkm.txtkm.database.Profile;
import com.txtkm.txtkm.utility.Utility;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Map;


public class ProfileController {

    @FXML
    protected Label nameLabel;

    @FXML
    protected TextField nameTextField;

    @FXML
    protected TextField descTextField;

    @FXML
    protected TextField yearTextField;

    @FXML
    protected TextField branchTextField;

    @FXML
    protected TextField phoneTextField;

    @FXML
    protected TextField mailTextField;

    @FXML
    protected TextField tagsTextField;

    @FXML
    protected ImageView profileImageView;

    public void setProfile(Profile profile) {
        nameLabel.setText(profile.getName());
        nameTextField.setText(profile.getName());
//        nameTextField.setDisable(true);
        descTextField.setText(profile.getDesc());
        yearTextField.setText(profile.getYear());
        branchTextField.setText(profile.getBranch());
        phoneTextField.setText(profile.getPhone_no());
        mailTextField.setText(profile.getEmail());
        mailTextField.setDisable(true);
        if (profile.getTags() != null) {
            StringBuilder res = new StringBuilder();
            for (Map.Entry<Integer, String> set : profile.getTags().entrySet()) {
                res.append(set.getValue()).append(" ");
            }
            tagsTextField.setText(res.toString());
        }
        if (profile.getUrls() != null) {
            profileImageView.setImage(new Image(profile.getUrls().get(0)));
        }
    }

    @FXML
    protected void onClickTest(ActionEvent e) {
        System.out.println(Utility.profile);
    }
}
