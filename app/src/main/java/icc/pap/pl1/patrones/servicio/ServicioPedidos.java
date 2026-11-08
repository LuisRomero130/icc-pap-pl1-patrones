package icc.pap.pl1.patrones.servicio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import icc.pap.pl1.patrones.decorador.Azucar;
import icc.pap.pl1.patrones.decorador.Crema;
import icc.pap.pl1.patrones.decorador.Pepperoni;
import icc.pap.pl1.patrones.decorador.Pollo;
import icc.pap.pl1.patrones.decorador.Queso;
import icc.pap.pl1.patrones.decorador.Tomate;
import icc.pap.pl1.patrones.modelo.Pedido;
import icc.pap.pl1.patrones.modelo.TipoProducto;
import icc.pap.pl1.patrones.prototype.RegistroPrototipos;

public class ServicioPedidos {

    private final RegistroPrototipos registro = new RegistroPrototipos();
    private final List<Pedido> pedidos = new ArrayList<>();
    private final Map<String, Integer> indicePorId = new HashMap<>();

    /** Crea un nuevo pedido a partir del prototipo (Prototype). */
    public Pedido agregarIngrediente(String clave) {
        if (clave == null || clave.isBlank()) {
            throw new IllegalArgumentException("Clave de producto no válida");
        }
        Pedido nuevo = registro.clonar(clave, nuevoId(clave));
        pedidos.add(nuevo);
        indicePorId.put(nuevo.id(), pedidos.size() - 1);
        return nuevo;
    }

    /** Añade un ingrediente al pedido seleccionado, identificado por su id. */
    public Pedido agregarIngrediente(String id, String ingrediente) {
        if (id == null) {
            throw new IllegalStateException("No se ha seleccionado ningún pedido");
        }
        Integer indice = indicePorId.get(id);
        if (indice == null) {
            throw new IllegalStateException("No se ha encontrado el pedido: " + id);
        }
        Pedido actual = pedidos.get(indice);
        Pedido nuevo = agregarIngrediente(actual, ingrediente);
        pedidos.set(indice, nuevo);   // reemplaza en el mismo lugar → la tabla no se desalinea
        return nuevo;
    }

    public void reiniciar() {
        pedidos.clear();
        indicePorId.clear();
    }

    public List<Pedido> listarPedidos() {
        return new ArrayList<>(pedidos);   // copia para que nadie modifique la lista externamente
    }

    // ---- Lógica original (se mantiene igual) ----

    public Pedido agregarIngrediente(Pedido pedidoNuevo, String ingrediente) {
        if (pedidoNuevo == null) {
            throw new IllegalStateException("No se ha ingresado ningún pedido");
        }
        if (ingrediente == null) {
            throw new IllegalArgumentException("Ingrediente no reconocido: null");
        }
        switch (ingrediente.toLowerCase()) {
            case "azucar":
                comprobar(pedidoNuevo, TipoProducto.Cafe);
                return new Azucar(pedidoNuevo);
            case "crema":
                comprobar(pedidoNuevo, TipoProducto.Cafe);
                return new Crema(pedidoNuevo);
            case "tomate":
                comprobar(pedidoNuevo, TipoProducto.Ensalada);
                return new Tomate(pedidoNuevo);
            case "pollo":
                comprobar(pedidoNuevo, TipoProducto.Ensalada);
                return new Pollo(pedidoNuevo);
            case "queso":
                comprobar(pedidoNuevo, TipoProducto.Pizza);
                return new Queso(pedidoNuevo);
            case "pepperoni":
                comprobar(pedidoNuevo, TipoProducto.Pizza);
                return new Pepperoni(pedidoNuevo);
            default:
                throw new IllegalArgumentException("Ingrediente no reconocido: " + ingrediente);
        }
    }

    private void comprobar(Pedido pedido, TipoProducto tipoCorrecto) {
        if (pedido.tipo() != tipoCorrecto) {
            throw new IllegalArgumentException(
                "Ingrediente no compatible: el pedido es " + pedido.tipo()
                + " y el ingrediente requiere " + tipoCorrecto);
        }
    }

    private String nuevoId(String clave) {
        return clave.toUpperCase() + "-" + (pedidos.size() + 1);
    }
}
