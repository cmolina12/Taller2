package uniandes.dpoo.estructuras.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.estructuras.logica.SandboxArreglos;

class TestSandboxArreglos
{

    private SandboxArreglos sencillo;
    private SandboxArreglos sinRepetidos;
    private SandboxArreglos vacio;

    private static final int[] enterosSencillos = new int[]{ 9, 8, 7, 6, 5, 4, 3, 2, 1, 0, 6, -2, -3, -4, -5, 6, 7, 8, 9 };
    private static final String[] cadenasSencillas = new String[]{ "a", "b", "c", "d", "e", "d", "c", "b", "a" };

    private static final int[] enterosSinRepetidos = new int[]{ 9, 8, 7, 6, 5, 4, 3, 2, 1, 0 };

    @BeforeEach
    void setUp( ) throws Exception
    {
        sencillo = new SandboxArreglos( );

        for( int i = 0; i < enterosSencillos.length; i++ )
        {
            sencillo.agregarEntero( enterosSencillos[ i ] );
        }
        for( int i = 0; i < cadenasSencillas.length; i++ )
        {
            sencillo.agregarCadena( cadenasSencillas[ i ] );
        }

        sinRepetidos = new SandboxArreglos( );
        for( int i = 0; i < enterosSinRepetidos.length; i++ )
        {
            sinRepetidos.agregarEntero( enterosSinRepetidos[ i ] );
        }

        vacio = new SandboxArreglos( );
    }

    @AfterEach
    void reset( )
    {
        sencillo = null;
        sinRepetidos = null;
        vacio = null;
    }

    @Test
    void testGetCantidadEnteros( )
    {
        assertEquals( enterosSencillos.length, sencillo.getCantidadEnteros( ), "La cantidad inicial de enteros no es correcta" );
        assertEquals( 0, vacio.getCantidadEnteros( ), "La cantidad inicial de enteros en el sandbox vacío no es correcta" );
    }

    @Test
    void testGetCantidadCadenas( )
    {
        assertEquals( cadenasSencillas.length, sencillo.getCantidadCadenas( ), "La cantidad de cadenas no es correcta" );
        assertEquals( 0, vacio.getCantidadCadenas( ), "La cantidad inicial de cadenas en el sandbox vacío no es correcta" );
    }

    @Test
    void testGetCopiaEnteros( )
    {
        int[] copia = sencillo.getCopiaEnteros( );

        assertEquals( enterosSencillos.length, copia.length, "El arreglo retornado no tiene la cantidad de elementos esperados" );
        for( int i = 0; i < enterosSencillos.length; i++ )
        {
            assertEquals( enterosSencillos[ i ], copia[ i ], "El arreglo retornado no tiene los elementos esperados" );
        }

        copia[ 0 ] = 999;
        int[] copia2 = sencillo.getCopiaEnteros( );
        assertFalse( copia[ 0 ] == copia2[ 0 ], "Dos llamados al método retornaron el mismo arreglo" );
    }

    @Test
    void testGetCopiaEnterosVacia( )
    {
        int[] copia = vacio.getCopiaEnteros( );
        assertNotNull( copia, "El método no debería retornar null" );
        assertEquals( 0, copia.length, "La cantidad de enteros en el sandbox vacío no es correcta" );
    }

    @Test
    void testGetCopiaCadenas( )
    {
        String[] copia = sencillo.getCopiaCadenas( );

        assertEquals( cadenasSencillas.length, copia.length, "El arreglo retornado no tiene la cantidad de elementos esperados" );
        for( int i = 0; i < cadenasSencillas.length; i++ )
        {
            assertEquals( cadenasSencillas[ i ], copia[ i ], "El arreglo retornado no tiene los elementos esperados" );
        }

        copia[ 0 ] = "Testing 1 2 3";
        String[] copia2 = sencillo.getCopiaCadenas( );
        assertFalse( copia[ 0 ].equals( copia2[ 0 ] ), "Dos llamados al método retornaron el mismo arreglo" );
    }

