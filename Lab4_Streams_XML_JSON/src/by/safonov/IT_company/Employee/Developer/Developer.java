package by.safonov.IT_company.Employee.Developer;

import by.safonov.IT_company.Employee.BaseEmployee;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Developer extends BaseEmployee implements Cloneable
{
    public Position position;
    private String progLang;

    public Developer(double salary, String skills, Position pos,String programmingLanguageLan) {
        super(salary, skills);
        this.position=pos;
        this.progLang=programmingLanguageLan;
    }
    public Developer() {
        super(0.0, "");
        this.position=Position.Junior;
        this.progLang="";
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
        return this.progLang;
    }
    public void setProgLAng(String lang)
    {
        this.progLang=lang;
    }
    public void developSoftware() {
        System.out.println("Developing software using " + progLang + ".");
    }

    public enum Position{
        Junior,
        Middle,
        Senior
    }
    @Override
    public String toString() {
        return "Developer{" +
                "salary=" + getSalary() +
                ", skills='" + getSkills() + '\'' +
                ", position=" + position +
                ", programmingLanguage='" + progLang + '\'' +
                '}';
    }
    @Override
    public Developer clone() {
        try {
            Developer cloned = (Developer) super.clone();
            cloned.position = this.position;
            cloned.progLang = this.progLang;
            return cloned;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}
