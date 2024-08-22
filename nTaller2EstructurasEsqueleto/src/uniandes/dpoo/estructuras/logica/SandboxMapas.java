package uniandes.dpoo.estructuras.logica;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;


/**
 * Esta clase tiene un conjunto de métodos para practicar operaciones sobre mapas.
 *
 * Todos los métodos deben operar sobre el atributo mapaCadenas que se declara como un Map.
 * 
 * En este mapa, las llaves serán cadenas y los valores serán también cadenas. La relación entre los dos será que cada llave será igual a la cadena del valor, pero invertida.
 * 
 * El objetivo de usar el tipo Map es que sólo puedan usarse métodos de esa interfaz y no métodos adicionales provistos por la implementación concreta (HashMap).
 * 
 * No pueden agregarse nuevos atributos.
 */
public class SandboxMapas
{
    /**
     * Un mapa de cadenas para realizar varias de las siguientes operaciones.
     * 
     * Las llaves del mapa son cadenas, así como los valores.
     * 
     * Las llaves corresponden a invertir la cadena que aparece asociada a cada llave.
     */
    private Map<String, String> mapaCadenas;

    /**
     * Crea una nueva instancia de la clase con las dos listas inicializadas pero vacías
     */
    public SandboxMapas( )
    {
        mapaCadenas = new HashMap<String, String>( );
    }

    /**
     * Retorna una lista con las cadenas del mapa (los valores) ordenadas lexicográficamente
     * @return Una lista ordenada con las cadenas que conforman los valores del mapa
     */
    public List<String> getValoresComoLista() {
        String[] valores = mapaCadenas.values().toArray(new String[0]);

        // Implementación bubble sort
        for (int i = 0; i < valores.length - 1; i++) {
            for (int j = 0; j < valores.length - i - 1; j++) {
                if (valores[j].compareTo(valores[j + 1]) > 0) {
                    String temp = valores[j];
                    valores[j] = valores[j + 1];
                    valores[j + 1] = temp;
                }
            }
        }

        List<String> listaOrdenada = new ArrayList<>();
        for (String valor : valores) {
            listaOrdenada.add(valor);
        }

        return listaOrdenada;
    }


    /**
     * Retorna una lista con las llaves del mapa ordenadas lexicográficamente de mayor a menor
     * @return Una lista ordenada con las cadenas que conforman las llaves del mapa
     */
    public List<String> getLlavesComoListaInvertida() {
        String[] llaves = mapaCadenas.keySet().toArray(new String[0]);

        // Implementación de bubble sort y luego inversión
        for (int i = 0; i < llaves.length - 1; i++) {
            for (int j = 0; j < llaves.length - i - 1; j++) {
                if (llaves[j].compareTo(llaves[j + 1]) < 0) {
                    String temp = llaves[j];
                    llaves[j] = llaves[j + 1];
                    llaves[j + 1] = temp;
                }
            }
        }

        List<String> listaInvertida = new ArrayList<>();
        for (String llave : llaves) {
            listaInvertida.add(llave);
        }

        return listaInvertida;
    }


    /**
     * Retorna la cadena que sea lexicográficamente menor dentro de las llaves del mapa .
     * 
     * Si el mapa está vacío, debe retornar null.
     * @return
     */
    public String getPrimera() {
        if (mapaCadenas.isEmpty()) {
            return null;
        }

        String minimaLlave = null;
        for (String llave : mapaCadenas.keySet()) {
            if (minimaLlave == null || llave.compareTo(minimaLlave) < 0) {
                minimaLlave = llave;
            }
        }

        return minimaLlave;
    }


    /**
     * Retorna la cadena que sea lexicográficamente mayor dentro de los valores del mapa
     * 
     * Si el conjunto está vacío, debe retornar null.
     * @return
     */
    public String getUltima() {
        if (mapaCadenas.isEmpty()) {
            return null;
        }

        String maximaLlave = null;
        for (String valor : mapaCadenas.values()) {
            if (maximaLlave == null || valor.compareTo(maximaLlave) > 0) {
                maximaLlave = valor;
            }
        }

        return maximaLlave;
    }


