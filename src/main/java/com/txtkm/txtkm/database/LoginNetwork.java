package com.txtkm.txtkm.database;

import com.txtkm.txtkm.exceptions.UserNotFoundException;
import com.txtkm.txtkm.utility.Utility;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

public class LoginNetwork {
    private String username;
    private String password;

    public LoginNetwork(String username, String password) throws NoSuchAlgorithmException {
        this.username = username;
        this.password = password;
        this.hashPassword();
    }

    public int checkValidLogin() throws SQLException, ClassNotFoundException, UserNotFoundException {
        Connection connection = DatabaseConnection.getConnection();
        String query = "select * from usertable where email = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, username);
        ResultSet rs = statement.executeQuery();
        if (!rs.next()) {
            return -1;
        }
        String password = rs.getString("password");
        String token = rs.getString("token");
        LoginPersistence.getPersistence().setPrefs(token);
        System.out.println(LoginPersistence.getPersistence().getPrefs());
        if (password.equals(this.password)) {
            ProfileBuilder builder = new ProfileBuilder(new Profile());
            Utility.profile = builder.setId(rs.getInt("id")).populateData().build();
            return 1;
        } else {
            return 0;
        }
    }

    private void hashPassword() throws NoSuchAlgorithmException {
        this.password = toHexString(getSHA(this.password));
        System.out.println(password);
    }

    private static byte[] getSHA(String input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        return md.digest(input.getBytes(StandardCharsets.UTF_8));
    }

    private static String toHexString(byte[] hash) {
        // Convert byte array into signum representation
        BigInteger number = new BigInteger(1, hash);

        // Convert message digest into hex value
        StringBuilder hexString = new StringBuilder(number.toString(16));

        // Pad with leading zeros
        while (hexString.length() < 32) {
            hexString.insert(0, '0');
        }

        return hexString.toString();
    }
}
