package by.safonov.IT_company.Employee.Developer;

import by.safonov.IT_company.Employee.BaseEmployee;

public class Developer extends BaseEmployee
{
    public Position position;
    private String programmingLanguage;

    public Developer(double salary, String skills, Position pos,String progLan) {
        super(salary, skills);
        this.position=pos;
        this.programmingLanguage=progLan;
    }
    public Developer() {
        super(0.0, "");
        this.position=Position.Junior;
        this.programmingLanguage="";
    }
    public Position getPosition() {
        return position;
    }
    public void setPosition(Position pos)
    {
        this.position=pos;
    }
    public String getProgLang()
    {
        return this.programmingLanguage;
    }
    public void setProgLAng(String lang)
    {
        this.programmingLanguage=lang;
    }
    public void developSoftware() {
        System.out.println("Developing software using " + programmingLanguage + ".");
    }

    public enum Position{
        Junior,
        Middle,
        Senior
    }
}
