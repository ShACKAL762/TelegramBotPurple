package Other;

import java.sql.*;

public class MySql {

    private Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String host = "localhost";
        String port = "3306";
        String db_name = "purpleCup_db";
        String connStr = "jdbc:mysql://" + host + ":" + port + "/" + db_name;
        // Если OpenServer, то здесь mysql напишите
        String login = "shackal";
        // Если OpenServer, то здесь mysql напишите
        String password = "61zytu220po";
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(connStr, login, password);

            System.out.println("Connected");
        return conn;
        }
        catch(Exception e)
        {
            System.out.println(e);
        }



        return null;
    }
    public void insert(String firstName, String lastName, String userName, long chatId) throws SQLException, ClassNotFoundException {

        String insert = "INSERT INTO " + Const.table + "(" + Const.firstName+ ","+ Const.lastName +","+ Const.userName + "," + Const.chaId + ")" + "VALUES(?,?,?,?)";
        try (PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert)) {
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, userName);
            preparedStatement.setLong(4, chatId);


            preparedStatement.executeUpdate();
        }catch (SQLException e){
            System.out.println(e);
        }
        getDbConnection().close();
    }
    public boolean select(long chatId) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = null;
        String select = "SELECT * FROM " + Const.table +  " WHERE " + Const.chaId + " =?";
        try {
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(select);
        preparedStatement.setLong(1,  chatId);


       resultSet = preparedStatement.executeQuery();
       while (resultSet.next()) {
           /*
           System.out.println("User Success");
           System.out.println(resultSet.getLong("chatId"));
           System.out.println(resultSet.getString(1));
            */
           resultSet.close();
           return true;

       }
        }catch (SQLException e){
            System.out.println(e);
        }

        assert resultSet != null;
        resultSet.close();
        return false;
    }
    public void UPDATE_NUMBER(long chatId, String number) throws SQLException, ClassNotFoundException {
        String update = "UPDATE " + Const.table + " SET " + Const.phoneNumber + " =? WHERE " + Const.chaId + " =?";
        String select = "SELECT " + Const.phoneNumber + " FROM " + Const.table + " WHERE " + Const.phoneNumber + " =?";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(select);
            preparedStatement.setString(1,  number);
            ResultSet res = preparedStatement.executeQuery();
            if (!res.next()) {
                PreparedStatement updateSt = getDbConnection().prepareStatement(update);
                updateSt.setString(1, number);
                updateSt.setLong(2, chatId);

                updateSt.executeUpdate();
            }else System.out.println("Номер зарегестрирован");
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        getDbConnection().close();
    }
    public String COUNT() throws SQLException, ClassNotFoundException {

        String select = "SELECT COUNT(*) " +
                "FROM " + Const.table;
        try {
            Statement statement = getDbConnection().createStatement();
            statement.executeQuery(select);
            statement.getResultSet();
            ResultSet res = statement.getResultSet();
            res.next();
            int count = res.getInt(1);
            statement.close();
            return "Количество зарегестрированных пользователей: " + count;


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    public String GETNUMBER(long chatId) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = null;
        String select = "SELECT * FROM " + Const.table + " WHERE " + Const.chaId + " =?";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(select);
            preparedStatement.setLong(1, chatId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                if (resultSet.getString(Const.phoneNumber) != null) {
                    String number = resultSet.getString(Const.phoneNumber);
                    resultSet.close();
                    return number;
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        assert resultSet != null;
        resultSet.close();
        return null;
    }
    public String GETNAME(long chatId) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = null;
        String select = "SELECT * FROM " + Const.table + " WHERE " + Const.chaId + " =?";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(select);
            preparedStatement.setLong(1, chatId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                if (resultSet.getString(Const.firstName) != null) {
                    String name = resultSet.getString(Const.firstName);
                    resultSet.close();
                    return name;
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        assert resultSet != null;
        resultSet.close();
        return null;
    }

}




