// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;



import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class BoxGrabber extends SubsystemBase {
  /** Creates a new BoxGrabber. */
  public WPI_TalonSRX rightIntake;
  public WPI_TalonSRX leftIntake;
  public BoxGrabber() {
    rightIntake = new WPI_TalonSRX(Constants.RIGHT_INTAKE);
    leftIntake = new WPI_TalonSRX(Constants.LEFT_INTAKE);

    leftIntake.follow(rightIntake);

    rightIntake.configVoltageCompSaturation(12);
    leftIntake.configVoltageCompSaturation(12);

    rightIntake.enableVoltageCompensation(true);
    leftIntake.enableVoltageCompensation(true);

    rightIntake.setSafetyEnabled(true);
    leftIntake.setSafetyEnabled(true);
  }
 
  
  public void intake(boolean grab, boolean release){
    if(grab){
      rightIntake.set(ControlMode.PercentOutput, 1);
      leftIntake.set(ControlMode.PercentOutput, 1);
    }
    else if(release){
      rightIntake.set(ControlMode.PercentOutput, -1);
      leftIntake.set(ControlMode.PercentOutput, -1);
    }
    else{
      rightIntake.set(ControlMode.PercentOutput, 0);
      leftIntake.set(ControlMode.PercentOutput, 0);
    }
  }
  public void intakeRun(double power){
    rightIntake.set(ControlMode.PercentOutput, power);
    leftIntake.set(ControlMode.PercentOutput, power);
  }
  public void stop(){
    rightIntake.set(ControlMode.PercentOutput, 0);
    leftIntake.set(ControlMode.PercentOutput, 0);
  }
}

  

//   @Override
//   public void periodic() {
//     // This method will be called once per scheduler run
//   }
// }