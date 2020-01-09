/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class DriveSubsystem extends SubsystemBase {
  SpeedController frontLeft = new WPI_TalonSRX(Constants.FRONT_LEFT_MOTOR_ID);
  SpeedController frontRight = new WPI_TalonSRX(Constants.FRONT_RIGHT_MOTOR_ID);
  SpeedController rearLeft = new WPI_TalonSRX(Constants.REAR_LEFT_MOTOR_ID);
  SpeedController rearRight = new WPI_TalonSRX(Constants.REAR_RIGHT_MOTOR_ID);

  SpeedControllerGroup leftSpeedController = new SpeedControllerGroup(frontLeft, rearLeft);
  SpeedControllerGroup rightSpeedController = new SpeedControllerGroup(frontRight, rearRight);

  DifferentialDrive drive = new DifferentialDrive(leftSpeedController, rightSpeedController);



  /**
   * Creates a new ExampleSubsystem.
   */
  public DriveSubsystem() {

  }

  public void drive(double fwd, double rot) {
    drive.arcadeDrive(fwd, rot);
  }

  public void autoDrive(double fwd, double rot){
    double gameData = DriverStation.getInstance().getMatchTime();
    if(gameData > 10){
      drive.arcadeDrive(fwd, rot);
    }
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
