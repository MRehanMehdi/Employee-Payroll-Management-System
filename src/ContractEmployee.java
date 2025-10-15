public class ContractEmployee extends Employee implements  Payable{
    double contractDuration;

    ContractEmployee(int empID, String name, double basicSalary, double contractDuration){
        super(empID,name,basicSalary);
        this.contractDuration = contractDuration;
    }
    public double getContractDuration() {
        return contractDuration;
    }

    @Override
    double calculateTax() {
        return 0.05 * (basicSalary);
    }
    public double calculateNetSalary(){
        return (basicSalary - calculateTax());
    }

    public void generatePayslip(){
        System.out.println("------------------------------------------------");
        System.out.println("Payslip for Permanent Employee");
        System.out.println("Employee ID   : " + getEmpID());
        System.out.println("Name          : " + getName());
        System.out.println("Basic Salary  : " + basicSalary);
        System.out.println("Contract Duration         : " + contractDuration);
        System.out.println("Tax (10%)     : " + calculateTax());
        System.out.println("Net Salary    : " + calculateNetSalary());
        System.out.println("------------------------------------------------");
    }
}