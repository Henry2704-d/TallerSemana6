public class Producto {
    private int id;
    private String nombre;
    private double precio;
    private int[] ventasMensuales = new int[3];

    public Producto(int id, String nombre, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void registrarVenta(int indiceMes, int cantidad) {
        if (indiceMes < 0 || indiceMes >= ventasMensuales.length) {
            throw new IllegalArgumentException("Índice de mes inválido (usa 0, 1 o 2).");
        }
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor que 0.");
        }
        ventasMensuales[indiceMes] += cantidad;
    }

    public int getTotalVentas() {
        int total = 0;
        for (int v : ventasMensuales) total += v;
        return total;
    }

    public int getVentasMes(int indiceMes) {
        if (indiceMes < 0 || indiceMes >= ventasMensuales.length) {
            throw new IllegalArgumentException("Índice de mes inválido (0, 1 o 2).");
        }
        return ventasMensuales[indiceMes];
    }

    @Override
    public String toString() {
        return "Producto " +
                "| ID " + id +
                "| Nombre: " + nombre +
                "| Precio: " + precio +
                "| Ventas Marzo: " + ventasMensuales[0] + "| Ventas Abril: "+
                ventasMensuales[1] +  "| Ventas Mayo: " +
                ventasMensuales[2];
    }
}