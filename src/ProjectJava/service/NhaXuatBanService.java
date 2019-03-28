/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProjectJava.service;

import ProjectJava.model.NhaXuatBan;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author CONG TAO
 */
public class NhaXuatBanService extends MySqlService{
     public ArrayList<NhaXuatBan> layToanBoNhaXuatBan(){
        ArrayList<NhaXuatBan> dsNXB = new ArrayList<NhaXuatBan>();
        try{
            String sql = "select * from nhaxuatban";
            PreparedStatement preStatement = conn.prepareStatement(sql);
            ResultSet result = preStatement.executeQuery();
            while(result.next()){
                NhaXuatBan nxb = new NhaXuatBan();
                nxb.setID(result.getString(1));
                nxb.setTenNXB(result.getString(2));
                nxb.setDiaChi(result.getString(3));
                nxb.setDienThoai(result.getString(4));
                dsNXB.add(nxb);
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return dsNXB;
    }
}
