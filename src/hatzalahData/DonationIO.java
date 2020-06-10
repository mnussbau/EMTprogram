package hatzalahData;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

	public static double getDonationTotals(int branchId, Connection dbconnection) throws SQLException {
		String sql = "select isnull(sum(donation_amount), 0) as [DonationTotal] from Donation where branch_id = ?";
		PreparedStatement ps;
		try {
			ps = dbconnection.prepareStatement(sql);

			ps.setInt(1, branchId);
			ResultSet rs = ps.executeQuery();
			double totals = 0.0;
			while (rs.next()) {
				rs.getDouble("DonationTotal");

			}
			return totals;
		} catch (SQLException e) {
			throw e;
		}
	}

	public static int getDonationAmount(int branchId, Connection dbconnection) throws SQLException {
		try {
			String sql = "select count(donation_id) as DonationCount from Donation where branch_id = ?";
			PreparedStatement ps;

			ps = dbconnection.prepareStatement(sql);

			ps.setInt(1, branchId);
			ResultSet rs = ps.executeQuery();
			int count = 0;
			while (rs.next()) {
				count = rs.getInt("DonationCount");
			}
			return count;
		} catch (SQLException e) {
			throw e;
		}
	}
}
