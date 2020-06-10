package hatzalahData;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import hatzalahBusiness.Credential;
import hatzalahBusiness.Job;

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
		} catch (SQLException ex) {
			db.rollback();
			throw ex;
		}
	}

	public static ArrayList<Job> getJobs(Connection dbConnection) throws SQLException {
		ArrayList<Job> jobs = new ArrayList<Job>();
		try {
			Statement statement = dbConnection.createStatement();
			ResultSet rs = statement.executeQuery("select * from job");

			while (rs.next()) {
				Job job = new Job(rs.getInt("job_id"), rs.getString("job_desc"));
				jobs.add(job);
			}
			rs.close();
			statement.close();

		} catch (SQLException sqlException) {
			throw sqlException;
		}

		return jobs;
	}

	public static String getJobName(Connection dbconnection, int job_id) throws SQLException {
		String sql = "select job_desc" + " from Job where job_id = ?";
		PreparedStatement pStatement = dbconnection.prepareStatement(sql);
		// plug the data into the statement , replacing the placeholder
		pStatement.setInt(1, job_id);
		ResultSet rs = pStatement.executeQuery();
		String jobName = null;
		while (rs.next()) {
			jobName = rs.getString("job_desc");
		}
		rs.close();
		return jobName;
	}
}