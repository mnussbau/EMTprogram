package hatzalahData;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import java.util.*;

public class CallIO {

	public static void addCall(Connection db, String branchName, LocalDate callReceived, String fname, String lname,
			int age, String addrStreet, String addrCity, String addrState, String zip, char transfered, String vin,
			String notes, List<String> symptoms, List<String> members, List<String> equipment) throws SQLException {
		int callId;
		db.setAutoCommit(false);
		try {

			String sql = "{call usp_AddCall(?,?,?,?,?,?,?,?,?,?,?,?)}";
			CallableStatement cs = db.prepareCall(sql);
			cs.setString(1, branchName);
			cs.setString(2, addrState);
			cs.setString(3, addrCity);
			cs.setString(4, addrState);
			cs.setString(5, zip);
			cs.setString(6, fname);
			cs.setString(7, lname);
			cs.setInt(8, age);
			cs.setString(9, transfered + "");
			if( (vin == null || vin.isBlank()) && transfered == 'N') {
				cs.setNull(10, Types.NULL);
			}else if((vin == null || vin.isBlank()) && transfered == 'Y') {
				throw new RuntimeException("Vin cannot be blank if the patient was transfered.");
			}else {
				cs.setString(10, vin);
			}
			cs.setDate(11, java.sql.Date.valueOf(callReceived));
			if (notes == null || notes.isBlank()) {
				cs.setNull(12, Types.NULL);
			} else {
				cs.setString(12, notes);
			}
			ResultSet rs = cs.executeQuery();

			if (rs.next()) {
				callId = rs.getInt("newCallId");
			} else {
				throw new RuntimeException("Could not get the new call.");
			}

			for (String s : symptoms) {
				addSymptomToCall(db, callId, s);
			}

			for (String m : members) {
				addMemberToCall(db, callId, m);
			}

			for (String e : equipment) {
				addEquipmentToCall(db, callId, e);
			}

			db.commit();
		} catch (SQLException e) {
			db.rollback();
			throw e;
		}

	}

	private static void addSymptomToCall(Connection db, int callId, String symptom) throws SQLException {
		db.setAutoCommit(false);
		
		String sql = "{call usp_AddSymptomsToCall(?,?)}";
		CallableStatement cs = db.prepareCall(sql);
		cs.setInt(1, callId);
		cs.setString(2, symptom);
		cs.execute();
		cs.getMoreResults();


	}

	private static void addMemberToCall(Connection db, int callID, String memberId) throws SQLException {
		db.setAutoCommit(false);
		String sql = "{call usp_AddMemberToCall(?,?)}";
		CallableStatement cs = db.prepareCall(sql);
		cs.setInt(1, callID);
		cs.setString(2, memberId);
		cs.execute();
		cs.getMoreResults();


	}

	private static void addEquipmentToCall(Connection db, int callId, String equipment) throws SQLException {
		String sql = "{call usp_AddEquipmentToCall(?,?)}";
		CallableStatement cs = db.prepareCall(sql);
		cs.setInt(1, callId);
		cs.setString(2, equipment);
		cs.execute();
		cs.getMoreResults();

	}

}
