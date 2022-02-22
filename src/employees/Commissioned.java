package employees;

import java.util.ArrayList;
import java.util.Scanner;

import utils.EmployeeControl;

public class Commissioned extends Employee {

    private double wage;
    private int workDays;
    private double commPerSale;
    private double Commission;

    public Commissioned(int id, String name, String address, boolean union, String payType, String paySchedule) {
        this.id = id;
        this.name = name;
        this.union = union;
        this.address = address;
        this.payType = payType;
        this.paySchedule = paySchedule;
        this.jobType = "Comissionado";
    }

    public static void addComm(ArrayList<Employee> employees) {
        Scanner input = new Scanner(System.in);
        int employeeId;

        System.out.println("Informe a matrícula do empregado: ");
        employeeId = input.nextInt();

        if (EmployeeControl.meet(employees, employeeId) == null) {
            System.out.println("Não foi localizado empregado com a matrícula informada\n");
        }
        else if (EmployeeControl.meet(employees, employeeId) instanceof Commissioned employee) {
            System.out.println("Insira o valor da venda:");
            double saleTotal = input.nextDouble();
            double commission = (saleTotal * employee.getCommPerSale());

            employee.addComm(commission);

            System.out.println("Valor a receber por esta venda: " + commission);
            System.out.print("\n");

        } else {
            System.out.println("Empregado não é Comissionado!\n");
        }
    }

    public void setSalary(double wage) {
        this.wage = wage;
    }

    public double getWage() {
        return wage;
    }

    public void setWorkDays() {
        this.workDays += 1;
    }

    public int getWorkDays() {
        return workDays;
    }

    public void resetWorkDays() {
        workDays = 0;
    }
    
    public String getAddress() {
		return this.address;
	}
    
    public String getPayType() {
		return this.payType;
	}

    public void setCommPerSale(double commPerSale) {
        this.commPerSale = commPerSale;
    }

    public double getCommPerSale() {
        return commPerSale;
    }

    public void addComm(double Commission) {
        this.Commission += Commission;
    }

    public double getCommission() {
        return Commission;
    }

    public void resetComm() {
        Commission = 0;
    }


}
