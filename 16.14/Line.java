
public class Line {
	public static double epsilon = 0.0001;
	public double slope, intercept;
	private boolean infinite_slope = false;
	
	public Line(GraphPoint p, GraphPoint q){
		if(Math.abs(p.x - q.x) > epsilon){
			slope = (q.y - p.y) / (q.x - p.x);
			intercept = p.y - slope * p.x;
		}
		else{
			infinite_slope = true;
			intercept = p.x;
		}
	}
	public static double floorToNearestEpsilon(double d){
		int r = (int)(d / epsilon);
		return ((double) r) * epsilon;
	}
	
	public boolean isEquivalent(double a, double b){
		return (Math.abs(a - b) < epsilon);
	}
	
	public boolean isEquivalent(Object o){
		Line l = (Line) o;
		if(isEquivalent(l.slope, slope) && isEquivalent(l.intercept, intercept) && (infinite_slope == l.infinite_slope)){
			return true;
		}
		else{
			return false;
		}
	}
	
	public void Print() {
		System.out.println(epsilon + intercept);
	}
}

