// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.BoxGrabber;

public class GrabberOne extends CommandBase {
  /** Creates a new GrabberOne. */
  BoxGrabber boxGrabber;
  DigitalInput  digitalInput;
  double power;
  public GrabberOne(BoxGrabber boxGrabber, double power, DigitalInput digitalInput) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.boxGrabber = boxGrabber;
    this.power=power;
    this.digitalInput  = digitalInput;
    addRequirements(boxGrabber);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(digitalInput.get()){
      boxGrabber.stop();
    }
    else{
    boxGrabber.intakeRun(power);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    boxGrabber.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
