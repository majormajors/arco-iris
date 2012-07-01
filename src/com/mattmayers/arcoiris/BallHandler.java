package com.mattmayers.arcoiris;

import java.util.ArrayList;
import java.util.List;

import orbotix.robot.base.Robot;
import orbotix.robot.base.RobotProvider;

public class BallHandler {
    private List<Robot> robots = new ArrayList<Robot>();
    
    public BallHandler() {
    }
    
    public Robot add(String robotId) {
        Robot robot = RobotProvider.getDefaultProvider().findRobot(robotId);
        this.add(robot);
        return robot;
    }
    
    public void add(Robot robot) {
        robots.add(robot);
    }
    
    public Robot get(int position) {
        return robots.get(position);
    }
    
    public int size(){
        return robots.size();
    }
}
