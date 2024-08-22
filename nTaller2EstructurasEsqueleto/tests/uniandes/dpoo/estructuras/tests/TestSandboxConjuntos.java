package uniandes.dpoo.estructuras.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.TreeSet;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.estructuras.logica.SandboxConjuntos;

class TestSandboxConjuntos
{
    private SandboxConjuntos sencillo;
    private SandboxConjuntos sinRepetidos;
    private SandboxConjuntos vacio;

    private static final String[] cadenasSencillas = new String[]{ "a", "a", "B", "c", "d", "e", "a", "d", "c", "B", "a" };
    private static final String[] cadenasSinRepetidos = new String[]{ "e", "d", "c", "B", "a" };
    private static final String[] cadenasOrdenadas = new String[]{ "B", "a", "c", "d", "e" };
    private static final String[] cadenasMayusculasOrdenadas = new String[]{ "A", "B", "C", "D", "E" };

    @BeforeEach
    void setUp( ) throws Exception
    {
        sencillo = new SandboxConjuntos( );

        for( int i = 0; i < cadenasSencillas.length; i++ )
        {
            sencillo.agregarCadena( cadenasSencillas[ i ] );
        }

        sinRepetidos = new SandboxConjuntos( );
        for( int i = 0; i < cadenasSinRepetidos.length; i++ )
        {
            sinRepetidos.agregarCadena( cadenasSinRepetidos[ i ] );
        }
        vacio = new SandboxConjuntos( );
    }

    @AfterEach
    void reset( )
    {
        sencillo = null;
        sinRepetidos = null;
        vacio = null;
    }

    @Test
    void testGetCantidadCadenas( )
    {
        assertEquals( cadenasSinRepetidos.length, sencillo.getCantidadCadenas( ), "La cantidad de cadenas no es correcta" );
        assertEquals( cadenasSinRepetidos.length, sinRepetidos.getCantidadCadenas( ), "La cantidad de cadenas no es correcta" );
        assertEquals( 0, vacio.getCantidadCadenas( ), "La cantidad inicial de cadenas en el sandbox vacío no es correcta" );
    }

    @Test
    void testGetCadenasComoLista( )
    {
        List<String> copia = sencillo.getCadenasComoLista( );

        assertEquals( cadenasOrdenadas.length, copia.size( ), "La lista retornada no tiene la cantidad de elementos esperados" );
        for( int i = 0; i < cadenasOrdenadas.length; i++ )
        {
            assertEquals( cadenasOrdenadas[ i ], copia.get( i ), "La lista retornada no tiene los elementos esperados" );
        }

        copia.set( 0, "Testing 1 2 3" );
        List<String> copia2 = sencillo.getCadenasComoLista( );
        assertFalse( copia.get( 0 ).equals( copia2.get( 0 ) ), "Dos llamados al método retornaron la misma lista" );
    }

    @Test
    void testGetCadenasComoListaVacia( )
    {
        List<String> copia = vacio.getCadenasComoLista( );
        assertNotNull( copia, "El método no debería retornar null" );
        assertEquals( 0, copia.size( ), "La cantidad de cadenas en el sandbox vacío no es correcta" );
    }

    @Test
    void testGetCadenasComoListaInvertida( )
    {
        List<String> copia = sencillo.getCadenasComoListaInvertida( );

        assertEquals( cadenasOrdenadas.length, copia.size( ), "La lista retornada no tiene la cantidad de elementos esperados" );
        for( int i = 0; i < cadenasOrdenadas.length; i++ )
        {
            assertEquals( cadenasOrdenadas[ cadenasOrdenadas.length - i - 1 ], copia.get( i ), "La lista retornada no tiene los elementos esperados" );
        }

        copia.set( 0, "Testing 1 2 3" );
        List<String> copia2 = sencillo.getCadenasComoLista( );
        assertFalse( copia.get( 0 ).equals( copia2.get( 0 ) ), "Dos llamados al método retornaron la misma lista" );
    }

