package hatzalahData;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

public class DonationIO {

	public static void addDonationData(String phoneNum, String amount, LocalDate date, String branch,
			Connection dbConnection) throws SQLException {
		try {
			dbConnection.setAutoCommit(false);
			CallableStatement cStatement;
			String sql = "{call usp_AddMember(?,?,?,?)}";
			cStatement = dbConnection.prepareCall(sql);
			cStatement.setString(1, phoneNum);
			cStatement.setDouble(2, Double.parseDouble(amount));
			cStatement.setDate(3, java.sql.Date.valueOf(date.toString()));
			cStatement.setString(4, branch);
			cStatement.executeQuery();
			dbConnection.commit();
		} catch (SQLException sqlE) {
			//throws an error converting nvarchar to date
			throw sqlE;
		}
	}
}
