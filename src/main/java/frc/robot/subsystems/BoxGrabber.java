// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class BoxGrabber extends SubsystemBase {
  /** Creates a new BoxClaw. */
    CANSparkMax boxGrabber;
  public BoxGrabber(){;
    boxGrabber = new CANSparkMax(Constants.BOX_GRABBER, MotorType.kBrushless);
  }
  public void setBoxGrabber(double power){
    boxGrabber.set(power);
  }
  public void boxGrabberStop(){
    boxGrabber.set(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
