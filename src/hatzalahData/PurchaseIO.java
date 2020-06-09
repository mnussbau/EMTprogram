package hatzalahData;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class PurchaseIO {
	public static void AddPurchaseData(Connection db, String EquipmentName, String BranchName, 
			String qty, LocalDate PurchaseDate, String price) throws SQLException {
			int Qty = Integer.parseInt(qty);
			try {
				db.setAutoCommit(false);
				CallableStatement stmt;
				String sql = "{call usp_AddPurchase(?,?,?,?,?)}";
				stmt = db.prepareCall(sql);
				stmt.setString(1, EquipmentName);
				stmt.setString(2, BranchName);
				stmt.setInt(3, Qty);
				stmt.setDate(4,java.sql.Date.valueOf(PurchaseDate));
				stmt.setBigDecimal(5, new BigDecimal(price));
				stmt.execute();
				stmt.getMoreResults();
				db.commit();
				
			}
			catch(SQLException ex) {
				db.rollback();
			}
			catch (DateTimeParseException ex1) {
				db.rollback();
				throw ex1;
			}
		}
}
