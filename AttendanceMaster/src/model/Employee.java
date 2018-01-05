package model;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

/****
 * 
 * @author Saurabh Gupta
 *
 */
public class Employee {
	long id;
	long srNo;
	String employeeName;
	long employeeNo;
	Department department;
	Timestamp dateTime;
	String date;
	String deptName;
	String status;
	String inTime;
	String outTime;
	String shortTime;
	String lateMark;
	String presentTime;
	Timestamp time1;
	Timestamp time2;
	Timestamp time3;
	Timestamp time4;
	Timestamp time5;
	Timestamp time6;
	Timestamp time7;
	Timestamp time8;
	Timestamp time9;
	Timestamp time10;
	long totalTime;
	Time inTime1;
	Time outTime2;
	
	java.sql.Date sqlDate;
	public Employee() {
		// TODO Auto-generated constructor stub
	}
	
	public Employee(long id, String deptName, String employeeName, long employeeNo, long srNo,String date,Department department) {
		// TODO Auto-generated constructor stub
		this.id=id;
		this.employeeName=employeeName;
		this.deptName=deptName;
		this.employeeNo=employeeNo;
		this.srNo=srNo;
//		this.dateTime=dateTime;
		this.date=date;
		this.department=department;

	}
	public Employee(long id, String deptName, String employeeName, long employeeNo,java.sql.Date dateTime,String date,Department department,String status,String inTime,String outTime,long totalTime,Time inTime1,Timestamp time4) {
		// TODO Auto-generated constructor stub
		this.id=id;
		this.employeeName=employeeName;
		this.deptName=deptName;
		this.employeeNo=employeeNo;
//		this.srNo=srNo;
//		this.dateTime=dateTime;
		this.date=date;
		this.department=department;
		this.status=status;
		this.inTime=inTime;
		this.outTime=outTime;
		this.sqlDate=dateTime;
		this.totalTime=totalTime;
		this.inTime1=inTime1;
		this.time4=time4;
	}
	

	public Employee(String deptName2, String employeeName2, long empNo, java.sql.Date sqlDate,String date, String status) {
		// TODO Auto-generated constructor stub
		
		this.deptName=deptName2;
		this.employeeName=employeeName2;
		this.employeeNo=empNo;
		this.date=date;
		this.status=status;
		this.sqlDate=sqlDate;
	}
	
	
	public Employee(String outTime,String inTime) {
		this.inTime=inTime;
		this.outTime=outTime;
	}

	public Employee(long id, String deptName, String employeeName, long employeeNo, long srNo1, String date,
			Department department, String status, String inTime, String outTime, String breaktime) {
		// TODO Auto-generated constructor stub
		this.id=id;
		this.employeeName=employeeName;
		this.deptName=deptName;
		this.employeeNo=employeeNo;
//		this.srNo=srNo;
//		this.dateTime=dateTime;
		this.date=date;
		this.department=department;
		this.status=status;
		this.inTime=inTime;
		this.outTime=outTime;
//		this.sqlDate=dateTime;
		
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getSrNo() {
		return srNo;
	}
	public void setSrNo(long srNo) {
		this.srNo = srNo;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public long getEmployeeNo() {
		return employeeNo;
	}
	public void setEmployeeNo(long employeeNo) {
		this.employeeNo = employeeNo;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public Timestamp getDateTime() {
		return dateTime;
	}
	public void setDateTime(Timestamp dateTime) {
		this.dateTime = dateTime;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getInTime() {
		return inTime;
	}

	public void setInTime(String inTime) {
		this.inTime = inTime;
	}

	public String getOutTime() {
		return outTime;
	}

	public void setOutTime(String outTime) {
		this.outTime = outTime;
	}

	public java.sql.Date getSqlDate() {
		return sqlDate;
	}

	public void setSqlDate(java.sql.Date sqlDate) {
		this.sqlDate = sqlDate;
	}

	public Timestamp getTime1() {
		return time1;
	}

	public Timestamp getTime2() {
		return time2;
	}

	public Timestamp getTime3() {
		return time3;
	}

	public Timestamp getTime4() {
		return time4;
	}

	public Timestamp getTime5() {
		return time5;
	}

	public Timestamp getTime6() {
		return time6;
	}

	public Timestamp getTime7() {
		return time7;
	}

	public Timestamp getTime8() {
		return time8;
	}

	public Timestamp getTime9() {
		return time9;
	}

	public Timestamp getTime10() {
		return time10;
	}

	public void setTime1(Timestamp time1) {
		this.time1 = time1;
	}

	public void setTime2(Timestamp time2) {
		this.time2 = time2;
	}

	public void setTime3(Timestamp time3) {
		this.time3 = time3;
	}

	public void setTime4(Timestamp time4) {
		this.time4 = time4;
	}

	public void setTime5(Timestamp time5) {
		this.time5 = time5;
	}

	public void setTime6(Timestamp time6) {
		this.time6 = time6;
	}

	public void setTime7(Timestamp time7) {
		this.time7 = time7;
	}

	public void setTime8(Timestamp time8) {
		this.time8 = time8;
	}

	public void setTime9(Timestamp time9) {
		this.time9 = time9;
	}

	public void setTime10(Timestamp time10) {
		this.time10 = time10;
	}

	public long getTotalTime() {
		return totalTime;
	}

	public void setTotalTime(long totalTime) {
		this.totalTime = totalTime;
	}

	public Time getInTime1() {
		return inTime1;
	}

	public Time getOutTime2() {
		return outTime2;
	}

	public void setInTime1(Time inTime1) {
		this.inTime1 = inTime1;
	}

	public void setOutTime2(Time outTime2) {
		this.outTime2 = outTime2;
	}

	public String getShortTime() {
		return shortTime;
	}

	public String getLateMark() {
		return lateMark;
	}

	public String getPresentTime() {
		return presentTime;
	}

	public void setShortTime(String shortTime) {
		this.shortTime = shortTime;
	}

	public void setLateMark(String lateMark) {
		this.lateMark = lateMark;
	}

	public void setPresentTime(String presentTime) {
		this.presentTime = presentTime;
	}


}
