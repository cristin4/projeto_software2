package utils;

import java.util.ArrayList;
import java.util.Scanner;

import employees.Unionist;

public class Syndicate {
	public static int calcSyndId(int id) {
		return id * 11 + 55;
	}

	public static Unionist create(String name, int id, int unionId) {
		Unionist newUnionist = new Unionist(name, id, unionId);
		System.out.print("\nInforme a taxa sindical:");
		Scanner scan = new Scanner(System.in);

		newUnionist.setUnionTax(scan.nextDouble());

		return newUnionist;
	}

	public static void print(Unionist unionist) {
		System.out.println("Nome: " + unionist.getName());
		System.out.println("Número da matricula: " + unionist.getUnionId());
		System.out.printf("Taxa do sindicato: %.2f \n", unionist.getUnionTax());
	}

	public static Unionist search(ArrayList<Unionist> unionists, int registration) {

		for (Unionist unionist : unionists) {
			if (unionist.getUnionId() == registration) {
				return unionist;
			}
		}
		return null;
	}

	public static void addFee(ArrayList<Unionist> unionists) {
		int unionId;
		Scanner scan = new Scanner(System.in);
		System.out.println("Informe a matricula do sindicato:");
		unionId = scan.nextInt();
		Unionist unionist = Syndicate.search(unionists, unionId);
		if (unionist != null) {
			System.out.println("Informe a taxa do serviço utilizada:");
			unionist.setServiceFees(scan.nextDouble());
			System.out.println("valor da Taxa: " + unionist.getServiceFees());
			System.out.println("Taxa de serviço adicionada com sucesso.");

		}
	}
}
