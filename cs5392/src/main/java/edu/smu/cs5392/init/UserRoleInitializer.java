package edu.smu.cs5392.init;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Component
class UserProvisioningRunner implements CommandLineRunner {

    private final JdbcTemplate jdbcTemplate;
    private final BCryptPasswordEncoder passwordEncoder;

    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Value("${spring.datasource.username}")
    private String dbUsername;

    @Value("${spring.datasource.password}")
    private String dbPassword;

    public UserProvisioningRunner(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.passwordEncoder = new BCryptPasswordEncoder(12); // Password encoding with 12 rounds
    }

    @Override
    public void run(String... args) throws Exception {
        provisionUsers();
    }

    @SuppressWarnings({ "null", "deprecation" })
    public void provisionUsers() {
        // Users data
        Object[][] users = {
            {"Laura", "Simson", "Admin", "laura.simson@abc.com", "laura", "laura@123"},
            {"Huang", "Yang", "Admin", "huang.yang@abc.com", "huang", "huang@123"},
            {"Smith", "Johnson", "Admin", "smith.johnson@abc.com", "smith", "smith@123"},
            {"Micah", "Adrene", "Admin", "micah.adrene@abc.com", "micah", "micah@123"},
            {"Azim", "Mohammed", "Operator", "azim.mohammed@abc.com", "azim", "azim@123"},
            {"Suraj", "Patel", "Operator", "suraj.patel@abc.com", "suraj", "suraj@123"},
            {"Lima", "Dunken", "Operator", "lima.dunken@abc.com", "lima", "lima@123"},
            {"Alice", "Troff", "Operator", "alice.troff@abc.com", "alice", "alice@123"},
            {"Logan", "Kroner", "Operator", "logan.kroner@abc.com", "logan", "logan@123"},
            {"Kate", "Briggs", "Operator", "kate.briggs@abc.com", "kate", "kate@123"}
        };

        // SQL queries
        String checkUserExistsSQL = "SELECT ID FROM User WHERE UserName = ?";
        String insertUserSQL = "INSERT INTO User (UserName, Password, FirstName, LastName, Email, Type) VALUES (?, ?, ?, ?, ?, ?)";
        //String findRoleSQL = "SELECT ID FROM Role WHERE name = ?";
        String insertUserRoleSQL = "INSERT INTO UserRoles (user_id, role_id) VALUES (?, ?)";
        
        for (Object[] user : users) {
            // Check if the user already exists
            long userId = -1;
            try {
                userId = jdbcTemplate.queryForObject(checkUserExistsSQL, new Object[]{user[4]}, Long.class);
                System.out.println("User " + user[4] + " already exists with ID: " + userId);
                continue; // Skip if user already exists
            } catch (Exception e) {
                // User does not exist, proceed with insertion
            }

            // Hash the password
            String hashedPassword = passwordEncoder.encode((String) user[5]);

            // Insert the user into the User table
            jdbcTemplate.update(insertUserSQL, user[4], hashedPassword, user[0], user[1], user[3], user[2]);

            // Retrieve the generated user ID
            userId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class);

            // Find the role ID for the user's type
            //long roleId = jdbcTemplate.queryForObject(findRoleSQL, new Object[]{user[2]}, Long.class);
            long roleId = -1;

            System.out.println("Role is" + user[2].toString());
            if (user[2].toString().compareToIgnoreCase("Admin") == 0) {
                roleId = 1;
            }
            else if (user[2].toString().compareToIgnoreCase("operator") == 0) {
                roleId = 2;
            }   

            // Insert the user-role relationship into UserRoles
            jdbcTemplate.update(insertUserRoleSQL, userId, roleId);
 
            System.out.println("User " + user[4] + " provisioned successfully.");
        }
    }

    public void deleteUser(String username) {
        String getUserIdSQL = "SELECT ID FROM User WHERE UserName = ?";
        String deleteUserRoleSQL = "DELETE FROM UserRoles WHERE user_id = ?";
        String deleteUserSQL = "DELETE FROM User WHERE ID = ?";

        try (@SuppressWarnings("null")
        Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            // Find the user ID
            long userId = -1;
            try (PreparedStatement getUserStmt = connection.prepareStatement(getUserIdSQL)) {
                getUserStmt.setString(1, username);
                try (ResultSet rs = getUserStmt.executeQuery()) {
                    if (rs.next()) {
                        userId = rs.getLong("ID");
                    } else {
                        System.out.println("User " + username + " does not exist.");
                        return;
                    }
                }
            }

            // Delete user-role relationships
            try (PreparedStatement deleteRoleStmt = connection.prepareStatement(deleteUserRoleSQL)) {
                deleteRoleStmt.setLong(1, userId);
                deleteRoleStmt.executeUpdate();
            }

            // Delete the user
            try (PreparedStatement deleteUserStmt = connection.prepareStatement(deleteUserSQL)) {
                deleteUserStmt.setLong(1, userId);
                deleteUserStmt.executeUpdate();
            }

            System.out.println("User " + username + " and associated roles deleted successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

