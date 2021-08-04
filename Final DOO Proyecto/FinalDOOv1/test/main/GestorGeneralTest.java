/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import modeloFactoryPersona.Empleado;
import modeloFactoryPersona.Oficinista;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import patronDAO.EmpleadoDAO;
import patronDAO.SegurosDAO;
import patronDAO.TurnoDAO;

/**
 *
 * @author cirkuit
 */
public class GestorGeneralTest {
    
    GestorGeneral gestorGeneral;
    EmpleadoDAO empleadoDao;
    SegurosDAO segurosDao;
    TurnoDAO turnoDao;
    
    public GestorGeneralTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        empleadoDao = mock(EmpleadoDAO.class);
        segurosDao = mock(SegurosDAO.class);
        turnoDao = mock(TurnoDAO.class);
        gestorGeneral = new GestorGeneral();
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testAdd1Plus1() 
    {
        int x  = 3 ; int y = 2;
        assertEquals(5, x+y);
    }
    
    @Test
    public void buscarEmpleado_Ok() 
    {
        when(empleadoDao.buscarEmpleado(anyInt())).thenReturn(getEmpleado());
        try {
            Field reader = GestorGeneral.class.getDeclaredField("empleadoDao");
            reader.setAccessible(true);
            reader.set(gestorGeneral, empleadoDao);
        } catch (IllegalAccessException | IllegalArgumentException | NoSuchFieldException | SecurityException ex) {
            System.out.println("Error: " + ex);
        }
        
        Empleado emp = gestorGeneral.buscarEmpleado(123);
        
        assertTrue(emp instanceof Empleado);
        assertEquals(emp.getNombre(), "Nombre");
    }
    
    @Test
    public void buscarEmpleado_NotFound() 
    {
        when(empleadoDao.buscarEmpleado(anyInt())).thenReturn(null);
        try {
            Field reader = GestorGeneral.class.getDeclaredField("empleadoDao");
            reader.setAccessible(true);
            reader.set(gestorGeneral, empleadoDao);
        } catch (IllegalAccessException | IllegalArgumentException | NoSuchFieldException | SecurityException ex) {
            System.out.println("Error: " + ex);
        }
        
        Empleado emp = gestorGeneral.buscarEmpleado(123);
        
        assertNull(emp);
    }
    
    @Test
    public void buscarEmpleadosMecanicos_Ok() 
    {
        when(empleadoDao.listarEmpleadosMecanicos(anyInt())).thenReturn(getListEmpleados());
        try {
            Field reader = GestorGeneral.class.getDeclaredField("empleadoDao");
            reader.setAccessible(true);
            reader.set(gestorGeneral, empleadoDao);
        } catch (IllegalAccessException | IllegalArgumentException | NoSuchFieldException | SecurityException ex) {
            System.out.println("Error: " + ex);
        }
        
        List<Empleado> lista = gestorGeneral.buscarEmpleadosMecanicos(2);
        
        assertNotNull(lista);
        assertEquals(lista.get(0).getNombre(), "Nombre1");
    }
    
    public void buscarEmpleadosMecanicos_Null() 
    {
        when(empleadoDao.listarEmpleadosMecanicos(anyInt())).thenReturn(null);
        try {
            Field reader = GestorGeneral.class.getDeclaredField("empleadoDao");
            reader.setAccessible(true);
            reader.set(gestorGeneral, empleadoDao);
        } catch (IllegalAccessException | IllegalArgumentException | NoSuchFieldException | SecurityException ex) {
            System.out.println("Error: " + ex);
        }
        
        List<Empleado> lista = gestorGeneral.buscarEmpleadosMecanicos(3);
        
        assertNull(lista);
    }
    
    @Test
    public void listarSeguros_Ok() 
    {
        when(segurosDao.getSeguros()).thenReturn(getListSeguros());
        try {
            Field reader = GestorGeneral.class.getDeclaredField("segurosDao");
            reader.setAccessible(true);
            reader.set(gestorGeneral, segurosDao);
        } catch (IllegalAccessException | IllegalArgumentException | NoSuchFieldException | SecurityException ex) {
            System.out.println("Error: " + ex);
        }
        
        List<String> lista= segurosDao.getSeguros();
        
        assertNotNull(lista);
        assertEquals(lista.get(1), "La Caja");
    }
    
    @Test
    public void listarSeguros_Null() 
    {
        when(segurosDao.getSeguros()).thenReturn(null);
        try {
            Field reader = GestorGeneral.class.getDeclaredField("segurosDao");
            reader.setAccessible(true);
            reader.set(gestorGeneral, segurosDao);
        } catch (IllegalAccessException | IllegalArgumentException | NoSuchFieldException | SecurityException ex) {
            System.out.println("Error: " + ex);
        }
        
        List<String> lista= segurosDao.getSeguros();
        
        assertNull(lista);
    }
    
    @Test
    public void verificarExistenciaTurno_True() 
    {
        when(turnoDao.verificarExistenciaTurno(any(Date.class),any(Empleado.class))).thenReturn(true);
        try {
            Field reader = GestorGeneral.class.getDeclaredField("turnoDao");
            reader.setAccessible(true);
            reader.set(gestorGeneral, turnoDao);
        } catch (IllegalAccessException | IllegalArgumentException | NoSuchFieldException | SecurityException ex) {
            System.out.println("Error: " + ex);
        }
        
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 10);
        cal.set(Calendar.MONTH, 1);
        cal.set(Calendar.YEAR, 2021);
        
        boolean estado = gestorGeneral.verificarExistenciaTurno(cal.getTime(),getEmpleado());
        
        assertTrue(estado);
    }
    
    
    
    private Empleado getEmpleado()
    {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 10);
        cal.set(Calendar.MONTH, 1);
        cal.set(Calendar.YEAR, 2021);
        
        Empleado empleado= new Oficinista(123, "Nombre", "Apellido", cal.getTime(), "M");
        return empleado;
    }
    
    private List<Empleado> getListEmpleados()
    {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 10);
        cal.set(Calendar.MONTH, 1);
        cal.set(Calendar.YEAR, 2021);
        
        List<Empleado>lista=new ArrayList<>();
        Empleado empleado1= new Oficinista(123, "Nombre1", "Apellido1", cal.getTime(), "M");
        lista.add(empleado1);
        Empleado empleado2= new Oficinista(321, "Nombre2", "Apellido2", cal.getTime(), "F");
        lista.add(empleado2);
        
        return lista;
    }
    
    private List<String> getListSeguros()
    {
        List<String> seguros=new ArrayList<>();
        
        seguros.add("Sancor");
        seguros.add("La Caja");
        seguros.add("San cristobal");
        
        return seguros;
    }
    
}
