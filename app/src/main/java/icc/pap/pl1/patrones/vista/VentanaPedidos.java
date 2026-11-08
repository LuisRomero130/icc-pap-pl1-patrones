package icc.pap.pl1.patrones.vista;

import icc.pap.pl1.patrones.modelo.Pedido;
import icc.pap.pl1.patrones.servicio.ServicioPedidos;    
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.math.RoundingMode;

public class VentanaPedidos extends JFrame {

    private static final Color AZUL_OSCURO = new Color(0x1E1B4B);
    private static final Color AZUL_CLARO  = new Color(0x2B2766);
    private static final Color MORADO      = new Color(0x7C5CFC);
    private static final Color AMARILLO    = new Color(0xFFC145);
    private static final Color FONDO       = new Color(0xF1F2F8);
    private static final Color LAVANDA     = new Color(0xEDE9FE);
    private static final Color GRIS        = new Color(0x6B7280);

    private final ServicioPedidos servicio = new ServicioPedidos();

    private final JComboBox<String> cmbIngrediente = new JComboBox<>(
            new String[]{"azucar", "crema", "tomate", "pollo", "queso", "pepperoni"});
    private final DefaultTableModel modelo = new DefaultTableModel(
            new String[]{"ID", "Producto", "Composición", "Total"}, 0);
    private final JTable tabla = new JTable(modelo);
    private final JLabel lblDetalle = new JLabel("Selecciona un pedido para ver su detalle.");
    private final JLabel lblTotal = new JLabel("$0.00");

    public VentanaPedidos() {
        super("PedidoLab - Prototype + Decorator");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(980, 620);
        setLocationRelativeTo(null);

        add(crearPanelIzquierdo(), BorderLayout.WEST);
        add(crearPanelDerecho(), BorderLayout.CENTER);
        configurarTabla();
    }

    private JPanel crearPanelIzquierdo() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(AZUL_OSCURO);
        panel.setBorder(new EmptyBorder(20, 16, 20, 16));
        panel.setPreferredSize(new Dimension(260, 0));

        panel.add(etiqueta("PedidoLab", 22, Color.WHITE, true));
        panel.add(etiqueta("Prototype + Decorator", 12, Color.LIGHT_GRAY, false));
        panel.add(Box.createVerticalStrut(24));

        panel.add(etiqueta("Producto base", 13, Color.WHITE, true));
        panel.add(Box.createVerticalStrut(8));
        panel.add(botonProducto("Café", "cafe"));
        panel.add(Box.createVerticalStrut(8));
        panel.add(botonProducto("Ensalada", "ensalada"));
        panel.add(Box.createVerticalStrut(8));
        panel.add(botonProducto("Pizza", "pizza"));
        panel.add(Box.createVerticalStrut(24));

        panel.add(etiqueta("Ingrediente", 13, Color.WHITE, true));
        panel.add(Box.createVerticalStrut(8));
        cmbIngrediente.setAlignmentX(Component.LEFT_ALIGNMENT);
        cmbIngrediente.setMaximumSize(new Dimension(Integer.MAX_VALUE, 36));
        panel.add(cmbIngrediente);
        panel.add(Box.createVerticalStrut(8));

        JButton btnAgregar = boton("Agregar ingrediente", AMARILLO, AZUL_OSCURO, 44);
        btnAgregar.addActionListener(e -> agregarIngrediente());
        panel.add(btnAgregar);
        panel.add(Box.createVerticalStrut(8));

        JButton btnReiniciar = boton("Reiniciar pedidos", AZUL_OSCURO, Color.WHITE, 40);
        btnReiniciar.setBorderPainted(true);
        btnReiniciar.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        btnReiniciar.addActionListener(e -> reiniciar());
        panel.add(btnReiniciar);

        panel.add(Box.createVerticalGlue());

        JPanel compatibles = new JPanel();
        compatibles.setLayout(new BoxLayout(compatibles, BoxLayout.Y_AXIS));
        compatibles.setBackground(AZUL_CLARO);
        compatibles.setBorder(new EmptyBorder(10, 12, 10, 12));
        compatibles.setAlignmentX(Component.LEFT_ALIGNMENT);
        compatibles.setMaximumSize(new Dimension(Integer.MAX_VALUE, 110));
        compatibles.add(etiqueta("Compatibles", 13, Color.WHITE, true));
        compatibles.add(etiqueta("Café: Azúcar, Crema", 12, Color.WHITE, false));
        compatibles.add(etiqueta("Ensalada: Tomate, Pollo", 12, Color.WHITE, false));
        compatibles.add(etiqueta("Pizza: Queso, Pepperoni", 12, Color.WHITE, false));
        panel.add(compatibles);

