abstract class Employee {
    private int empID;
    private String name;
    protected double basicSalary;

    Employee(int empID, String name, double basicSalary){
        this.empID = empID;
        this.name = name;
        this.basicSalary = basicSalary;
    }
    abstract double calculateTax();

    //setter for empID
    void setEmpID(int empID){
        this.empID = empID;
    }

    //setter for name
    void setName(String name){
        this.name = name;
    }

    //setter for basicsalary
    void setBasicSalary(double basicSalary){
        this.basicSalary = basicSalary;
    }

    //getter for empID
    int getEmpID(){
        return empID;
    }

    //getter for name
    String getName(){
        return name;
    }

    //getter for basicsalary
    double getBasicSalary(){
        return basicSalary;
    }
}