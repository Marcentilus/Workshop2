package pl.coderslab;

import java.sql.*;

public class DbUtil {
    private static final String DB_URL ="jdbc:mysql://localhost:3306/workshop2?useSSL=false&characterEncoding=utf8&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String DB_USER ="root";
    private static final String DB_PASSWORD = "coderslab";
    private static final String DELETE_QUERY = "DELETE FROM tableName where id = ?";
    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }
    public static void insert(Connection conn, String query, String... params){
        try(PreparedStatement statement = conn.prepareStatement(query)){
            for(int i = 0; i < params.length; i++){
                statement.setString(i +1, params[i]);
            }
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public static void printData(Connection conn, String query, String... columnNames) {
        try(PreparedStatement statement = conn.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();){
            while (resultSet.next()){
                for (String columnName: columnNames){
                    System.out.print(resultSet.getString(columnName)+ " ");
                }
                System.out.println();
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public static void remove(Connection conn, String tableName, int id){
        try(PreparedStatement statement = conn.prepareStatement(DELETE_QUERY.replace("tableName", tableName));){
            statement.setInt(1,id);
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public static int alterTable(Connection conn, String query, String columnName, int id){
        int result = 0;
        try(PreparedStatement statement = conn.prepareStatement(query);){
            statement.setInt(2, id);
            statement.setString(1,columnName);
            statement.executeUpdate();
            result = statement.getUpdateCount();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return  result;
    }



}