    @Test
    void testGetCopiaCadenasVacia( )
    {
        String[] copia = vacio.getCopiaCadenas( );
        assertNotNull( copia, "El método no debería retornar null" );
        assertEquals( 0, copia.length, "La cantidad de cadenas en el sandbox vacío no es correcta" );
    }

    @Test
    void testAgregarEntero( )
    {
        assertEquals( enterosSencillos.length, sencillo.getCantidadEnteros( ), "La cantidad inicial de enteros no es correcta" );

        sencillo.agregarEntero( 99 );

        assertEquals( enterosSencillos.length + 1, sencillo.getCantidadEnteros( ), "La cantidad de enteros no aumentó correctamente" );

        int[] copia = sencillo.getCopiaEnteros( );
        assertEquals( 99, copia[ copia.length - 1 ], "El nuevo entero no quedó almacenado al final del arreglo" );
    }

    @Test
    void testAgregarEnteroVacio( )
    {
        vacio.agregarEntero( 99 );

        assertEquals( 1, vacio.getCantidadEnteros( ), "La cantidad de enteros no aumentó correctamente" );

        int[] copia = vacio.getCopiaEnteros( );
        assertEquals( 99, copia[ 0 ], "El nuevo entero no quedó almacenado al final del arreglo" );
    }

    @Test
    void testAgregarCadena( )
    {
        assertEquals( cadenasSencillas.length, sencillo.getCantidadCadenas( ), "La cantidad inicial de cadenas no es correcta" );

        sencillo.agregarCadena( "mmm" );

        assertEquals( cadenasSencillas.length + 1, sencillo.getCantidadCadenas( ), "La cantidad de cadenas no aumentó correctamente" );

        String[] copia = sencillo.getCopiaCadenas( );
        assertEquals( "mmm", copia[ copia.length - 1 ], "La nueva cadena no quedó almacenada al final del arreglo" );
    }

    @Test
    void testAgregarCadenaVacio( )
    {
        vacio.agregarCadena( "mmmm" );

        assertEquals( 1, vacio.getCantidadCadenas( ), "La cantidad de cadenas no aumentó correctamente" );

        String[] copia = vacio.getCopiaCadenas( );
        assertEquals( "mmmm", copia[ 0 ], "La nueva cadena no quedó almacenado al final del arreglo" );
    }

    @Test
    void testEliminarEnteroUnico( )
    {
        assertEquals( enterosSencillos.length, sencillo.getCantidadEnteros( ), "La cantidad inicial de enteros no es correcta" );
        sencillo.eliminarEntero( 0 );
        assertEquals( enterosSencillos.length - 1, sencillo.getCantidadEnteros( ), "La cantidad de enteros debería haber disminuido en 1" );
    }

    @Test
    void testEliminarEnteroDuplicado( )
    {
        assertEquals( enterosSencillos.length, sencillo.getCantidadEnteros( ), "La cantidad inicial de enteros no es correcta" );
        sencillo.eliminarEntero( 9 );
        assertEquals( enterosSencillos.length - 2, sencillo.getCantidadEnteros( ), "La cantidad de enteros debería haber disminuido en 2" );
    }

    @Test
    void testEliminarEnteroInexistente( )
    {
        assertEquals( enterosSencillos.length, sencillo.getCantidadEnteros( ), "La cantidad inicial de enteros no es correcta" );
        sencillo.eliminarEntero( 99 );
        assertEquals( enterosSencillos.length, sencillo.getCantidadEnteros( ), "La cantidad de enteros debería seguir siendo la misma" );
    }

    @Test
    void testEliminarEnteroVacio( )
    {
        vacio.eliminarEntero( 99 );
        assertEquals( 0, vacio.getCantidadEnteros( ), "La cantidad de enteros debería seguir siendo la misma" );
    }

    @Test
    void testEliminarCadenaUnico( )
    {
        assertEquals( cadenasSencillas.length, sencillo.getCantidadCadenas( ), "La cantidad inicial de cadenas no es correcta" );
        sencillo.eliminarCadena( "e" );
        assertEquals( cadenasSencillas.length - 1, sencillo.getCantidadCadenas( ), "La cantidad de cadenas debería haber disminuido en 1" );
    }

