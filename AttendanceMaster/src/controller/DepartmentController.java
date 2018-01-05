package controller;


import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import application.Main;
import dao.DepartmentDAO;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import model.Department;

/****
 * 
 * @author Saurabh Gupta
 *
 */

public class DepartmentController {
	
	Tooltip errorTip= new Tooltip();
	List<Department> departMentList;
	JFXTextField deptTxt;
	JFXTextField sunHourInTime;
	JFXTextField sunMinInTime;
	JFXTextField sunHourOutTime;
	JFXTextField sunMinOutTime;
	JFXTextField monHourInTime;
	JFXTextField monMinInTime;
	JFXTextField monHourOutTime;
	JFXTextField monMinOutTime;
	JFXTextField tuesHourInTime;
	JFXTextField tuesMinInTime;
	JFXTextField tuesHourOutTime;
	JFXTextField tuesMinOutTime;
	JFXTextField wedHourInTime;
	JFXTextField wedMinInTime;
	JFXTextField wedHourOutTime;
	JFXTextField wedMinOutTime;
	JFXTextField thursHourInTime;
	JFXTextField thursMinInTime;
	JFXTextField thursHourOutTime;
	JFXTextField thursMinOutTime;
	JFXTextField friHourInTime;
	JFXTextField friMinInTime;
	JFXTextField friHourOutTime;
	JFXTextField FriMinOutTime;
	JFXTextField satHourInTime;
	JFXTextField satMinInTime;
	JFXTextField satHourOutTime;
	JFXTextField satMinOutTime;

	JFXComboBox<String> sunInAmPmCombo;
	JFXComboBox<String> sunOutAmPmCombo;
	JFXComboBox<String> monInAmPmCombo;
	JFXComboBox<String> monOutAmPmCombo;
	JFXComboBox<String> tuesInAmPmCombo;
	JFXComboBox<String>	tuesOutAmPmCombo;
	JFXComboBox<String> wedInAmPmCombo;
	JFXComboBox<String> wedOutAmPmCombo;
	JFXComboBox<String> thurInAmPmCombo;
	JFXComboBox<String> thurOutAmPmCombo;
	JFXComboBox<String> friInAmPmCombo;
	JFXComboBox<String> friOutAmPmCombo;
	JFXComboBox<String> satInAmPmCombo;
	JFXComboBox<String> satOutAmPmCombo;
//	JFXComboBox<String> sunAmPmCombo;
	
	
	DepartmentDAO departmentDAO= new DepartmentDAO();

