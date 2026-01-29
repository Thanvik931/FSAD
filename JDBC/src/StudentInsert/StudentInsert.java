package StudentInsert;

import java.sql.*;

public class StudentInsert {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/show_prk";  // Your DB
        String username = "root";      // MySQL username
        String password = "Anusha@961";  // MySQL password
        
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            
            // Data to insert
            int rollno = 161;
            String name = "Sama Thanvik Reddy";
            
            String sql = "INSERT INTO students (rollno, name) VALUES (?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, rollno);
            pstmt.setString(2, name);
            
            int rows = pstmt.executeUpdate();
            System.out.println(rows + " row(s) inserted. RollNo: " + rollno + ", Name: " + name);
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}