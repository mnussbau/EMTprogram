package hatzalahBusiness;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Bus {

	private int busId;
	private String vin; 
	private LocalDate datePurchased;
	private LocalDate lastMaintained; 
	private BigDecimal purchasePrice;
	private Branch branch;
	
	public Bus(int busId, String vin, LocalDate datePurchased, LocalDate lastMaintained, BigDecimal purchasePrice,
			Branch branch) {
		super();
		this.busId = busId;
		this.vin = vin;
		this.datePurchased = datePurchased;
		this.lastMaintained = lastMaintained;
		this.purchasePrice = purchasePrice;
		this.branch = branch;
	}

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public LocalDate getDatePurchased() {
		return datePurchased;
	}

	public void setDatePurchased(LocalDate datePurchased) {
		this.datePurchased = datePurchased;
	}

	public LocalDate getLastMaintained() {
		return lastMaintained;
	}

	public void setLastMaintained(LocalDate lastMaintained) {
		this.lastMaintained = lastMaintained;
	}

	public BigDecimal getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(BigDecimal purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public int getBusId() {
		return busId;
	}

	@Override
	public String toString() {
		return "Bus [busId=" + busId + ", vin=" + vin + ", datePurchased=" + datePurchased + ", lastMaintained="
				+ lastMaintained + ", purchasePrice=" + purchasePrice + ", branch=" + branch + "]";
	} 


}
