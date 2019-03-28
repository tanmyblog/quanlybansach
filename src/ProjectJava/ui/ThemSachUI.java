/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProjectJava.ui;

import ProjectJava.model.CBONXBData;
import java.awt.BorderLayout;
import java.awt.Color;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.border.TitledBorder;

/**
 *
 * @author Phat
 */
public class ThemSachUI extends JDialog {

    JTextField txtIDSach, txtTenSach, txtSoTrang;
    JButton btnLuu;

    JComboBox cboNXB;

    String tenBtn = "Xác nhận";

    Connection conn = QuanLySachUI.conn;
    Statement statement = QuanLySachUI.statement;

    public String maschon = "";

    public static int ketqua = -1;

    public ThemSachUI(String title) throws SQLException {
        this.setTitle(title);
        addControls();
        addEvents();
//        if(maschon.length()!=0){
//            hienThiThongTinChiTiet();
//            
//        }
    }

    public void hienThiThongTinChiTiet() {
        try {
            String sql = "select * from sach where IDSach = '" + maschon + "'";
            statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            if (result.next()) {
                txtIDSach.setText(result.getString(1));
                txtTenSach.setText(result.getString(2));
                cboNXB.setSelectedItem(result.getString(3));
                txtSoTrang.setText(result.getString(4));
            }
            txtIDSach.setEditable(false);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void addEvents() {
        btnLuu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                xuLyThem();
            }

            private void xuLyThem() {

                CBONXBData obj = (CBONXBData) cboNXB.getSelectedItem();

                if (maschon.length() == 0) {
                    try {

                        String sql = "insert into sach values('" + txtIDSach.getText() + "','" + txtTenSach.getText() + "','" + obj.getID() + "','" + txtSoTrang.getText() + "')";
                        statement = conn.createStatement();
                        int x = statement.executeUpdate(sql);
                        ketqua = x;
                        dispose();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                } else {
                    try {

                        String sql = "update sach set tensach = '" + txtTenSach.getText()
                                + "',IDNhaXuatBan='" + obj.getID()
                                + "',sotrang='" + txtSoTrang.getText()
                                + "'where IDSach = '" + txtIDSach.getText() + "'";
                        statement = conn.createStatement();
                        int x = statement.executeUpdate(sql);
                        ketqua = x;
                        dispose();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
    }

    public void getDataCBO(JComboBox cbo) throws SQLException {
        String sql = "select ID, TenNXB from nhaxuatban";
        statement = conn.createStatement();
        ResultSet result = statement.executeQuery(sql);

        while (result.next()) {

            cbo.addItem(new CBONXBData(result.getInt(1), result.getString(2)));

        }

    }

    private void addControls() throws SQLException {
        Container con = getContentPane();
        con.setLayout(new BoxLayout(con, BoxLayout.Y_AXIS));
        
        JPanel pnNorth = new JPanel();
        con.add(pnNorth, BorderLayout.NORTH);
        pnNorth.setLayout(new BorderLayout());
        JPanel pnChiTiet = new JPanel();
        pnNorth.add(pnChiTiet, BorderLayout.CENTER);

        pnChiTiet.setLayout(new BoxLayout(pnChiTiet, BoxLayout.Y_AXIS));
        JPanel pnSach = new JPanel();
        JLabel lblSach = new JLabel("THÔNG TIN SÁCH");
        lblSach.setForeground(Color.RED);
        Font ft = new Font("segoe ui", Font.BOLD, 24);
        lblSach.setFont(ft);
        pnSach.add(lblSach);
        pnChiTiet.add(pnSach);

        JPanel pnMa = new JPanel();
        pnMa.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel lblMa = new JLabel("Mã sách: ");
        txtIDSach = new JTextField(20);
        pnMa.add(lblMa);
        pnMa.add(txtIDSach);
        con.add(pnMa);

        JPanel pnTen = new JPanel();
        pnTen.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel lblTen = new JLabel("Tên sách: ");
        txtTenSach = new JTextField(20);
        pnTen.add(lblTen);
        pnTen.add(txtTenSach);
        con.add(pnTen);

        JPanel pnNXB = new JPanel();
        pnNXB.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel lblNXB = new JLabel("Nhà xuất bản: ");

        cboNXB = new JComboBox();
        getDataCBO(cboNXB);
        cboNXB.setBounds(50, 50, 90, 20);
        cboNXB.setSelectedIndex(0);

        pnNXB.add(lblNXB);
        pnNXB.add(cboNXB);
        con.add(pnNXB);

        JPanel pnSoTrang = new JPanel();
        pnSoTrang.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel lblSoTrang = new JLabel("Số trang: ");
        txtSoTrang = new JTextField(20);
        pnSoTrang.add(lblSoTrang);
        pnSoTrang.add(txtSoTrang);
        con.add(pnSoTrang);

        JPanel pnButton = new JPanel();
        pnButton.setLayout(new FlowLayout(FlowLayout.LEFT));

        btnLuu = new JButton(tenBtn);
        pnButton.add(btnLuu);
        con.add(pnButton);

        lblMa.setPreferredSize(lblNXB.getPreferredSize());
        lblTen.setPreferredSize(lblNXB.getPreferredSize());
        //cboNXB.setPreferredSize(lblSoTrang.getPreferredSize());
        lblSoTrang.setPreferredSize(lblNXB.getPreferredSize());

        TitledBorder borderThongTinChiTiet = new TitledBorder(BorderFactory.createLineBorder(Color.GRAY), "Thông tin chi tiết");
        pnChiTiet.setBorder(borderThongTinChiTiet);

    }

    public void showWindow() {
        this.setSize(500, 400);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setModal(true);
        this.setVisible(true);
    }
}
