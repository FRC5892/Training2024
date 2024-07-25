// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ShooterSubsystem extends SubsystemBase {
  // START snippet #1
  // private final CANSparkMax leftMotor;
  // private final CANSparkMax rightMotor;
  // END snippet #1

  public ShooterSubsystem() {
    // START snippet #2
    // leftMotor = new CANSparkMax(1,MotorType.kBrushless);
    // rightMotor = new CANSparkMax(2,MotorType.kBrushless);
    // END snippet #2

    // START snippet #3
    // leftMotor.setInverted(true);
    // leftMotor.follow(rightMotor);
    // END snippet #3
  }
  // START snippet #4
  // private void startSpinning() {
  //   rightMotor.set(1);
  // }
  // END snippet #4

  // START snippet #5
  // private void stop() {
  //   rightMotor.set(0);
  // }
  // END snippet #5 

  // START snippet #6
  // public Command runShooterCommand() {
  //   return startEnd(this::startSpinning, this::stop);
  // }
  // END snippet #6 


  
  // Don't worry about this
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
