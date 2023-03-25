// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;


import edu.wpi.first.util.sendable.Sendable;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilderImpl;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.commands.AutonomousCommand;
import frc.robot.commands.AutonomousCommandThree;
import frc.robot.commands.AutonomousCommandTwo;
import frc.robot.commands.ClawGrab;
import frc.robot.commands.DriveArcadeCustomized;
import frc.robot.commands.DriveFast;
import frc.robot.commands.GrabberOne;
import frc.robot.commands.MoveArm;
import frc.robot.commands.MoveClaw;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.BoxGrabber;
import frc.robot.subsystems.Claw;
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
  Intake boxGrabber = new Intake();
  BoxGrabber gBox = new BoxGrabber();
  Drivetrain driveTrain = new Drivetrain();
  Claw claw = new Claw();
  Arm arm = new Arm();
  DigitalInput digitalInput = new DigitalInput(Constants.LINE_BREAKER_PORT); // add port number in constants file
  

  XboxController xboxController = new XboxController(Constants.XBOX_CONTROLLER_PORT);
  XboxController xboxController2 = new XboxController(Constants.XBOX_CONTROLLER_PORT_2);
  
  SendableChooser<Command> chooser = new SendableChooser<>();
  // Replace with CommandPS4Controller or CommandJoystick if needed
 //AutonomousCommand autoCommand = new AutonomousCommand(driveTrain, 0.5,  -0.5, boxGrabber, 0.75);
  AutonomousCommand autoCommand = new AutonomousCommand(driveTrain, 0, -0.5, gBox, .6);
  AutonomousCommandTwo autoCommandTwo= new AutonomousCommandTwo(driveTrain);
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();
    // driveTrain.setDefaultCommand(new DriveArcade(driveTrain, xboxController::getRightX, xboxController::getLeftY));
    //driveTrain.setDefaultCommand(new DriveTank(driveTrain, xboxController::getRightY, xboxController::getLeftY));
    //driveTrain.setDefaultCommand(new DriveCurvature(driveTrain, xboxController::getRightX, xboxController::getLeftY));
    //driveTrain.setDefaultCommand(new DriveTank(driveTrain, xboxController::getRightY, xboxController::getLeftY));
    driveTrain.setDefaultCommand(new DriveArcadeCustomized(driveTrain, xboxController::getLeftY, xboxController::getRightX, 0.8, xboxController));
    
    driveTrain.putNumbers();
   
    chooser.setDefaultOption("Default Auto Command", autoCommand);
    chooser.addOption("Second Auto Command", autoCommandTwo);

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
    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`
      //new JoystickButton(xboxController, Button.kRightBumper.value).whileTrue(new DriveFast(driveTrain, -1));
      //new JoystickButton(xboxController, Button.kLeftBumper.value).whileTrue(new DriveFast(driveTrain, 1));
      new JoystickButton(xboxController2, Button.kRightBumper.value).whileTrue(new GrabberOne(boxGrabber, 1, digitalInput, xboxController2));
      new JoystickButton(xboxController2, Button.kLeftBumper.value).whileTrue(new GrabberOne(boxGrabber, -1, digitalInput, xboxController2));
      new JoystickButton(xboxController2, Button.kA.value).whileTrue(new MoveClaw(claw, 0.1));
      new JoystickButton(xboxController2, Button.kB.value).whileTrue(new MoveClaw(claw, -0.1));
      new JoystickButton(xboxController2, Button.kX.value).whileTrue(new ClawGrab(gBox, 0.6));
      new JoystickButton(xboxController2, Button.kY.value).whileTrue(new ClawGrab(gBox, -0.1));
      //new JoystickButton(xboxController2, Button.kRightBumper.value).whileTrue(new MoveArm(arm, 0.1));
      //new JoystickButton(xboxController2, Button.kLeftBumper.value).whileTrue(new MoveArm(arm, -0.1));

    // Schedule `exampleMethodCommand` when the Xbox controller's B button is pressed,
    // cancelling on release.
   
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