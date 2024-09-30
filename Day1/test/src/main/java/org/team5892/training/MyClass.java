package org.team5892.training;
import org.team5892.training.BaseClass;
public class MyClass implements BaseClass{
    private double number;
    public MyClass (double number) {
        this.number = number;
    }   
    public double getNumber() {
        return this.number;
    }
    public void setNumber(double number) {
        this.number = number;
    }
}
