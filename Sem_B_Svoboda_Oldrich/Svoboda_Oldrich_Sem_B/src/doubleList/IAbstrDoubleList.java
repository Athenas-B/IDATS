/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doubleList;

import java.util.Iterator;
import obce.IObec;

/**
 *
 * @author Oldřich
 */
public interface IAbstrDoubleList<T> extends Iterable<T> {

    void zrus();//-zrušení celého seznamu,

    boolean jePrazdy();//-test naplněnosti seznamu,

    void vlozPrvni(T obj);//-vložení prvku do seznamu na první místo 

    void vlozPosledni(T obj);//-vložení prvku do seznamu na poslední místo, 

    void vlozNaslednika(T obj);//-vložení  prvku  do  seznamu  jakožto následníka aktuálního prvku,

    void vlozPredchudce(T obj);//-vložení  prvku  do  seznamu  jakožto předchůdce aktuálního prvku,

    T zpristupniAktualni();//-zpřístupnění aktuálního prvku seznamu,

    T zpristupniPrvni();//-zpřístupnění prvního prvku seznamu,

    T zpristupniPosledni();//-zpřístupnění posledního prvku seznamu,

    T zpristupniNaslednika();//-zpřístupnění následníka aktuálního prvku, 

    T zpristupniPredchudce();//-zpřístupnění předchůdce aktuálního prvku,

    T odeberAktualni();//-odebrání (vyjmutí)  aktuálního prvku ze seznamu poté je aktuální prvek nastaven na první prvek

    T odeberPrvni();//-odebrání prvního prvku ze seznamu,

    T odeberPosledni();//-odebrání posledního prvku ze seznamu, 

    T odeberNaslednika();//-odebrání následníka aktuálního prvku ze seznamu,

    T odeberPredchudce();//-odebrání předchůdce aktuálního prvku ze seznamu,

    Iterator<T> vytvorIterator();//-vytvoří iteráto 

    public boolean najdiData(T reference); //vyhledání data (obce) na základě shody referencí
}
