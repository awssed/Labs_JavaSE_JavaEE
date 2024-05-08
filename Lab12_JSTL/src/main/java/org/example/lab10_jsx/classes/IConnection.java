package org.example.lab10_jsx.classes;

import java.sql.Statement;
import java.util.ArrayList;

public interface IConnection {
    public Boolean getConnection();
    public void closeConnection();
    public Statement getStatment();
}
