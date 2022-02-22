package utils;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

import employees.Employee;
import employees.Unionist;

public class Status {
	ArrayList<Employee> employees;
	ArrayList<Unionist> unionists;
	ArrayList<String> schedules;

	public Status(ArrayList<Employee> employees, ArrayList<Unionist> unionists, ArrayList<String> schedules) {
		this.employees = new ArrayList<>(employees);
		this.unionists = new ArrayList<>(unionists);
		this.schedules = new ArrayList<>(schedules);
	}

	public ArrayList<Employee> getEmployees() {
		return this.employees;
	}

	public ArrayList<Unionist> getUnionists() {
		return this.unionists;
	}

	public ArrayList<String> getSchedules() {
		return this.schedules;
	}

	public static Status copy(ArrayList<Employee> employees, ArrayList<Unionist> unionists,
			ArrayList<String> schedules) {
		return new Status(employees, unionists, schedules);
	}

	public static Status menu(Stack<Status> statusNow, Stack<Status> statusUndo) {
		Scanner scan = new Scanner(System.in);

		System.out.println("Deseja desfazer ou refazer a última operação?");
		System.out.println("1 - Desfazer\n2 - Refazer");

		int choice = scan.nextInt();
		scan.nextLine();

		if (choice == 1) {
			System.out.println("Desfazendo...");
			return undo(statusNow, statusUndo);
		} else if (choice == 2) {
			System.out.println("Refazendo...");
			return redo(statusNow, statusUndo);
		}
		return null;
	}

	public static Status undo(Stack<Status> statusNow, Stack<Status> statusUndo) {
		if (statusNow.size() == 1) {
			System.out.println("Não houve ação para desfazer!");
			return null;
		}

		Status undo = statusNow.pop();

		statusUndo.push(undo);

		return statusNow.peek();
	}

	public static Status redo(Stack<Status> statusNow, Stack<Status> statusUndo) {
		if (statusUndo.size() == 1) {
			System.out.println("Não houve ação para refazer!");
			return null;
		}

		Status redo = statusUndo.pop();

		statusNow.push(redo);

		return redo;
	}
}
