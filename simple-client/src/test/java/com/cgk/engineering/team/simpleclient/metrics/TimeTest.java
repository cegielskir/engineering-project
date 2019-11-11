package com.cgk.engineering.team.simpleclient.metrics;

import com.cgk.engineering.team.simpleclient.algorithm.NormalizedLevenshtein;
import com.cgk.engineering.team.simpleclient.algorithm.interfaces.StringSimilarity;
import org.junit.Test;
import org.simmetrics.StringMetric;
import org.simmetrics.metrics.StringMetrics;

import static org.assertj.core.api.Assertions.assertThat;

public class TimeTest {
    private static StringMetric stringMetric;
//    private final static String LONG_TEXT_1 = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec molestie vehicula ultrices. " +
//            "Phasellus convallis sapien vel libero ullamcorper consectetur vel ut mi. Morbi auctor tortor laoreet risus rhoncus blandit." +
//            " Duis sit amet faucibus tortor. Etiam rhoncus feugiat leo, ac semper leo. Cras faucibus nec ex sed rhoncus. " +
//            "Proin maximus facilisis mattis. Etiam at vestibulum felis. Fusce efficitur aliquet ullamcorper. Proin nibh risus, tempus at ornare " +
//            "sed, blandit id felis. Phasellus porta, ligula quis ultricies blandit, nisl eros luctus eros, et volutpat lorem diam at nulla. " +
//            "Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Morbi faucibus nunc at faucibus vehicula. " +
//            "Sed quis consequat diam, vitae convallis ipsum. Pellentesque molestie euismod accumsan. " +
//            "Integer quis libero et ligula gravida mattis gravida ac lectus.";
//    private final static String LONG_TEXT_2 = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec molestie vehicula ultrices. " +
//            "Phasellus convallis sapien vel libero ullamcorper consectetur vel ut mi. Morbi auctor tortor laoreet risus rhoncus blandit." +
//            "Morbi lacus tellus, cursus nec euismod at, faucibus scelerisque urna. Etiam sodales id massa sit amet mollis. " +
//            "Etiam ullamcorper at arcu quis lacinia. In consequat convallis diam. Nullam nisl mi, finibus eget erat ut, l" +
//            "obortis efficitur erat. Curabitur vel dui sodales, blandit tellus porttito" +
//            "Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Morbi faucibus nunc at faucibus vehicula. " +
//            "Sed quis consequat diam, vitae convallis ipsum. Pellentesque molestie euismod accumsan. " +
//            "Integer quis libero et ligula gravida mattis gravida ac lectus.";
      private final static String LONG_TEXT_1  = "– Dobrze rozumiałem, że w kręgu liderów, którzy tam się pojawili, funkcjonuje projekt polityczny równoległy do tych wydarzeń, które dzieją się na Majdanie. Kiedy zobaczyłem tę mimikrę, to jak wspaniałe, zdrowe żądania mądrej młodzieży zostają przesłonięte doraźną perspektywą przejęcia władzy, zrozumiałem, że popadamy w wielką, narodową biedę – stwierdził były ukraiński prezydent. Powodem dystansu W. Juszczenki wobec Euromajdanu były także rozlegające się tam hasła zwolnienia z więzienia Julii Tymoszenko, którą Wiktor Juszczenko uważał za destrukcyjną postać na ukraińskiej scenie politycznej. – Kiedy cały Majdan skanduje „Uwolnić Julię!”, nie ma tam dla mnie miejsca – powiedział ukraiński polityk. Krytyce byłej premier poświęcił znaczą część wywiadu, przekonując, że działa ona w interesie Kremla. – Putin nie posiada na Ukrainie bardziej destrukcyjnego politycznie czynnika niż Tymoszenko. Jeśli Tymoszenko doczeka się pierwszego potknięcia Petra Poroszenki, ujrzycie na czym polega jej misja – przewiduje były prezydent Ukrainy. Oprac. JCK Źródło: http://www.pravda.com.ua/rus/news/2014/12/25/7053321/ Fot. www.mcclatchydc.com Informacja opublikowana również na www.komitet-ukrainski.pl";
      private final static String LONG_TEXT_2 = "– Uważamy, że sfera energetyki powinna być kontrolowana przez państwo. Wierzę, że w rękach państwa ta kompania przyniesie Krymowi więcej pożytku – powiedział podczas sesji krymskiego parlamentu głowa republiki Siergiej Aksjonow. Zapewnił, że „własność kompanii pozostanie w państwowych rękach, jej prywatyzacja nie jest planowana”. Dodał, że decyzja o nacjonalizacji była także reakcją na nieuczciwe praktyki, jakich dopuszczało się kierownictwo firmy oraz jego niechęć do przerejestrowania przedsiębiorstwa zgodnie z regułami rosyjskiego systemu prawnego i „nie jest związana z osobą właściciela holdingu”. DTEK „Krymenergo” to jedno z największych przedsiębiorstw Krymu. Pracuje w nim 6 tys. osób. Kompania dostarcza energię elektryczną mieszkańcom półwyspu, obsługuje terytorium o powierzchni 27 tys. km2. Równocześnie Republika Krymu dokonała także nacjonalizacji regionalnej Międzybankowej Giełdy Walutowej. W uzasadnieniu podano, że giełda była stworzona „za pieniądze regionu”. Oprac. J. C. Kamiński Fot. eastbook.eu (na fotografii Rinat Achmetow).";
      //    @Test
//    public void LevenshteinTest() {
//        long start = System.currentTimeMillis();
//        double result = new NormalizedLevenshtein().similarity(LONG_TEXT_1, LONG_TEXT_2);
//        long end = System.currentTimeMillis();
//        System.out.println("Levenshtein: " + result + ", time: " + (end - start) + "ms");
//
//        assertThat(1).isEqualTo(1);
//    }

