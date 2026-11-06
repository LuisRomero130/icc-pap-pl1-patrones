package icc.pap.pl1.patrones.decorador;

import java.math.BigDecimal;

import icc.pap.pl1.patrones.modelo.Pedido;

public class Pollo extends DecoradorIngrediente{

    public Pollo(Pedido pedido) {
        super(pedido);
       
    }
     @Override 
    public String descripcion(){
        return pedido.descripcion() + " con pollo";
    }
    @Override 
    public BigDecimal precio(){
        return pedido.precio().add(new BigDecimal("1.50"));
        
    }
    
}
