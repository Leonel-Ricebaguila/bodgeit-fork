package com.bodgeit.controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class LoginController {

    public boolean authenticateUser(Connection conn, String username, String password) {
        try {
            Statement stmt = conn.createStatement();
            
            // ESCENA DEL CRIMEN: Concatenación directa de strings (Vulnerabilidad R03)
            // Aquí es donde entra el payload: admin' OR '1'='1
            String query = "SELECT * FROM Users WHERE username = '" + username + 
                           "' AND password = '" + password + "'";
            
            ResultSet rs = stmt.executeQuery(query);
            
            if (rs.next()) {
                System.out.println("Login exitoso para el usuario: " + username);
                return true;
            }
        } catch (Exception e) {
            System.err.println("Error en la base de datos: " + e.getMessage());
        }
        return false;
    }
}