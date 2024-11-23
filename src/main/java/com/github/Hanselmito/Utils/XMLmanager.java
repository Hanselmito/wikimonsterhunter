package com.github.Hanselmito.Utils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class XMLmanager {
    public static <T> boolean writeXML(T c, String filename) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(c.getClass());
        Marshaller m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        m.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
        m.marshal(c, new File(filename));
        return true;
    }

    public static <T> T readXML(T c, String filename) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(c.getClass());
        Unmarshaller um = context.createUnmarshaller();
        return (T) um.unmarshal(new File(filename));
    }
}