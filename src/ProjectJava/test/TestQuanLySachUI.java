/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProjectJava.test;

import ProjectJava.ui.QuanLyNXBUI;
import ProjectJava.ui.QuanLySachUI;
import ProjectJava.ui.QuanLyThanhVienUI;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;

/**
 *  
 * @author CONG TAO
 */
public class TestQuanLySachUI extends JFrame{
    
    public JTabbedPane createTabbed() throws SQLException{
        JTabbedPane tabbed;
        tabbed = new JTabbedPane();
        
        QuanLyNXBUI ui =new QuanLyNXBUI("Chương trình quản lý nhà xuất bản");
        QuanLySachUI sui = new QuanLySachUI("Quản lý các đầu sách");
        QuanLyThanhVienUI uui = new QuanLyThanhVienUI("Quản lý thành viên");
        
        tabbed.addTab("Nhà xuất bản", null, ui.getContentPane(),"Quản lý nhà xuất bản");
        tabbed.addTab("Sách", null, sui.getContentPane(),"Quản lý các đầu sách");
        tabbed.addTab("Thành viên", null, uui.getContentPane(),"Quản lý thành viên");
        
        return tabbed;
    }
    
    public void setUI() throws SQLException{
        add(createTabbed());
    }
    
    public void setDisplay(){
        this.setSize(900, 600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    
    public TestQuanLySachUI() throws SQLException{
        setUI();
        setDisplay();
    }
    
    public static void main(String[] args) throws SQLException{
        
        new TestQuanLySachUI();
        
    }
}
