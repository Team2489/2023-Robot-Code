// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase {
  public CANSparkMax rightIntake;
  public CANSparkMax leftIntake;
  public Intake() {
    rightIntake = new CANSparkMax(Constants.RIGHT_INTAKE, MotorType.kBrushless);
    leftIntake = new CANSparkMax(Constants.LEFT_INTAKE, MotorType.kBrushless);

    

    rightIntake.enableVoltageCompensation(12);
    leftIntake.enableVoltageCompensation(12);

    rightIntake.setIdleMode(IdleMode.kBrake);
    leftIntake.setIdleMode(IdleMode.kBrake);

    intakeRun(0);
  }
  
  public void intakeRun(double power){
    rightIntake.set(power);
    leftIntake.set(power);
  }

  public void runRightIntake(double power){
    rightIntake.set(power);
  }
  
  public void runLeftIntake(double power){
    leftIntake.set(power);
  }

  public void stop(){
    rightIntake.set(0);
    leftIntake.set(0);
  }
}