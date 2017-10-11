package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;


@TeleOp(name = "RunTest")
public class TestTeleop extends OpMode {

    DcMotor frontLeft, frontRight, backLeft, backRight;


    @Override
    public void init() {

        frontLeft = (DcMotor) hardwareMap.get("mapFrontLeft");
        frontRight = (DcMotor) hardwareMap.get("mapFrontRight");
        backLeft = (DcMotor) hardwareMap.get("mapBackLeft");
        backRight = (DcMotor) hardwareMap.get("mapBackRight");


        telemetry.addData("Status", "Initialized");

    }

    @Override
    public void loop() {

        if (gamepad1.a){
            backLeft.setPower(0.5);
        } else backLeft.setPower(0);

        if (gamepad1.b){
            backRight.setPower(0.5);
        } else backRight.setPower(0);

        if (gamepad1.x){
            frontLeft.setPower(0.5);
        } else frontLeft.setPower(0);

        if (gamepad1.y){
            frontRight.setPower(0.5);
        } else frontRight.setPower(0);


    }
}
