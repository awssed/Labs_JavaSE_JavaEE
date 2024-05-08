package DAO;

import java.sql.SQLException;

public interface IConnection {
    public Boolean getConnection()throws SQLException;
    public void CloseConnection() throws SQLException;
}
