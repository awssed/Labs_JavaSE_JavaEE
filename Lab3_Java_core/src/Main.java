import by.safonov.IT_company.Director.Director;
import by.safonov.IT_company.Employee.Engeneer.Engeneer;
import by.safonov.IT_company.Employee.Developer.Developer;
import by.safonov.IT_company.Employee.*;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import javax.swing.text.Position;
import java.util.List;

public class Main {
    static{
        new DOMConfigurator().doConfigure("log/log4j.xml",
                LogManager.getLoggerRepository());
    }
    private static final Logger LOG = Logger.getLogger(Main.class);
    public static void main(String[] args) {
        LOG.info("Starting program_____________________________");
        try {
            LOG.info("Making Director__________________________");
            Director director= Director.getInstance(10000, "Managment","IT department");
            LOG.info("Making engeneers_________________________");

            Engeneer engineer1 = new Engeneer(5000, "Java, SQL");
            engineer1.setEngineeringDomain("Software Development");

            Engeneer engineer2 = new Engeneer(4500, "Python, Machine Learning");
            engineer2.setEngineeringDomain("Data Science");


            System.out.println("Engineer 1:");
            System.out.println("Salary: " + engineer1.getSalary());
            System.out.println("Skills: " + engineer1.getSkill());
            System.out.println("Engineering Domain: " + engineer1.getEngineeringDomain());

            System.out.println();


            System.out.println("Engineer 2:");
            System.out.println("Salary: " + engineer2.getSalary());
            System.out.println("Skills: " + engineer2.getSkill());
            System.out.println("Engineering Domain: " + engineer2.getEngineeringDomain());

            LOG.info("Making developers_____________________");
            Developer developer1 = new Developer(6000, "Java", Developer.Position.Middle, "Java");
            developer1.setPosition(Developer.Position.Junior);

            Developer developer2 = new Developer(8000, "Python", Developer.Position.Junior, "Python");
            developer2.setPosition(Developer.Position.Senior);

            System.out.println("Developer 1:");
            System.out.println("Salary: " + developer1.getSalary());
            System.out.println("Skills: " + developer1.getSkill());
            System.out.println("Position: " + developer1.getPosition());
            System.out.println("Programming Language: " + developer1.getProgLang());

            System.out.println();

            System.out.println("Developer 2:");
            System.out.println("Salary: " + developer2.getSalary());
            System.out.println("Skills: " + developer2.getSkill());
            System.out.println("Position: " + developer2.getPosition());
            System.out.println("Programming Language: " + developer2.getProgLang());

            director.addEmployee(developer1);
            director.addEmployee(developer2);
            director.addEmployee(engineer1);
            director.addEmployee(engineer2);

            System.out.println("Number of employees: " + director.countEmployees());

            director.manageEmployees();
            director.setGoals();
            director.makeStrategicDecisions();

            System.out.println("Sorting employees by salary:");
            director.sortEmployeesBySalary();

            System.out.println("Employees with Java skills:");
            LOG.info("Employess with JAVA skill:");
            List<BaseEmployee> javaEmployees = director.findEmployeesBySkills("Java");
            for (BaseEmployee employee : javaEmployees) {
                if (employee instanceof Developer) {
                    Developer developer = (Developer) employee;
                    System.out.println("Developer - Salary: " + developer.getSalary() + ", Skills: " + developer.getSkill() + ", Position: " + developer.getPosition() + ", Programming Language: " + developer.getProgLang());
                    LOG.info("Developer - Salary: " + developer.getSalary() + ", Skills: " + developer.getSkill() + ", Position: " + developer.getPosition() + ", Programming Language: " + developer.getProgLang());
                } else if (employee instanceof Engeneer) {
                    Engeneer engeneer = (Engeneer) employee;
                    System.out.println("Engineer - Salary: " + engeneer.getSalary() + ", Skills: " + engeneer.getSkill() + ", Engineering Domain: " + engeneer.getEngineeringDomain());
                }
            }


        }catch (RuntimeException e)
        {
            System.out.println("Exception:"+e.getMessage());
            LOG.fatal("Exception:"+e.getMessage());
        }
    }
}