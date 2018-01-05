package application;
	
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Hours;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;

import controller.AutoCompleteComboBoxListener;
import controller.DepartmentController;
import controller.EmployeeController;
import controller.UploadController;
import dao.DepartmentDAO;
import dao.EmployeeDAO;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.CurrentMonthYear;
import model.Department;
import model.Employee;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ButtonBuilder;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/****
 * 
 * @author Saurabh Gupta
 *
 */

public class Main extends Application {
	
	public static Stage primaryStage = new Stage();
	public static BorderPane root = new BorderPane();
	static MenuBar menuBar= new MenuBar();
	Tooltip errorTip= new Tooltip();
	Menu uploadMenu= new Menu("Upload");
	Menu homeMenu= new Menu();
	MenuItem uploadMenuItem= new MenuItem("Upload File");
	
	Menu departmentMenu= new Menu("Department");
	MenuItem addDepItem= new MenuItem("Add Department");
	MenuItem updateDepItem= new MenuItem("Update Department");
	
	Menu employeeMenu = new Menu("Employee");
	MenuItem addEmployeeItem= new MenuItem("Add Employee");
	
	Menu reportMenu= new Menu("Reports");
	MenuItem employeeWiseItem= new MenuItem("Employee Wise Attendance");
	MenuItem deptWiseItem= new MenuItem("Department Wise Attendance");
	MenuItem dateWiseItem= new MenuItem("Date Wise Attendance");
	
	UploadController uploadController= new UploadController();
	DepartmentController departmentController= new DepartmentController();
	EmployeeController employeeController= new EmployeeController();
	static EmployeeDAO employeeDAO= new EmployeeDAO();
	
	  Label exportLbl= new Label("Export");
	  JFXRadioButton excel;
	  JFXRadioButton pdf;
	  JFXButton generateBtn;
	  final static ToggleGroup group = new ToggleGroup();
	
	public static TableView<Employee> employeeView= new TableView<>();
	
	JFXComboBox<String> employeeCombo;
	JFXComboBox<String> monthCombo;
	JFXComboBox<String> yearCombo;
	JFXDatePicker datePick;
	JFXComboBox<String> deptCombo;
	
	
	public static LineChart<String, Number> lineChart;
	
	public static Stage dialog;
	
