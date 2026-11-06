package icc.pap.pl1.patrones.prototype;

import java.math.BigDecimal;

import icc.pap.pl1.patrones.modelo.TipoProducto;

public class Ensalada extends ProductoBase {

    public Ensalada(String id) {
        super(id, TipoProducto.Ensalada, "Ensalada", new BigDecimal("3.00"));
    
    }

    @Override
    public ProductoBase copiar(String nuevoID) {
        return new Ensalada(nuevoID);
        
    }
    
}
