// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class DriveStraight extends CommandBase {
  /** Creates a new DriveStraight. */
  Drivetrain driveTrain;
  public DriveStraight(Drivetrain driveTrain) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.driveTrain = driveTrain;
    addRequirements(driveTrain);
    
    
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    driveTrain.resetGyros();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    driveTrain.putNumbers();
     if(driveTrain.getAngle() > -5 && driveTrain.getAngle() <5){
        driveTrain.arcadeDriveCustomized(0.1, 0);
    }
     
     else if(driveTrain.getAngle() < -5){
      driveTrain.arcadeDriveCustomized(0, 0.1);
     }
     else{
      driveTrain.arcadeDriveCustomized(0, -0.1);
     }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    driveTrain.stopMotors();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
