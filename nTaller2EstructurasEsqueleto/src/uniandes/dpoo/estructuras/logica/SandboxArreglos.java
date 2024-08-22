package uniandes.dpoo.estructuras.logica;

import java.util.HashMap;

/**
 * Esta clase tiene un conjunto de métodos para practicar operaciones sobre arreglos de enteros y de cadenas.
 *
 * Todos los métodos deben operar sobre los atributos arregloEnteros y arregloCadenas.
 * 
 * No pueden agregarse nuevos atributos.
 * 
 * Implemente los métodos usando operaciones sobre arreglos (ie., no haga cosas como construir listas para evitar la manipulación de arreglos).
 */
public class SandboxArreglos
{
    /**
     * Un arreglo de enteros para realizar varias de las siguientes operaciones.
     * 
     * Ninguna posición del arreglo puede estar vacía en ningún momento.
     */
    private int[] arregloEnteros;

    /**
     * Un arreglo de cadenas para realizar varias de las siguientes operaciones
     * 
     * Ninguna posición del arreglo puede estar vacía en ningún momento.
     */
    private String[] arregloCadenas;

    /**
     * Crea una nueva instancia de la clase con los dos arreglos inicializados pero vacíos (tamaño 0)
     */
    public SandboxArreglos( )
    {
        arregloEnteros = new int[]{};
        arregloCadenas = new String[]{};
    }

    /**
     * Retorna una copia del arreglo de enteros, es decir un nuevo arreglo del mismo tamaño que contiene copias de los valores del arreglo original
     * @return Una copia del arreglo de enteros
     */
    public int[] getCopiaEnteros()
    {
        // Crear un nuevo arreglo del mismo tamaño que arregloEnteros
    	
    	int[] copia = new int[arregloEnteros.length];
    	
    	// Copiamos cada elemento del arreglo original al nuevo
    	
    	for (int i = 0; i < arregloEnteros.length; i++) {
    		copia[i] = arregloEnteros[i];
    	}
    	
    	// Devolvemos el nuevo arreglo que es un copia del original
    	
    	return copia;
    }

    /**
     * Retorna una copia del arreglo de cadenas, es decir un nuevo arreglo del mismo tamaño que contiene copias de los valores del arreglo original
     * @return Una copia del arreglo de cadenas
     */
    public String[] getCopiaCadenas( )
    {
    	// Creamos un nueoa rreglo del mismo tamaño que arregloCadenas
        String[] copia = new String[arregloCadenas.length];
        
        // Copiamos cada elemento del arreglo original al nuevo
        
        for (int i = 0; i < arregloCadenas.length; i++) {
        	copia[i] = arregloCadenas[i];
        }
        
        return copia;
    }

    /**
     * Retorna la cantidad de valores en el arreglo de enteros
     * @return
     */
    public int getCantidadEnteros( )
    {
        return arregloEnteros.length;
    }

    /**
     * Retorna la cantidad de valores en el arreglo de cadenas
     * @return
     */
    public int getCantidadCadenas( )
    {
        return arregloCadenas.length;
    }

    /**
     * Agrega un nuevo valor al final del arreglo. Es decir que este método siempre debería aumentar en 1 la capacidad del arreglo.
     * 
     * @param entero El valor que se va a agregar.
     */
    public void agregarEntero( int entero )
    {
    	// Crear un nuevo arreglo con una capacidad adicional para el nuevo entero
    	int[] nuevoArreglo = new int[arregloEnteros.length + 1];
    	
    	// Copiar los elementos existentes al nuevo arreglo
    	for (int i = 0; i < arregloEnteros.length ; i++) {
    		nuevoArreglo[i] = arregloEnteros[i];
    	}
    	
    	// Añadir el nuevo entero al final del nuevo arreglo
    	nuevoArreglo[arregloEnteros.length] = entero;
    	
    	// Reasignar arregloEnteros al nuevo arreglo
    	arregloEnteros = nuevoArreglo;
    	
    }

    /**
     * Agrega un nuevo valor al final del arreglo. Es decir que este método siempre debería aumentar en 1 la capacidad del arreglo.
     * 
     * @param cadena La cadena que se va a agregar.
     */
    public void agregarCadena( String cadena )
    {
    	// Crear un nuevo arreglo con una capacidad adicional para la nueva cadena
    	
    	String[] nuevoArreglo = new String[arregloCadenas.length + 1];
    	
    	// Copiar los elementos existentes al nuevo arreglo
    	
    	for (int i = 0; i < arregloCadenas.length; i++) {
    		nuevoArreglo[i] = arregloCadenas[i];
    	}
    	
    	// Añadir la nueva cadena al final del nuevo arreglo
    	
    	nuevoArreglo[arregloCadenas.length] = cadena;
    	
    	// Reasignar arregloCadenas al nuevo arreglo
    	
    	arregloCadenas = nuevoArreglo;

    }