    @Test
    void testGetPrimera( )
    {
        assertEquals( cadenasOrdenadas[ 0 ], sencillo.getPrimera( ), "El primer elemento retornado no es el correcto" );
        assertEquals( null, vacio.getPrimera( ), "El primer elemento retornado no es el correcto" );
    }

    @Test
    void testGetUltima( )
    {
        assertEquals( cadenasOrdenadas[ cadenasOrdenadas.length - 1 ], sencillo.getUltima( ), "El último elemento retornado no es el correcto" );
        assertEquals( null, vacio.getUltima( ), "El último elemento retornado no es el correcto" );
    }

    @Test
    void testGetSiguientesIncluido( )
    {
        Collection<String> siguientes = sencillo.getSiguientes( "c" );
        assertEquals( 3, siguientes.size( ), "La cantidad de elementos retornada no es correcta" );
        for( int i = 2; i < cadenasOrdenadas.length; i++ )
        {
            assertTrue( siguientes.contains( cadenasOrdenadas[ i ] ), "Un elemento esperado no aparece en el resultado -> " + cadenasOrdenadas[ i ] );
        }
    }

    @Test
    void testGetSiguientesNoIncluido( )
    {
        Collection<String> siguientes = sencillo.getSiguientes( "ccc" );
        assertEquals( 2, siguientes.size( ), "La cantidad de elementos retornada no es correcta" );
        for( int i = 3; i < cadenasOrdenadas.length; i++ )
        {
            assertTrue( siguientes.contains( cadenasOrdenadas[ i ] ), "Un elemento esperado no aparece en el resultado -> " + cadenasOrdenadas[ i ] );
        }
    }

    @Test
    void testGetSiguientesFueraDelRango( )
    {
        Collection<String> siguientes = sencillo.getSiguientes( "z" );
        assertEquals( 0, siguientes.size( ), "La cantidad de elementos retornada no es correcta" );
    }

    @Test
    void testGetSiguientesVacio( )
    {
        Collection<String> siguientes = vacio.getSiguientes( "a" );
        assertEquals( 0, siguientes.size( ), "La cantidad de elementos retornada no es correcta" );
    }

    @Test
    void testAgregarCadenaVacio( )
    {
        vacio.agregarCadena( "mmmm" );

        assertEquals( 1, vacio.getCantidadCadenas( ), "La cantidad de cadenas no aumentó correctamente" );

        List<String> copia = vacio.getCadenasComoLista( );
        assertEquals( "mmmm", copia.get( 0 ), "La nueva cadena no quedó almacenado al final del conjunto" );
    }

    @Test
    void testAgregarCadenaFinal( )
    {
        assertEquals( cadenasSinRepetidos.length, sencillo.getCantidadCadenas( ), "La cantidad inicial de cadenas no es correcta" );

        sencillo.agregarCadena( "zzz" );

        assertEquals( cadenasSinRepetidos.length + 1, sencillo.getCantidadCadenas( ), "La cantidad de cadenas no aumentó correctamente" );

        List<String> copia = sencillo.getCadenasComoLista( );
        assertEquals( "zzz", copia.get( copia.size( ) - 1 ), "La nueva cadena no quedó almacenada al final del conjunto" );
    }

    @Test
    void testAgregarCadenaInicio( )
    {
        assertEquals( cadenasSinRepetidos.length, sencillo.getCantidadCadenas( ), "La cantidad inicial de cadenas no es correcta" );

        sencillo.agregarCadena( "AAA" );

        assertEquals( cadenasSinRepetidos.length + 1, sencillo.getCantidadCadenas( ), "La cantidad de cadenas no aumentó correctamente" );

        List<String> copia = sencillo.getCadenasComoLista( );
        assertEquals( "AAA", copia.get( 0 ), "La nueva cadena no quedó almacenada al inicio del conjunto" );
    }

