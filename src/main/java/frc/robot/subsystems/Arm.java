// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.math.controller.ArmFeedforward;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;

import edu.wpi.first.wpilibj2.command.ProfiledPIDSubsystem;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

// sim dependencies
import edu.wpi.first.wpilibj.simulation.EncoderSim;

public class Arm extends SubsystemBase {

    private class Frame extends ProfiledPIDSubsystem {
        private final PWMSparkMax m_frameMotor =
            new PWMSparkMax(Constants.frameMotorPort);
        private final Encoder m_frameEncoder =
            new Encoder(Constants.kEncoderPorts[0], Constants.kEncoderPorts[1]);
        private final ArmFeedforward m_frameFeedforward =
            new ArmFeedforward(Constants.kSVolts, Constants.kGVolts,
                               Constants.kVVoltSecondPerRad,
                               Constants.kAVoltSecondSquaredPerRad);

        public Frame() {
            super(
                new ProfiledPIDController(
                    Constants.kP,
                    0,
                    0,
                    new TrapezoidProfile.Constraints(
                        Constants.kMaxVelocityRadPerSecond,
                        Constants.kMaxAccelerationRadPerSecSquared)),
                0);
            m_frameEncoder.setDistancePerPulse(Constants.kEncoderDistancePerPulse);
        }

        @Override
        public void useOutput(double output, TrapezoidProfile.State setpoint) {
            // Calculate the feedforward from the sepoint
            double feedforward = m_frameFeedforward.calculate(setpoint.position,
                                                              setpoint.velocity);
            // Add the feedforward to the PID output to get the motor output
            m_frameMotor.setVoltage(output + feedforward);
        }

        @Override
        public double getMeasurement() {
            return m_frameEncoder.getDistance() + Constants.kArmOffsetRads;
        }
        // Start arm at rest in neutral position
        //setGoal(Constants.kArmOffsetRads);
    }

    private class Rod extends ProfiledPIDSubsystem {
        private final PWMSparkMax m_rodMotor =
            new PWMSparkMax(Constants.rodMotorPort);
        private final Encoder m_rodEncoder =
            new Encoder(Constants.kEncoderPorts[2], Constants.kEncoderPorts[3]);
        private final ArmFeedforward m_rodFeedforward =
            new ArmFeedforward(Constants.kSVolts, Constants.kGVolts,
                               Constants.kVVoltSecondPerRad,
                               Constants.kAVoltSecondSquaredPerRad);

        public Rod() {
            super(
                new ProfiledPIDController(
                    Constants.kP,
                    0,
                    0,
                    new TrapezoidProfile.Constraints(
                        Constants.kMaxVelocityRadPerSecond,
                        Constants.kMaxAccelerationRadPerSecSquared)),
                0);
            m_rodEncoder.setDistancePerPulse(Constants.kEncoderDistancePerPulse);
        }

        @Override
        public void useOutput(double output, TrapezoidProfile.State setpoint) {
            // Calculate the feedforward from the sepoint
            double feedforward = m_rodFeedforward.calculate(setpoint.position,
                                                            setpoint.velocity);
            // Add the feedforward to the PID output to get the motor output
            m_rodMotor.setVoltage(output + feedforward);
        }

        @Override
        public double getMeasurement() {
            return m_rodEncoder.getDistance() + Constants.kArmOffsetRads;
        }

    }

    public Arm() {
        Frame frame = new Frame();
        frame.setGoal(0);

        Rod rod = new Rod();
        rod.setGoal(0);
    }

    public void intake(){
        // move frame and rod to position for pickup,
        //  frame.setGoal(X);
        //  rod.setGoal(X);
        // take cubicBall with claw
    }

    public void throwUp(){
        // move frame and rod to position to throw up,
        // throw cubicBall with claw
    }

    public void throwMid(){
        // move frame and rod to position to throw mid,
        // throw cubicBall with claw
    }

    public void rest(){
        // move frame and rod to rest position
    }

    public void calibrate() {
    }

    public void stop(){
        // stop all motors (frame, rod, claw)
    }

}

