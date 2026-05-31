package tampilan;

import koneksi.koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class login extends JFrame {

    private Connection conn = new koneksi().connect();

    JLabel l1 = new JLabel("LOGIN");
    JLabel l2 = new JLabel("Username");
    JLabel l3 = new JLabel("Password");

    JTextField txtuser = new JTextField();
    JPasswordField txtpass = new JPasswordField();

    JButton blogin = new JButton("LOGIN");

    public login(){

        setTitle("Form Login");
        setSize(350,250);
        setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        l1.setBounds(140,20,100,30);

        l2.setBounds(40,70,100,25);
        txtuser.setBounds(120,70,150,25);

        l3.setBounds(40,110,100,25);
        txtpass.setBounds(120,110,150,25);

        blogin.setBounds(120,160,100,30);

        add(l1);
        add(l2);
        add(txtuser);
        add(l3);
        add(txtpass);
        add(blogin);

        blogin.addActionListener(e -> login());

    }

    private void login(){

        try{

            String sql =
            "SELECT * FROM kasir16 WHERE nm_kasir=? AND password=?";

            PreparedStatement stat = conn.prepareStatement(sql);

            stat.setString(1, txtuser.getText());
            stat.setString(2, txtpass.getText());

            ResultSet rs = stat.executeQuery();

            if(rs.next()){

                String id = rs.getString("id_kasir");

                // simpan id kasir login
                UserID.setUserLogin(id);

                JOptionPane.showMessageDialog(null,
                        "Login Berhasil");

                new dashboard().setVisible(true);

                dispose();

            } else {

                JOptionPane.showMessageDialog(null,
                        "Username / Password Salah");

            }

        } catch(Exception e){

            JOptionPane.showMessageDialog(null,
                    "Error : " + e);

        }

    }

    public static void main(String[] args) {

        new login().setVisible(true);

    }
}