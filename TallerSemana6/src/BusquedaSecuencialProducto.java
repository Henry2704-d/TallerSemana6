import java.util.List;

public class BusquedaSecuencialProducto implements BusquedaProducto {

    @Override
    public int buscarPorId(List<Producto> productos, int id) {
        // Busqueda Secuencual: recorre uno por uno
        for (int i = 0; i < productos.size(); i++) {
            if (productos.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int buscarPorNombre(List<Producto> productos, String nombre) {
        for (int i = 0; i < productos.size(); i++) {
            if (productos.get(i).getNombre().equalsIgnoreCase(nombre)) {
                return i;
            }
        }
        return -1;
    }
}
