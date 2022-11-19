package pl.coderslab.entity;

import org.mindrot.jbcrypt.BCrypt;
import pl.coderslab.DbUtil;

import java.sql.*;

public class UserDao {
    private static final String CREATE_USER_QUERY = "INSERT INTO workshop2.users(email, username, password) VALUES (?, ?,?)";
    private static final String READ_USER_QUERY = "Select * from workshop2.users where id = ?";
    private static final String UPDATE_USER_QUERY = "UPDATE workshop2.users set ? = ? WHERE id = ?";
    private static final String DELETE_USER_QUERY = "DELETE FROM workshop2.users where id = ?";
    private static final String FINDALL_USER_QUERY = "Select * from workshop2.users";

    public static void main(String[] args) {

    }

    public static User create(User user) {
        try (Connection connection = DbUtil.connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_USER_QUERY, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getUserName());
            preparedStatement.setString(3, hashPassword(user.getPassword()));
            preparedStatement.executeUpdate();
            try(ResultSet rs = preparedStatement.getGeneratedKeys()) {
                if (rs.next()) {
                    int id = rs.getInt(1);
                    user.setId(id);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
    public User read(int userId){
        User user = new User();
    try(Connection connection = DbUtil.connect()){
        PreparedStatement statement = connection.prepareStatement(READ_USER_QUERY);
        statement.setInt(1, userId);
        try(ResultSet rs = statement.executeQuery()) {
            while (rs.next()){
               // System.out.println(rs.getString("username") + " " + rs.getString("email") + " " + rs.getString("password"));
            user.setId(userId);
            user.setUserName(rs.getString("username"));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));
        }
        }
    }catch (SQLException e){
        e.printStackTrace();
    }
    return user;
    }

    public static String hashPassword(String password){
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
}
