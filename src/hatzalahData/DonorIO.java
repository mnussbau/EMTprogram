package hatzalahData;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import hatzalahBusiness.Donor;

public class DonorIO {

	public static void addDonorData(String fname, String lname, String phoneNum, String street, String city,
			String state, String zip, Connection dbConnection) throws SQLException {
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

	public static Donor getDonorData(Connection db, String phoneNum) throws SQLException {
		db.setAutoCommit(false);
		Donor donor = null;
		CallableStatement cs;
		String sql = "select * from Donor where donor_phone_num = ?";
		cs = db.prepareCall(sql);
		cs.setString(1, phoneNum);
		ResultSet set = cs.executeQuery();
		if (set.next()) {
			Integer id = set.getInt("donor_id");
			String fname = set.getString("donor_fname");
			String lname = set.getString("donor_lname");
			Integer addr = set.getInt("donor_addr_id");
			String phone = set.getString("donor_phone_num");
			donor = new Donor(id, fname, lname, addr, phone);
		}
		return donor;
	}

	public static void updateDonorData(Connection db, String identifyingPhoneNum, String lname, String newPhoneNum,
			String addrStrt, String addrCity, String addrState, String addrZip) throws SQLException {
		try {
			db.setAutoCommit(false);
			CallableStatement cs;
			String sql = "{call usp_UpdateDonor(?,?,?,?,?,?,?)}";
			cs = db.prepareCall(sql);
			cs.setString(1, identifyingPhoneNum);
			if (lname == null || lname.isBlank()) {
				cs.setNull(2, java.sql.Types.NULL);
			} else {
				cs.setString(2, lname);
			}
			if (newPhoneNum == null || newPhoneNum.isBlank()) {
				cs.setNull(3, java.sql.Types.NULL);
			} else {
				cs.setString(3, newPhoneNum);
			}
			if (addrStrt == null || addrStrt.isBlank()) {
				cs.setNull(4, java.sql.Types.NULL);
			} else {
				cs.setString(4, addrStrt);
			}
			if (addrCity == null || addrCity.isBlank()) {
				cs.setNull(5, java.sql.Types.NULL);
			} else {
				cs.setString(5, addrCity);
			}
			if (addrState == null || addrState.isBlank()) {
				cs.setNull(6, java.sql.Types.NULL);
			} else {
				cs.setString(6, addrState);
			}
			if (addrZip == null || addrZip.isBlank()) {
				cs.setNull(7, java.sql.Types.NULL);
			} else {
				cs.setString(7, addrZip);
			}
			cs.execute();
			cs.getMoreResults();
			db.commit();

		} catch (SQLException e) {
			db.rollback();
			throw e;
		}

	}
}
