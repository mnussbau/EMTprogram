package hatzalahData;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import hatzalahBusiness.Branch;

public class BranchData {
		public static ArrayList<Branch> getBranch()throws SQLException{
			ArrayList<Branch> branches = new ArrayList<Branch>();
			try {
				String url = "jdbc:sqlserver://localhost:1433;instance=SQLEXPRESS01;databaseName=Hatzolah;integratedSecurity=true";
				Connection dbConnection = DriverManager.getConnection(url);
				Statement statement = dbConnection.createStatement();
				ResultSet rs = statement.executeQuery("select * from branch");
				System.out.println(rs.findColumn("branch_name"));
				System.out.println("executed the statement");
				
				while (rs.next()) {
					Branch branch = new Branch(rs.getInt("branch_id"), rs.getString("branch_name"), rs.getInt("year_est"));
					branches.add(branch);
				}
				rs.close();
				statement.close();
				dbConnection.close();
				
				}
			catch (SQLException sqlException) {
				System.out.println("SQLEXCEPTION" + "  " + sqlException.getMessage());
				throw sqlException;
			}
		return branches;

	}


}
