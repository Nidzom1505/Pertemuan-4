/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pertemuan4;

/**
 *
 * @author Nidzzz
 */
public class TryCatch extends Exception {

    public void coba() {
        try {
            int result = 55 / 0;
            System.out.println("Hasil: " + result);
        } catch (ArithmeticException e) {
            System.out.println("Perhitungan kurang sip");
        }
    }
}
