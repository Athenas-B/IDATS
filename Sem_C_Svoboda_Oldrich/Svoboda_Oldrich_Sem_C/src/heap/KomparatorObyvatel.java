/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heap;

import java.util.Comparator;
import obce.IObec;

/**
 *
 * @author Oldřich
 */
public class KomparatorObyvatel implements Comparator<IObec> {

    @Override
    public int compare(IObec o1, IObec o2) {
        return o1.getCelkem() - o2.getCelkem();

    }

    @Override
    public String toString() {
        return "Razeni dle obyvatel";
    }

}
