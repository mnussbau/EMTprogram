package hatzalahData;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

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
}
