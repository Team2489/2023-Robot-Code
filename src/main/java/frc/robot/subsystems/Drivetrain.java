// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;


import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import frc.robot.Constants;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drivetrain extends SubsystemBase {
  /** Creates a new Drivetrain. */
   CANSparkMax rightFrontSpark;
   CANSparkMax rightBackSpark;
   CANSparkMax leftFrontSpark;
   CANSparkMax leftBackSpark;
   MotorControllerGroup rightMotors;
   MotorControllerGroup leftMotors;
   DifferentialDrive dDrive;
   

  public Drivetrain() {
    rightFrontSpark = new CANSparkMax(Constants.RIGHT_FRONT_SPARK, MotorType.kBrushless);
    rightBackSpark = new CANSparkMax(Constants.RIGHT_BACK_SPARK, MotorType.kBrushless);
    leftFrontSpark = new CANSparkMax(Constants.LEFT_FRONT_SPARK, MotorType.kBrushless);
    leftBackSpark = new CANSparkMax(Constants.LEFT_BACK_SPARK, MotorType.kBrushless);

    rightBackSpark.follow(rightFrontSpark);
    leftBackSpark.follow(leftFrontSpark);

    rightFrontSpark.enableVoltageCompensation(12);
    rightBackSpark.enableVoltageCompensation(12);
    leftFrontSpark.enableVoltageCompensation(12);
    leftBackSpark.enableVoltageCompensation(12);

    rightFrontSpark.setIdleMode(IdleMode.kBrake);
    rightBackSpark.setIdleMode(IdleMode.kBrake);
    leftFrontSpark.setIdleMode(IdleMode.kBrake);
    leftBackSpark.setIdleMode(IdleMode.kBrake);


    rightMotors = new MotorControllerGroup(rightFrontSpark, rightBackSpark);
    leftMotors = new MotorControllerGroup(leftFrontSpark, leftBackSpark);
    dDrive = new DifferentialDrive(leftMotors, rightMotors);

   
  }
  public void arcadeDrive(double speed, double rotation){
    
    dDrive.arcadeDrive(speed, rotation);
  }
  public void curvatureDrive(double speed, double rotation){
    dDrive.curvatureDrive(speed,rotation, false);
  }
  

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}