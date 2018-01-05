package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;

import model.Department;
import util.DBUtil;

public class DepartmentDAO {
	
	/****
	 * 
	 * @author Saurabh Gupta
	 *
	 */
	public static List<Department> getDepartmentList(){
		String stmt="select id,departmentName from am_department";
		List<Department> departmentList= new ArrayList<>();
		ResultSet rs= null;
		try {
			rs=DBUtil.dbExecuteQuery(stmt);
			while(rs.next()) {
				Department department= new Department();
				department.setId(rs.getLong("id"));
				department.setDeptName(rs.getString("departmentName"));
				departmentList.add(department);
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) {
					rs.close();
				}
				DBUtil.dbDisconnect();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		return departmentList;
		
	}
	
	public boolean createDepartment(Department department) {
		boolean result=false;
		String stmt="insert into am_department(departmentName,sundayInTime,sundayOutTime,mondayinTime,mondayOutTime,tuesInTime,tuesOutTime"
				+ ",wedInTime,wedOutTime,thursInTime,thurOutTime,friInTime,friOutTime,satInTime,satOutTime) values('"+department.getDeptName()+"','"+
			department.getSunInTime()+"','"+department.getSunOutTime()+"','"+department.getMonInTime()+"','"+department.getMonOutTIme()+"','"+department.getTuesInTIme()+"','"+
			department.getTuesOutTime()+"','"+department.getWedInTime()+"','"+department.getWedOutTime()+"','"+department.getThursInTime()+"','"+department.getThursOutTime()+"','"+
			department.getFriInTime()+"','"+department.getFriOutTime()+"','"+department.getSatInTime()+"','"+department.getSatOutTime()+"')";
		try {
			DBUtil.dbExecuteUpdate(stmt);
			result=true;
		} catch (ClassNotFoundException | SQLException e) {
			result=false;
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				DBUtil.dbDisconnect();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		return result;
		
	}
	
	

}
