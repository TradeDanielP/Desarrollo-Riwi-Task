import jdk.nashorn.internal.scripts.JO;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

            /*
            1. Agregar
            2. Eliminar
            3. Buscar Por Nombre
            4. Buscar por categoria
            5. Listar Inventario
            6. Salir
            */

        String option = "";

        Inventario objInventario = new Inventario();

        int id = 0;
        String nombre = "";
        double precio = 0;
        String categoria = "";
        String marca = "";

        int idEliminar = 0;

        String categtoriaBuscar = "";
        String marcaBuscar = "";

        do {

            option = JOptionPane.showInputDialog("---MENU--- \n" +
                    "1. Agregar Productos \n " +
                    "2. Eliminar Productos \n " +
                    "3. Buscar por Nombre \n " +
                    "4. Buscar por Categoria \n " +
                    "5. Listar Inventario \n " +
                    "6. Salir");
            switch (option){
                case "1":
                    id =  Integer.parseInt(JOptionPane.showInputDialog(null,"Ingrese el ID del producto"));
                    nombre = JOptionPane.showInputDialog(null,"Escriba el nombre del Producto");
                    precio = Double.parseDouble(JOptionPane.showInputDialog(null,"Digite el precio del producto"));
                    categoria = JOptionPane.showInputDialog(null,"Escriba la categoria del prodcuto");
                    marca = JOptionPane.showInputDialog(null,"Escriba la marca del producto");
                    ProductoEspecifico newProduct = new ProductoEspecifico(id,nombre,precio,categoria,marca);

                    objInventario.agregarProducto(newProduct);
                    break;

                case "2":
                    objInventario.mostrarProductos();
                    idEliminar = Integer.parseInt(JOptionPane.showInputDialog(null,"Digite el id del producto que desea eliminar"));
                    objInventario.eliminarProducto(idEliminar);
                    break;
                case "3":
                    categtoriaBuscar = JOptionPane.showInputDialog(null, "Escriba el nombre de la categoria que quiere buscar");

                    if (objInventario.buscarPorNombre(categtoriaBuscar) == null) {
                        JOptionPane.showMessageDialog(null,"No se encontró resultado");
                    } else {
                        JOptionPane.showMessageDialog(null,objInventario.buscarPorNombre(categtoriaBuscar));
                    }
                    break;
                case "4":
                    marcaBuscar = JOptionPane.showInputDialog(null, "Escriba el nombre de la marca que quiere buscar");


                    if (objInventario.buscarPorCategoria(marcaBuscar) == null) {
                        JOptionPane.showMessageDialog(null,"No se encontró resultado");
                    } else {
                        JOptionPane.showMessageDialog(null,objInventario.buscarPorCategoria(marcaBuscar));
                    }
                    break;
                case "5":
                    objInventario.mostrarProductos();
                    break;
            }
        } while (!option.equals("6"));

    }
}