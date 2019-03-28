/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProjectJava.service;

import com.mysql.jdbc.Driver;
import java.sql.Connection;
import java.util.Properties;

/**
 *
 * @author CONG TAO
 */
public class MySqlService {
    protected Connection conn;
    public MySqlService(){
        try{
             String strlConn = "jdbc:mysql://localhost/csdlquanlysach?useUnicode=true&characterEncoding=utf-8";
             Properties pro = new Properties();
             pro.put("user","root");
             pro.put("password","admin");
             Driver driver = new Driver();
             conn = driver.connect(strlConn, pro);
         }
         catch(Exception ex){
             ex.printStackTrace();
         }
    }
}
