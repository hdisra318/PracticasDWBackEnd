import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Clase CategoryMethods que implementa los metodos pedidos
 * 
 * @author Israel Hernandez Dorantes - 318206604
 * @version 1.0
 */
public class CategoryMethods {

    /**
     * Guarda las categorias registradas
     */
    HashMap<Integer, Category> categoryList = new HashMap<>();

    /**
     * Metodo createCategory que registra los datos de un objeto Category
     * @param category
     */
    void createCategory(Category category){

        if(!categoryList.isEmpty() && categoryList.containsKey(category.category_id)){
            System.out.println("- El id proporcionado ya fue registrado\n");
            return;
        }

        //Agregando al hash map global
        categoryList.put(category.category_id, category);
        System.out.println("-> Categoria agregada con exito\n");

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
        System.out.println("-> Lista de categorias registradas:");
        categoryList.forEach((id, categoria) -> {
            System.out.println("--> Id: "+id.intValue()+
            "\nCategoria: "+categoria.category+
            "\nAcronimo: "+categoria.acronym);
        });
        
    }

    /**
     * Metodo getCategory que recibe desde la consola el id de una categoria y 
     * muestra sus datos (category_id, category, acronym)
     * 
     * @param category_id id de la categoria
     */
    void getCategory(Integer category_id){

        if(categoryList.isEmpty()){
            System.out.println("- No existen categorias registradas\n");
            return;
        }

        Category cate = categoryList.getOrDefault(category_id, null);
        if(cate == null){
            System.out.println("- No existe una categoria con el id ingresado\n");
            return;
        }
        
        System.out.println("-> Los datos de la categoria solicitada son:");
        System.out.println("Id: "+category_id.intValue());
        System.out.println("Categoria: "+cate.category);
        System.out.println("Acronimo: "+cate.acronym);

    }

    /**
     * Metodo deleteCategory que recibe desde la consola el id de una 
     * categoria y elimina su registro de la lista
     * 
     * @param category_id id de la categoria a eliminar
     */
    void deleteCategory(Integer category_id){

        if(categoryList.isEmpty()){
            System.out.println("- No existen categorias registradas\n");
            return;
        }

        if(!categoryList.containsKey(category_id)){
            System.out.println("- No existe una categoria con el id ingresado\n");
            return;
        }

        categoryList.remove(category_id);
        System.out.println("-> Categoria eliminada del registro\n");

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
                sc.nextLine();
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
                        sc.nextLine();
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
                        sc.nextLine();
                    }catch(InputMismatchException ime){
                        System.out.println("\nPor favor escribe un id valido");
                        //Vaciando el buffer
                        sc.nextLine();
                        continue;
                    }
                    System.out.println("\n-> Estatus:");
                    cm.getCategory(Integer.valueOf(id));
                    break;

                case 4:
                    System.out.print("\n-> Ingresa el id de la categoria a buscar:  ");
                    try{
                        id = sc.nextInt();
                        sc.nextLine();
                    }catch(InputMismatchException ime){
                        System.out.println("\nPor favor escribe un id valido");
                        //Vaciando el buffer
                        sc.nextLine();
                        continue;
                    }
                    System.out.println("\n-> Estatus:");
                    cm.deleteCategory(Integer.valueOf(id));
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