    /**
     * Elimina todas las apariciones de un determinado valor dentro del arreglo de enteros
     * @param valor El valor que se va eliminar
     */
    public void eliminarEntero( int valor )
    {
    	// Contar cuantos elementos no son igual al valor para dimensionar el nuevo arreglo correctamente
    	
    	int cuenta = 0;
    	for (int i = 0; i < arregloEnteros.length; i++) {
    		if (arregloEnteros[i] != valor) {
    			cuenta++;
    		}
    	}
    	
    	// Crear un nuevo arreglo con el tamaño de los elementos que no son igual al valor
    	int[] nuevoArreglo = new int[cuenta];
    	int indice = 0;
    	for (int i = 0; i < arregloEnteros.length; i++) {
    		if (arregloEnteros[i] != valor) {
    			nuevoArreglo[indice] = arregloEnteros[i];
    			indice++;
    		}
    	}
    	
    	// Reasignar arregloEnteros al nuevo arreglo
    	
    	arregloEnteros = nuevoArreglo;
    }

    /**
     * Elimina todas las apariciones de un determinado valor dentro del arreglo de cadenas
     * @param cadena La cadena que se va eliminar
     */
    public void eliminarCadena( String cadena )
    {
    	// Contar cuantos elementos no son igual al valor para dimensionar el nuevo arreglo correctamente
    	
    	int cuenta = 0;
    	for (int i = 0; i < arregloCadenas.length; i++) {
    		if (!arregloCadenas[i].equals(cadena)) {
    			cuenta++;
    		}
    	}
    	
    	// Crear un nuevo arreglo con el tamaño de los elementos que no son igual al valor
    	
    	String[] nuevoArreglo = new String[cuenta];
    	int indice = 0;
    	for (int i = 0; i < arregloCadenas.length; i++) {
    		if (!arregloCadenas[i].equals(cadena)) {
    			nuevoArreglo[indice] = arregloCadenas[i];
    			indice++;
    		}
    	}
    	
    	// Reasignar arregloCadenas al nuevo arreglo
    	
    	arregloCadenas = nuevoArreglo;
    }

    /**
     * Inserta un nuevo entero en el arreglo de enteros.
     * 
     * @param entero El nuevo valor que debe agregarse
     * @param posicion La posición donde debe quedar el nuevo valor en el arreglo aumentado. Si la posición es menor a 0, se inserta el valor en la primera posición. Si la
     *        posición es mayor que el tamaño del arreglo, se inserta el valor en la última posición.
     */
    public void insertarEntero( int entero, int posicion )
    {
    	// Crear un nuevo arreglo con una capacidad adicional para el nuevo entero
    	int[] nuevoArreglo = new int[arregloEnteros.length + 1];
    	
    	// Ajustar la posicion de insercion segun los tres casos posibles 
    	
    	int posInsertar;
    	
    	if (posicion < 0) {
    		posInsertar = 0;
    	} else if (posicion > arregloEnteros.length) {
    		posInsertar = arregloEnteros.length;
    	} else {posInsertar = posicion;
    	}
    	
    	// Copiar los elementos hasta la posicion de insercion
    	
    	for (int i = 0; i < posInsertar; i++) {
    		nuevoArreglo[i] = arregloEnteros[i];
    	}
    	
    	// Insertar el nuevo entero en la posicion indicada
    	
    	nuevoArreglo[posInsertar] = entero;
    	
    	// Copiar el resto de los elementos despues de la posicion insertada
    	
    	for (int i = posInsertar; i < arregloEnteros.length; i++) {
    		nuevoArreglo[i + 1] = arregloEnteros[i];
    	}
    	
    	// Reasignar arregloEnteros al nuevo arreglo
    	arregloEnteros = nuevoArreglo;
    }

