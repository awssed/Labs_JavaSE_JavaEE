import by.safonov.IT_company.Director.Director;
import by.safonov.IT_company.Employee.Engeneer.Engeneer;
import by.safonov.IT_company.Employee.Developer.Developer;
import by.safonov.IT_company.Employee.*;
import by.safonov.IT_company.SaxDevelopers.SaxParser;
import by.safonov.IT_company.SaxDevelopers.SaxParser;
import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.swing.text.Position;
import javax.xml.parsers.SAXParserFactory;
import java.io.*;
import java.util.ArrayList;
import java.io.File;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {
    private static final Logger LOG = Logger.getLogger(Main.class);
    public static void main(String[] args) {
        try {
            //Чтение XML
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            SaxParser saxp=new SaxParser();
            parser.parse(new File("src\\developers.xml"),saxp);
            ArrayList<Developer> developers=saxp.getResult();
            for(Developer dev:developers)
            {
                System.out.println(dev.toString());
            }
            var writer= new FileWriter("JSON_Serialize\\serialize.json");
            ObjectMapper mapper=new ObjectMapper();
            String jsonDevelopers=mapper.writeValueAsString(developers);

            writer.write(jsonDevelopers,0,jsonDevelopers.length());
            writer.close();

            //Stream Api
            List<Developer> sortedDevelopers = developers.stream()
                    .sorted((dev1, dev2) -> Double.compare(dev2.getSalary(), dev1.getSalary()))
                    .toList();
            //десереализация
            FileReader reader = new FileReader("JSON_Serialize\\serialize.json");
            ArrayList<Developer> deserializedDevelopers = mapper.readValue(reader, new TypeReference<ArrayList<Developer>>() {});
            for(Developer dev:deserializedDevelopers)
            {
                System.out.println(dev.toString());
            }
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}