	@Override
	public void start(Stage primaryStage) {
			
			Main.primaryStage=primaryStage;
//			FXMLLoader loder= new FXMLLoader();
//			String xmlPath="src/application/CompanyController.fxml";
//			FileInputStream fxmleStream= new FileInputStream(xmlPath);
//			root.setCenter((GridPane) loder.load(fxmleStream)); 
//			
//			Menu companyMenu= new Menu();
//			MenuItem addcomMenuItem= new MenuItem();
//			FileInputStream inputFile= new FileInputStream(new File("C:/Users/W10/Desktop/atteandance.xls"));
//			HSSFWorkbook workbook= new HSSFWorkbook(inputFile);
//			HSSFSheet sheet=workbook.getSheetAt(0);
////			Iterator<Row> rowIterator=sheet.iterator();
//			int count=0;
//			SimpleDateFormat format= new SimpleDateFormat("dd-MMM-yy hh:mm:ss a");
//			SimpleDateFormat format1= new SimpleDateFormat("dd-MM-yy");
//			for(int i=1;i<sheet.getLastRowNum()+1;i++) {
//				count=0;
//				Row row=sheet.getRow(i);
//				Row row1=sheet.getRow(i-1);
////				System.out.println(row.getCell(0).getStringCellValue());
////				System.out.println(row1.getCell(0).getStringCellValue());
//				System.out.println("Counter  "+i);
//				if(row.getCell(0).getStringCellValue().equals(row1.getCell(0).getStringCellValue())) {
//					if(row.getCell(1).getStringCellValue().equals(row1.getCell(1).getStringCellValue())) {
//						if(row.getCell(2).getStringCellValue().equals(row1.getCell(2).getStringCellValue())) {
//							Date date1=format.parse(row.getCell(3).getStringCellValue());
//							Date date2=format.parse(row1.getCell(3).getStringCellValue());
//							DateTime d1=new DateTime(date1);
//							DateTime d2= new DateTime(date2);
//						
////						System.out.println(date1+"--"+date2+"=="+d1.withTimeAtStartOfDay().isEqual(d2.withTimeAtStartOfDay()));
////							if(count==1) {
//							if(d1.withTimeAtStartOfDay().isEqual(d2.withTimeAtStartOfDay())) {
////							count=1;
//								i=i+1;
//							long diff=date1.getTime()-date2.getTime();
//							long diffSeconds=diff/1000;
////							long diffSeconds = timeDifferenceMilliseconds / 1000;
//						    long diffMinutes = diff / (60 * 1000);
//						    long diffHours = diff / (60 * 60 * 1000);
//						    long diffDays = diff / (60 * 60 * 1000 * 24);
//						    System.out.println("Count "+i+"  "+row.getCell(1).getStringCellValue()+"-"+date1+"--"+date2+"="+diffHours);
//								}
////							}
//							else  {
////									for(int j=1;j<sheet.getLastRowNum();j++) {
////										Row row3=sheet.getRow(j);	
////										if(!row3.equals(row1)) {
//////										System.out.println(row.getCell(0).getStringCellValue());
////										if(row.getCell(0).getStringCellValue().equals(row1.getCell(0).getStringCellValue())) {
////											if(row.getCell(1).getStringCellValue().equals(row1.getCell(1).getStringCellValue())) {
////												if(row.getCell(2).getStringCellValue().equals(row1.getCell(2).getStringCellValue())) {
////									
//////											System.out.println(row3.getCell(3).getStringCellValue());
////											Date date3=format.parse(row3.getCell(3).getStringCellValue());
////											DateTime d3=new DateTime(date3);
////											if(!d2.withTimeAtStartOfDay().equals(d3)) {
//												System.out.println("Count "+i+"  "+"User"+row.getCell(1).getStringCellValue()+"absent on"+date2);
////												i=i-1;
////												count=0;
////												break;
////											}
////										}
////									}
//											
////											}
////										}
////									}
//								}
//							
//						}
//						}
//					}
//				}
			
//			while(rowIterator.hasNext()) {
//				Row row=rowIterator.next();
//				if(row.getRowNum()!=0) {
//					
//				String cell=row.getCell(0).getStringCellValue();
//				String cell1=row.getCell(1).getStringCellValue();
//				String cell2= row.getCell(2).getStringCellValue();
//				String cell3=row.getCell(3).getStringCellValue();
//				
//				
//				
//				
//				System.out.println(cell);
////				Iterator<Cell> cellIterator=row.cellIterator();
////				System.out.println(row.getRowNum());
////				while(cellIterator.hasNext()) {
////					Cell cell = cellIterator.next();
////					switch (cell.getCellTypeEnum()) {
////					case BOOLEAN:
////						System.out.println("Boolean"+cell.getBooleanCellValue());
////						break;
////					case STRING:
////						System.out.println("String"+cell.getStringCellValue());
////						break;
////					case NUMERIC:
////						System.out.println("Numeric"+cell.getNumericCellValue());
////						break;
////					default:
////						break;
////					}
////				}
//				
//				}
//				System.out.println("");
//			} 
//			workbook.close();
//			inputFile.close();
			uploadMenu.getItems().add(uploadMenuItem);
			uploadMenuItem.setOnAction(e->setOnActionMethod(e));
			
			addDepItem.setOnAction(e->setOnActionMethod(e));
			updateDepItem.setOnAction(e->setOnActionMethod(e));
			departmentMenu.getItems().addAll(addDepItem);
			
//			ImageView homeImg= new ImageView(new Image(ResourceLoader.load("/images/homeIcon.png")));
//			homeImg.setFitHeight(20);
//			homeImg.setFitWidth(20);
			Label homeLbl= new Label("Home");
			homeMenu.setGraphic(homeLbl);
//			addEmployeeItem.setOnAction(e->setOnActionMethod(e));
//			employeeMenu.getItems().add(addEmployeeItem);
			
			employeeWiseItem.setOnAction(e->setOnActionMethod(e));
			deptWiseItem.setOnAction(e->setOnActionMethod(e));
			dateWiseItem.setOnAction(e->setOnActionMethod(e));
			reportMenu.getItems().addAll(dateWiseItem,employeeWiseItem);
			homeLbl.setOnMouseClicked(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {
					// TODO Auto-generated method stub
					prepareChart();
				}
			});
			
			
			
