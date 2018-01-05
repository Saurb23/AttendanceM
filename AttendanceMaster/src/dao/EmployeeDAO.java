package dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.transformation.SortedList;
import model.CurrentMonthYear;
import model.Department;
import model.Employee;
import util.DBUtil;

/****
 * 
 * @author Saurabh Gupta
 *
 */
public class EmployeeDAO {
	
	public List<Employee> showEmployees(){
		List<Employee> employeeList= new ArrayList<>();
		String stmt="select e.id,departmentId,departmentName,employeeNo,employeeName,dateTime,time1,time2,time3,time4,time5,time6,time7,time8,time9,time10 from am_employee e inner join "
				+ "am_department d on d.id=e.departmentId";
		ResultSet rs=null;
		try {
			rs=DBUtil.dbExecuteQuery(stmt);
			while(rs.next()) {
				Employee employee= new Employee();
				employee.setId(rs.getLong("id"));
				employee.setEmployeeNo(rs.getLong("employeeNo"));
				employee.setEmployeeName(rs.getString("employeeName"));
				employee.setDepartment(new Department());
				employee.getDepartment().setId(rs.getLong("departmentId"));
				employee.getDepartment().setDeptName(rs.getString("departmentName"));
				employee.setDateTime(rs.getTimestamp("dateTime"));
				
				employeeList.add(employee);
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return employeeList;
	}
	
	public List<Employee> getEmployeeList(){
		List<Employee> employeList= new ArrayList<>();
		String stmt="select distinct employeeNo,employeeName from am_employee";
		ResultSet rs=null;
		try {
			rs=DBUtil.dbExecuteQuery(stmt);
			while(rs.next()) {
				Employee employee= new Employee();
				employee.setEmployeeName(rs.getString("employeeName"));
				employee.setEmployeeNo(rs.getLong("employeeNo"));
				employeList.add(employee);
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return employeList;
		
		
	}
	
	public List<Employee> getEmployeeData(String employeeName,String monthName,String yearName){
		List<Employee> employeeList= new ArrayList<>();
		
		String stmt="select e.id,departmentId,departmentName,sundayInTime,sundayOutTime,mondayinTime,mondayOutTime,tuesInTime,tuesOutTime" + 
				",wedInTime,wedOutTime,thursInTime,thurOutTime,friInTime,friOutTime,satInTime,satOutTime,employeeNo,employeeName,dateTime,time1,time2,time3,time4,time5,time6,time7,time8,time9,time10 from am_employee e inner join "
				+ "am_department d on d.id=e.departmentId where employeeName='"+employeeName+"' and month(dateTime)='"+monthName+"'"
						+ " and year(dateTime)='"+yearName+"'";
		ResultSet rs=null;
		SimpleDateFormat format=new SimpleDateFormat("dd-MMM-yyyy");
		try {
			rs=DBUtil.dbExecuteQuery(stmt);
			while(rs.next()) {
				Employee employee= new Employee();
				employee.setId(rs.getLong("id"));
				employee.setEmployeeNo(rs.getLong("employeeNo"));
				employee.setEmployeeName(rs.getString("employeeName"));
				employee.setDepartment(new Department());
				employee.getDepartment().setId(rs.getLong("departmentId"));
				employee.getDepartment().setDeptName(rs.getString("departmentName"));
				employee.getDepartment().setSunInTime(rs.getTime("sundayInTime"));
				employee.getDepartment().setSunOutTime(rs.getTime("sundayOutTime"));
				employee.getDepartment().setMonInTime(rs.getTime("mondayInTime"));
				employee.getDepartment().setMonOutTIme(rs.getTime("mondayOutTime"));
				employee.getDepartment().setTuesInTIme(rs.getTime("tuesInTime"));
				employee.getDepartment().setTuesOutTime(rs.getTime("tuesOutTime"));
				employee.getDepartment().setWedInTime(rs.getTime("wedInTime"));
				employee.getDepartment().setWedOutTime(rs.getTime("wedOutTime"));
				employee.getDepartment().setThursInTime(rs.getTime("thursInTime"));
				employee.getDepartment().setThursOutTime(rs.getTime("thurOutTime"));
				employee.getDepartment().setFriInTime(rs.getTime("friInTime"));
				employee.getDepartment().setFriOutTime(rs.getTime("friOutTime"));
				employee.getDepartment().setSatInTime(rs.getTime("satInTime"));
				employee.getDepartment().setSatOutTime(rs.getTime("satOutTime"));
				employee.setDateTime(rs.getTimestamp("dateTime"));
				employee.setTime1(java.sql.Timestamp.valueOf(rs.getString("time1")));
			     
			      employee.setTime2(java.sql.Timestamp.valueOf(rs.getString("time2").toString().replaceAll("null","1917-10-10 00:00:00").toString()));
			     // System.out.println(rs.getString("time3"+(rs.getString("time3").replace("null","0000-00-00 00:00:00.0"))));
			  
			      employee.setTime3(java.sql.Timestamp.valueOf(rs.getString("time3").toString().replaceAll("null","1917-10-10 00:00:00").toString()));
			     // System.out.println(rs.getString("time4"));
			      employee.setTime4(java.sql.Timestamp.valueOf(rs.getString("time4").replace("null", "1917-10-10 00:00:00").toString()));
			    //  System.out.println(rs.getString("time5"));
			      employee.setTime5(java.sql.Timestamp.valueOf(rs.getString("time5").replace("null", "1917-10-10 00:00:00").toString()));
			    //  System.out.println(rs.getString("time6"));
			      employee.setTime6(java.sql.Timestamp.valueOf(rs.getString("time6").replace("null", "1917-10-10 00:00:00").toString()));
			      employee.setTime7(java.sql.Timestamp.valueOf(rs.getString("time7").replace("null", "1917-10-10 00:00:00").toString()));
			      employee.setTime8(java.sql.Timestamp.valueOf(rs.getString("time8").replace("null", "1917-10-10 00:00:00").toString()));
			      employee.setTime9(java.sql.Timestamp.valueOf(rs.getString("time9").replace("null", "1917-10-10 00:00:00").toString()));
			      employee.setTime10(java.sql.Timestamp.valueOf(rs.getString("time10").replace("null", "1917-10-10 00:00:00").toString()));
				
				employee.setDate(String.valueOf(new Date(employee.getDateTime().getTime())));
				employeeList.add(employee);
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return employeeList;
	}
	
	public List<CurrentMonthYear> getYears(){
		String stmt="select month,year from yeartable";
		List<CurrentMonthYear> yearList= new ArrayList<>();
		ResultSet rs=null;
		try {
			rs=DBUtil.dbExecuteQuery(stmt);
			while(rs.next()) {
				CurrentMonthYear cm= new CurrentMonthYear();
				cm.setMonth(rs.getInt("month"));
				cm.setYear(rs.getInt("year"));
//				String year=rs.getString("year");
				
				yearList.add(cm);
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return yearList;
		
	}
	
	public List<Employee> getExistingEmployees(){
		List<Employee> employeeList= new ArrayList<>();
		String stmt="select departmentId,departmentName,employeeNo,employeeName,dateTime from am_employee e inner join "
				+ "am_department d on d.id=e.departmentId";
		ResultSet rs=null;
		try {
			rs=DBUtil.dbExecuteQuery(stmt);
			while(rs.next()) {
				Employee employee= new Employee();
//				employee.setId(rs.getLong("id"));
				employee.setEmployeeNo(rs.getLong("employeeNo"));
				employee.setEmployeeName(rs.getString("employeeName"));
				employee.setDepartment(new Department());
				employee.getDepartment().setId(rs.getLong("departmentId"));
				employee.getDepartment().setDeptName(rs.getString("departmentName"));
				employee.setDateTime(rs.getTimestamp("dateTime"));
				employeeList.add(employee);
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return employeeList;
	}
	
	
	public List<Employee> viewBreakTime(Employee employee){
		List<Employee> breakList=new ArrayList<>();
		SimpleDateFormat sf= new SimpleDateFormat("yyyy-MM-dd");
		
//		Date date=null;
//		try {
//			
//		} catch (ParseException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		System.out.println(ld.toString());
//			date = new Timestamp(employee.getDateTime().getTime());
		
		String stmt="select dateTime,time1,time2,time3,time4,time5,time6,time7,time8,time9,time10 from am_employee where employeeNo="+employee.getEmployeeNo()+" and date(dateTime)='"+employee.getSqlDate()+"' order by dateTime asc";
		ResultSet rs=null;
		try {
			rs=DBUtil.dbExecuteQuery(stmt);
			while(rs.next()) {
				Employee employee2= new Employee();
				employee2.setDateTime(rs.getTimestamp("dateTime"));
				
				employee2.setDate(employee.getSqlDate().toString());
				employee2.setTime1(java.sql.Timestamp.valueOf(rs.getString("time1")));
			     
				employee2.setTime2(java.sql.Timestamp.valueOf(rs.getString("time2").toString().replaceAll("null","1917-10-10 00:00:00").toString()));
			     // System.out.println(rs.getString("time3"+(rs.getString("time3").replace("null","0000-00-00 00:00:00.0"))));
			  
				employee2.setTime3(java.sql.Timestamp.valueOf(rs.getString("time3").toString().replaceAll("null","1917-10-10 00:00:00").toString()));
			      System.out.println(rs.getString("time4"));
				employee2.setTime4(java.sql.Timestamp.valueOf(rs.getString("time4").replace("null", "1917-10-10 00:00:00").toString()));
			    //  System.out.println(rs.getString("time5"));
				employee2.setTime5(java.sql.Timestamp.valueOf(rs.getString("time5").replace("null", "1917-10-10 00:00:00").toString()));
			    //  System.out.println(rs.getString("time6"));
				employee2.setTime6(java.sql.Timestamp.valueOf(rs.getString("time6").replace("null", "1917-10-10 00:00:00").toString()));
				employee2.setTime7(java.sql.Timestamp.valueOf(rs.getString("time7").replace("null", "1917-10-10 00:00:00").toString()));
				employee2.setTime8(java.sql.Timestamp.valueOf(rs.getString("time8").replace("null", "1917-10-10 00:00:00").toString()));
				employee2.setTime9(java.sql.Timestamp.valueOf(rs.getString("time9").replace("null", "1917-10-10 00:00:00").toString()));
				employee2.setTime10(java.sql.Timestamp.valueOf(rs.getString("time10").replace("null", "1917-10-10 00:00:00").toString()));
//				System.out.println(employee2.getDateTime());
				breakList.add(employee2);
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return breakList;
	}
	
	public List<Employee> getDateData(String deptName,String datewise){
	    List<Employee> employeeList= new ArrayList<>();
	    String stmt="select e.id,departmentId,departmentName,sundayInTime,sundayOutTime,mondayinTime,mondayOutTime,tuesInTime,tuesOutTime" + 
	    		",wedInTime,wedOutTime,thursInTime,thurOutTime,friInTime,friOutTime,satInTime,satOutTime,employeeNo,employeeName,dateTime,time1,time2,time3,time4,time5,time6,time7,time8,time9,time10 from am_employee e inner join "
	      + "am_department d on d.id=e.departmentId where departmentName='"+deptName+"' and date(dateTime)='"+ datewise+"'  order by employeeNo";
	    ResultSet rs=null;
	    try {
	     rs=DBUtil.dbExecuteQuery(stmt);
	     while(rs.next()) {
	      Employee employee= new Employee();
	      employee.setId(rs.getLong("id"));
	      employee.setEmployeeNo(rs.getLong("employeeNo"));
	      employee.setEmployeeName(rs.getString("employeeName"));
	      employee.setDepartment(new Department());
	      employee.getDepartment().setId(rs.getLong("departmentId"));
	      employee.getDepartment().setDeptName(rs.getString("departmentName"));
	      employee.getDepartment().setSunInTime(rs.getTime("sundayInTime"));
			employee.getDepartment().setSunOutTime(rs.getTime("sundayOutTime"));
			employee.getDepartment().setMonInTime(rs.getTime("mondayInTime"));
			employee.getDepartment().setMonOutTIme(rs.getTime("mondayOutTime"));
			employee.getDepartment().setTuesInTIme(rs.getTime("tuesInTime"));
			employee.getDepartment().setTuesOutTime(rs.getTime("tuesOutTime"));
			employee.getDepartment().setWedInTime(rs.getTime("wedInTime"));
			employee.getDepartment().setWedOutTime(rs.getTime("wedOutTime"));
			employee.getDepartment().setThursInTime(rs.getTime("thursInTime"));
			employee.getDepartment().setThursOutTime(rs.getTime("thurOutTime"));
			employee.getDepartment().setFriInTime(rs.getTime("friInTime"));
			employee.getDepartment().setFriOutTime(rs.getTime("friOutTime"));
			employee.getDepartment().setSatInTime(rs.getTime("satInTime"));
			employee.getDepartment().setSatOutTime(rs.getTime("satOutTime"));
	      employee.setDateTime(rs.getTimestamp("dateTime"));
	      
	      employee.setTime1(java.sql.Timestamp.valueOf(rs.getString("time1")));
	     
	      employee.setTime2(java.sql.Timestamp.valueOf(rs.getString("time2").toString().replaceAll("null","1917-10-10 00:00:00").toString()));
	     // System.out.println(rs.getString("time3"+(rs.getString("time3").replace("null","0000-00-00 00:00:00.0"))));
	  
	      employee.setTime3(java.sql.Timestamp.valueOf(rs.getString("time3").toString().replaceAll("null","1917-10-10 00:00:00").toString()));
	     // System.out.println(rs.getString("time4"));
	      employee.setTime4(java.sql.Timestamp.valueOf(rs.getString("time4").replace("null", "1917-10-10 00:00:00").toString()));
	    //  System.out.println(rs.getString("time5"));
	      employee.setTime5(java.sql.Timestamp.valueOf(rs.getString("time5").replace("null", "1917-10-10 00:00:00").toString()));
	    //  System.out.println(rs.getString("time6"));
	      employee.setTime6(java.sql.Timestamp.valueOf(rs.getString("time6").replace("null", "1917-10-10 00:00:00").toString()));
	      employee.setTime7(java.sql.Timestamp.valueOf(rs.getString("time7").replace("null", "1917-10-10 00:00:00").toString()));
	      employee.setTime8(java.sql.Timestamp.valueOf(rs.getString("time8").replace("null", "1917-10-10 00:00:00").toString()));
	      employee.setTime9(java.sql.Timestamp.valueOf(rs.getString("time9").replace("null", "1917-10-10 00:00:00").toString()));
	      employee.setTime10(java.sql.Timestamp.valueOf(rs.getString("time10").replace("null", "1917-10-10 00:00:00").toString()));
	      employeeList.add(employee);
	     }
	    } catch (ClassNotFoundException | NullPointerException |SQLException e) {
	     // TODO Auto-generated catch block
	     e.printStackTrace();
	    }
	    
	    return employeeList;
	   }
	
	 public List<Employee> getEmployeeByDept(String deptName){
		List<Employee> employeList= new ArrayList<>();
		String stmt="select distinct employeeNo,employeeName,departmentName from am_employee e inner join am_department d on d.id=e.departmentId"
				+ " where departmentName='"+deptName+"'";
		ResultSet rs=null;
		try {
			rs=DBUtil.dbExecuteQuery(stmt);
			while(rs.next()) {
				Employee employee= new Employee();
				employee.setEmployeeName(rs.getString("employeeName"));
				employee.setEmployeeNo(rs.getLong("employeeNo"));
				employeList.add(employee);
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return employeList;
		
		
	}
	 
	 public List<Employee> showEmployeesByMonth(String monthName,String yearName){
			List<Employee> employeeList= new ArrayList<>();
			
			String stmt="select e.id,departmentId,departmentName,sundayInTime,sundayOutTime,mondayinTime,mondayOutTime,tuesInTime,tuesOutTime" + 
					",wedInTime,wedOutTime,thursInTime,thurOutTime,friInTime,friOutTime,satInTime,satOutTime,employeeNo,employeeName,dateTime,time1,time2,time3,time4,time5,time6,time7,time8,time9,time10 from am_employee e inner join "
					+ "am_department d on d.id=e.departmentId where month(dateTime)='"+monthName+"'"
							+ " and year(dateTime)='"+yearName+"'";
			ResultSet rs=null;
			SimpleDateFormat format=new SimpleDateFormat("dd-MMM-yyyy");
			try {
				rs=DBUtil.dbExecuteQuery(stmt);
				while(rs.next()) {
					Employee employee= new Employee();
					employee.setId(rs.getLong("id"));
					employee.setEmployeeNo(rs.getLong("employeeNo"));
					employee.setEmployeeName(rs.getString("employeeName"));
					employee.setDepartment(new Department());
					employee.getDepartment().setId(rs.getLong("departmentId"));
					employee.getDepartment().setDeptName(rs.getString("departmentName"));
					employee.getDepartment().setSunInTime(rs.getTime("sundayInTime"));
					employee.getDepartment().setSunOutTime(rs.getTime("sundayOutTime"));
					employee.getDepartment().setMonInTime(rs.getTime("mondayInTime"));
					employee.getDepartment().setMonOutTIme(rs.getTime("mondayOutTime"));
					employee.getDepartment().setTuesInTIme(rs.getTime("tuesInTime"));
					employee.getDepartment().setTuesOutTime(rs.getTime("tuesOutTime"));
					employee.getDepartment().setWedInTime(rs.getTime("wedInTime"));
					employee.getDepartment().setWedOutTime(rs.getTime("wedOutTime"));
					employee.getDepartment().setThursInTime(rs.getTime("thursInTime"));
					employee.getDepartment().setThursOutTime(rs.getTime("thurOutTime"));
					employee.getDepartment().setFriInTime(rs.getTime("friInTime"));
					employee.getDepartment().setFriOutTime(rs.getTime("friOutTime"));
					employee.getDepartment().setSatInTime(rs.getTime("satInTime"));
					employee.getDepartment().setSatOutTime(rs.getTime("satOutTime"));
					employee.setDateTime(rs.getTimestamp("dateTime"));
					employee.setTime1(java.sql.Timestamp.valueOf(rs.getString("time1")));
				     
				      employee.setTime2(java.sql.Timestamp.valueOf(rs.getString("time2").toString().replaceAll("null","1917-10-10 00:00:00").toString()));
				     // System.out.println(rs.getString("time3"+(rs.getString("time3").replace("null","0000-00-00 00:00:00.0"))));
				  
				      employee.setTime3(java.sql.Timestamp.valueOf(rs.getString("time3").toString().replaceAll("null","1917-10-10 00:00:00").toString()));
				     // System.out.println(rs.getString("time4"));
				      employee.setTime4(java.sql.Timestamp.valueOf(rs.getString("time4").replace("null", "1917-10-10 00:00:00").toString()));
				    //  System.out.println(rs.getString("time5"));
				      employee.setTime5(java.sql.Timestamp.valueOf(rs.getString("time5").replace("null", "1917-10-10 00:00:00").toString()));
				    //  System.out.println(rs.getString("time6"));
				      employee.setTime6(java.sql.Timestamp.valueOf(rs.getString("time6").replace("null", "1917-10-10 00:00:00").toString()));
				      employee.setTime7(java.sql.Timestamp.valueOf(rs.getString("time7").replace("null", "1917-10-10 00:00:00").toString()));
				      employee.setTime8(java.sql.Timestamp.valueOf(rs.getString("time8").replace("null", "1917-10-10 00:00:00").toString()));
				      employee.setTime9(java.sql.Timestamp.valueOf(rs.getString("time9").replace("null", "1917-10-10 00:00:00").toString()));
				      employee.setTime10(java.sql.Timestamp.valueOf(rs.getString("time10").replace("null", "1917-10-10 00:00:00").toString()));
					
					employee.setDate(String.valueOf(new Date(employee.getDateTime().getTime())));
					employeeList.add(employee);
				}
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return employeeList;
		}
		
	 
	 public CurrentMonthYear getCurrentMonthYear() {
		 String stmt="select month,year from yeartable order by id desc limit 1";
		 CurrentMonthYear currentMonthYear= null;
		 ResultSet rs=null;
		 try {
			rs=DBUtil.dbExecuteQuery(stmt);
			 while(rs.next()) {
				 currentMonthYear=new CurrentMonthYear();
				 currentMonthYear.setMonth(rs.getInt("month"));
				 currentMonthYear.setYear(rs.getInt("year"));
			 }
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 return	currentMonthYear;
	 }

}
