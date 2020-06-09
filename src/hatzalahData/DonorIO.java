package hatzalahData;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DonorIO {
	
	public static void addDonorData(String fname, String lname, String phoneNum,
			String street, String city, String state, String zip, Connection dbConnection) throws SQLException {
		try {
			dbConnection.setAutoCommit(false);
			CallableStatement cStatement;
			String sql = "{call usp_AddDonor(?,?,?,?,?,?,?)}";
			cStatement = dbConnection.prepareCall(sql);
			cStatement.setString(1, fname);
			cStatement.setString(2, lname);
			cStatement.setString(3, phoneNum);
			cStatement.setString(4, street);
			cStatement.setString(5, city);
			cStatement.setString(6, state);
			cStatement.setString(7, zip);
			cStatement.execute();
			cStatement.getMoreResults();
			dbConnection.commit();
		} catch (SQLException sqlE) {
			dbConnection.rollback();
			throw sqlE;
		}
	}

}
