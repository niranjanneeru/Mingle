package com.txtkm.txtkm.controllers;

import com.txtkm.txtkm.Login;
import com.txtkm.txtkm.database.Post;
import com.txtkm.txtkm.database.PostBuilder;
import com.txtkm.txtkm.exceptions.UserNotFoundException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.Buffer;
import java.sql.SQLException;

public class AcceptorController {

    @FXML
    protected ListView<String> requestList;

    @FXML
    protected TextField pname;

    @FXML
    protected TextField pdesc;

    @FXML
    protected Button raccept;

    @FXML
    protected Button rdecline;

    PostBuilder builder;

    public void setPost(Post post) {
        raccept.setDisable(true);
        rdecline.setDisable(true);
        builder = new PostBuilder(post);
        try {
            builder.generateRequests();
        } catch (SQLException | ClassNotFoundException | UserNotFoundException e) {
            e.printStackTrace();
        }

        for (Post.Request r : builder.build().getRequests()) {
            requestList.getItems().add(r.requester.getName());
        }

        requestList.setOnMouseClicked(mouseEvent -> {
            int i = requestList.getSelectionModel().getSelectedIndex();
            Post.Request r = builder.build().getRequests().get(i);
            pname.setText(r.requester.getName());
            pname.setEditable(false);
            pdesc.setText(r.desc);
            pdesc.setEditable(false);
            raccept.setDisable(false);
            rdecline.setDisable(false);
        });
    }

    @FXML
    protected void acceptClick(ActionEvent e) throws SQLException, ClassNotFoundException {
        int id = requestList.getSelectionModel().getSelectedIndex();
        Post.Request r = builder.build().getRequests().get(id);
        builder.setAccepted(r.id);
    }

    @FXML
    protected void declineClick(ActionEvent e) throws SQLException, ClassNotFoundException {
        int id = requestList.getSelectionModel().getSelectedIndex();
        Post.Request r = builder.build().getRequests().get(id);
        builder.setDeclined(r.id);
    }

    @FXML
    protected void backOnClick(ActionEvent e){
        FXMLLoader fxmlLoader = new FXMLLoader(Login.class.getResource("post.fxml"));
        Stage window = (Stage) rdecline.getScene().getWindow();
        try {
            window.setScene(new Scene(fxmlLoader.load(), 799, 494));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
