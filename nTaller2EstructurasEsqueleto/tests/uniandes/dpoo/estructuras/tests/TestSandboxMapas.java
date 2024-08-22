package uniandes.dpoo.estructuras.tests;

import static org.junit.Assert.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.estructuras.logica.SandboxMapas;

class TestSandboxMapas
{
    private SandboxMapas sencillo;
    private SandboxMapas vacio;

    private static final String[] cadenasSencillas = new String[]{ "aa", "aa", "Be", "cc", "dd", "ee", "aa", "dd", "cc", "BB", "aaa" };
    private static final String[] cadenasOrdenadas = new String[]{ "BB", "Be", "aa", "aaa", "cc", "dd", "ee" };
    private static final String[] llaves = new String[]{ "aa", "eB", "cc", "dd", "ee", "BB", "aaa" };
    private static final String[] llavesInvertidas = new String[]{ "ee", "eB", "dd", "cc", "aaa", "aa", "BB" };
    private static final String[] llavesInvertidasMayusculas = new String[]{ "ee", "eB", "dd", "cc","BB", "aaa", "aa"  };

    @BeforeEach
    void setUp( ) throws Exception
    {
        sencillo = new SandboxMapas( );

        for( int i = 0; i < cadenasSencillas.length; i++ )
        {
            sencillo.agregarCadena( cadenasSencillas[ i ] );
        }

        vacio = new SandboxMapas( );
    }

    @AfterEach
    void reset( )
    {
        sencillo = null;
        vacio = null;
    }

    @Test
    void testGetCantidadCadenas( )
    {
        assertEquals( cadenasOrdenadas.length, sencillo.getCantidadCadenasDiferentes( ), "La cantidad de cadenas no es correcta" );
        assertEquals( 0, vacio.getCantidadCadenasDiferentes( ), "La cantidad inicial de cadenas en el sandbox vacío no es correcta" );
    }

    @Test
    void testGetCantidadCadenasDiferentes( )
    {
        assertEquals( cadenasOrdenadas.length, sencillo.getCantidadCadenasDiferentes( ), "La cantidad de cadenas no es correcta" );
        assertEquals( 0, vacio.getCantidadCadenasDiferentes( ), "La cantidad inicial de cadenas en el sandbox vacío no es correcta" );
    }

    @Test
    void testGetLlaves( )
    {
        Collection<String> copia = sencillo.getLlaves( );

        assertEquals( llaves.length, copia.size( ), "La colección retornada no tiene la cantidad de elementos esperados" );
        for( int i = 0; i < llaves.length; i++ )
        {
            assertTrue( copia.contains( llaves[ i ].toUpperCase( ) ), "La colección retornada no tiene los elementos esperados" );
        }

        Collection<String> copia2 = sencillo.getLlaves( );
        assertNotSame( "Dos llamados al método retornaron la misma colección", copia, copia2 );
    }

    @Test
    void testGetLlavesVacia( )
    {
        Collection<String> copia = vacio.getLlaves( );
        assertNotNull( copia, "El método no debería retornar null" );
        assertEquals( 0, copia.size( ), "La cantidad de cadenas en el sandbox vacío no es correcta" );
    }

    @Test
    void testGetValoresComoLista( )
    {
        List<String> copia = sencillo.getValoresComoLista( );

        assertEquals( cadenasOrdenadas.length, copia.size( ), "La lista retornada no tiene la cantidad de elementos esperados" );
        for( int i = 0; i < cadenasOrdenadas.length; i++ )
        {
            assertEquals( cadenasOrdenadas[ i ], copia.get( i ), "La lista retornada no tiene los elementos esperados" );
        }

        Collection<String> copia2 = sencillo.getValoresComoLista( );
        assertNotSame( "Dos llamados al método retornaron la misma colección", copia, copia2 );
    }

