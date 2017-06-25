
public class Vector {
	
	private double hVal;
    private double vVal;
    public static final double LENGTH = 1;
    
    public Vector(double angle) {
        setAngle(angle);
    }
    
    public double getH() {
        return hVal;
    }
    
    public double getV() {
        return vVal;
    }
    
    public void setAngle(double angle) {
        double angleRad = angle*Math.PI/180;
        hVal = LENGTH * Math.sin(angleRad);
        vVal = (-1)*LENGTH * Math.cos(angleRad);
    }
}
