/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifo;

import doubleList.AbstrDoubleList;

/**
 *
 * @author Oldřich
 */
public class AbstrFIFO<E> extends AbstrDoubleList<E> implements IIFO<E> {

    @Override
    public boolean jePrazdny() {
        return super.jePrazdy();
    }

    @Override
    public void vloz(E data) {
        super.vlozPosledni(data);
    }

    @Override
    public E odeber() {
        return super.odeberPrvni();
    }

}
