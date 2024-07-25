#### 1. Declare the motor variables
```java
private final CANSparkMax leftMotor;
private final CANSparkMax rightMotor;
```
This first snippet declares 2 variables, one for each motor. To declare a variable in java, First say the type/class (CANSparkMax), then the variable name (leftMotor).We use the SparkMax motor controller, like you saw in the first activity. We are communicating to them with using the CAN network, so the type we are using is CANSparkMax. All lines end with a semicolon (;). Additionally, these lines have the keywords ``private`` and ``final`` before the variable type & name. ``private`` means it cannot be accessed from outside this file and ``final`` means it cannot be changed. This is called making a variable immutable. It is more efficient. This means the motor cannot be replaced with a different motor using the equal sign, but we can still call functions on this variable, which you will see later. 
#### 2. Initialize the motors
```java
leftMotor = new CANSparkMax(1,MotorType.kBrushless);
rightMotor = new CANSparkMax(2,MotorType.kBrushless);
```
Without going into it too much, java has primitive types like ``double`` (a number) and ``boolean`` (true or false) like any other programming language. Java also has classes, which need to be initialized into objects. Declaration, like what we did in step 1, is the process of defining the variable, along with its type and name. Initialization, on the other hand, is assigning a value. Every java file is a class, including this file and CANSparkMax. Classes are types. Later, when we make button bindings, you will see us initialize this class the same way as we are initializing the motors here. When a class is initialized using the ``new`` keyword, the class's constructor is called. This segment goes in the shooter subsystem's constructor. That means this code will run when the robot is booting.



#### 3. Configure the Motors
```java
leftMotor.setInverted(true);
leftMotor.follow(rightMotor);
```
We are now calling functions on the ``leftMotor`` variable. Specifically, we are making it go the same speed as the right motor, but in the opposite direction. 

#### 4. Create the run function
```java
private void startSpinning() {
  rightMotor.set(1);
}
```
This snippet declares a function. Like the variables in snippet #1, it is private. It also has a type and name. The type here is ``void``, meaning it returns nothing. When a function is called, it runs the code inside its ``{}``, in this case calling another function.Functions can also take inputs, which we would declare in the ``()`` section, but we are just setting a fixed speed, so we don't need any.
#### 5. Create the stop function
```java
private void stop() {
  rightMotor.set(0);
}
```
This is very similar to step #4. On other note: The constructor used in snippets #2&#3 is basically a function, just without a name or return type. 
#### 6. Create the Command
```java
public Command runShooterCommand() {
  return startEnd(this::startSpinning, this::stop);
}
```
Here we jump back into some special FRC logic. This function returns a Command object (using the return keyword). Commands can be bound to buttons. the ``startEnd`` function is part of all subsystems. It just makes it easier to create commands. The ``startEnd`` function makes a command that runs a function (``startSpinning()``) when it starts running, and when it finishes (``stop()``). Don't worry about the ``this::`` for now, just know its calling those functions. 
#### 7. Open the "RobotContainer.java" file
Or click [Here](./src/main/java/frc/robot/RobotContainer.java)
#### 8. Declare the XboxController variable
```java
CommandXboxController controller = new CommandXboxController(0);
```
This snippet declares the controller variable (on port ``0``) with the CommandXboxController type, so we can bind buttons to it. It also initializes it, in the same line!
#### 9. Declare the Subsystem variable
```java
ShooterSubsystem shooterSubsystem = new ShooterSubsystem();
```
This creates a shooterSubsytem object so we can use those public functions.
#### 10. Bind your command to a button
```java
controller.a().whileTrue(shooterSubsystem.runShooterCommand());
```
This line binds the ``a`` button on the controller to the ``runShooterCommand`` we made earlier. Notice how we didn't need to create a variable for the command, we could just use it inline. There are multiple options for button binding, and we are using ``whileTrue`` here. This means the command will start when the button is pressed and end when the button is released. That's the last code snippet!
#### 11. Deploy
Ask your presenter for help and we'll get this code running!