    @Test
    void testEliminarCadenaDuplicado( )
    {
        assertEquals( cadenasSencillas.length, sencillo.getCantidadCadenas( ), "La cantidad inicial de cadenas no es correcta" );
        sencillo.eliminarCadena( "d" );
        assertEquals( cadenasSencillas.length - 2, sencillo.getCantidadCadenas( ), "La cantidad de cadenas debería haber disminuido en 2" );
    }

    @Test
    void testEliminarCadenaInexistente( )
    {
        assertEquals( cadenasSencillas.length, sencillo.getCantidadCadenas( ), "La cantidad inicial de cadenas no es correcta" );
        sencillo.eliminarCadena( "zzz" );
        assertEquals( cadenasSencillas.length, sencillo.getCantidadCadenas( ), "La cantidad de cadenas debería seguir siendo la misma" );
    }

    @Test
    void testEliminarCadenaInexistenteVacio( )
    {
        vacio.eliminarCadena( "d" );
        assertEquals( 0, vacio.getCantidadCadenas( ), "La cantidad de cadenas debería seguir siendo la misma" );
    }

    @Test
    void testEliminarEnteroPorPosicionCorrectos( )
    {
        assertEquals( enterosSencillos.length, sencillo.getCantidadEnteros( ), "La cantidad inicial de enteros no es correcta" );

        sencillo.eliminarEnteroPorPosicion( 0 );

        assertEquals( enterosSencillos.length - 1, sencillo.getCantidadEnteros( ), "La cantidad de enteros no disminuyó correctamente" );

        int[] copia = sencillo.getCopiaEnteros( );
        assertEquals( 8, copia[ 0 ], "No se eliminó correctamente el primer entero" );

        assertEquals( 1, sencillo.contarApariciones( 9 ), "El valor no se eliminó correctamente" );

        sencillo.eliminarEnteroPorPosicion( 1 );

        assertEquals( enterosSencillos.length - 2, sencillo.getCantidadEnteros( ), "La cantidad de enteros no disminuyó correctamente" );

        copia = sencillo.getCopiaEnteros( );
        assertEquals( 8, copia[ 0 ], "No se eliminó correctamente el segundo entero" );
        assertEquals( 6, copia[ 1 ], "No se eliminó correctamente el segundo entero" );

        assertEquals( 1, sencillo.contarApariciones( 7 ), "El valor no se eliminó correctamente" );

        sencillo.eliminarEnteroPorPosicion( copia.length - 1 );
        assertEquals( enterosSencillos.length - 3, sencillo.getCantidadEnteros( ), "La cantidad de enteros no disminuyó correctamente" );

        copia = sencillo.getCopiaEnteros( );
        assertEquals( 8, copia[ copia.length - 1 ], "No se eliminó correctamente el último entero" );

        assertEquals( 0, sencillo.contarApariciones( 9 ), "El valor no se eliminó correctamente" );
    }

    @Test
    void testEliminarEnteroPorPosicionIncorrectos( )
    {
        assertEquals( enterosSencillos.length, sencillo.getCantidadEnteros( ), "La cantidad inicial de enteros no es correcta" );
        sencillo.eliminarEnteroPorPosicion( -1 );
        assertEquals( enterosSencillos.length, sencillo.getCantidadEnteros( ), "La cantidad de enteros no debería haber cambiado" );

        sencillo.eliminarEnteroPorPosicion( enterosSencillos.length );
        assertEquals( enterosSencillos.length, sencillo.getCantidadEnteros( ), "La cantidad de enteros no debería haber cambiado" );

        sencillo.eliminarEnteroPorPosicion( enterosSencillos.length + 10 );
        assertEquals( enterosSencillos.length, sencillo.getCantidadEnteros( ), "La cantidad de enteros no debería haber cambiado" );
    }

