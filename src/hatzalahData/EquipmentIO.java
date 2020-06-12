package hatzalahData;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import hatzalahBusiness.Equipment;

public class EquipmentIO {
	
	public static List<Equipment> getEquipment(Connection db) throws SQLException{
		db.setAutoCommit(false);
		List<Equipment> allEquipment = new ArrayList<>();
		Statement statement = db.createStatement();
		ResultSet rs = statement.executeQuery("select * from EQUIPMENT;");
		while (rs.next()) {
			allEquipment.add(new Equipment(rs.getInt("Equip_id"),rs.getString("Equip_desc")));
		}
		return allEquipment;
	}
	
	public static void addEquipment(Connection db, String equipName) throws SQLException{
		db.setAutoCommit(false);
		try {
			db.setAutoCommit(false);
			CallableStatement stmt;
			String sql = "{call usp_AddEquipment(?)}";
			stmt = db.prepareCall(sql);
			stmt.setString(1, equipName);
			stmt.execute();
			stmt.getMoreResults();
			db.commit();
			return;
		} catch (SQLException ex) {
			db.rollback();
			throw ex;
		}
	}
}
