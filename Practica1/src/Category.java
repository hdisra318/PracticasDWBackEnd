/**
 * Clase Category
 * 
 * @author Israel Hernandez Dorantes - 318206604
 * @version 1.0
 */
public class Category {

    /* Atributos */

    Integer category_id;
    String category;
    String acronym;


    /**
     * Constructor de Category
     * @param category_id id de la categoria
     * @param category categoria
     * @param acronym acronimo de la categoria
     */
    Category(Integer category_id, String category, String acronym){
        this.category_id = category_id;
        this.category = category;
        this.acronym = acronym;
    }

}