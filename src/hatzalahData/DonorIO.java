package hatzalahData;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class DonorIO {
	
	public static void addDonorData(String fname, String lname, String phoneNum,
			String street, String city, String state, String zip, Connection dbConnection) throws SQLException {
		try {
			dbConnection.setAutoCommit(false);
			CallableStatement cStatement;
			String sql = "{call usp_AddMember(?,?,?,?,?,?,?)}";
			cStatement = dbConnection.prepareCall(sql);
			cStatement.setString(1, fname);
			cStatement.setString(2, lname);
			cStatement.setString(3, phoneNum);
			cStatement.setString(4, street);
			cStatement.setString(5, city);
			cStatement.setString(6, state);
			cStatement.setString(7, zip);
			cStatement.executeQuery();
			dbConnection.commit();
		} catch (SQLException sqlE) {
			//not working : it throws an error; error converting data type nvarchar to date. 
			throw sqlE;
		}
	}

}