    @Test
    void testInsertarEnteroInicio( )
    {
        sencillo.insertarEntero( 99, 0 );

        assertEquals( enterosSencillos.length + 1, sencillo.getCantidadEnteros( ), "La cantidad de enteros no aumentó correctamente" );

        int[] copia = sencillo.getCopiaEnteros( );
        assertEquals( 99, copia[ 0 ], "No se insertó el entero correctamente en la primera posición" );

        for( int i = 0; i < enterosSencillos.length; i++ )
        {
            assertEquals( enterosSencillos[ i ], copia[ i + 1 ], "No se ajustaron correctamente los enteros existentes" );
        }
    }

    @Test
    void testInsertarEnteroInicioVacio( )
    {
        vacio.insertarEntero( 99, 0 );

        assertEquals( 1, vacio.getCantidadEnteros( ), "La cantidad de enteros no aumentó correctamente" );

        int[] copia = vacio.getCopiaEnteros( );
        assertEquals( 99, copia[ 0 ], "No se insertó el entero correctamente en la primera posición" );
    }

    @Test
    void testInsertarEnteroFinal( )
    {
        sencillo.insertarEntero( 99, enterosSencillos.length );

        assertEquals( enterosSencillos.length + 1, sencillo.getCantidadEnteros( ), "La cantidad de enteros no aumentó correctamente" );

        int[] copia = sencillo.getCopiaEnteros( );
        assertEquals( 99, copia[ enterosSencillos.length ], "No se insertó el entero correctamente en la última posición" );

        for( int i = 0; i < enterosSencillos.length; i++ )
        {
            assertEquals( enterosSencillos[ i ], copia[ i ], "No se ajustaron correctamente los enteros existentes" );
        }
    }

    @Test
    void testInsertarEnteroAfueraDespues( )
    {
        sencillo.insertarEntero( 99, enterosSencillos.length + 10 );

        assertEquals( enterosSencillos.length + 1, sencillo.getCantidadEnteros( ), "La cantidad de enteros no aumentó correctamente" );

        int[] copia = sencillo.getCopiaEnteros( );
        assertEquals( 99, copia[ enterosSencillos.length ], "No se insertó el entero correctamente en la última posición" );

        for( int i = 0; i < enterosSencillos.length; i++ )
        {
            assertEquals( enterosSencillos[ i ], copia[ i ], "No se ajustaron correctamente los enteros existentes" );
        }
    }

    @Test
    void testInsertarEnteroAfueraAntes( )
    {
        sencillo.insertarEntero( 99, -100 );

        assertEquals( enterosSencillos.length + 1, sencillo.getCantidadEnteros( ), "La cantidad de enteros no aumentó correctamente" );

        int[] copia = sencillo.getCopiaEnteros( );
        assertEquals( 99, copia[ 0 ], "No se insertó el entero correctamente en la primera posición" );

        for( int i = 0; i < enterosSencillos.length; i++ )
        {
            assertEquals( enterosSencillos[ i ], copia[ i + 1 ], "No se ajustaron correctamente los enteros existentes" );
        }
    }

    @Test
    void testInsertarEnteroMitad( )
    {
        sencillo.insertarEntero( 99, 2 );

        assertEquals( enterosSencillos.length + 1, sencillo.getCantidadEnteros( ), "La cantidad de enteros no aumentó correctamente" );

        int[] copia = sencillo.getCopiaEnteros( );
        assertEquals( 99, copia[ 2 ], "No se insertó el entero correctamente en la posición 2" );

        assertEquals( enterosSencillos[ 0 ], copia[ 0 ], "No se ajustaron correctamente los enteros existentes" );
        assertEquals( enterosSencillos[ 1 ], copia[ 1 ], "No se ajustaron correctamente los enteros existentes" );

        for( int i = 2; i < enterosSencillos.length; i++ )
        {
            assertEquals( enterosSencillos[ i ], copia[ i + 1 ], "No se ajustaron correctamente los enteros existentes" );
        }
    }

