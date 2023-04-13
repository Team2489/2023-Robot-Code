// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.commands.LowGoalAuto;
import frc.robot.commands.LowGoalAutoStop;
import frc.robot.commands.BrakeMotors;
import frc.robot.commands.CubeAdjust;
import frc.robot.commands.CubeShoot;
import frc.robot.commands.DriveArcadeCustomized;
import frc.robot.commands.HighGoalAuto;
import frc.robot.commands.HighGoalAutoStop;
import frc.robot.commands.IntakeIn;
import frc.robot.commands.IntakeOut;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Drivetrain;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */

public class RobotContainer {
  // The robot's subsystems and commands are defined here...

  Intake boxGrabber = new Intake();
  Drivetrain driveTrain = new Drivetrain();
  DigitalInput digitalInput = new DigitalInput(Constants.LINE_BREAKER_PORT); 
  
  XboxController xboxController = new XboxController(Constants.XBOX_CONTROLLER_PORT);
  XboxController xboxController2 = new XboxController(Constants.XBOX_CONTROLLER_PORT_2);
  
  SendableChooser<Command> chooser = new SendableChooser<>();

  LowGoalAuto lowGoalAuto = new LowGoalAuto(driveTrain, 0, 0.5, boxGrabber, -0.4);
  LowGoalAutoStop lowGoalAutoStop = new LowGoalAutoStop(driveTrain, boxGrabber, -0.4);
  HighGoalAutoStop highGoalAutoStop= new HighGoalAutoStop(boxGrabber, -1);
  HighGoalAuto highGoalAuto = new HighGoalAuto(driveTrain, 0, -0.5, boxGrabber, -1);

  public RobotContainer() {
    configureBindings();
    driveTrain.setDefaultCommand(new DriveArcadeCustomized(driveTrain, xboxController::getLeftY, xboxController::getRightX, 0.3, 0.2, 0.8, xboxController));
    chooser.setDefaultOption("Low Goal Auto", lowGoalAuto);
    chooser.addOption("Low Goal Auto Stop", lowGoalAutoStop);
    chooser.addOption("High Goal Auto Stop", highGoalAutoStop);
    chooser.addOption("High Goal Auto", highGoalAuto);
    SmartDashboard.putData(chooser);
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */

  private void configureBindings() {
      new JoystickButton(xboxController2, Button.kRightBumper.value).whileTrue(new IntakeOut(boxGrabber, 0.35));
      new JoystickButton(xboxController2, Button.kLeftBumper.value).whileTrue(new IntakeIn(boxGrabber, -0.35, digitalInput, xboxController2));
      new JoystickButton(xboxController2, Button.kA.value).whileTrue(new CubeShoot(boxGrabber, -1));
      new JoystickButton(xboxController2, Button.kX.value).whileTrue(new CubeAdjust(boxGrabber, 0.15));
      new JoystickButton(xboxController2, Button.kY.value).whileTrue(new IntakeIn(boxGrabber, -0.35, digitalInput, xboxController));
      new JoystickButton(xboxController, Button.kA.value).whileTrue(new BrakeMotors(driveTrain));
    }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand(){
    return chooser.getSelected();
  }
  
}