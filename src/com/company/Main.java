package com.company;

import java.sql.*;

public class Main {

    public static void main(String[] args) {
        Connection conn = null;
        try {
            String url = "jdbc:mysql://localhost:3306/notebook?user=root";
            conn = DriverManager.getConnection(url);

            System.out.println("got it! Connected to DB!");

            Statement stmt = null;
            String query = "select * from textentry";
            try {
                stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    int id = rs.getInt("Id");
                    String comment = rs.getString("comment");
                    String date = rs.getDate("date").toString();
                    String note = rs.getString("note");
                    System.out.println(id + "\n" + comment + "\n" + date + "\n" + note);
                }
            } catch (SQLException e) {
                throw new Error("Problem", e);
            } finally {
                if (stmt != null) {stmt.close(); }
            }

        } catch (SQLException e) {
            throw new Error("Problem", e);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
