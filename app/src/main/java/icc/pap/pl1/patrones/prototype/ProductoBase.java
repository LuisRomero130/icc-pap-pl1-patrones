package icc.pap.pl1.patrones.prototype;

import java.math.BigDecimal;

import icc.pap.pl1.patrones.modelo.Pedido;
import icc.pap.pl1.patrones.modelo.TipoProducto;

public abstract class ProductoBase implements Pedido , Prototipo<ProductoBase>{
    private final String id;
    private final TipoProducto tipo;
    private final String descripcion;
    private final BigDecimal precioBase;

    public ProductoBase(String id, TipoProducto tipo, String descripcion, BigDecimal precioBase) {
        this.id = id;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.precioBase = precioBase;
    }
    @Override 
    public String id(){
        return this.id;

    }
    @Override 
    public TipoProducto tipo(){
        return this.tipo;
    }
    @Override 
    public String descripcion(){
        return this.descripcion;
    }
    @Override 
    public BigDecimal precio(){
        return this.precioBase;
    }
    @Override 
    public abstract ProductoBase copiar(String nuevoID);

    

    
    
}
