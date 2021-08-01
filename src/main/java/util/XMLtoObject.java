package util;

import model.RozetkaFilters;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class XMLtoObject {

    public RozetkaFilters convert() {

        try {
            File file = new File( "src/main/resources/rozetkaFilters.xml");

            JAXBContext jaxbContext = JAXBContext.newInstance(RozetkaFilters.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            RozetkaFilters rozetkaFilters = (RozetkaFilters) jaxbUnmarshaller.unmarshal(file);
            return rozetkaFilters;

        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }
}