package com.github.Hanselmito.Test;

import com.github.Hanselmito.Conection.ConnectionProperties;
import com.github.Hanselmito.Utils.XMLmanager;

import javax.xml.bind.JAXBException;

public class SaveConection {
    public static void main(String[] args) {
        try {
            ConnectionProperties c = new ConnectionProperties("localhost", "3306", "wikimonsterhunter", "root", "");
            XMLmanager.writeXML(c, "Connection.xml");
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}