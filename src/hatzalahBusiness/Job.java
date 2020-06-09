package hatzalahBusiness;

public class Job {

	private int id;
	private String description;
	public Job(int id, String description) {
		super();
		this.id = id;
		this.description = description;
	}
	@Override
	public String toString() {
		return "Job [id=" + id + ", description=" + description + "]";
	}
	public int getId() {
		return id;
	}
	public String getDescription() {
		return description;
	};
}
