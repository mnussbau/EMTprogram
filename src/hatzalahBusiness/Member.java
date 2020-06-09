package hatzalahBusiness;

import java.time.LocalDate;

public class Member {
	private String memberId;
	private String fname;
	private String lname;
	private LocalDate dateJoined;
	private LocalDate bday;
	char maritalStatus;
	int branch_id;
	int address_id;
	int credential_id; 
	private String member_phone_num;
	private char active_status;
	private int job_id;
	@Override
	public String toString() {
		return "Member [memberId=" + memberId + ", fname=" + fname + ", lname=" + lname + ", dateJoined=" + dateJoined
				+ ", bday=" + bday + ", maritalStatus=" + maritalStatus + ", branch_id=" + branch_id + ", address_id="
				+ address_id + ", credential_id=" + credential_id + ", member_phone_num=" + member_phone_num
				+ ", active_status=" + active_status + "]";
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public LocalDate getDateJoined() {
		return dateJoined;
	}
	public void setDateJoined(LocalDate dateJoined) {
		this.dateJoined = dateJoined;
	}
	public LocalDate getBday() {
		return bday;
	}
	public void setBday(LocalDate bday) {
		this.bday = bday;
	}
	public char getMaritalStatus() {
		return maritalStatus;
	}
	public void setMaritalStatus(char maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	public int getBranch_id() {
		return branch_id;
	}
	public void setBranch_id(int branch_id) {
		this.branch_id = branch_id;
	}
	public int getAddress_id() {
		return address_id;
	}
	public void setAddress_id(int address_id) {
		this.address_id = address_id;
	}
	public int getCredential_id() {
		return credential_id;
	}
	public void setCredential_id(int credential_id) {
		this.credential_id = credential_id;
	}
	public String getMember_phone_num() {
		return member_phone_num;
	}
	public void setMember_phone_num(String member_phone_num) {
		this.member_phone_num = member_phone_num;
	}
	public char getActive_status() {
		return active_status;
	}
	public void setActive_status(char active_status) {
		this.active_status = active_status;
	}
	public Member(String memberId, String fname, String lname, LocalDate dateJoined, LocalDate bday, char maritalStatus,
			int branch_id, int address_id, int credential_id, int job_id, String member_phone_num, char active_status) {
		super();
		this.memberId = memberId;
		this.fname = fname;
		this.lname = lname;
		this.dateJoined = dateJoined;
		this.bday = bday;
		this.maritalStatus = maritalStatus;
		this.branch_id = branch_id;
		this.address_id = address_id;
		this.credential_id = credential_id;
		this.member_phone_num = member_phone_num;
		this.active_status = active_status;
		this.setJob_id(job_id);
	}
	public int getJob_id() {
		return job_id;
	}
	public void setJob_id(int job_id) {
		this.job_id = job_id;
	}


}
