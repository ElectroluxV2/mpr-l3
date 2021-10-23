package pl.pjatk.tdd.test.sklep;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pl.pjatk.tdd.sklep.KartaKlienta;
import pl.pjatk.tdd.sklep.Sklep;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class SklepTestZnizkiStalegoKlienta {

    @Parameterized.Parameter (value = 0)
    public double kwotaZakupu;

    @Parameterized.Parameter (value = 1)
    public int oczekiwanaZnizkaDlaStalegoKlienta;

    private KartaKlienta karta;

    @Parameterized.Parameters (name = "[{index}] Przy zakupie za kwotę {0} przez stałego klienta oczekiwana zniżka powinna wynieść {1}%")
    public static Collection<Object[]> dataProvider(){
        return Arrays.asList(new Object[][]{
                {50.00, 3},
                {50.01, 8},
                {100.00, 8},
                {100.01, 13}
        });
    }

    @Before
    public void setup(){
        karta = Sklep.wydajNowaKarte();
        while (karta.pobierzIloscDostepnychKuponow() < Sklep.SKALY_KLIENT_OD_WLACZIE) {
            Sklep.dokonajZakupu(karta, Sklep.MAKSYMALNIE_KUPONOW_W_TRANSAKCJI * Sklep.KUPON_CO_ILE);
        }

    }

    @Test
    public void klientPowinienDostacOdpowiedniaZnizkeProcetowa(){
        assertEquals(oczekiwanaZnizkaDlaStalegoKlienta, Sklep.policzZnizkiProcentowe(karta, kwotaZakupu), 0.01);
    }
}
