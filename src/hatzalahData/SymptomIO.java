package hatzalahData;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import hatzalahBusiness.*;

public class SymptomIO {

	public static void addSymptom(Connection db, String sympName) throws SQLException {
		db.setAutoCommit(false);
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
		} catch (SQLException ex) {
			db.rollback();
			throw ex;
		}
	}

	public static List<Symptom> getSymptoms(Connection db) throws SQLException {
		List<Symptom> list = new ArrayList<>();
		Statement statement = db.createStatement();
		ResultSet rs = statement.executeQuery("select * from SYMPTOM");
		while (rs.next()) {
			list.add(new Symptom(rs.getInt("Symptom_id"), rs.getString("Symptom_Desc")));

		}
		return list;
	}
	
	
}
