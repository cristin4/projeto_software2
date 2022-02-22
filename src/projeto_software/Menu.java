package projeto_software;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

import employees.Commissioned;
import employees.Employee;
import employees.Hourly;
import employees.Unionist;
import utils.EmployeeControl;
import utils.PaySchedule;
import utils.Payroll;
import utils.Status;
import utils.Syndicate;

public class Menu {

	public void start() {
		ArrayList<Employee> employees = new ArrayList<>();
		ArrayList<Unionist> unionists = new ArrayList<>();
		ArrayList<String> schedules = (ArrayList<String>) PaySchedule.create();
		
		Stack<Status> statusNow = new Stack<>();
		Stack<Status> statusUndo = new Stack<>();
		
		statusNow.add(new Status(employees, unionists, schedules));
		statusUndo.add(new Status(employees, unionists, schedules));
		
		int employeeId = 0;
		boolean sair = false;

		while (!sair) {
			System.out.println("\nEscolha uma opção");
			System.out.println("1: Adicionar funcionário");
			System.out.println("2: Remover funcionário");
			System.out.println("3: Lançar cartão de ponto");
			System.out.println("4: Lançar resultado venda");
			System.out.println("5: Lançar taxa de serviço");
			System.out.println("6: Atualizar dados do funcionário");
			System.out.println("7: Rodar hoje");
			System.out.println("8: Undo/Redo");
			System.out.println("9: Alterar agenda de pagamentos do funcionário");
			System.out.println("10: Editar agendas de pagamento");
			System.out.println("11: Lista de funcionário");
			System.out.println("12: Lista de funcionário que faz parte do sindicato");
			System.out.println("13: Sair");
			Scanner sc = new Scanner(System.in);
			int opcao = sc.nextInt();
			switch (opcao) {
			case 1:
				System.out.println("\nCadastro de funcionário");
				employeeId++;
				Employee newEmployee = EmployeeControl.create(employeeId);
				if (newEmployee != null) {
					employees.add(newEmployee);
					if (newEmployee.getUnion()) {
						Unionist newUnionist = Syndicate.create(newEmployee.getName(), employeeId,
								Syndicate.calcSyndId(employeeId));

						unionists.add(newUnionist);
						System.out.println("Filiado ao sindicado pelo id: " + newUnionist.getUnionId());
					}
					System.out.println("Funcionário criado");
					
					statusNow.push(Status.copy(employees, unionists, schedules));
				} else {
					System.out.println("Erro ao criar funcionário");
				}
				break;
			case 2:
				if (!employees.isEmpty()) {
					EmployeeControl.remove(employees, unionists);
					statusNow.push(Status.copy(employees, unionists, schedules));
				} else {
					System.out.println("Não há registo de funcionário");
				}
				break;
			case 3:
				if (!employees.isEmpty()) {
					Hourly.addTimeCard(employees);
					statusNow.push(Status.copy(employees, unionists, schedules));
				} else {
					System.out.println("Não há registo de funcionário");
				}
				break;
			case 4:
				if (!employees.isEmpty()) {
					Commissioned.addComm(employees);
					statusNow.push(Status.copy(employees, unionists, schedules));
				} else {
					System.out.println("Não há registo de funcionário");
				}
				break;
			case 5:
				if (!employees.isEmpty()) {
					Syndicate.addFee(unionists);
					statusNow.push(Status.copy(employees, unionists, schedules));
				} else {
					System.out.println("Não há registo de funcionário");
				}
				break;
			case 6:
				if (!employees.isEmpty()) {
					EmployeeControl.update(employees, unionists);
					statusNow.push(Status.copy(employees, unionists, schedules));
				} else {
					System.out.println("Não há registo de funcionário");
				}
				break;
			case 7:
				if (!employees.isEmpty()) {
					Payroll.rotate(employees, unionists, schedules);
					statusNow.push(Status.copy(employees, unionists, schedules));
				} else {
					System.out.println("Não há registo de funcionário");
				}
				break;
			case 8:
				System.out.println("Desfazer/Refazer");
				
				Status newStatus = Status.menu(statusNow, statusUndo);
				if(newStatus != null) {
					employees = new ArrayList<>(newStatus.getEmployees());
					unionists = new ArrayList<>(newStatus.getUnionists());
					schedules = new ArrayList<>(newStatus.getSchedules());
				}
				break;
			case 9:
				if (!employees.isEmpty()) {
					PaySchedule.choose(schedules, employees);
					statusNow.push(Status.copy(employees, unionists, schedules));
				} else {
					System.out.println("Não há registo de funcionário");
				}
				break;
			case 10:
				if (!employees.isEmpty()) {
					PaySchedule.management(schedules);
					statusNow.push(Status.copy(employees, unionists, schedules));
				} else {
					System.out.println("Não há registo de funcionário");
				}
				break;
			case 11:
				if (employees.isEmpty()) {
					System.out.println("\nNão há funcionários cadastrados.");
				} else {
					System.out.println("\nHá " + employees.size() + " funcionário(s) cadastrado(s)");
					for (Employee newEmp : employees) {
						EmployeeControl.print(newEmp);
						System.out.print("\n");
					}
				}
				break;
			case 12:
				if (unionists.isEmpty()) {
					System.out.println("\nNão há funcionários participando do sindicato.");
				} else {
					System.out.println("Há " + unionists.size() + " funcionário(s) participando do sindicato.");
					for (Unionist unionist : unionists) {
						Syndicate.print(unionist);
						System.out.print("\n");
					}
				}
				break;
			case 13:
				sair = true;
				System.out.println("Saindo do sistema");
				break;
			default:
				System.out.println("Opção inválida");
				break;
			}
		}
	}

}
