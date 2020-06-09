package hatzalahData;

import java.math.BigDecimal;
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
			String sql = "{call usp_addDonation(?,?,?,?)}";
			cStatement = dbConnection.prepareCall(sql);
			cStatement.setString(1, phoneNum);
			cStatement.setBigDecimal(2, new BigDecimal(amount));
			cStatement.setDate(3, java.sql.Date.valueOf(date));
			cStatement.setString(4, branch);
			cStatement.execute();
			cStatement.getMoreResults();
			dbConnection.commit();
		} catch (SQLException sqlE) {
			dbConnection.rollback();
			throw sqlE;
		}
	}
}
