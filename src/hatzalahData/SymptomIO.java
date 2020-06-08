package hatzalahData;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;


public class SymptomIO {
	
	public static void addSymptom(Connection db, String sympName) throws SQLException {
		
		try {
			db.setAutoCommit(false);
			CallableStatement stmt;
			String sql = "{call AddSymptom(?)}";
			stmt = db.prepareCall(sql);
			stmt.setString(1, sympName);	
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
