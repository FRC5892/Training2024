package org.team5892.training;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

class FractionTest {
    private static final PrintStream originalOut = System.out;
    private static final PrintStream originalErr = System.err;
    private static final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private static final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private static final Class<Fraction> fractionClass = Main.FRACTION_CLASS;

    @BeforeAll
    public static void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterAll
    public static void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    void getDoubleDefault() {
        Object object = construct(10);
        assertEquals(10d, call("getDouble", false, object));
    }

    @Test
    void getDoubleDifferentDenominator() {
        Object fraction = construct(30);
        call("setDenominator", false, fraction, 10.0);
        assertEquals(3.0, call("getDouble", false, fraction));
    }

    @Test
    void setDenominator() {
        Object fraction = construct(2);
        call("setDenominator", false, fraction, 3.0);
        assertEquals(3.0, getPrivateDouble("denominator", fraction));
    }

    @Test
    void setNumerator() {
        Object fraction = construct(1);
        call("setNumerator", false, fraction, 3.0);
        assertEquals(3.0, getPrivateDouble("numerator", fraction));
        assertEquals(1.0, getPrivateDouble("denominator", fraction));
    }

    @Test
    void getNumerator() {
        Object fraction = construct(3);
        setPrivateDouble("numerator",10 ,fraction);
        Object result = call("getNumerator", false, fraction);
        assertInstanceOf(Double.class, result);
        assertEquals(10.0, result );
    }


    @Test
    void getDenominator() {
        Object fraction = construct(3);
        setPrivateDouble("denominator",10 ,fraction);
        Object result = call("getDenominator", false, fraction);
        assertInstanceOf(Double.class, result);
        assertEquals(10.0, result );

    }

    @Test
    void multiply() {
        Object fraction1 = construct(1);
        setPrivateDouble("numerator",1 ,fraction1);
        setPrivateDouble("denominator",3 ,fraction1);
        Object fraction2 = construct(2);
        setPrivateDouble("numerator",2 ,fraction2);
        setPrivateDouble("denominator",7 ,fraction2);
        call("multiply", false, fraction1, fraction2);
        assertEquals(2.0, getPrivateDouble("numerator", fraction1));
        assertEquals(21.0, getPrivateDouble("denominator", fraction1));
    }

    @Test
    void add() {
        Object fraction1 = construct(1);
        setPrivateDouble("numerator",1 ,fraction1);
        setPrivateDouble("denominator",3 ,fraction1);
        Object fraction2 = construct(2);
        setPrivateDouble("numerator",2 ,fraction2);
        setPrivateDouble("denominator",7 ,fraction2);
        call("add", false, fraction1, fraction2);
        assertEquals(13.0, getPrivateDouble("numerator", fraction1));
        assertEquals(21.0, getPrivateDouble("denominator", fraction1));
        // 1/3 + 2/7 = 13/21
    }

    @Test
    void opposite() {
        //TODO This isn't actually done
        Object fraction1 = construct(1);
        setPrivateDouble("numerator",1 ,fraction1);
        setPrivateDouble("denominator",3 ,fraction1);
        Object fraction2 = construct(2);
        setPrivateDouble("numerator",2 ,fraction2);
        setPrivateDouble("denominator",7 ,fraction2);
        call("multiply", false, fraction1, fraction2);
        assertEquals(2.0, getPrivateDouble("numerator", fraction1));
        assertEquals(21.0, getPrivateDouble("denominator", fraction1));
    }

    @Test
    void makeDenominatorValid() {
        //TODO

    }

    Object construct(double d) {
        try {
            return fractionClass.getConstructor(double.class).newInstance(d);
        } catch (NoSuchMethodException e) {
            fail("getDouble class not found!");
        } catch (InvocationTargetException | InstantiationException e) {
            fail();
        } catch (IllegalAccessException e) {
            fail("constructor must be public!");
        }
        return null;
    }

    Object call(String name, boolean shouldBePrivate, Object object, Object... args) {
        try {
            Class<?>[] parameters = getParameters(args);
            Method method = fractionClass.getDeclaredMethod(name, parameters);
            boolean canAccess = method.canAccess(object);
            assertEquals(canAccess, !shouldBePrivate, name + "() has unexpected access requirements!");
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
        try {
            Field field = fractionClass.getDeclaredField(name);
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