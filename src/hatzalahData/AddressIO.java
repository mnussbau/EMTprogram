package hatzalahData;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import hatzalahBusiness.Address;

public class AddressIO {

	public static Address getAddress(Connection dbconnection, int addr_id) throws SQLException {
		//addr_id, addr_city, addr_street,  addr_state, Zip
		String sql = "select addr_id, addr_city, addr_street, addr_state, Zip" + " from Home_Address where addr_id = ?";
		PreparedStatement pStatement = dbconnection.prepareStatement(sql);
		// plug the data into the statement , replacing the placeholder
		pStatement.setInt(1, addr_id);
		ResultSet rs = pStatement.executeQuery();
		Address address = null;
		while (rs.next()) {
			address = new Address(rs.getInt("addr_id"), rs.getString("addr_city"), rs.getString("addr_street"), rs.getString("addr_state"), rs.getString("Zip"));
		}
		rs.close();
		return address;
	}
}
