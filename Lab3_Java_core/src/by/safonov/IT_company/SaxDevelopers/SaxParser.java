package by.safonov.IT_company.SaxDevelopers;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.*;
import by.safonov.IT_company.Employee.Developer.Developer;
import java.util.ArrayList;

import javax.xml.stream.events.StartElement;
import java.util.ArrayList;

public class SaxParser extends DefaultHandler {
    String thisElement;
    ArrayList<Developer> developers =new ArrayList<Developer>();
    @Override
    public void startDocument() throws SAXException{
        System.out.println("Start parse XML...");
    }
    @Override
    public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {
        thisElement = qName;
    }
    @Override
    public void characters (char ch[], int start, int length)
            throws SAXException
    {
        Developer developer= new Developer();
        if(thisElement.equals("Salary"))
        {
            developer.setSalary(Double.parseDouble(new String(ch, start, length)));
        }
        if(thisElement.equals("Skills"))
        {
            developer.setSkills(new String(ch, start, length));
        }
        if(thisElement.equals("Position"))
        {
            String position=new String(ch,start,length);
            if(position.equals("Junior"))
            {
                developer.setPosition(Developer.Position.Junior);
            }if(position.equals("Middle"))
            {
                developer.setPosition(Developer.Position.Middle);
            }if(position.equals("Senior"))
            {
                developer.setPosition(Developer.Position.Senior);
            }
            if(thisElement.equals("ProgrammingLanguage"))
            {
                developer.setProgLAng(new String(ch, start, length));
                developers.add(developer);
            }
        }
    }
    @Override
    public void endDocument()
    {
        System.out.println("Stop parse XML...");
    }

}
