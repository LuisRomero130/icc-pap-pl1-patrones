package icc.pap.pl1.patrones.decorador;

import java.math.BigDecimal;

import icc.pap.pl1.patrones.modelo.Pedido;

public class Crema extends DecoradorIngrediente{

    public Crema(Pedido pedido) {
        super(pedido);
        
    }
     @Override 
    public String descripcion(){
        return pedido.descripcion() + " con crema";
    }
    @Override 
    public BigDecimal precio(){
        return pedido.precio().add(new BigDecimal("1.20"));
        
    }
    
}
