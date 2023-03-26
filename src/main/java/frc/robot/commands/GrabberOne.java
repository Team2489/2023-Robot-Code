// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;

public class GrabberOne extends CommandBase {
  /** Creates a new GrabberOne. */
  Intake boxGrabber = null;
  DigitalInput  digitalInput = null;
  double power = 0.0;
  XboxController xboxController = null;

  public GrabberOne(Intake boxGrabber, double power, DigitalInput digitalInput, XboxController xboxController) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.boxGrabber = boxGrabber;
    this.power=power;
    this.xboxController = xboxController;
    this.digitalInput = digitalInput;
    addRequirements(boxGrabber);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    boxGrabber.stop();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(digitalInput.get()){
      boxGrabber.intakeRun(power*0.75);
    }
    else if(xboxController.getRightBumper()){
      boxGrabber.intakeRun(0.75);
    }
    else{
      boxGrabber.stop();
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    boxGrabber.stop();
    System.out.println("intake ended");
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
