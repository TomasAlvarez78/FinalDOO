package modeloFactoryPersona;

import java.lang.reflect.Field;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import mockito-all-1.9.5.jar;
import java.util.List;

import java.util.Calendar;
import java.util.Date;
import patronDAO.EmpleadoDAO;
import patronDAO.SegurosDAO;
import patronDAO.TurnoDAO;
import main.GestorGeneral;


public class GestorgeneralTest {
    GestorGeneral gestorGeneral;
    EmpleadoDAO empleadoDao;
    SegurosDAO segurosDao;
    TurnoDAO turnoDao;
    
    @Before
    public void SetUp()
    {
        empleadoDao= mock(EmpleadoDAO.class);
        segurosDao= mock(SegurosDAO.class);
        turnoDao = mock(TurnoDAO.class);
        gestorGeneral = new GestorGeneral();
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
        
        Empleado emp = gestorGeneral.buscarEmpleado(2039745);
        
        assertTrue(emp instanceof Empleado);
        assertEquals(emp.nombre, "Nombre");
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
        
        Empleado emp = gestorGeneral.buscarEmpleado(2039745);
        
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
        assertEquals(lista.get(0).nombre, "Nombre1");
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
