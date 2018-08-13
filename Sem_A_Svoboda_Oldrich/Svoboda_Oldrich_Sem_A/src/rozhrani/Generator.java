/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rozhrani;

import obce.IObec;
import obce.Obec;

/**
 *
 * @author Oldřich
 */
public final class Generator {

    private Generator() {
    }

    public static IObec generujObec() {
        int psc = generujCislo(1000, 99999);
        int muzu = generujCislo(0, 99999);
        int zen = generujCislo(0, 99999);
        String nazev = generujNazev();
        IObec obec = new Obec(psc, nazev, muzu + zen);
        obec.setPocetMuzu(muzu);
        obec.setPocetZen(zen);
        return obec;
    }

    public static int generujCislo(int od, int po) {
        int cislo = (int) (Math.random() * (po - od + 1) + od);
        return cislo;
    }

    public static String generujNazev() {
        String nazev = "";
        int pocet = generujCislo(3, 70);
        int mezery = generujCislo(4, 20);
        int znak = generujCislo(0x41, 0x5A);
        nazev += (char) znak;
        for (int i = 1; i < pocet; i++) {
            if (i % mezery == 0) {
                znak = 0xA0;
            } else {
                znak = generujCislo(0x61, 0x7A);
            }
            nazev += (char) znak;
        }

        return nazev;
    }

}