    /**
     * Retorna una colección con las llaves del mapa, convertidas a mayúsculas.
     * 
     * El orden de las llaves retornadas no importa.
     * @return Una lista de cadenas donde todas las cadenas están en mayúsculas
     */
    public Collection<String> getLlaves( )
    {
        Collection<String> llavesMayusculas = new ArrayList<>();
        for (String llave : mapaCadenas.keySet()) {
        	llavesMayusculas.add(llave.toUpperCase());
        }
        
        return llavesMayusculas;
    }

    /**
     * Retorna la cantidad de *valores* diferentes en el mapa
     * @return
     */
    public int getCantidadCadenasDiferentes() {
        List<String> valoresUnicos = new ArrayList<>();
        for (String valor : mapaCadenas.values()) {
            if (!valoresUnicos.contains(valor)) {
                valoresUnicos.add(valor);
            }
        }
        return valoresUnicos.size();
    }


    /**
     * Agrega un nuevo valor al mapa de cadenas: el valor será el recibido por parámetro, y la llave será la cadena invertida
     * 
     * Este método podría o no aumentar el tamaño del mapa, dependiendo de si ya existía la cadena en el mapa
     * 
     * @param cadena La cadena que se va a agregar al mapa
     */
    public void agregarCadena( String cadena )
    {
    	String llave = new StringBuilder(cadena).reverse().toString();
    	
    	mapaCadenas.put(llave, cadena);

    }

    /**
     * Elimina una cadena del mapa, dada la llave
     * @param cadena La llave para identificar el valor que se debe eliminar
     */
    public void eliminarCadenaConLLave( String llave )
    {
    	mapaCadenas.remove(llave);
    }

    /**
     * Elimina una cadena del mapa, dado el valor
     * @param cadena El valor que se debe eliminar
     */
    public void eliminarCadenaConValor( String valor )
    {
        String llave = null;
        for (Map.Entry<String, String> entry : mapaCadenas.entrySet()) {
            if (entry.getValue().equals(valor)) {
                llave = entry.getKey();
                break;
            }
        }
        if (llave != null) {
            mapaCadenas.remove(llave);
        }
    }

    /**
     * Reinicia el mapa de cadenas con las representaciones como Strings de los objetos contenidos en la lista del parámetro 'objetos'.
     * 
     * Use el método toString para convertir los objetos a cadenas.
     * @param valores Una lista de objetos
     */
    public void reiniciarMapaCadenas( List<Object> objetos )
    {
    	mapaCadenas.clear(); // Limpiar el mapa actual
    	
    	for (Object obj : objetos) {
    		String valor = obj.toString();
    		String llave = new StringBuilder(valor).reverse().toString();
    		mapaCadenas.put(llave,  valor);
    	}
    	
    	
    }

    /**
     * Modifica el mapa de cadenas reemplazando las llaves para que ahora todas estén en mayúsculas pero sigan conservando las mismas cadenas asociadas.
     */
    public void volverMayusculas( )
    {
    	Map<String, String> nuevoMapa = new HashMap<>();
    	for (Map.Entry<String, String> entry : mapaCadenas.entrySet()) {
    		String llaveMayusculas = entry.getKey().toUpperCase();
    		nuevoMapa.put(llaveMayusculas, entry.getValue());
    	}
    	
    	mapaCadenas = nuevoMapa;
    }

    /**
     * Verifica si todos los elementos en el arreglo de cadenas del parámetro hacen parte del mapa de cadenas (de los valores)
     * @param otroArreglo El arreglo de enteros con el que se debe comparar
     * @return True si todos los elementos del arreglo están dentro de los valores del mapa
     */
    public boolean compararValores( String[] otroArreglo )
    {
        for (String valor : otroArreglo) {
        	if (!mapaCadenas.containsValue(valor)) {
        		return false;
        	}
        }
        
        return true;
    }

}
