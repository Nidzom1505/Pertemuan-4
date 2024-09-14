/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pertemuan4;

import java.io.IOException;

/**
 *
 * @author Nidzzz
 */
public class Throws extends Exception {

    public void coba() throws IOException {
        int result = 55 / 0;
        System.out.println("Hasil: " + result);
    }
}