    @Test
    void testReiniciarArregloEnteros( )
    {
        double[] nuevosValores = new double[]{ 0.1, 1.2, 2.2, 3.4, 4.45, 5.49 };

        sencillo.reiniciarArregloEnteros( nuevosValores );
        assertEquals( nuevosValores.length, sencillo.getCantidadEnteros( ), "La nueva cantidad de enteros no es la correcta" );

        int[] copia = sencillo.getCopiaEnteros( );

        for( int i = 0; i < nuevosValores.length; i++ )
        {
            int valorAproximado = ( int )nuevosValores[ i ];
            assertEquals( valorAproximado, copia[ i ], "No se ajustaron correctamente los enteros existentes" );
        }
    }

    @Test
    void testReiniciarArregloEnterosVacio( )
    {
        double[] nuevosValores = new double[]{ 0.1, 1.2, 2.2, 3.4, 4.45, 5.49 };

        vacio.reiniciarArregloEnteros( nuevosValores );
        assertEquals( nuevosValores.length, vacio.getCantidadEnteros( ), "La nueva cantidad de enteros no es la correcta" );

        int[] copia = vacio.getCopiaEnteros( );

        for( int i = 0; i < nuevosValores.length; i++ )
        {
            int valorAproximado = ( int )nuevosValores[ i ];
            assertEquals( valorAproximado, copia[ i ], "No se ajustaron correctamente los enteros existentes" );
        }
    }

    @Test
    void testReiniciarArregloCadenas( )
    {
        Object[] nuevosValores = new Object[]{ new Double( "0.01" ), new Character( 'a' ), "Hello", new Integer( "25" ), new Boolean( false ) };

        sencillo.reiniciarArregloCadenas( nuevosValores );
        assertEquals( nuevosValores.length, sencillo.getCantidadCadenas( ), "La nueva cantidad de cadenas no es la correcta" );

        String[] copia = sencillo.getCopiaCadenas( );

        for( int i = 0; i < nuevosValores.length; i++ )
        {
            String cadenaEsperada = nuevosValores[ i ].toString( );
            assertEquals( cadenaEsperada, copia[ i ], "No se ajustaron correctamente las cadenas existentes" );
        }
    }

    @Test
    void testVolverPositivos( )
    {
        sencillo.volverPositivos( );
        int[] copia = sencillo.getCopiaEnteros( );

        for( int i = 0; i < enterosSencillos.length; i++ )
        {
            assertEquals( Math.abs( enterosSencillos[ i ] ), copia[ i ], "No se ajustaron correctamente los enteros existentes" );
        }
    }

    @Test
    void testVolverPositivosVacio( )
    {
        vacio.volverPositivos( );
        int[] copia = vacio.getCopiaEnteros( );
        assertEquals( 0, copia.length, "La cantidad de enteros es correcta" );
    }

    @Test
    void testContarEntero( )
    {
        assertEquals( 1, sencillo.contarApariciones( 0 ), "No contó correctamente el entero" );
        assertEquals( 1, sencillo.contarApariciones( 1 ), "No contó correctamente el entero" );
        assertEquals( 1, sencillo.contarApariciones( -2 ), "No contó correctamente el entero" );
        assertEquals( 2, sencillo.contarApariciones( 9 ), "No contó correctamente el entero" );
        assertEquals( 2, sencillo.contarApariciones( 8 ), "No contó correctamente el entero" );
        assertEquals( 3, sencillo.contarApariciones( 6 ), "No contó correctamente el entero" );
        assertEquals( 0, sencillo.contarApariciones( 99 ), "No contó correctamente el entero" );
    }

    @Test
    void testContarEnteroVacio( )
    {
        assertEquals( 0, vacio.contarApariciones( 0 ), "No contó correctamente el entero" );
        assertEquals( 0, vacio.contarApariciones( 2 ), "No contó correctamente el entero" );
        assertEquals( 0, vacio.contarApariciones( -2 ), "No contó correctamente el entero" );
        assertEquals( 0, vacio.contarApariciones( 99 ), "No contó correctamente el entero" );
    }

