package org.jlab.clas12.analysis;

import org.jlab.clas.physics.Vector3;

/*
 * Bank:VertDoca
 * info:Track Cross information for Particles bank
 * Noraim Nunez (CSUDH), June 22, 2018
 */
public class VertDoca {
    //extends to Traj. might change variable name.
    private Vector3 common = new Vector3(); // position of the common vertex (cm)

    private int index1;
    private int index2;

    private final Vector3 posTrack1 = new Vector3(); //position of the first track at the DOCA point (cm)

    private final Vector3 directTrack1 = new Vector3(); //direction vector of the first track at the DOCA point (cm)

    private final Vector3 posTrack2 = new Vector3(); //position of the second track at the DOCA point (cm)

    private final Vector3 directTrack2 = new Vector3(); //direction vector of the second track at the DOCA point (cm)

    private float r; //distance between two tracks (cm)

    public Vector3 getCommon() {
        return common;
    }

    public Vector3 getPosTrack1() {
        return posTrack1;
    }
    public int getIndex1() {
        return index1;
    }

    public void setIndex1(int index1) {
        this.index1 = index1;
    }

    public int getIndex2() {
        return index2;
    }

    public void setIndex2(int index2) {
        this.index2 = index2;
    }

    public Vector3 getDirectTrack1() {
        return directTrack1;
    }

    public Vector3 getPosTrack2() {
        return posTrack2;
    }

    public Vector3 getDirectTrack2() {
        return directTrack2;
    }

    public float getR() {
        return r;
    }

    public void setR(float r) {
        this.r = r;
    }
}
