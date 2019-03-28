/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProjectJava.ui;

import ProjectJava.model.Sach;
import ProjectJava.service.SachService;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author CONG TAO
 */
public class TimKiemUI extends JFrame{
    
    JTextField txtTim;
    JButton btnBatDauTim;
    DefaultTableModel dtmSach;
    JTable tblSach;
    
    Connection conn = QuanLySachUI.conn;
    Statement statement = QuanLySachUI.statement;
    
    public TimKiemUI(String title){
        super(title);
        addControls();
        addEvents();
    }
    
    public String tenNXB(int ID) throws SQLException {
        String rs = "";
        String sql = "select TenNXB from nhaxuatban where ID ="+ID;
        statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            
        if(result.next()){
            rs = result.getString(1);
        }
        
        return rs;
    }
    
    private void addEvents() {
        btnBatDauTim.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    xuLyTimKiem();
                } catch (SQLException ex) {
                    Logger.getLogger(TimKiemUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            private void xuLyTimKiem() throws SQLException {
                SachService sservice = new SachService();
              
                ArrayList<Sach>dsSach = sservice.timSachTheoNhaXuatBan(Integer.parseInt(txtTim.getText()));
                dtmSach.setRowCount(0);
                for(Sach s: dsSach){
                    Vector<Object> vec = new Vector<Object>();
                    vec.add(s.getIDSach());
                    vec.add(s.getTensach());
                    vec.add(tenNXB(s.getNxb()));
                    vec.add(s.getSotrang());
                    dtmSach.addRow(vec);
                }
            }
        });
    }
    


    private void addControls() {
        Container con = getContentPane();
        con.setLayout(new BorderLayout());
        JPanel pnNorth = new JPanel();
        pnNorth.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel lblNhap = new JLabel("Bạn hãy nhập mã nxb để tìm sách: ");
        txtTim = new JTextField(15);
        btnBatDauTim = new JButton("Tìm kiếm");
        pnNorth.add(lblNhap);
        pnNorth.add(txtTim);
        pnNorth.add(btnBatDauTim);
        con.add(pnNorth,BorderLayout.NORTH);
        
        JPanel pnCenter = new JPanel();
        pnCenter.setLayout(new BorderLayout());
        dtmSach = new DefaultTableModel();
        dtmSach.addColumn("Mã sách");
        dtmSach.addColumn("Tên sách");
        dtmSach.addColumn("Nhà xuất bản");
        dtmSach.addColumn("Số trang");
        tblSach = new JTable(dtmSach);
        JScrollPane scTable = new JScrollPane(tblSach,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        pnCenter.add(scTable,BorderLayout.CENTER);
        con.add(pnCenter,BorderLayout.CENTER);
    }
    
    public void showWindow(){
        this.setSize(500,600);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
    }
}
