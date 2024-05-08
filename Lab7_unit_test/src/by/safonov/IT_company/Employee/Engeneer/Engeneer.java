package by.safonov.IT_company.Employee.Engeneer;


import by.safonov.IT_company.Employee.BaseEmployee;

public class Engeneer extends BaseEmployee
{
    private String engineeringDomain;
    public Engeneer(double salary, String skills) {
        super(salary, skills);
    }
    public String getEngineeringDomain() {
        return engineeringDomain;
    }

    public void setEngineeringDomain(String engineeringDomain) {
        this.engineeringDomain = engineeringDomain;
    }
    public void performEngineeringTasks() {
        System.out.println("Performing engineering tasks in the " + engineeringDomain + " domain.");
    }
}