    /**
     * Elimina un valor del arreglo de enteros dada su posición.
     * @param posicion La posición donde está el elemento que debe ser eliminado. Si el parámetro posicion no corresponde a ninguna posición del arreglo de enteros, el método
     *        no debe hacer nada.
     */
    public void eliminarEnteroPorPosicion( int posicion )
    {
    	// Verificar si la posicion es valida
    	if (posicion < 0 || posicion >= arregloEnteros.length ) {
    		return;
    	}
    	
    	// Crear un nuevo arreglo con un tamaño menor para eliminar el entero
    	
    	int[] nuevoArreglo = new int[arregloEnteros.length - 1];
    	
    	// Copiar elementos antes de la posicion especifiada
    	
    	for (int i = 0; i < posicion; i++) {
    		nuevoArreglo[i] = arregloEnteros[i];
    	}
    	
    	// Copiar elementos despues de la posicion especificada, omitiendo el elemento en la posicion
    	
    	for (int i = posicion + 1; i < arregloEnteros.length; i++) {
    		nuevoArreglo[i-1] = arregloEnteros[i];
    	}
    	
    	// Reasignar arregloEnteros al nuevo arreglo
    	
    	arregloEnteros = nuevoArreglo;
    }

    /**
     * Reinicia el arreglo de enteros con los valores contenidos en el arreglo del parámetro 'valores' truncados.
     * 
     * Es decir que si el valor fuera 3.67, en el nuevo arreglo de enteros debería quedar el entero 3.
     * @param valores Un arreglo de valores decimales.
     */
    public void reiniciarArregloEnteros( double[] valores )
    {
    	// Crear un nuevo arreglo de enteros con la misma longitud que el arreglo de valores
    	
    	int[] nuevoArreglo = new int[valores.length];
    	
    	// Convertir cada valor decimal a entero y almacenarlo en el nuevo arreglo
    	
    	for (int i = 0; i < valores.length; i++) {
    		nuevoArreglo[i] = (int) valores[i];
    	}
    	
    	// Reasignar arregloEnteros al nuevoa rreglo
    	
    	arregloEnteros = nuevoArreglo;
    }

    /**
     * Reinicia el arreglo de cadenas con las representaciones como Strings de los objetos contenidos en el arreglo del parámetro 'objetos'.
     * 
     * Use el método toString para convertir los objetos a cadenas.
     * @param valores Un arreglo de objetos
     */
    public void reiniciarArregloCadenas( Object[] objetos )
    {
    	// Crear un nuevo arreglo de cadena con la misma longitud que el arreglo de objetos
    	
    	String[] nuevoArreglo = new String[objetos.length];
    	
    	// Convertir cada aobjeto a cadena y almacenarlo en el nuevo arreglo
    	
    	for (int i = 0; i < objetos.length; i++) {
    		nuevoArreglo[i] = objetos[i].toString();
    	}
    	
    	// Reasignar arregloCadenas al nuevo arreglo
    	
    	arregloCadenas = nuevoArreglo;
    }

    /**
     * Modifica el arreglo de enteros para que todos los valores sean positivos.
     * 
     * Es decir que si en una posición había un valor negativo, después de ejecutar el método debe quedar el mismo valor muliplicado por -1.
     */
    public void volverPositivos( )
    {
    	// Recorrer el arreglo de enteros y cambiar los valores negativos a positivos
    	
    	for (int i = 0; i < arregloEnteros.length; i++) {
    		if (arregloEnteros[i] < 0) {
    			arregloEnteros[i] = -arregloEnteros[i];
    		}
    	}
    }

