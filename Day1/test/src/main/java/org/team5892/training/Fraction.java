package org.team5892.training;

public class Fraction {

    private double numerator;
    private double denominator;

    public Fraction(double newNumerator) {
        this.numerator = newNumerator;
        this.denominator = 1;
    }
    public double getDouble() {
        return numerator/denominator;
    }
    public void multiply(Fraction other) {
        this.setNumerator(other.getNumerator()*this.getNumerator());
        this.setNumerator(other.getNumerator()*this.getNumerator());
    }
    public void add(Fraction other) {
        this.setNumerator(other.getNumerator()+this.getNumerator());
        if (this.numerator != other.getDenominator()) {
            this.setNumerator(other.getNumerator()*this.getNumerator());
        } else {
            this.setDenominator(other.getNumerator()*this.getNumerator());
        }
    }
    public void opposite() {
        this.setNumerator(-getNumerator());
    }
    public double getNumerator() {
        return numerator;
    }
    public void setNumerator(double numerator) {
        this.numerator = numerator;
    }
    public double getDenominator() {
        return denominator;
    }
    public void setDenominator(double denominator) {
        this.denominator = denominator;
        makeDenominatorValid();
    }
    private void makeDenominatorValid() {
        if (this.denominator == 0) {
            System.err.println("Denominator was 0. That's not allowed! Setting it to 1 so life can continue");
             denominator = 1;
        }
    }


}
