package hatzalahData;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import hatzalahBusiness.Branch;
import hatzalahBusiness.Member;

public class BranchData {
	public static ArrayList<Branch> getBranch(Connection dbConnection) throws SQLException {
		dbConnection.setAutoCommit(false);
		ArrayList<Branch> branches = new ArrayList<Branch>();
		try {
			Statement statement = dbConnection.createStatement();
			ResultSet rs = statement.executeQuery("select * from branch");

			while (rs.next()) {
				Branch branch = new Branch(rs.getInt("branch_id"), rs.getString("branch_name"), rs.getInt("year_est"));
				branches.add(branch);
			}
			rs.close();
			statement.close();

		} catch (SQLException sqlException) {
			System.out.println("SQLEXCEPTION" + "  " + sqlException.getMessage());
			throw sqlException;
		}
		return branches;

	}

	public static Integer getBranchId(Connection dbConnection, String branchName) throws SQLException {
		dbConnection.setAutoCommit(false);
		CallableStatement statement = dbConnection.prepareCall("select branch_id from branch where branch_name = ?");
		statement.setString(1, branchName);
		ResultSet result = statement.executeQuery();
		if (result.next()) {
			return result.getInt("branch_id");
		}
		return null;

	}

	public static String getBranchName(Connection dbConnection, int branchId) throws SQLException {
		String sql = "select branch_name" + " from Branch where branch_id = ?";
		PreparedStatement pStatement = dbConnection.prepareStatement(sql);
		// plug the data into the statement , replacing the placeholder
		pStatement.setInt(1, branchId);
		ResultSet rs = pStatement.executeQuery();
		String branchName = null;
		while (rs.next()) {
			branchName = rs.getString("branch_name");
		}
		rs.close();
		return branchName;
	}

	public static void addBranch(Connection db, String branchName, String yearEst, String initial) throws SQLException {
		db.setAutoCommit(false);
		try {
			CallableStatement statement = db.prepareCall("{call usp_addBranch(?,?,?)}");
			statement.setString(1, branchName);
			statement.setString(2, yearEst);
			statement.setString(3, initial);
			statement.execute();
			statement.getMoreResults();
			db.commit();
		} catch (SQLException e) {
			db.rollback();
			throw e;
		}
	}
}
