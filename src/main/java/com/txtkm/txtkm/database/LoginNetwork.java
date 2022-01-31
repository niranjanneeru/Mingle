package com.txtkm.txtkm.database;

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

    public int checkValidLogin(Connection connection) throws SQLException {
        String query = "select * from usertable where email = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, username);
        ResultSet rs = statement.executeQuery();
        if (!rs.next()) {
            return -1;
        }
        String password = rs.getString("password");
        if (password.equals(this.password)) {
            return 1;
        } else {
            return 0;
        }
    }

    private void hashPassword() throws NoSuchAlgorithmException {
        this.password = toHexString(getSHA(this.password));
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
