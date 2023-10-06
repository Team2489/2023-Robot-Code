// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class AutonomousCommand extends SequentialCommandGroup {
  /** Creates a new AutonomousCommand. */
  DigitalInput digitalInput;
  XboxController xboxController2;
  public AutonomousCommand(Drivetrain driveTrain, double power, double rotation, Intake boxGrabber, double intakePower, DigitalInput digitalInput, XboxController xboxController2) {
    // Add your commands in the addCommands() call, e.g.

    addCommands(
    new Drive(driveTrain, power, 0).withTimeout(0.75),
    new CubeShoot(boxGrabber, intakePower, -1).withTimeout(1.55),
    new Drive(driveTrain, 0,-rotation).withTimeout(1.25));
  }
}
