package employees;

public class Salaried extends Employee {
	private double wage;
	private int workDays;

	public Salaried(int id, String name, String address, boolean union, String payType, String paySchedule) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.jobType = "Assalariado";
		this.union = union;
		this.payType = payType;
		this.paySchedule = paySchedule;

	}

	public void setWage(double wage) {
		this.wage = wage;
	}

	public double getWage() {
		return this.wage;
	}

	public void setWorkDays() {
		this.workDays += 1;
	}

	public int getWorkDays() {
		return this.workDays;
	}

	public void resetWorkDays() {
		this.workDays = 0;
	}
}
