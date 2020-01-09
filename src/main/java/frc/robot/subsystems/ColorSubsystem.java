package frc.robot.subsystems;

import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.PWM;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Timer;

public class ColorSubsystem extends SubsystemBase {
  String gameData;
  private boolean x=true;
  private int rotate = 0;
  private double time = Timer.getMatchTime();
  private final I2C.Port i2cPort = I2C.Port.kOnboard;
  private final ColorSensorV3 colorSensor = new ColorSensorV3(i2cPort);
  PWM colorWheel = new PWM(0);

  public ColorSubsystem() {
  }

  public void color(Boolean placeholder) {
    Color color = colorSensor.getColor();
    SmartDashboard.updateValues();
    SmartDashboard.putNumber("rColor", color.red);
    SmartDashboard.putNumber("gColor", color.green);
    SmartDashboard.putNumber("bColor", color.blue);
    SmartDashboard.putNumber("timerthing", Timer.getMatchTime());
    SmartDashboard.updateValues();
    gameData= " ";
    //gameData = DriverStation.getInstance().getGameSpecificMessage();
    switch (gameData.charAt(0)) {
    case 'B':
      // Blue case code
      if (color.blue > .4 && color.green > .4) {
        SmartDashboard.putString("Color", "cyan");
        colorWheel.setSpeed(0);
      }else{
        colorWheel.setSpeed(.5);
      }
      break;
    case 'G':
      // Green case code
      if (color.red < .5 && color.blue < .5 && color.green > .5) {
        SmartDashboard.putString("Color", "green");
        colorWheel.setSpeed(0);
      }else{
        colorWheel.setSpeed(.5);
      }
      break;
    case 'R':
      // Red case code
      if (color.red > .5 && color.blue < .5 && color.green < .5) {
        SmartDashboard.putString("Color", "red");
        colorWheel.setSpeed(.0);
      }else{
        colorWheel.setSpeed(.5);
      }
      break;
    case 'Y':
      // Yellow case code
      if (color.red > .3 && color.blue < .5 && color.green > .5) {
        SmartDashboard.putString("Color", "yellow");
        colorWheel.setSpeed(0);
      } else{
        colorWheel.setSpeed(.5);
      }
      break;
    default:
      // This is corrupt data
      if (placeholder) {
        x=true;
        double fixed = Timer.getMatchTime();
        SmartDashboard.putNumber("fixed", fixed);
        while (x){
          while(Timer.getMatchTime()>=(fixed-5.9)){
           colorWheel.setSpeed(0.5);
          }
          x = false;
          colorWheel.setSpeed(0);
        }
      } else{
        colorWheel.setSpeed(0);
      }
    }
  }
  public void periodic(){

  }
}  