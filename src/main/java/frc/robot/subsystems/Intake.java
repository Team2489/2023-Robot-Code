// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;




import com.revrobotics.CANSparkMax;

import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase {
  /** Creates a new BoxGrabber. */
  public CANSparkMax rightIntake;
  public CANSparkMax leftIntake;
  public Intake() {


    rightIntake = new CANSparkMax(Constants.RIGHT_INTAKE, MotorType.kBrushless);
    leftIntake = new CANSparkMax(Constants.LEFT_INTAKE, MotorType.kBrushless);
    

    leftIntake.follow(rightIntake);

    rightIntake.enableVoltageCompensation(12);
    leftIntake.enableVoltageCompensation(12);

    
    
  }
 
  
  public void intakeRun(double power){
    rightIntake.set(power);
    leftIntake.set(power);
  }
  public void stop(){
    rightIntake.set(0);
    leftIntake.set(0);
  }
}

  

//   @Override
//   public void periodic() {
//     // This method will be called once per scheduler run
//   }
// }