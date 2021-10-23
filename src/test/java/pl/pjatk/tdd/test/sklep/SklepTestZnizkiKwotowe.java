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
public class SklepTestZnizkiKwotowe {

    @Parameterized.Parameter (value = 0)
    public double kwotaZakupu;

    @Parameterized.Parameter (value = 1)
    public int oczekiwanaZnizkaProcentowa;

    private KartaKlienta karta;

    @Parameterized.Parameters (name = "[{index}] Przy zakupie za kwotę {0} oczekiwana zniżka powinna wynieść {1}%")
    public static Collection<Object[]> dataProvider(){
        return Arrays.asList(new Object[][]{
                {50.00, 0},
                {50.01, 5},
                {100.00, 5},
                {100.01, 10}
        });
    }

    @Before
    public void setup(){
        karta = Sklep.wydajNowaKarte();
    }

    @Test
    public void klientPowinienDostacOdpowiedniaZnizkeProcetowa(){
        assertEquals(oczekiwanaZnizkaProcentowa, Sklep.policzZnizkiProcentowe(karta, kwotaZakupu), 0.01);
    }
}
