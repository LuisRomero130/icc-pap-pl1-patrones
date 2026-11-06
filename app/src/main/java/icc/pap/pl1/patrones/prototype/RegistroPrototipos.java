package icc.pap.pl1.patrones.prototype;

import java.util.HashMap;

public class RegistroPrototipos {
    private final HashMap<String,ProductoBase> prototipos= new HashMap<>();

    public void registrar(String clave,ProductoBase prototipo){
        prototipos.put(clave,prototipo);

    }
    public RegistroPrototipos(){
        registrar("cafe", new Cafe("PROTOTIPO-CAFE"));
        registrar("ensalada", new Ensalada("PROTOTIPO-ENSALADA"));
        registrar("pizza",new Pizza("PROTOTIPO-PIZZA"));

    }
    public ProductoBase clonar(String clave, String nuevoID) {
        ProductoBase prototipo = prototipos.get(clave);
        if (prototipo == null) {
            throw new IllegalArgumentException("Clave inexistente en el registro: " + clave);
        }
        return prototipo.copiar(nuevoID);
    }
    public boolean esInstanciaRegistrada(ProductoBase producto) {
        return prototipos.containsValue(producto);
    }
}