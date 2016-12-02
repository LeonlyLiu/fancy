package linetrace;

import jp.ac.kagawa_u.infoexpr.Sensor.ColorSensor;
import jp.ac.kagawa_u.infoexpr.Sensor.LightSensor;
import jp.ac.kagawa_u.infoexpr.Sensor.TouchSensor;
import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.motor.Motor;
import lejos.hardware.port.SensorPort;
import lejos.robotics.RegulatedMotor;

public class Main {

    static TouchSensor touch = new TouchSensor(SensorPort.S1);
    static LightSensor rightLight = new LightSensor(SensorPort.S2);
    static ColorSensor leftColor = new ColorSensor(SensorPort.S3);    
    static RegulatedMotor rightMotor  = Motor.B;
    static RegulatedMotor leftMotor  = Motor.C;

    public static void main(String[] args) {
        float middleValue = 0.　40F;
        //遅い速度
        int lowSpeed = 200;
        //速い速度
        int highSpeed = 600;
        //回転速度
        int turnSpeed = 300;

        while　(! Button.ESCAPE.isDown()){

            // 黒＆黒
            if　(getColor(leftColor) == 3 && 
            rightLight.getLight() < middleValue){
            }
            // 黒＆白
            else if　(getColor(leftColor) == 3 && 
	    rightLight.getLight() >= middleValue){
                motorSetSpeed(lowSpeed, highSpeed);
                motorForward();
                detectColor(getColor(leftColor));
            }
            // 白＆黒
            else if　(getColor(leftColor) == 4 && 
            rightLight.getLight() < middleValue){
            	motorSetSpeed(highSpeed, lowSpeed);
                motorForward();
                detectColor(getColor(leftColor));
            }
            // 白＆白
            else if　(getColor(leftColor) == 4 && 
            rightLight.getLight() >= middleValue){
                motorSetSpeed(highSpeed, highSpeed);
                motorForward();
                detectColor(getColor(leftColor));
            }
        }
    }

    private static void motorSetSpeed(int leftMotorSpeed, int rightMotorSpeed){
        leftMotor.setSpeed(leftMotorSpeed);
        rightMotor.setSpeed(rightMotorSpeed);
    }

    private static void motorForward(){
        leftMotor.forward();
        rightMotor.forward();
    }

    private static int getColor(ColorSensor color){
        //赤を認識
    	if　(color.getBlue() < 0.05F && 
	color.getRed() > 0.15F && 
	color.getGreen() < 0.05F) return 1; 
        //緑を認識
   	else if (color.getBlue() < 0.05F && 
	color.getRed() < 0.06F && 
	color.getGreen() > 0.11F)　return 2; 
        //黒を認識
    	else if (color.getBlue() < 0.04F　&& 
	color.getRed() < 0.04F　&& 
	color.getGreen() < 0.04F)　return 3; 
        //白を認識
    	else if (color.getBlue() > 0.10F　&& 
	color.getRed() > 0.10F　&& 
	color.getGreen() > 0.10F)　return 4;
    
    	return 0;
    }

    private static void detectColor(int color){
    	//赤の場合
	if (color == 1)	fullTurn();//一回転
    	//緑の場合
	if (color == 2)	Sound.playTone(200, 100);//音を鳴らす
    }
	
    //一回転をする
    private static void fullTurn(){	
	leftMotor.resetTachoCount();    	
    	leftMotor.setSpeed(turnSpeed);
        rightMotor.setSpeed(turnSpeed);
    	
        while (leftMotor.getTachoCount() < 720){ 
            leftMotor.forward();
            rightMotor.backward();
        }
    }
}
