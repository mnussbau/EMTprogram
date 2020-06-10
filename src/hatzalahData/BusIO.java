package hatzalahData;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class BusIO {
	public static void AddBusData(Connection db, String VIN, LocalDate datePurchased, LocalDate lastMaintained,
			String purchasePrice, String branchName) throws SQLException {
		try {
			db.setAutoCommit(false);
			CallableStatement stmt;
			String sql = "{call usp_AddBus(?,?,?,?,?)}";
			stmt = db.prepareCall(sql);
			stmt.setString(1, VIN);
			stmt.setDate(2, java.sql.Date.valueOf(datePurchased));
			stmt.setDate(3, java.sql.Date.valueOf(lastMaintained));
			stmt.setBigDecimal(4, new BigDecimal(purchasePrice));
			stmt.setString(5, branchName);
			stmt.execute();
			stmt.getMoreResults();
			db.commit();

		} catch (SQLException ex) {
			db.rollback();
		} catch (DateTimeParseException ex1) {
			db.rollback();
			throw ex1;
		}
	}

	public static void UpdateBusData(Connection db, String vin, LocalDate lastMaintained) throws SQLException {
		try {
			db.setAutoCommit(false);
			CallableStatement stmt = db.prepareCall("{call usp_UpdateBus(?,?)}");
			stmt.setString(1, vin);
			stmt.setDate(2, java.sql.Date.valueOf(lastMaintained));// Maintained
			stmt.execute();
			stmt.getMoreResults();
			db.commit();
		} catch (SQLException e) {
			db.rollback();
			throw e;
		}
	}

	public static int getBusCount(int branchId, Connection dbconnection) throws SQLException {

		String sql = "select count(vin) as BusCount from bus where branch_id = ?";
		PreparedStatement pStatement;
		try {
			pStatement = dbconnection.prepareStatement(sql);

			pStatement.setInt(1, branchId);
			ResultSet rs = pStatement.executeQuery();
			int count = 0;
			while (rs.next()) {
				count = rs.getInt("BusCount");
			}
			return count;
		} catch (SQLException e) {
			dbconnection.rollback();
			throw e;
		}

	}

}
