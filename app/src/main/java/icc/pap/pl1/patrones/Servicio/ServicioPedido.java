package icc.pap.pl1.patrones.servicio;

import icc.pap.pl1.patrones.decorador.Azucar;
import icc.pap.pl1.patrones.decorador.Crema;
import icc.pap.pl1.patrones.decorador.Pepperoni;
import icc.pap.pl1.patrones.decorador.Pollo;
import icc.pap.pl1.patrones.decorador.Queso;
import icc.pap.pl1.patrones.decorador.Tomate;
import icc.pap.pl1.patrones.modelo.Pedido;
import icc.pap.pl1.patrones.modelo.TipoProducto;

public class ServicioPedido {
    public Pedido agregarIngrediente(Pedido pedidoNuevo, String ingrediente){
        if(pedidoNuevo==null){

            throw new IllegalStateException("No se a ingresado ningun pedido");
            
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
                throw new IllegalArgumentException("Ingrediente no reconocido: "+ ingrediente);




            


           
        }


    }
    private void comprobar(Pedido pedido, TipoProducto tipoCorrecto) {
    if (pedido.tipo() != tipoCorrecto) {
        throw new IllegalArgumentException(
            "Ingrediente no compatible: el pedido es " + pedido.tipo()
            + " y el ingrediente requiere " + tipoCorrecto);
        }
    }
    

    
}
