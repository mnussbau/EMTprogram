package hatzalahData;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class JobIO {
	public static void AddJobData(Connection db, String jobName) throws SQLException {
		
		try {
			db.setAutoCommit(false);
			CallableStatement stmt;
			String sql = "{call usp_AddJob(?)}";
			stmt = db.prepareCall(sql);
			stmt.setString(1, jobName);	
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
}
