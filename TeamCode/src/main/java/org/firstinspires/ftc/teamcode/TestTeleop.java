package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;


@TeleOp(name = "RunTest")
public class TestTeleop extends OpMode {

    DcMotor frontLeft, frontRight, backLeft, backRight;


    @Override
    public void init() {

        frontLeft = (DcMotor) hardwareMap.get("mapFrontLeft");
        frontRight = (DcMotor) hardwareMap.get("mapFrontRight");
        backLeft = (DcMotor) hardwareMap.get("mapBackLeft");
        backRight = (DcMotor) hardwareMap.get("mapBackRight");
    }

    @Override
    public void loop() {
        doDrive();
    }

    private void doDrive() {
        double drive = gamepad1.left_stick_y;
        double strafe = gamepad1.left_stick_x;
        double rotate = gamepad1.right_stick_x;

        double FL = drive + strafe + rotate;
        double FR = drive - strafe - rotate;
        double BL = drive - strafe  + rotate;
        double BR = drive + strafe - rotate;

        frontLeft.setPower(Range.clip(FL, -1, 1));
        frontRight.setPower(Range.clip(FR, -1, 1));
        backLeft.setPower(Range.clip(BL, -1, 1));
        backRight.setPower(Range.clip(BR, -1, 1));
    }
}