    @Test
    void testAgregarCadenaIntermedia( )
    {
        assertEquals( cadenasSinRepetidos.length, sencillo.getCantidadCadenas( ), "La cantidad inicial de cadenas no es correcta" );

        sencillo.agregarCadena( "Ba" );

        assertEquals( cadenasSinRepetidos.length + 1, sencillo.getCantidadCadenas( ), "La cantidad de cadenas no aumentó correctamente" );

        List<String> copia = sencillo.getCadenasComoLista( );
        assertEquals( "Ba", copia.get( 1 ), "La nueva cadena no quedó almacenada al inicio del conjunto" );
    }

    @Test
    void testAgregarCadenaRepetida( )
    {
        assertEquals( cadenasSinRepetidos.length, sencillo.getCantidadCadenas( ), "La cantidad inicial de cadenas no es correcta" );

        sencillo.agregarCadena( "a" );

        assertEquals( cadenasSinRepetidos.length, sencillo.getCantidadCadenas( ), "La cantidad de cadenas no debería haber cambiado" );
    }

    @Test
    void testEliminarCadena( )
    {
        assertEquals( cadenasSinRepetidos.length, sencillo.getCantidadCadenas( ), "La cantidad inicial de cadenas no es correcta" );
        sencillo.eliminarCadena( "a" );
        assertEquals( cadenasSinRepetidos.length - 1, sencillo.getCantidadCadenas( ), "La cantidad de cadenas debería haber disminuido en 1" );
    }

    @Test
    void testEliminarPrimeraCadena( )
    {
        assertEquals( cadenasSinRepetidos.length, sencillo.getCantidadCadenas( ), "La cantidad inicial de cadenas no es correcta" );
        sencillo.eliminarCadena( sencillo.getPrimera( ) );
        assertEquals( cadenasSinRepetidos.length - 1, sencillo.getCantidadCadenas( ), "La cantidad de cadenas debería haber disminuido en 1" );
    }

    @Test
    void testEliminarUltimaCadena( )
    {
        assertEquals( cadenasSinRepetidos.length, sencillo.getCantidadCadenas( ), "La cantidad inicial de cadenas no es correcta" );
        sencillo.eliminarCadena( sencillo.getPrimera( ) );
        assertEquals( cadenasSinRepetidos.length - 1, sencillo.getCantidadCadenas( ), "La cantidad de cadenas debería haber disminuido en 1" );
    }

    @Test
    void testEliminarCadenaInexistente( )
    {
        assertEquals( cadenasSinRepetidos.length, sencillo.getCantidadCadenas( ), "La cantidad inicial de cadenas no es correcta" );
        sencillo.eliminarCadena( "zzz" );
        assertEquals( cadenasSinRepetidos.length, sencillo.getCantidadCadenas( ), "La cantidad de cadenas debería seguir siendo la misma" );
    }

    @Test
    void testEliminarCadenaInexistenteVacio( )
    {
        vacio.eliminarCadena( "d" );
        assertEquals( 0, vacio.getCantidadCadenas( ), "La cantidad de cadenas debería seguir siendo la misma" );
    }

    @Test
    void testEliminarCadenaSinMayusculasOMinusculasDiferente( )
    {
        assertEquals( cadenasSinRepetidos.length, sencillo.getCantidadCadenas( ), "La cantidad inicial de cadenas no es correcta" );
        sencillo.eliminarCadenaSinMayusculasOMinusculas( "b" );
        assertEquals( cadenasSinRepetidos.length - 1, sencillo.getCantidadCadenas( ), "La cantidad de cadenas debería haber disminuido en 1" );
    }

    @Test
    void testEliminarCadenaSinMayusculasOMinusculasIgual( )
    {
        assertEquals( cadenasSinRepetidos.length, sencillo.getCantidadCadenas( ), "La cantidad inicial de cadenas no es correcta" );
        sencillo.eliminarCadenaSinMayusculasOMinusculas( "B" );
        assertEquals( cadenasSinRepetidos.length - 1, sencillo.getCantidadCadenas( ), "La cantidad de cadenas debería haber disminuido en 1" );
    }

