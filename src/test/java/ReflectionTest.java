import com.Shalimov.Reflection;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class ReflectionTest {

    @Test
    public void testCreateObject() throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        Object object = Reflection.createObject(PersonClassForTest.class);
        assertEquals(PersonClassForTest.class, object.getClass());
    }

    @Test
    public void testEvokeMethodsWithoutParameters() {
        PersonClassForTest testObject = new PersonClassForTest();
        List<?> methods = Reflection.invokeMethodsWithoutParameters(testObject);
        assertEquals(7, methods.size());
        assertTrue(methods.contains("method1"));
        assertTrue(methods.contains("method3"));
        assertFalse(methods.contains("method2"));
    }

    //Asc about test correctness criterion
    @Test
    public void testPrintSignatureFinalMethods() throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        Reflection.methodsWithSignatureContainsFinal(Reflection.createObject(PersonClassForTest.class));
    }

    @Test
    public void testPrintMethodsModifierExceptPublic() {
        Reflection.classWithoutPublicMethods(PersonClassForTest.class);
    }

    @Test
    public void testPrintSuperClassAndInterface() {
        Reflection.returnAllSuperClassesAndInterfacesOfClass(PersonClassForTest.class);
    }

    @Test
    public void testSetDefaultValues() throws ReflectiveOperationException {
        ClassForTest testClass = new ClassForTest();
        Reflection.changePrivateParameters(testClass);
        assertEquals(0, testClass.getPrivateInteger());
        assertEquals(2, testClass.getPublicInteger());
        assertEquals(0.0, testClass.getPrivateDouble(), 0);
        assertEquals(1.1, testClass.getPublicDouble(), 0);
        assertEquals(0, testClass.getPrivateChar());
        assertEquals('a', testClass.getPublicChar());
        assertFalse(testClass.getPrivateBoolean());
        assertTrue(testClass.getPublicBoolean());
        assertNull(testClass.getPrivateString());
        assertEquals("Hello!", testClass.getPublicString());
    }
}
