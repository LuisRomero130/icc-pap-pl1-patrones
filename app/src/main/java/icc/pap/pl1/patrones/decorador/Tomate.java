package icc.pap.pl1.patrones.decorador;

import java.math.BigDecimal;

import icc.pap.pl1.patrones.modelo.Pedido;

public class Tomate extends DecoradorIngrediente {

    public Tomate(Pedido pedido) {
        super(pedido);
     
    }
     @Override 
    public String descripcion(){
        return pedido.descripcion() + " con tomate";
    }
    @Override 
    public BigDecimal precio(){
        return pedido.precio().add(new BigDecimal("1.10"));
        
    }
    
}
