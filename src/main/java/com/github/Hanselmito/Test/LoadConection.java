package com.github.Hanselmito.Test;

import com.github.Hanselmito.Conection.ConnectionProperties;
import com.github.Hanselmito.Utils.XMLmanager;

import javax.xml.bind.JAXBException;

public class LoadConection {
    public static void main(String[] args) {
        ConnectionProperties c = XMLmanager.readXML(new ConnectionProperties(), "Connection.xml");
        System.out.println(c);
    }
}