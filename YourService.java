package jp.jaxa.iss.kibo.rpc.sampleapk;

import android.util.Log;

import jp.jaxa.iss.kibo.rpc.api.KiboRpcService;

import gov.nasa.arc.astrobee.Result;
import gov.nasa.arc.astrobee.types.Point;
import gov.nasa.arc.astrobee.types.Quaternion;

import org.opencv.core.Mat;

/**
 * Class meant to handle commands from the Ground Data System and execute them in Astrobee
 */

public class YourService extends KiboRpcService {

    private final String TAG = this.getClass().getSimpleName();

    @Override
    protected void runPlan1(){
        Log.i(TAG, "Start Mission");
        // the mission starts
        api.startMission();

        // move to point1
        Point point = new Point(10.71000f, -7.70000f, 4.48000f);
        Quaternion quaternion = new Quaternion(0f, 0.707f, 0f, 0.707f);
        Result result = api.moveTo(point, quaternion, false);

        final int LOOP_MAX = 5;

        // check result and loop if moveTo api does not work
        int loopCounter = 0;
        while (!result.hasSucceeded() && loopCounter < LOOP_MAX){
            //retry
            result = api.moveTo(point, quaternion, true);
            ++loopCounter;
        }
        // report point1 arrival
        api.reportPoint1Arrival();


        // irradiate the laser
        api.laserControl(true);

        // take target1 snapshots
        api.takeTarget1Snapshot();

        // get a camera image
        Mat image = api.getMatNavCam();

        //Save the image
        api.saveMatImage(image, "point1.png");

        // turn the laser off
        api.laserControl(false);

        // move to point2
        Point point2 = new Point(11.27460f, -9.92284f, 5.29881f);
        Quaternion quaternion2 = new Quaternion(0f, 0f, -0.707f, 0.707f);
        Result result2 = api.moveTo(point2, quaternion2, false);

        final int LOOP_MAX2 = 5;

        // check result and loop if moveTo api does not work
        int loopCounter2 = 0;
        while (!result2.hasSucceeded() && loopCounter2 < LOOP_MAX2){
            //retry
            result2 = api.moveTo(point2, quaternion2, true);
            ++loopCounter2;
         }


        // irradiate the laser
        api.laserControl(true);

        // take target2 snapshots
        api.takeTarget2Snapshot();

        // get a camera image
        Mat image2 = api.getMatNavCam();

        //Save the image
        api.saveMatImage(image2, "point2.png");

        // turn the laser off
        api.laserControl(false);

        // move to point_goal
        Point point_goal = new Point(11.27460f, -7.89178f, 4.96538f);
        Quaternion quaternion_goal = new Quaternion(0f, 0f, -0.707f, 0.707f);
        Result result_goal = api.moveTo(point_goal, quaternion_goal, false);

        final int LOOP_MAX_goal = 5;

        // check result and loop if moveTo api does not work
        int loopCounter_goal = 0;
        while (!result_goal.hasSucceeded() && loopCounter_goal < LOOP_MAX_goal){
            //retry
            result_goal = api.moveTo(point_goal, quaternion_goal, true);
            ++loopCounter_goal;


        // send mission completion
        api.reportMissionCompletion();
    }}}