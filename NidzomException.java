/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pertemuan4;

/**
 *
 * @author Nidzzz
 */
public class NidzomException extends Exception {

    public NidzomException(String message) {
        super(message);
    }

    public static int cekHitungan(int angka1, int angka2) throws NidzomException {
        if (angka2 == 0) {
            throw new NidzomException("Angka Pembagi tidak boleh 0!");
        }
        return (int) angka1 / angka2;
    }

    public static void coba() throws NidzomException {
        int angka1 = 10;
        int angka2 = 0;
        double result = cekHitungan(angka1, angka2);
        System.out.println("Hasil: " + result);
    }

    public static void main(String[] args) throws NidzomException {
        try {
            int angka1 = 10;
            int angka2 = 0;
            double result = cekHitungan(angka1, angka2);
            System.out.println("Hasil: " + result);
        } catch (NidzomException e) {
            System.err.println("Terjadi eror: " + e.getMessage());
        }
        
        System.out.println("THROWS");
        
        coba();   
    }
}