    @Test
    void testEliminarCadenaSinMayusculasOMinusculasVacio( )
    {
        sencillo.eliminarCadenaSinMayusculasOMinusculas( "b" );
        assertEquals( 0, vacio.getCantidadCadenas( ), "La cantidad de cadenas debería seguir siendo la misma" );
    }

    @Test
    void testEliminarPrimera( )
    {
        assertEquals( cadenasSinRepetidos.length, sencillo.getCantidadCadenas( ), "La cantidad inicial de cadenas no es correcta" );
        sencillo.eliminarPrimera( );
        assertEquals( cadenasSinRepetidos.length - 1, sencillo.getCantidadCadenas( ), "La cantidad de cadenas debería haber disminuido en 1" );
        List<String> copia = sencillo.getCadenasComoLista( );
        assertEquals( "a", copia.get( 0 ), "No eliminó correctamente el primer elemento" );
    }

    @Test
    void testReiniciarConjuntoCadenas( )
    {
        String[] nuevosValores = "Esta es una prueba con cadenas y con cadenas y con cadenas!".split( " " );
        sencillo.reiniciarConjuntoCadenas( Arrays.asList( nuevosValores ) );
        assertEquals( 8, sencillo.getCantidadCadenas( ), "La nueva cantidad de enteros no es la correcta" );

        List<String> copia = sencillo.getCadenasComoLista( );

        for( int i = 0; i < nuevosValores.length; i++ )
        {
            String cadena = nuevosValores[ i ];
            assertTrue( copia.contains( cadena ), "No se ajustaron correctamente los enteros existentes" );
        }
    }

    @Test
    void testVolverMayusculas( )
    {
        sencillo.volverMayusculas( );
        List<String> copia = sencillo.getCadenasComoLista( );

        for( int i = 0; i < cadenasMayusculasOrdenadas.length; i++ )
        {
            assertEquals( cadenasMayusculasOrdenadas[ i ], copia.get( i ), "No se ajustaron correctamente las cadenas a mayúsculas" );
        }
    }

    @Test
    void testVolverMayusculasVacio( )
    {
        vacio.volverMayusculas( );
        List<String> copia = vacio.getCadenasComoLista( );
        assertEquals( 0, copia.size( ), "La cantidad de cadenas no es correcta" );
    }

    @Test
    void testInvertirCadenas( )
    {
        TreeSet<String> invertido = vacio.invertirCadenas( );
        assertEquals( 0, invertido.size( ), "La cantidad de cadenas es correcta" );

        List<String> copia = new ArrayList<String>( invertido );
        for( int i = 0; i < invertido.size( ); i++ )
        {
            assertTrue( copia.get( i ).equals( cadenasOrdenadas[ cadenasOrdenadas.length - i - 1 ] ), "No se organizaron correctamente los enteros" );
        }
    }

    @Test
    void testInvertirCadenasVacio( )
    {
        TreeSet<String> copia = vacio.invertirCadenas( );
        assertEquals( 0, copia.size( ), "La cantidad de cadenas no es correcta" );
    }

    @Test
    void testCompararElementosIguales( )
    {
        assertTrue( sencillo.compararElementos( cadenasSencillas ), "No comparó correctamente los arreglos cuando eran iguales" );
        assertTrue( sencillo.compararElementos( cadenasSinRepetidos ), "No comparó correctamente los arreglos cuando eran los mismos pero sin repetidos" );
        assertTrue( sencillo.compararElementos( cadenasOrdenadas ), "No comparó correctamente los arreglos cuando eran los mismos pero ordenados" );
    }

    @Test
    void testCompararElementosDiferentes( )
    {
        assertFalse( sencillo.compararElementos( cadenasMayusculasOrdenadas ), "No comparó correctamente los arreglos cuando eran diferentes" );
    }

}
