/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bvs;

import ifo.AbstrFIFO;
import ifo.AbstrLIFO;
import ifo.IIFO;
import java.util.Iterator;
import java.util.NoSuchElementException;
import obce.IKlic;

/**
 *
 * @author Oldřich
 */
public class AbstrBVS implements IAbstrBVS {

    private Prvek koren = null;
    private Prvek aktualni = null;

    @Override
    public void zrus() {
        aktualni = null;
        koren = null;
    }

    @Override
    public boolean jePrazdny() {
        return (koren == null);
    }

    @Override
    public IKlic najdi(IKlic klic) {
        if (jePrazdny()) {
            return null;
        }
        aktualni = koren;

        while (aktualni.obsah.compareTo(klic) != 0) {
            if (aktualni.obsah.compareTo(klic) < 0) {
                if (aktualni.pravySyn == null) {
                    return null;
                } else {
                    aktualni = aktualni.pravySyn;
                }
            } else if (aktualni.obsah.compareTo(klic) > 0) {
                if (aktualni.levySyn == null) {
                    return null;
                } else {
                    aktualni = aktualni.levySyn;
                }
            }
        }
        return aktualni.obsah;
    }

    @Override
    public void vloz(IKlic klic) {
        if (koren == null) {
            koren = new Prvek(klic);
            aktualni = koren;
        } else {
            aktualni = koren;
            Prvek otecVlozeneho = najdiOtceKVlozeni(klic);
            if (otecVlozeneho.obsah.compareTo(klic) < 0) {
                otecVlozeneho.pravySyn = new Prvek(klic);
                otecVlozeneho.pravySyn.otec = otecVlozeneho;
            } else {
                otecVlozeneho.levySyn = new Prvek(klic);
                otecVlozeneho.levySyn.otec = otecVlozeneho;
            }

        }
    }

    private Prvek najdiOtceKVlozeni(IKlic klic) {
        if (aktualni.obsah.compareTo(klic) < 0) {
            if (aktualni.pravySyn == null) {
                return aktualni;
            } else {
                aktualni = aktualni.pravySyn;
                return najdiOtceKVlozeni(klic);
            }
        } else {
            if (aktualni.levySyn == null) {
                return aktualni;
            } else {
                aktualni = aktualni.levySyn;
                return najdiOtceKVlozeni(klic);
            }
        }
    }

    @Override
    public IKlic odeber(IKlic klic) {
        najdi(klic);
        Prvek KOdebrani = aktualni;
        if (KOdebrani == null) {
            return null;
        } else if (KOdebrani == koren) {
            if (koren.levySyn != null) {
                Prvek novyKoren = najdiVlevo(KOdebrani);
                novyKoren.pravySyn = koren.pravySyn;
                novyKoren.otec.pravySyn = novyKoren.levySyn;
                novyKoren.otec = null;

                novyKoren.levySyn = koren.levySyn;
                koren.levySyn.otec = novyKoren;

                koren = novyKoren;
                opravZacykleni(novyKoren);
            } else if (koren.pravySyn != null) {
                Prvek novyKoren = najdiVpravo(KOdebrani);
                novyKoren.otec.levySyn = novyKoren.pravySyn;
                novyKoren.otec = null;
                novyKoren.pravySyn = koren.pravySyn;
                koren = novyKoren;
                opravZacykleni(koren);
            } else {
                zrus();
            }
        } else {
            if (KOdebrani.levySyn != null) {
                Prvek novy = najdiVlevo(KOdebrani);

                novy.pravySyn = KOdebrani.pravySyn;
                novy.pravySyn.otec = novy;

                if (novy.levySyn == null && novy.otec != KOdebrani) {
                    novy.levySyn = KOdebrani.levySyn;
                    KOdebrani.levySyn.otec = novy;
                    //novy.otec.pravySyn = novy.levySyn;
                } else if (novy.otec != KOdebrani) {
                    novy.levySyn.otec = novy.otec;
                    novy.otec.pravySyn = novy.levySyn;

                    novy.levySyn = KOdebrani.levySyn;
                    KOdebrani.levySyn.otec = novy;

                }

                novy.otec = KOdebrani.otec;
                nastavOtce(KOdebrani, novy);

                opravZacykleni(novy);
            } else if (KOdebrani.pravySyn != null) {
                Prvek novy = najdiVpravo(KOdebrani);

                novy.levySyn = KOdebrani.levySyn;

                if (KOdebrani.pravySyn == novy) {
                } else {
                    if (novy.pravySyn != null) {
                        novy.pravySyn.otec = novy.otec;
                    }
                    novy.otec.levySyn = novy.pravySyn;
                    novy.pravySyn = KOdebrani.pravySyn;
                }

                nastavOtce(KOdebrani, novy);
                novy.otec = KOdebrani.otec;
                opravZacykleni(novy);
            } else {
                nastavOtce(KOdebrani, null);
                KOdebrani.otec = null;
            }
        }
        return KOdebrani.obsah;
    }

