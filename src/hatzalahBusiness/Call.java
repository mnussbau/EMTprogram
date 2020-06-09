package hatzalahBusiness;

import java.time.LocalDate;

public class Call {
	
	private int callId; 
	private Branch branch; 
	private LocalDate callReceived;
	private String fname;
	private String lname;
	private int age;
	private Address addr;
	private char transferred;
	private Bus bus;
	private String notes;
	
	public Call(int callId, Branch branch, LocalDate callReceived, String fname, String lname, int age, Address addr,
			char transferred, Bus bus, String notes) {
		super();
		
		this.callId = callId;
		this.branch = branch;
		this.callReceived = callReceived;
		this.fname = fname;
		this.lname = lname;
		this.age = age;
		this.addr = addr;
		this.transferred = transferred;
		this.bus = bus;
		this.notes = notes;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public LocalDate getCallReceived() {
		return callReceived;
	}

	public void setCallReceived(LocalDate callReceived) {
		this.callReceived = callReceived;
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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Address getAddr() {
		return addr;
	}

	public void setAddr(Address addr) {
		this.addr = addr;
	}

	public char getTransferred() {
		return transferred;
	}

	public void setTransferred(char transferred) {
		this.transferred = transferred;
	}

	public Bus getBus() {
		return bus;
	}

	public void setBus(Bus bus) {
		this.bus = bus;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public int getCallId() {
		return callId;
	}

	
 

}
