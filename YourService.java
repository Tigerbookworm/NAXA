/* Copyright (c) 2017, United States Government, as represented by the
 * Administrator of the National Aeronautics and Space Administration.
 *
 * All rights reserved.
 *
 * The Astrobee platform is licensed under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package jp.jaxa.iss.kibo.rpc.sampleapk;

import android.nfc.Tag;
import android.util.Log;

import jp.jaxa.iss.kibo.rpc.api.KiboRpcService;

import gov.nasa.arc.astrobee.Result;
import gov.nasa.arc.astrobee.types.Point;
import gov.nasa.arc.astrobee.types.Quaternion;

import org.opencv.core.Mat;

import static android.util.Log.i;

/**
 * Class meant to handle commands from the Ground Data System and execute them in Astrobee
 */

public class YourService extends KiboRpcService {

    private final String TAG = this.getClass().getSimpleName();

    @Override
    protected void runPlan1(){
        Point startP =  new Point(10.76150f, -6.88490f,  5.31647f);
        Quaternion startQ = new Quaternion(0f, 0f, -.707f, 0.707f);
        api.moveTo(startP, startQ, false);
        Log.i(TAG, "Mission Started");
        // the mission starts
        api.startMission();

        // move to point1
        Point point = new Point(10.72000f, -7.75000f, 4.48000f);
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
        i(TAG, "Arrived at Point 1");


        // irradiate the laser
        api.laserControl(true);

        // take target1 snapshots
        api.takeTarget1Snapshot();

        // get a camera image
        Mat image = api.getMatNavCam();

        //Save the image
        api.saveMatImage(image, "point1.png");

        //Record in Log
        Log.i(TAG, "Point 1 reached and photo of Point 1 with laser captured and saved");

        // turn the laser off
        api.laserControl(false);

          // move to point2
        Point point20 = new Point(11.27460f, -9.92284f, 5.29881f);
        Quaternion quaternion20 = new Quaternion(0f, 0f, -0.707f, 0.707f);
        Result result20 = api.moveTo(point20, quaternion20, false);

        final int LOOP_MAX20 = 5;

         // check result and loop if moveTo api does not work
         int loopCounter20 = 0;
         while (!result20.hasSucceeded() && loopCounter20 < LOOP_MAX20){
             //retry
             result20 = api.moveTo(point20, quaternion20, true);
             ++loopCounter20;
           }

        //Move closer to Target2
        Point point21 = new Point(10.71000f, -7.71100f, 4.48000f);
        Quaternion quaternion21 = new Quaternion(0f, 0f, -0.707f, 0.707f);
        Result result21 = api.moveTo(point21, quaternion21, false);

        final int LOOP_MAX21 = 5;

        // check result and loop if moveTo api does not work
        int loopCounter21 = 0;
        while (!result21.hasSucceeded() && loopCounter21 < LOOP_MAX21){
            //retry
            result21 = api.moveTo(point21, quaternion21, true);
            ++loopCounter21;
        }

        Point point22 = new Point(10.71000f, -10.00000f, 5.29881);
        Quaternion quaternion22 = new Quaternion(0f, 0f, -0.707f, 0.707f);
        Result result22 = api.moveTo(point22, quaternion22, false);

        final int LOOP_MAX22 = 5;

        // check result and loop if moveTo api does not work
        int loopCounter22 = 0;
        while (!result22.hasSucceeded() && loopCounter22 < LOOP_MAX22){
            //retry
            result22 = api.moveTo(point22, quaternion22, true);
            ++loopCounter22;
        }

        Point point23 = new Point(10.71000f, -10.00000f, 7.29881f);
        Quaternion quaternion23 = new Quaternion(0f, 0f, -0.707f, 0.707f);
        Result result23 = api.moveTo(point23, quaternion23, false);

        final int LOOP_MAX23 = 5;

        // check result and loop if moveTo api does not work
        int loopCounter23 = 0;
        while (!result23.hasSucceeded() && loopCounter23 < LOOP_MAX23){
            //retry
            result23 = api.moveTo(point23, quaternion23, true);
            ++loopCounter23;
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

        // mission completed
        Log.i(TAG, "Mission completed.");
        api.reportMissionCompletion();
    }}}