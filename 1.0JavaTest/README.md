# Java Basics Badge Test
Finish this to earn your Java Basics badge! 
This test is a little weird but it uses the java we robotics really uses

## On a chromebook
[![Open in GitHub Codespaces](https://github.com/codespaces/badge.svg)](https://codespaces.new/FRC5892/Training2024)

Open a in a new Codespace (just click create codespace if prompted for settings) and hit 3 dots -> file -> open folder -> day 1 -> test -> ok
## Testing
This project was designed with unit tests, i.e a way for developers to make sure a change doesn't 
break anything unexpected by testing all functionality before the new code can get too far. This 
means the code will test itself. Here are the possible ways to test the code:
* click the Wpilib icon (gear with a W) in the top right corner and select `Test Robot Code`
* Hit ctl+shift+p and type `Test Robot Code`

Whichever way you choose, the code will compile and run. Check the console to see the output.
If it says BUILD FAILED, then you still have work to do. If you want, you can scroll up and 
try to read why it failed, but you could also just ask for help.

## Instructions
You are implementing a Fraction system in java! yay! 

> [!WARNING]
>  The auto grading requires precise names.
>  Words that are formatted like `this`* and have a * need to be precise, so copy and pasting is recommend

> [!NOTE]
> in reality, you should always return a new object, but we are modifying the object for simplicity and to show knowledge.
####  In the `Main` class
1. Create a main method
2. print `Hello world!`* to the standard output (stdout).
#### Making a `Fraction` class
3. Make the class 
   1. in the explorer tab on the right, right-click the (condensed) folders org/team5892/training.
   2. click on `Create a new class or Command`.
   3. select `Empty Class`.
   4. Add that class to `Main.java` line 5, replacing the `null`. Example: `= Fraction.class`
4. Add a field for the ``numerator``*, and a field for the ``denominator``*. They should be inaccessible from other classes. These numbers should be stored as the most common type for whole or decimal values (not a float).
5. Add an accessible by anyone constructor that takes in and sets the numerator. The denominator should default to `1.0`.
> [!IMPORTANT]
> The constructor should take only one argument, the numerator 
6. Add public getters and setters for both fields.
7. The setter of denominator should validate the new denominator by calling `makeDenominatorValid()`.
8. Add a private method `makeDenominatorValid()`* (takes in no arguments) that checks if the denominator is 0. If it is 0, it sets th denominator to `1.0` and sends the message `Denominator was 0. That's not allowed! Setting it to 1 so life can continue`* to the standard error console (stderr).
> [!TIP]
> Stderr is the standard error console. In java it can be accessed by `System.err` instead of `System.out`
9. Add a public method, in the correct case, that returns (gets) the double equivalent of the fraction (hint: divide).
10. Add a public method `add(Fraction)`* that takes in a fraction and adds it to itself. It shouldn't return anything. If the denominators are different, multiply (no need to simplify). If they are the same, leave the denominator the same. Use math knowledge to implement the numerator adding (for both same and different denominators).
11. To allow subtraction, add a public method `opposite()`* that makes the `numerator` negative. It should not change the denominator.
12. Add a public method `multiply(Fraction)`* that takes in a fraction and multiplies itself by that other fraction. There is no need to simplify.
13. Add a method `reciprocal()`* that makes the fraction a reciprocal of itself.
#### 'Testing' in `Main`
14. In the `Main` method, make a fraction with the value 1/2
15. Divide that by another fraction, with a value of 1/7 (by first taking the reciprocal of the 2nd fraction)
16. print the output (to stdout) like this `1/2 / 1/7 = x.x`, where `x.x` is the double of the fraction after division
17. Do steps 14-16 again, but this time subtract `1/2` by `1/7`
18. Test! (Look at the test section above)
