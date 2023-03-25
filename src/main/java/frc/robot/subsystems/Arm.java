// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Arm extends SubsystemBase {
  /** Creates a new Arm. */
  CANSparkMax arm;
  public Arm() {
    arm = new CANSparkMax(Constants.ARM_MOTOR, MotorType.kBrushless);
    arm.enableVoltageCompensation(12);
    arm.setIdleMode(IdleMode.kBrake);
  }

  public void moveArm(double power){
    arm.set(power);
  }
  public void stopArm(){
    arm.set(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
