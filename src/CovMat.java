/*
 * Bank: REC::CovMat 
 * info: Reconstructed track covariance matrix
 * Noraim Nunez (CSUDH), June 22, 2018 
 */
public class CovMat {
 
    //same as particle trajectory 
    private int index; 
    private int pindex;
    //
       
    //covariance matrix element at last superlayer used in the fit
    private float C11;
    private float C12;
    private float C13;  
    private float C14;  
    private float C15;  
    private float C22;  
    private float C23;
    private float C24;  
    private float C25;  
    private float C33;  
    private float C34;
    private float C35;
    private float C44;
    private float C45;
    private float C55; 

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getPindex() {
        return pindex;
    }

    public void setPindex(int pindex) {
        this.pindex = pindex;
    }

    public float getC11() {
        return C11;
    }

    public void setC11(float C11) {
        this.C11 = C11;
    }

    public float getC12() {
        return C12;
    }

    public void setC12(float C12) {
        this.C12 = C12;
    }

    public float getC13() {
        return C13;
    }

    public void setC13(float C13) {
        this.C13 = C13;
    }

    public float getC14() {
        return C14;
    }

    public void setC14(float C14) {
        this.C14 = C14;
    }

    public float getC15() {
        return C15;
    }

    public void setC15(float C15) {
        this.C15 = C15;
    }

    public float getC22() {
        return C22;
    }

    public void setC22(float C22) {
        this.C22 = C22;
    }

    public float getC23() {
        return C23;
    }

    public void setC23(float C23) {
        this.C23 = C23;
    }

    public float getC24() {
        return C24;
    }

    public void setC24(float C24) {
        this.C24 = C24;
    }

    public float getC25() {
        return C25;
    }

    public void setC25(float C25) {
        this.C25 = C25;
    }

    public float getC33() {
        return C33;
    }

    public void setC33(float C33) {
        this.C33 = C33;
    }

    public float getC34() {
        return C34;
    }

    public void setC34(float C34) {
        this.C34 = C34;
    }

    public float getC35() {
        return C35;
    }

    public void setC35(float C35) {
        this.C35 = C35;
    }

    public float getC44() {
        return C44;
    }

    public void setC44(float C44) {
        this.C44 = C44;
    }

    public float getC45() {
        return C45;
    }

    public void setC45(float C45) {
        this.C45 = C45;
    }

    public float getC55() {
        return C55;
    }

    public void setC55(float C55) {
        this.C55 = C55;
    }
}

    