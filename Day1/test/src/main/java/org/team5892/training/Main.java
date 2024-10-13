package org.team5892.training;

public final class Main {
    /* This line is used for auto testing/grading. It will be set in step 3.V */
    public final static Class<?> FRACTION_CLASS = Fraction.class;

    /* If this is not present, there will be weird errors*/
    public static void main(String[] args) {
        System.out.println("Hello world!");
        {
            Fraction fraction1 = new Fraction(1);
            fraction1.setDenominator(2);
            Fraction fraction2 = new Fraction(1);
            fraction2.setDenominator(7);
            fraction2.reciprocal();
            fraction1.multiply(fraction2);
            System.out.println("1/2 / 1/7 = " + fraction1.getDouble());
        }
        {
            Fraction fraction1 = new Fraction(1);
            fraction1.setDenominator(2);
            Fraction fraction2 = new Fraction(1);
            fraction2.setDenominator(7);
            fraction2.opposite();
            fraction1.add(fraction2);
            System.out.println("1/2 - 1/7 = " + fraction1.getDouble());
        }


    }
}
