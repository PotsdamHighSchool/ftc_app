package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.*;
import com.qualcomm.robotcore.hardware.*;
import com.qualcomm.robotcore.util.Range;


@TeleOp(name = "OmniBot")
public class OmniTeleop extends OpMode {

    private DcMotor frontLeft, frontRight, backLeft, backRight;
    private Servo gripperLeft, gripperRight;

    double forwardVector, strafeVector, rotate;

    double rawFL, rawFR, rawBL, rawBR;



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
        telemetry.addData("Motors", "\n(%.2f) | (%.2f)\n(%.2f) | (%.2f)",rawFL, rawFR, rawBL, rawBR  );
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
}
