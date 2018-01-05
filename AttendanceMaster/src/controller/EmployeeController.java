package controller;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Locale.Category;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;

import com.jfoenix.controls.JFXButton;
import com.sun.javafx.scene.traversal.TopMostTraversalEngine;

import application.Main;
import application.ResourceLoader;
import dao.EmployeeDAO;
import eu.hansolo.medusa.Gauge;
import eu.hansolo.medusa.GaugeBuilder;
import eu.hansolo.medusa.Gauge.SkinType;
import eu.hansolo.medusa.skins.SlimSkin;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.DateComparator;
import model.Employee;

/****
 * 
 * @author Saurabh Gupta
 *
 */

public class EmployeeController {
	EmployeeDAO employeeDAO= new EmployeeDAO();
	ObservableList<Employee> employeeData= FXCollections.observableArrayList();
	private static final long MILLIS_PER_DAY = 24L * 3600 * 1000;
	public JFXButton breakTimeDetail;
	ObservableList<Employee> breakList=FXCollections.observableArrayList();
	Date date2=null;
	int i=1;
	
	public static List<Employee> tempEmpList= new ArrayList<>();
	
	public TableView<Employee> showEmployeeList(){
		
		
		TableView<Employee> employeeView=new TableView<Employee>();
		employeeData.clear();
		TableColumn<Employee, Long> srNoCol= new TableColumn<Employee, Long>("Sr No.");
		srNoCol.setCellValueFactory(new PropertyValueFactory<>("srNo"));
		TableColumn<Employee, String> deptCol= new TableColumn<Employee, String>("Department");
		deptCol.setCellValueFactory(new PropertyValueFactory<>("deptName"));
		TableColumn<Employee, Long> empNoCol= new TableColumn<Employee, Long>("Employee\n"+" Number");
		empNoCol.setCellValueFactory(new PropertyValueFactory<>("employeeNo"));
		TableColumn<Employee, String> empCol= new TableColumn<Employee, String>("Employee");
		empCol.setCellValueFactory(new PropertyValueFactory<>("employeeName"));
		TableColumn<Employee, String> dateCol= new TableColumn<Employee, String>("Date/Time");
		dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
		
		employeeView.getColumns().addAll(srNoCol,deptCol,empNoCol,empCol,dateCol);
		
		List<Employee> employeeList=employeeDAO.showEmployees();
		long srNo=1;
		SimpleDateFormat formatter=new SimpleDateFormat("dd-MMM-yy hh:mm:ss a");
		for(Employee e:employeeList) {
			String date=formatter.format(e.getDateTime());
//			System.out.println(date);
			employeeData.add(new Employee(e.getId(),e.getDepartment().getDeptName(),e.getEmployeeName(),e.getEmployeeNo(),srNo,date,e.getDepartment()));
			srNo++;
			
		}
		
		employeeView.setItems(employeeData);
		return employeeView;
		
		
	}
	
