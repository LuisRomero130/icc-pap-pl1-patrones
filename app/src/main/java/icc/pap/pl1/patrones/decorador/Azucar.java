package icc.pap.pl1.patrones.decorador;

import java.math.BigDecimal;

import icc.pap.pl1.patrones.modelo.Pedido;


public class Azucar extends DecoradorIngrediente {
    public Azucar(Pedido pedido) {
        super(pedido);
        
    }
    @Override 
    public String descripcion(){
        return pedido.descripcion() + " con azucar";
    }
    @Override 
    public BigDecimal precio(){
        return pedido.precio().add(new BigDecimal("0.50"));

    }

    

    
}
