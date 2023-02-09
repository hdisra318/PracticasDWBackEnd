import java.util.LinkedList;
import java.util.Scanner;

/**
 * Clase CategoryMethods que implementa los métodos pedidos
 * 
 * @author Israel Hernandez Dorantes - 318206604
 * @version 1.0
 */
class CategoryMethods {

    /**
     * Lista de categorias registradas
     */
    LinkedList<LinkedList> categoryList = new LinkedList<>();

    /**
     * Metodo createCategory que registra los datos de un objeto Category
     * @param category
     */
    void createCategory(Category category){
        
        // Scanner sc = new Scanner(System.in);
        // System.out.println("- Proporciona el id de la categoria:  ");
        // Integer id = Integer.valueOf(sc.nextInt());
        // System.out.println("- Proporciona la categoria:  ");
        // String cate = sc.nextLine();
        // System.out.println("- Proprociona el acronimo:  ");
        // String acro = sc.nextLine();

        if(inCategoryList(category.category_id.intValue()) == null){
            System.out.println("El id proporcionado ya fue registrado");
            return;
        }

        //Agregando los datos del objeto a la lista
        LinkedList<String> categoria = new LinkedList<>();
        categoria.add(category.acronym);
        categoria.add(category.category);
        categoria.add(String.valueOf(category.category_id));

        //Agregando la lista a la lista global
        categoryList.add(categoria);

    }


    /**
     * Metodo getCategories muestra en consola una lista con todas 
     * las categorías registradas
     */
    void getCategories(){

        // Imprimiendo las categorias de la lista
        System.out.println("Lista de categorias registradas:");
        for(int i = 0; i<categoryList.size(); ++i){

            System.out.println((i+1)+".\tId: "+categoryList.get(i).get(0)+
            "\n  \tCategoria: "+categoryList.get(i).get(1)+
            "\n  \tAcronimo: "+categoryList.get(i).get(2));

        }
    }

    /**
     * Metodo getCategory que recibe desde la consola el id de una categoría y 
     * muestra sus datos (category_id, category, acronym)
     * 
     * @param category_id id de la categoria
     */
    void getCategory(int category_id){
        
        //Transformando a Integer
        Integer id = Integer.valueOf(category_id);

        if(categoryList.isEmpty()){
            System.out.println("No existen categorías registradas");
            return;
        }

        LinkedList<String> catList = inCategoryList(category_id);
        if(catList == null){
            System.out.println("No existe una categoría con el id ingresado”");
            return;
        }

        System.out.println("Los datos de la categoria solicitada son:");
        System.out.println("Id: "+category_id);
        System.out.println("Categoria: "+catList.get(1));
        System.out.println("Acronimo: "+catList.get(2));

    }

    /**
     * Metodo auxiliar que verifica si el id de la categoria se encuentra 
     * registrado en la lista global de categorias
     * 
     * @param category_id id de la categoria
     * @return la lista de la categoria a la que pertenece, null en otro caso 
     */
    private LinkedList<String> inCategoryList(int category_id){

        LinkedList<String> listFound = null;

        for(LinkedList l : categoryList){

            if(l.get(0).equals(""+category_id)){
                listFound = l;
                break;
            }

        }

        return listFound;
    }


    /**
     * Metodo deleteCategory que recibe desde la consola el id de una 
     * categoría y elimina su registro de la lista
     * 
     * @param category_id id de la categoria a eliminar
     */
    void deleteCategory(int category_id){

        LinkedList<String> catList = inCategoryList(category_id);
        if(catList == null){
            System.out.println("No existe una categoría con el id ingresado”");
            return;
        }

        categoryList.remove(catList);
        System.out.println("Registro de la categoria eliminada");

    }


    /**
     * Metodo main
     */
    public static void main(String[] args){

        /* Ejecucion del programa */

        

    }


}