    private void nastavOtce(Prvek KOdebrani, Prvek novy) {
        if (KOdebrani.otec.levySyn == KOdebrani) {
            KOdebrani.otec.levySyn = novy;
        } else if (KOdebrani.otec.pravySyn == KOdebrani) {
            KOdebrani.otec.pravySyn = novy;
        }
    }

    private void opravZacykleni(Prvek prvek) {
        if (prvek.otec == prvek) {
            prvek.otec = null;
        }
        if (prvek.levySyn == prvek) {
            prvek.levySyn = null;
        }
        if (prvek.pravySyn == prvek) {
            prvek.pravySyn = null;
        }
    }

    private Prvek najdiVlevo(Prvek zacatek) {//nejpravejsi
        Prvek aktualni = zacatek.levySyn;
        while (aktualni.getPravySyn() != null) {
            aktualni = aktualni.pravySyn;
        }
        return aktualni;
    }

    private Prvek najdiVpravo(Prvek zacatek) {//nejlevejsi
        Prvek aktualni = zacatek.pravySyn;
        while (aktualni.getLevySyn() != null) {
            aktualni = aktualni.levySyn;
        }
        return aktualni;
    }

    @Override
    public Iterator<IKlic> vytvorIterator(ETypProhl typ) {
        Iterator<IKlic> vrat = null;
        switch (typ) {
            case HLOUBKY:
                vrat = new Iterator() {//zásobník vložit všechny levé, odebírat a přidat z nej pravého
                    IIFO<Prvek> zasobnik = new AbstrLIFO();
                    Prvek aktualni = koren;

                    {
                        if (aktualni != null) {
                            zasobnik.vloz(koren);
                            pridejLeve(aktualni);
                        }
                    }

                    private void pridejLeve(Prvek prvek) {
                        while (prvek.getLevySyn() != null) {
                            prvek = prvek.levySyn;
                            zasobnik.vloz(prvek);
                        }
                    }

                    @Override
                    public boolean hasNext() {
                        if (aktualni == null) {
                            return false;
                        }
                        return !zasobnik.jePrazdny();
                    }

                    @Override
                    public IKlic next() {
                        if (zasobnik.jePrazdny()) {
                            throw new NoSuchElementException();
                        }
                        Prvek vrat = zasobnik.odeber();
                        if (vrat.pravySyn != null) {
                            zasobnik.vloz(vrat.pravySyn);
                            pridejLeve(vrat.pravySyn);
                        }
//                        if (vrat.otec.pravySyn != null) {
//                            zasobnik.vloz(vrat.otec.pravySyn);
//                            pridejLeve(vrat.otec.pravySyn);
//                        }
                        return vrat.obsah;
                    }
                };
                break;
            case SIRKY:
                vrat = new Iterator() {
                    IIFO<Prvek> fronta = new AbstrFIFO();
                    Prvek aktualni = koren;

                    {
                        if (aktualni != null) {
                            fronta.vloz(koren);
                        }
                    }

                    @Override
                    public boolean hasNext() {
                        return !fronta.jePrazdny();
                    }

                    @Override
                    public IKlic next() {
                        if (fronta.jePrazdny()) {
                            throw new NoSuchElementException();
                        }
                        Prvek vrat = fronta.odeber();
                        if (vrat.levySyn != null) {
                            fronta.vloz(vrat.levySyn);
                        }
                        if (vrat.pravySyn != null) {
                            fronta.vloz(vrat.pravySyn);
                        }
                        return vrat.obsah;
                    }
                };
                break;
        }
        return vrat;
    }

    private class Prvek {

        private Prvek otec = null;
        private Prvek levySyn = null;
        private Prvek pravySyn = null;
        private IKlic obsah;

        public Prvek(IKlic obsah) {
            this.obsah = obsah;
        }

        public Prvek getOtec() {
            return otec;
        }

        public void setOtec(Prvek otec) {
            this.otec = otec;
        }

        public Prvek getLevySyn() {
            return levySyn;
        }

        public void setLevySyn(Prvek levySyn) {
            this.levySyn = levySyn;
        }

        public Prvek getPravySyn() {
            return pravySyn;
        }

        public void setPravySyn(Prvek pravySyn) {
            this.pravySyn = pravySyn;
        }

        public IKlic getObsah() {
            return obsah;
        }

        public void setObsah(IKlic obsah) {
            this.obsah = obsah;
        }

        @Override
        public String toString() {
            return "Prvek{" + "otec=" + otec + ", levySyn=" + levySyn + ", pravySyn=" + pravySyn + ", obsah=" + obsah + '}';
        }
    }
}
