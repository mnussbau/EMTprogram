package hatzalahBusiness;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

public class Branch {
	private int branchId;
	private String branchName;
	private int yearEstablished;
	public Branch(int i, String string, int j) {
		super();
		this.branchId = i;
		this.branchName = string;
		this.yearEstablished = j;
	}
	public int getBranchId() {
		return branchId;
	}
	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public int getYearEstablished() {
		return yearEstablished;
	}
	public void setYearEstablished(int yearEstablished) {
		this.yearEstablished = yearEstablished;
	}
	@Override
	public String toString() {
		return "Branch [branchId=" + branchId + ", branchName=" + branchName + ", yearEstablished=" + yearEstablished
				+ "]";
	}
	
	

}
