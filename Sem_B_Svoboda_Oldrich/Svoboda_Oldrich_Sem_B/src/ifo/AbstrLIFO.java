/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifo;

import doubleList.AbstrDoubleList;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author Oldřich
 */
public class AbstrLIFO<E> extends AbstrDoubleList<E> implements IIFO<E> {

    @Override
    public boolean jePrazdny() {
        return super.jePrazdy();
    }

    @Override
    public void vloz(E data) {
        super.vlozPrvni(data);
    }

    @Override
    public E odeber() {
        return super.odeberPrvni();
    }

}
