/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package ProjectJava.ui;

import ProjectJava.db.DB;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author TanMy
 */
public class QuanLyThanhVienUI extends JFrame {
    
    DefaultTableModel dtmUSER;
    JTable tbUSER;
    int i = 0;
    
    public static Connection conn = null;
    public static Statement statement = null;
    JButton btnThem, btnSua, btnXoa, btnTimKiem;
    
    public QuanLyThanhVienUI(String title) throws SQLException {
        super(title);
        addControls();
        addEvents();
        conn = DB.ketNoiCSDLMySQL();
        hienThiThanhVien();
    }
    
    private void hienThiThanhVien() {
        try {
            String sql = "SELECT `IDUser`, `FullName`,`Email`,`Phone`,`Address` FROM `user` ORDER BY `IDUser` DESC";
            statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            dtmUSER.setRowCount(0);
            ResultSetMetaData rsmd = result.getMetaData(); //  lấy thông tin như tổng số cột, tên cột, kiểu của cột, … trong một bảng
            i = rsmd.getColumnCount();
            while (result.next()) {
                Vector<Object> vec = new Vector<Object>(); // Lớp Vector trong Java triển khai một mảng động. Nó tương tự như ArrayList
                for (int j = 1; j <= i; j++) {
                    vec.add(result.getString(j));
                }
                dtmUSER.addRow(vec);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private void addEvents() {
        btnThem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ThemThanhVienUI ui = null;
                try {
                    ui = new ThemThanhVienUI("Thêm Thông Tin Thành Viên");
                } catch (SQLException ex) {
                    Logger.getLogger(QuanLyThanhVienUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                ui.showWindow();
                
                if (ThemThanhVienUI.ketqua > 0) {
                    hienThiThanhVien();
                }
            }
        });
        btnSua.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int row = tbUSER.getSelectedRow();
                if (row == -1) {
                    return;
                }
                String ma = tbUSER.getValueAt(row, 0) + "";
                ThemThanhVienUI ui = null;
                try {
                    ui = new ThemThanhVienUI("Cập Nhật Thông Tin Thành Viên");
                } catch (SQLException ex) {
                    Logger.getLogger(QuanLyThanhVienUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                ui.maschon = ma;
                ui.hienThiThongTinChiTiet();
                ui.showWindow();
                
                if (ThemThanhVienUI.ketqua > 0) {
                    hienThiThanhVien();
                }
            }
        });
        btnXoa.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String ma = tbUSER.getValueAt(tbUSER.getSelectedRow(), 0) + "";
                try {
                    String sql = "DELETE FROM `user` WHERE `IDUser` = '" + ma + "'";
                    statement = conn.createStatement();
                    int x = statement.executeUpdate(sql);
                    if (x > 0) {
                        hienThiThanhVien();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        
        btnTimKiem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TimThanhVienUI ui = new TimThanhVienUI("Tìm kiếm thông tin thành viên");
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
        con.add(pnNorth, BorderLayout.NORTH);
        con.add(pnCenter, BorderLayout.CENTER);
        con.add(pnSouth, BorderLayout.SOUTH);
        pnNorth.setLayout(new BorderLayout());
        JPanel pnChiTiet = new JPanel();
        pnNorth.add(pnChiTiet, BorderLayout.CENTER);
        JPanel pnThucHien = new JPanel();
        pnNorth.add(pnThucHien, BorderLayout.EAST);
        
        pnChiTiet.setLayout(new BoxLayout(pnChiTiet, BoxLayout.Y_AXIS));
        JPanel pnNXB = new JPanel();
        JLabel lblNXB = new JLabel("THÔNG TIN THÀNH VIÊN");
        lblNXB.setForeground(Color.BLUE);
        Font ft = new Font("lato", Font.BOLD, 24);
        lblNXB.setFont(ft);
        pnNXB.add(lblNXB);
        pnChiTiet.add(pnNXB);
        
        //cot phia tren ben phai
        pnThucHien.setLayout(new BoxLayout(pnThucHien, BoxLayout.Y_AXIS));
        //them
        JPanel pnButtonThem = new JPanel();
        btnThem = new JButton("Thêm");
        btnThem.setPreferredSize(new Dimension(100, 40));
        pnButtonThem.add(btnThem);
        pnThucHien.add(pnButtonThem);
        
        //sua
        JPanel pnButtonSua = new JPanel();
        btnSua = new JButton("Sửa");
        btnSua.setPreferredSize(new Dimension(100, 40));
        pnButtonSua.add(btnSua);
        pnThucHien.add(pnButtonSua);
        
        //xoa
        JPanel pnButtonXoa = new JPanel();
        btnXoa = new JButton("Xoá");
        btnXoa.setPreferredSize(new Dimension(100, 40));
        pnButtonXoa.add(btnXoa);
        pnThucHien.add(pnButtonXoa);
        
        pnCenter.setLayout(new BorderLayout());
        dtmUSER = new DefaultTableModel();
        dtmUSER.addColumn("ID");
        dtmUSER.addColumn("Họ Tên");
        dtmUSER.addColumn("Email");
        dtmUSER.addColumn("Điện thoại");
        dtmUSER.addColumn("Địa chỉ");
        
        tbUSER = new JTable(dtmUSER);
        
        JScrollPane scTable = new JScrollPane(
                tbUSER, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS
        );
        pnCenter.add(scTable, BorderLayout.CENTER);
//        con.add(scTable,BorderLayout.CENTER);

        // tạo khung của các panel
        TitledBorder borderThongTinChiTiet = new TitledBorder(BorderFactory.createLineBorder(Color.GRAY), "Mục");
        pnChiTiet.setBorder(borderThongTinChiTiet);

        TitledBorder borderThucHien = new TitledBorder(BorderFactory.createLineBorder(Color.GRAY), "Thực hiện");
        pnThucHien.setBorder(borderThucHien);

        TitledBorder borderDanhSachUser = new TitledBorder(BorderFactory.createLineBorder(Color.GRAY), "Danh sách thành viên");
        pnCenter.setBorder(borderDanhSachUser);

        //nut tim kiem
        JPanel pnButtonOfSouth = new JPanel();
        pnButtonOfSouth.setLayout(new FlowLayout(FlowLayout.LEFT));
        btnTimKiem = new JButton("Tìm Kiếm");
        pnButtonOfSouth.add(btnTimKiem);
        pnSouth.add(pnButtonOfSouth);

        btnThem.setIcon(new ImageIcon("img//them.png"));
        btnSua.setIcon(new ImageIcon("img//sua.png"));
        btnXoa.setIcon(new ImageIcon("img//xoa.png"));
        btnTimKiem.setIcon(new ImageIcon("img//timkiem.png"));
    }
    
    public void showWindow() {
        this.setSize(800, 600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null); // giua man hinh
        this.setVisible(true);
    }
}
