package com.kcl.keepitclean.main.roadnetwork;

public class SingleLane extends AbstractLaneSection {

	@Override
	public boolean isOpenForward() {
		return getOpen();
	}

	@Override
	public boolean isOpenLeft() {
		return getOpen();
	}

	@Override
	public boolean isOpenRight() {
		return getOpen();
	}

	@Override
	public boolean isOpeanBackward() {
		return getOpen();
	}
}
