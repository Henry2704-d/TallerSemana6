import java.util.ArrayList;
import java.util.List;

public class Tienda {
    private List<Producto> listado;
    private BusquedaProducto buscador;

    public Tienda(BusquedaProducto buscador) {
        this.listado = new ArrayList<>();
        this.buscador = buscador;
    }

    public void agregar(Producto producto) {
        listado.add(producto);
    }

    public boolean actualizarPrecioPorId(int id, double nuevoPrecio) {
        int pos = buscador.buscarPorId(listado, id);
        if (pos != -1) {
            listado.get(pos).setPrecio(nuevoPrecio);
            return true;
        }
        return false;
    }

    public boolean registrarVenta(int id, int indiceMes, int cantidad) {
        int pos = buscador.buscarPorId(listado, id);
        if (pos != -1) {
            listado.get(pos).registrarVenta(indiceMes, cantidad);
            return true;
        }
        return false;
    }

    public Producto buscarPorId(int id) {
        int pos = buscador.buscarPorId(listado, id);
        if (pos != -1) {
            return listado.get(pos);
        }
        return null;
    }

    public Producto buscarPorNombre(String nombre) {
        int pos = buscador.buscarPorNombre(listado, nombre);
        if (pos != -1) {
            return listado.get(pos);
        }
        return null;
    }

    public String resumenVentasPorMes(int indiceMes) {
        StringBuilder sb = new StringBuilder();
        for (Producto p : listado) {
            sb.append(p.getNombre())
                    .append(": ")
                    .append(p.getVentasMes(indiceMes))
                    .append(" unidades\n");
        }
        return sb.toString();
    }

    //Busqueda por precio
    public Producto buscarPorPrecio(double precio) {
        for (Producto p : listado) {
            if (p.getPrecio() == precio) {   // secuencial, uno por uno
                return p;
            }
        }
        return null;
    }

    //Busqueda por Categoría
    public Producto buscarPorCategoria(String categoria) {
        return buscarPorNombre(categoria);
    }

    public List<Producto> todos() {
        return listado;
    }
}