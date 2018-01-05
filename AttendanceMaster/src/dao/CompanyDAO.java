package dao;

import java.sql.SQLException;

import model.Company;
import util.DBUtil;

/****
 * 
 * @author Saurabh Gupta
 *
 */
public class CompanyDAO {
	
	public boolean createCompany(Company company) {
		String stmt="insert into company(companyName) values('"+company.getCompanyName()+"')";
		boolean result=false;
		try {
			DBUtil.dbExecuteUpdate(stmt);
			result=true;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			result=false;
			e.printStackTrace();
		}
		finally {
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
