package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "TankBot")
public class TankTeleop extends OpMode {

    private DcMotor leftWheels, rightWheels, arm;
    private Servo gripperRod, gripperLeft, gripperRight;
    private boolean gripperLogic;

    @Override
    public void init() {

      leftWheels = hardwareMap.dcMotor.get("mapLeft");
      leftWheels.setDirection(DcMotorSimple.Direction.REVERSE);
      rightWheels = hardwareMap.dcMotor.get("mapRight");

      arm = hardwareMap.dcMotor.get("Arm");

      gripperRod = hardwareMap.servo.get("MattsRod");
      gripperLeft = hardwareMap.servo.get("mapGripperLeft");
      gripperRight = hardwareMap.servo.get("mapGripperRight");

      gripperLogic = true;
    }

    @Override
    public void loop() {
        gripperRod.setPosition(1);
        leftWheels.setPower(gamepad1.left_stick_y);
        rightWheels.setPower(gamepad1.right_stick_y);

        if (gamepad2.x) {
            gripperLeft.setPosition(1);
            gripperRight.setPosition(0);
        }
        if (gamepad2.b) {
            gripperLeft.setPosition(.5);
            gripperRight.setPosition(.5);
        }


        if (gamepad2.y && gripperLogic && gripperLeft.getPosition() == .5){
            gripperLeft.setPosition(1);
            gripperRight.setPosition(0);
            gripperLogic = !gripperLogic;

        }
        else if (gamepad2.y && gripperLogic && gripperLeft.getPosition() == 1){
            gripperLeft.setPosition(1);
            gripperRight.setPosition(0);
            gripperLogic = !gripperLogic;
        }
        if (!gamepad2.y){
            gripperLogic = true;
        }
        arm.setPower(gamepad2.left_stick_y);
    }
}
