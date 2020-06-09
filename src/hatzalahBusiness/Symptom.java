package hatzalahBusiness;

public class Symptom {
	private int symptomID;
	private String symptomDesc;
	

	public Symptom(int id, String desc) {
		symptomID=id;
		symptomDesc = desc;
	}


	public int getSymptomID() {
		return symptomID;
	}


	public String getSymptomDesc() {
		return symptomDesc;
	}


	public void setSymptomDesc(String symptomDesc) {
		this.symptomDesc = symptomDesc;
	}


	@Override
	public String toString() {
		return "Symptom [symptomID=" + symptomID + ", symptomDesc=" + symptomDesc + "]";
	}
	

}
