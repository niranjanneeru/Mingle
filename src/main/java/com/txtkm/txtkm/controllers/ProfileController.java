package com.txtkm.txtkm.controllers;

import com.txtkm.txtkm.database.Profile;
import com.txtkm.txtkm.database.ProfileBuilder;
import com.txtkm.txtkm.exceptions.UserNotFoundException;
import com.txtkm.txtkm.utility.Utility;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javax.xml.transform.Source;
import java.sql.SQLException;

public class ProfileController {

    @FXML
    protected void onClickTest(ActionEvent e) {
        System.out.println(Utility.profile);
    }
}
