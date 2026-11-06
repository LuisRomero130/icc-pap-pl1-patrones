package icc.pap.pl1.patrones.prototype;

import java.math.BigDecimal;

import icc.pap.pl1.patrones.modelo.TipoProducto;

public class Cafe extends ProductoBase{

    public Cafe(String id) {
        super(id, TipoProducto.Cafe, "Cafe", new BigDecimal("2.20"));
        
    }

    @Override
    public ProductoBase copiar(String nuevoID) {
        return new Cafe(nuevoID);

        
    }


   

}
