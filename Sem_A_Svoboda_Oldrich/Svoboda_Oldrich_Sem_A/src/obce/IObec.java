/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package obce;

import java.io.Serializable;

/**
 *
 * @author Oldřich
 */
public interface IObec extends Serializable{

    int getCelkem();

    String getObec();

    int getPocetMuzu();

    int getPocetZen();

    int getPsc();

    void setCelkem(int celkem);

    void setObec(String obec);

    void setPocetMuzu(int pocetMuzu);

    void setPocetZen(int pocetZen);

    void setPsc(int psc);
    
}