    @Test
    void testContarCadena( )
    {
        assertEquals( 1, sencillo.contarApariciones( "e" ), "No contó correctamente las cadenas" );
        assertEquals( 2, sencillo.contarApariciones( "A" ), "No contó correctamente las cadenas con diferencias entre mayúsculas y minúsculas" );
        assertEquals( 2, sencillo.contarApariciones( "B" ), "No contó correctamente las cadenas con diferencias entre mayúsculas y minúsculas" );
        assertEquals( 0, sencillo.contarApariciones( "z" ), "No contó correctamente las cadenas que no están en el arreglo" );

        sencillo.agregarCadena( "x" + 'y' + 'z' );
        assertEquals( 1, sencillo.contarApariciones( "xyz" ), "No contó correctamente las cadenas" );
    }

    @Test
    void testContarCadenaVacio( )
    {
        assertEquals( 0, vacio.contarApariciones( "e" ), "No contó correctamente las cadenas" );
        assertEquals( 0, vacio.contarApariciones( "a" ), "No contó correctamente las cadenas" );
        assertEquals( 0, vacio.contarApariciones( "b" ), "No contó correctamente las cadenas" );
        assertEquals( 0, vacio.contarApariciones( "z" ), "No contó correctamente las cadenas" );
    }

    @Test
    void testOrganizarEnteros( )
    {

        sencillo.organizarEnteros( );

        int[] copia = sencillo.getCopiaEnteros( );

        for( int i = 1; i < copia.length; i++ )
        {
            assertTrue( copia[ i - 1 ] <= copia[ i ], "No se organizaron correctamente los enteros" );
        }

    }

    @Test
    void testOrganizarEnterosVacio( )
    {
        vacio.organizarEnteros( );

        int[] copia = vacio.getCopiaEnteros( );
        assertEquals( 0, copia.length, "La cantidad de enteros es correcta" );
    }

    @Test
    void testOrganizarCadenas( )
    {
        sencillo.organizarCadenas( );

        String[] copia = sencillo.getCopiaCadenas( );

        for( int i = 1; i < copia.length; i++ )
        {
            assertTrue( copia[ i - 1 ].compareTo( copia[ i ] ) <= 0, "No se organizaron correctamente las cadenas" );
        }
    }

    @Test
    void testOrganizarCadenasVacio( )
    {
        vacio.organizarCadenas( );

        String[] copia = vacio.getCopiaCadenas( );
        assertEquals( 0, copia.length, "La cantidad de cadenas es correcta" );
    }

    @Test
    void testEncontrarEntero( )
    {
        int[] posiciones9 = sencillo.buscarEntero( 9 );
        assertEquals( 2, posiciones9.length, "No encontró la posición correctamente: " + Arrays.toString( posiciones9 ) );
        assertEquals( 0, posiciones9[ 0 ], "No encontró la posición correctamente: " + Arrays.toString( posiciones9 ) );
        assertEquals( enterosSencillos.length - 1, posiciones9[ 1 ], "No encontró la posición correctamente: " + Arrays.toString( posiciones9 ) );

        int[] posiciones8 = sencillo.buscarEntero( 8 );
        assertEquals( 2, posiciones8.length, "No encontró la posición correctamente" );
        assertEquals( 1, posiciones8[ 0 ], "No encontró la posición correctamente" );
        assertEquals( enterosSencillos.length - 2, posiciones8[ 1 ], "No encontró la posición correctamente" );

        int[] posiciones0 = sencillo.buscarEntero( 0 );
        assertEquals( 1, posiciones0.length, "No encontró la posición correctamente" );
        assertEquals( 9, posiciones0[ 0 ], "No encontró la posición correctamente" );

        int[] posicionesM2 = sencillo.buscarEntero( -2 );
        assertEquals( 1, posicionesM2.length, "No encontró la posición correctamente" );
        assertEquals( 11, posicionesM2[ 0 ], "No encontró la posición correctamente" );

        int[] posiciones99 = sencillo.buscarEntero( 99 );
        assertEquals( 0, posiciones99.length, "No reconocio un valor que no está en el arreglo" );
    }

