package utils;

import java.util.ArrayList;

import employees.Commissioned;
import employees.Employee;
import employees.Hourly;
import employees.Salaried;
import employees.Unionist;

public class Payroll {

	public static void rotate(ArrayList<Employee> employees, ArrayList<Unionist> unionists,
			ArrayList<String> schedules) {
		for (String schedule : schedules) {
			System.out.println("Agenda: " + schedule + "\n");
			Payroll.runEmp(employees, unionists, schedule, schedules);

		}
	}

	public static void runEmp(ArrayList<Employee> employees, ArrayList<Unionist> unionists, String schedule,
			ArrayList<String> schedules) {
		for (Employee employee : employees) {

			Unionist unionist = Syndicate.search(unionists, Syndicate.calcSyndId(employee.getId()));

			if (employee instanceof Salaried) {
				Payroll.salariedWage((Salaried) employee, unionist, schedule, schedules);
			} else if (employee instanceof Commissioned) {
				Payroll.commissionedWage((Commissioned) employee, unionist, schedule, schedules);
			} else if (employee instanceof Hourly) {
				Payroll.hourlyWage((Hourly) employee, unionist, schedule, schedules);
			}

		}
	}
	
	private static int getDays(String schedule) {
		if (schedule.endsWith("Mensalmente")) {
			return 30;
		} else if (schedule.endsWith("Quinzenalmente")) {
			return 15;
		} else if (schedule.endsWith("Semanalmente")) {
			return 7;
		} else {
			return 0;
		}
	}

	public static void salariedWage(Salaried employee, Unionist unionist, String schedule,
			ArrayList<String> schedules) {

		if (schedule.equals(employee.getPaySchedule())) {
			employee.setWorkDays();
			int days;
			
			days = getDays(schedule);

			if (employee.getWorkDays() == days) {
				System.out.println(employee.getName() + ":");
				System.out.printf("Pagamento bruto R$ %.2f\n", employee.getWage());
				if (unionist == null) {
					System.out.printf("Pagamento líquido: R$ %.2f\n", employee.getWage());
				} else {
					System.out.printf("Contribuição sindical: R$ %.2f\n", unionist.getUnionTax());
					System.out.printf("Taxa de serviços: R$ %.2f\n", unionist.getServiceFees());
					System.out.printf("Pagamento líquido: R$ %.2f\n",
							(employee.getWage() - (unionist.getUnionTax() + unionist.getServiceFees())));
				}
				employee.resetWorkDays();
			} else {
				System.out.println(
						"Consta nos registros que não há pagamentos a receber para funcionário " + employee.getName());
			}
		}
	}

	public static void commissionedWage(Commissioned employee, Unionist unionist, String schedule,
			ArrayList<String> schedules) {

		if (schedule.equals(employee.getPaySchedule())) {
			employee.setWorkDays();
			int days;

			days = getDays(schedule);

			if (employee.getWorkDays() == days) {
				System.out.println(employee.getName() + ":");
				System.out.printf("Salário R$ %.2f\n", employee.getWage());
				System.out.printf("Comissões R$ %.2f\n", employee.getCommission());
				System.out.printf("Pagamento bruto R$ %.2f\n", (employee.getWage() + employee.getCommission()));
				if (unionist == null) {
					System.out.printf("Pagamento líquido: R$ %.2f\n", (employee.getWage() + employee.getCommission()));
				} else {
					System.out.printf("Contribuição sindical: R$ %.2f\n", unionist.getUnionTax());
					System.out.printf("Taxa de serviços: R$ %.2f\n", unionist.getServiceFees());
					System.out.printf("Pagamento líquido: R$ %.2f\n", ((employee.getWage() + employee.getCommission())
							- (unionist.getUnionTax() + unionist.getServiceFees())));
				}
				employee.resetWorkDays();
				employee.resetComm();
			} else {
				System.out.println(
						"Consta nos registros que não há pagamentos a receber para funcionário " + employee.getName());
			}
		}
	}

	public static void hourlyWage(Hourly employee, Unionist unionist, String schedule, ArrayList<String> schedules) {
		if (schedule.equals(employee.getPaySchedule())) {

			int days;

			days = getDays(schedule);
			
			if (employee.getWorkDays() == days) {
				System.out.println(employee.getName() + ":");
				System.out.printf("Pagamento por hora trabalhada R$ %.2f\n", employee.getHourlyPay());
				System.out.printf("Salário bruto R$ %.2f\n", employee.getPay());
				if (unionist == null) {
					System.out.printf("Pagamento líquido: R$ %.2f\n", employee.getPay());
				} else {
					System.out.printf("Contribuição sindical: R$ %.2f\n ", unionist.getUnionTax());
					System.out.printf("Taxa de serviços: R$ %.2f\n", unionist.getServiceFees());
					System.out.printf("Pagamento líquido: R$ %.2f\n ",
							(employee.getPay() - (unionist.getUnionTax() + unionist.getServiceFees())));
				}
				employee.resetWorkDays();
				employee.resetPay();
			} else {
				System.out.println(
						"Consta nos registros que não há pagamentos a receber para funcionário " + employee.getName());
			}
		}
	}
}
