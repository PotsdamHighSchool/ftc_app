package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;


@TeleOp(name = "OmniBot")
public class OmniTeleop extends OpMode {

    private DcMotor frontLeft, frontRight, backLeft, backRight;
    private Servo gripperLeft, gripperRight;

    private double forwardVector, strafeVector, rotate;

    private double rawFL, rawFR, rawBL, rawBR;

    private boolean gripper;
    private boolean x=true;

    @Override
    public void init() {

        frontLeft = (DcMotor) hardwareMap.get("mapFrontLeft");
        frontRight = (DcMotor) hardwareMap.get("mapFrontRight");
        backLeft = (DcMotor) hardwareMap.get("mapBackLeft");
        backRight = (DcMotor) hardwareMap.get("mapBackRight");

        gripperLeft = hardwareMap.servo.get("mapGripperLeft");
        gripperRight = hardwareMap.servo.get("mapGripperRight");

        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);


    }

    @Override
    public void loop() {
        normalDrive();
        grippers();

            //testDrive();
    }

    private void normalDrive() {

         forwardVector = -gamepad1.left_stick_y/3;
         strafeVector = gamepad1.left_stick_x/3;
         rotate = gamepad1.right_stick_x/3;

         rawFL = forwardVector + strafeVector + rotate;
         rawFR = forwardVector - strafeVector - rotate;
         rawBL = forwardVector - strafeVector + rotate;
         rawBR = forwardVector + strafeVector - rotate;


        frontLeft.setPower(Range.clip(-1, rawFL, 1));
        frontRight.setPower(Range.clip(-1, rawFR, 1));
        backLeft.setPower(Range.clip(-1, rawBL, 1));
        backRight.setPower(Range.clip(-1, rawBR, 1));

    }

    private void grippers(){
        //Gripper Servos

        toggleGripper(gamepad1.a);

        if (gamepad1.x){
            gripperLeft.setPosition(1);
            gripperRight.setPosition(0);
        }
        if (gamepad1.b){
            gripperLeft.setPosition(.5);
            gripperRight.setPosition(.5);
        }
    }

    private void testDrive(){
        if(gamepad1.x){
            frontLeft.setPower(.5);
        }
        else{
            frontLeft.setPower(0);
        }
        if(gamepad1.y){
            frontRight.setPower(.5);
        }
        else{
            frontRight.setPower(0);
        }
        if(gamepad1.a){
            backLeft.setPower(.5);
        }
        else{
            backLeft.setPower(0);
        }
        if(gamepad1.b){
            backRight.setPower(.5);
        }
        else{
            backRight.setPower(0);
        }
    }
    private void totallyRealGUI() {
        if (rawFL > 0) {
            if (rawFR > 0) {
                if (rawBL > 0) {
                    if (rawBR > 0) {
                        telemetry.addData("Motors", "\n(%.2f) | (%.2f)\n(%.2f) | (%.2f)", rawFL, rawFR, rawBL, rawBR);
                    }
                        else{
                            telemetry.addData("Motors", "\n(%.2f) | (%.2f)\n(%.2f) |(%.2f)", rawFL, rawFR, rawBL, rawBR);
                        }
                    if (rawBR > 0) {
                        telemetry.addData("Motors", "\n(%.2f) | (%.2f)\n(%.2f) | (%.2f)", rawFL, rawFR, rawBL, rawBR);
                    }
                    else{
                        telemetry.addData("Motors", "\n(%.2f) | (%.2f)\n(%.2f) |(%.2f)", rawFL, rawFR, rawBL, rawBR);
                    }

                }
            }

        }
    }
    private void toggleGripper(boolean Pressed){
        double GL = gripperLeft.getPosition();
        double GR = gripperRight.getPosition();

        if(Pressed && x){
            x=false;
            if (GL == .5){
                gripperLeft.setPosition(1);
                gripperRight.setPosition(0);
            }
            else if( GL == 1) {
                gripperLeft.setPosition(.5);
                gripperRight.setPosition(.5);
            }
        }
        if(!Pressed){
            x=true;
        }

        /*
        if (newValue != gripper){
            if (gripper && GL != 1.00){
                gripperLeft.setPosition(1);
                gripperRight.setPosition(0);
            } else if( GR != 0.5) {
                gripperLeft.setPosition(.5);
                gripperRight.setPosition(.5);
            }
        }

        gripper = newValue;
*/
    }

}
