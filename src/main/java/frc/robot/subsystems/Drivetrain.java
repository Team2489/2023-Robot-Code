// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;


import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import frc.robot.Constants;
import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;

import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.romi.RomiGyro;

public class Drivetrain extends SubsystemBase {
  /** Creates a new Drivetrain. */
  CANSparkMax rightFrontSpark;
  CANSparkMax rightBackSpark;
  CANSparkMax leftFrontSpark;
  CANSparkMax leftBackSpark;
  MotorControllerGroup rightMotors;
  MotorControllerGroup leftMotors;
  DifferentialDrive dDrive;
  DifferentialDriveOdometry m_odometry;
  Pose2d  m_pose;

  private Spark rightMotorRomi;
  private Spark leftMotorRomi;
  private Encoder leftEncoderRomi;
  private Encoder rightEncoderRomi;
  private RomiGyro gyroRomi;


  public Drivetrain() {
    if (RobotBase.isReal()) {
      rightFrontSpark = new CANSparkMax(Constants.RIGHT_FRONT_SPARK, MotorType.kBrushless);
      rightBackSpark = new CANSparkMax(Constants.RIGHT_BACK_SPARK, MotorType.kBrushless);
      leftFrontSpark = new CANSparkMax(Constants.LEFT_FRONT_SPARK, MotorType.kBrushless);
      leftBackSpark = new CANSparkMax(Constants.LEFT_BACK_SPARK, MotorType.kBrushless);

      rightBackSpark.follow(rightFrontSpark);
      leftBackSpark.follow(leftFrontSpark);

    //rightBackSpark.follow(rightFrontSpark);
    //leftBackSpark.follow(leftFrontSpark);

      rightFrontSpark.setIdleMode(IdleMode.kBrake);
      rightBackSpark.setIdleMode(IdleMode.kBrake);
      leftFrontSpark.setIdleMode(IdleMode.kBrake);
      leftBackSpark.setIdleMode(IdleMode.kBrake);

    rightFrontSpark.setIdleMode(IdleMode.kCoast);
    rightBackSpark.setIdleMode(IdleMode.kCoast);
    leftFrontSpark.setIdleMode(IdleMode.kCoast);
    leftBackSpark.setIdleMode(IdleMode.kCoast);

    } else {
        // PWM channels 0 and 1 respectively
      leftMotorRomi = new Spark(0);
      rightMotorRomi = new Spark(1);

      rightMotors = new MotorControllerGroup(rightMotorRomi);
      leftMotors = new MotorControllerGroup(leftMotorRomi);
      leftEncoderRomi = new Encoder(4, 5);
      rightEncoderRomi = new Encoder(6, 7);

      gyroRomi = new RomiGyro();

    }

    dDrive = new DifferentialDrive(leftMotors, rightMotors);
    //m_odometry = new DifferentialDriveOdometry(
    //    gyroSim.getRotation2d(),
    //    leftEncoderSim.getDistance(), rightEncoderSim.getDistance());

  }

  public void arcadeDrive(double speed, double rotation){
    
    dDrive.arcadeDrive(speed, rotation);
  }
  public void curvatureDrive(double speed, double rotation){
    dDrive.curvatureDrive(speed,rotation, false);
  }
  public void tankDrive(double right, double left){
    rightFrontSpark.set(right);
    rightBackSpark.set(right);
    leftFrontSpark.set(-left);
    leftBackSpark.set(-left);
  }
  

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    //m_pose = m_odometry.update(gyroSim.getRotation2d(),
    //            leftEncoderSim.getDistance(),
    //            rightEncoderSim.getDistance());
  }
}