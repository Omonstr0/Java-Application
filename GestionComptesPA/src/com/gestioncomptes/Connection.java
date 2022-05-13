package com.gestioncomptes;

import java.sql.*;

public class Connection {

    public java.sql.Connection c;
    public Statement s;

    public Connection() {
        try {
            c = DriverManager.getConnection("jdbc:mysql://?:?", "?", "?");
            s = c.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
