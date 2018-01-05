package model;

import java.util.Comparator;

/****
 * 
 * @author Saurabh Gupta
 *
 */

public class DateComparator implements Comparator<Employee> {

	@Override
	public int compare(Employee o1, Employee o2) {
		// TODO Auto-generated method stub
//		System.out.println(o1.getSqlDate());
		return o1.getSqlDate().compareTo(o2.getSqlDate());
	}
	

}
