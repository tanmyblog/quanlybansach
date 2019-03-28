/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProjectJava.service;

import ProjectJava.model.ThanhVien;
import static ProjectJava.ui.QuanLyThanhVienUI.conn;
import com.sun.istack.internal.FinalArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author TanMy
 */
public class UserService {
    public ArrayList<ThanhVien> timThanhVien(int iduser) {
        ArrayList<ThanhVien> dsUser = new FinalArrayList<ThanhVien>();
        try {
            String sql = "SELECT * FROM `user` WHERE `IDUser` = ?";
            PreparedStatement preStatement = conn.prepareStatement(sql);
            preStatement.setInt(1, iduser);
            ResultSet result = preStatement.executeQuery();
            while (result.next()) {
                ThanhVien u = new ThanhVien();
                u.setIDUser(result.getInt(1));
                u.setFullName(result.getString(2));
                u.setEmail(result.getString(3));
                u.setPhone(result.getString(4));
                u.setAddress(result.getString(5));
                dsUser.add(u);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return dsUser;
    }
}
