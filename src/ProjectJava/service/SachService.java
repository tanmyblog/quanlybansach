/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProjectJava.service;

import ProjectJava.model.Sach;
import static ProjectJava.ui.QuanLySachUI.conn;
import static ProjectJava.ui.QuanLySachUI.statement;
import com.sun.istack.internal.FinalArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author CONG TAO
 */
public class SachService extends MySqlService {

    

    public ArrayList<Sach> timSachTheoNhaXuatBan(int manxb) {
        ArrayList<Sach> dsSach = new FinalArrayList<Sach>();
        try {
            String sql = "select * from sach where IDNhaXuatBan = ?";
            PreparedStatement preStatement = conn.prepareStatement(sql);
            preStatement.setInt(1, manxb);
            ResultSet result = preStatement.executeQuery();
            while (result.next()) {
                Sach s = new Sach();
                s.setIDSach(result.getString(1));
                s.setTensach(result.getString(2));
                s.setNxb(result.getInt(3));
                s.setSotrang(result.getInt(4));
                dsSach.add(s);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return dsSach;
    }
}
