package uniandes.dpoo.estructuras.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.estructuras.logica.SandboxListas;

class TestSandboxListas
{
    private SandboxListas sencillo;
    private SandboxListas sinRepetidos;
    private SandboxListas vacio;

    private static final int[] enterosSencillos = new int[]{ 9, 8, 7, 6, 5, 4, 3, 2, 1, 0, 6, -2, -3, -4, -5, 6, 7, 8, 9 };
    private static final String[] cadenasSencillas = new String[]{ "a", "b", "c", "d", "e", "d", "c", "b", "a" };

    private static final int[] enterosSinRepetidos = new int[]{ 9, 8, 7, 6, 5, 4, 3, 2, 1, 0 };

    @BeforeEach
    void setUp( ) throws Exception
    {
        sencillo = new SandboxListas( );

        for( int i = 0; i < enterosSencillos.length; i++ )
        {
            sencillo.agregarEntero( enterosSencillos[ i ] );
        }
        for( int i = 0; i < cadenasSencillas.length; i++ )
        {
            sencillo.agregarCadena( cadenasSencillas[ i ] );
        }

        sinRepetidos = new SandboxListas( );
        for( int i = 0; i < enterosSinRepetidos.length; i++ )
        {
            sinRepetidos.agregarEntero( enterosSinRepetidos[ i ] );
        }

        vacio = new SandboxListas( );
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
        List<Integer> copia = sencillo.getCopiaEnteros( );

        assertEquals( enterosSencillos.length, copia.size( ), "La lista retornada  no tiene la cantidad de elementos esperados" );
        for( int i = 0; i < enterosSencillos.length; i++ )
        {
            assertEquals( enterosSencillos[ i ], copia.get( i ), "La lista retornada no tiene los elementos esperados" );
        }

        copia.set( 0, 999 );
        List<Integer> copia2 = sencillo.getCopiaEnteros( );
        assertFalse( copia.get( 0 ) == copia2.get( 0 ), "Dos llamados al método retornaron la misma lista" );
    }

    @Test
    void testGetCopiaEnterosVacia( )
    {
        List<Integer> copia = vacio.getCopiaEnteros( );
        assertNotNull( copia, "El método no debería retornar null" );
        assertEquals( 0, copia.size( ), "La cantidad de enteros en el sandbox vacío no es correcta" );
    }

    @Test
    void testGetCopiaCadenas( )
    {
        List<String> copia = sencillo.getCopiaCadenas( );

        assertEquals( cadenasSencillas.length, copia.size( ), "La lista retornada no tiene la cantidad de elementos esperados" );
        for( int i = 0; i < cadenasSencillas.length; i++ )
        {
            assertEquals( cadenasSencillas[ i ], copia.get( i ), "La lista retornada no tiene los elementos esperados" );
        }

        copia.set( 0, "Testing 1 2 3" );
        List<String> copia2 = sencillo.getCopiaCadenas( );
        assertFalse( copia.get( 0 ).equals( copia2.get( 0 ) ), "Dos llamados al método retornaron la misma lista" );
    }

    @Test
    void testGetCopiaCadenasVacia( )
    {
        List<String> copia = vacio.getCopiaCadenas( );
        assertNotNull( copia, "El método no debería retornar null" );
        assertEquals( 0, copia.size( ), "La cantidad de cadenas en el sandbox vacío no es correcta" );
    }

    @Test
    void testGetCopiaEnterosComoArreglos( )
    {
        int[] copiaArreglo = sencillo.getEnterosComoArreglo( );

        assertEquals( enterosSencillos.length, copiaArreglo.length, "El arreglo retornado no tiene la cantidad de elementos esperados" );
        for( int i = 0; i < enterosSencillos.length; i++ )
        {
            assertEquals( enterosSencillos[ i ], copiaArreglo[ i ], "El arreglo retornado no tiene los elementos esperados" );
        }

        copiaArreglo[ 0 ] = 999;
        int[] copia2 = sencillo.getEnterosComoArreglo( );
        assertFalse( copiaArreglo[ 0 ] == copia2[ 0 ], "Dos llamados al método retornaron el mismo arreglo" );
    }

    @Test
    void testGetCopiaEnterosComoArreglosVacia( )
    {
        int[] copiaArreglo = vacio.getEnterosComoArreglo( );
        assertNotNull( copiaArreglo, "El método no debería retornar null" );
        assertEquals( 0, copiaArreglo.length, "La cantidad de enteros en el sandbox vacío no es correcta" );
    }

