package com.github.Hanselmito.Test;

import com.github.Hanselmito.Conection.ConnectionProperties;
import com.github.Hanselmito.Utils.XMLmanager;

public class SaveConection {
    public static void main(String[] args) {
        ConnectionProperties c = new ConnectionProperties("localhost","3306","wikimonsterhunter","root","");
        XMLmanager.writeXML(c,"Connection.xml");
    }
}
