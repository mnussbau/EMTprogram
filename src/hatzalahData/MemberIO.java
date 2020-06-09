package hatzalahData;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import hatzalahBusiness.*;

public class MemberIO {

	public static String addMemberData(String fname, String lname, String phoneNum, LocalDate bday,
			Character maritalStatus, String branchName, LocalDate dateJoined, String credentialName, String jobName,
			Character status, String street, String city, String state, String zip, Connection dbConnection) throws SQLException {
		String memberId = "";
		try {
			dbConnection.setAutoCommit(false);
			CallableStatement cStatement;
			String sql = "{call usp_AddMember(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
			cStatement = dbConnection.prepareCall(sql);
			cStatement.setString(1, fname);
			cStatement.setString(2, lname);
			cStatement.setDate(3, java.sql.Date.valueOf(dateJoined.toString()));
			cStatement.setDate(4, java.sql.Date.valueOf(bday.toString()));
			cStatement.setString(5, "" + maritalStatus);
			cStatement.setString(6, branchName);
			cStatement.setString(7, street);
			cStatement.setString(8, city);
			cStatement.setString(9, state);
			cStatement.setString(10, zip);
			cStatement.setString(11, credentialName);
			cStatement.setString(12, jobName);
			cStatement.setString(13,  phoneNum);
			cStatement.setString(14,  "" + status);
			ResultSet rs = cStatement.executeQuery();

			dbConnection.commit();

			if (rs.next()) {
				memberId = rs.getString("memberid");
			}

		} catch (SQLException sqlE) {
			throw sqlE;
		}
		return memberId;



	public static List<Member> getMembers(Connection db) throws SQLException{
		db.setAutoCommit(false);
		Statement statement = db.createStatement();
		ResultSet rs = statement.executeQuery("select * from member");
		List<Member>  members = new ArrayList<>();
		while (rs.next()) {
			Member member = new Member(rs.getString("member_id"),rs.getString("fname"),rs.getString("lname"),
				rs.getDate("date_joined").toLocalDate(),rs.getDate("bday").toLocalDate(),
				rs.getString("Marital_status").charAt(0), rs.getInt("branch_id"), rs.getInt("Address_id"),
				rs.getInt("credential_id"),rs.getInt("job_id"),rs.getString("member_phone_num"),rs.getString("active_status").charAt(0));
			members.add(member);
		}
		return members;

	}

}
