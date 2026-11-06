package icc.pap.pl1.patrones.prototype;

import java.math.BigDecimal;

import icc.pap.pl1.patrones.modelo.TipoProducto;

public class Pizza extends ProductoBase {

    public Pizza(String id) {
        super(id, TipoProducto.Pizza, "Pizza", new BigDecimal("5.00"));
        
    }

    @Override
    public ProductoBase copiar(String nuevoID) {
        return new Pizza(nuevoID);
    }
       

    
}
