
/*
* Defines car position based on Lane, Road and LaneSection Indexes 
*/

package com.kcl.keepitclean.main.vehicle;
public class Position (){
	int road=0;
	int lane=0;
	int laneSection=0;
	
	
	void setRoad(int x){
		road= x;
	}
	
	void setLane(int x){
		lane=x;
	}
	void setLaneSection(int x){
		laneSection=x;
	}
	
	int getRoad(){
		return road;
	}
	int getLane(){
		return lane;
	}
	int getLaneSection(){
		return lanesection; 
	}
	int Update (int i ,int j, int  k){
		road = i;
		lane=j;
		laneSection=k;
	}
	
}
