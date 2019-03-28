/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProjectJava.ui;

import ProjectJava.model.ThanhVien;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.border.TitledBorder;

/**
 *
 * @author TanMy
 */
public class ThemThanhVienUI extends JDialog {

    JTextField txtIDUser, txtUserName, txtPassWord, txtFullName, txtEmail, txtAddress;
    JTextField txtPhone = new JTextField(11);
    JButton btnLuu;
    String tenBtn = "Thực Hiện";

    Connection conn = QuanLyThanhVienUI.conn;
    Statement statement = QuanLyThanhVienUI.statement;

    public String maschon = "";
    public static int ketqua = -1;

    public ThemThanhVienUI(String title) throws SQLException {
        this.setTitle(title);
        addControls();
        addEvents();
    }

    public void hienThiThongTinChiTiet() {
        try {
            String sql = "SELECT * FROM `user` WHERE `IDUSer` = '" + maschon + "'";
            statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            if (result.next()) {
                txtIDUser.setText(result.getString(1));
                txtFullName.setText(result.getString(2));
                txtEmail.setText(result.getString(3));
                txtPhone.setText(result.getString(4));
                txtAddress.setText(result.getString(5));
            }
            txtIDUser.setEditable(false);
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
                String phone = txtPhone.getText().trim();
                String email = txtEmail.getText().trim();
                if (email.isEmpty() || !valEmail(email)) {
                    JOptionPane.showMessageDialog(null, "Email không hợp lệ hoặc rỗng");
                } else if (phone.isEmpty() || !valPhone(phone)) {
                    JOptionPane.showMessageDialog(null, "Số điện thoại không hợp lệ hoặc rỗng");
                } else {
                    if (maschon.length() == 0) {
                        try {
                            String sql = "INSERT INTO `user`(`FullName`, `Email`, `Phone`, `Address`) VALUES('" + txtFullName.getText() + "','" + email.toLowerCase() + "','" + phone + "','" + txtAddress.getText() + "')";
                            statement = conn.createStatement();
                            ketqua = statement.executeUpdate(sql);
                            dispose();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    } else {
                        try {
                            String sql = "UPDATE `user` SET "
                                    + "`FullName` = '" + txtFullName.getText() + "', "
                                    + "`Email` = '" + txtEmail.getText() + "', "
                                    + "`Phone` = '" + txtPhone.getText() + "',"
                                    + "`Address` = '" + txtAddress.getText() + "' "
                                    + " WHERE `IDUser` = '" + txtIDUser.getText() + "'";
                            statement = conn.createStatement();
                            ketqua = statement.executeUpdate(sql);
                            dispose();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                }

            }
        });
    }

/*    Phương thức matches() trong Java xác định có hay không chuỗi này so khớp (match) với regular expression đã cho. 
 *    Một dẫn chứng của phương thức này là form dạng str.matches(regex) 
 *    biến đổi chính xác kết quả giống như trong Pattern.matches(regex, str).
 */
    
    private boolean valPhone(String phone) {
        String intRegex = "^[0-9][0-9]{9}$";
        Pattern phonePat = Pattern.compile(intRegex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = phonePat.matcher(phone);
        return matcher.find();
    }

    private boolean valEmail(String email) {
        String emailRegex = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
        Pattern emailPat = Pattern.compile(emailRegex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = emailPat.matcher(email);
        return matcher.find();
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
        JPanel pnUser = new JPanel();
        JLabel lblSach = new JLabel("THÔNG TIN THÀNH VIÊN");
        lblSach.setForeground(Color.BLUE);
        Font ft = new Font("Arial", Font.BOLD, 24);
        lblSach.setFont(ft);
        pnUser.add(lblSach);
        pnChiTiet.add(pnUser);

        JPanel pnMa = new JPanel();
        pnMa.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel lblMa = new JLabel("IDUser: ");
        txtIDUser = new JTextField(35);
        txtIDUser.setEditable(false);
        pnMa.add(lblMa);
        pnMa.add(txtIDUser);
        pnChiTiet.add(pnMa);

        JPanel pnFullName = new JPanel();
        pnFullName.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel lblFullName = new JLabel("Họ Tên: ");
        txtFullName = new JTextField(35);
        pnFullName.add(lblFullName);
        pnFullName.add(txtFullName);
        pnChiTiet.add(pnFullName);

        JPanel pnEmail = new JPanel();
        pnEmail.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel lblEmail = new JLabel("Email: ");
        txtEmail = new JTextField(35);
        pnEmail.add(lblEmail);
        pnEmail.add(txtEmail);
        pnChiTiet.add(pnEmail);

        JPanel pnPhone = new JPanel();
        pnPhone.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel lblPhone = new JLabel("SĐT: ");
        txtPhone = new JTextField(35);
        pnPhone.add(lblPhone);
        pnPhone.add(txtPhone);
        pnChiTiet.add(pnPhone);

        JPanel pnAddress = new JPanel();
        pnAddress.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel lblAddress = new JLabel("Địa chỉ: ");
        txtAddress = new JTextField(35);
        pnAddress.add(lblAddress);
        pnAddress.add(txtAddress);
        pnChiTiet.add(pnAddress);

        JPanel pnButton = new JPanel();
        pnButton.setLayout(new FlowLayout(FlowLayout.CENTER));

        btnLuu = new JButton(tenBtn);
        pnButton.add(btnLuu);
        pnChiTiet.add(pnButton);

        lblMa.setPreferredSize(lblFullName.getPreferredSize());
        lblEmail.setPreferredSize(lblFullName.getPreferredSize());
        lblPhone.setPreferredSize(lblFullName.getPreferredSize());
        lblAddress.setPreferredSize(lblFullName.getPreferredSize());

        TitledBorder borderThongTinChiTiet = new TitledBorder(BorderFactory.createLineBorder(Color.GRAY), "Thông tin chi tiết");
        pnChiTiet.setBorder(borderThongTinChiTiet);

    }

    public void showWindow() {
        this.setSize(550, 450);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setModal(true);
        this.setVisible(true);
    }
}