    @Test
    public void blockDistanceTest() {
        stringMetric = StringMetrics.blockDistance();
        long start = System.currentTimeMillis();
        double result = stringMetric.compare(LONG_TEXT_1, LONG_TEXT_2);
        long end = System.currentTimeMillis();
        System.out.println("BlockDistance: " + result + ", time: " + (end - start) + "ms");

        assertThat(1).isEqualTo(1);
    }

    @Test
    public void cosineTest() {
        stringMetric = StringMetrics.cosineSimilarity();
        long start = System.currentTimeMillis();
        double result = stringMetric.compare(LONG_TEXT_1, LONG_TEXT_2);
        long end = System.currentTimeMillis();
        System.out.println("Cosine: " + result + ", time: " + (end - start) + "ms");

        assertThat(1).isEqualTo(1);
    }

    @Test
    public void damerauLevenshteinTest() {
        stringMetric = StringMetrics.damerauLevenshtein();
        long start = System.currentTimeMillis();
        double result = stringMetric.compare(LONG_TEXT_1, LONG_TEXT_2);
        long end = System.currentTimeMillis();
        System.out.println("DamerauLevenshtein: " + result + ", time: " + (end - start) + "ms");

        assertThat(1).isEqualTo(1);
    }

    @Test
    public void diceTest() {
        stringMetric = StringMetrics.dice();
        long start = System.currentTimeMillis();
        double result = stringMetric.compare(LONG_TEXT_1, LONG_TEXT_2);
        long end = System.currentTimeMillis();
        System.out.println("Dice: " + result + ", time: " + (end - start) + "ms");

        assertThat(1).isEqualTo(1);
    }

    @Test
    public void euclideanTest() {
        stringMetric = StringMetrics.euclideanDistance();
        long start = System.currentTimeMillis();
        double result = stringMetric.compare(LONG_TEXT_1, LONG_TEXT_2);
        long end = System.currentTimeMillis();
        System.out.println("euclidean: " + result + ", time: " + (end - start) + "ms");

        assertThat(1).isEqualTo(1);
    }

