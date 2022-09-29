
/* - 
 * OVERVIEW: Interfaccia per uno StringMultiSet
 */

interface StringMultiSet extends Iterable<String> {
    
    /**
     * Aggiunge un elemento a questo StringMultiSet
     * 
     * @param s la stringa da aggiungere al StringMultiSet
     * @return la molteplicità dell'elemento <b>s</b> nel StringMultiSet dopo
     *         l'inserimento
     * @throws NullPointerException se <b>s</b> è {@code null}
     */
    int add(String s);
  
    /**
     * Rimuove un elemento da questo StringMultiSet
     * 
     * @param s l'elemento da rimuovere dal StringMultiSet
     * @return la moltiplicità di <b>s</b> nel StringMultiSet prima della rimozione
     * @throws NullPointerException se <b>s</b> è {@code null}
     */
    int remove(String s);
  
    /**
     * Verifica se un elemento è presente in questo StringMultiSet
     * 
     * @param s l'elemento di cui verificare la presenza nel StringMultiSet
     * @return {@code} true} se <b>s</b> è presente nel StringMultiSet,
     *         {@code false} altrimenti
     * @throws NullPointerException se <b>s</b> è {@code null}
     */
    boolean contains(String s);
  
    /**
     * Restituisce la molteplicità di un elemento in questo StringMultiSet
     * 
     * @param s l'elemento di cui si vuole conoscere la molteplicità in questo
     *          StringMultiSet
     * @return la molteplicità di <b>s</b> in questo StringMultiSet, {@code 0} se
     *         l'elemento <b>s</b> non è presente
     * @throws NullPointerException se <b>s</b> è {@code null}
     */
    int multiplicity(String s);
  
    /**
     * Restituisce la cardinalità di questo StringMultiSet
     * 
     * @return la cardinalità di questo StringMultiSet
     */
    int size();
  
    /**
     * Unisce due StringMultiSet
     * 
     * @param o il StringMultiSet da unire a questo StringMultiSet
     * @return un nuovo StringMultiSet dato dall'unione di questo StringMultiSet e
     *         <b>o</b>
     * @throws NullPointerException se <b>o</b> è {@code null}
     */
    StringMultiSet union(StringMultiSet o);
  
    /**
     * Intersezione di due StringMultiSet
     * 
     * @param o il StringMultiSet da intersecare a questo StringMultiSet
     * @return un nuovo StringMultiSet dato dall'intersezione di questo
     *         StringMultiSet e <b>o</b>
     * @throws NullPointerException se <b>o</b> è {@code null}
     */
    StringMultiSet intersection(StringMultiSet o);

    // public List<String> getElements();
}