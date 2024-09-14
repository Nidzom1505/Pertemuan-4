/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pertemuan4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nidzzz
 */
public class DataBase {

    Connection conn;
    Statement stmt;
    PreparedStatement pstmt = null;

    String driver = "org.postgresql.Driver";
    String koneksi = "jdbc:postgresql://localhost:5432/pbo";
    String user = "postgres";
    String password = "nidzom15";
    InputStreamReader inputStreamReader = new InputStreamReader(System.in);
    BufferedReader input = new BufferedReader(inputStreamReader);

    public void tambah() {
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(koneksi, user, password);
            conn.setAutoCommit(false);

            String sql = "INSERT INTO santri VALUES(?,?,?,?)";
            pstmt = conn.prepareStatement(sql);

            boolean selesai = false;
            while (!selesai) {
                System.out.println("ISI DATA:");
                System.out.print("Nomor Induk Santri: ");
                String nis = input.readLine().trim();
                System.out.print("Nama : ");
                String nama = input.readLine().trim();
                System.out.print("Asal: ");
                String asal = input.readLine().trim();
                System.out.print("Tanggal Lahir: ");
                String tl = input.readLine().trim();

                pstmt.setString(1, nis);
                pstmt.setString(2, nama);
                pstmt.setString(3, asal);
                pstmt.setString(4, tl);
                pstmt.executeUpdate();

                System.out.print("Data Sudah Benar? ");
                String pilihan = input.readLine().trim();
                if (!pilihan.equalsIgnoreCase("belum")) {
                    selesai = true;
                }
            }

            conn.commit();
            pstmt.close();
            conn.close();
            System.out.println("Sukses diinput");
        } catch (ClassNotFoundException | SQLException | IOException ex) {
            System.out.println("Terjadi kesalahan saat input");
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException e) {
                System.out.println("Input Gagal");
            }
        }
    }

    public void tampil() {
        try {
            // TODO code application logi
            Class.forName(driver);
            String sql = "SELECT * FROM santri";
            conn = DriverManager.getConnection(koneksi, user, password);
            stmt = conn.createStatement();

            while (!conn.isClosed()) {
                ResultSet rs;
                rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    System.out.println(String.valueOf(rs.getObject(1)) + " | " + String.valueOf(rs.getObject(2)) + " | " + String.valueOf(rs.getObject(3)) + " | " + String.valueOf(rs.getObject(4)));
                }
                conn.close();
            }

            stmt.close();

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void hapus() {
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(koneksi, user, password);

            // Prompt the user for the dogtag of the record they want to delete
            System.out.print("Masukkan nis: ");
            String nis = input.readLine().trim();

            // Delete the record from the database
            String deleteSql = "DELETE FROM santri WHERE nis= ?";
            pstmt = conn.prepareStatement(deleteSql);
            pstmt.setString(1, nis);
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("NIS " + nis + " Berhasil dihapus!");
            } else {
                System.out.println("NIS" + nis + " tidak ada");
            }

            pstmt.close();
            conn.close();
        } catch (ClassNotFoundException | SQLException | IOException ex) {
            System.out.println("Cek Lagi!!!");
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void update() {
        try {
            Class.forName(driver);
            String sql = "UPDATE santri SET nama = ?, asal =?, tanggal_lahir = ? WHERE nis = ?";
            conn = DriverManager.getConnection(koneksi, user, password);
            pstmt = conn.prepareStatement(sql);

            System.out.print("Masukkan NIS: ");
            String nis = input.readLine().trim();
            System.out.print("Nama Santri: ");
            String nama = input.readLine().trim();
            System.out.print("Asal: ");
            String asal = input.readLine().trim();
            System.out.print("Tanggal lahir: ");
            String tl = input.readLine().trim();

            pstmt.setString(1, nama);
            pstmt.setString(2, asal);
            pstmt.setString(3, tl);
            pstmt.setString(4, nis);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Data berhasil diupdate.");
            } else {
                System.out.println("Data tidak ditemukan.");
            }

            pstmt.close();
            conn.close();
        } catch (ClassNotFoundException | SQLException | IOException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void menu() {
        boolean isRunning = true;  // Kontrol jalannya program

        while (isRunning) {
            System.out.println("\n========= MENU UTAMA =========");
            System.out.println("1. Input Data");
            System.out.println("2. Tampil Data");
            System.out.println("3. Hapus Data");
            System.out.println("4. Update Data");
            System.out.print("PILIHAN> ");

            try {
                int pilihan = Integer.parseInt(input.readLine());
                switch (pilihan) {
                    case 0:
                        isRunning = false;  // Keluar dari loop dan aplikasi berhenti
                        System.out.println("Terima kasih! Program selesai.");
                        break;
                    case 1:
                        tambah();
                        break;
                    case 2:
                        tampil();
                        break;
                    case 3:
                        hapus();
                        break;
                    case 4:
                        update();
                        break;
                    default:
                        System.out.println("Pilihan salah! Coba lagi.");
                }

                // Setelah menyelesaikan tugas, tanya apakah ingin kembali ke menu
                if (isRunning) {
                    System.out.print("Ingin kembali ke menu? (y/t): ");
                    String lanjut = input.readLine().toLowerCase();
                    if (!lanjut.equals("y")) {
                        isRunning = false;
                        System.out.println("Terima kasih!");
                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    public static void main(String[] args) {
        DataBase p = new DataBase();
        p.menu();
    }
}
