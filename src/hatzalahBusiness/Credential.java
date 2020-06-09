package hatzalahBusiness;

public class Credential {
	private int id;
	private String description;
	public Credential(int id, String description) {
		super();
		this.id = id;
		this.description = description;
	}
	@Override
	public String toString() {
		return "Credential [id=" + id + ", description=" + description + "]";
	}
	public int getId() {
		return id;
	}
	public String getDescription() {
		return description;
	}
	

}
