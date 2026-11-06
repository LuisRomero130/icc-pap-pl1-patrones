package icc.pap.pl1.patrones.decorador;

import java.math.BigDecimal;

import icc.pap.pl1.patrones.modelo.Pedido;

public class Pepperoni extends DecoradorIngrediente {

    public Pepperoni(Pedido pedido) {
        super(pedido);
       
    }
     @Override 
    public String descripcion(){
        return pedido.descripcion() + "con pepperoni";
    }
    @Override 
    public BigDecimal precio(){
        return pedido.precio().add(new BigDecimal("1.10"));
        
    }
    
}
