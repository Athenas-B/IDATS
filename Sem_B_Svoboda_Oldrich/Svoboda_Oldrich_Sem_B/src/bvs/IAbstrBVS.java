/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bvs;

import java.util.Iterator;
import obce.IKlic;

/**
 *
 * @author Oldřich
 */
public interface IAbstrBVS {

    void zrus(); //zrušení celé tabulky

    boolean jePrazdny(); //test prázdnosti tabulky

    Object najdi(IKlic klic); // vyhledá prvek dle klíče //vrací obal nebo vnitřek?

    void vloz(IKlic klic); // vloží prvek do tabulky

    Object odeber(IKlic klic); // odebere prvek dle klíče z tabulky

    Iterator vytvorIterator(ETypProhl typ);/*vytvoří iterátor, který umožňuje 
     procházení stromu do šířky/hloubky (in-order)*/

}
