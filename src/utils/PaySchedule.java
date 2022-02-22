package utils;

import java.util.ArrayList;
import java.util.Scanner;

import employees.Employee;

public class PaySchedule {

	public static ArrayList<String> create() {
		ArrayList<String> schedules = new ArrayList<>();
		schedules.add(0, "Mensalmente");
		schedules.add(1, "Quinzenalmente");
		schedules.add(2, "Semanalmente");
		return schedules;
	}

	public static void management(ArrayList<String> schedules) {
		System.out.println("1 - Criar agenda\n" + "2 - Listar as agendas\n" + "3 - Apagar agenda");
		Scanner scan = new Scanner(System.in);
		int option = scan.nextInt();
		scan.nextLine();

		if (option == 1) {
			addSchedule(schedules);
		} else if (option == 2) {
			listSchedule(schedules);
		} else if (option == 3) {
			deleteSchedule(schedules);
		} else {
			System.out.println("opção inválida");
		}
	}

	public static void listSchedule(ArrayList<String> schedules) {
		int cont = 0;
		for (String schedule : schedules) {
			System.out.printf(cont + " - ");
			System.out.println(schedule);
			cont++;
		}
	}

	public static void addSchedule(ArrayList<String> schedules) {
		System.out.println("Digite a agenda de pagamento que deseja adicionar");
		Scanner scan = new Scanner(System.in);

		int size = schedules.size();
		schedules.add(size, scan.nextLine());
	}

	public static void deleteSchedule(ArrayList<String> schedules) {
		listSchedule(schedules);
		System.out.println("Digite qual deseja apagar, com exceção das agendas padrão");
		Scanner scan = new Scanner(System.in);
		int option = scan.nextInt();
		if (option > 2) {
			schedules.remove(option);
		}
	}

	public static void choose(ArrayList<String> schedules, ArrayList<Employee> employees) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Informe o matrícula do funcionário");
		int id = scan.nextInt();
		scan.nextLine();

		Employee employee = EmployeeControl.meet(employees, id);

		if (employee != null) {
			System.out.println("Agenda de pagamento atual do funcinário: " + employee.getPaySchedule());
			System.out.println("\nEscolha entre as opções abaixo, a nova agenda de pagamento:");
			PaySchedule.listSchedule(schedules);

			int paySch = scan.nextInt();
			if (paySch >= 0 && paySch < schedules.size()) {
				employee.setPaySchedule(schedules.get(paySch));
				System.out.println("Agenda autualizada com sucesso.");
			} else {
				System.out.println("Opção inválida, favor verificar.");
			}
		}
	}
}
