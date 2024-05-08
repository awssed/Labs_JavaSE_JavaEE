package by.safonov.IT_company;

import by.safonov.IT_company.Director.Director;
import by.safonov.IT_company.Employee.BaseEmployee;
import by.safonov.IT_company.Employee.Developer.Developer;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestDirectorEmployee {
    @DataProvider(name = "testdata")
    public Object[][] testData()
    {
        return new Object[][]
                {
                        {19000,"Java,Python",Developer.Position.Middle,"Java"},
                        {20000,"Java,Python",Developer.Position.Middle,"Java"}
                };
    }
    private Director director;
    private Developer developer;
    @BeforeClass()
    private void SetUp()
    {
        director=Director.getInstance(2000,"Skill","Department");
        developer= developer = new Developer(5000.0, "Java, Python", Developer.Position.Junior, "Java");
    }
    @Test(expectedExceptions =RuntimeException.class)
    private void testSingleDirector()
    {
        Director newDirector=Director.getInstance(5000,"Skills","Department");
    }
    @Test
    private void testAddDeveloper()
    {
        director.addEmployee(developer);
        Assert.assertEquals(1,director.countEmployees());
    }
    @Test(dataProvider="testdata")
    private void testSortEmloyee(double salary,String skills,Developer.Position position,String progLang)
    {
        Developer employee1 = new Developer(salary, skills, position, progLang);
        director.addEmployee(employee1);
        director.sortEmployeesBySalary();
        Assert.assertEquals(employee1.getSalary(), director.getEmployees().get(0).getSalary());
    }

    private void testFindSkillEmloyee()
    {
        Developer developer1=new Developer(4000,"Skill", Developer.Position.Senior,"ProgLang");
        director.addEmployee(developer1);
        BaseEmployee expected=developer1;
        Assert.assertSame(expected,developer1);
    }
}