	public GridPane addDepartment() {
		
		departMentList=departmentDAO.getDepartmentList();
		GridPane gp= new GridPane();

//		gp.setMinSize(800, 600);
		gp.setPadding(new Insets(0,10,0,10));
		gp.getStyleClass().add("grid");
		gp.setAlignment(Pos.CENTER);
//		gp.setHgap(10);
		gp.setVgap(10);
		gp.setGridLinesVisible(false);
		
		Label titleLbl= new Label("Add New Department");
		titleLbl.setStyle("-fx-font-size:20;-fx-text-fill:black;-fx-font-weight:bold");
		GridPane.setMargin(titleLbl, new Insets(0,-180,20,180));
		gp.add(titleLbl, 0, 0);
		
		
		deptTxt= new JFXTextField();
		deptTxt.setPromptText("Department Name");
		deptTxt.setPrefWidth(350);
		deptTxt.setLabelFloat(true);
		
		Label mondayLbl= new Label("Monday");
		Label sundayLbl= new Label("Sunday");
		Label tuesdayLbl= new Label("Tuesday");
		Label wednesdayLbl= new Label("Wednesday");
		Label thursdayLbl= new Label("Thursday");
		Label fridayLbl= new Label("Friday");
		Label saturdayLbl= new Label("Saturday");
		
		
		Label colonLbl1= new Label(":");
		colonLbl1.setStyle("-fx-font-size:20;");
		Label colonLbl2= new Label(":");
		colonLbl2.setStyle("-fx-font-size:20;");
		Label colonLbl3= new Label(":");
		colonLbl3.setStyle("-fx-font-size:20;");
		Label colonLbl4= new Label(":");
		colonLbl4.setStyle("-fx-font-size:20;");
		Label colonLbl5= new Label(":");
		colonLbl5.setStyle("-fx-font-size:20;");
		Label colonLbl6= new Label(":");
		colonLbl6.setStyle("-fx-font-size:20;");
		Label colonLbl7= new Label(":");
		colonLbl7.setStyle("-fx-font-size:20;");
		Label colonLbl8= new Label(":");
		colonLbl8.setStyle("-fx-font-size:20;");
		Label colonLbl9= new Label(":");
		colonLbl9.setStyle("-fx-font-size:20;");
		Label colonLbl10= new Label(":");
		colonLbl10.setStyle("-fx-font-size:20;");
		Label colonLbl11= new Label(":");
		colonLbl11.setStyle("-fx-font-size:20;");
		Label colonLbl12= new Label(":");
		colonLbl12.setStyle("-fx-font-size:20;");
		Label colonLbl13= new Label(":");
		colonLbl13.setStyle("-fx-font-size:20;");
		Label colonLbl14= new Label(":");
		colonLbl14.setStyle("-fx-font-size:20;");
		
		sunInAmPmCombo= new JFXComboBox<>();
		sunInAmPmCombo.getItems().addAll("AM","PM");
		sunInAmPmCombo.setValue("PM");
		
		sunOutAmPmCombo= new JFXComboBox<>();
		sunOutAmPmCombo.getItems().addAll("AM","PM");
		sunOutAmPmCombo.setValue("PM");
		
		monInAmPmCombo= new JFXComboBox<>();
		monInAmPmCombo.getItems().addAll("AM","PM");
		monInAmPmCombo.setValue("PM");
		
		monOutAmPmCombo= new JFXComboBox<>();
		monOutAmPmCombo.getItems().addAll("AM","PM");
		monOutAmPmCombo.setValue("PM");
		
		tuesInAmPmCombo= new JFXComboBox<>();
		tuesInAmPmCombo.getItems().addAll("AM","PM");
		tuesInAmPmCombo.setValue("PM");
		
		tuesOutAmPmCombo= new JFXComboBox<>();
		tuesOutAmPmCombo.getItems().addAll("AM","PM");
		tuesOutAmPmCombo.setValue("PM");
		
		wedInAmPmCombo= new JFXComboBox<>();
		wedInAmPmCombo.getItems().addAll("AM","PM");
		wedInAmPmCombo.setValue("PM");
		
		wedOutAmPmCombo= new JFXComboBox<>();
		wedOutAmPmCombo.getItems().addAll("AM","PM");
		wedOutAmPmCombo.setValue("PM");
		
		thurInAmPmCombo= new JFXComboBox<>();
		thurInAmPmCombo.getItems().addAll("AM","PM");
		thurInAmPmCombo.setValue("PM");
		
		thurOutAmPmCombo= new JFXComboBox<>();
		thurOutAmPmCombo.getItems().addAll("AM","PM");
		thurOutAmPmCombo.setValue("PM");
		
		friInAmPmCombo= new JFXComboBox<>();
		friInAmPmCombo.getItems().addAll("AM","PM");
		friInAmPmCombo.setValue("PM");
		
		friOutAmPmCombo= new JFXComboBox<>();
		friOutAmPmCombo.getItems().addAll("AM","PM");
		friOutAmPmCombo.setValue("PM");
		
		satInAmPmCombo= new JFXComboBox<>();
		satInAmPmCombo.getItems().addAll("AM","PM");
		satInAmPmCombo.setValue("PM");
		
		satOutAmPmCombo= new JFXComboBox<>();
		satOutAmPmCombo.getItems().addAll("AM","PM");
		satOutAmPmCombo.setValue("PM");
		
		HBox sundayHB= new HBox();
		sundayHB.setSpacing(5);
		sunHourInTime= new JFXTextField();
//		sunHourInTime.setLabelFloat(true);
		sunHourInTime.setText("00");
		sunHourInTime.setPrefWidth(50);
		sunMinInTime= new JFXTextField();
		sunMinInTime.setText("00");
		sunMinInTime.setPrefWidth(50);
		sunHourOutTime= new JFXTextField();
		sunHourOutTime.setText("00");
		sunHourOutTime.setPrefWidth(50);
		sunMinOutTime= new JFXTextField();
		sunMinOutTime.setText("00");
		sunMinOutTime.setPrefWidth(50);

		HBox.setMargin(sunHourOutTime,  new Insets(0,0,0,30));
		HBox.setMargin(colonLbl1, new Insets(5,0,0,0));
		HBox.setMargin(colonLbl2, new Insets(5,0,0,0));
		sundayHB.getChildren().addAll(sunHourInTime,colonLbl1,sunMinInTime,sunInAmPmCombo,sunHourOutTime,colonLbl2,sunMinOutTime,sunOutAmPmCombo);
		
		HBox mondayHB= new HBox();
		mondayHB.setSpacing(5);
		monHourInTime= new JFXTextField();
		monHourInTime.setText("00");
		monHourInTime.setPrefWidth(50);
		monMinInTime= new JFXTextField();
		monMinInTime.setText("00");
		monMinInTime.setPrefWidth(50);
		monHourOutTime= new JFXTextField();
		monHourOutTime.setText("00");
		monHourOutTime.setPrefWidth(50);
		monMinOutTime= new JFXTextField();
		monMinOutTime.setText("00");
		monMinOutTime.setPrefWidth(50);

		HBox.setMargin(monHourOutTime,  new Insets(0,0,0,30));
		HBox.setMargin(colonLbl3, new Insets(5,0,0,0));
		HBox.setMargin(colonLbl4, new Insets(5,0,0,0));
		mondayHB.getChildren().addAll(monHourInTime,colonLbl3,monMinInTime,monInAmPmCombo,monHourOutTime,colonLbl4,monMinOutTime,monOutAmPmCombo);
		

		HBox tuesdayHB= new HBox();
		tuesdayHB.setSpacing(5);
		tuesHourInTime= new JFXTextField();
		tuesHourInTime.setText("00");
		tuesHourInTime.setPrefWidth(50);
		tuesMinInTime= new JFXTextField();
		tuesMinInTime.setText("00");
		tuesMinInTime.setPrefWidth(50);
		tuesHourOutTime= new JFXTextField();
		tuesHourOutTime.setText("00");
		tuesHourOutTime.setPrefWidth(50);
		tuesMinOutTime= new JFXTextField();
		tuesMinOutTime.setText("00");
		tuesMinOutTime.setPrefWidth(50);

		tuesMinOutTime.setText("00");
		tuesMinOutTime.setPrefWidth(50);
		HBox.setMargin(tuesHourOutTime,  new Insets(0,0,0,30));
		HBox.setMargin(colonLbl5, new Insets(5,0,0,0));
		HBox.setMargin(colonLbl6,new Insets(5,0,0,0));
		tuesdayHB.getChildren().addAll(tuesHourInTime,colonLbl5,tuesMinInTime,tuesInAmPmCombo,tuesHourOutTime,colonLbl6,tuesMinOutTime,tuesOutAmPmCombo);
		
		HBox wednesdayHB= new HBox();
		wednesdayHB.setSpacing(5);
		wedHourInTime= new JFXTextField();
		wedHourInTime.setText("00");
		wedHourInTime.setPrefWidth(50);
		wedMinInTime= new JFXTextField();
		wedMinInTime.setText("00");
		wedMinInTime.setPrefWidth(50);
		wedHourOutTime= new JFXTextField();
		wedHourOutTime.setText("00");
		wedHourOutTime.setPrefWidth(50);
		wedMinOutTime= new JFXTextField();
		wedMinOutTime.setText("00");
		wedMinOutTime.setPrefWidth(50);

		HBox.setMargin(wedHourOutTime,  new Insets(0,0,0,30));
		HBox.setMargin(colonLbl7,new Insets(5,0,0,0));
		HBox.setMargin(colonLbl8,new Insets(5,0,0,0));
		wednesdayHB.getChildren().addAll(wedHourInTime,colonLbl7,wedMinInTime,wedInAmPmCombo,wedHourOutTime,colonLbl8,wedMinOutTime,wedOutAmPmCombo);
		
		HBox thursdayHB= new HBox();
		thursdayHB.setSpacing(5);
		thursHourInTime= new JFXTextField();
		thursHourInTime.setText("00");
		thursHourInTime.setPrefWidth(50);
		thursMinInTime= new JFXTextField();
		thursMinInTime.setText("00");
		thursMinInTime.setPrefWidth(50);
		thursHourOutTime= new JFXTextField();
		thursHourOutTime.setText("00");
		thursHourOutTime.setPrefWidth(50);
		thursMinOutTime= new JFXTextField();
		thursMinOutTime.setText("00");
		thursMinOutTime.setPrefWidth(50);
		HBox.setMargin(thursHourOutTime,  new Insets(0,0,0,30));
		HBox.setMargin(colonLbl9, new Insets(5,0,0,0));
		HBox.setMargin(colonLbl10, new Insets(5,0,0,0));
		thursdayHB.getChildren().addAll(thursHourInTime,colonLbl9,thursMinInTime,thurInAmPmCombo,thursHourOutTime,colonLbl10,thursMinOutTime,thurOutAmPmCombo);
		
		HBox fridayHB= new HBox();
		fridayHB.setSpacing(5);
		friHourInTime= new JFXTextField();
		friHourInTime.setText("00");
		friHourInTime.setPrefWidth(50);
		friMinInTime= new JFXTextField();
		friMinInTime.setText("00");
		friMinInTime.setPrefWidth(50);
		friHourOutTime= new JFXTextField();
		friHourOutTime.setText("00");
		friHourOutTime.setPrefWidth(50);
		FriMinOutTime= new JFXTextField();
		FriMinOutTime.setText("00");
		FriMinOutTime.setPrefWidth(50);

		HBox.setMargin(friHourOutTime,  new Insets(0,0,0,30));
		HBox.setMargin(colonLbl11, new Insets(5,0,0,0));
		HBox.setMargin(colonLbl12, new Insets(5,0,0,0));
		fridayHB.getChildren().addAll(friHourInTime,colonLbl11,friMinInTime,friInAmPmCombo,friHourOutTime,colonLbl12,FriMinOutTime,friOutAmPmCombo);
		
		HBox saturdayHB= new HBox();
		saturdayHB.setSpacing(5);
		satHourInTime= new JFXTextField();
		satHourInTime.setText("00");
		satHourInTime.setPrefWidth(50);
		satMinInTime= new JFXTextField();
		satMinInTime.setText("00");
		satMinInTime.setPrefWidth(50);
		satHourOutTime= new JFXTextField();
		satHourOutTime.setText("00");
		satHourOutTime.setPrefWidth(50);
		satMinOutTime= new JFXTextField();
		satMinOutTime.setText("00");
		satMinOutTime.setPrefWidth(50);

		HBox.setMargin(satHourOutTime, new Insets(0,0,0,30));
		HBox.setMargin(colonLbl13, new Insets(5,0,0,0));
		HBox.setMargin(colonLbl14, new Insets(5,0,0,0));
		saturdayHB.getChildren().addAll(satHourInTime,colonLbl13,satMinInTime,satInAmPmCombo,satHourOutTime,colonLbl14,satMinOutTime,satOutAmPmCombo);
		
		Label inTimeLbl= new Label("In Time");
		Label outTimeLbl= new Label("Out Time");
		HBox inOutHB= new HBox();
		inOutHB.setSpacing(200);
		inOutHB.getChildren().addAll(inTimeLbl,outTimeLbl);
		GridPane.setMargin(deptTxt, new Insets(0,-20,0,20));
		gp.add(deptTxt, 0, 1,2,1);
		
		GridPane.setMargin(inOutHB, new Insets(0,-40,0,40));
		gp.add(inOutHB, 1, 2);
		GridPane.setMargin(sundayLbl, new Insets(0,-20,0,20));
		gp.add(sundayLbl, 0, 4);
		
		gp.add(sundayHB, 1, 4);
		GridPane.setMargin(mondayLbl, new Insets(0,-20,0,20));
		gp.add(mondayLbl, 0, 5);
		gp.add(mondayHB, 1, 5);
		GridPane.setMargin(tuesdayLbl, new Insets(0,-20,0,20));
		gp.add(tuesdayLbl, 0, 6);
		gp.add(tuesdayHB, 1, 6);
		GridPane.setMargin(wednesdayLbl, new Insets(0,-20,0,20));
		gp.add(wednesdayLbl, 0, 7);
		gp.add(wednesdayHB, 1, 7);
		GridPane.setMargin(thursdayLbl, new Insets(0,-20,0,20));
		gp.add(thursdayLbl, 0, 8);
		gp.add(thursdayHB, 1, 8);
		GridPane.setMargin(fridayLbl, new Insets(0,-20,0,20));
		gp.add(fridayLbl, 0, 9);
		gp.add(fridayHB, 1, 9);
		GridPane.setMargin(saturdayLbl, new Insets(0,-20,0,20));
		gp.add(saturdayLbl, 0, 10);
		gp.add(saturdayHB, 1, 10);
		
		JFXButton submitBtn= new JFXButton("Submit");
		
		SimpleDateFormat format= new SimpleDateFormat("hh:mm:ss a");
		submitBtn.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				if(!validateDepartment()) {
					return;
				}
				
				Department department= new Department();
				department.setDeptName(deptTxt.getText());
				for(Department d:departMentList) {
					if(d.getDeptName().equalsIgnoreCase(deptTxt.getText())) {
						errorTip.setText("Department '"+deptTxt.getText()+"' Already existed");
						deptTxt.setTooltip(errorTip);
						errorTip.show(deptTxt, 200, 100);
						deptTxt.requestFocus();
						return;
					}
				}
				try {
//					String time="08:00:00 AM";
//					Date date= format.parse(time);
					Date sunIndate=format.parse(sunHourInTime.getText()+":"+sunMinInTime.getText()+":00 "+sunInAmPmCombo.getValue());
					Date sunOutdate=format.parse(sunHourOutTime.getText()+":"+sunMinOutTime.getText()+":00 "+sunOutAmPmCombo.getValue());
					Date monInDate=format.parse(monHourInTime.getText()+":"+monMinInTime.getText()+":00 "+monInAmPmCombo.getValue());
					Date monOutDate= format.parse(monHourOutTime.getText()+":"+monMinOutTime.getText()+":00 "+monOutAmPmCombo.getValue());
					Date tueInDate= format.parse(tuesHourInTime.getText()+":"+tuesMinInTime.getText()+":00 "+tuesInAmPmCombo.getValue());
					Date tueOutDate= format.parse(tuesHourOutTime.getText()+":"+tuesMinOutTime.getText()+":00 "+tuesOutAmPmCombo.getValue());
					Date wedInDate= format.parse(wedHourInTime.getText()+":"+wedMinInTime.getText()+":00 "+wedInAmPmCombo.getValue());
					Date wedOutDate= format.parse(wedHourOutTime.getText()+":"+wedMinOutTime.getText()+":00 "+wedOutAmPmCombo.getValue());
					Date thurInDate= format.parse(thursHourInTime.getText()+":"+thursMinInTime.getText()+":00 "+thurInAmPmCombo.getValue());
					Date thurOutDate= format.parse(thursHourOutTime.getText()+":"+thursMinOutTime.getText()+":00 "+thurOutAmPmCombo.getValue());
					Date friInDate= format.parse(friHourInTime.getText()+":"+friMinInTime.getText()+":00 "+friInAmPmCombo.getValue());
					Date friOutDate= format.parse(friHourOutTime.getText()+":"+FriMinOutTime.getText()+":00 "+friOutAmPmCombo.getValue());
					Date satInDate= format.parse(satHourInTime.getText()+":"+satMinInTime.getText()+":00 "+satInAmPmCombo.getValue());
					Date satOutDate= format.parse(satHourOutTime.getText()+":"+satMinOutTime.getText()+":00 "+satOutAmPmCombo.getValue());
					
					department.setSunInTime(new Time(sunIndate.getTime()));
					department.setSunOutTime(new Time(sunOutdate.getTime()));
					
					department.setMonInTime(new Time(monInDate.getTime()));
					department.setMonOutTIme(new Time(monOutDate.getTime()));
					
					department.setTuesInTIme(new Time(tueInDate.getTime()));
					department.setTuesOutTime(new Time(tueOutDate.getTime()));
					
					department.setWedInTime(new Time(wedInDate.getTime()));
					department.setWedOutTime(new Time(wedOutDate.getTime()));
					
					department.setThursInTime(new Time(thurInDate.getTime()));
					department.setThursOutTime(new Time(thurOutDate.getTime()));
					
					
					department.setFriInTime(new Time(friInDate.getTime()));
					department.setFriOutTime(new Time(friOutDate.getTime()));
					
					
					department.setSatInTime(new Time(satInDate.getTime()));
					department.setSatOutTime(new Time(satOutDate.getTime()));
					
					boolean result=departmentDAO.createDepartment(department);
					if(result) {
						Alert alert= new Alert(AlertType.INFORMATION,"You have successfullly added new Department");
						alert.setTitle("Success");
						alert.showAndWait();
						Main.dialog.close();
					}else {
						Alert alert= new Alert(AlertType.ERROR,"Error while adding data..Please try again");
						alert.setTitle("Error");
						alert.showAndWait();
					}
					
					
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		GridPane.setMargin(submitBtn, new Insets(20,-200,0,200));
		gp.add(submitBtn, 0, 12);
		
		return gp;
		
	}
	
	public boolean validateDepartment() {
		if(deptTxt.getText()==null||deptTxt.getText().isEmpty()) {
			errorTip.setText("Please enter department name");
			deptTxt.setTooltip(errorTip);
			errorTip.show(deptTxt, 300, 100);
			deptTxt.requestFocus();
			return false;
		}
//		if(monHourInTime.getText()==null||monHourInTime.getText().isEmpty()) {
//			errorTip.setText("Please enter monday In Time");
//			monHourInTime.setTooltip(errorTip);
//			errorTip.show(monHourInTime, 500, 250);
//			monHourInTime.requestFocus();
//			return false;
//		}
//		if(monMinInTime.getText()==null||monMinInTime.getText().isEmpty()) {
//			errorTip.setText("Please enter monday In Time");
//			monMinInTime.setTooltip(errorTip);
//			errorTip.show(monMinInTime, 500, 250);
//			monMinInTime.requestFocus();
//			return false;
//		}
//		if(monHourOutTime.getText()==null||monHourOutTime.getText().isEmpty()) {
//			errorTip.setText("Please enter monday Out Time");
//			monHourOutTime.setTooltip(errorTip);
//			errorTip.show(monHourOutTime, 500, 250);
//			monHourOutTime.requestFocus();
//			return false;
//		}
//		if(mon.getText()==null||monMinInTime.getText().isEmpty()) {
//			errorTip.setText("Please enter monday In Time");
//			monMinInTime.setTooltip(errorTip);
//			errorTip.show(monMinInTime, 500, 250);
//			monMinInTime.requestFocus();
//			return false;
//		}
//		
//		if(tuesHourInTime.getText()==null||tuesHourInTime.getText().isEmpty()) {
//			errorTip.setText("Please enter tuesday In Time");
//			tuesHourInTime.setTooltip(errorTip);
//			errorTip.show(tuesHourInTime, 500, 250);
//			tuesHourInTime.requestFocus();
//			return false;
//		}
//		
//		if(tuesHourInTime.getText()==null||tuesHourInTime.getText().isEmpty()) {
//			errorTip.setText("Please enter tuesday In Time");
//			tuesHourInTime.setTooltip(errorTip);
//			errorTip.show(tuesHourInTime, 500, 250);
//			tuesHourInTime.requestFocus();
//			return false;
//		}
//		
		return true;
		
	}
	
	public List<Integer> fillHourComboBox() {
		List<Integer> hourCombo= new ArrayList<>();
		for(int i=1;i<=12;i++) {
			hourCombo.add(i);
		}
		return hourCombo;
		
	}
	
//	public List<Integer> fillMinuteComboBox(){
//		List<Integer> minuteCombo= new ArrayList<>();
//		for(int i=1;i<=;i++) {
//			minuteCombo.add(i);
//		}
//		return minuteCombo;
//	}
	

}
