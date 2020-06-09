package hatzalahBusiness;

public class Equipment {
	int equip_id;
	String equip_desc;
	public int getEquip_id() {
		return equip_id;
	}
	public void setEquip_id(int equip_id) {
		this.equip_id = equip_id;
	}
	public String getEquip_desc() {
		return equip_desc;
	}
	public void setEquip_desc(String equip_desc) {
		this.equip_desc = equip_desc;
	}
	@Override
	public String toString() {
		return "Equipment [equip_id=" + equip_id + ", equip_desc=" + equip_desc + "]";
	}
	public Equipment(int equip_id, String equip_desc) {
		super();
		this.equip_id = equip_id;
		this.equip_desc = equip_desc;
	}
}