    @Test
    void testEncontrarEnteroVacio( )
    {
        assertEquals( 0, vacio.buscarEntero( 9 ).length, "No encontró la posición correctamente" );
        assertEquals( 0, vacio.buscarEntero( 8 ).length, "No encontró la posición correctamente" );
        assertEquals( 0, vacio.buscarEntero( 0 ).length, "No encontró la posición correctamente" );
        assertEquals( 0, vacio.buscarEntero( -2 ).length, "No encontró la posición correctamente" );
    }

    @Test
    void testCalcularRangoEnteros( )
    {
        int[] rango = sencillo.calcularRangoEnteros( );
        assertEquals( -5, rango[ 0 ], "El menor valor del rango no es el correcto" );
        assertEquals( 9, rango[ 1 ], "El mayor valor del rango no es el correcto" );
    }

    @Test
    void testCalcularRangoEnterosVacio( )
    {
        int[] rango = vacio.calcularRangoEnteros( );
        assertEquals( 0, rango.length, "El rango retornado debería estar vacío" );
    }

    @Test
    void testCalcularHistograma( )
    {
        HashMap<Integer, Integer> histograma = sencillo.calcularHistograma( );

        assertEquals( 14, histograma.size( ), "La cantidad de valores en el histograma no es la correcta" );

        assertEquals( 1, histograma.get( -5 ), "La cantidad de veces que aparece el valor en el histograma no es la correcta" );
        assertEquals( 1, histograma.get( -4 ), "La cantidad de veces que aparece el valor en el histograma no es la correcta" );
        assertEquals( 1, histograma.get( -3 ), "La cantidad de veces que aparece el valor en el histograma no es la correcta" );
        assertEquals( 1, histograma.get( -2 ), "La cantidad de veces que aparece el valor en el histograma no es la correcta" );
        assertEquals( 1, histograma.get( 0 ), "La cantidad de veces que aparece el valor en el histograma no es la correcta" );
        assertEquals( 1, histograma.get( 1 ), "La cantidad de veces que aparece el valor en el histograma no es la correcta" );
        assertEquals( 1, histograma.get( 2 ), "La cantidad de veces que aparece el valor en el histograma no es la correcta" );
        assertEquals( 1, histograma.get( 3 ), "La cantidad de veces que aparece el valor en el histograma no es la correcta" );
        assertEquals( 1, histograma.get( 4 ), "La cantidad de veces que aparece el valor en el histograma no es la correcta" );
        assertEquals( 1, histograma.get( 5 ), "La cantidad de veces que aparece el valor en el histograma no es la correcta" );
        assertEquals( 3, histograma.get( 6 ), "La cantidad de veces que aparece el valor en el histograma no es la correcta" );
        assertEquals( 2, histograma.get( 7 ), "La cantidad de veces que aparece el valor en el histograma no es la correcta" );
        assertEquals( 2, histograma.get( 8 ), "La cantidad de veces que aparece el valor en el histograma no es la correcta" );
        assertEquals( 2, histograma.get( 9 ), "La cantidad de veces que aparece el valor en el histograma no es la correcta" );
    }

    @Test
    void testCalcularHistogramaVacio( )
    {
        HashMap<Integer, Integer> histograma = vacio.calcularHistograma( );
        assertEquals( 0, histograma.size( ), "La cantidad de valores en el histograma no es la correcta" );
    }

    @Test
    void testContarEnterosRepetidos( )
    {
        assertEquals( 4, sencillo.contarEnterosRepetidos( ), "No contó correctamente la cantidad de enteros que están repetidos" );
    }

    @Test
    void testContarEnterosRepetidosSinRepetidos( )
    {
        assertEquals( 0, sinRepetidos.contarEnterosRepetidos( ), "No contó correctamente la cantidad de enteros que están repetidos" );
    }