	public TableView<Employee> getEmployeeData(String employeeName,String monthName,String yearName){
		
		employeeData.clear();
		
		List<Employee> employeeList=employeeDAO.getEmployeeData(employeeName, monthName, yearName);
		List<Date> tempDateList= new ArrayList<>();
		List<Employee> employeeDataList= new ArrayList<>();
		long srNo=1;
		long diff=0;
		long diff1=0;
		long diffSeconds=0;
		long diffMinutes=0;
		long diffHours =0;
		String inTime="";
		String outTime="";
		Time inTime1=null;
		Time inTime2= null;
		String status="";
		long totalTime=0;
		SimpleDateFormat formatter=new SimpleDateFormat("hh:mm:ss a");
		SimpleDateFormat dateFormat= new SimpleDateFormat("EEE,dd-MM-yyyy");
//		SimpleDateFormat monthFormat= new SimpleDateFormat("MMM-yyyy");
		
		String date=null;
		Date sqlDate= null;
		String deptName=null;;
		long empNo=0;
		
		
		for(Employee e1:employeeList) {
			  sqlDate= new Date(e1.getDateTime().getTime());
			  empNo=employeeList.get(i).getEmployeeNo();
				 deptName=employeeList.get(i).getDepartment().getDeptName();
			  tempDateList.add(sqlDate);
			  Date date1=new Date(e1.getTime1().getTime());
			  
			  Date date2=null;
			   date=dateFormat.format(e1.getDateTime().getTime());
			  if(e1.getTime1()!=java.sql.Timestamp.valueOf("1917-10-10 00:00:00")) {
				
				  inTime=formatter.format(e1.getTime1());
				  
					try {
						inTime1=new Time(formatter.parse(inTime).getTime());
					} catch (ParseException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
//				System.out.println(inTime);
			  } 
			  if(!(e1.getTime2().equals(java.sql.Timestamp.valueOf("1917-10-10 00:00:00")))) {
					  outTime=formatter.format(e1.getTime2());
					  date2=new Date(e1.getTime2().getTime());
					 
					 //System.out.println("2"+outTime); 
				   } 
			  if (!(e1.getTime4().equals(java.sql.Timestamp.valueOf("1917-10-10 00:00:00"))) ){
						  outTime=formatter.format(e1.getTime4());
						  date2=new Date(e1.getTime4().getTime());
						 // System.out.println("4");
						  
			      }
			  if(!(e1.getTime6().equals(java.sql.Timestamp.valueOf("1917-10-10 00:00:00")))) {
							  outTime=formatter.format(e1.getTime6());
							  date2=new Date(e1.getTime6().getTime());
							  //System.out.println("6");
							  
			      }
			  if(!(e1.getTime8().equals(java.sql.Timestamp.valueOf("1917-10-10 00:00:00")))) {
				  outTime=formatter.format(e1.getTime8());
				  date2=new Date(e1.getTime8().getTime());
				 // System.out.println("10");		  
			     }			  
			  if(!(e1.getTime10().equals(java.sql.Timestamp.valueOf("1917-10-10 00:00:00")))) {
									  outTime=formatter.format(e1.getTime10());
									  date2=new Date(e1.getTime10().getTime());
									// System.out.println("10");
				}
			  if(date2==null) {
				  status="Absent";
			  }else {
			  diff=date2.getTime()-date1.getTime();
			  diffSeconds	=diff/1000%60;
			  diffMinutes   = diff / (60 * 1000)%60;
			  diffHours  = diff / (60 * 60 * 1000)%24;
			  status="Present";
			  }
			
			  
			  if(!(e1.getTime2().equals(java.sql.Timestamp.valueOf("1917-10-10 00:00:00")))&&!(e1.getTime4().equals(java.sql.Timestamp.valueOf("1917-10-10 00:00:00")))) {
					
					 diff1=e1.getTime3().getTime()-e1.getTime2().getTime();
//					 System.out.println("3-2"+employee.getEmployeeName()+" date "+employee.getSqlDate()+" total time"+employee.getTotalTime()+" diff "+(employee.getTotalTime()-diff) / (60 * 60 * 1000)%24);
				   }
				 
				 
			  if (!(e1.getTime4().equals(java.sql.Timestamp.valueOf("1917-10-10 00:00:00")))&&!(e1.getTime6().equals(java.sql.Timestamp.valueOf("1917-10-10 00:00:00")))){
						 
						 // System.out.println("4");
						  diff1=diff1+(e1.getTime5().getTime()-e1.getTime4().getTime());
//						  System.out.println("5-4"+employee.getEmployeeName()+" date "+employee.getSqlDate()+" diff "+(employee.getTotalTime()-diff) / (60 * 60 * 1000)%24);
			      }
			  if(!(e1.getTime6().equals(java.sql.Timestamp.valueOf("1917-10-10 00:00:00")))&&!(e1.getTime8().equals(java.sql.Timestamp.valueOf("1917-10-10 00:00:00")))) {
							 diff1=diff1+(e1.getTime7().getTime()-e1.getTime6().getTime());  
//							 System.out.println("7-6"+employee.getEmployeeName()+" date "+e1.getTime6()+" diff "+(employee.getTotalTime()-diff) / (60 * 60 * 1000)%24);
			      }
			  if(!(e1.getTime8().equals(java.sql.Timestamp.valueOf("1917-10-10 00:00:00")))&&!(e1.getTime10().equals(java.sql.Timestamp.valueOf("1917-10-10 00:00:00")))) {
				  	diff1=diff1+(e1.getTime9().getTime()-e1.getTime8().getTime());
//				  	System.out.println("9-8"+employee.getEmployeeName()+" date "+employee.getSqlDate()+" diff "+(employee.getTotalTime()-diff) / (60 * 60 * 1000)%24);
				 // System.out.println("10");		  
			     }			  
			  
			  	diff=diff-diff1;
				  employeeData.add(new Employee(e1.getId(),e1.getDepartment().getDeptName(),e1.getEmployeeName(),e1.getEmployeeNo(),sqlDate,date,e1.getDepartment(),status,inTime,outTime,diff,inTime1,e1.getTime4()));
				  outTime=null;
				  inTime=null;
				  inTime1=null;
				  diff=0;
				  diff1=0;
			  
				  
				  //System.out.println("Enter into list data -----------------------");
			  
			//  System.out.println("Loop End");
			  
		  }
		  
		
//		Calendar cal = Calendar.getInstance();
//		cal.setTime(new java.util.Date(monthFormat.parse(monthName+"-"+yearName).getTime()));
//		int month = cal.get;
		
//		Calendar calendar = Calendar.getInstance();
//		calendar.set(Calendar.YEAR, Integer.parseInt(yearName));
//		calendar.set(Calendar.MONTH, Integer.parseInt(monthName));
//		int numDays = calendar.getActualMaximum(Calendar.DATE);
		
//		java.util.Date startDate=new java.util.Date(dateFormat.parse("01"+"-"+monthName+"-"+yearName+"").getTime());
//		Date endDate=new Date(dateFormat.parse(numDays+"-"+monthName+"-"+yearName+"").getTime());
//		List<LocalDate> dates=new ArrayList<>();
//		for(int i=0;i<numDays;i++) {
//			LocalDate d=startDate.wit
//		}
		
//		for(int i=0;i<employeeList.size();i++) {
//	
//			sqlDate= new Date(employeeList.get(i).getDateTime().getTime());
//			 empNo=employeeList.get(i).getEmployeeNo();
//			 deptName=employeeList.get(i).getDepartment().getDeptName();
//			 
//			 int count=0;
////			 String date1=formatter.format(e.getDateTime().getTime());
//			 
//			 Date date1=new Date(employeeList.get(i).getDateTime().getTime());
//			 employeeList.get(i).setSqlDate(sqlDate);
//			 
//			 inTime=formatter.format(employeeList.get(i).getDateTime());
//			 try {
//				inTime1= new Time(formatter.parse(inTime).getTime());
//			} catch (ParseException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//			 List<Employee> emList= employeeDAO.viewBreakTime(employeeList.get(i));
//			 
////			 System.out.println(emList.size());
//			 if(emList.size()==1) {
//				 count++;
//			 }else {
//				 
//			 for(int j=0;j<emList.size();j++) {
//				 
//				 if(j==0) {
//					 continue;
//				 }
//				 if(j%2==0) {
//					 continue;
//				 }
//				date2=null;
//				
////				 String date2=formatter.format(e.getDateTime().getTime());
//				if(emList.size()%2==0) {
//				date2 =new Date(emList.get(emList.size()-1).getDateTime().getTime());
//				}
//				else {
//	
//				 date2=new Date(emList.get(j).getDateTime().getTime());
//				}
////				 if(String.valueOf(date1).equals(String.valueOf(date2))){
//					
//					 count=count+2;
////					 System.out.println("size"+emList.size());
//					 
////					 if(count%2==0) {
//						 
////						 System.out.println("count"+count+"inTime"+inTime);
//						 
//						diff=date2.getTime()-date1.getTime();
//						outTime=formatter.format(date2);
//						diffSeconds	=diff/1000%60;
//						diffMinutes   = diff / (60 * 1000)%60;
//						diffHours  = diff / (60 * 60 * 1000)%24;
//						
////						j++;
////					 }
//					
////				 }
//				 
//				
//			 
//			 }
//			 }
////			 System.out.println("inTime"+inTime+"====OutTime"+outTime+"===Count"+count);
//			 if(count>1) {
//				 status="present";
//				
//			 }else if(count==1) {
//				status="absent";
//			 }
//			
//			if(status.equals("present")) {
//				status=status+"\n"+diffHours+":"+diffMinutes+":"+diffSeconds;
//			}
//			date=dateFormat.format(employeeList.get(i).getDateTime());
//			tempDateList.add(sqlDate);
////			System.out.println(e.getDateTime());
////			System.out.println(date);
//		
//			if(diffHours==0&&diffMinutes==0&&diffSeconds==0) {
//				outTime=null;
//			}	
//			
//		 if(!status.equalsIgnoreCase("present\n0:0:0")) {
//			 if(diffHours>=0&&diffMinutes>=0&&diffSeconds>=0) {
////				 employeeDataList.add(new Employee(employeeList.get(i).getId(),employeeList.get(i).getDepartment().getDeptName(),employeeList.get(i).getEmployeeName(),employeeList.get(i).getEmployeeNo(),sqlDate,date,employeeList.get(i).getDepartment(),status,inTime,outTime,diff,inTime1));
//				employeeData.add(new Employee(employeeList.get(i).getId(),employeeList.get(i).getDepartment().getDeptName(),employeeList.get(i).getEmployeeName(),employeeList.get(i).getEmployeeNo(),sqlDate,date,employeeList.get(i).getDepartment(),status,inTime,outTime,diff,inTime1,employeeList.get(i).getTime4()));
//				
//				
//			 }
//				}
//		 
//		 
//			
//		}
//		
		
		try {
			List<Date> dateList=fillInDates(tempDateList,monthName, yearName, "dd-MM-yyyy");
			String missingDate=null;
			Date sqlMissingDate=null;
			for(Date d:dateList) {
//				for(Date s:tempDateList) {
//					String tempDate=dateFormat.format(s);
					 missingDate=dateFormat.format(d);
					 sqlMissingDate=d;
//					if(tempDate.equals(missingDate)) {
////						System.out.println("datelist "+s+".equal(tempdate"+d+") =="+s.equals(d));
//						dateList.remove(s);
////						continue;
//					}
//					System.out.println("datelist "+d+".equal(tempdate"+s+") =="+tempDate.equals(missingDate));
//				}
					
					if(!missingDate.contains("Sun")) {
					employeeData.add(new Employee(deptName,employeeName,empNo,sqlMissingDate,missingDate,"Absent"));
					}
				}
			}
			
		catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
//	
//		for(i=1;i<employeeData.size();i++) {
////			System.out.println(employeeData.get(i).getDate());
//			if(employeeData.get(i).getDate().equals(employeeData.get(i-1).getDate())) {
//				employeeData.remove(i);
//				System.out.println("Reached");
//			}
//		}
		
//		 try {
//				List<String> dateList=fillInDates(employeeData, monthName, yearName,"dd-MM-yyyy");
//				for(String s:dateList) {
//					
//					employeeData.add(new Employee(deptName,employeeName,empNo,s,"absent"));
//				}
//			} catch (ParseException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//			 
//		 	
//		 SortedList<Employee> sortedEmp= new SortedList<>(employeeData);
//		 sortedEmp.setComparator(Comparator.comparing(Employee::getDate));
		 
		TableView<Employee> employeeView= new TableView<Employee>();
		TableColumn<Employee, Long> srNoCol= new TableColumn<Employee, Long>("Sr No");
		srNoCol.setCellValueFactory(new PropertyValueFactory<>("srNo"));
		
		TableColumn<Employee, String> deptCol= new TableColumn<Employee, String>("Department");
		deptCol.setCellValueFactory(new PropertyValueFactory<>("deptName"));
		TableColumn<Employee, Long> empNoCol= new TableColumn<Employee, Long>("Employee\n"+" Number");
		empNoCol.setCellValueFactory(new PropertyValueFactory<>("employeeNo"));
		TableColumn<Employee, String> empCol= new TableColumn<Employee, String>("Employee");
		empCol.setCellValueFactory(new PropertyValueFactory<>("employeeName"));
		TableColumn<Employee, String> dateCol= new TableColumn<Employee, String>("Date");
		dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
		TableColumn<Employee, String> dateTimeCol= new TableColumn<Employee, String>("Time");
		TableColumn<Employee, String> fromDatCol= new TableColumn<Employee, String>("In Time");
		fromDatCol.setCellValueFactory(new PropertyValueFactory<>("inTime"));
		TableColumn<Employee,String> toDateCol= new TableColumn<Employee, String>("Out Time");
		toDateCol.setCellValueFactory(new PropertyValueFactory<>("outTime"));
		dateTimeCol.getColumns().addAll(fromDatCol,toDateCol);
		
		TableColumn<Employee, Employee> statusCol= new TableColumn<Employee, Employee>("Status");
		statusCol.setCellValueFactory(e->new ReadOnlyObjectWrapper<>(e.getValue()));
		statusCol.setCellFactory(e->new TableCell<Employee,Employee>(){
			@Override
		    protected void updateItem(Employee employee,boolean empty){
				if(employee==null){
					setGraphic(null);
					return;
				}else{
					if(employee.getStatus().equalsIgnoreCase("Absent")) {
						Label absentLbl= new Label("Absent");
						absentLbl.setStyle("-fx-font-weight:bold;-fx-text-fill:red");
						setGraphic(absentLbl);
					}else if(employee.getStatus().equalsIgnoreCase("Present")) {
					
							long diffSeconds	=(employee.getTotalTime())/1000%60;
							  long diffMinutes   = (employee.getTotalTime()) / (60 * 1000)%60;
							  long diffHours  = (employee.getTotalTime()) / (60 * 60 * 1000)%24;
							Label presentLbl= new Label("Present\n"+diffHours+":"+diffMinutes+":"+diffSeconds);
							presentLbl.setStyle("-fx-font-weight:bold;-fx-text-fill:green");
							setGraphic(presentLbl);
						}
						
					}
				}
			
		});
		TableColumn<Employee, Employee> breakTimeCol= new TableColumn<Employee, Employee>("Break Time");
		breakTimeCol.setCellValueFactory(e->new ReadOnlyObjectWrapper<>(e.getValue()));
		breakTimeCol.setCellFactory(e->new TableCell<Employee,Employee>(){

			@Override
		    protected void updateItem(Employee employee,boolean empty){
				if(employee==null){
					setGraphic(null);
					return;
				}else{
					if(!employee.getStatus().equals("Absent")&&!(employee.getTime4().equals(java.sql.Timestamp.valueOf("1917-10-10 00:00:00")))) {
						
					breakTimeDetail=new JFXButton("Break Time");
					setGraphic(breakTimeDetail);
					breakTimeDetail.getStyleClass().add("del-btn");
					breakTimeDetail.setAlignment(Pos.CENTER);
					breakTimeDetail.setFocusTraversable(true);
					breakTimeDetail.setOnAction(new EventHandler<ActionEvent>() {
						
						@Override
						public void handle(ActionEvent event) {
							// TODO Auto-generated method stub
							GridPane gridPane= viewBreakTime(employee);
							Scene scene = new Scene(gridPane,500,400);
							scene.getStylesheets().add(getClass().getResource("/images/popup.css").toExternalForm());
							scene.setFill(Color.TRANSPARENT);
							Stage dialog = new Stage();

							dialog.setScene(scene);
//							dialog.setX(200);
//							dialog.setY(150);
//							dialog.setTitle("Add New Supplier");
							dialog.initOwner(Main.primaryStage);
							dialog.initModality(Modality.WINDOW_MODAL);
							dialog.initStyle(StageStyle.TRANSPARENT);
							ImageView closeImg = new ImageView(new Image(ResourceLoader.load("/images/closeIcon.png")));
					        closeImg.setFitHeight(30);
					        closeImg.setFitWidth(30);
					        closeImg.setStyle("-fx-cursor:hand");
					        GridPane.setMargin(closeImg, new Insets(0,-50,20,150));
					        gridPane.add(closeImg, 1, 0);
					        closeImg.setOnMouseClicked(new EventHandler<MouseEvent>() {

					    		@Override
					    		public void handle(MouseEvent arg0) {
					    			// TODO Auto-generated method stub
					    			dialog.close();
					    		}

					    	});

							dialog.show();
					
						}
					});
					}else {
						setGraphic(null);
					}
				}
			}
		});
		
		TableColumn<Employee, Employee> shortTimeCol= new TableColumn<Employee, Employee>("Short Time");
		shortTimeCol.setCellValueFactory(e->new ReadOnlyObjectWrapper<>(e.getValue()));
		shortTimeCol.setCellFactory(e->new TableCell<Employee,Employee>(){
			@Override
		    protected void updateItem(Employee employee,boolean empty){
				if(employee==null){
					setGraphic(null);
					setText(null);
					return;
				}else{
					if(!employee.getStatus().equals("Absent")) {
						Calendar cal=Calendar.getInstance();
						try {
							cal.setTime(new java.util.Date(dateFormat.parse(employee.getDate()).getTime()));
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						int day=cal.get(Calendar.DAY_OF_WEEK);
//						System.out.println(day);
						switch(day) {
						case 1:
							long time=employee.getDepartment().getSunOutTime().getTime()-employee.getDepartment().getSunInTime().getTime();
							if(employee.getTotalTime()<time) {
								long shortTime=time-employee.getTotalTime();
								long diffSeconds	=shortTime/1000%60;
								long diffMinutes   = shortTime / (60 * 1000)%60;
								long diffHours  = shortTime / (60 * 60 * 1000)%24;
								Label shortLbl=new Label(diffHours+":"+diffMinutes+":"+diffSeconds);
								shortLbl.setStyle("-fx-font-weight:bold;-fx-text-fill:red");
//								setText(diffHours+":"+diffMinutes+":"+diffSeconds);
								setGraphic(shortLbl);
								break;
							}
						case 2:
							 time=employee.getDepartment().getMonOutTIme().getTime()-employee.getDepartment().getMonInTime().getTime();
//							 long diffHours1=time/((60 * 60 * 1000)%24);
//							 System.out.println(diffHours1);
							if(employee.getTotalTime()<time) {
								long shortTime=time-employee.getTotalTime();
								long diffSeconds	=shortTime/1000%60;
								long diffMinutes   = shortTime / (60 * 1000)%60;
								long diffHours  = shortTime / (60 * 60 * 1000)%24;
//								setText("Monday\n"+diffHours+":"+diffMinutes+":"+diffSeconds);
								Label shortLbl=new Label(diffHours+":"+diffMinutes+":"+diffSeconds);
								shortLbl.setStyle("-fx-font-weight:bold;-fx-text-fill:red");
//								setText(diffHours+":"+diffMinutes+":"+diffSeconds);
								setGraphic(shortLbl);
								break;
							}
						case 3:
							 time=employee.getDepartment().getTuesOutTime().getTime()-employee.getDepartment().getTuesInTIme().getTime();
							if(employee.getTotalTime()<time) {
								long shortTime=time-employee.getTotalTime();
								long diffSeconds	=shortTime/1000%60;
								long diffMinutes   = shortTime / (60 * 1000)%60;
								long diffHours  = shortTime / (60 * 60 * 1000)%24;
								Label shortLbl=new Label(diffHours+":"+diffMinutes+":"+diffSeconds);
								shortLbl.setStyle("-fx-font-weight:bold;-fx-text-fill:red");
//								setText(diffHours+":"+diffMinutes+":"+diffSeconds);
								setGraphic(shortLbl);
								break;
							}
						case 4:
							 time=employee.getDepartment().getWedOutTime().getTime()-employee.getDepartment().getWedInTime().getTime();
							if(employee.getTotalTime()<time) {
								long shortTime=time-employee.getTotalTime();
								long diffSeconds	=shortTime/1000%60;
								long diffMinutes   = shortTime / (60 * 1000)%60;
								long diffHours  = shortTime / (60 * 60 * 1000)%24;
								Label shortLbl=new Label(diffHours+":"+diffMinutes+":"+diffSeconds);
								shortLbl.setStyle("-fx-font-weight:bold;-fx-text-fill:red");
//								setText(diffHours+":"+diffMinutes+":"+diffSeconds);
								setGraphic(shortLbl);
								break;
							}
						case 5:
							 time=employee.getDepartment().getThursOutTime().getTime()-employee.getDepartment().getThursInTime().getTime();
							if(employee.getTotalTime()<time) {
								long shortTime=time-employee.getTotalTime();
								long diffSeconds	=shortTime/1000%60;
								long diffMinutes   = shortTime / (60 * 1000)%60;
								long diffHours  = shortTime / (60 * 60 * 1000)%24;
								Label shortLbl=new Label(diffHours+":"+diffMinutes+":"+diffSeconds);
								shortLbl.setStyle("-fx-font-weight:bold;-fx-text-fill:red");
//								setText(diffHours+":"+diffMinutes+":"+diffSeconds);
								setGraphic(shortLbl);
								break;
							}
						case 6:
							 time=employee.getDepartment().getFriOutTime().getTime()-employee.getDepartment().getFriInTime().getTime();
							if(employee.getTotalTime()<time) {
								long shortTime=time-employee.getTotalTime();
								long diffSeconds	=shortTime/1000%60;
								long diffMinutes   = shortTime / (60 * 1000)%60;
								long diffHours  = shortTime / (60 * 60 * 1000)%24;
								Label shortLbl=new Label(diffHours+":"+diffMinutes+":"+diffSeconds);
								shortLbl.setStyle("-fx-font-weight:bold;-fx-text-fill:red");
//								setText(diffHours+":"+diffMinutes+":"+diffSeconds);
								setGraphic(shortLbl);
								break;
							}
						case 7:
							 time=employee.getDepartment().getSatOutTime().getTime()-employee.getDepartment().getSatInTime().getTime();
//							 System.out.println(time+"total Time "+employee.getTotalTime());
//							  diffHours1=time/((60 * 60 * 1000)%24);
//							 System.out.println(diffHours1);
							if(employee.getTotalTime()<time) {
								long shortTime=time-employee.getTotalTime();
								long diffSeconds	=shortTime/1000%60;
								long diffMinutes   = shortTime / (60 * 1000)%60;
								long diffHours  = shortTime / (60 * 60 * 1000)%24;
								Label shortLbl=new Label(diffHours+":"+diffMinutes+":"+diffSeconds);
								shortLbl.setStyle("-fx-font-weight:bold;-fx-text-fill:red");
//								setText(diffHours+":"+diffMinutes+":"+diffSeconds);
								setGraphic(shortLbl);
								break;
							}
						}
						
					}else {
						setText(null);
						setGraphic(null);
					}
				}
			}
		});
		
		TableColumn<Employee, Employee> lateMarkCol= new TableColumn<Employee, Employee>("Late Mark");
		lateMarkCol.setCellValueFactory(e->new ReadOnlyObjectWrapper<>(e.getValue()));
		lateMarkCol.setCellFactory(e->new TableCell<Employee,Employee>(){
			@Override
		    protected void updateItem(Employee employee,boolean empty){
				if(employee==null){
					setGraphic(null);
					return;
				}else{
					if(!employee.getStatus().equals("Absent")) {
						Calendar cal=Calendar.getInstance();
						try {
							cal.setTime(new java.util.Date(dateFormat.parse(employee.getDate()).getTime()));
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						int day=cal.get(Calendar.DAY_OF_WEEK);
//						System.out.println(day);
						switch(day) {
						case 1:
//							long time=employee.getDepartment().getSunInTime().getTime()-employee.getInTime1();
//							System.out.println(employee.getInTime1().after(employee.getDepartment().getSunInTime()));
							if(employee.getInTime1().after(employee.getDepartment().getSunInTime())) {
//								long shortTime=time-employee/.getTotalT/ime();
//								long diffSeconds	=shortTime/1000%60;//
//								long diffMinutes   = shortTime / (60 * 1000)%60;
//								long diffHours  = shortTime / (60 * 60 * 1000)%24;
								Label yesLbl= new Label("Yes");
								yesLbl.setStyle("-fx-font-weight:bold;-fx-text-fill:red");
//								setText(diffHours+":"+diffMinutes+":"+diffSeconds);
								setGraphic(yesLbl);
								break;
							}else {
								setGraphic(null);
								setText(null);
								break;
							}
						case 2:
//							 time=employee.getDepartment().getMonOutTIme().getTime()-employee.getDepartment().getMonInTime().getTime();
//							 long diffHours1=time/((60 * 60 * 1000)%24);
//						 System.out.println(diffHours1);
//							System.out.println(employee.getInTime1().after(employee.getDepartment().getMonInTime()));
//							System.out.println(employee.getInTime1());
//							System.out.println(employee.getDepartment().getMonInTime());
							 if(employee.getInTime1().after(employee.getDepartment().getMonInTime())) {
//								long shortTime=time-employee.getTotalTime();
//								long diffSeconds	=shortTime/1000%60;
//								long diffMinutes   = shortTime / (60 * 1000)%60;
//								long diffHours  = shortTime / (60 * 60 * 1000)%24;
								 Label yesLbl= new Label("Yes");
									yesLbl.setStyle("-fx-font-weight:bold;-fx-text-fill:red");
//									setText(diffHours+":"+diffMinutes+":"+diffSeconds);
									setGraphic(yesLbl);
								break;
							}else {
								setGraphic(null);
								setText(null);
								break;
							}
						case 3:
//							 time=employee.getDepartment().getTuesOutTime().getTime()-employee.getDepartment().getTuesInTIme().getTime();
							if(employee.getInTime1().after(employee.getDepartment().getTuesInTIme())) {
//								long shortTime=time-employee.getTotalTime();
//								long diffSeconds	=shortTime/1000%60;
//								long diffMinutes   = shortTime / (60 * 1000)%60;
//								long diffHours  = shortTime / (60 * 60 * 1000)%24;
								Label yesLbl= new Label("Yes");
								yesLbl.setStyle("-fx-font-weight:bold;-fx-text-fill:red");
//								setText(diffHours+":"+diffMinutes+":"+diffSeconds);
								setGraphic(yesLbl);
								break;
							}else {
								setGraphic(null);
								setText(null);
								break;
							}
						case 4:
//							 time=employee.getDepartment().getWedOutTime().getTime()-employee.getDepartment().getWedInTime().getTime();
							 if(employee.getInTime1().after(employee.getDepartment().getWedInTime())) {
//								long shortTime=time-employee.getTotalTime();
//								long diffSeconds	=shortTime/1000%60;
//								long diffMinutes   = shortTime / (60 * 1000)%60;
//								long diffHours  = shortTime / (60 * 60 * 1000)%24;
								 Label yesLbl= new Label("Yes");
									yesLbl.setStyle("-fx-font-weight:bold;-fx-text-fill:red");
//									setText(diffHours+":"+diffMinutes+":"+diffSeconds);
									setGraphic(yesLbl);
								break;
							}else {
								setGraphic(null);
								setText(null);
								break;
							}
						case 5:
//							 time=employee.getDepartment().getThursOutTime().getTime()-employee.getDepartment().getThursInTime().getTime();
							 if(employee.getInTime1().after(employee.getDepartment().getThursInTime())) {
//								long shortTime=time-employee.getTotalTime();
//								long diffSeconds	=shortTime/1000%60;
//								long diffMinutes   = shortTime / (60 * 1000)%60;
//								long diffHours  = shortTime / (60 * 60 * 1000)%24;
								 Label yesLbl= new Label("Yes");
									yesLbl.setStyle("-fx-font-weight:bold;-fx-text-fill:red");
//									setText(diffHours+":"+diffMinutes+":"+diffSeconds);
									setGraphic(yesLbl);
								break;
							}else {
								setGraphic(null);
								setText(null);
								break;
							}
						case 6:
//							 time=employee.getDepartment().getFriOutTime().getTime()-employee.getDepartment().getFriInTime().getTime();
							 if(employee.getInTime1().after(employee.getDepartment().getFriInTime())) {
//								long shortTime=time-employee.getTotalTime();
//								long diffSeconds	=shortTime/1000%60;
//								long diffMinutes   = shortTime / (60 * 1000)%60;
//								long diffHours  = shortTime / (60 * 60 * 1000)%24;
								 Label yesLbl= new Label("Yes");
									yesLbl.setStyle("-fx-font-weight:bold;-fx-text-fill:red");
//									setText(diffHours+":"+diffMinutes+":"+diffSeconds);
									setGraphic(yesLbl);
								break;
							}else {
								setGraphic(null);
								setText(null);
								break;
							}
						case 7:
//							 time=employee.getDepartment().getSatOutTime().getTime()-employee.getDepartment().getSatInTime().getTime();
//							 System.out.println(time+"total Time "+employee.getTotalTime());
//							  diffHours1=time/((60 * 60 * 1000)%24);
//							 System.out.println(diffHours1);
							 if(employee.getInTime1().after(employee.getDepartment().getSatInTime())) {
//								long shortTime=time-employee.getTotalTime();
//								long dif/fSeconds	=shortTime/1000%60;
//								long diffMinutes   = shortTime / (60 * 1000)%60;
//								long diff/Hours  = shortTime / (60 * 60 * 1000)%24;
								 Label yesLbl= new Label("Yes");
									yesLbl.setStyle("-fx-font-weight:bold;-fx-text-fill:red");
//									setText(diffHours+":"+diffMinutes+":"+diffSeconds);
									setGraphic(yesLbl);
								break;
							}else {
								setGraphic(null);
								setText(null);
								break;
							}
						}
						
					}else {
						setText(null);
					}
					}
				
			}
		});

//		statusCol.setCellValueFactory(e->new ReadOnlyObjectWrapper<>(e.getValue()));
//		statusCol.setCellFactory(e->new TableCell<Employee,Employee>(){
//
//			@Override
//		    protected void updateItem(Employee employee,boolean empty){
//				if(employee==null){
//					setGraphic(null);
//					setText(null);
//					return;
//				}else{
//				 boolean presentFlag=false;
//				 boolean absentFlag=false;
//				 String prsent="";
//				 String absent="";
//				 
//				
//				 
////					for(int i=1;i<employeeList.size();i++) {
////						DateTime dt1= new DateTime(employeeList.get(i).getDateTime());
//////						for(int j=0;j<employeeList.size();j++) {
//////							if(!employeeList.get(j).equals(employeeList.get(i))) {
////						DateTime dt2= new DateTime(employeeList.get(i-1).getDateTime());
////						Date date1=new Date(employeeList.get(i).getDateTime().getTime());
////						Date date2=new Date(employeeList.get(i-1).getDateTime().getTime());
//////						System.out.println(date1);
////						if(date1.compareTo(date2)==0) {
////							setText("Present");
////							i=i+1;
////						}else {
////							setText("absent");
////						}
//////						if(dt1.withTimeAtStartOfDay().isEqual(dt2.withTimeAtStartOfDay())) {
////////							System.out.println("Hi");
//////							presentFlag=true;
//////							absentFlag=false;
//////							break;
//////						}else   {
////////							setText("absent");
//////							absentFlag=true;
//////							presentFlag=false;
//////							continue;
////////							setGraphic(null);
//////						}
//////							}
//////		
//////						}
//////						if(presentFlag) {
//////							setText("present");
//////						}else if(absentFlag) {
//////							setText("absent");
//////						}
//////						
////						
////						
//					}
//////					
//				
//				
//			}
//				});
//		
		employeeView.getColumns().addAll(deptCol,empNoCol,empCol,dateCol,dateTimeCol,statusCol,breakTimeCol,shortTimeCol,lateMarkCol);
		
		
		Collections.sort(employeeData,new DateComparator());
		
//		  SortedList<Employee> emSortedList=new SortedList<>(employeeData);
//		 Collections.sort(employeeData);
//			employeeData.addAll(employeeDataList);
		  employeeView.setItems(employeeData);
		  
		return employeeView;
	
	}
	
	private static List<Date> fillInDates( List<Date> tempDateList,String monthName,String yearName,String format) throws ParseException {
		  
		 List<Date> dateList=new ArrayList<>();
		 Calendar monthStart = new GregorianCalendar(Integer.parseInt(yearName), Integer.parseInt(monthName)-1, 1);
		 int numDays= monthStart.getActualMaximum(Calendar.DAY_OF_MONTH);
		 SimpleDateFormat dateFormat=new SimpleDateFormat("EEE,dd-MMM-yyyy");
		
//		calendar.set(Calendar.YEAR, Integer.parseInt(yearName));
//		calendar.set(Calendar.MONTH, Integer.parseInt(monthName));
//		 = calendar.getActualMaximum(Calendar.DATE);
//		System.out.println(numDays);
		 
		String date1="00-"+monthName+"-"+Integer.parseInt(yearName)+"";
		String date2=numDays+"-"+monthName+"-"+Integer.parseInt(yearName)+"";
//		System.out.println(date1);
//		System.out.println(date2);
	    SimpleDateFormat sdf = new SimpleDateFormat(format);
	    
	    Calendar calendar1 = Calendar.getInstance();
//	    calendar1.setTimeInMillis(0);
	    calendar1.setTime(new java.sql.Date(sdf.parse(date1).getTime()));
//	    System.out.println(calendar1.getTime());
	    Calendar calendar2 = Calendar.getInstance();
//	    calendar2.setTimeInMillis(0);
	    calendar2.setTime(sdf.parse(date2));

	    java.util.Date currentDate = calendar1.getTime();
	   for(int i=1;i<=numDays;i++) {
		   if(!calendar1.getTime().equals(calendar2.getTime())) {
			   calendar1.add(Calendar.DAY_OF_MONTH, 1);
	        currentDate = calendar1.getTime();
	        Date sqlDate=new Date(currentDate.getTime());
	      if(!tempDateList.contains(sqlDate)) {
	    	  dateList.add(sqlDate);
	      }
	       
	      
//	        System.out.println(sqlDate.toString());
//	        System.out.println(currentDate.toString());
	       
		   }
//	        System.out.println("Reached");
	    }
//	    for(Date s:dateList) {
//	    	 String date=dateFormat.format(s);
//	    	 for(Date d:tempDateList) {
//	    		 String tempDate=dateFormat.format(d);
//	    		 if(date.equals(tempDate)) {
//	    			 dateList.remove(s);
//	    		 }
//	    	 }
//	    }
	    	
//	    long min = Long.MAX_VALUE, max = Long.MIN_VALUE;
//	    
//	    for (Employee e : employeeData) {
////	    	System.out.println(e.getDateTime());
//	        long time = sdf.parse(e.getDate().toString()).getTime();
//	        if(min > time) min = time;
//	        if(max < time) max = time;
//	    }
//	    String[] dates2 = new String[(int) ((max - min)/MILLIS_PER_DAY+1)];
//	    for(int i=0;i<dates2.length;i++) {
//	        dates2[i] = sdf.format(new Date(min + i * MILLIS_PER_DAY));
//	    }
	   
//	    System.out.println(Arrays.toString(dates2));
	    
	    return dateList;
	    
	}
	
	public GridPane viewBreakTime(Employee employee) {
		List<Employee> employeeList=employeeDAO.viewBreakTime(employee);
		GridPane gp=new GridPane();
//		errorTip.setAutoHide(true);
		gp.getStyleClass().add("grid");
		gp.setHgap(10);
		gp.setVgap(10);
		gp.setAlignment(Pos.CENTER);
		gp.setPadding(new Insets(10, 10, 10, 10));
//		gp.setGridLinesVisible(true);
		breakList.clear();
		Label titleLabel = new Label(" View Break Time");
		GridPane.setMargin(titleLabel, new Insets(0,-50,0,50));
		gp.add(titleLabel, 0, 0);
		
		TableView<Employee> breakView= new TableView<Employee>();
		TableColumn<Employee, String> inTimeCol= new TableColumn<Employee, String>("In Time");
		inTimeCol.setPrefWidth(120);
		inTimeCol.setCellValueFactory(new PropertyValueFactory<>("inTime"));
		TableColumn<Employee, String> outTimeCol= new TableColumn<Employee, String>("Out Time");
		outTimeCol.setCellValueFactory(new PropertyValueFactory<>("outTime"));
		outTimeCol.setPrefWidth(120);
		breakView.getColumns().addAll(outTimeCol,inTimeCol);
		SimpleDateFormat format= new SimpleDateFormat("hh:mm:ss a");
		String inTime="";
		String outTime="";
		
//		int length=employeeList.size();
		for(Employee e1:employeeList) {
			 if(!(e1.getTime2().equals(java.sql.Timestamp.valueOf("1917-10-10 00:00:00")))&&!(e1.getTime4().equals(java.sql.Timestamp.valueOf("1917-10-10 00:00:00")))) {
				  outTime=format.format(e1.getTime2());
				  inTime=format.format(e1.getTime3());
				
				  breakList.add(new Employee(outTime,inTime));
				 //System.out.println("2"+outTime); 
			   }
			 
			 
		  if (!(e1.getTime4().equals(java.sql.Timestamp.valueOf("1917-10-10 00:00:00")))&&!(e1.getTime6().equals(java.sql.Timestamp.valueOf("1917-10-10 00:00:00")))){
					  outTime=format.format(e1.getTime4());
					  inTime=format.format(e1.getTime5());
					  breakList.add(new Employee(outTime,inTime));
					 // System.out.println("4");
					  
		      }
		  if(!(e1.getTime6().equals(java.sql.Timestamp.valueOf("1917-10-10 00:00:00")))&&!(e1.getTime8().equals(java.sql.Timestamp.valueOf("1917-10-10 00:00:00")))) {
						  outTime=format.format(e1.getTime6());
						  inTime=format.format(e1.getTime7());
						  breakList.add(new Employee(outTime,inTime));
						  //System.out.println("6");
						  
		      }
		  if(!(e1.getTime8().equals(java.sql.Timestamp.valueOf("1917-10-10 00:00:00")))&&!(e1.getTime10().equals(java.sql.Timestamp.valueOf("1917-10-10 00:00:00")))) {
			  	outTime=format.format(e1.getTime8());
			  	inTime=format.format(e1.getTime9());
			  	breakList.add(new Employee(outTime,inTime));
			 // System.out.println("10");		  
		     }			  
		  
		  
		}
//		employeeList.remove(employeeList.size()-1);
//		if(length<=4) {
//			for(int i=0;i<employeeList.size();i++) {
//				if(i==0) {
//					employeeList.remove(i);
//					continue;
//				}
//				if(i==employeeList.size()-1) {
//					break;
//				}
//				
//				inTime=format.format(employeeList.get(i).getDateTime());
//				System.out.println("inTime "+inTime);
//				for(int j=0;j<employeeList.size();j++) {
//					if(j==employeeList.size()-1) {
//						break;
//					}
//					if(!employeeList.get(i).equals(employeeList.get(j))) {
//					outTime=format.format(employeeList.get(j).getDateTime());
////					System.out.println("outTIme"+outTime);
//					}
//				}
////				System.out.println("countOuter "+i);
//				
//				breakList.add(new Employee(outTime,inTime));
//				
//			}
//		}
//		System.out.println(breakList.size());
		breakView.setItems(breakList);
		breakView.setMaxSize(300, 200);
		gp.add(breakView, 0, 2,2,1);
		
		return gp;
		
	}
	
	
	public TableView<Employee> getDateData1(String deptName,String dateWise){
		  
		  employeeData.clear();
		  
		  List<Employee> employeeList1=employeeDAO.getDateData(deptName,dateWise);
		 
		 
		  SimpleDateFormat dateFormat= new SimpleDateFormat("EEE,dd-MM-yyyy");
//		  long srNo1=1;
		  long diff=0;
		  long diff1=0;
		  long d=0;
//		  long diffSeconds=0;
//		  long diffMinutes=0;
//		  long diffHours =0;
		  Time inTime=null;
		  String inTime1="";
		  String outTime="";
		  String breaktime="";
		  String status1="";
		  Date sqlDate= null;
		  SimpleDateFormat timeFormatter=new SimpleDateFormat("hh:mm:ss a");
		  SimpleDateFormat formatter=new SimpleDateFormat("dd-MM-yy hh:mm:ss a");
		  SimpleDateFormat format=new SimpleDateFormat("dd-MMM-yy");
		 
		  
		  for(Employee e1:employeeList1) {
			  sqlDate= new Date(e1.getDateTime().getTime());
			  Date date1=new Date(e1.getTime1().getTime());
			  
			  Date date2=null;
			  String date=dateFormat.format(e1.getDateTime().getTime());
			  if(e1.getTime1()!=java.sql.Timestamp.valueOf("1917-10-10 00:00:00")) {
				
				  inTime1=timeFormatter.format(e1.getTime1());
				  
					try {
						inTime=new Time(timeFormatter.parse(inTime1).getTime());
					} catch (ParseException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
//				System.out.println(inTime);
			  } 
			  if(!(e1.getTime2().equals(java.sql.Timestamp.valueOf("1917-10-10 00:00:00")))) {
					  outTime=timeFormatter.format(e1.getTime2());
					  date2=new Date(e1.getTime2().getTime());
					 //System.out.println("2"+outTime); 
				   } 
			  if (!(e1.getTime4().equals(java.sql.Timestamp.valueOf("1917-10-10 00:00:00"))) ){
						  outTime=timeFormatter.format(e1.getTime4());
						  date2=new Date(e1.getTime4().getTime());
						 // System.out.println("4");
						  
			      }
			  if(!(e1.getTime6().equals(java.sql.Timestamp.valueOf("1917-10-10 00:00:00")))) {
							  outTime=timeFormatter.format(e1.getTime6());
							  date2=new Date(e1.getTime6().getTime());
							  //System.out.println("6");
							  
			      }
			  if(!(e1.getTime8().equals(java.sql.Timestamp.valueOf("1917-10-10 00:00:00")))) {
				  outTime=timeFormatter.format(e1.getTime8());
				  date2=new Date(e1.getTime8().getTime());
				 // System.out.println("10");		  
			     }			  
			  if(!(e1.getTime10().equals(java.sql.Timestamp.valueOf("1917-10-10 00:00:00")))) {
									  outTime=timeFormatter.format(e1.getTime10());
									  date2=new Date(e1.getTime10().getTime());
									// System.out.println("10");
				}
			  if(date2==null) {
				  status1="Absent";
			  }else {
			  diff=date2.getTime()-date1.getTime();
//			  diffSeconds	=diff/1000%60;
//			  diffMinutes   = diff / (60 * 1000)%60;
//			  diffHours  = diff / (60 * 60 * 1000)%24;
			  status1="Present";
			  }
			  
			  if(!(e1.getTime2().equals(java.sql.Timestamp.valueOf("1917-10-10 00:00:00")))&&!(e1.getTime4().equals(java.sql.Timestamp.valueOf("1917-10-10 00:00:00")))) {
					
					 diff1=e1.getTime3().getTime()-e1.getTime2().getTime();
//					 System.out.println("3-2"+employee.getEmployeeName()+" date "+employee.getSqlDate()+" total time"+employee.getTotalTime()+" diff "+(employee.getTotalTime()-diff) / (60 * 60 * 1000)%24);
				   }
				 
				 
			  if (!(e1.getTime4().equals(java.sql.Timestamp.valueOf("1917-10-10 00:00:00")))&&!(e1.getTime6().equals(java.sql.Timestamp.valueOf("1917-10-10 00:00:00")))){
						 
						 // System.out.println("4");
						  diff1=diff1+(e1.getTime5().getTime()-e1.getTime4().getTime());
//						  System.out.println("5-4"+employee.getEmployeeName()+" date "+employee.getSqlDate()+" diff "+(employee.getTotalTime()-diff) / (60 * 60 * 1000)%24);
			      }
			  if(!(e1.getTime6().equals(java.sql.Timestamp.valueOf("1917-10-10 00:00:00")))&&!(e1.getTime8().equals(java.sql.Timestamp.valueOf("1917-10-10 00:00:00")))) {
							 diff1=diff1+(e1.getTime7().getTime()-e1.getTime6().getTime());  
//							 System.out.println("7-6"+employee.getEmployeeName()+" date "+e1.getTime6()+" diff "+(employee.getTotalTime()-diff) / (60 * 60 * 1000)%24);
			      }
			  if(!(e1.getTime8().equals(java.sql.Timestamp.valueOf("1917-10-10 00:00:00")))&&!(e1.getTime10().equals(java.sql.Timestamp.valueOf("1917-10-10 00:00:00")))) {
				  	diff1=diff1+(e1.getTime9().getTime()-e1.getTime8().getTime());
//				  	System.out.println("9-8"+employee.getEmployeeName()+" date "+employee.getSqlDate()+" diff "+(employee.getTotalTime()-diff) / (60 * 60 * 1000)%24);
				 // System.out.println("10");		  
			     }			  
			  
			  	diff=diff-diff1;
			  	
				  employeeData.add(new Employee(e1.getId(),e1.getDepartment().getDeptName(),e1.getEmployeeName(),e1.getEmployeeNo(),sqlDate,date,e1.getDepartment(),status1,inTime1,outTime,diff,inTime,e1.getTime4()));
				  tempEmpList.add(new Employee(e1.getId(),e1.getDepartment().getDeptName(),e1.getEmployeeName(),e1.getEmployeeNo(),sqlDate,date,e1.getDepartment(),status1,inTime1,outTime,diff,inTime,e1.getTime4()));
				  outTime=null;
				  inTime=null;
				  inTime1=null;
				  diff=0;
				  diff1=0;
				  
				  //System.out.println("Enter into list data -----------------------");
			  
			//  System.out.println("Loop End");
			  
		  }
		  
		 
//		   for(Employee e1:employeeList1) {
//		    int count1=0;
//		    System.out.println("for");
////		    String date1=formatter.format(e.getDateTime().getTime());
//		    Date date1=new Date(e1.getDateTime().getTime());
//		    inTime1=formatter.format(e1.getDateTime());
//		    for(Employee em1:emList1) {
//		     //count1=0;
////		     String date2=formatter.format(e.getDateTime().getTime());
//		     Date date2=new Date(em1.getDateTime().getTime());
//		    // if(String.valueOf(date1).equals(String.valueOf(date2))){
//		    // if(e1.getEmployeeName().toString().equals(em1.getEmployeeName().toString())) {
//		     if(e1.getEmployeeNo()==em1.getEmployeeNo()) {
//		      count1++;
//		      System.out.println("============="+em1.getEmployeeName()+" "+e1.getEmployeeName()+"     "+count1);
//		      if(count1%2==0) {
//		      diff1= em1.getDateTime().getTime()-e1.getDateTime().getTime();
//		      outTime=formatter.format(em1.getDateTime());
//		      diffSeconds1 =diff1/1000%60;
//		      diffMinutes1   = diff1 / (60 * 1000)%60;
//		      diffHours1  = diff1 / (60 *60 * 1000)%24;
//		      }
//		    
//		      if(count1==3) {
//		   
//		      System.out.println("count break time "+count1);
//		             diff1= em1.getDateTime().getTime()-d;
//		       diffSeconds1 =diff1/1000%60;
//		       diffMinutes1   = diff1 / (60 * 1000)%60;
//		       diffHours1  = diff1 / (60 * 60 * 1000)%24;
//		       breaktime=diffHours1+":"+diffMinutes1+":"+diffSeconds1;
//		      }
//		     }
//		     d=em1.getDateTime().getTime();
//		     System.out.println(d);
//		    }
//		    
////		    System.out.println(count);
//		    if(count1>1) {
//		     status1="present";
//		    }else if(count1==1) {
//		    status1="absent";
//		    }
//		   
//		   if(status1.equals("present")) {
//		    status1=status1+"\n"+diffHours1+":"+diffMinutes1+":"+diffSeconds1;
//		   }
//		   String date=formatter.format(e1.getDateTime());
//		   
//		//   System.out.println(e.getDateTime());
//		//   System.out.println(date);
//		  
//		   if(diffHours1==0&&diffMinutes1==0&&diffSeconds1==0) {
//		    outTime=null;
//		   }
//		   if(!status1.equalsIgnoreCase("present\n0:0:0")) {
//		   employeeData.add(new Employee(e1.getId(),e1.getDepartment().getDeptName(),e1.getEmployeeName(),e1.getEmployeeNo(),srNo1,date,e1.getDepartment(),status1,inTime1,outTime,breaktime));
//		   srNo1++;
//		   } 
//		   breaktime="";
//		  }
		  
		  
		  TableView<Employee> employeeView= new TableView<Employee>();
		  TableColumn<Employee, Long> srNoCol= new TableColumn<Employee, Long>("Sr No");
		  srNoCol.setCellValueFactory(new PropertyValueFactory<>("srNo"));
		  TableColumn<Employee, String> deptCol= new TableColumn<Employee, String>("Department");
		  deptCol.setCellValueFactory(new PropertyValueFactory<>("deptName"));
		  TableColumn<Employee, Long> empNoCol= new TableColumn<Employee, Long>("Employee\n"+" Number");
		  empNoCol.setCellValueFactory(new PropertyValueFactory<>("employeeNo"));
		  TableColumn<Employee, String> empCol= new TableColumn<Employee, String>("Employee");
		  empCol.setCellValueFactory(new PropertyValueFactory<>("employeeName"));
		  TableColumn<Employee, String> dateCol= new TableColumn<Employee, String>("Date");
		  dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
		  TableColumn<Employee, String> dateTimeCol= new TableColumn<Employee, String>("Time");
		  TableColumn<Employee, String> fromDatCol= new TableColumn<Employee, String>("In Time");
		  fromDatCol.setCellValueFactory(new PropertyValueFactory<>("inTime"));
		  TableColumn<Employee,String> toDateCol= new TableColumn<Employee, String>("Out Time");
		  toDateCol.setCellValueFactory(new PropertyValueFactory<>("outTime"));
		  
		  dateTimeCol.getColumns().addAll(fromDatCol,toDateCol);
		  
		  TableColumn<Employee, Employee> statusCol= new TableColumn<Employee, Employee>("Status");
		  statusCol.setCellValueFactory(e->new ReadOnlyObjectWrapper<>(e.getValue()));
			statusCol.setCellFactory(e->new TableCell<Employee,Employee>(){
				@Override
			    protected void updateItem(Employee employee,boolean empty){
					if(employee==null){
						setGraphic(null);
						return;
					}else{
						if(employee.getStatus().equalsIgnoreCase("Absent")) {
							Label absentLbl= new Label("Absent");
							absentLbl.setStyle("-fx-font-weight:bold;-fx-text-fill:red");
							setGraphic(absentLbl);
				
						}else if(employee.getStatus().equalsIgnoreCase("Present")) {
							long diffSeconds	=employee.getTotalTime()/1000%60;
							  long diffMinutes   = employee.getTotalTime() / (60 * 1000)%60;
							  long diffHours  = employee.getTotalTime() / (60 * 60 * 1000)%24;
							Label presentLbl= new Label("Present\n"+diffHours+":"+diffMinutes+":"+diffSeconds);
							presentLbl.setStyle("-fx-font-weight:bold;-fx-text-fill:green");
							employee.setStatus(presentLbl.getText());
							setGraphic(presentLbl);
						}else {
							setGraphic(null);
						}
					}
				}
			});
			TableColumn<Employee, Employee> breakTimeCol= new TableColumn<Employee, Employee>("Break Time");
			breakTimeCol.setCellValueFactory(e->new ReadOnlyObjectWrapper<>(e.getValue()));
			breakTimeCol.setCellFactory(e->new TableCell<Employee,Employee>(){

				@Override
			    protected void updateItem(Employee employee,boolean empty){
					if(employee==null){
						setGraphic(null);
						return;
					}else{
						if(!employee.getStatus().equals("Absent")&&!(employee.getTime4().equals(java.sql.Timestamp.valueOf("1917-10-10 00:00:00")))) {
							
						breakTimeDetail=new JFXButton("Break Time");
						setGraphic(breakTimeDetail);
						breakTimeDetail.getStyleClass().add("del-btn");
						breakTimeDetail.setAlignment(Pos.CENTER);
						breakTimeDetail.setFocusTraversable(true);
						breakTimeDetail.setOnAction(new EventHandler<ActionEvent>() {
							
							@Override
							public void handle(ActionEvent event) {
								// TODO Auto-generated method stub
								GridPane gridPane= viewBreakTime(employee);
								Scene scene = new Scene(gridPane,500,400);
								scene.getStylesheets().add(getClass().getResource("/images/popup.css").toExternalForm());
								scene.setFill(Color.TRANSPARENT);
								Stage dialog = new Stage();

								dialog.setScene(scene);
//								dialog.setX(200);
//								dialog.setY(150);
//								dialog.setTitle("Add New Supplier");
								dialog.initOwner(Main.primaryStage);
								dialog.initModality(Modality.WINDOW_MODAL);
								dialog.initStyle(StageStyle.TRANSPARENT);
								ImageView closeImg = new ImageView(new Image(ResourceLoader.load("/images/closeIcon.png")));
						        closeImg.setFitHeight(30);
						        closeImg.setFitWidth(30);
						        closeImg.setStyle("-fx-cursor:hand");
						        GridPane.setMargin(closeImg, new Insets(0,-50,20,150));
						        gridPane.add(closeImg, 1, 0);
						        closeImg.setOnMouseClicked(new EventHandler<MouseEvent>() {

						    		@Override
						    		public void handle(MouseEvent arg0) {
						    			// TODO Auto-generated method stub
						    			dialog.close();
						    		}

						    	});

								dialog.show();
						
							}
						});
						}else {
							setGraphic(null);
						}
					}
				}
			});
			
			TableColumn<Employee, Employee> shortTimeCol= new TableColumn<Employee, Employee>("Short Time");
			shortTimeCol.setCellValueFactory(e->new ReadOnlyObjectWrapper<>(e.getValue()));
			shortTimeCol.setCellFactory(e->new TableCell<Employee,Employee>(){
				@Override
			    protected void updateItem(Employee employee,boolean empty){
					if(employee==null){
						setGraphic(null);
						setText(null);
						return;
					}else{
						if(!employee.getStatus().equals("Absent")) {
							Calendar cal=Calendar.getInstance();
							try {
								cal.setTime(new java.util.Date(dateFormat.parse(employee.getDate()).getTime()));
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							int day=cal.get(Calendar.DAY_OF_WEEK);
//							System.out.println(day);
							switch(day) {
							case 1:
								long time=employee.getDepartment().getSunOutTime().getTime()-employee.getDepartment().getSunInTime().getTime();
								if(employee.getTotalTime()<time) {
									long shortTime=time-employee.getTotalTime();
									long diffSeconds	=shortTime/1000%60;
									long diffMinutes   = shortTime / (60 * 1000)%60;
									long diffHours  = shortTime / (60 * 60 * 1000)%24;
									Label shortLbl=new Label(diffHours+":"+diffMinutes+":"+diffSeconds);
									employee.setShortTime(shortLbl.getText());
									shortLbl.setStyle("-fx-font-weight:bold;-fx-text-fill:red");
//									setText(diffHours+":"+diffMinutes+":"+diffSeconds);
									setGraphic(shortLbl);
									break;
								}
							case 2:
								 time=employee.getDepartment().getMonOutTIme().getTime()-employee.getDepartment().getMonInTime().getTime();
//								 long diffHours1=time/((60 * 60 * 1000)%24);
//								 System.out.println(diffHours1);
								if(employee.getTotalTime()<time) {
									long shortTime=time-employee.getTotalTime();
									long diffSeconds	=shortTime/1000%60;
									long diffMinutes   = shortTime / (60 * 1000)%60;
									long diffHours  = shortTime / (60 * 60 * 1000)%24;
//									setText("Monday\n"+diffHours+":"+diffMinutes+":"+diffSeconds);
									Label shortLbl=new Label(diffHours+":"+diffMinutes+":"+diffSeconds);
									employee.setShortTime(shortLbl.getText());
									shortLbl.setStyle("-fx-font-weight:bold;-fx-text-fill:red");
//									setText(diffHours+":"+diffMinutes+":"+diffSeconds);
									setGraphic(shortLbl);
									break;
								}
							case 3:
								 time=employee.getDepartment().getTuesOutTime().getTime()-employee.getDepartment().getTuesInTIme().getTime();
								if(employee.getTotalTime()<time) {
									long shortTime=time-employee.getTotalTime();
									long diffSeconds	=shortTime/1000%60;
									long diffMinutes   = shortTime / (60 * 1000)%60;
									long diffHours  = shortTime / (60 * 60 * 1000)%24;
									Label shortLbl=new Label(diffHours+":"+diffMinutes+":"+diffSeconds);
									employee.setShortTime(shortLbl.getText());
									shortLbl.setStyle("-fx-font-weight:bold;-fx-text-fill:red");
//									setText(diffHours+":"+diffMinutes+":"+diffSeconds);
									setGraphic(shortLbl);
									break;
								}
							case 4:
								 time=employee.getDepartment().getWedOutTime().getTime()-employee.getDepartment().getWedInTime().getTime();
								if(employee.getTotalTime()<time) {
									long shortTime=time-employee.getTotalTime();
									long diffSeconds	=shortTime/1000%60;
									long diffMinutes   = shortTime / (60 * 1000)%60;
									long diffHours  = shortTime / (60 * 60 * 1000)%24;
									Label shortLbl=new Label(diffHours+":"+diffMinutes+":"+diffSeconds);
									employee.setShortTime(shortLbl.getText());
									shortLbl.setStyle("-fx-font-weight:bold;-fx-text-fill:red");
//									setText(diffHours+":"+diffMinutes+":"+diffSeconds);
									setGraphic(shortLbl);
									break;
								}
							case 5:
								 time=employee.getDepartment().getThursOutTime().getTime()-employee.getDepartment().getThursInTime().getTime();
								if(employee.getTotalTime()<time) {
									long shortTime=time-employee.getTotalTime();
									long diffSeconds	=shortTime/1000%60;
									long diffMinutes   = shortTime / (60 * 1000)%60;
									long diffHours  = shortTime / (60 * 60 * 1000)%24;
									Label shortLbl=new Label(diffHours+":"+diffMinutes+":"+diffSeconds);
									employee.setShortTime(shortLbl.getText());
									shortLbl.setStyle("-fx-font-weight:bold;-fx-text-fill:red");
//									setText(diffHours+":"+diffMinutes+":"+diffSeconds);
									setGraphic(shortLbl);
									break;
								}
							case 6:
								 time=employee.getDepartment().getFriOutTime().getTime()-employee.getDepartment().getFriInTime().getTime();
								if(employee.getTotalTime()<time) {
									long shortTime=time-employee.getTotalTime();
									long diffSeconds	=shortTime/1000%60;
									long diffMinutes   = shortTime / (60 * 1000)%60;
									long diffHours  = shortTime / (60 * 60 * 1000)%24;
									Label shortLbl=new Label(diffHours+":"+diffMinutes+":"+diffSeconds);
									employee.setShortTime(shortLbl.getText());
									shortLbl.setStyle("-fx-font-weight:bold;-fx-text-fill:red");
//									setText(diffHours+":"+diffMinutes+":"+diffSeconds);
									setGraphic(shortLbl);
									break;
								}
							case 7:
								 time=employee.getDepartment().getSatOutTime().getTime()-employee.getDepartment().getSatInTime().getTime();
//								 System.out.println(time+"total Time "+employee.getTotalTime());
//								  diffHours1=time/((60 * 60 * 1000)%24);
//								 System.out.println(diffHours1);
								if(employee.getTotalTime()<time) {
									long shortTime=time-employee.getTotalTime();
									long diffSeconds	=shortTime/1000%60;
									long diffMinutes   = shortTime / (60 * 1000)%60;
									long diffHours  = shortTime / (60 * 60 * 1000)%24;
									Label shortLbl=new Label(diffHours+":"+diffMinutes+":"+diffSeconds);
									employee.setShortTime(shortLbl.getText());
									shortLbl.setStyle("-fx-font-weight:bold;-fx-text-fill:red");
//									setText(diffHours+":"+diffMinutes+":"+diffSeconds);
									setGraphic(shortLbl);
									break;
								}
							}
							
						}else {
							setText(null);
							setGraphic(null);
						}
					}
				}
			});
			
			TableColumn<Employee, Employee> lateMarkCol= new TableColumn<Employee, Employee>("Late Mark");
			lateMarkCol.setCellValueFactory(e->new ReadOnlyObjectWrapper<>(e.getValue()));
			lateMarkCol.setCellFactory(e->new TableCell<Employee,Employee>(){
				@Override
			    protected void updateItem(Employee employee,boolean empty){
					if(employee==null){
						setGraphic(null);
						return;
					}else{
						if(!employee.getStatus().equals("Absent")) {
							Calendar cal=Calendar.getInstance();
							try {
								cal.setTime(new java.util.Date(dateFormat.parse(employee.getDate()).getTime()));
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							int day=cal.get(Calendar.DAY_OF_WEEK);
//							System.out.println(day);
							switch(day) {
							case 1:
//								long time=employee.getDepartment().getSunInTime().getTime()-employee.getInTime1();
//								System.out.println(employee.getInTime1().after(employee.getDepartment().getSunInTime()));
								if(employee.getInTime1().after(employee.getDepartment().getSunInTime())) {
//									long shortTime=time-employee/.getTotalT/ime();
//									long diffSeconds	=shortTime/1000%60;//
//									long diffMinutes   = shortTime / (60 * 1000)%60;
//									long diffHours  = shortTime / (60 * 60 * 1000)%24;
									Label yesLbl= new Label("Yes");
									employee.setLateMark(yesLbl.getText());
									yesLbl.setStyle("-fx-font-weight:bold;-fx-text-fill:red");
//									setText(diffHours+":"+diffMinutes+":"+diffSeconds);
									setGraphic(yesLbl);
									break;
								}else {
									setGraphic(null);
									setText(null);
									break;
								}
							case 2:
//								 time=employee.getDepartment().getMonOutTIme().getTime()-employee.getDepartment().getMonInTime().getTime();
//								 long diffHours1=time/((60 * 60 * 1000)%24);
//							 System.out.println(diffHours1);
//								System.out.println(employee.getInTime1().after(employee.getDepartment().getMonInTime()));
//								System.out.println(employee.getInTime1());
//								System.out.println(employee.getDepartment().getMonInTime());
								 if(employee.getInTime1().after(employee.getDepartment().getMonInTime())) {
//									long shortTime=time-employee.getTotalTime();
//									long diffSeconds	=shortTime/1000%60;
//									long diffMinutes   = shortTime / (60 * 1000)%60;
//									long diffHours  = shortTime / (60 * 60 * 1000)%24;
									 Label yesLbl= new Label("Yes");
									 employee.setLateMark(yesLbl.getText());
										yesLbl.setStyle("-fx-font-weight:bold;-fx-text-fill:red");
//										setText(diffHours+":"+diffMinutes+":"+diffSeconds);
										setGraphic(yesLbl);
									break;
								}else {
									setGraphic(null);
									setText(null);
									break;
								}
							case 3:
//								 time=employee.getDepartment().getTuesOutTime().getTime()-employee.getDepartment().getTuesInTIme().getTime();
								if(employee.getInTime1().after(employee.getDepartment().getTuesInTIme())) {
//									long shortTime=time-employee.getTotalTime();
//									long diffSeconds	=shortTime/1000%60;
//									long diffMinutes   = shortTime / (60 * 1000)%60;
//									long diffHours  = shortTime / (60 * 60 * 1000)%24;
									Label yesLbl= new Label("Yes");
									employee.setLateMark(yesLbl.getText());
									yesLbl.setStyle("-fx-font-weight:bold;-fx-text-fill:red");
//									setText(diffHours+":"+diffMinutes+":"+diffSeconds);
									setGraphic(yesLbl);
									break;
								}else {
									setGraphic(null);
									setText(null);
									break;
								}
							case 4:
//								 time=employee.getDepartment().getWedOutTime().getTime()-employee.getDepartment().getWedInTime().getTime();
								 if(employee.getInTime1().after(employee.getDepartment().getWedInTime())) {
//									long shortTime=time-employee.getTotalTime();
//									long diffSeconds	=shortTime/1000%60;
//									long diffMinutes   = shortTime / (60 * 1000)%60;
//									long diffHours  = shortTime / (60 * 60 * 1000)%24;
									 Label yesLbl= new Label("Yes");
									 employee.setLateMark(yesLbl.getText());
										yesLbl.setStyle("-fx-font-weight:bold;-fx-text-fill:red");
//										setText(diffHours+":"+diffMinutes+":"+diffSeconds);
										setGraphic(yesLbl);
									break;
								}else {
									setGraphic(null);
									setText(null);
									break;
								}
							case 5:
//								 time=employee.getDepartment().getThursOutTime().getTime()-employee.getDepartment().getThursInTime().getTime();
								 if(employee.getInTime1().after(employee.getDepartment().getThursInTime())) {
//									long shortTime=time-employee.getTotalTime();
//									long diffSeconds	=shortTime/1000%60;
//									long diffMinutes   = shortTime / (60 * 1000)%60;
//									long diffHours  = shortTime / (60 * 60 * 1000)%24;
									 Label yesLbl= new Label("Yes");
									 employee.setLateMark(yesLbl.getText());
										yesLbl.setStyle("-fx-font-weight:bold;-fx-text-fill:red");
//										setText(diffHours+":"+diffMinutes+":"+diffSeconds);
										setGraphic(yesLbl);
									break;
								}else {
									setGraphic(null);
									setText(null);
									break;
								}
							case 6:
//								 time=employee.getDepartment().getFriOutTime().getTime()-employee.getDepartment().getFriInTime().getTime();
								 if(employee.getInTime1().after(employee.getDepartment().getFriInTime())) {
//									long shortTime=time-employee.getTotalTime();
//									long diffSeconds	=shortTime/1000%60;
//									long diffMinutes   = shortTime / (60 * 1000)%60;
//									long diffHours  = shortTime / (60 * 60 * 1000)%24;
									 Label yesLbl= new Label("Yes");
									 employee.setLateMark(yesLbl.getText());
										yesLbl.setStyle("-fx-font-weight:bold;-fx-text-fill:red");
//										setText(diffHours+":"+diffMinutes+":"+diffSeconds);
										setGraphic(yesLbl);
									break;
								}else {
									setGraphic(null);
									setText(null);
									break;
								}
							case 7:
//								 time=employee.getDepartment().getSatOutTime().getTime()-employee.getDepartment().getSatInTime().getTime();
//								 System.out.println(time+"total Time "+employee.getTotalTime());
//								  diffHours1=time/((60 * 60 * 1000)%24);
//								 System.out.println(diffHours1);
								 if(employee.getInTime1().after(employee.getDepartment().getSatInTime())) {
//									long shortTime=time-employee.getTotalTime();
//									long dif/fSeconds	=shortTime/1000%60;
//									long diffMinutes   = shortTime / (60 * 1000)%60;
//									long diff/Hours  = shortTime / (60 * 60 * 1000)%24;
									 Label yesLbl= new Label("Yes");
									 employee.setLateMark(yesLbl.getText());
										yesLbl.setStyle("-fx-font-weight:bold;-fx-text-fill:red");
//										setText(diffHours+":"+diffMinutes+":"+diffSeconds);
										setGraphic(yesLbl);
									break;
								}else {
									setGraphic(null);
									setText(null);
									break;
								}
							}
							
						}else {
							setText(null);
						}
						}
					
				}
			});
		//  statusCol.setCellValueFactory(e->new ReadOnlyObjectWrapper<>(e.getValue()));
		//  statusCol.setCellFactory(e->new TableCell<Employee,Employee>(){
		//
		//   @Override
//		      protected void updateItem(Employee employee,boolean empty){
//		    if(employee==null){
//		     setGraphic(null);
//		     setText(null);
//		     return;
//		    }else{
//		     boolean presentFlag=false;
//		     boolean absentFlag=false;
//		     String prsent="";
//		     String absent="";
//		     
		//    
//		     
////		     for(int i=1;i<employeeList.size();i++) {
////		      DateTime dt1= new DateTime(employeeList.get(i).getDateTime());
//////		      for(int j=0;j<employeeList.size();j++) {
//////		       if(!employeeList.get(j).equals(employeeList.get(i))) {
////		      DateTime dt2= new DateTime(employeeList.get(i-1).getDateTime());
////		      Date date1=new Date(employeeList.get(i).getDateTime().getTime());
////		      Date date2=new Date(employeeList.get(i-1).getDateTime().getTime());
//////		      System.out.println(date1);
////		      if(date1.compareTo(date2)==0) {
////		       setText("Present");
////		       i=i+1;
////		      }else {
////		       setText("absent");
////		      }
//////		      if(dt1.withTimeAtStartOfDay().isEqual(dt2.withTimeAtStartOfDay())) {
////////		       System.out.println("Hi");
//////		       presentFlag=true;
//////		       absentFlag=false;
//////		       break;
//////		      }else   {
////////		       setText("absent");
//////		       absentFlag=true;
//////		       presentFlag=false;
//////		       continue;
////////		       setGraphic(null);
//////		      }
//////		       }
		//////  
//////		      }
//////		      if(presentFlag) {
//////		       setText("present");
//////		      }else if(absentFlag) {
//////		       setText("absent");
//////		      }
//////		      
////		      
////		      
//		     }
//////		     
		//    
		//    
		//   }
//		    });
		//  
			employeeView.getColumns().addAll(deptCol,empNoCol,empCol,dateCol,dateTimeCol,statusCol,breakTimeCol,shortTimeCol,lateMarkCol);
		  employeeView.setItems(employeeData);
		  return employeeView;
		 
		 }
	
	
	public void prepareBarChart(ObservableList<Employee> employeeList) {
		
		Rectangle rectangle=new Rectangle(100, 60);
		rectangle.setArcWidth(6);
		rectangle.setArcHeight(6);
		rectangle.setFill(Color.RED);
		
		Gauge gauge=GaugeBuilder.create()
				.skinType(SkinType.SLIM)
				.barColor(Color.RED)
				.decimals(0)
				.maxValue(30)
				.unit("UNIT")
				.build();
		
		
		
				
	}
	 
}
