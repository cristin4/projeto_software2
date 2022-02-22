package employees;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import utils.EmployeeControl;

public class Hourly extends Employee {
	private double hourlyPay; // pagamento por hora
	private int maxHours = 8;
	private double pay;
	private int workDays = 0;

	public Hourly(int id, String name, String address, boolean union, String payType, String paySchedule) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.jobType = "Horista";
		this.union = union;
		this.payType = payType;
		this.paySchedule = paySchedule;
	}

	public void setHourlyPay(double hourlyPay) {
		this.hourlyPay = hourlyPay;
	}

	public double getHourlyPay() {
		return this.hourlyPay;
	}

	public void setMaxHours(int maxHours) {
		this.maxHours = maxHours;
	}

	public int getMaxHours() {
		return this.maxHours;
	}

	public void setPay(double pay) {
		this.pay += pay;
	}

	public double getPay() {
		return this.pay;
	}

	public void setWorkDays() {
		this.workDays += workDays;
	}

	public int getWorkDays() {
		return this.workDays;
	}

	public void resetWorkDays() {
		this.workDays = 0;
	}

	public void resetPay() {
		this.pay = 0;
	}

	public static void addTimeCard(ArrayList<Employee> employees) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Informe a matricula do funcionário");
		int id = scan.nextInt();
		scan.nextLine();
		if (EmployeeControl.meet(employees, id)instanceof Hourly employee) {
			System.out.println("Informe a data e hora de entrada [dd-MM-yyyy HH:mm]");
			DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
			LocalDateTime start = LocalDateTime.parse(scan.nextLine(), format);

			System.out.println("Informe a data e hora de saída [dd-MM-yyyy HH:mm]");
			LocalDateTime end = LocalDateTime.parse(scan.nextLine(), format);

			String between = Duration.between(start, end).toString();

			double workHours = Duration.parse(between).toHours();

			employee.setWorkDays();
			double pay;
			if (workHours > employee.getMaxHours()) {
				pay = (employee.getMaxHours() * employee.getHourlyPay())
						+ (workHours - employee.getMaxHours()) * (employee.getHourlyPay() * 1.5);
			} else {

				pay = employee.getHourlyPay() * workHours;
			}
			employee.setPay(pay);

			System.out.println("Cartão adicionado com sucesso.");
			System.out.println("Valor a receber:" + pay);
			System.out.println("\n");

		}

	}
}