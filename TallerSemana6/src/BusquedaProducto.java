import java.util.List;

public interface BusquedaProducto {
    int buscarPorId(List<Producto> productos, int id);
    int buscarPorNombre(List<Producto> productos, String nombre);
}