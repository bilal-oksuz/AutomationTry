package com.Common.Clients;

import java.sql.*;
import java.util.List;

public class MSSQL {
    private  final String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private Connection connection;

    public MSSQL()
    {

    }

    public MSSQL(String url)
    {
        setConnection(url);
    }

    public Connection getDbConnection(String url)
    {
        try
        {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(url);
            return connection;
        }

        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public void setConnection(String url)
    {
        this.connection = getDbConnection(url);
    }

    public void DownDbConnection(Connection conncetion)
    {
        try
        {
            conncetion.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public ResultSet ExecuteQuery(Connection connection,String query)
    {
        ResultSet rs=null;
        Statement stmt;
        try
        {
            stmt = connection.createStatement();
            rs = stmt.executeQuery(query);
        }
        catch (SQLException e) {
            e.printStackTrace();

        }
        return rs;
    }

    public List<String> Results(ResultSet resultSet)
    {
        List<String> result=null;
        try{
            while(resultSet.next()) {

                resultSet.getString("name");
                resultSet.getInt("age");
                resultSet.getBigDecimal("coefficient");

                // etc.
            }
            return result;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return result;
        }

    }

    public boolean insertData(Connection connection,String insert)
    {
        boolean success = false;
        try {
            Statement stmt = connection.createStatement();
            stmt.execute(insert);
            success=true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            success=false;
        }
        finally {
            return success;
        }
    }

    public boolean updateData(Connection connection,String query)
    {
        boolean success = false;
        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(query);
            success=true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            success=false;
        }
        finally {
            return success;
        }
    }


}