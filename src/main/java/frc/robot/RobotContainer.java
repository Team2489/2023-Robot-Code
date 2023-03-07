// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;


import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.commands.DriveArcade;
import frc.robot.commands.DriveCurvature;
import frc.robot.commands.DriveTank;
import frc.robot.commands.GrabberOne;
import frc.robot.subsystems.BoxGrabber;
// import frc.robot.commands.GrabberIntake;
// import frc.robot.commands.GrabberOne;
// import frc.robot.subsystems.BoxGrabber;
import frc.robot.subsystems.Drivetrain;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  BoxGrabber boxGrabber = new BoxGrabber();
  Drivetrain driveTrain = new Drivetrain();

  XboxController xboxController = new XboxController(Constants.XBOX_CONTROLLER_PORT);

  // Replace with CommandPS4Controller or CommandJoystick if needed
  

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();
    // driveTrain.setDefaultCommand(new DriveArcade(driveTrain, xboxController::getRightX, xboxController::getLeftY));
    driveTrain.setDefaultCommand(new DriveArcade(driveTrain, xboxController::getRightX, xboxController::getLeftY));
    //driveTrain.setDefaultCommand(new DriveCurvature(driveTrain, xboxController::getRightX, xboxController::getLeftY));
    //driveTrain.setDefaultCommand(new DriveTank(driveTrain, xboxController::getRightY, xboxController::getLeftY));
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
    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`
      new JoystickButton(xboxController, Button.kX.value).whileTrue(new GrabberOne(boxGrabber, 0.75));
      new JoystickButton(xboxController, Button.kY.value).whileTrue(new GrabberOne(boxGrabber, 0.75));

    // Schedule `exampleMethodCommand` when the Xbox controller's B button is pressed,
    // cancelling on release.
   
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  
}