        return panel;
    }

    private JButton botonProducto(String texto, String clave) {
        JButton b = boton(texto, MORADO, Color.WHITE, 56);
        b.addActionListener(e -> clonar(clave));
        return b;
    }

    private JPanel crearPanelDerecho() {
        JPanel panel = new JPanel(new BorderLayout(0, 16));
        panel.setBackground(FONDO);
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JPanel tarjetaPedidos = tarjeta();
        tarjetaPedidos.add(etiqueta("Pedidos creados", 18, AZUL_OSCURO, true), BorderLayout.NORTH);
        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBorder(BorderFactory.createEmptyBorder());
        scroll.getViewport().setBackground(Color.WHITE);
        tarjetaPedidos.add(scroll, BorderLayout.CENTER);

        JPanel tarjetaDetalle = tarjeta();
        tarjetaDetalle.add(etiqueta("Detalle del pedido", 18, AZUL_OSCURO, true), BorderLayout.NORTH);
        lblDetalle.setForeground(GRIS);
        lblDetalle.setFont(lblDetalle.getFont().deriveFont(14f));
        tarjetaDetalle.add(lblDetalle, BorderLayout.CENTER);

        JPanel barraTotal = new JPanel(new BorderLayout());
        barraTotal.setBackground(AZUL_OSCURO);
        barraTotal.setBorder(new EmptyBorder(14, 18, 14, 18));
        barraTotal.add(etiqueta("Total del pedido", 14, Color.WHITE, true), BorderLayout.WEST);
        lblTotal.setForeground(AMARILLO);
        lblTotal.setFont(lblTotal.getFont().deriveFont(Font.BOLD, 30f));
        barraTotal.add(lblTotal, BorderLayout.EAST);
        tarjetaDetalle.add(barraTotal, BorderLayout.SOUTH);

        panel.add(tarjetaPedidos, BorderLayout.CENTER);
        panel.add(tarjetaDetalle, BorderLayout.SOUTH);
        return panel;
    }

    private JPanel tarjeta() {
        JPanel p = new JPanel(new BorderLayout(0, 12));
        p.setBackground(Color.WHITE);
        p.setBorder(new EmptyBorder(16, 18, 16, 18));
        return p;
    }

    private void configurarTabla() {
        tabla.setRowHeight(34);
        tabla.setShowGrid(false);
        tabla.setFont(tabla.getFont().deriveFont(14f));
        tabla.setForeground(AZUL_OSCURO);
        tabla.setSelectionBackground(LAVANDA);
        tabla.setSelectionForeground(AZUL_OSCURO);
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabla.setDefaultEditor(Object.class, null);
        tabla.getTableHeader().setBackground(Color.WHITE);
        tabla.getTableHeader().setForeground(GRIS);
        tabla.getTableHeader().setReorderingAllowed(false);

        tabla.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                mostrarDetalle();
            }
        });
    }

    private void clonar(String clave) {
        try {
            servicio.agregarIngrediente(clave);
            refrescarTabla();
            int ultima = modelo.getRowCount() - 1;
            tabla.setRowSelectionInterval(ultima, ultima);
        } catch (IllegalArgumentException ex) {
            mostrarError(ex.getMessage());
        }
    }

    private void agregarIngrediente() {
        int fila = tabla.getSelectedRow();
        String id = null;
        if (fila >= 0) {
            id = (String) modelo.getValueAt(fila, 0);
        }
        try {
            servicio.agregarIngrediente(id, (String) cmbIngrediente.getSelectedItem());
            refrescarTabla();
            tabla.setRowSelectionInterval(fila, fila);
        } catch (IllegalArgumentException | IllegalStateException ex) {
            mostrarError(ex.getMessage());
        }
    }

    private void reiniciar() {
        servicio.reiniciar();
        refrescarTabla();
    }

    private void refrescarTabla() {
        modelo.setRowCount(0);
        for (Pedido p : servicio.listarPedidos()) {
            modelo.addRow(new Object[]{
                p.id(),
                p.tipo(),
                p.descripcion(),
                "$" + p.precio().setScale(2, RoundingMode.HALF_UP)
            });
        }
    }

    private void mostrarDetalle() {
        int fila = tabla.getSelectedRow();
        if (fila < 0) {
            lblDetalle.setText("Selecciona un pedido para ver su detalle.");
            lblTotal.setText("$0.00");
            return;
        }
        Pedido p = servicio.listarPedidos().get(fila);
        lblDetalle.setText(p.id() + "  -  " + p.descripcion());
        lblTotal.setText("$" + p.precio().setScale(2, RoundingMode.HALF_UP));
    }

    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Operación rechazada",
                JOptionPane.WARNING_MESSAGE);
    }

    private JButton boton(String texto, Color fondo, Color letra, int alto) {
        JButton b = new JButton(texto);
        b.setBackground(fondo);
        b.setForeground(letra);
        b.setFont(b.getFont().deriveFont(Font.BOLD, 15f));
        b.setFocusPainted(false);
        b.setBorderPainted(false);
        b.setContentAreaFilled(false);
        b.setOpaque(true);
        b.setAlignmentX(Component.LEFT_ALIGNMENT);
        b.setMaximumSize(new Dimension(Integer.MAX_VALUE, alto));
        return b;
    }

    private JLabel etiqueta(String texto, float tamano, Color color, boolean negrita) {
        JLabel l = new JLabel(texto);
        l.setFont(l.getFont().deriveFont(negrita ? Font.BOLD : Font.PLAIN, tamano));
        l.setForeground(color);
        l.setAlignmentX(Component.LEFT_ALIGNMENT);
        return l;
    }
}