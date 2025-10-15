import java.util.ArrayList;
import java.util.*;

public class PayrollSystem {

    static Scanner s = new Scanner(System.in);
    static ArrayList<Payable> employees = new ArrayList<>();

    public static void main(String[] args) {
        int choice;

        while (true) {
            System.out.println("\n========= Employee Payroll System =========");
            System.out.println("1. Add Employee");
            System.out.println("2. View All Employees");
            System.out.println("3. Search Employee");
            System.out.println("4. Highest Net Salary");
            System.out.println("5. Average Salary");
            System.out.println("6. Generate Payslip");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");

            choice = s.nextInt();
            s.nextLine(); // clear newline

            switch (choice) {
                case 1 -> addEmployee();
                case 2 -> viewAll();
                case 3 -> searchEmployee();
                case 4 -> highestSalary();
                case 5 -> averageSalary();
                case 6 -> payslip();
                case 7 -> exitSummary();
                default -> System.out.println("Invalid option! Please try again.");
            }
        }
    }

    static void addEmployee() {
        if (employees.size() >= 5) {
            System.out.println("You can only add up to 5 employees!");
            return;
        }

        System.out.print("Enter Employee Type (Permanent/Contract): ");
        String type = s.nextLine().toLowerCase();

        System.out.print("Enter Name: ");
        String name = s.nextLine();

        System.out.print("Enter ID: ");
        int id = s.nextInt();

        System.out.print("Enter Basic Salary: ");
        double basicSalary = s.nextDouble();

        if (type.equals("permanent")) {
            System.out.print("Enter Bonus: ");
            double bonus = s.nextDouble();
            employees.add(new PermanentEmployee(id, name, basicSalary, bonus));
        } else if (type.equals("contract")) {
            System.out.print("Enter Contract Duration (in months): ");
            double duration = s.nextDouble();
            employees.add(new ContractEmployee(id, name, basicSalary, duration));
        } else {
            System.out.println("Invalid employee type!");
            return;
        }

        System.out.println("Employee added successfully!");
    }

    static void viewAll() {
        if (employees.isEmpty()) {
            System.out.println("No employees found!");
            return;
        }

        System.out.println("\n--------------------------------------------------------------");
        System.out.printf("%-10s %-15s %-12s %-12s %-12s %-12s\n",
                "ID", "Name", "Type", "Salary", "Bonus/Dur", "Net Salary");
        System.out.println("--------------------------------------------------------------");

        for (Payable p : employees) {
            Employee e = (Employee) p;
            String type;
            String extra;

            if (e instanceof PermanentEmployee pe) {
                type = "Permanent";
                extra = String.format("%.2f", pe.getBonus());
            } else if (e instanceof ContractEmployee ce) {
                type = "Contract";
                extra = ce.getContractDuration() + " mo";
            } else {
                type = "-";
                extra = "-";
            }

            System.out.printf("%-10d %-15s %-12s %-12.2f %-12s %-12.2f\n",
                    e.getEmpID(), e.getName(), type, e.getBasicSalary(), extra, p.calculateNetSalary());
        }

        System.out.println("--------------------------------------------------------------");
    }

    static void searchEmployee() {
        if (employees.isEmpty()) {
            System.out.println("No employees in the system!");
            return;
        }

        System.out.print("Enter Employee ID to search: ");
        int id = s.nextInt();

        for (Payable p : employees) {
            Employee e = (Employee) p;
            if (e.getEmpID() == id) {
                p.generatePayslip();
                return;
            }
        }
        System.out.println("Employee not found!");
    }

    static void highestSalary() {
        if (employees.isEmpty()) {
            System.out.println("No employees available!");
            return;
        }

        Payable highest = employees.get(0);
        for (Payable p : employees) {
            if (p.calculateNetSalary() > highest.calculateNetSalary()) {
                highest = p;
            }
        }

        System.out.println("\nEmployee with Highest Net Salary:");
        highest.generatePayslip();
    }

    static void averageSalary() {
        if (employees.isEmpty()) {
            System.out.println("No employees available!");
            return;
        }

        double total = 0;
        for (Payable p : employees) {
            total += p.calculateNetSalary();
        }

        double avg = total / employees.size();
        System.out.printf("Average Net Salary of Employees: %.2f\n", avg);
    }

    static void payslip() {
        if (employees.isEmpty()) {
            System.out.println("No employees in the system!");
            return;
        }

        System.out.print("Enter Employee ID: ");
        int id = s.nextInt();

        for (Payable p : employees) {
            Employee e = (Employee) p;
            if (e.getEmpID() == id) {
                p.generatePayslip();
                return;
            }
        }
        System.out.println("Employee not found!");
    }

    static void exitSummary() {
        System.out.println("\nProgram closed successfully!");
        System.out.println("Total Employees: " + employees.size());
        if (!employees.isEmpty()) averageSalary();
        System.out.println("Goodbye!");
        System.exit(0);
    }
}