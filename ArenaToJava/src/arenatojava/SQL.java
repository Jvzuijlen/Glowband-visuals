/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arenatojava;

import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Frank
 */
public class SQL
{
    Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;
        
        public SQL()
        {
            try{
                myConn = DriverManager.getConnection("jdbc:mysql://studmysql01.fhict.local/dbi310878", "dbi310878", "Iie72-HD");
                myStmt = myConn.createStatement();     
            }
            catch(SQLException e){
                System.out.println(e);
            }
        }
        
        public Crowd getCrowdInfo()
        {
            Crowd crowd = null;
            
            try
            {
                String sqlQuery = "SELECT * FROM glowstick_crowd WHERE ID = 1";
                myRs = myStmt.executeQuery(sqlQuery);
                
                while(myRs.next())
                {
                    int width  = myRs.getInt("WIDTH");
                    int height = myRs.getInt("HEIGHT");
                    double NWLA = myRs.getDouble("NWLA");
                    double NWLO = myRs.getDouble("NWLO");
                    double SELA = myRs.getDouble("SELA");
                    double SELO = myRs.getDouble("SELO");
                    
                    crowd = new Crowd(width, height);
                    //crowd.NWlatitude = NWLA;
                    //crowd.NWlongitude = NWLO;
                    //crowd.SElatitude = SELA;
                    //crowd.SElongitude = SELO;
                    
                }
            }
            catch(SQLException e)
            {
                System.out.println(e);
            }
            return crowd;
        }
        
        public void dropTable()
        {
            try
            {
                String sqlQuery = "DROP TABLE IF EXISTS glowstick_visuals;";
                try (PreparedStatement pstmt = myConn.prepareStatement(sqlQuery))
                {
                    pstmt.execute();
                } 
            }
            catch(SQLException e)
            {
                System.out.println(e);
            }
        }
        
        public void createTable()
        {
            try
            {
                String sqlQuery = "CREATE TABLE glowstick_visuals(ID INT(10) PRIMARY KEY,X INT(10),Y INT(10), RED INT(10), GREEN INT(10), BLUE INT(10));";
                try (PreparedStatement pstmt = myConn.prepareStatement(sqlQuery))
                {
                    pstmt.execute();
                } 
            }
            catch(SQLException e)
            {
                System.out.println(e);
            }
        }
        
        public void updateColors(Color color, int id)
        {
            try
            {
                String sqlQuery = "UPDATE glowstick_visuals SET Red = ?, Green = ?, Blue = ? WHERE ID = ?;";
                try (PreparedStatement pstmt = myConn.prepareStatement(sqlQuery))
                {
                    pstmt.setInt(1, color.getRed());
                    pstmt.setInt(2, color.getGreen());
                    pstmt.setInt(3, color.getBlue());
                    pstmt.setInt(4, id);
                    pstmt.executeUpdate();
                } 
            }
            catch(SQLException e)
            {
                System.out.println(e);
            }
        }
        
        public void updateColors(Color[][] imgData, int width, int height)
        {
            try
            {
                int i = 1;
                for (int x = 0; x < width; x++)
                {
                    for (int y = 0; y < height; y++)
                    {
                        String sqlQuery = "INSERT INTO glowstick_visuals (ID, X, Y, Red, Green, Blue) VALUES (?, ?, ?, ?, ?, ?) "
                                + "ON DUPLICATE KEY UPDATE X = ?, Y = ?, Red = ?, Green = ?, Blue = ?;";
                        
                        try (PreparedStatement pstmt = myConn.prepareStatement(sqlQuery)) {
                            pstmt.setInt(1, i++);
                            pstmt.setInt(2, x);
                            pstmt.setInt(3, y);
                            
                            pstmt.setInt(4, imgData[x][y].getRed());
                            pstmt.setInt(5, imgData[x][y].getGreen());
                            pstmt.setInt(6, imgData[x][y].getBlue());
                            
                            pstmt.setInt(7, x);
                            pstmt.setInt(8, y);
                            pstmt.setInt(9, imgData[x][y].getRed());
                            pstmt.setInt(10, imgData[x][y].getGreen());
                            pstmt.setInt(11, imgData[x][y].getBlue());
                            pstmt.executeUpdate();
                        }
                    }
                }
            }
            catch(SQLException e)
            {
                System.out.println(e);
            }
        }
}
