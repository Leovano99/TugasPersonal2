import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class PasienForm extends JFrame {
    private JTextField tfNama, tfAlamat, tfNIK, tfTanggalLahir;
    private JButton btnTambah, btnUpdate, btnHapus, btnPrev, btnNext, btnDaftar;
    private ArrayList<Pasien> pasienList = new ArrayList<>();
    private int currentIndex = -1;

    public PasienForm() {
        setTitle("Klinik - Manajemen Pasien");
        setSize(500, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(5, 2));

        formPanel.add(new JLabel("Nama:"));
        tfNama = new JTextField(20);
        formPanel.add(tfNama);

        formPanel.add(new JLabel("Alamat:"));
        tfAlamat = new JTextField(50);
        formPanel.add(tfAlamat);

        formPanel.add(new JLabel("NIK:"));
        tfNIK = new JTextField(15);
        formPanel.add(tfNIK);

        formPanel.add(new JLabel("Tanggal Lahir (YYYY-MM-DD):"));
        tfTanggalLahir = new JTextField(10);
        formPanel.add(tfTanggalLahir);

        add(formPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();

        btnTambah = new JButton("Tambah");
        btnTambah.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tambahPasien();
            }
        });

        btnUpdate = new JButton("Update");
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updatePasien();
            }
        });

        btnHapus = new JButton("Hapus");
        btnHapus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hapusPasien();
            }
        });

        btnPrev = new JButton("<<");
        btnPrev.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prevPasien();
            }
        });

        btnNext = new JButton(">>");
        btnNext.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nextPasien();
            }
        });

        btnDaftar = new JButton("Daftar Pasien");
        btnDaftar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tampilkanDaftarPasien();
            }
        });

        buttonPanel.add(btnTambah);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnHapus);
        buttonPanel.add(btnPrev);
        buttonPanel.add(btnNext);
        buttonPanel.add(btnDaftar);

        add(buttonPanel, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void tambahPasien() {
        try {
            String nama = tfNama.getText();
            String alamat = tfAlamat.getText();
            long NIK = Long.parseLong(tfNIK.getText());
            Date tanggalLahir = new SimpleDateFormat("yyyy-MM-dd").parse(tfTanggalLahir.getText());

            for (Pasien pasien : pasienList) {
                if (pasien.getNIK() == NIK) {
                    JOptionPane.showMessageDialog(this, "Pasien dengan NIK yang sama sudah ada!");
                    return;
                }
            }

            Pasien pasienBaru = new Pasien(nama, alamat, NIK, tanggalLahir);
            pasienList.add(pasienBaru);
            currentIndex = pasienList.size() - 1;
            JOptionPane.showMessageDialog(this, "Data pasien berhasil ditambahkan.");
        } catch (NumberFormatException | ParseException e) {
            JOptionPane.showMessageDialog(this, "Data yang dimasukkan tidak valid!");
        }
    }

    private void updatePasien() {
        if (currentIndex >= 0 && currentIndex < pasienList.size()) {
            try {
                String nama = tfNama.getText();
                String alamat = tfAlamat.getText();
                long NIK = Long.parseLong(tfNIK.getText());
                Date tanggalLahir = new SimpleDateFormat("yyyy-MM-dd").parse(tfTanggalLahir.getText());

                for (Pasien pasien : pasienList) {
                    if (pasien.getNIK() == NIK && pasien != pasienList.get(currentIndex)) {
                        JOptionPane.showMessageDialog(this, "Pasien dengan NIK yang sama sudah ada!");
                        return;
                    }
                }

                Pasien pasienUpdate = pasienList.get(currentIndex);
                pasienUpdate.setNama(nama);
                pasienUpdate.setAlamat(alamat);
                pasienUpdate.setNIK(NIK);
                pasienUpdate.setTanggalLahir(tanggalLahir);
                JOptionPane.showMessageDialog(this, "Data pasien berhasil diperbarui.");
            } catch (NumberFormatException | ParseException e) {
                JOptionPane.showMessageDialog(this, "Data yang dimasukkan tidak valid!");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Tidak ada data pasien yang dipilih.");
        }
    }

    private void hapusPasien() {
        if (currentIndex >= 0 && currentIndex < pasienList.size()) {
            pasienList.remove(currentIndex);
            JOptionPane.showMessageDialog(this, "Data pasien berhasil dihapus.");
            bersihkanForm();
            currentIndex = -1;
        } else {
            JOptionPane.showMessageDialog(this, "Tidak ada data pasien yang dipilih.");
        }
    }

    private void prevPasien() {
        if (currentIndex > 0) {
            currentIndex--;
            tampilkanPasien(pasienList.get(currentIndex));
        }
    }

    private void nextPasien() {
        if (currentIndex < pasienList.size() - 1) {
            currentIndex++;
            tampilkanPasien(pasienList.get(currentIndex));
        }
    }

    private void tampilkanPasien(Pasien pasien) {
        tfNama.setText(pasien.getNama());
        tfAlamat.setText(pasien.getAlamat());
        tfNIK.setText(String.valueOf(pasien.getNIK()));
        tfTanggalLahir.setText(new SimpleDateFormat("yyyy-MM-dd").format(pasien.getTanggalLahir()));
    }

    private void bersihkanForm() {
        tfNama.setText("");
        tfAlamat.setText("");
        tfNIK.setText("");
        tfTanggalLahir.setText("");
    }

    private void tampilkanDaftarPasien() {
        StringBuilder sb = new StringBuilder();
        sb.append("Nomor\tNama Pasien\tNIK\tTanggal Lahir\tAlamat\n");
        for (int i = 0; i < pasienList.size(); i++) {
            Pasien pasien = pasienList.get(i);
            sb.append((i + 1) + "\t" + pasien.getNama() + "\t" + pasien.getNIK() + "\t" + new SimpleDateFormat("yyyy-MMM-dd").format(pasien.getTanggalLahir()) + "\t" + pasien.getAlamat() + "\n");
        }
        JOptionPane.showMessageDialog(this, sb.toString(), "Daftar Pasien", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        new PasienForm();
    }
}

