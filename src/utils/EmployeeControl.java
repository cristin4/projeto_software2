package utils;

import java.util.ArrayList;
import java.util.Scanner;

import employees.Commissioned;
import employees.Employee;
import employees.Hourly;
import employees.Salaried;
import employees.Unionist;

public class EmployeeControl {

	public static void update(ArrayList<Employee> employees, ArrayList<Unionist> unionists) {
		Scanner input = new Scanner(System.in);
		int option, id;

		System.out.println("Informe a matr�cula do funcion�rio: ");
		id = input.nextInt();

		Employee employee = meet(employees, id);

		if (employee != null) {
			System.out.println("\nEmpregado a ser alterado o cadastro:");
			print(employee);

			System.out.println("""

					Escolha o dado a ser atualizado:
					1 - Nome
					2 - Endere�o
					3 - Tipo de empregado
					4 - M�todo de pagamento
					5 - Filia��o sindicato
					6 - Taxa sindicato""");
			option = input.nextInt();

			switch (option) {
			case 1 -> {
				System.out.println("Insira o novo nome para o empregado:");
				employee.setName(input.nextLine());
				System.out.println("Nome do empregado atualizado com sucesso!\n");
			}
			case 2 -> {
				System.out.println("Insira o novo endere�o para o empregado:");
				employee.setAddress(input.nextLine());
				System.out.println("Endere�o do empregado atualizado com sucesso!\n");
			}
			case 3 -> {
				System.out.println("""

						Insira o novo tipo de empregado:
						1 - Assalariado
						2 - Comissionado
						3 - Horista""");

				int oldId = employee.getId();
				String oldName = employee.getName();
				String oldAddress = employee.getAddress();
				String oldPaymentMethod = employee.getPayType();
				boolean oldUnion = employee.getUnion();
				String oldPaymentSchedule = employee.getPaySchedule();
				int newType = input.nextInt();

				switch (newType) {
				case 1 -> {
					employees.remove(employee);
					Salaried newSalaried = new Salaried(oldId, oldName, oldAddress, oldUnion, oldPaymentMethod,
							oldPaymentSchedule);
					employees.add(newSalaried);
					System.out.println("Informe o valor da remunera��o mensal (xxxx,xx): ");
					newSalaried.setWage(input.nextDouble());
					System.out.println("Tipo de empregado alterado para Assalariado com sucesso!\n");
				}
				case 2 -> {
					employees.remove(employee);
					Commissioned newCommissioned = new Commissioned(oldId, oldName, oldAddress, oldUnion,
							oldPaymentMethod, oldPaymentSchedule);
					newCommissioned.setUnion(oldUnion);
					employees.add(newCommissioned);
					System.out.println("Informe o valor da remunera��o mensal (xxxx,xx): ");
					newCommissioned.setSalary(input.nextDouble());
					System.out.println("Informe o valor da comiss�o por venda (0,xx): ");
					newCommissioned.setCommPerSale(input.nextDouble());
					System.out.println("Tipo de empregado alterado para Comissionado com sucesso!\n");
				}
				case 3 -> {
					employees.remove(employee);
					Hourly newHourly = new Hourly(oldId, oldName, oldAddress, oldUnion, oldPaymentMethod,
							oldPaymentSchedule);
					employees.add(newHourly);
					System.out.println("Informe o valor da hora trabalhada (xxxx,xx): ");
					newHourly.setHourlyPay(input.nextDouble());
					System.out.println("Tipo de empregado alterado para Horista com sucesso!\n");
				}
				default -> System.out.println("Op��o inv�lida! Abortando opera��o\n");
				}
			}
			case 4 -> {
				System.out.println(
						"Digite o novo m�todo de pagamento: \nCheque por Correios\nCheque em m�os\nDep�sito em conta banc�ria");
				employee.setPayType(input.nextLine());
				System.out.println("M�todo de pagamento alterado com sucesso!\n");
			}
			case 5 -> {
				int unionId = Syndicate.calcSyndId(id);
				Unionist unionist = Syndicate.search(unionists, unionId);
				if (unionist == null) {
					System.out.println(
							"Empregado n�o � filiado ao sindicato. Deseja filiar ao sindicato?\n S - Sim | N - N�o");
					if (input.nextLine().equalsIgnoreCase("s")) {
						Unionist newUnionist = Syndicate.create(employee.getName(), id, unionId);
						unionists.add(newUnionist);
						System.out
								.println("Empregado filiado ao sindicato pela Matr�cula: " + newUnionist.getUnionId());
					}
				} else {
					System.out.println("""
							Empregado filiado ao sindicato. Deseja CANCELAR a filia��o ao sindicato?
							 S - Sim | N - N�o""");
					if (input.nextLine().equalsIgnoreCase("s")) {
						unionists.remove(Syndicate.search(unionists, unionId));
						System.out.println("Empregado removido do sindicato com sucesso!\n");
					}
				}
			}
			case 6 -> {
				System.out.println("Insira a nova taxa sindical: ");
				Syndicate.search(unionists, Syndicate.calcSyndId(id)).setUnionTax(input.nextDouble());
				System.out.println("Taxa sindical atualizada com sucesso!\n");
			}
			default -> System.out.println("Op��o inv�lida! Abortando opera��o\n");

			}

		} else {
			System.out.println("Empregado n�o encontrado! Verifique a matr�cula!\n");
		}
	}

