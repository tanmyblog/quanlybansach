/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProjectJava.ui;

import com.mysql.jdbc.Driver;
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
import java.sql.Statement;
import java.util.Properties;
import java.util.Vector;
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

import ProjectJava.db.DB;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Phat
 */
public class QuanLySachUI extends JFrame {

    int i = 0;

    DefaultTableModel dtmSach;
    JTable tbSach;

    public static Connection conn = null;
    public static Statement statement = null;

    JButton btnThem, btnLuu, btnSua, btnXoa, btnTimKiem;

    public QuanLySachUI(String title) throws SQLException {

        super(title);

        conn = DB.ketNoiCSDLMySQL();
        addControls();
        addEvents();
        hienThiToanBoSach();
    }

    private void hienThiToanBoSach() {
        try {
            String sql = "select IDSach, tensach, TenNXB, sotrang from sach a, nhaxuatban b where a.IDNhaXuatBan = b.ID";
            statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            dtmSach.setRowCount(0);
            ResultSetMetaData rsmd = result.getMetaData();
            i = rsmd.getColumnCount();
            while (result.next()) {
                Vector<Object> vec = new Vector<Object>();
                for (int j = 1; j <= i; j++) {
                    vec.add(result.getString(j));
                }

                dtmSach.addRow(vec);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void addEvents() {
        btnThem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ThemSachUI ui;
                try {
                    ui = new ThemSachUI("Mục");
                    ui.showWindow();
                } catch (SQLException ex) {
                    Logger.getLogger(QuanLySachUI.class.getName()).log(Level.SEVERE, null, ex);
                }

                if (ThemSachUI.ketqua > 0) {
                    hienThiToanBoSach();
                }
            }
        });
        btnSua.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int row = tbSach.getSelectedRow();
                if (row == -1) {
                    return;
                }
                String ma = tbSach.getValueAt(row, 0) + "";
                ThemSachUI ui = null;
                try {
                    ui = new ThemSachUI("CẬP NHẬT THÔNG TIN SÁCH");
                    ui.maschon = ma;
                    ui.hienThiThongTinChiTiet();
                    ui.showWindow();
                } catch (SQLException ex) {
                    Logger.getLogger(QuanLySachUI.class.getName()).log(Level.SEVERE, null, ex);
                }

                if (ThemSachUI.ketqua > 0) {
                    hienThiToanBoSach();
                }
            }
        });
        btnXoa.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String ma = tbSach.getValueAt(tbSach.getSelectedRow(), 0) + "";
                try {
                    String sql = "delete from sach where IDSach = '" + ma + "'";
                    statement = conn.createStatement();
                    int x = statement.executeUpdate(sql);
                    if (x > 0) {
                        hienThiToanBoSach();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
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
        JPanel pnSach = new JPanel();
        JLabel lblSach = new JLabel("THÔNG TIN SÁCH");
        lblSach.setForeground(Color.RED);
        Font ft = new Font("segoe ui", Font.BOLD, 24);
        lblSach.setFont(ft);
        pnSach.add(lblSach);
        pnChiTiet.add(pnSach);

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
        dtmSach = new DefaultTableModel();
        dtmSach.addColumn("MÃ SÁCH");
        dtmSach.addColumn("TÊN SÁCH");
        dtmSach.addColumn("NHÀ XUẤT BẢN");
        dtmSach.addColumn("SỐ TRANG");

        tbSach = new JTable(dtmSach);

        JScrollPane scTable = new JScrollPane(
                tbSach, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS
        );
        pnCenter.add(scTable, BorderLayout.CENTER);
//        con.add(scTable,BorderLayout.CENTER);

        //Ke khung
        TitledBorder borderThongTinChiTiet = new TitledBorder(BorderFactory.createLineBorder(Color.GRAY), "Mục");
        pnChiTiet.setBorder(borderThongTinChiTiet);

        TitledBorder borderThucHien = new TitledBorder(BorderFactory.createLineBorder(Color.GRAY), "Thực hiện");
        pnThucHien.setBorder(borderThucHien);

        TitledBorder borderDanhSachNXB = new TitledBorder(BorderFactory.createLineBorder(Color.GRAY), "Danh sách các đầu sách");
        pnCenter.setBorder(borderDanhSachNXB);

        btnThem.setIcon(new ImageIcon("img\\them.png"));
        btnSua.setIcon(new ImageIcon("img\\sua.png"));
        btnXoa.setIcon(new ImageIcon("img\\xoa.png"));

        //nut tim kiem


//        
//       
//       
    }

    public void showWindow() {
        this.setSize(800, 600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