    @Test
    void testAgregarEntero( )
    {
        assertEquals( enterosSencillos.length, sencillo.getCantidadEnteros( ), "La cantidad inicial de enteros no es correcta" );

        sencillo.agregarEntero( 99 );

        assertEquals( enterosSencillos.length + 1, sencillo.getCantidadEnteros( ), "La cantidad de enteros no aumentó correctamente" );

        List<Integer> copia = sencillo.getCopiaEnteros( );
        assertEquals( 99, copia.get( copia.size( ) - 1 ), "El nuevo entero no quedó almacenado al final de la lista" );
    }

    @Test
    void testAgregarEnteroVacio( )
    {
        vacio.agregarEntero( 99 );

        assertEquals( 1, vacio.getCantidadEnteros( ), "La cantidad de enteros no aumentó correctamente" );

        List<Integer> copia = vacio.getCopiaEnteros( );
        assertEquals( 99, copia.get( 0 ), "El nuevo entero no quedó almacenado al final de la lista" );
    }

    @Test
    void testAgregarCadena( )
    {
        assertEquals( cadenasSencillas.length, sencillo.getCantidadCadenas( ), "La cantidad inicial de cadenas no es correcta" );

        sencillo.agregarCadena( "mmm" );

        assertEquals( cadenasSencillas.length + 1, sencillo.getCantidadCadenas( ), "La cantidad de cadenas no aumentó correctamente" );

        List<String> copia = sencillo.getCopiaCadenas( );
        assertEquals( "mmm", copia.get( copia.size( ) - 1 ), "La nueva cadena no quedó almacenada al final de la lista" );
    }

    @Test
    void testAgregarCadenaVacio( )
    {
        vacio.agregarCadena( "mmmm" );

        assertEquals( 1, vacio.getCantidadCadenas( ), "La cantidad de cadenas no aumentó correctamente" );

        List<String> copia = vacio.getCopiaCadenas( );
        assertEquals( "mmmm", copia.get( 0 ), "La nueva cadena no quedó almacenado al final de la lista" );
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

        List<Integer> copia = sencillo.getCopiaEnteros( );
        assertEquals( 8, copia.get( 0 ), "No se eliminó correctamente el primer entero" );

        assertEquals( 1, sencillo.contarApariciones( 9 ), "El valor no se eliminó correctamente" );

        sencillo.eliminarEnteroPorPosicion( 1 );

        assertEquals( enterosSencillos.length - 2, sencillo.getCantidadEnteros( ), "La cantidad de enteros no disminuyó correctamente" );

        copia = sencillo.getCopiaEnteros( );
        assertEquals( 8, copia.get( 0 ), "No se eliminó correctamente el segundo entero" );
        assertEquals( 6, copia.get( 1 ), "No se eliminó correctamente el segundo entero" );

        assertEquals( 1, sencillo.contarApariciones( 7 ), "El valor no se eliminó correctamente" );

        sencillo.eliminarEnteroPorPosicion( copia.size( ) - 1 );
        assertEquals( enterosSencillos.length - 3, sencillo.getCantidadEnteros( ), "La cantidad de enteros no disminuyó correctamente" );

        copia = sencillo.getCopiaEnteros( );
        assertEquals( 8, copia.get( copia.size( ) - 1 ), "No se eliminó correctamente el último entero" );

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

        List<Integer> copia = sencillo.getCopiaEnteros( );
        assertEquals( 99, copia.get( 0 ), "No se insertó el entero correctamente en la primera posición" );

        for( int i = 0; i < enterosSencillos.length; i++ )
        {
            assertEquals( enterosSencillos[ i ], copia.get( i + 1 ), "No se ajustaron correctamente los enteros existentes" );
        }
    }

    @Test
    void testInsertarEnteroInicioVacio( )
    {
        vacio.insertarEntero( 99, 0 );

        assertEquals( 1, vacio.getCantidadEnteros( ), "La cantidad de enteros no aumentó correctamente" );

        List<Integer> copia = vacio.getCopiaEnteros( );
        assertEquals( 99, copia.get( 0 ), "No se insertó el entero correctamente en la primera posición" );
    }

