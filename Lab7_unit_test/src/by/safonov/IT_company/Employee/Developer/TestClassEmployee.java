package by.safonov.IT_company.Employee.Developer;

import by.safonov.IT_company.Employee.Developer.Developer;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class TestClassEmployee {
    private Developer developer;
    private ByteArrayOutputStream testStream;
    private PrintStream originalPrint;
    private PrintStream testPrint;


    @BeforeClass
    public void setUp() {
        developer = new Developer(5000.0, "Java, Python", Developer.Position.Junior, "Java");
//        testStream=new ByteArrayOutputStream();
//        testPrint=new PrintStream(testStream);
//        System.setOut(testPrint);
    }
    @Test
    public void testGetPosition() {
        Developer.Position actualPosition = developer.position;
        Assert.assertEquals(Developer.Position.Junior, actualPosition);
    }
    @Test
    public void testSetPosition() {
        developer.setPosition(Developer.Position.Senior);
        Developer.Position actualPosition = developer.position;
        Assert.assertEquals(Developer.Position.Senior, actualPosition);
    }
    @Test
    public void testGetLang() {
        String actualLang = developer.getProgLang();
        Assert.assertEquals("Java", actualLang);
    }
    @Test
    private void testSetLang() {
        developer.setProgLAng("Python");
        String actualLang = developer.getProgLang();
        Assert.assertEquals("Python", actualLang);
    }
    @Ignore
    @Test
    private void testDevelopSoftware()
    {
        developer.developSoftware();
        String expectedOutput = "Developing software using Python.";
        String actualOutput = testPrint.toString().trim();
        Assert.assertEquals(expectedOutput,actualOutput);
    }
    @Test(timeOut = 2000)
    private void testCloneDeveloper()
    {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Developer clone=developer.clone();
        Assert.assertNotSame(clone,developer);
        Assert.assertEquals(clone.getPosition(),developer.getPosition());
        Assert.assertEquals(clone.getProgLang(),developer.getProgLang());
        Assert.assertEquals(clone.getSalary(),developer.getSalary());
        Assert.assertEquals(clone.getSkills(),developer.getSkills());
        Assert.assertEquals(clone.toString(),developer.toString());
    }
    @AfterClass
    private void tearDown() {
//        System.setOut(originalPrint);
    }
}