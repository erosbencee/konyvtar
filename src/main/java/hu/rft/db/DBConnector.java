/*
 * The MIT License
 *
 * Copyright 2017 University of Debrecen.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package hu.rft.db;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
/**
 *
 * @author Dani
 */
public class DBConnector {
    
    private Connection conn;
    
    public DBConnector() throws SQLException, ClassNotFoundException {
        
        Class.forName("com.mysql.jdbc.Driver");
       // conn = DriverManager.getConnection("jdbc:mysql://localhost/rft_konyvtar", "sysadmin", "sys4dmin");
        conn = DriverManager.getConnection("jdbc:mysql://25.78.139.144/rft_konyvtar", "sysadmin", "sys4dmin");
    }
    
    public void registerUser(String forename, String surname, String dob, String login, String pwd, String email) throws SQLException {
        
        //System.out.println("registerUser method start");
        
        String query;
        Statement st = conn.createStatement();
        ResultSet rs;
        int user_id;
        
        query = "SELECT MAX(USER_ID) maxuser FROM KVT_USER;";
        
        rs = st.executeQuery(query);
        
        //System.out.println("got max userid");
        
        rs.first();
        user_id = rs.getInt("maxuser") + 1;
        
        //System.out.println("User ID: " + user_id);
        
        PreparedStatement pst = conn.prepareStatement("INSERT INTO KVT_USER (USER_ID, FORENAME, SURNAME, DATE_OF_BIRTH, LOGIN_NAME, PASSWORD, EMAIL_ADDR, REGISTERED_ON, LAST_LOGIN) " +
                                                      "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)");
        
        pst.setString(1, Integer.toString(user_id));
        pst.setString(2, forename);
        pst.setString(3, surname);
        pst.setDate(4, Date.valueOf(LocalDate.parse(dob, DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
        pst.setString(5, login);
        pst.setString(6, pwd);
        pst.setString(7, email);
        pst.setDate(8, Date.valueOf(LocalDate.parse(LocalDate.now().toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
        pst.setDate(9, Date.valueOf(LocalDate.parse("4000-01-01", DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
        
        pst.executeUpdate();
        
        //System.out.println("table insert complete");
    }
    
    public boolean checkLogin(String login, String pwd) throws SQLException {
        
        PreparedStatement pst = conn.prepareStatement("SELECT COUNT(*) FROM KVT_USER WHERE LOGIN_NAME = ? AND PASSWORD = ?;");
        pst.setString(1, login);
        pst.setString(2, pwd);
        
        ResultSet rs = pst.executeQuery();
        rs.first();
        
        return rs.getInt(1) != 0;
    }
}
