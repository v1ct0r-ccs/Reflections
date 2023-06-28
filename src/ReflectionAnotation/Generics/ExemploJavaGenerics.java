package ReflectionAnotation.Generics;

import ReflectionAnotation.Generics.Fruta.Banana;
import ReflectionAnotation.Generics.Fruta.IFruta;
import ReflectionAnotation.Generics.Fruta.Maca;

import java.util.ArrayList;
import java.util.List;

public class ExemploJavaGenerics {

    public static void main(String[] args) {
        List<String> lista = new ArrayList<>();
        lista.add("Victor");
        lista.add("1");
        imprimirMetodo(lista);

        List<Long> listaLong = new ArrayList<>();
        listaLong.add(1L);
        listaLong.add(2L);
        imprimirLong(listaLong);

        String primeiroSt = pegarPrimeiroLista(lista);
        System.out.println(primeiroSt);

        Long primeiroLg = pegarPrimeiroLista(listaLong);
        System.out.println(primeiroLg);

        imprimirWildcard(listaLong);

        List<IFruta> frutas = new ArrayList<>();
        frutas.add(new Maca());
        frutas.add(new Banana());
        imprimirFrutas(frutas);
    }

    public static void imprimirFrutas(List<? extends IFruta> lista) {
        for (IFruta fruta : lista) {
            System.out.println(fruta);
        }
    }

    public static void imprimirWildcard(List<?> lista) {
        System.out.println("**** <Wildcard> **** ");
        for (Object wc : lista) {
            System.out.println(wc);
        }
    }

    public static void imprimirLong(List<Long> lista) {
        System.out.println("**** <Long> ****");
        for (Long lg : lista) {
            System.out.println(lg);
        }
    }

    public static <T> void imprimirMetodo(List<T> lista) {
        System.out.println("**** <T> ****");
        for (Object st : lista) {
            System.out.println(st);
        }
    }

    public static <T> T pegarPrimeiroLista(List<T> lista) {
        System.out.println("**** <T> T ****");
        return lista.get(0);
    }
}
