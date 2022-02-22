package employees;

public abstract class Employee {
	protected int id;
	protected String name;
	protected String address;
	protected String jobType;
	protected boolean union = false;
	protected String payType;
	protected String paySchedule;

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return this.id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddress() {
		return this.address;
	}

	public void setJobType(String jobType) {
		this.jobType = jobType;
	}

	public String getJobType() {
		return this.jobType;
	}

	public void setUnion(boolean union) {
		this.union = union;
	}

	public boolean getUnion() {
		return this.union;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getPayType() {
		return this.payType;
	}

	public void setPaySchedule(String paySchedule) {
		this.paySchedule = paySchedule;
	}

	public String getPaySchedule() {
		return this.paySchedule;
	}
}
