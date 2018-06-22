
public class DetectorHit {
	private int index; // #16 Index of the hit in the specific detector bank
	private int pindex; // #16 Row number in the particle bank hit is associated with
	private int detector;// #8 Detector ID,defined in COATJAVA DetectorType
	private int sector; // #8 Sector of the Detector hit
	private int layer; // #8 Layer of the Detector hit
	private float time; // Time associated with the hit (ns)
	private float path; // Path from vertex to the hit position (cm)
	private float chi2; // Quality of hit-track matching
	private float x; // X coordinate of the hit (cm)
	private float y; // Y coordinate of the hit (cm)
	private float z; // Z coordinate of the hit (cm)
    private int status; //Hit status 

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
	public int getDetector() {
		return detector;
	}
	public void setDetector(int detector) {
		this.detector = detector;
	}
	public int getSector() {
		return sector;
	}
	public void setSector(int sector) {
		this.sector = sector;
	}
	public int getLayer() {
		return layer;
	}
	public void setLayer(int layer) {
		this.layer = layer;
	}
	
	public float getTime() {
		return time;
	}
	public void setTime(float time) {
		this.time = time;
	}
	public float getPath() {
		return path;
	}
	public void setPath(float path) {
		this.path = path;
	}
	public float getChi2() {
		return chi2;
	}
	public void setChi2(float chi2) {
		this.chi2 = chi2;
	}
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
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
}
