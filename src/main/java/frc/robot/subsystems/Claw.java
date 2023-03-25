// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;



import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Claw extends SubsystemBase {
  /** Creates a new Claw. */
  CANSparkMax clawMotor;
  private RelativeEncoder clawEncoder;
  public Claw() {
    clawMotor = new CANSparkMax(Constants.CLAW_MOTOR, MotorType.kBrushless);
    clawEncoder = clawMotor.getEncoder();
    clawMotor.enableVoltageCompensation(12);
    clawMotor.setIdleMode(IdleMode.kBrake);
  }

  public double getEncoderValue(){
    return (clawEncoder.getPosition()*(18/42)*(1/100));
  }
  public void zeroEncoder(){
    clawEncoder.setPosition(0);
  }
  public void rotateClawUp(double power){
    clawMotor.set(power);
  }
  public void stop(){
    clawMotor.set(0);
  }
  public void putNumbers(){
    System.out.println("Encoder value: " + this.getEncoderValue());
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
