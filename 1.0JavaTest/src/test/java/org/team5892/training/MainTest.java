// you likely haven't seen this before. This is organization that matches the folders. Don't worry about it much.
package org.team5892.training;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public final class MainTest {
    private static final PrintStream originalOut = System.out;
    private static final PrintStream originalErr = System.err;
    private static final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private static final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private static final Class<?> fractionClass = Main.FRACTION_CLASS;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    void classTest() {
        assertNotNull(fractionClass, "Fraction class needs to be set in main!");
    }

    @Test
    void helloTest() {
        call("main", false, null, (Object) new String[]{});
        String[] out = outContent.toString().split("\n");
        assertEquals("Hello world!", out[0], "Must print 'Hello world!'");
    }

    @Test
    void divideTest() {
        call("main", false, null, (Object) new String[]{});
        String[] out = outContent.toString().split("\n");
        assertEquals("1/2 / 1/7 = 3.5", out[1], "Must divide and output decimal");
    }

    @Test
    void subtractTest() {
        call("main", false, null, (Object) new String[]{});
        String[] out = outContent.toString().split("\n");
        assertEquals("1/2 - 1/7 = 0.35714285714285715", out[2], "Must subtract and output decimal");
    }

    Object call(String name, boolean shouldBePrivate, Object object, Object... args) {
        try {
            Class<?>[] parameters = getParameters(args);
            Method method = Main.class.getDeclaredMethod(name, parameters);
            boolean canAccess = method.canAccess(object);
            assertEquals(canAccess, !shouldBePrivate, name + "() has unexpected access requirements!");
            if (shouldBePrivate) {
                method.setAccessible(true);
            }
            return method.invoke(object, args);
        } catch (NoSuchMethodException e) {
            fail(name + "() not found!");
        } catch (InvocationTargetException e) {
            fail(name + "() should not throw an exception!");
        } catch (IllegalAccessException e) {
            fail(name + "() should be public!");
        }
        return null;
    }

    Class<?>[] getParameters(Object... args) {
        Class<?>[] parameters = new Class[args.length];
        for (int i = 0; i < args.length; i++) {
            Class<?> clazz = args[i].getClass();
            if (clazz == Double.class) {
                parameters[i] = double.class;
            } else {
                parameters[i] = clazz;
            }
        }
        return parameters;
    }


}