			menuBar.getMenus().addAll(homeMenu,uploadMenu,departmentMenu,reportMenu);
			 menuBar.prefWidthProperty().bind(primaryStage.widthProperty());
			 root.setTop(menuBar);
			 prepareChart();
			
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/images/file2.css").toExternalForm());
			
			primaryStage.setScene(scene);
			primaryStage.getIcons().add(new Image(ResourceLoader.load("/images/attendanceLogo1.png")));
			primaryStage.setMaximized(true);
			primaryStage.setTitle("Attendance Master");
			primaryStage.show();
		
	}
	
	public void setOnActionMethod(ActionEvent e) {
		if(e.getSource()==uploadMenuItem) {
			GridPane gridPane = uploadController.uploadData();
//			System.out.println("Raaced");
			Scene scene = new Scene(gridPane,500,400);
			scene.getStylesheets().add(getClass().getResource("/images/popup.css").toExternalForm());
			scene.addEventFilter(KeyEvent.KEY_PRESSED, event->{
				if(event.getCode()==KeyCode.ESCAPE) {
					dialog.close();
				}
			});
			scene.setFill(Color.TRANSPARENT);
			 dialog = new Stage();

			dialog.setScene(scene);
//			dialog.setX(200);
//			dialog.setY(150);
//			dialog.setTitle("Add New Supplier");
			dialog.initOwner(primaryStage);
			dialog.initModality(Modality.WINDOW_MODAL);
			dialog.initStyle(StageStyle.TRANSPARENT);
			ImageView closeImg = new ImageView(new Image(ResourceLoader.load("/images/closeIcon.png")));
	        closeImg.setFitHeight(30);
	        closeImg.setFitWidth(30);
	        closeImg.setStyle("-fx-cursor:hand");
	        GridPane.setMargin(closeImg, new Insets(-50,-50,0,0));
	        gridPane.add(closeImg, 2, 0);
	        closeImg.setOnMouseClicked(new EventHandler<MouseEvent>() {

	    		@Override
	    		public void handle(MouseEvent arg0) {
	    			// TODO Auto-generated method stub
	    			dialog.close();
	    		}

	    	});

			dialog.show();
	
		}
		else if(e.getSource()==addDepItem) {
			GridPane gridPane = departmentController.addDepartment();
//			System.out.println("Raaced");
			Scene scene = new Scene(gridPane,880,700);
			scene.getStylesheets().add(getClass().getResource("/images/popup.css").toExternalForm());
			scene.setFill(Color.TRANSPARENT);
			 dialog = new Stage();
			 scene.addEventFilter(KeyEvent.KEY_PRESSED, event->{
					if(event.getCode()==KeyCode.ESCAPE) {
						dialog.close();
					}
				});

			dialog.setScene(scene);
//			dialog.setX(200);
//			dialog.setY(150);
//			dialog.setTitle("Add New Supplier");
			dialog.initOwner(primaryStage);
			dialog.initModality(Modality.WINDOW_MODAL);
			dialog.initStyle(StageStyle.TRANSPARENT);
			ImageView closeImg = new ImageView(new Image(ResourceLoader.load("/images/closeIcon.png")));
	        closeImg.setFitHeight(30);
	        closeImg.setFitWidth(30);
	        closeImg.setStyle("-fx-cursor:hand");
	        GridPane.setMargin(closeImg, new Insets(0,0,20,20));
	        gridPane.add(closeImg, 2, 0);
	        closeImg.setOnMouseClicked(new EventHandler<MouseEvent>() {

	    		@Override
	    		public void handle(MouseEvent arg0) {
	    			// TODO Auto-generated method stub
	    			dialog.close();
	    		}

	    	});

			dialog.show();
	
		}
//		else if(e.getSource()==updateDepItem) {
//			GridPane gridPane = departmentController.updateDepartment();
////			System.out.println("Raaced");
//			Scene scene = new Scene(gridPane,880,700);
//			scene.getStylesheets().add(getClass().getResource("/images/popup.css").toExternalForm());
//			scene.setFill(Color.TRANSPARENT);
//			 dialog = new Stage();
//
//			dialog.setScene(scene);
////			dialog.setX(200);
////			dialog.setY(150);
////			dialog.setTitle("Add New Supplier");
//			dialog.initOwner(primaryStage);
//			dialog.initModality(Modality.WINDOW_MODAL);
//			dialog.initStyle(StageStyle.TRANSPARENT);
//			ImageView closeImg = new ImageView(new Image(ResourceLoader.load("/images/closeIcon.png")));
//	        closeImg.setFitHeight(30);
//	        closeImg.setFitWidth(30);
//	        closeImg.setStyle("-fx-cursor:hand");
//	        GridPane.setMargin(closeImg, new Insets(0,0,20,20));
//	        gridPane.add(closeImg, 2, 0);
//	        closeImg.setOnMouseClicked(new EventHandler<MouseEvent>() {
//
//	    		@Override
//	    		public void handle(MouseEvent arg0) {
//	    			// TODO Auto-generated method stub
//	    			dialog.close();
//	    		}
//
//	    	});
//
//			dialog.show();
//	
//		}
		else if(e.getSource()==employeeWiseItem) {
			root.getChildren().removeAll(employeeView,lineChart);
//			List<Employee> employeeData= employeeDAO.getEmployeeList();
			List<CurrentMonthYear> yearList=employeeDAO.getYears();
			ObservableList<String> employeeList= FXCollections.observableArrayList();
//			for(Employee em:employeeData) {
//				employeeList.add(em.getEmployeeName());
//			}
			
			List<Department> departmentData= DepartmentDAO.getDepartmentList();
			  ObservableList<String> depList= FXCollections.observableArrayList();
			  
			  for(Department dp:departmentData) {
			   depList.add(dp.getDeptName());
			  }
			  
			  deptCombo= new JFXComboBox<>();
			  deptCombo.setLabelFloat(true);
			  deptCombo.setEditable(true);
			  deptCombo.getStyleClass().add("jf-combo-box");
			  deptCombo.setPromptText("Department Name");
			
			  deptCombo.setItems(depList);
			  
			  new AutoCompleteComboBoxListener<>(deptCombo);
			  
			 deptCombo.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					if(deptCombo.getValue()!=null) {
						List<Employee> empList=employeeDAO.getEmployeeByDept(deptCombo.getValue());
						for(Employee e:empList) {
							employeeList.add(e.getEmployeeName());
						}
					}
				}
			});
			 
			
			employeeCombo= new JFXComboBox<>();
			employeeCombo.setLabelFloat(true);
			employeeCombo.setEditable(true);
			employeeCombo.getStyleClass().add("jf-combo-box");
			employeeCombo.setPromptText("Employee Name");
			
			 employeeCombo.setItems(employeeList);
			new AutoCompleteComboBoxListener<>(employeeCombo);
			
			
			monthCombo= new JFXComboBox<>();
			monthCombo.setLabelFloat(true);
			monthCombo.setPromptText("Select Month");
			monthCombo.setEditable(false);
			monthCombo.setItems(fillMonthCombo());
			monthCombo.setValue("01");
			monthCombo.setOnKeyReleased(new EventHandler<KeyEvent>() {

				@Override
				public void handle(KeyEvent event) {
					// TODO Auto-generated method stub
//					errorTip.hide();
					String s=AutoCompleteComboBoxListener.jumpTo(event.getText(), monthCombo.getValue(), monthCombo.getItems());
					if(s!=null) {
						monthCombo.requestFocus();
						monthCombo.getSelectionModel().select(s);

					}
				}
			});
			
			yearCombo= new JFXComboBox<>();
			yearCombo.setLabelFloat(true);
			yearCombo.setPromptText("Select Year");
			for(CurrentMonthYear cm:yearList) {
			yearCombo.getItems().add(String.valueOf(cm.getYear()));
			}
			yearCombo.setOnKeyReleased(new EventHandler<KeyEvent>() {

				@Override
				public void handle(KeyEvent event) {
					// TODO Auto-generated method stub
//					errorTip.hide();
					String s=AutoCompleteComboBoxListener.jumpTo(event.getText(), yearCombo.getValue(), yearCombo.getItems());
					if(s!=null) {
						yearCombo.requestFocus();
						yearCombo.getSelectionModel().select(s);

					}
				}
			});
			
			
			JFXButton viewBtn= new JFXButton("View");
			viewBtn.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					if(!validateEmployeeData()) {
						return;
					}
					
					employeeView=employeeController.getEmployeeData(employeeCombo.getValue(),monthCombo.getValue(),yearCombo.getValue());
					employeeView.setPrefSize(900, 500);
					BorderPane.setMargin(employeeView, new Insets(0,0,0,100));
					root.setCenter(employeeView);
					employeeView.requestFocus();
					employeeView.getSelectionModel().selectFirst();
					employeeView.getFocusModel().focus(0);
				}
			});
			
			HBox monthYearHB= new HBox();
			monthYearHB.setSpacing(10);
			monthYearHB.getChildren().addAll(monthCombo,yearCombo);
			
			VBox comboVB= new VBox();
			comboVB.setSpacing(40);
			comboVB.getChildren().addAll(deptCombo,employeeCombo,monthYearHB,viewBtn);
			
			GridPane gridPane= new GridPane();
			gridPane.add(comboVB, 0, 0);
			BorderPane.setMargin(gridPane, new Insets(200,10,0,100));
			root.setLeft(gridPane);
			Platform.runLater(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					employeeCombo.requestFocus();
				}
			});
			
		}
		else if(e.getSource()==dateWiseItem) {
			root.getChildren().removeAll(employeeView,lineChart);
			  List<Department> departmentData= DepartmentDAO.getDepartmentList();
			  ObservableList<String> depList= FXCollections.observableArrayList();
			  
			  for(Department dp:departmentData) {
			   depList.add(dp.getDeptName());
			  }

			  deptCombo= new JFXComboBox<>();
			  deptCombo.setLabelFloat(true);
			  deptCombo.setEditable(true);
			  deptCombo.getStyleClass().add("jf-combo-box");
			  deptCombo.setPromptText("Department Name");
			
			  deptCombo.setItems(depList);
			  
			  
			  new AutoCompleteComboBoxListener<>(deptCombo);
			  VBox comboVb= new VBox();
			  
			  datePick=new JFXDatePicker();
			  datePick.setPromptText("Select Date");
			  KeyEventHandler.dateKeyEvent(datePick);
			  datePick.setValue(LocalDate.now());
			  LocalDate date1=datePick.getValue();
			  JFXButton viewBtn= new JFXButton("View");
			  viewBtn.setOnAction(new EventHandler<ActionEvent>() {
			   
			   @Override
			   public void handle(ActionEvent event) {
			    // TODO Auto-generated method stub
				   errorTip.hide();
			   if(deptCombo.getValue()==null) {
					errorTip.setText("Please select Department");
					deptCombo.setTooltip(errorTip);
					errorTip.show(deptCombo, 100, 100);
					deptCombo.requestFocus();
					return;
				}
			    if(datePick.getValue()==null) {
			     errorTip.setText("Please select date");
			     datePick.setTooltip(errorTip);
			     errorTip.show(datePick, 100, 200);
			     datePick.requestFocus();
			     return;
			    }
			   
			    
			    employeeView=employeeController.getDateData1(deptCombo.getValue(),datePick.getValue().toString());
			    employeeView.setMinSize(800, 500);
			    BorderPane.setMargin(employeeView, new Insets(0,0,0,0));
			    root.setCenter(employeeView);
			    employeeView.requestFocus();
			    employeeView.getSelectionModel().selectFirst();
			    employeeView.getFocusModel().focus(0);
			    
			    pdf= new JFXRadioButton("PDF");

				   pdf.setToggleGroup(group);
				   pdf.setSelected(true);
				    excel=new JFXRadioButton("EXCEL");
				   excel.setToggleGroup(group);
				   excel.setSelected(true);
				   generateBtn=new JFXButton();
				   HBox exportHB= new HBox();
				   exportHB.getChildren().addAll(pdf,excel,generateBtn);
				   generateBtn.setOnAction(new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent event) {
						// TODO Auto-generated method stub
						if(pdf.isSelected()) {
							EmployeeReport.showPDF(EmployeeController.tempEmpList);
						}
					}
				});
				   
			   }      
			  });
			 
			  comboVb.setSpacing(20);
			
			  exportLbl.setStyle("-fx-font-size:14;-fx-font-weight:bold;");
			  
			  comboVb.getChildren().addAll(deptCombo,datePick,viewBtn);
			  BorderPane.setMargin(comboVb, new Insets(200,10,0,100));
			  root.setLeft(comboVb);
			  Platform.runLater(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						datePick.requestFocus();
					}
				});
				
			  
			//  Platform.runLater(new Runnable() {
			//
			//   @Override
			//   public void run() {
