import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

/*-
 * OVERVIEW: Le istanze di questa classe rappresentano un StringMultiSet.
 *           Gli oggetti di questo tipo sono mutabili:
 *           ciò è dovuto alle operazioni di aggiunta e rimozione di elementi.
 * 
 */
public class MapStringMultiSet implements StringMultiSet {
    // ATTRIBUTI
    private Map<String, Integer> insieme;

    /*
     * ABS FUN: AF(stringa) = (stringa : molteplicità di stringa)
     * REP INV: molteplicità >= 0
     */


    //COSTRUTTORI
    /*
     * Inizializza this come TreeMap
     */
    public MapStringMultiSet() {
        insieme = new TreeMap<>();
    }


    @Override
    public int add(String s) {
        Objects.requireNonNull(s, "La stringa da inserire non può essere NULL");
        if (contains(s))
            insieme.put(s, multiplicity(s) + 1);
        else
            insieme.put(s, 1); 
        return insieme.get(s);
    }

    @Override
    public int remove(String s) {
        Objects.requireNonNull(s, "La stringa da rimuovere non può essere NULL");
        // Avrei preferito usare una IllegalArgumentException ma la consegna indica di
        // ignorare il fatto che un elemento che non esiste non possa essere rimosso, quindi:s
        if (!contains(s))
            return 0;
        insieme.put(s, multiplicity(s) - 1);
        if (multiplicity(s) == 0)
            insieme.remove(s);
        return multiplicity(s);
    }

    @Override
    public boolean contains(String s) {
        Objects.requireNonNull(s);
        return insieme.containsKey(s);
    }

    @Override
    public int multiplicity(String s) {
        if (contains(s))
            return insieme.get(s);
        return 0;
    }

    @Override
    public int size() {
        int card = 0;
        for (String key : insieme.keySet())
            card = card + multiplicity(key);
        return card;
    }

    @Override
    public StringMultiSet union(StringMultiSet o) {
        Objects.requireNonNull(o, "Lo StringMultiSet da unire non può essere NULL");
        StringMultiSet u = new MapStringMultiSet();
        for (String key : insieme.keySet())
            for (int i = 0; i < multiplicity(key); i++)
                u.add(key);
        for (String key : o)
            for (int i = 0; i < o.multiplicity(key); i++)
                u.add(key);      
        return u;
    }

    @Override
    public StringMultiSet intersection(StringMultiSet o) {
        Objects.requireNonNull(o, "Lo StringMultiSet da intersecare non può essere NULL");
        MapStringMultiSet u = new MapStringMultiSet();

        for (String key : insieme.keySet()) 
            for (String key2 : o)
                if (contains(key) && o.contains(key2))
                    for (int i=0; i< Math.min(multiplicity(key), multiplicity(key2));i++)
                        u.add(key2);
        return u;
    }

    @Override
    public Iterator<String> iterator() {
        return insieme.keySet().iterator();
    }

    @Override
    public String toString() {
        final StringBuilder result = new StringBuilder();
        result.append(size() + "{");
        for (final String str : insieme.keySet())
            result.append(str + ": " + multiplicity(str) + ",");
        result.append("}");
        return result.toString();
    }

}
