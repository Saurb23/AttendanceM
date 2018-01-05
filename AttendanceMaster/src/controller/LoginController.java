package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class LoginController {

    @FXML
    private JFXTextField userNameTxt;

    @FXML
    private Label titleLbl;

    @FXML
    private JFXPasswordField passwordTxt;

    @FXML
    private JFXButton submitBtn;

    @FXML
    void loginApp(ActionEvent event) {
    	
    }

}
