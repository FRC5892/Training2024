// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;

public class ShooterSubsystem extends SubsystemBase {
  /** Creates a new ShooterSubsystem. */
  CANSparkMax lMotor = new CANSparkMax(ShooterConstants.LEFT_MOTOR_ID, MotorType.kBrushless);
  CANSparkMax rMotor = new CANSparkMax(ShooterConstants.RIGHT_MOTOR_ID, MotorType.kBrushless);

  
  public ShooterSubsystem() {
    // if the P of either shooter motor is greater than 0.5, than it is supposed to be the arm, so throw an exception to avoid damage
    if (lMotor.getPIDController().getP() > 0.5 || rMotor.getPIDController().getP() > 0.5) {
      DriverStation.reportError("ID mismatch detected. Are ids right?", false);
      throw new RuntimeException("ID mismatch detected. Are ids right?");
    }
    lMotor.restoreFactoryDefaults();
    rMotor.restoreFactoryDefaults();
    
    lMotor.follow(rMotor);
    lMotor.setInverted(true);
  }
  public Command runShooterCommand() {
    return startEnd(()->rMotor.set(1), () -> rMotor.set(0));
  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
