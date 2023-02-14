import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Clase CategoryMethods que implementa los métodos pedidos
 * 
 * @author Israel Hernandez Dorantes - 318206604
 * @version 1.0
 */
public class CategoryMethods {

    /**
     * Lista de categorias registradas
     */
    LinkedList<LinkedList<String>> categoryList = new LinkedList<>();

    /**
     * Metodo createCategory que registra los datos de un objeto Category
     * @param category
     */
    void createCategory(Category category){

        if(!categoryList.isEmpty() && inCategoryList(category.category_id.intValue()) != null){
            System.out.println("- El id proporcionado ya fue registrado\n");
            return;
        }

        //Agregando los datos del objeto a la lista
        LinkedList<String> categoria = new LinkedList<>();
        categoria.add(String.valueOf(category.category_id));
        categoria.add(category.category);
        categoria.add(category.acronym);

        //Agregando la lista a la lista global
        categoryList.add(categoria);
        System.out.println("- Categoria agregada con exito\n");

    }


    /**
     * Metodo getCategories muestra en consola una lista con todas 
     * las categorias registradas
     */
    void getCategories(){

        if(categoryList.isEmpty()){
            System.out.println("- No existen categorias registradas\n");
            return;
        }

        // Imprimiendo las categorias de la lista
        System.out.println("->Lista de categorias registradas:");
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

        if(categoryList.isEmpty()){
            System.out.println("- No existen categorias registradas\n");
            return;
        }

        LinkedList<String> catList = inCategoryList(category_id);
        if(catList == null){
            System.out.println("- No existe una categoria con el id ingresado\n");
            return;
        }

        System.out.println("-> Los datos de la categoria solicitada son:");
        System.out.println("Id: "+category_id);
        System.out.println("Categoria: "+catList.get(1));
        System.out.println("Acronimo: "+catList.get(2));

    }

    /**
     * Metodo deleteCategory que recibe desde la consola el id de una 
     * categoría y elimina su registro de la lista
     * 
     * @param category_id id de la categoria a eliminar
     */
    void deleteCategory(int category_id){

        if(categoryList.isEmpty()){
            System.out.println("- No existen categorias registradas\n");
            return;
        }

        LinkedList<String> catList = inCategoryList(category_id);
        if(catList == null){
            System.out.println("- No existe una categoria con el id ingresado");
            return;
        }

        categoryList.remove(catList);
        System.out.println("-> Registro de la categoria eliminada\n");

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

        for(LinkedList<String> l : categoryList){

            if(l.get(0).equals(""+category_id)){
                listFound = l;
                break;
            }

        }

        return listFound;
    }



    /**
     * Metodo main
     */
    public static void main(String[] args){

        /* Ejecucion del programa */

        Scanner sc = new Scanner(System.in);
        CategoryMethods cm = new CategoryMethods();

        /* Menu */
        boolean salir = false;
        int opc, id;
        while(!salir){

            System.out.println("\n\n\t******** Menu ********");
            System.out.println("--> 1. Crear una categoria");
            System.out.println("--> 2. Mostrar la lista de categorias registradas "+
            "hasta el momento");
            System.out.println("--> 3. Mostrar una categoria registrada dado su id");
            System.out.println("--> 4. Eliminar una categoria registrada");
            System.out.println("--> 5. Salir");
            System.out.print("Opicion:  ");

            try{
                opc = sc.nextInt();
            }catch(InputMismatchException ime){
                System.out.println("\nPor favor escribe una opcion valida");
                //Vaciando el buffer
                sc.nextLine();
                continue;
            }



            switch(opc){

                case 1:
                    System.out.print("\n-> Ingresa el id de la categoria:  ");
                    try{
                        id = sc.nextInt();
                    }catch(InputMismatchException ime){
                        System.out.println("\nPor favor escribe un id valido");
                        //Vaciando el buffer
                        sc.nextLine();
                        continue;
                    }
                    System.out.print("-> Ingresa la categoria:  ");
                    String categoria = sc.nextLine();
                    System.out.print("-> Ingresa el acronimo de la categoria:  ");
                    String acronim = sc.nextLine();
                    System.out.println("\n-> Estatus:");
                    cm.createCategory(new Category(Integer.valueOf(id), categoria, acronim));
                    break;

                case 2:
                    System.out.println("\n-> Estatus:");
                    cm.getCategories();
                    break;

                case 3:
                    System.out.print("\n-> Ingresa el id de la categoria a buscar:  ");
                    try{
                        id = sc.nextInt();
                    }catch(InputMismatchException ime){
                        System.out.println("\nPor favor escribe un id valido");
                        //Vaciando el buffer
                        sc.nextLine();
                        continue;
                    }
                    System.out.println("\n-> Estatus:");
                    cm.getCategory(id);
                    break;

                case 4:
                    System.out.print("\n-> Ingresa el id de la categoria a buscar:  ");
                    try{
                        id = sc.nextInt();
                    }catch(InputMismatchException ime){
                        System.out.println("\nPor favor escribe un id valido");
                        //Vaciando el buffer
                        sc.nextLine();
                        continue;
                    }
                    System.out.println("\n-> Estatus:");
                    cm.deleteCategory(id);
                    break;

                case 5:
                    System.out.println("\t******** Hasta pronto ********\n\n");
                    salir = true;
                    break;

                default:
                    System.out.println("\n--> Opcion no valida, intenta de nuevo\n");
                    break;
            }

            
        }

    }


}