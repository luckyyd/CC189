import java.util.ArrayList;
import java.util.Set;

public class main {
	Line findBestLine(GraphPoint[] points){
		HashMapList<Double, Line> lineBySlope = getListOfLines(points);
		return getBestLine(lineBySlope);
	}

	private Line getBestLine(HashMapList<Double, Line> linesBySlope) {
		Line bestLine = null;
		int bestCount = 0;
		Set<Double> slopes = linesBySlope.keySet();
		for (double slope : slopes){
			ArrayList<Line> lines = linesBySlope.get(slope);
			for(Line line : lines){
				int count = countEquivalentLines(linesBySlope, line);
				if(count > bestCount){
					bestLine = line;
					bestCount = count;
					bestLine.Print();
					System.out.println(bestCount);
				}
			}
		}
		return bestLine;
	}

	private int countEquivalentLines(HashMapList<Double, Line> linesBySlope, Line line) {
		double key = Line.floorToNearestEpsilon(line.slope);
		int count = countEquivalentLines(linesBySlope.get(key), line);
		count += countEquivalentLines(linesBySlope.get(key - Line.epsilon), line);
		count += countEquivalentLines(linesBySlope.get(key + Line.epsilon), line);
		return count;
	}

	private int countEquivalentLines(ArrayList<Line> lines, Line line) {
		if(lines == null){
			return 0;
		}
		int count = 0;
		for(Line parallelLine : lines){
			if(parallelLine.isEquivalent(line)){
				count++;
			}
		}
		return count;
	}

	private HashMapList<Double, Line> getListOfLines(GraphPoint[] points) {
		HashMapList<Double, Line> linesBySlope = new HashMapList<Double, Line>();
		for(int i = 0; i < points.length; i++){
			for(int j = i + 1; j < points.length; j++){
				Line line = new Line(points[i], points[j]);
				double key = Line.floorToNearestEpsilon(line.slope);
				linesBySlope.put(key, line);
			}
		}
		return linesBySlope;
	}
	
	
	
}
