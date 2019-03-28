/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProjectJava.ui;

import ProjectJava.db.DB;
import ProjectJava.service.MySqlService;
import com.mysql.jdbc.Driver;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author CONG TAO
 */
public class QuanLyNXBUI extends JFrame{
    
    DefaultTableModel dtmNXB;
    JTable tbNXB;
    
    int i =0;
    
    public static Connection conn = null;
    public static Statement statement =null;
    JButton btnThem,btnSua,btnXoa,btnTimKiem;
    
    
    
    public QuanLyNXBUI(String title) throws SQLException{
        super(title);
        addControls();
        addEvents();
        //ketNoiCSDLMySQL();
        conn = DB.ketNoiCSDLMySQL();
        hienThiToanBoNXB();
    }
    
    private void hienThiToanBoNXB(){
        try{
            String sql = "select * from nhaxuatban";
            statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            dtmNXB.setRowCount(0);
            ResultSetMetaData rsmd = result.getMetaData();
            i = rsmd.getColumnCount();
            while (result.next()) {
                Vector<Object> vec = new Vector<Object>();
                for (int j = 1; j <= i; j++) {
                    vec.add(result.getString(j));
                }

                dtmNXB.addRow(vec);
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
   /* private void MySqlService() {
         try{
             String strlConn = "jdbc:mysql://localhost/csdlQuanLySach?useUnicode=true&characterEncoding=utf-8";
             Properties pro = new Properties();
             pro.put("user","root");
             pro.put("password","");
             Driver driver = new Driver();
             conn=driver.connect(strlConn, pro);
         }
         catch(Exception ex){
             ex.printStackTrace();
         }
    }*/
    
//    private void ketNoiCSDLMySQL() {
//         try{
//             String strlConn = "jdbc:mysql://localhost/csdlQuanLySach?useUnicode=true&characterEncoding=utf-8";
//             Properties pro = new Properties();
//             pro.put("user","root");
//             pro.put("password","");
//             Driver driver = new Driver();
//             conn=driver.connect(strlConn, pro);
//         }
//         catch(Exception ex){
//             ex.printStackTrace();
//         }
//    }
    
    private void addEvents() {
        btnThem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ThemNhaXuatBan ui =new ThemNhaXuatBan("Thêm Thông Tin Nhà Xuất Bản");
                ui.showWindow();
                
                if(ThemNhaXuatBan.ketqua>0){
                    hienThiToanBoNXB();
                }
            }
        });
        btnSua.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int row = tbNXB.getSelectedRow();
                if(row==-1)
                    return;
                String ma = tbNXB.getValueAt(row, 0)+"";
                ThemNhaXuatBan ui = new ThemNhaXuatBan("Cập Nhật Thông Tin Nhà Xuất Bản");
                ui.maschon = ma;
                ui.hienThiThongTinChiTiet();
                ui.showWindow();
                
                if(ThemNhaXuatBan.ketqua>0){
                    hienThiToanBoNXB();
                }
            }
        });
        
        btnXoa.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String ma = tbNXB.getValueAt(tbNXB.getSelectedRow(), 0)+"";
                try{
                    String sql = "delete from nhaxuatban where ID = '"+ma+"'";
                    statement = conn.createStatement();
                    int x = statement.executeUpdate(sql);
                    if(x>0){
                        hienThiToanBoNXB();
                    }
                }
                catch(Exception ex){
                    ex.printStackTrace();
                }
            }
        });
        
        btnTimKiem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TimKiemUI ui = new TimKiemUI("Tìm kiếm thông tin sách");
                ui.showWindow();
            }
        });
    }
    
    private void addControls() {
       Container con = getContentPane();
       con.setLayout(new BorderLayout());
       
        JPanel pnNorth = new JPanel();

        JPanel pnCenter = new JPanel();
        JPanel pnSouth = new JPanel();
        pnSouth.setLayout(new FlowLayout(FlowLayout.LEFT));
        con.add(pnNorth,BorderLayout.NORTH);
        con.add(pnCenter,BorderLayout.CENTER);
        con.add(pnSouth,BorderLayout.SOUTH);
        pnNorth.setLayout(new BorderLayout());
        JPanel pnChiTiet = new JPanel();
        pnNorth.add(pnChiTiet,BorderLayout.CENTER);
        JPanel pnThucHien = new JPanel();
        pnNorth.add(pnThucHien,BorderLayout.EAST);
        
        pnChiTiet.setLayout(new BoxLayout(pnChiTiet, BoxLayout.Y_AXIS));
        JPanel pnNXB = new JPanel();
        JLabel lblNXB = new JLabel("THÔNG TIN NHÀ XUẤT BẢN");
        lblNXB.setForeground(Color.ORANGE);
        Font ft = new Font("segoe ui", Font.BOLD, 24);
        lblNXB.setFont(ft);
        pnNXB.add(lblNXB);
        pnChiTiet.add(pnNXB);
         
        
//        JLabel lblTitle = new JLabel("Chuong trinh quan ly sach");
//        lblTitle.setFont(new Font("segoe ui",Font.BOLD,20));
//        pnNorth.add(lblTitle);
//        con.add(pnNorth,BorderLayout.NORTH);

        //cot phia tren ben phai
         pnThucHien.setLayout(new BoxLayout(pnThucHien, BoxLayout.Y_AXIS));
         //them
         JPanel pnButtonThem = new JPanel();
         btnThem = new JButton("Thêm");
         btnThem.setPreferredSize(new Dimension(100,40));
         pnButtonThem.add(btnThem);
         pnThucHien.add(pnButtonThem);
         
         //sua
         JPanel pnButtonSua = new JPanel();
         btnSua= new JButton("Sửa");
         btnSua.setPreferredSize(new Dimension(100,40));
         pnButtonSua.add(btnSua);
         pnThucHien.add(pnButtonSua);
         
         //xoa
         JPanel pnButtonXoa = new JPanel();
         btnXoa = new JButton("Xoá");
         btnXoa.setPreferredSize(new Dimension(100,40));
         pnButtonXoa.add(btnXoa);
         pnThucHien.add(pnButtonXoa);
        
        pnCenter.setLayout(new BorderLayout());
        dtmNXB = new DefaultTableModel();
        dtmNXB.addColumn("Mã NXB");
        dtmNXB.addColumn("Tên NXB");
        dtmNXB.addColumn("Địa chỉ");
        dtmNXB.addColumn("Điện thoại");
  
        tbNXB = new JTable(dtmNXB);
        
        JScrollPane scTable = new JScrollPane(
                tbNXB,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS
        );
        pnCenter.add(scTable,BorderLayout.CENTER);
//        con.add(scTable,BorderLayout.CENTER);
        
        //Ke khung
         TitledBorder borderThongTinChiTiet = new 
            TitledBorder(BorderFactory.createLineBorder(Color.GRAY),"Mục");
         pnChiTiet.setBorder(borderThongTinChiTiet);
         
         TitledBorder borderThucHien = new 
            TitledBorder(BorderFactory.createLineBorder(Color.GRAY),"Thực hiện");
         pnThucHien.setBorder(borderThucHien);
         
         TitledBorder borderDanhSachNXB = new 
            TitledBorder(BorderFactory.createLineBorder(Color.GRAY),"Danh sách nhà xuất bản");
         pnCenter.setBorder(borderDanhSachNXB);
         
         
         
         
         //nut tim kiem
         JPanel pnButtonOfSouth = new JPanel();
         pnButtonOfSouth.setLayout(new FlowLayout(FlowLayout.LEFT));
         btnTimKiem = new JButton("Tìm Kiếm");
         pnButtonOfSouth.add(btnTimKiem);
         pnSouth.add(pnButtonOfSouth);
         
         btnThem.setIcon(new ImageIcon("img\\them.png"));
         btnSua.setIcon(new ImageIcon("img\\sua.png"));
         btnXoa.setIcon(new ImageIcon("img\\xoa.png"));
         btnTimKiem.setIcon(new ImageIcon("img\\timkiem.png"));
    }

    public void showWindow(){
        this.setSize(800,600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
