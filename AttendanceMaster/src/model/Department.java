package model;

import java.sql.Time;


/****
 * 
 * @author Saurabh Gupta
 *
 */
public class Department {
	long id;
	long srNo;
	String deptName;
	String inTime;
	String outTime;
	Time sunInTime;
	Time sunOutTime;
	Time monInTime;
	Time monOutTIme;
	Time tuesInTIme;
	Time tuesOutTime;
	Time wedInTime;
	Time wedOutTime;
	Time thursInTime;
	Time thursOutTime;
	Time friInTime;
	Time friOutTime;
	Time satOutTime;
	Time satInTime;
	 public enum DAYS{
		 SUNDAY,MONDAY,TUESDAY,WEDNESDAY,THURSDAY,FRIDAY,SATURDAY
	 }
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((deptName == null) ? 0 : deptName.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Department other = (Department) obj;
		if (deptName == null) {
			if (other.deptName != null)
				return false;
		} else if (!deptName.equals(other.deptName))
			return false;
		return true;
	}
	public long getSrNo() {
		return srNo;
	}
	public void setSrNo(long srNo) {
		this.srNo = srNo;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
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
	public Time getSunInTime() {
		return sunInTime;
	}
	public void setSunInTime(Time sunInTime) {
		this.sunInTime = sunInTime;
	}
	public Time getSunOutTime() {
		return sunOutTime;
	}
	public void setSunOutTime(Time sunOutTime) {
		this.sunOutTime = sunOutTime;
	}
	public Time getMonInTime() {
		return monInTime;
	}
	public void setMonInTime(Time monInTime) {
		this.monInTime = monInTime;
	}
	public Time getMonOutTIme() {
		return monOutTIme;
	}
	public void setMonOutTIme(Time monOutTIme) {
		this.monOutTIme = monOutTIme;
	}
	public Time getTuesInTIme() {
		return tuesInTIme;
	}
	public void setTuesInTIme(Time tuesInTIme) {
		this.tuesInTIme = tuesInTIme;
	}
	public Time getTuesOutTime() {
		return tuesOutTime;
	}
	public void setTuesOutTime(Time tuesOutTime) {
		this.tuesOutTime = tuesOutTime;
	}
	public Time getWedInTime() {
		return wedInTime;
	}
	public void setWedInTime(Time wedInTime) {
		this.wedInTime = wedInTime;
	}
	public Time getWedOutTime() {
		return wedOutTime;
	}
	public void setWedOutTime(Time wedOutTime) {
		this.wedOutTime = wedOutTime;
	}
	public Time getThursInTime() {
		return thursInTime;
	}
	public void setThursInTime(Time thursInTime) {
		this.thursInTime = thursInTime;
	}
	public Time getThursOutTime() {
		return thursOutTime;
	}
	public void setThursOutTime(Time thursOutTime) {
		this.thursOutTime = thursOutTime;
	}
	public Time getFriInTime() {
		return friInTime;
	}
	public void setFriInTime(Time friInTime) {
		this.friInTime = friInTime;
	}
	public Time getFriOutTime() {
		return friOutTime;
	}
	public void setFriOutTime(Time friOutTime) {
		this.friOutTime = friOutTime;
	}
	public Time getSatOutTime() {
		return satOutTime;
	}
	public void setSatOutTime(Time satOutTime) {
		this.satOutTime = satOutTime;
	}
	public Time getSatInTime() {
		return satInTime;
	}
	public void setSatInTime(Time satInTime) {
		this.satInTime = satInTime;
	}

	 
	 
}
