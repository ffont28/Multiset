import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;


/*-
 * OVERVIEW: Le istanze di questa classe rappresentano un StringMultiSet.
 *           Gli oggetti di questo tipo sono mutabili:
 *           ciò è dovuto alle operazioni di aggiunta e rimozione di elementi.
 * 
 */
public class ListStringMultiSet implements StringMultiSet {
    // ATTRIBUTI
    private List<String> insieme;

    /*
     * ABS FUN: AF(stringa) = (stringa, stringa, stringa (ripetuta tante volte quanto la sua cardinalità) )  
     * REP INV: molteplicità >= 0
     */

    // COSTRUTTORI
    /*
     * Inizializza this come ArrayList
     */
    public ListStringMultiSet() {
        insieme = new ArrayList<>();
    }

    @Override
    public int add(String s) {
        Objects.requireNonNull(s, "La stringa da inserire non può essere NULL");
        insieme.add(s);
        return multiplicity(s);
    }

    @Override
    public int remove(String s) {
        Objects.requireNonNull(s, "La stringa da rimuovere non può essere NULL");
        // Avrei preferito usare una IllegalArgumentException ma la consegna indica di
        // ignorare il fatto che un elemento che non esiste non possa essere rimosso,
        // quindi:s
        if (!contains(s))
            return 0;
        insieme.remove(s);
        return multiplicity(s);
    }

    @Override
    public boolean contains(String s) {
        Objects.requireNonNull(s);
        return insieme.contains(s);
    }

    @Override
    public int multiplicity(String s) {
        int conteggio = 0;
        for (String str : insieme)
            if (str.equals(s))
                conteggio++;
        return conteggio;
    }

    @Override
    public int size() {
        return insieme.size();
    }

    @Override
    public StringMultiSet union(StringMultiSet o) {
        Objects.requireNonNull(o, "Lo StringMultiSet da unire non può essere NULL");
        StringMultiSet u = new ListStringMultiSet();
        for (String key : insieme)
            u.add(key);
        for (String key : o)
            u.add(key);
        return u;
    }


    @Override
    public StringMultiSet intersection(StringMultiSet o) {
        Objects.requireNonNull(o, "Lo StringMultiSet da intersecare non può essere NULL");
        StringMultiSet u = new ListStringMultiSet();
        Map<String, Integer> supporto1 = new TreeMap<>();
        Map<String, Integer> supporto2 = new TreeMap<>();
        for (String key : insieme) {
            if (supporto1.containsKey(key))
                supporto1.put(key, supporto1.get(key) + 1);
            else
                supporto1.put(key, 1);
        }
        
        for (String key : o){
            if (supporto2.containsKey(key))
                supporto2.put(key, supporto2.get(key) + 1);
            else
                supporto2.put(key, 1); 
        }   
        for (String str : supporto1.keySet())
            if (!supporto2.containsKey(str)) supporto1.remove(str);
            else
            if (supporto1.get(str) > supporto2.get(str))
                supporto1.put(str, supporto2.get(str));

        for (String str : supporto1.keySet())
            for (int i = 0; i < supporto1.get(str); i++)
                u.add(str);
        return u;
    }

    @Override
    public Iterator<String> iterator() {
        return insieme.iterator();
    }

    @Override
    public String toString() {
        final StringBuilder result = new StringBuilder();
        result.append(size() + "{");
        for (final String str : insieme)
            result.append(str + ": " + multiplicity(str) + ",");
        result.append("}");
        return result.toString();
    }

 }
