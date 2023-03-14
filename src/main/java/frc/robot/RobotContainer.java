// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;


import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.commands.DriveArcade;
import frc.robot.commands.DriveArcadeCustomized;
import frc.robot.commands.DriveCurvature;
import frc.robot.commands.DriveTank;
import frc.robot.commands.GrabberOne;
import frc.robot.commands.ArmCalibrate;
import frc.robot.commands.ArmTake;
import frc.robot.commands.ArmThrowUp;
import frc.robot.commands.ArmThrowMid;
import frc.robot.subsystems.BoxGrabber;
import frc.robot.subsystems.Arm;
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
  Arm arm = new Arm();
  BoxGrabber boxGrabber = new BoxGrabber();
  Drivetrain driveTrain = new Drivetrain();
  DigitalInput digitalInput = new DigitalInput(Constants.LINE_BREAKER_PORT); // add port number in constants file
  

  XboxController xboxController = new XboxController(Constants.XBOX_CONTROLLER_PORT);

  POVButton[] povButtons;
  //POVButton m_povDown = new POVButton(xboxController,0);
  //POVButton m_povLeft = new POVButton(xboxController,90);
  //POVButton m_povUp = new POVButton(xboxController,180);
  //POVButton m_povRight = new POVButton(xboxController,270);
  // Replace with CommandPS4Controller or CommandJoystick if needed
  

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    for (int i=0; i<4; ++i) {
      povButtons[i] = new POVButton(xboxController, i*90);
    }

    // Configure the trigger bindings
    configureBindings();
    // driveTrain.setDefaultCommand(new DriveArcade(driveTrain, xboxController::getRightX, xboxController::getLeftY));
    //driveTrain.setDefaultCommand(new DriveTank(driveTrain, xboxController::getRightY, xboxController::getLeftY));
    //driveTrain.setDefaultCommand(new DriveCurvature(driveTrain, xboxController::getRightX, xboxController::getLeftY));
    //driveTrain.setDefaultCommand(new DriveTank(driveTrain, xboxController::getRightY, xboxController::getLeftY));
    driveTrain.setDefaultCommand(new DriveArcadeCustomized(driveTrain, xboxController::getRightX, xboxController::getLeftY));
    
    driveTrain.putNumbers();
    
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
      new JoystickButton(xboxController, Button.kX.value).whileTrue(new GrabberOne(boxGrabber, 0.75, digitalInput));
      new JoystickButton(xboxController, Button.kY.value).whileTrue(new GrabberOne(boxGrabber, 0.75, digitalInput));

      new JoystickButton(xboxController, Button.kA.value).whileTrue(new ArmCalibrate(arm));
      povButtons[0].onTrue(new ArmTake(arm));
      povButtons[1].onTrue(new ArmThrowUp(arm));
      povButtons[2].onTrue(new ArmThrowMid(arm));
    // Schedule `exampleMethodCommand` when the Xbox controller's B button is pressed,
    // cancelling on release.
   
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  
}
