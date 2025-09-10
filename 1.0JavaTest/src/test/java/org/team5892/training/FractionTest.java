package org.team5892.training;

import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

class FractionTest {
    private static final PrintStream originalOut = System.out;
    private static final PrintStream originalErr = System.err;
    private static ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private static ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private static final Class<?> fractionClass = Main.FRACTION_CLASS;

    @BeforeEach
    public void setUpStreams() {
        outContent = new ByteArrayOutputStream();
        errContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    void constructorDefaultValues() {
        Object fraction = construct(10);
        assertEquals(10.0, getPrivateDouble("numerator", fraction), "constructor didn't set proper numerator");
        assertEquals(1.0, getPrivateDouble("denominator", fraction), "constructor didn't set proper default denominator");


    }

    @Test
    void getDoubleDifferentDenominator() {
        Object fraction = construct(30);
        setPrivateDouble("numerator", 30, fraction);
        setPrivateDouble("denominator", 10, fraction);
        assertEquals(3.0, call("getDouble", false, fraction), "getDouble() didn't properly convert to decimal");
    }

    @Test
    void setDenominator() {
        Object fraction = construct(2);
        call("setDenominator", false, fraction, 3.0);
        assertEquals(3.0, getPrivateDouble("denominator", fraction), "setDenominator() failed to set denominator");
    }

    @Test
    void setNumerator() {
        Object fraction = construct(1);
        call("setNumerator", false, fraction, 3.0);
        setPrivateDouble("denominator", 1, fraction);
        assertEquals(3.0, getPrivateDouble("numerator", fraction), "setNumerator() failed to set numerator");
        assertEquals(1.0, getPrivateDouble("denominator", fraction), "setNumerator() set the denominator!");
    }

    @Test
    void getNumerator() {
        Object fraction = construct(3);
        setPrivateDouble("numerator", 10, fraction);
        Object result = call("getNumerator", false, fraction);
        assertInstanceOf(Double.class, result, "getNumerator() doesn't return a 'double'!");
        assertEquals(10.0, result, "getNumerator() doesn't return the right number");
    }


    @Test
    void getDenominator() {
        Object fraction = construct(3);
        setPrivateDouble("denominator", 10, fraction);
        Object result = call("getDenominator", false, fraction);
        assertInstanceOf(Double.class, result, "getDenominator() doesn't return a 'double'!");
        assertEquals(10.0, result, "getDenominator() doesn't return the right number");

    }

    @Test
    void multiply() {
        Object fraction1 = construct(1);
        setPrivateDouble("numerator", 1, fraction1);
        setPrivateDouble("denominator", 3, fraction1);
        Object fraction2 = construct(2);
        setPrivateDouble("numerator", 2, fraction2);
        setPrivateDouble("denominator", 7, fraction2);
        call("multiply", false, fraction1, fraction2);
        assertEquals(2.0, getPrivateDouble("numerator", fraction1), "multiply() numerator is wrong");
        assertEquals(21.0, getPrivateDouble("denominator", fraction1), "multiply() denominator is wrong");
    }

    @Test
    void add() {
        Object fraction1 = construct(1);
        setPrivateDouble("numerator", 1, fraction1);
        setPrivateDouble("denominator", 3, fraction1);
        Object fraction2 = construct(2);
        setPrivateDouble("numerator", 2, fraction2);
        setPrivateDouble("denominator", 7, fraction2);
        call("add", false, fraction1, fraction2);
        assertEquals(13.0, getPrivateDouble("numerator", fraction1), "add() numerator is wrong");
        assertEquals(21.0, getPrivateDouble("denominator", fraction1), "add() denominator is wrong");
        // 1/3 + 2/7 = 13/21
    }

    @Test
    void addSameDenominator() {
        Object fraction1 = construct(1);
        setPrivateDouble("numerator", 1, fraction1);
        setPrivateDouble("denominator", 7, fraction1);
        Object fraction2 = construct(2);
        setPrivateDouble("numerator", 2, fraction2);
        setPrivateDouble("denominator", 7, fraction2);
        call("add", false, fraction1, fraction2);
        assertEquals(3.0, getPrivateDouble("numerator", fraction1), "add() numerator is wrong");
        assertEquals(7.0, getPrivateDouble("denominator", fraction1), "add() denominator is wrong");
        // 1/3 + 2/7 = 13/21
    }

    @Test
    void opposite() {
        //TODO This isn't actually done
        Object fraction = construct(1);
        setPrivateDouble("numerator", 1, fraction);
        setPrivateDouble("denominator", 3, fraction);
        call("opposite", false, fraction);
        assertEquals(-1.0, getPrivateDouble("numerator", fraction), "opposite() set the wrong numerator");
        assertEquals(3.0, getPrivateDouble("denominator", fraction), "opposite() shouldn't set the denominator");
    }

    @Test
    void reciprocal() {
        Object fraction = construct(1);
        setPrivateDouble("numerator", 1, fraction);
        setPrivateDouble("denominator", 3, fraction);
        call("reciprocal", false, fraction);
        assertEquals(3.0, getPrivateDouble("numerator", fraction), "inverse() set the wrong numerator");
        assertEquals(1.0, getPrivateDouble("denominator", fraction), "inverse() set the wrong denominator");
    }


    @Test
    void makeDenominatorValid0() {
        Object fraction = construct(2);
        setPrivateDouble("numerator", 2, fraction);
        setPrivateDouble("denominator", 0, fraction);
        call("makeDenominatorValid", true, fraction);
        assertEquals(2.0, getPrivateDouble("numerator", fraction), "makeDenominatorValid() should not change the numerator");
        assertEquals(1.0, getPrivateDouble("denominator", fraction), "makeDenominatorValid() should set the denominator to 1");
        assertEquals("Denominator was 0. That's not allowed! Setting it to 1 so life can continue\n", errContent.toString(), "Error message wasn't sent to stderr");
        assertEquals("", outContent.toString(), "Error message shouldn't be sent to stdout");

    }

    @Test
    void makeDenominatorValidAlreadyValid() {
        Object fraction = construct(2);
        setPrivateDouble("numerator", 2, fraction);
        setPrivateDouble("denominator", 3, fraction);
        call("makeDenominatorValid", true, fraction);
        assertEquals(2.0, getPrivateDouble("numerator", fraction), "makeDenominatorValid() should not change the numerator");
        assertEquals(3.0, getPrivateDouble("denominator", fraction), "makeDenominatorValid() should set the denominator to 1 when the denominator is 0");
        assertEquals("", errContent.toString(), "Error message shouldn't be sent");

    }

    Object construct(double d) {
        if (fractionClass == null) {
            fail("Please set FRACTION_CLASS in main.java");
        }
        try {
            return fractionClass.getConstructor(double.class).newInstance(d);
        } catch (NoSuchMethodException e) {
            fail("No constructor found!");
        } catch (InvocationTargetException | InstantiationException e) {
            fail(e);
        } catch (IllegalAccessException e) {
            fail("constructor must be public!");
        }
        return null;
    }

    Object call(String name, boolean shouldBePrivate, Object object, Object... args) {
        if (fractionClass == null) {
            fail("Please set FRACTION_CLASS in main.java");
        }
        try {
            Class<?>[] parameters = getParameters(args);
            Method method = fractionClass.getDeclaredMethod(name, parameters);
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

    double getPrivateDouble(String name, Object object) {
        if (fractionClass == null) {
            fail("Please set FRACTION_CLASS in main.java");
        }
        try {
            Field field = fractionClass.getDeclaredField(name);
            if (field.canAccess(object)) {
                fail(name + " should not be accessible");
            }
            field.setAccessible(true);
            return field.getDouble(object);
        } catch (NoSuchFieldException e) {
            fail(name + " not found!");
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Internal Logic Error. Help!!!");
        }
        return Double.NEGATIVE_INFINITY;
    }

    void setPrivateDouble(String name, double value, Object object) {
        try {
            Field field = fractionClass.getDeclaredField(name);
            field.setAccessible(true);
            field.setDouble(object, value);
        } catch (NoSuchFieldException e) {
            fail(name + " not found!");
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Internal Logic Error. Help!!!");
        }
    }


}