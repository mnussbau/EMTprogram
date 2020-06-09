package hatzalahBusiness;

public class Address {
	private int addrId;
	private String addrCity;
	private String addrStreet;
	private String addrState;
	private String zip;
	
	public Address(int addrId, String addrCity, String addrStreet, String addrState, String zip) {
		super();
		this.addrId = addrId;
		this.addrCity = addrCity;
		this.addrStreet = addrStreet;
		this.addrState = addrState;
		this.zip = zip;
	}

	public String getAddrCity() {
		return addrCity;
	}

	public void setAddrCity(String addrCity) {
		this.addrCity = addrCity;
	}

	public String getAddrStreet() {
		return addrStreet;
	}

	public void setAddrStreet(String addrStreet) {
		this.addrStreet = addrStreet;
	}

	public String getAddrState() {
		return addrState;
	}

	public void setAddrState(String addrState) {
		this.addrState = addrState;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public int getAddrId() {
		return addrId;
	}

	@Override
	public String toString() {
		return "Address [addrId=" + addrId + ", addrCity=" + addrCity + ", addrStreet=" + addrStreet + ", addrState="
				+ addrState + ", zip=" + zip + "]";
	}

	
}
