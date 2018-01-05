package controller;

import java.util.Optional;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import dao.CompanyDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import model.Company;

/****
 * 
 * @author Saurabh Gupta
 *
 */
public class CompanyController {
	
	Tooltip errorTip= new Tooltip();
	CompanyDAO companyDAO= new CompanyDAO();
	
    @FXML
    private Label companyLbl;

    @FXML
    private JFXTextField companyTxt;

    @FXML
    private JFXButton submitBtn;

    @FXML
    void createCompany(ActionEvent event) {
    	if(companyTxt.getText()==null||companyTxt.getText().isEmpty()) {
    		errorTip.setText("Please enter company Name");
    		companyTxt.setTooltip(errorTip);
    		errorTip.show(companyTxt, 200, 200);
    		return;
    	}
    	Company company= new Company();
    	company.setCompanyName(companyTxt.getText());
    	boolean result=companyDAO.createCompany(company);
    	if(result) {
    		Alert alert= new Alert(AlertType.INFORMATION,"Company created Successfully");
    		alert.setTitle("Success");
    		Optional<ButtonType> result1=alert.showAndWait();
    		if(result1.get()==ButtonType.OK) {
    			
    		}
    		
    	}else {
    		Alert alert= new Alert(AlertType.ERROR,"Error while inserting data");
    		alert.setTitle("Error");
    		alert.showAndWait();
    	}
    	
    }

}
