package hatzalahBusiness;

public class Donor {

	private Integer donor_id;
	private String donor_fname;
	private String donor_lname;
	private Integer donor_addr_id;
	private String donor_phone_num;
	public Donor(Integer donor_id, String donor_fname, String donor_lname, Integer donor_addr_id,
			String donor_phone_num) {
		super();
		this.donor_id = donor_id;
		this.donor_fname = donor_fname;
		this.donor_lname = donor_lname;
		this.donor_addr_id = donor_addr_id;
		this.donor_phone_num = donor_phone_num;
	}
	public Integer getDonor_id() {
		return donor_id;
	}
	public void setDonor_id(Integer donor_id) {
		this.donor_id = donor_id;
	}
	public String getDonor_fname() {
		return donor_fname;
	}
	public void setDonor_fname(String donor_fname) {
		this.donor_fname = donor_fname;
	}
	public String getDonor_lname() {
		return donor_lname;
	}
	public void setDonor_lname(String donor_lname) {
		this.donor_lname = donor_lname;
	}
	public Integer getDonor_addr_id() {
		return donor_addr_id;
	}
	public void setDonor_addr_id(Integer donor_addr_id) {
		this.donor_addr_id = donor_addr_id;
	}
	public String getDonor_phone_num() {
		return donor_phone_num;
	}
	public void setDonor_phone_num(String donor_phone_num) {
		this.donor_phone_num = donor_phone_num;
	}
	
	
}
