package tampilan;

import java.util.HashMap;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import net.sf.jasperreports.engine.JRException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


import koneksi.koneksi;
import java.sql.Connection;



public class dashboard extends JFrame {

    JLabel title = new JLabel("DASHBOARD KASIR");

    JLabel luser = new JLabel();

    JButton bbarang = new JButton("ADD BARANG");
    JButton bpelanggan = new JButton("ADD PELANGGAN");
    JButton bnota = new JButton("NOTA");
    JButton bkasir = new JButton("ADD KASIR");
    JButton blaporan = new JButton("LAPORAN PENJUALAN");
    JButton blogout = new JButton("LOGOUT");

    // koneksi database
    Connection conn = new koneksi().connect();

    public dashboard(){

        setTitle("Dashboard");
        setSize(500,450);
        setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        title.setBounds(150,20,250,30);

        luser.setBounds(20,20,200,30);

        // tampilkan id kasir login
        luser.setText("ID LOGIN : " + UserID.getUserLogin());

        bbarang.setBounds(150,80,180,35);
        bpelanggan.setBounds(150,130,180,35);
        bnota.setBounds(150,180,180,35);
        bkasir.setBounds(150,230,180,35);

        // tombol laporan
        blaporan.setBounds(150,280,180,35);

        blogout.setBounds(150,330,180,35);

        add(title);
        add(luser);

        add(bbarang);
        add(bpelanggan);
        add(bnota);
        add(bkasir);

        add(blaporan);
        add(blogout);

        // BUTTON BARANG
        bbarang.addActionListener(e -> {

            new barang().setVisible(true);

        });

        // BUTTON PELANGGAN
        bpelanggan.addActionListener(e -> {

            new Pelanggan().setVisible(true);

        });

        // BUTTON NOTA
        bnota.addActionListener(e -> {

            new Nota().setVisible(true);

        });

        // BUTTON KASIR
        bkasir.addActionListener(e -> {

            new kasir().setVisible(true);

        });

        // BUTTON LAPORAN
        
        blaporan.addActionListener(e -> {

            try {

                String path =
                "./src/tampilan/LapPelanggan.jasper";

                HashMap parameter = new HashMap();

                JasperPrint print =
                JasperFillManager.fillReport(
                        path,
                        parameter,
                        conn
                );

                JasperViewer.viewReport(print, false);

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        null,
                        "Dokumen Tidak Ada \n" + ex
                );

            }

        });

        // BUTTON LOGOUT
        blogout.addActionListener(e -> {

            dispose();

            new login().setVisible(true);

        });

    }

    public static void main(String[] args) {

        new dashboard().setVisible(true);

    }

}