    @Test
    public void generalizedJaccardTest() {
        stringMetric = StringMetrics.generalizedJaccard();
        long start = System.currentTimeMillis();
        double result = stringMetric.compare(LONG_TEXT_1, LONG_TEXT_2);
        long end = System.currentTimeMillis();
        System.out.println("generalizedJaccard: " + result + ", time: " + (end - start) + "ms");

        assertThat(1).isEqualTo(1);
    }

    @Test
    public void jaccardTest() {
        stringMetric = StringMetrics.jaccard();
        long start = System.currentTimeMillis();
        double result = stringMetric.compare(LONG_TEXT_1, LONG_TEXT_2);
        long end = System.currentTimeMillis();
        System.out.println("jaccard: " + result + ", time: " + (end - start) + "ms");

        assertThat(1).isEqualTo(1);
    }

    @Test
    public void jaroWinklerest() {
        stringMetric = StringMetrics.jaroWinkler();
        long start = System.currentTimeMillis();
        double result = stringMetric.compare(LONG_TEXT_1, LONG_TEXT_2);
        long end = System.currentTimeMillis();
        System.out.println("jaroWinkler: " + result + ", time: " + (end - start) + "ms");

        assertThat(1).isEqualTo(1);
    }

    @Test
    public void levenshteinTest() {
        stringMetric = StringMetrics.levenshtein();
        long start = System.currentTimeMillis();
        double result = stringMetric.compare(LONG_TEXT_1, LONG_TEXT_2);
        long end = System.currentTimeMillis();
        System.out.println("Levenshtein: " + result + ", time: " + (end - start) + "ms");

        assertThat(1).isEqualTo(1);
    }

    @Test
    public void mongeElkanTest() {
        stringMetric = StringMetrics.mongeElkan();
        long start = System.currentTimeMillis();
        double result = stringMetric.compare(LONG_TEXT_1, LONG_TEXT_2);
        long end = System.currentTimeMillis();
        System.out.println("mongeElkan: " + result + ", time: " + (end - start) + "ms");

        assertThat(1).isEqualTo(1);
    }

    @Test
    public void needlemanWunchTest() {
        stringMetric = StringMetrics.needlemanWunch();
        long start = System.currentTimeMillis();
        double result = stringMetric.compare(LONG_TEXT_1, LONG_TEXT_2);
        long end = System.currentTimeMillis();
        System.out.println("needlemanWunch: " + result + ", time: " + (end - start) + "ms");

        assertThat(1).isEqualTo(1);
    }

    @Test
    public void overlapCoefficientTest() {
        stringMetric = StringMetrics.overlapCoefficient();
        long start = System.currentTimeMillis();
        double result = stringMetric.compare(LONG_TEXT_1, LONG_TEXT_2);
        long end = System.currentTimeMillis();
        System.out.println("overlapCoefficient: " + result + ", time: " + (end - start) + "ms");

        assertThat(1).isEqualTo(1);
    }

    @Test
    public void qGramsDistanceTest() {
        stringMetric = StringMetrics.qGramsDistance();
        long start = System.currentTimeMillis();
        double result = stringMetric.compare(LONG_TEXT_1, LONG_TEXT_2);
        long end = System.currentTimeMillis();
        System.out.println("qGramsDistance: " + result + ", time: " + (end - start) + "ms");

        assertThat(1).isEqualTo(1);
    }

    @Test
    public void simonWhiteTest() {
        stringMetric = StringMetrics.simonWhite();
        long start = System.currentTimeMillis();
        double result = stringMetric.compare(LONG_TEXT_1, LONG_TEXT_2);
        long end = System.currentTimeMillis();
        System.out.println("simonWhite: " + result + ", time: " + (end - start) + "ms");

        assertThat(1).isEqualTo(1);
    }

    @Test
    public void smithWatermanTest() {
        stringMetric = StringMetrics.smithWatermanGotoh();
        long start = System.currentTimeMillis();
        double result = stringMetric.compare(LONG_TEXT_1, LONG_TEXT_2);
        long end = System.currentTimeMillis();
        System.out.println("smithWaterman: " + result + ", time: " + (end - start) + "ms");

        assertThat(1).isEqualTo(1);
    }
}

