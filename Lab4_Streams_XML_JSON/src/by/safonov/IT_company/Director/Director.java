package by.safonov.IT_company.Director;

import by.safonov.IT_company.Employee.BaseEmployee;
import java.util.*;

public class Director extends BaseEmployee {

    private static Director instance;


    private String department;
    private List<BaseEmployee> employees;

    private Director(double salary, String skills, String department) {
        super(salary, skills);
        this.department = department;
        this.employees = new ArrayList<>();
    }
    public static Director getInstance(double salary,String skills, String department)
    {
        if(instance==null)
        {
            instance=new Director(salary,skills,department);
        }
        else {
            throw new RuntimeException("Only one instance of Director is allowed");
        }
        return instance;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void manageEmployees() {
        System.out.println("Managing employees in the " + department + " department.");
    }

    public void setGoals() {
        System.out.println("Setting goals and objectives for the IT company.");
    }

    public void makeStrategicDecisions() {
        System.out.println("Making strategic decisions for the IT company.");
    }

    public void addEmployee(BaseEmployee employee) {
        employees.add(employee);
    }

    public int countEmployees() {
        return employees.size();
    }

    public void sortEmployeesBySalary() {
        Collections.sort(employees, (e1, e2) -> Double.compare(e2.getSalary(), e1.getSalary()));
    }

    public List<BaseEmployee> findEmployeesBySkills(String requiredSkills) {
        List<BaseEmployee> matchingEmployees = new ArrayList<>();
        for (BaseEmployee employee : employees) {
            if (employee.getSkills().equals(requiredSkills)) {
                matchingEmployees.add(employee);
            }
        }
        return matchingEmployees;
    }
}
