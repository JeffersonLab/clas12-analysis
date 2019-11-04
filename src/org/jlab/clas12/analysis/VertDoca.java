package org.jlab.clas12.analysis;
/*
 * Bank:VertDoca  
 * info:Track Cross information for Particles bank
 * Noraim Nunez (CSUDH), June 22, 2018 
 */
public class VertDoca {
    //extends to Traj. might change variable name. 
    private float x;//position of the common vertex (cm)
    private float y;
    private float z; 
    //
    
    private int index1;
    private int index2;
    private float x1;//position of the first track at the DOCA point (cm)
    private float y1;
    private float z1; 
    private float cx1;//direction vector of the first track at the DOCA point (cm)
    private float cy1;
    private float cz1; 
    private float x2;//position of the second track at the DOCA point (cm)
    private float y2;
    private float z2;
    private float cx2;//direction vector of the second track at the DOCA point (cm)
    private float cy2;
    private float cz2;
    private float r; //distance between two tracks (cm)

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
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

    public float getX1() {
        return x1;
    }

    public void setX1(float x1) {
        this.x1 = x1;
    }

    public float getY1() {
        return y1;
    }

    public void setY1(float y1) {
        this.y1 = y1;
    }

    public float getZ1() {
        return z1;
    }

    public void setZ1(float z1) {
        this.z1 = z1;
    }

    public float getCx1() {
        return cx1;
    }

    public void setCx1(float cx1) {
        this.cx1 = cx1;
    }

    public float getCy1() {
        return cy1;
    }

    public void setCy1(float cy1) {
        this.cy1 = cy1;
    }

    public float getCz1() {
        return cz1;
    }

    public void setCz1(float cz1) {
        this.cz1 = cz1;
    }

    public float getX2() {
        return x2;
    }

    public void setX2(float x2) {
        this.x2 = x2;
    }

    public float getY2() {
        return y2;
    }

    public void setY2(float y2) {
        this.y2 = y2;
    }

    public float getZ2() {
        return z2;
    }

    public void setZ2(float z2) {
        this.z2 = z2;
    }

    public float getCx2() {
        return cx2;
    }

    public void setCx2(float cx2) {
        this.cx2 = cx2;
    }

    public float getCy2() {
        return cy2;
    }

    public void setCy2(float cy2) {
        this.cy2 = cy2;
    }

    public float getCz2() {
        return cz2;
    }

    public void setCz2(float cz2) {
        this.cz2 = cz2;
    }

    public float getR() {
        return r;
    }

    public void setR(float r) {
        this.r = r;
    }      
    }

