package hatzalahData;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import hatzalahBusiness.Branch;
import hatzalahBusiness.Credential;

public class CredentialIO {
public static void AddCredentialData(Connection db, String credential) throws SQLException {
		
		try {
			db.setAutoCommit(false);
			CallableStatement stmt;
			String sql = "{call usp_AddCredential(?)}";
			stmt = db.prepareCall(sql);
			stmt.setString(1, credential);	
			stmt.execute();
			stmt.getMoreResults();
			db.commit();
			return;
		}catch(SQLException ex) {
			db.rollback();
			throw ex;
		}
}
	public static ArrayList<Credential> getCredentials(Connection dbConnection)throws SQLException{
		ArrayList<Credential> credentials = new ArrayList<Credential>();
		try {
//			String url = "jdbc:sqlserver://localhost:1433;instance=SQLEXPRESS01;databaseName=Hatzolah;integratedSecurity=true";
//			Connection dbConnection = DriverManager.getConnection(url);
//			dbconnection.getcon
			Statement statement = dbConnection.createStatement();
			ResultSet rs = statement.executeQuery("select * from job_credential");
			
			while (rs.next()) {
				Credential credential = new Credential(rs.getInt("cred_id"), rs.getString("cred_desc"));
				credentials.add(credential);
			}
			rs.close();
			statement.close();
			
			
			}
		catch (SQLException sqlException) {
			throw sqlException;
		}
		
		return credentials;
	}
	public static String getCredentialName(Connection dbconnection, int credential_id) throws SQLException {
		String sql = "select cred_desc" + " from Job_Credential where cred_id = ?";
		PreparedStatement pStatement = dbconnection.prepareStatement(sql);
		// plug the data into the statement , replacing the placeholder
		pStatement.setInt(1, credential_id);
		ResultSet rs = pStatement.executeQuery();
		String credentialName = null;
		while (rs.next()) {
			credentialName = rs.getString("cred_desc");
		}
		rs.close();
		return credentialName;
	}
	

}