    @Test
    void testInsertarEnteroFinal( )
    {
        sencillo.insertarEntero( 99, enterosSencillos.length );

        assertEquals( enterosSencillos.length + 1, sencillo.getCantidadEnteros( ), "La cantidad de enteros no aumentó correctamente" );

        List<Integer> copia = sencillo.getCopiaEnteros( );
        assertEquals( 99, copia.get( enterosSencillos.length ), "No se insertó el entero correctamente en la última posición" );

        for( int i = 0; i < enterosSencillos.length; i++ )
        {
            assertEquals( enterosSencillos[ i ], copia.get( i ), "No se ajustaron correctamente los enteros existentes" );
        }
    }

    @Test
    void testInsertarEnteroAfueraDespues( )
    {
        sencillo.insertarEntero( 99, enterosSencillos.length + 10 );

        assertEquals( enterosSencillos.length + 1, sencillo.getCantidadEnteros( ), "La cantidad de enteros no aumentó correctamente" );

        List<Integer> copia = sencillo.getCopiaEnteros( );
        assertEquals( 99, copia.get( enterosSencillos.length ), "No se insertó el entero correctamente en la última posición" );

        for( int i = 0; i < enterosSencillos.length; i++ )
        {
            assertEquals( enterosSencillos[ i ], copia.get( i ), "No se ajustaron correctamente los enteros existentes" );
        }
    }

    @Test
    void testInsertarEnteroAfueraAntes( )
    {
        sencillo.insertarEntero( 99, -100 );

        assertEquals( enterosSencillos.length + 1, sencillo.getCantidadEnteros( ), "La cantidad de enteros no aumentó correctamente" );

        List<Integer> copia = sencillo.getCopiaEnteros( );
        assertEquals( 99, copia.get( 0 ), "No se insertó el entero correctamente en la primera posición" );

        for( int i = 0; i < enterosSencillos.length; i++ )
        {
            assertEquals( enterosSencillos[ i ], copia.get( i + 1 ), "No se ajustaron correctamente los enteros existentes" );
        }
    }

    @Test
    void testInsertarEnteroMitad( )
    {
        sencillo.insertarEntero( 99, 2 );

        assertEquals( enterosSencillos.length + 1, sencillo.getCantidadEnteros( ), "La cantidad de enteros no aumentó correctamente" );

        List<Integer> copia = sencillo.getCopiaEnteros( );
        assertEquals( 99, copia.get( 2 ), "No se insertó el entero correctamente en la posición 2" );

        assertEquals( enterosSencillos[ 0 ], copia.get( 0 ), "No se ajustaron correctamente los enteros existentes" );
        assertEquals( enterosSencillos[ 1 ], copia.get( 1 ), "No se ajustaron correctamente los enteros existentes" );

        for( int i = 2; i < enterosSencillos.length; i++ )
        {
            assertEquals( enterosSencillos[ i ], copia.get( i + 1 ), "No se ajustaron correctamente los enteros existentes" );
        }
    }

    @Test
    void testReiniciarArregloEnteros( )
    {
        double[] nuevosValores = new double[]{ 0.1, 1.2, 2.2, 3.4, 4.45, 5.49 };

        sencillo.reiniciarArregloEnteros( nuevosValores );
        assertEquals( nuevosValores.length, sencillo.getCantidadEnteros( ), "La nueva cantidad de enteros no es la correcta" );

        List<Integer> copia = sencillo.getCopiaEnteros( );

        for( int i = 0; i < nuevosValores.length; i++ )
        {
            int valorAproximado = ( int )nuevosValores[ i ];
            assertEquals( valorAproximado, copia.get( i ), "No se ajustaron correctamente los enteros existentes" );
        }
    }

    @Test
    void testReiniciarArregloEnterosVacio( )
    {
        double[] nuevosValores = new double[]{ 0.1, 1.2, 2.2, 3.4, 4.45, 5.49 };

        vacio.reiniciarArregloEnteros( nuevosValores );
        assertEquals( nuevosValores.length, vacio.getCantidadEnteros( ), "La nueva cantidad de enteros no es la correcta" );

        List<Integer> copia = vacio.getCopiaEnteros( );

        for( int i = 0; i < nuevosValores.length; i++ )
        {
            int valorAproximado = ( int )nuevosValores[ i ];
            assertEquals( valorAproximado, copia.get( i ), "No se ajustaron correctamente los enteros existentes" );
        }
    }

