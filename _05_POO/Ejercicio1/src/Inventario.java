import javax.swing.*;
import java.util.ArrayList;

public class Inventario {

    private ArrayList<ProductoEspecifico> listaProductos;

    public Inventario() {
        this.listaProductos = new ArrayList<>();
    }

    public void agregarProducto(ProductoEspecifico producto) {
        this.listaProductos.add(producto);
    }

    public boolean eliminarProducto(int id) {
        return this.listaProductos.removeIf(producto -> producto.getId() == id);
    }

    public Object mostrarProductos() {
        for (ProductoEspecifico iterador : this.listaProductos){
            JOptionPane.showMessageDialog(null,iterador.toString());
        }
        return null;
    }

    public ProductoEspecifico buscarPorNombre(String nombreBuscar){
        for (ProductoEspecifico iterador : this.listaProductos){
            if(iterador.getNombre().equalsIgnoreCase(nombreBuscar)){
                return iterador;
            }
        }
        return null;
    }

    public ProductoEspecifico buscarPorCategoria(String categoriaBuscar){
        for (ProductoEspecifico iterador : this.listaProductos){
            if(iterador.getCategoria().equalsIgnoreCase(categoriaBuscar)){
                return iterador;
            }
        }

        return null;
    }




}