//			    // TODO Auto-generated method stub
//			    employeeCombo.requestFocus();
			//   }
			//   
			//  });
			 }
	}
	
	public ObservableList<String> fillMonthCombo(){
		ObservableList<String> monthList=FXCollections.observableArrayList();
		monthList.addAll("01", "02", "03", "04", "05", 
				"06", "07", "08", "09", "10", "11", "12");
		return monthList;
		
	}
	
	public boolean validateEmployeeData(){
		if(deptCombo.getValue()==null) {
			errorTip.setText("Please select Department");
			deptCombo.setTooltip(errorTip);
			errorTip.show(deptCombo, 100, 200);
			deptCombo.requestFocus();
			return false;
		}
		if(employeeCombo.getValue()==null) {
			errorTip.setText("Please select employee Name");
			employeeCombo.setTooltip(errorTip);
			errorTip.show(employeeCombo, 100, 300);
			employeeCombo.requestFocus();
			return false;
		}
		
		if(monthCombo.getValue()==null) {
			errorTip.setText("Please select month");
			monthCombo.setTooltip(errorTip);
			errorTip.show(monthCombo, 100, 400);
			monthCombo.requestFocus();
			return false;
		}
		if(yearCombo.getValue()==null) {
			errorTip.setText("Please select year");
			yearCombo.setTooltip(errorTip);
			errorTip.show(yearCombo, 200, 400);
			yearCombo.requestFocus();
			return false;
		}
		
		return true;
		
	}
	
	/**
	 * 
	 */
	public static  void prepareChart() {
		
		CurrentMonthYear monthYear=employeeDAO.getCurrentMonthYear();

		if(monthYear!=null) {
		 Calendar monthStart = new GregorianCalendar(monthYear.getYear(), monthYear.getMonth()-1, 1);
		 int numDays= monthStart.getActualMaximum(Calendar.DAY_OF_MONTH);
		 
		List<Employee>	employeeList= employeeDAO.getEmployeeList();
		final CategoryAxis xAxis= new CategoryAxis();
		final NumberAxis yAxis= new NumberAxis();
		yAxis.setAutoRanging(false);
		yAxis.setLowerBound(0);
		yAxis.setUpperBound(numDays);
		yAxis.setTickUnit(1);
		xAxis.setLabel("Employee Name");
		
		lineChart=new LineChart<String,Number>(xAxis, yAxis);
		String monthName=Month.of(monthYear.getMonth()).name();
		lineChart.setTitle("Employees Performance ("+monthName+" "+monthYear.getYear()+")");
		XYChart.Series series=new XYChart.Series<>();
		series.setName("Number of Days");
		for(Employee e:employeeList) {
			List<Employee> employeeData=employeeDAO.getEmployeeData(e.getEmployeeName(), String.valueOf(monthYear.getMonth()), String.valueOf(monthYear.getYear()));
			int count=0;
			for(Employee em:employeeData) {
				 if(!(em.getTime2().equals(java.sql.Timestamp.valueOf("1917-10-10 00:00:00")))) {
					count=count+1;
				   } 
			}
			
			series.getData().add(new XYChart.Data(e.getEmployeeName(),count));
		}
		
		lineChart.getData().add(series);
		root.getChildren().clear();
		root.setTop(menuBar);
		root.setCenter(lineChart);
		
		}
		
	}
	
	
	
//	public static void main(String[] args) {
//		launch(args);
//	}
}