    @Test
    void testContarEnterosRepetidosVacio( )
    {
        assertEquals( 0, vacio.contarEnterosRepetidos( ), "No contó correctamente la cantidad de enteros que están repetidos" );
    }

    @Test
    void testCompararArregloEnterosIguales( )
    {
        assertTrue( sencillo.compararArregloEnteros( enterosSencillos ), "No comparó correctamente los arreglos cuando eran iguales" );

        enterosSencillos[ 0 ] = -99;
        assertFalse( sencillo.compararArregloEnteros( enterosSencillos ), "No comparó correctamente los arreglos cuando eran diferentes" );
    }

    @Test
    void testCompararArregloEnterosDiferentes( )
    {
        int[] ordenados = new int[]{ -5, -4, -3, -2, 0, 1, 2, 3, 4, 5, 6, 6, 6, 7, 7, 8, 8, 9, 9 };
        assertFalse( sencillo.compararArregloEnteros( ordenados ), "No comparó correctamente los arreglos en orden diferente" );
    }

    @Test
    void testCompararArregloEnterosVacio( )
    {
        assertFalse( vacio.compararArregloEnteros( enterosSencillos ), "No comparó correctamente los arreglos cuando eran diferentes" );
    }

    @Test
    void testMismosEnterosMismoOrden( )
    {
        assertTrue( sencillo.mismosEnteros( enterosSencillos ), "No comparó correctamente los arreglos" );
    }

    @Test
    void testMismosEnterosDiferenteOrden( )
    {
        int[] ordenados = new int[]{ -5, -4, -3, -2, 0, 1, 2, 3, 4, 5, 6, 6, 6, 7, 7, 8, 8, 9, 9 };
        assertTrue( sencillo.mismosEnteros( ordenados ), "No comparó correctamente los arreglos en orden diferente" );
    }

    @Test
    void testMismosEnterosVacio( )
    {
        assertFalse( vacio.mismosEnteros( enterosSencillos ), "No comparó correctamente los arreglos" );
        assertTrue( vacio.mismosEnteros( new int[]{} ), "No comparó correctamente los arreglos" );
    }

    @Test
    void testGenerarEnterosBasico( )
    {
        sencillo.generarEnteros( 100, -10, 10 );

        assertEquals( 100, sencillo.getCantidadEnteros( ), "La cantidad de elementos no es correcta" );

        int[] rango = sencillo.calcularRangoEnteros( );
        assertTrue( -10 <= rango[ 0 ], "El menor valor del rango no es el correcto: " + Arrays.toString( rango ) );
        assertTrue( 10 >= rango[ 1 ], "El mayor valor del rango no es el correcto: " + Arrays.toString( rango ) );
    }

    @Test
    void testGenerarEnterosGrande( )
    {
        sencillo.generarEnteros( 10000, -10, 10 );

        assertEquals( 10000, sencillo.getCantidadEnteros( ), "La cantidad de elementos no es correcta" );

        int[] rango = sencillo.calcularRangoEnteros( );
        assertEquals( -10, rango[ 0 ], "El menor valor del rango no es el correcto" );
        assertEquals( 10, rango[ 1 ], "El mayor valor del rango no es el correcto" );
    }

    @Test
    void testGenerarEnterosDistribucion( )
    {
        sencillo.generarEnteros( 10000, -10, 10 );

        HashMap<Integer, Integer> histograma = sencillo.calcularHistograma( );

        int minimaCantidad = 10000;
        int maximaCantidad = 0;

        for( Iterator<Integer> iterator = histograma.values( ).iterator( ); iterator.hasNext( ); )
        {
            int cantidad = ( Integer )iterator.next( );
            if( cantidad < minimaCantidad )
                minimaCantidad = cantidad;
            if( cantidad > maximaCantidad )
                maximaCantidad = cantidad;
        }

        double proporcionExacta = 10000.0 / 21.0;

        if( minimaCantidad < ( proporcionExacta * 0.8 ) )
            fail( "Los valores no están bien distribuidos" );
    }

}
