package employees;

public class Unionist {
	private String name;
	private int id;
	private int unionId;
	private double unionTax;
	private double serviceFees = 0;

	public Unionist(String name, int id, int unionId) {
		this.name = name;
		this.id = id;
		this.unionId = unionId;

	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return this.id;
	}

	public void setUnionId(int unionId) {
		this.unionId = unionId;
	}

	public int getUnionId() {
		return this.unionId;
	}

	public void setUnionTax(double unionTax) {
		this.unionTax = unionTax;
	}

	public double getUnionTax() {
		return this.unionTax;
	}

	public void setServiceFees(double serviceFees) {
		this.serviceFees += serviceFees;
	}

	public double getServiceFees() {
		return this.serviceFees;
	}
}