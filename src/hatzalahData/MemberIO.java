package hatzalahData;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import hatzalahBusiness.*;

public class MemberIO {

	public static List<Member> getMembers(Connection db) throws SQLException{
		db.setAutoCommit(false);
		Statement statement = db.createStatement();
		ResultSet rs = statement.executeQuery("select * from member");
		List<Member>  members = new ArrayList<>();
		while (rs.next()) {
			Member member = new Member(rs.getString("member_id"),rs.getString("fname"),rs.getString("lname"),
				rs.getDate("date_joined").toLocalDate(),rs.getDate("bday").toLocalDate(),
				rs.getString("Marital_status").charAt(0), rs.getInt("branch_id"), rs.getInt("Address_id"),
				rs.getInt("credential_id"),rs.getInt("job_id"),rs.getString("member_phone_num"),rs.getString("active_status").charAt(0));
			members.add(member);
		}
		return members;
	}

}
