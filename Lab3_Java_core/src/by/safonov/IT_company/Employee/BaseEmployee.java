package by.safonov.IT_company.Employee;

public abstract class BaseEmployee implements IEmployee
{
    private double salary;
    private String skills;

    public BaseEmployee(double salary, String skills) {
        this.salary = salary;
        this.skills = skills;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getSkill() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }
}