    @Test
    void testReiniciarArregloCadenas( )
    {
        Object[] nuevosValores = new Object[]{ new Double( "0.01" ), new Character( 'a' ), "Hello", new Integer( "25" ), new Boolean( false ) };
        List<Object> objetos = Arrays.asList( nuevosValores );

        sencillo.reiniciarArregloCadenas( objetos );
        assertEquals( nuevosValores.length, sencillo.getCantidadCadenas( ), "La nueva cantidad de cadenas no es la correcta" );

        List<String> copia = sencillo.getCopiaCadenas( );

        for( int i = 0; i < nuevosValores.length; i++ )
        {
            String cadenaEsperada = nuevosValores[ i ].toString( );
            assertEquals( cadenaEsperada, copia.get( i ), "No se ajustaron correctamente las cadenas existentes" );
        }
    }

    @Test
    void testVolverPositivos( )
    {
        sencillo.volverPositivos( );
        List<Integer> copia = sencillo.getCopiaEnteros( );

        for( int i = 0; i < enterosSencillos.length; i++ )
        {
            assertEquals( Math.abs( enterosSencillos[ i ] ), copia.get( i ), "No se ajustaron correctamente los enteros existentes" );
        }
    }

    @Test
    void testVolverPositivosVacio( )
    {
        vacio.volverPositivos( );
        List<Integer> copia = vacio.getCopiaEnteros( );
        assertEquals( 0, copia.size( ), "La cantidad de enteros es correcta" );
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

        List<Integer> copia = sencillo.getCopiaEnteros( );

        for( int i = 1; i < copia.size( ); i++ )
        {
            assertTrue( copia.get( i - 1 ) >= copia.get( i ), "No se organizaron correctamente los enteros" );
        }

    }

    @Test
    void testOrganizarEnterosVacio( )
    {
        vacio.organizarEnteros( );

        List<Integer> copia = vacio.getCopiaEnteros( );
        assertEquals( 0, copia.size( ), "La cantidad de enteros es correcta" );
    }

    @Test
    void testOrganizarCadenas( )
    {
        sencillo.organizarCadenas( );

        List<String> copia = sencillo.getCopiaCadenas( );

        for( int i = 1; i < copia.size( ); i++ )
        {
            assertTrue( copia.get( i - 1 ).compareTo( copia.get( i ) ) <= 0, "No se organizaron correctamente las cadenas" );
        }
    }

    @Test
    void testOrganizarCadenasVacio( )
    {
        vacio.organizarCadenas( );

        List<String> copia = vacio.getCopiaCadenas( );
        assertEquals( 0, copia.size( ), "La cantidad de cadenas es correcta" );
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
    void testGenerarEnterosBasico( )
    {
        sencillo.generarEnteros( 100, -10, 10 );

        assertEquals( 100, sencillo.getCantidadEnteros( ), "La cantidad de elementos no es correcta" );

        List<Integer> copia = sencillo.getCopiaEnteros( );
        int minimo = Integer.MAX_VALUE;
        int maximo = Integer.MIN_VALUE;
        for( Integer valor : copia )
        {
            if( valor < minimo )
                minimo = valor;
            if( valor > maximo )
                maximo = valor;
        }
        assertTrue( -10 <= minimo, "El menor valor del rango no es el correcto: " + minimo + " a " + maximo );
        assertTrue( 10 >= maximo, "El mayor valor del rango no es el correcto: " + minimo + " a " + maximo );
    }

    @Test
    void testGenerarEnterosGrande( )
    {
        sencillo.generarEnteros( 10000, -10, 10 );

        assertEquals( 10000, sencillo.getCantidadEnteros( ), "La cantidad de elementos no es correcta" );

        List<Integer> copia = sencillo.getCopiaEnteros( );
        int minimo = Integer.MAX_VALUE;
        int maximo = Integer.MIN_VALUE;
        for( Integer valor : copia )
        {
            if( valor < minimo )
                minimo = valor;
            if( valor > maximo )
                maximo = valor;
        }
        assertTrue( -10 <= minimo, "El menor valor del rango no es el correcto: " + minimo + " a " + maximo );
        assertTrue( 10 >= maximo, "El mayor valor del rango no es el correcto: " + minimo + " a " + maximo );
    }

    @Test
    void testGenerarEnterosDistribucion( )
    {
        sencillo.generarEnteros( 10000, -10, 10 );

        int minimaCantidad = 10000;
        int maximaCantidad = 0;

        for( int valor = -10; valor <= 10; valor++ )
        {
            int cantidad = sencillo.contarApariciones( valor );
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
