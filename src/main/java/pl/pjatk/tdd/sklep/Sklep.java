package pl.pjatk.tdd.sklep;

public class Sklep {
    public static final int KUPON_CO_ILE = 40;
    public static final int MAKSYMALNIE_KUPONOW_W_TRANSAKCJI = 3;
    public static final int SKALY_KLIENT_OD_WLACZIE = 10;
    public static void dokonajZakupu(final KartaKlienta karta, final double kwotaZakupu) {
        // Gdy dopuszczamy ujemne kwoty zakupu -> karta.dodajKupony(Math.max(0, Math.min(MAKSYMALNIE_KUPONOW_W_TRANSAKCJI, (int) Math.floor(kwotaZakupu / KUPON_CO_ILE))));
        karta.dodajKupony(Math.min(MAKSYMALNIE_KUPONOW_W_TRANSAKCJI, (int) Math.floor(kwotaZakupu / KUPON_CO_ILE)));
    }

    public static double policzZnizkiProcentowe(final KartaKlienta kartaKlienta, final double kwotaZakupu) {
        final var stalaZnizka = kartaKlienta.pobierzIloscDostepnychKuponow() >= 10 ? 3 : 0;

        var znizkaProcentowa = 0;
        if (kwotaZakupu > 100) znizkaProcentowa = 10;
        else if (kwotaZakupu > 50) znizkaProcentowa = 5;

        return stalaZnizka + znizkaProcentowa;

        // Java 17 <3
//        return switch (kwotaZakupu) {
//            default -> 0;
//            case kwotaZakupu > 100 -> 10;
//            case kwotaZakupu > 50 -> 5;
//        } + kartaKlienta.pobierzIloscDostepnychKuponow() >= 10 ? 3 : 0;
    }

    public static KartaKlienta wydajNowaKarte(){
        return new KartaKlienta();
    }
}
