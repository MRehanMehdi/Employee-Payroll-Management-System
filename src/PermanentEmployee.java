public class PermanentEmployee extends Employee implements Payable{
    double bonus;

    PermanentEmployee(int empID, String name, double basicSalary, double bonus){
        super(empID,name,basicSalary);
        this.bonus = bonus;
    }

    double getBonus(){
        return bonus;
    }

    @Override
    double calculateTax() {
        return 0.1 * (basicSalary + bonus);
    }

    public double calculateNetSalary(){
        return (basicSalary + bonus) -
                calculateTax();
    }

    public void generatePayslip(){
        System.out.println("------------------------------------------------");
        System.out.println("Payslip for Permanent Employee");
        System.out.println("Employee ID   : " + getEmpID());
        System.out.println("Name          : " + getName());
        System.out.println("Basic Salary  : " + basicSalary);
        System.out.println("Bonus         : " + bonus);
        System.out.println("Tax (10%)     : " + calculateTax());
        System.out.println("Net Salary    : " + calculateNetSalary());
        System.out.println("------------------------------------------------");

    }
}