	public static void print(Employee newEmp) {
		System.out.println("\nMatr�cula do empregado: " + newEmp.getId() + "\nNome: " + newEmp.getName()
				+ "\nEndere�o: " + newEmp.getAddress() + "\nTipo de Empregado: " + newEmp.getJobType());

		System.out.println("M�todo de pagamento: " + newEmp.getPayType() + " [" + newEmp.getPaySchedule() + "]");

		System.out.print("Sindicato: ");
		if (newEmp.getUnion()) {
			System.out.println("Sim");
		} else {
			System.out.println("N�o");
		}

		System.out.print("\n");
	}

	public static Employee create(int employeeId) {
		Scanner input = new Scanner(System.in);
		System.out.println("Matr�cula: " + employeeId);

		System.out.print("Informe o nome do empregado: ");
		String name = input.nextLine();

		System.out.print("\nInsira o Endere�o Completo: ");
		String address = input.nextLine();

		String payType;
		System.out.println(
				"Digite o m�todo de pagamento: \nCheque por Correios\nCheque em m�os\nDep�sito na conta banc�ria");
		payType = input.nextLine();
		
		String jobType;
		System.out.println("Digite o tipo de contrata��o: \nAssalariado\nComissionado\nHorista");
		jobType = input.nextLine();

		System.out.println("� um membro do sindicato?" + "\n S - Sim | N - N�o");
		boolean syndicate = input.next().contentEquals("S");

		if (jobType.equalsIgnoreCase("Assalariado")) {
			Salaried newEmployee = new Salaried(employeeId, name, address, syndicate, payType, jobType);
			System.out.println("Informe o valor do sal�rio mensal (xxxx,xx): ");
			newEmployee.setWage(input.nextDouble());
			return newEmployee;
		} else if (jobType.equalsIgnoreCase("Comissionado")) {
			Commissioned newEmployee = new Commissioned(employeeId, name, address, syndicate, payType, jobType);
			System.out.println("Informe o valor do sal�rio mensal (xxxx,xx): ");
			newEmployee.setSalary(input.nextDouble());
			System.out.println("Informe o percentual da comiss�o por venda (xx): ");
			newEmployee.setCommPerSale(input.nextDouble() / 100);
			newEmployee.resetComm();
			return newEmployee;
		} else if (jobType.equalsIgnoreCase("Horista")) {
			Hourly newEmployee = new Hourly(employeeId, name, address, syndicate, payType, jobType);
			System.out.println("Informe o valor da hora trabalhada (xxxx,xx): ");
			newEmployee.setHourlyPay(input.nextDouble());
			return newEmployee;
		}

		input.nextLine();
		return null; // only if error occurred
	}

	public static void remove(ArrayList<Employee> employees, ArrayList<Unionist> unionists) {
		Scanner input = new Scanner(System.in);
		int employeeId;

		System.out.println("Informe a matr�cula do empregado a ser apagado: ");
		employeeId = input.nextInt();

		Employee employee = meet(employees, employeeId);

		if (employee == null) {
			System.out.println("Empregado n�o localizado, verifique a matr�cula!");
		} else if (employee.getId() == employeeId) {
			System.out.println("Empregado(a) " + employee.getName()
					+ " ser� removido, tem certeza? (Esta a��o tamb�m o remover� do cadastro Sindical) [S/N]");

			String delete = input.next().toLowerCase();

			if (delete.equals("s")) {
				employees.remove(employee);

				unionists.removeIf(unionist -> unionist.getUnionId() == Syndicate.calcSyndId(employee.getId()));

				System.out.println("Empregado removido com sucesso!");

			} else {
				System.out.println("Abortado.");
			}
		}
	}

	public static Employee meet(ArrayList<Employee> employees, int id) {
		for (Employee employee : employees) {
			if (employee.getId() == id) {
				return employee;
			}
		}
		return null;
	}
}