    /**
     * Modifica el arreglo de enteros para que todos los valores queden organizados de menor a mayor.
     */
    public void organizarEnteros() {
        int n = arregloEnteros.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arregloEnteros[j] > arregloEnteros[j + 1]) {
                    // Intercambiar arregloEnteros[j] y arregloEnteros[j+1]
                    int temp = arregloEnteros[j];
                    arregloEnteros[j] = arregloEnteros[j + 1];
                    arregloEnteros[j + 1] = temp;
                }
            }
        }
    }


    /**
     * Modifica el arreglo de cadenas para que todos los valores queden organizados lexicográficamente.
     */
    public void organizarCadenas() {
        int n = arregloCadenas.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arregloCadenas[j].compareTo(arregloCadenas[j + 1]) > 0) {
                    // Intercambiar arregloCadenas[j] y arregloCadenas[j+1]
                    String temp = arregloCadenas[j];
                    arregloCadenas[j] = arregloCadenas[j + 1];
                    arregloCadenas[j + 1] = temp;
                }
            }
        }
    }


    /**
     * Cuenta cuántas veces aparece el valor recibido por parámetro en el arreglo de enteros
     * @param valor El valor buscado
     * @return La cantidad de veces que aparece el valor
     */
    public int contarApariciones( int valor )
    {
        int conteo = 0;
        
        for (int i = 0; i < arregloEnteros.length; i++) {
        	if (arregloEnteros[i] == valor) {
        		conteo++;
        	}
        }
        
        return conteo;
    }

    /**
     * Cuenta cuántas veces aparece la cadena recibida por parámetro en el arreglo de cadenas.
     * 
     * La búsqueda no debe diferenciar entre mayúsculas y minúsculas.
     * @param cadena La cadena buscada
     * @return La cantidad de veces que aparece la cadena
     */
    public int contarApariciones( String cadena )
    {
        int conteo = 0;
        
        for (int i = 0; i < arregloCadenas.length; i++) {
        	if (arregloCadenas[i].equalsIgnoreCase(cadena)) {
        		conteo++;
        	}
        }
        
        return conteo;
    }

    /**
     * Busca en qué posiciones del arreglo de enteros se encuentra el valor que se recibe en el parámetro
     * @param valor El valor que se debe buscar
     * @return Un arreglo con los números de las posiciones del arreglo de enteros en las que se encuentra el valor buscado. Si el valor no se encuentra, el arreglo retornado
     *         es de tamaño 0.
     */
    public int[] buscarEntero( int valor )
    {
    	// Primero contar cuantas veces aparece el valor
    	int contador = 0;
    	for (int i = 0; i < arregloEnteros.length; i++) {
    		if (arregloEnteros[i] == valor) {
    			contador++;
    		}
    	}
    	
    	// Si el contador es cero, retornar un arreglo vacio
    	
    	if (contador == 0) {
    		return new int[0];
    	}
    	
    	// Crear un arreglo para almacenar las posiciones donde aparece el valor
    	
    	int[] posiciones = new int[contador];
    	
    	// Rellenar el arreglo con las posiciones
    	
    	int indice = 0;
    	
    	for (int i = 0; i < arregloEnteros.length; i++) {
    		if (arregloEnteros[i] == valor) {
    			posiciones[indice] = i;
    			indice++;
    		}
    		
    	}
        
    	return posiciones;
    }

    /**
     * Calcula cuál es el rango de los enteros (el valor mínimo y el máximo).
     * @return Un arreglo con dos posiciones: en la primera posición, debe estar el valor mínimo en el arreglo de enteros; en la segunda posición, debe estar el valor máximo
     *         en el arreglo de enteros. Si el arreglo está vacío, debe retornar un arreglo vacío.
     */
    public int[] calcularRangoEnteros( )
    {
    	
    	// Verificar si el arreglo de enteros esta vacio
    	
    	if (arregloEnteros.length == 0) {
    		return new int[0];
    	}
    	
    	int min = Integer.MAX_VALUE;
    	int max = Integer.MIN_VALUE;
    	
    	for (int i = 0; i < arregloEnteros.length; i++) {
    		min = Math.min(min, arregloEnteros[i]);
    		max = Math.max(max, arregloEnteros[i]);
    		
    	}
        // Preparar el arreglo de resultados con el minimo y el maximo
    	
    	int[] resultado = {min, max};
    	
    	return resultado;
        
        
    }

    /**
     * Calcula un histograma de los valores del arreglo de enteros y lo devuelve como un mapa donde las llaves son los valores del arreglo y los valores son la cantidad de
     * veces que aparece cada uno en el arreglo de enteros.
     * @return Un mapa con el histograma de valores.
     */
    public HashMap<Integer, Integer> calcularHistograma( )
    {
        HashMap<Integer, Integer> histograma = new HashMap<>();
        
        // Recorrer cada numero en el arreglo de enteros
        
        for (int numero : arregloEnteros) {
        	// Verificar si el numero ya esta en el mapa
        	if (histograma.containsKey(numero)) {
        		// Si el numero esta, incrementar su contador en 1
        		histograma.put(numero, histograma.get(numero)+1);
        	} else {
        		// Si el numero no esta, agregarlo al mapa con un contador inicial de 1
        		histograma.put(numero, 1);
        	}
        }
        
        // Devolver el histograma completo
        
        return histograma;
    }

    /**
     * Cuenta cuántos valores dentro del arreglo de enteros están repetidos.
     * @return La cantidad de enteos diferentes que aparecen más de una vez
     */
    public int contarEnterosRepetidos( )
    {
    	
    	int cantidadRepetidos = 0;
    	
    	// Crear un arreglo booleano para marcar los elementos ya contados como repetidos
    	boolean [] yaContados = new boolean[arregloEnteros.length];

    	// Recorrer cada elemento del arreglo
    	
    	for (int i = 0; i < arregloEnteros.length; i++) {
    		if (yaContados[i]) {
    			// Si ya fue contado como repetido, lo saltamos
    			continue;
    		}
    		
    		int conteo = 0;
    		for (int j = i + 1; j < arregloEnteros.length;j++) {
    			if (arregloEnteros[i] == arregloEnteros[j]) {
    				conteo++;
    				yaContados[j] = true; // Marcar el duplicado para no contarlo de nuevo
    			}
    		}
    		
    		if (conteo > 0) {
    			cantidadRepetidos++;
    		}
    	}
    	
    	return cantidadRepetidos;
    }

    /**
     * Compara el arreglo de enteros con otro arreglo de enteros y verifica si son iguales, es decir que contienen los mismos elementos exactamente en el mismo orden.
     * @param otroArreglo El arreglo de enteros con el que se debe comparar
     * @return True si los arreglos son idénticos y false de lo contrario
     */
    public boolean compararArregloEnteros( int[] otroArreglo )
    {
        // Comparar primeramente la longitud para descartar
    	
    	if (otroArreglo.length != arregloEnteros.length) {
    		return false;
    	}
    	
    	// Comparar todos los elementos de ambos arreglos 
    	
    	for (int i = 0; i < arregloEnteros.length; i++) {
    		if (arregloEnteros[i] != otroArreglo[i]) {
    			return false;
    		}
    	}
    	
    	// Si en ningun momento retorna false, entonces si tienen la misma longitud y los mismos elementos en el mismo orden
    	return true;
    }

    /**
     * Compara el arreglo de enteros con otro arreglo de enteros y verifica que tengan los mismos elementos, aunque podría ser en otro orden.
     * @param otroArreglo El arreglo de enteros con el que se debe comparar
     * @return True si los elementos en los dos arreglos son los mismos
     */
    public boolean mismosEnteros(int[] otroArreglo) {
    	
        // Comparar primeramente la longitud para descartar
        if (otroArreglo.length != arregloEnteros.length) {
            return false;
        }

        // Recorrer cada elemento del primer arreglo
        for (int i = 0; i < arregloEnteros.length; i++) {
            int conteoEnArregloOriginal = 0;
            int conteoEnOtroArreglo = 0;

            // Contar ocurrencias del elemento en el arreglo original
            for (int j = 0; j < arregloEnteros.length; j++) {
                if (arregloEnteros[i] == arregloEnteros[j]) {
                    conteoEnArregloOriginal++;
                }
            }

            // Contar ocurrencias del elemento en el otro arreglo, marcando los contados
            boolean[] yaContadosEnOtro = new boolean[otroArreglo.length];
            for (int k = 0; k < otroArreglo.length; k++) {
                if (arregloEnteros[i] == otroArreglo[k] && !yaContadosEnOtro[k]) {
                    conteoEnOtroArreglo++;
                    yaContadosEnOtro[k] = true; // Marcamos este elemento como contado
                }
            }

            // Si los conteos no coinciden, los arreglos no son iguales
            if (conteoEnArregloOriginal != conteoEnOtroArreglo) {
                return false;
            }
        }

        // Si todos los elementos coinciden en cantidad, son los mismos
        return true;
    }


    /**
     * Cambia los elementos del arreglo de enteros por una nueva serie de valores generada de forma aleatoria.
     * 
     * Para generar los valores se debe partir de una distribución uniforme usando Math.random().
     * 
     * Los números en el arreglo deben quedar entre el valor mínimo y el máximo.
     * @param cantidad La cantidad de elementos que debe haber en el arreglo
     * @param minimo El valor mínimo para los números generados
     * @param maximo El valor máximo para los números generados
     */
    public void generarEnteros( int cantidad, int minimo, int maximo )
    {
    	arregloEnteros = new int[cantidad];
    	
    	// Llenar el arreglo con valores aleatorios dentro del rango con formula
    	for (int i = 0; i < cantidad; i++) {
    		arregloEnteros[i] = (int) (Math.random()*(maximo - minimo + 1))+minimo;
    	}
    }
    
    

}
