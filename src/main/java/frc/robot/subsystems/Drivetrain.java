// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;



import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import frc.robot.Constants;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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

   AHRS ahrs;
   

   MotorControllerGroup rightMotors;
   MotorControllerGroup leftMotors;
   DifferentialDrive dDrive;
   
   DifferentialDriveOdometry odometry;
   Pose2d pose;

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

    //odometry = new DifferentialDriveOdometry(ahrs.getRotation2d(), rightFrontEncoder.getPosition(), leftFrontEncoder.getPosition());
  
    ahrs = new AHRS(SPI.Port.kMXP);

   
  }
  public void arcadeDrive(double speed, double rotation){
    
    dDrive.arcadeDrive(speed, rotation);
  }
  public void curvatureDrive(double speed, double rotation){
    dDrive.curvatureDrive(speed,rotation, false);
  }
  public void setMotorSpeed(){
    rightFrontSpark.set(-0.5);
    rightBackSpark.set(-0.5);
    leftFrontSpark.set(0.5);
    leftBackSpark.set(0.5);
  }
  public void tankDrive(double right, double left){
    rightFrontSpark.set(right);
    rightBackSpark.set(right);
    leftFrontSpark.set(-left);
    leftBackSpark.set(-left);
  }
  public void arcadeDriveCustomized(double speed, double rotation){
    rightFrontSpark.set(rotation-speed);
    rightBackSpark.set(rotation-speed);
    leftFrontSpark.set(speed+rotation);
    leftBackSpark.set(speed+rotation);
  }
  public void zeroEncoders(){
    rightFrontEncoder.setPosition(0);
    rightBackEncoder.setPosition(0);
    leftFrontEncoder.setPosition(0);
    leftBackEncoder.setPosition(0);
  }
  public void resetPosition(){
    odometry.resetPosition(ahrs.getRotation2d(), rightFrontEncoder.getPosition(), leftFrontEncoder.getPosition(), pose);
  }
  public void updateOdometry(){
    odometry.update(ahrs.getRotation2d(), rightFrontEncoder.getPosition(), leftFrontEncoder.getPosition());
  }
  public void stopMotors(){
    rightFrontSpark.set(0);
    rightBackSpark.set(0);
    leftFrontSpark.set(0);
    leftBackSpark.set(0);
  }
  public void resetGyros(){
    ahrs.reset();
  }
  public double getAngle(){
    return ahrs.getAngle();
  }
  public double getLeftPosition(){
    return leftFrontEncoder.getPosition();
  }
  

  public void putNumbers(){
  SmartDashboard.putNumber("Right Front Position", rightFrontEncoder.getPosition());
  SmartDashboard.putNumber("Right Back Position", rightBackEncoder.getPosition());
  SmartDashboard.putNumber("Left Front Position", leftFrontEncoder.getPosition());
  SmartDashboard.putNumber("Left Back Position", leftBackEncoder.getPosition());
  SmartDashboard.putNumber("Right Front Velocity", rightFrontEncoder.getVelocity());
  SmartDashboard.putNumber("Right Back Velocity", rightBackEncoder.getVelocity());
  SmartDashboard.putNumber("Left Front Velocity", leftFrontEncoder.getVelocity());
  SmartDashboard.putNumber("Left Back Velocity", leftBackEncoder.getVelocity());

  //---------------------------------Odometry--------------------------------------
 

  // --------------------------------NAV X Below-----------------------------------

  SmartDashboard.putBoolean(  "IMU_Connected",        ahrs.isConnected());
  SmartDashboard.putBoolean(  "IMU_IsCalibrating",    ahrs.isCalibrating());
  SmartDashboard.putNumber(   "IMU_Yaw",              ahrs.getYaw());
  SmartDashboard.putNumber(   "IMU_Pitch",            ahrs.getPitch());
  SmartDashboard.putNumber(   "IMU_Roll",             ahrs.getRoll());
  
  /* Display tilt-corrected, Magnetometer-based heading (requires             */
  /* magnetometer calibration to be useful)                                   */
  
  SmartDashboard.putNumber(   "IMU_CompassHeading",   ahrs.getCompassHeading());
  
  /* Display 9-axis Heading (requires magnetometer calibration to be useful)  */
  SmartDashboard.putNumber(   "IMU_FusedHeading",     ahrs.getFusedHeading());

  /* These functions are compatible w/the WPI Gyro Class, providing a simple  */
  /* path for upgrading from the Kit-of-Parts gyro to the navx MXP            */
  
  SmartDashboard.putNumber(   "IMU_TotalYaw",         ahrs.getAngle());
  SmartDashboard.putNumber(   "IMU_YawRateDPS",       ahrs.getRate());

  /* Display Processed Acceleration Data (Linear Acceleration, Motion Detect) */
  
  SmartDashboard.putNumber(   "IMU_Accel_X",          ahrs.getWorldLinearAccelX());
  SmartDashboard.putNumber(   "IMU_Accel_Y",          ahrs.getWorldLinearAccelY());
  SmartDashboard.putBoolean(  "IMU_IsMoving",         ahrs.isMoving());
  SmartDashboard.putBoolean(  "IMU_IsRotating",       ahrs.isRotating());

  /* Display estimates of velocity/displacement.  Note that these values are  */
  /* not expected to be accurate enough for estimating robot position on a    */
  /* FIRST FRC Robotics Field, due to accelerometer noise and the compounding */
  /* of these errors due to single (velocity) integration and especially      */
  /* double (displacement) integration.                                       */
  
  SmartDashboard.putNumber(   "Velocity_X",           ahrs.getVelocityX());
  SmartDashboard.putNumber(   "Velocity_Y",           ahrs.getVelocityY());
  SmartDashboard.putNumber(   "Displacement_X",       ahrs.getDisplacementX());
  SmartDashboard.putNumber(   "Displacement_Y",       ahrs.getDisplacementY());
  
  /* Display Raw Gyro/Accelerometer/Magnetometer Values                       */
  /* NOTE:  These values are not normally necessary, but are made available   */
  /* for advanced users.  Before using this data, please consider whether     */
  /* the processed data (see above) will suit your needs.                     */
  
  SmartDashboard.putNumber(   "RawGyro_X",            ahrs.getRawGyroX());
  SmartDashboard.putNumber(   "RawGyro_Y",            ahrs.getRawGyroY());
  SmartDashboard.putNumber(   "RawGyro_Z",            ahrs.getRawGyroZ());
  SmartDashboard.putNumber(   "RawAccel_X",           ahrs.getRawAccelX());
  SmartDashboard.putNumber(   "RawAccel_Y",           ahrs.getRawAccelY());
  SmartDashboard.putNumber(   "RawAccel_Z",           ahrs.getRawAccelZ());
  SmartDashboard.putNumber(   "RawMag_X",             ahrs.getRawMagX());
  SmartDashboard.putNumber(   "RawMag_Y",             ahrs.getRawMagY());
  SmartDashboard.putNumber(   "RawMag_Z",             ahrs.getRawMagZ());
  SmartDashboard.putNumber(   "IMU_Temp_C",           ahrs.getTempC());
  
  /* Omnimount Yaw Axis Information                                           */
  /* For more info, see http://navx-mxp.kauailabs.com/installation/omnimount  */
  AHRS.BoardYawAxis yaw_axis = ahrs.getBoardYawAxis();
  SmartDashboard.putString(   "YawAxisDirection",     yaw_axis.up ? "Up" : "Down" );
  SmartDashboard.putNumber(   "YawAxis",              yaw_axis.board_axis.getValue() );
  
  /* Sensor Board Information                                                 */
  SmartDashboard.putString(   "FirmwareVersion",      ahrs.getFirmwareVersion());
  
  /* Quaternion Data                                                          */
  /* Quaternions are fascinating, and are the most compact representation of  */
  /* orientation data.  All of the Yaw, Pitch and Roll Values can be derived  */
  /* from the Quaternions.  If interested in motion processing, knowledge of  */
  /* Quaternions is highly recommended.                                       */
  SmartDashboard.putNumber(   "QuaternionW",          ahrs.getQuaternionW());
  SmartDashboard.putNumber(   "QuaternionX",          ahrs.getQuaternionX());
  SmartDashboard.putNumber(   "QuaternionY",          ahrs.getQuaternionY());
  SmartDashboard.putNumber(   "QuaternionZ",          ahrs.getQuaternionZ());
  
  /* Sensor Data Timestamp */
  SmartDashboard.putNumber(   "SensorTimestamp",		ahrs.getLastSensorTimestamp());
  
  /* Connectivity Debugging Support                                           */
  SmartDashboard.putNumber(   "IMU_Byte_Count",       ahrs.getByteCount());
  SmartDashboard.putNumber(   "IMU_Update_Count",     ahrs.getUpdateCount());



}
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}