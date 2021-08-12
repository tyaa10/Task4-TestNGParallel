package util;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import model.RozetkaFilters;

/* import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller; */
import java.io.File;

public class XMLtoObject {

    public RozetkaFilters convert() {

        try {
            File file = new File( "src/main/resources/rozetkaFilters.xml");

            JAXBContext jaxbContext = JAXBContext.newInstance(RozetkaFilters.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            return (RozetkaFilters) jaxbUnmarshaller.unmarshal(file);

        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }
}