package controller;

import java.awt.Desktop;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import application.Main;
import dao.DepartmentDAO;
import dao.EmployeeDAO;
import dao.UploadDAO;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import model.CurrentMonthYear;
import model.Department;
import model.Employee;

/****
 * 
 * @author Saurabh Gupta
 *
 */
public class UploadController {
	
	JFXTextField excelPath;
	Tooltip errorTip= new Tooltip();
	List<Department> departMentList;
	List<Employee> employeeList;
	List<Employee> newEmpList;
	UploadDAO uploadDAO= new UploadDAO();
	DepartmentDAO departmentDAO= new DepartmentDAO();
	EmployeeDAO employeeDAO= new EmployeeDAO();
//	List<String> monthList;
	List<CurrentMonthYear> yearList;
	List<CurrentMonthYear> newYearList;
//	SimpleDateFormat dateFormat= new SimpleDateFormat("dd-MMM-yyyy");
	
	
	public GridPane uploadData() {
		
		departMentList= departmentDAO.getDepartmentList();
		
		employeeList= employeeDAO.showEmployees();
		yearList=employeeDAO.getYears();
		
		newEmpList= new ArrayList<>();
		newYearList= new ArrayList<>();
		GridPane gp= new GridPane();
		List<Employee> tempEmpList= new ArrayList<>();

//		gp.setMinSize(800, 600);
		gp.setPadding(new Insets(10,10,10,10));
		gp.getStyleClass().add("grid");
		gp.setAlignment(Pos.CENTER);
//		gp.setHgap(30);
		gp.setVgap(30);
		gp.setGridLinesVisible(false);
		
		Label titleLbl= new Label("Upload Attendance Sheet");
		titleLbl.setStyle("-fx-font-size:20;-fx-text-fill:black;-fx-font-weight:bold");
	
		excelPath= new JFXTextField();
		excelPath.setPromptText("Excel sheet Path");
		excelPath.setPrefWidth(200);
		
		FileChooser chooser= new FileChooser();
		chooser.setTitle("Choose Logo");
		chooser.setInitialDirectory(new File(System.getProperty("user.home")));
		chooser.getExtensionFilters().add(
			    new FileChooser.ExtensionFilter("Excel files (*.xls)", "*.xls")
			);

		JFXButton openBtn= new JFXButton("Browse");
		openBtn.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				File file= chooser.showOpenDialog(Main.primaryStage);
				if(file!=null) {
					openFile(file);
				}
				
			}
		});
		
		JFXButton submitBtn= new JFXButton("Submit");
		submitBtn.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				if(!validateData()) {
					return;
				}
				if(departMentList.isEmpty()) {
					Alert alert= new Alert(AlertType.ERROR,"No Department Name found..Please add department first!");
					alert.setTitle("Error");
					alert.showAndWait();
					return;
				}
				try {
					File file = new File(excelPath.getText());
				
					FileInputStream inputFile= new FileInputStream(new File(excelPath.getText()));
					if(file.length()==0) {
						System.out.println("Empty File");
						Alert alert= new Alert(AlertType.WARNING,"File is Empty");
						alert.setTitle("Warning");
						alert.showAndWait();
						inputFile.close();
						return;
					}
					
					HSSFWorkbook workbook= new HSSFWorkbook(inputFile);
					SimpleDateFormat format= new SimpleDateFormat("dd-MMM-yy hh:mm:ss aa");
					Department department= new Department();
					int year=0;
					int month=0;
					
					HSSFSheet sheet=workbook.getSheetAt(0);
					for(int i=0;i<=sheet.getLastRowNum();i++) {
						Row row=sheet.getRow(i);
						
						if(row.getRowNum()==0) {
							continue;
						}
//						System.out.println(department==null);
						department= new Department();
						department.setDeptName(row.getCell(0).getStringCellValue());
//						System.out.println(department.getDeptName()+"  "+i);
						for(Department d:departMentList) {
							if(department.getDeptName().equals(d.getDeptName())) {
								department.setId(d.getId());
							}
						}
						
						if(department.getId()==0) {
							Alert alert= new Alert(AlertType.ERROR,"Department with name '"+department.getDeptName()+"' not Found..Please add department first!");
							alert.setTitle("Error");
							alert.showAndWait();
							return;
						}
//						departMentList.add(department);
						
						Employee employee= new Employee();
						employee.setDepartment(department);
						employee.getDepartment().setId(department.getId());
						employee.setEmployeeName(row.getCell(1).getStringCellValue());
						employee.setEmployeeNo(Long.parseLong(row.getCell(2).getStringCellValue()));
						Date date1=format.parse(row.getCell(3).getStringCellValue());
						Date date2=format.parse(row.getCell(3).getStringCellValue());
						
						Calendar cal = Calendar.getInstance();
						cal.setTime(date1);
						year = cal.get(Calendar.YEAR);
						month=cal.get(Calendar.MONTH);
//						System.out.println(month);
						CurrentMonthYear cm= new CurrentMonthYear();
							cm.setMonth(month+1);
						
						cm.setYear(year);
						if(yearList.isEmpty()) {
							yearList.add(cm);
						}else {
							
							for(CurrentMonthYear cy:yearList) {
							if(cy.getMonth()!=month+1&&cy.getYear()!=year){
//								if(!newYearList.contains(cm)) {
								yearList.add(cm);
//								}
							}
							}
//							for(String y:yearList) {
//								if(!y.equals(String.valueOf(year))) {
//									newYearList.add(y);
//								}
//							}
						}
						Timestamp timestamp= new Timestamp(date1.getTime());
						employee.setDateTime(timestamp);
						Timestamp timestamp2=new Timestamp(date2.getTime());
						employee.setDate(String.valueOf(new java.sql.Date(timestamp2.getTime())));
						
						
						
						if(employeeList.isEmpty()) {
							newEmpList.add(employee);
						}else {
							if(!employeeList.contains(employee)) {
								newEmpList.add(employee);
							}
//							for(int j=0;j<newEmpList.size();j++) {
//							System.out.println("Inside Loop"+newEmpList.size()+" j"+j);
//							if(newEmpList.get(j).getEmployeeNo()==employee.getEmployeeNo()) {
//							if(newEmpList.get(j).getDate().equals(employee.getDate())) {
//								if(newEmpList.get(j).getTime1()==null) {
//									newEmpList.get(j).setTime1(employee.getDateTime());	
//								}
//								else if(newEmpList.get(j).getTime2()==null) {
//									newEmpList.get(j).setTime2(employee.getDateTime());
//								}
//								else if(newEmpList.get(j).getTime3()==null) {
//									newEmpList.get(j).setTime3(employee.getDateTime());
//								}
//								else if(newEmpList.get(j).getTime4()==null) {
//									newEmpList.get(j).setTime4(employee.getDateTime());
//								}
//								else if(newEmpList.get(j).getTime5()==null) {
//									newEmpList.get(j).setTime5(employee.getDateTime());
//								}
//								else if(newEmpList.get(j).getTime6()==null) {
//									newEmpList.get(j).setTime6(employee.getDateTime());
//								}
//								else if(newEmpList.get(j).getTime7()==null) {
//									newEmpList.get(j).setTime7(employee.getDateTime());
//								}
//								else if(newEmpList.get(j).getTime8()==null) {
//									newEmpList.get(j).setTime8(employee.getDateTime());
//								}
//								else if(newEmpList.get(j).getTime9()==null) {
//									newEmpList.get(j).setTime9(employee.getDateTime());
//								}else if(newEmpList.get(j).getTime10()==null) {
//									newEmpList.get(j).setTime10(employee.getDateTime());
//								}
//							}else {
//								newEmpList.add(employee);
//							}
//							
//							
//							}
//						}
							
//						for(Employee e:employeeList) {
//							if(e.getDepartment().getId()==employee.getDepartment().getId()&&e.getEmployeeName().equals(employee.getEmployeeName())) {
//									if(e.getDateTime().equals(employee.getDateTime())){
//										employeeExist=true;}
//								else {
//									
//								}
//							
//							}
//						}
							
							
						}
						
						
					}
					 tempEmpList.addAll(newEmpList);
				     for(int j=0;j<newEmpList.size();j++) {
				      int count=0;
				      for(int j1=0;j1<tempEmpList.size();j1++) {
				       
				       if(newEmpList.get(j).getEmployeeNo()==tempEmpList.get(j1).getEmployeeNo()) {
				        if(newEmpList.get(j).getDate().equals(tempEmpList.get(j1).getDate())) {
				         
				         if(count==0) {
				         newEmpList.get(j).setTime1(tempEmpList.get(j1).getDateTime()); 
				         
				         //newEmpList.remove(tempEmpList.get(j1));
				         
				         //System.out.println(tempEmpList.get(j1)+" "+newEmpList.size());
				         
				         //count++;
				         
				         } if(count==1) {
				          
				          newEmpList.get(j).setTime2(tempEmpList.get(j1).getDateTime()); 
				          newEmpList.remove(tempEmpList.get(j1));
				          //count++;
				         }
				         
				          if(count==2) {
				          
				          newEmpList.get(j).setTime3(tempEmpList.get(j1).getDateTime()); 
				          newEmpList.remove(tempEmpList.get(j1));
				          //count++;
				         }
				         
				          if(count==3) {
				 
				          newEmpList.get(j).setTime4(tempEmpList.get(j1).getDateTime());
				          newEmpList.remove(tempEmpList.get(j1));
				          //count++;
				         }
				         if(count==4) {
				          
				          newEmpList.get(j).setTime5(tempEmpList.get(j1).getDateTime());
				          newEmpList.remove(tempEmpList.get(j1));
				          //count++;
				         }
				         if(count==5) {
				        	 newEmpList.get(j).setTime6(tempEmpList.get(j1).getDateTime());
					          newEmpList.remove(tempEmpList.get(j1));
				         }
				         if(count==6) {
				        	 newEmpList.get(j).setTime7(tempEmpList.get(j1).getDateTime());
					          newEmpList.remove(tempEmpList.get(j1));
				         }
				         if(count==7) {
				        	 newEmpList.get(j).setTime8(tempEmpList.get(j1).getDateTime());
					          newEmpList.remove(tempEmpList.get(j1));
				         }
				         if(count==8) {
				        	 newEmpList.get(j).setTime9(tempEmpList.get(j1).getDateTime());
					          newEmpList.remove(tempEmpList.get(j1));
				         }
				         if(count==9) {
				        	 newEmpList.get(j).setTime10(tempEmpList.get(j1).getDateTime());
					          newEmpList.remove(tempEmpList.get(j1));
				         }
				         
				         count++;
				        }
				        
				       }
				}
				      }
					
					inputFile.close();
					workbook.close();
					if(newEmpList.isEmpty()) {
						Alert alert= new Alert(AlertType.WARNING,"Employees with given date/Time already exist");
						alert.setTitle("Warning");
						alert.showAndWait();
						return;
					}
					boolean result=uploadDAO.uploadData(department,newEmpList,yearList);
					if(result) {
						Alert alert= new Alert(AlertType.INFORMATION,"Data uploaded Successfully");
						alert.setTitle("Success");
						alert.showAndWait();
						
						Main.dialog.close();
						Main.prepareChart();
							
						
					}else {
						Alert alert= new Alert(AlertType.ERROR,"error in uploading data..!");
						alert.setTitle("Error");
						alert.showAndWait();
//						Main.dialog.close();
					}
					
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		GridPane.setMargin(titleLbl, new Insets(0,-20,0,20));
		gp.add(titleLbl, 0, 0);
//		GridPane.setMargin(child, value);
		gp.add(excelPath, 0, 2);
		gp.add(openBtn, 1, 2);
		GridPane.setMargin(submitBtn, new Insets(0,-80,0,80));
		gp.add(submitBtn, 0, 4);
		
		return gp;
	}
	
	private void openFile(File file) {
		 Desktop desktop = Desktop.getDesktop();
//		 System.out.println(file.getAbsolutePath().toString());
	        	excelPath.setText(file.getAbsolutePath().toString());
	    }
	
	public boolean validateData() {
		if(excelPath.getText()==null||excelPath.getText().isEmpty()) {
			errorTip.setText("Please select excel path");
			excelPath.setTooltip(errorTip);
			errorTip.show(excelPath, 500, 250);
			excelPath.requestFocus();
			return false;
		}
				
		return true;
	}
	 

}
