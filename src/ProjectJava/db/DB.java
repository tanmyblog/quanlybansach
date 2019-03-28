/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProjectJava.db;

import com.mysql.jdbc.Driver;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author Phat
 */
public class DB {
    
    public static Connection ketNoiCSDLMySQL() throws SQLException {
        String strlConn = "jdbc:mysql://localhost/csdlquanlysach?useUnicode=true&characterEncoding=utf-8";
        Properties pro = new Properties();
        pro.put("user","root");
        pro.put("password","admin");
        Driver driver = new Driver();
        Connection conn = driver.connect(strlConn, pro);
        return conn;
    }
}
