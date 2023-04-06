// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
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

   RelativeEncoder rightFrontEncoder;
   RelativeEncoder rightBackEncoder;
   RelativeEncoder leftFrontEncoder;
   RelativeEncoder leftBackEncoder;
  
   MotorControllerGroup rightMotors;
   MotorControllerGroup leftMotors;
   DifferentialDrive dDrive;

  public Drivetrain() {
    rightFrontSpark = new CANSparkMax(Constants.RIGHT_FRONT_SPARK, MotorType.kBrushless);
    rightBackSpark = new CANSparkMax(Constants.RIGHT_BACK_SPARK, MotorType.kBrushless);
    leftFrontSpark = new CANSparkMax(Constants.LEFT_FRONT_SPARK, MotorType.kBrushless);
    leftBackSpark = new CANSparkMax(Constants.LEFT_BACK_SPARK, MotorType.kBrushless);

    rightFrontSpark.setInverted(false);
    rightBackSpark.setInverted(false);
    leftFrontSpark.setInverted(true);
    leftBackSpark.setInverted(true);

    rightFrontSpark.restoreFactoryDefaults();
    rightBackSpark.restoreFactoryDefaults();
    leftFrontSpark.restoreFactoryDefaults();
    leftBackSpark.restoreFactoryDefaults();

    rightFrontEncoder = rightFrontSpark.getEncoder();
    rightBackEncoder = rightBackSpark.getEncoder();
    leftFrontEncoder = leftFrontSpark.getEncoder();
    leftBackEncoder = leftBackSpark.getEncoder();
    
    rightFrontSpark.enableVoltageCompensation(12);
    rightBackSpark.enableVoltageCompensation(12);
    leftFrontSpark.enableVoltageCompensation(12);
    leftBackSpark.enableVoltageCompensation(12);

   

    rightMotors = new MotorControllerGroup(rightFrontSpark, rightBackSpark);
    leftMotors = new MotorControllerGroup(leftFrontSpark, leftBackSpark);
    dDrive = new DifferentialDrive(leftMotors, rightMotors);

    arcadeDrive(0, 0);
    arcadeDriveCustomized(0,0);
  }
  
  public void arcadeDrive(double speed, double rotation){
    dDrive.arcadeDrive(speed, rotation);
  }
  
  public void arcadeDriveCustomized(double speed, double rotation){
    rightFrontSpark.set(rotation-speed);
    rightBackSpark.set(rotation-speed);
    leftFrontSpark.set(speed+rotation);
    leftBackSpark.set(speed+rotation);
  }

  public void stopMotors(){
    rightFrontSpark.set(0);
    rightBackSpark.set(0);
    leftFrontSpark.set(0);
    leftBackSpark.set(0);
  }
  
  @Override
    public void periodic() {
  }
}