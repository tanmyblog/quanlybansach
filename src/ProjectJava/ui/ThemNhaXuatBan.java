/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProjectJava.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

/**
 *
 * @author CONG TAO
 */
public class ThemNhaXuatBan extends JDialog {

    JTextField txtMaNXB, txtTenNXB, txtDiaChi, txtDienThoai;
    JButton btnLuu;

    Connection conn = QuanLyNXBUI.conn;
    Statement statement = QuanLyNXBUI.statement;

    public String maschon = "";

    public static int ketqua = -1;

    public ThemNhaXuatBan(String title) {
        this.setTitle(title);
        addControls();
        addEvents();
        if (maschon.length() != 0) {
            hienThiThongTinChiTiet();

        }
    }

    public void hienThiThongTinChiTiet() {
        try {
            String sql = "select * from nhaxuatban where ID = '" + maschon + "'";
            statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            if (result.next()) {
                txtMaNXB.setText(result.getString(1));
                txtTenNXB.setText(result.getString(2));
                txtDiaChi.setText(result.getString(3) + "");
                txtDienThoai.setText(result.getString(4) + "");
            }
            txtMaNXB.setEditable(false);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void addEvents() {
        btnLuu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                xuLyLuu();
            }

            private void xuLyLuu() {
                if (maschon.length() == 0) {
                    try {
                        String sql = "insert into nhaxuatban values('" + txtMaNXB.getText() + "','" + txtTenNXB
                                .getText() + "','" + txtDiaChi.getText() + "','" + txtDienThoai.getText() + "')";
                        statement = conn.createStatement();
                        int x = statement.executeUpdate(sql);
                        ketqua = x;
                        dispose();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                } else {
                    try {
                        String sql = "update nhaxuatban set tenNXB = '" + txtTenNXB.getText()
                                + "',diachi='" + txtDiaChi.getText()
                                + "',dienthoai='" + txtDienThoai.getText()
                                + "'where ID = '" + txtMaNXB.getText() + "'";
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

//        btnLuu.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                xuLyLuu();
//            }
//            
//            protected void xuLyLuu() {
//                try{
//                    String sql = "insert into nhaxuatban values('"+txtMaNXB.getText()
//                            +"',N'"+txtTenNXB.getText()+"',N'"+txtDiaChi.getText()+"','"+txtDienThoai.getText()+"')";
//                    statement = conn.createStatement();
//                    int x = statement.executeUpdate(sql);
//                    ketqua=x;
//                    dispose();
//                }
//                catch(Exception ex){
//                    ex.printStackTrace();
//                }
//            }
//        });
    }

    private void addControls() {
        Container con = getContentPane();
        con.setLayout(new BoxLayout(con, BoxLayout.Y_AXIS));

        JPanel pnNorth = new JPanel();
        con.add(pnNorth, BorderLayout.NORTH);
        pnNorth.setLayout(new BorderLayout());
        JPanel pnChiTiet = new JPanel();
        pnNorth.add(pnChiTiet, BorderLayout.CENTER);

        pnChiTiet.setLayout(new BoxLayout(pnChiTiet, BoxLayout.Y_AXIS));
        JPanel pnNXB = new JPanel();
        JLabel lblNXB = new JLabel("THÔNG TIN NHÀ XUẤT BẢN");
        lblNXB.setForeground(Color.ORANGE);
        Font ft = new Font("segoe ui", Font.BOLD, 24);
        lblNXB.setFont(ft);
        pnNXB.add(lblNXB);
        pnChiTiet.add(pnNXB);
        //con.add(pnNXB);

        //ma nha xuat ban
        JPanel pnMaNXB = new JPanel();
        JLabel lblMaNXB = new JLabel("Mã nxb:");
        txtMaNXB = new JTextField(25);
        pnMaNXB.add(lblMaNXB);
        pnMaNXB.add(txtMaNXB);
        pnChiTiet.add(pnMaNXB);
        //con.add(pnMaNXB);

        //ten nha xuat ban
        JPanel pnTenNXB = new JPanel();
        JLabel lblTenNXB = new JLabel("Tên nxb:");
        txtTenNXB = new JTextField(25);
        pnTenNXB.add(lblTenNXB);
        pnTenNXB.add(txtTenNXB);
        pnChiTiet.add(pnTenNXB);
        //con.add(pnTenNXB);

        //dia chi
        JPanel pnDiaChi = new JPanel();
        JLabel lblDiaChi = new JLabel("Địa chỉ:");
        txtDiaChi = new JTextField(25);
        pnDiaChi.add(lblDiaChi);
        pnDiaChi.add(txtDiaChi);
        pnChiTiet.add(pnDiaChi);
        //con.add(pnDiaChi);

        //dien thoai
        JPanel pnDienThoai = new JPanel();
        JLabel lblDienThoai = new JLabel("Điện thoại:");
        txtDienThoai = new JTextField(25);
        pnDienThoai.add(lblDienThoai);
        pnDienThoai.add(txtDienThoai);
        pnChiTiet.add(pnDienThoai);
        //con.add(pnDienThoai);

        //luu
        JPanel pnButtonLuu = new JPanel();
        btnLuu = new JButton("Thêm NXB");
        pnButtonLuu.add(btnLuu);
        pnChiTiet.add(pnButtonLuu);
        //con.add(pnButtonLuu);

        //chieu dai
        lblMaNXB.setPreferredSize(lblDienThoai.getPreferredSize());
        lblTenNXB.setPreferredSize(lblDienThoai.getPreferredSize());
        lblDiaChi.setPreferredSize(lblDienThoai.getPreferredSize());

        //Ke khung
        TitledBorder borderThongTinChiTiet = new TitledBorder(BorderFactory.createLineBorder(Color.GRAY), "Thông tin chi tiết");
        pnChiTiet.setBorder(borderThongTinChiTiet);

    }

    public void showWindow() {
        this.setSize(550, 260);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setModal(true);
        this.setVisible(true);
    }
}