    @Test
    void testGetLlavesComoListaInvertida( )
    {
        List<String> copia = sencillo.getLlavesComoListaInvertida( );

        assertEquals( llavesInvertidas.length, copia.size( ), "La lista retornada no tiene la cantidad de elementos esperados" );
        for( int i = 0; i < llavesInvertidas.length; i++ )
        {
            assertEquals( llavesInvertidas[ i ], copia.get( i ), "La lista retornada no tiene los elementos esperados" );
        }

        Collection<String> copia2 = sencillo.getLlavesComoListaInvertida( );
        assertNotSame( "Dos llamados al método retornaron la misma colección", copia, copia2 );
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
    void testAgregarCadenaVacio( )
    {
        vacio.agregarCadena( "mmmm" );

        assertEquals( 1, vacio.getCantidadCadenasDiferentes( ), "La cantidad de cadenas no aumentó correctamente" );

        List<String> copia = vacio.getValoresComoLista( );
        assertEquals( "mmmm", copia.get( 0 ), "La nueva cadena no quedó almacenado" );
    }

    @Test
    void testEliminarCadenaConLlave( )
    {
        assertEquals( cadenasOrdenadas.length, sencillo.getCantidadCadenasDiferentes( ), "La cantidad inicial de cadenas no es correcta" );
        sencillo.eliminarCadenaConLLave( "eB" );
        assertEquals( cadenasOrdenadas.length - 1, sencillo.getCantidadCadenasDiferentes( ), "La cantidad de cadenas debería haber disminuido en 1" );
    }

    @Test
    void testEliminarCadenaConLlaveInexistente( )
    {
        assertEquals( cadenasOrdenadas.length, sencillo.getCantidadCadenasDiferentes( ), "La cantidad inicial de cadenas no es correcta" );
        sencillo.eliminarCadenaConLLave( "zzz" );
        assertEquals( cadenasOrdenadas.length, sencillo.getCantidadCadenasDiferentes( ), "La cantidad de cadenas debería seguir siendo la misma" );
    }

    @Test
    void testEliminarCadenaConLlaveInexistenteVacio( )
    {
        vacio.eliminarCadenaConLLave( "d" );
        assertEquals( 0, vacio.getCantidadCadenasDiferentes( ), "La cantidad de cadenas debería seguir siendo la misma" );
    }

    @Test
    void testEliminarCadenaConValor( )
    {
        assertEquals( cadenasOrdenadas.length, sencillo.getCantidadCadenasDiferentes( ), "La cantidad inicial de cadenas no es correcta" );
        sencillo.eliminarCadenaConValor( "Be" );
        assertEquals( cadenasOrdenadas.length - 1, sencillo.getCantidadCadenasDiferentes( ), "La cantidad de cadenas debería haber disminuido en 1" );
    }

    @Test
    void testEliminarCadenaConValorInexistente( )
    {
        assertEquals( cadenasOrdenadas.length, sencillo.getCantidadCadenasDiferentes( ), "La cantidad inicial de cadenas no es correcta" );
        sencillo.eliminarCadenaConValor( "zzz" );
        assertEquals( cadenasOrdenadas.length, sencillo.getCantidadCadenasDiferentes( ), "La cantidad de cadenas debería seguir siendo la misma" );
    }

    @Test
    void testEliminarCadenaConValorInexistenteVacio( )
    {
        vacio.eliminarCadenaConValor( "d" );
        assertEquals( 0, vacio.getCantidadCadenasDiferentes( ), "La cantidad de cadenas debería seguir siendo la misma" );
    }

    @Test
    void testReiniciarConjuntoCadenas( )
    {
        String[] nuevosValores = "Esta es una prueba con cadenas y con cadenas y con cadenas!".split( " " );
        sencillo.reiniciarMapaCadenas( Arrays.asList( nuevosValores ) );
        assertEquals( 8, sencillo.getCantidadCadenasDiferentes( ), "La nueva cantidad de cadenas no es la correcta" );
    }

    @Test
    void testVolverMayusculas( )
    {
        sencillo.volverMayusculas( );
        List<String> copia = sencillo.getLlavesComoListaInvertida( );

        for( int i = 0; i < llavesInvertidasMayusculas.length; i++ )
        {
            assertEquals( llavesInvertidasMayusculas[ i ].toUpperCase( ), copia.get( i ), "No se ajustaron correctamente las llaves a mayúsculas" );
        }
    }

    @Test
    void testCompararValores( )
    {
        assertTrue( sencillo.compararValores( cadenasSencillas ), "No comparó correctamente los arreglos cuando eran iguales" );
        assertTrue( sencillo.compararValores( cadenasOrdenadas ), "No comparó correctamente los arreglos cuando eran los mismos pero sin repetidos" );
        assertFalse( sencillo.compararValores( llaves ), "Se equivocó cuando se mezclaron las llaves con los valores" );
    }

}
