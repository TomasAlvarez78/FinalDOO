/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloFactoryPersona;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author cirkuit
 */
public class PersonaTest {
    
    public PersonaTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    /**
     * Test of getNombreCompleto method, of class Persona.
     */
    @Test
    public void testGetNombreCompleto() {
        System.out.println("getNombreCompleto");
        
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 10);
        cal.set(Calendar.MONTH, 1);
        cal.set(Calendar.YEAR, 2021);
        
        Persona instance = new Cliente(42569343,"Tomas","Alvarez",cal.getTime(),"M","Lamborgini",101);
        //int dni,String nombre, String apellido, Date fechaNacimiento,String sexo,String auto,int companiaSeguro
        String expResult = "Tomas Alvarez";
        String result = instance.getNombreCompleto();
        
        System.out.println(result + " = " + expResult);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }

    /**
     * Test of getEdad method, of class Persona.
     */
    @Test
    public void testGetEdad() throws ParseException {
        System.out.println("getEdad");
        Persona instance = new Cliente(42569343,"Tomas","Alvarez",new SimpleDateFormat("yyyy-MM-dd").parse("2000-06-10"),"M","Lamborgini",101);
        //new SimpleDateFormat("yyyy-MM-dd").parse("2021-06-10")
        int expResult = 21;
        int result = instance.getEdad();
        System.out.println("Edad resultante: " + result + " anios");
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
    
    //TEST DE DAO
    

    public class PersonaImpl extends Persona {

        public PersonaImpl() {
            super(0, "", "", null, "");
        }

        public String getTipo() {
            return "";
        }

        @Override
        public int getEspecialidad() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }
    
}
