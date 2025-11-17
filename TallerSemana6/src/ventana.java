//Henry Olmedo, Guillermo Vaca, David Jácome, Gregory Jimenez, Israel Pilla

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ventana {
    private JPanel principal;
    private JTabbedPane lstListado;
    private JComboBox<String> cboProducto;
    private JTextField txtPrecio;
    private JSpinner spiCantidad;
    private JButton btnComprar;
    private JButton btnEditar;
    private JList<String> list1;
    private JButton btnListar;

    // Pestaña BUSCAR
    private JTextField txtBusquedaPrecio;
    private JTextArea txtBuscarPrecio;
    private JButton btnBuscarPrecio;
    private JComboBox cboCategoriasBusqueda;
    private JButton btnBuscarCategoria;
    private JTextArea txtBuscarCategoria;
    private JTextField txtID;
    private JButton btnBuscarID;
    private JTextArea txtBuscarID;
    private JComboBox cboMes;
    private JComboBox cboBuscarMes;
    private JTextArea txtBuscarMes;
    private JButton btnBuscarMes;

    private Tienda tienda;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Tienda Online");
        frame.setContentPane(new ventana().principal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public ventana() {
        // 1. Configurar spinner para que no se salga de las proporciones
        SpinnerNumberModel snm = new SpinnerNumberModel(1, 1, 100, 1);
        spiCantidad.setModel(snm);

        // 2. Crear tienda y productos usando búsqueda secuencial
        tienda = new Tienda(new BusquedaSecuencialProducto());
        tienda.agregar(new Producto(1, "Carne", 5.50));
        tienda.agregar(new Producto(2, "Pollo", 4.25));
        tienda.agregar(new Producto(3, "Pescado", 6.00));

        // 3. Cuando cambie el producto del combobox, muestra su precio automaticamente
        cboProducto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarPrecioProductoSeleccionado();
            }
        });

        // 3. Mostrar precio del primero al iniciar
        mostrarPrecioProductoSeleccionado();

        // 4. Botón comprar = registrar venta en el mes que se elige
        btnComprar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombreProd = cboProducto.getSelectedItem().toString();
                Producto p = tienda.buscarPorNombre(nombreProd);
                if (p == null) {
                    JOptionPane.showMessageDialog(null,
                            "Producto no encontrado.");
                    return;
                }

                int cantidad = Integer.parseInt(spiCantidad.getValue().toString());

                try {
                    // índice del mes según el combo (0 = Marzo, 1 = Abril, 2 = Mayo)
                    int indiceMes = cboMes.getSelectedIndex();

                    tienda.registrarVenta(p.getId(), indiceMes, cantidad);
                    JOptionPane.showMessageDialog(null,
                            "Compra registrada. Mes: " +
                                    cboMes.getSelectedItem() +
                                    " | Cantidad: " + cantidad);

                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });

        // 5. Botón editar = actualizar precio del producto seleccionado
        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombreProd = cboProducto.getSelectedItem().toString();
                Producto p = tienda.buscarPorNombre(nombreProd);
                if (p == null) {
                    JOptionPane.showMessageDialog(null,
                            "Producto no encontrado.");
                    return;
                }

                try {
                    double nuevoPrecio = Double.parseDouble(txtPrecio.getText());
                    p.setPrecio(nuevoPrecio);
                    JOptionPane.showMessageDialog(null,
                            "Precio actualizado para " + p.getNombre());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null,
                            "El precio debe ser numérico.");
                }
            }
        });

        // 6. Botón mostrar lista = mostrar productos en la pestaña Listado
        btnListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultListModel<String> dlm = new DefaultListModel<>();
                for (Producto p : tienda.todos()) {
                    dlm.addElement(p.toString());
                }
                list1.setModel(dlm);
            }
        });

        //Pestaña de Buscar

        //Buscar por precio
        btnBuscarPrecio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String textoPrecio = txtBusquedaPrecio.getText().trim();
                if (textoPrecio.isEmpty()) {
                    JOptionPane.showMessageDialog(null,
                            "Ingresa un precio para buscar.");
                    return;
                }

                try {
                    double precio = Double.parseDouble(textoPrecio);
                    Producto p = tienda.buscarPorPrecio(precio);
                    if (p != null) {
                        txtBuscarPrecio.setText(p.toString());
                    } else {
                        txtBuscarPrecio.setText(
                                "No se encontró producto con precio: " + precio);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null,
                            "El precio debe ser numérico.");
                }
            }
        });

        //Buscar por categoría
        btnBuscarCategoria.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String categoria = cboCategoriasBusqueda
                        .getSelectedItem().toString();

                Producto p = tienda.buscarPorCategoria(categoria);
                if (p != null) {
                    txtBuscarCategoria.setText(p.toString());
                } else {
                    txtBuscarCategoria.setText(
                            "No se encontró producto en la categoría: " + categoria);
                }
            }
        });

        //Buscar por ID
        btnBuscarID.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String textoId = txtID.getText().trim();
                if (textoId.isEmpty()) {
                    JOptionPane.showMessageDialog(null,
                            "Ingresa un ID para buscar.");
                    return;
                }

                try {
                    int id = Integer.parseInt(textoId);
                    Producto p = tienda.buscarPorId(id);
                    if (p != null) {
                        txtBuscarID.setText(p.toString());
                    } else {
                        txtBuscarID.setText(
                                "No se encontró producto con ID: " + id);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null,
                            "El ID debe ser numérico.");
                }
            }
        });

        //Buscar por mes (resumen de ventas por mes)
        btnBuscarMes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // índice del mes a buscar (0 = Marzo, 1 = Abril, 2 = Mayo)

                int indiceMes = cboBuscarMes.getSelectedIndex();

                String resumen = tienda.resumenVentasPorMes(indiceMes);
                txtBuscarMes.setText(
                        "Ventas del mes " + cboBuscarMes.getSelectedItem() + ":\n\n" +
                                resumen
                );
            }
        });
    }

    private void mostrarPrecioProductoSeleccionado() {
        String nombreProd = cboProducto.getSelectedItem().toString();
        Producto p = tienda.buscarPorNombre(nombreProd);
        if (p != null) {
            txtPrecio.setText(String.valueOf(p.getPrecio()));
        }
    }
}