package dao;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import model.CurrentMonthYear;
import model.Department;
import model.Employee;
import util.DBUtil;

/****
 * 
 * @author Saurabh Gupta
 *
 */
public class UploadDAO {
	
	public boolean uploadData(Department department,List<Employee> emplList,List<CurrentMonthYear> yearList) {
		boolean result=false;
		long deptId=0;
		List<String> sqlStmt= new ArrayList<>();
//		if(department!=null) {
//			String stmt="insert into am_department(departmentName) values('"+department.getDeptName()+"')";
//			try {
//				deptId=DBUtil.dbExecuteUpdateandReturn(stmt);
//			} catch (ClassNotFoundException | SQLException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//		}
//			SimpleDateFormat format=new SimpleDateFormat("dd-MMM-yy HH:mm:ss");
		
		sqlStmt.clear();
		for(Employee e:emplList) {
//			Timestamp dateTime=new Timestamp(e.getDateTime().getTime());
			
//			System.out.println(deptId);
			
			String stmt1="insert into am_employee(departmentId,employeeNo,employeeName,dateTime,time1,time2,time3,time4,time5,time6,time7,time8,time9,time10) values("+department.getId()+","+e.getEmployeeNo()+",'"+e.getEmployeeName()+"','"+e.getDate()+"','"
					+e.getTime1()+"','"+e.getTime2()+"','"+e.getTime3()+"','"+e.getTime4()+"','"+e.getTime5()+"','"+e.getTime6()+"','"+e.getTime7()+"','"+e.getTime8()+"','"+e.getTime9()+"','"+e.getTime10()+"')";
			sqlStmt.add(stmt1);
		}
		for(CurrentMonthYear cy:yearList) {
		String stmt2="insert into yeartable(month,year) value("+cy.getMonth()+","+cy.getYear()+")";
		sqlStmt.add(stmt2);
		
		}
		try {
			DBUtil.executeBatch(sqlStmt);
			result=true;
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			result=false;
			e1.printStackTrace();
		}
		
		return result;
		
		
		
	}

}
