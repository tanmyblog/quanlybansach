/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProjectJava.ui;

import ProjectJava.model.ThanhVien;
import ProjectJava.service.UserService;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
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
 * @author TanMy
 */
public class TimThanhVienUI extends JFrame{
    JTextField txtTim;
    JButton btnBatDauTim;
    DefaultTableModel dtmUser;
    JTable tblUser;
    
    Connection conn = QuanLyThanhVienUI.conn;
    Statement statement = QuanLyThanhVienUI.statement;
    
    public TimThanhVienUI(String title){
        super(title);
        addControls();
        addEvents();
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
                UserService uservice = new UserService();
              
                ArrayList<ThanhVien> dsUser = uservice.timThanhVien(Integer.parseInt(txtTim.getText()));
                dtmUser.setRowCount(0);
                for(ThanhVien u: dsUser){
                    Vector<Object> vec = new Vector<Object>();
                    vec.add(u.getIDUser());
                    vec.add(u.getFullName());
                    vec.add(u.getEmail());
                    vec.add(u.getPhone());
                    vec.add(u.getAddress());
                    dtmUser.addRow(vec);
                }
            }
        });
    }

    private void addControls() {
        Container con = getContentPane();
        con.setLayout(new BorderLayout());
        JPanel pnNorth = new JPanel();
        pnNorth.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel lblNhap = new JLabel("Bạn hãy nhập id user để tìm: ");
        txtTim = new JTextField(15);
        btnBatDauTim = new JButton("Tìm kiếm");
        pnNorth.add(lblNhap);
        pnNorth.add(txtTim);
        pnNorth.add(btnBatDauTim);
        con.add(pnNorth,BorderLayout.NORTH);
        
        JPanel pnCenter = new JPanel();
        pnCenter.setLayout(new BorderLayout());
        dtmUser = new DefaultTableModel();
        dtmUser.addColumn("Id user");
        dtmUser.addColumn("Họ tên");
        dtmUser.addColumn("Email");
        dtmUser.addColumn("SĐT");
        dtmUser.addColumn("Địa chỉ");
        tblUser = new JTable(dtmUser);
        JScrollPane scTable = new JScrollPane(tblUser,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        pnCenter.add(scTable,BorderLayout.CENTER);
        con.add(pnCenter,BorderLayout.CENTER);
    }
    
    public void showWindow(){
        this.setSize(700,300);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
    }
}
