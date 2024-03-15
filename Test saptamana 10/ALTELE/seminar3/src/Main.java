import org.example.pb1.Area;
import org.example.pb1.Patrat;

public class Main {
    public static void main(String[] args) {

        System.out.println("Hello world!");
        /**Definiti interfata functionala
         * Pb1.
         * a) org.example.pb1.Area care are o metoda abstracta compute, ce returneaza aria unei figuri geometrice
         * b) Definiti functii lambda pt calculul ariei unei figuri geometrice: cercuri si patrate
         */

        Area<Patrat> ariaPatrat= (Patrat p)-> {
        double arie=p.getL()*getL();
        return arie;
        }

        double compute(E figura);


    }
}