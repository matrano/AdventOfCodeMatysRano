package graphicDesign;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.CubicCurve2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Line2D.Double;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JPanel;

public class Areas extends JPanel {
	public int mouseX;
	public int mouseY;
	public int collision = 0;
	GeneralPath.Double generalPath = new GeneralPath.Double(1, 5);
	public double vectorAngle;
	public HashMap<String, Double> dictShape = new HashMap<String, Double>();
	public int size = 3; // the limit is 3
	public double circleWH = 30;

	/**
	 * Create the panel.
	 */
	public Areas() {
		setBackground(Color.CYAN);
		
//		initializeDictShape();
//		System.out.println(dictShape.size());
		
		repaint();
	}

	/**
	 * Permet de dessiner une scene qui inclut ici une simple balle en mouvement
	 * @param g Contexte graphique
	 */
	@Override
	public void paintComponent(Graphics g) {		
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;	
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		
		CubicCurve2D.Double cubicCurve = 
				new CubicCurve2D.Double(0,0,20,20, 40, 20, 60, 60);
		AffineTransform mat = new AffineTransform();
		g2d.setColor( Color.green );
		mat.translate(30,40);
		mat.rotate(0.3,0 ,0);
		mat.scale( 3.0, 3.5 );
		g2d.draw(  mat.createTransformedShape(cubicCurve)  );
		
		Rectangle2D.Double rect1 = 
				new Rectangle2D.Double(0, 0, 100, 20);
		mat = new AffineTransform();
		g2d.setColor( Color.gray );
		mat.translate(60,200);
		mat.scale( 3.0, 3.5 );
		g2d.draw(  mat.createTransformedShape(rect1)  );
		
		int posX = 120;
		int posY = 150;
		Line2D.Double vector = new Line2D.Double(posX, posY, mouseX, mouseY);
//		vectorAngle = Math.atan2(mouseX - posX, mouseY - posY);
		vectorAngle = Math.atan2(mouseX - posX, mouseY - posY) + ((3 * Math.PI) / 2);
		System.out.println("First Angle: " + vectorAngle);
		mat = new AffineTransform();
		g2d.setColor( Color.black );
		g2d.draw(  mat.createTransformedShape(vector)  );
		
		mat = new AffineTransform();
		generalPath.moveTo(0, 50);
		generalPath.lineTo(0, 100);
		generalPath.curveTo(33, 50, 66, 150, 100, 100);
		generalPath.curveTo(150, 20, 166, 150, 200, 100);
		generalPath.lineTo(200, 50);
		generalPath.closePath();
		g2d.setColor( Color.red );
		Point2D.Double mousePosition = new Point2D.Double(mouseX, mouseY);
		if (generalPath.contains(mousePosition)) {
			g2d.setColor( Color.blue );
			collision = 1;
			collectingMapInfo(mouseX, mouseY);
		} 
		g2d.fill(  mat.createTransformedShape(generalPath)  );
		Area generalPathArea = new Area(generalPath);
		
		Ellipse2D.Double circle = new Ellipse2D.Double(mouseX - (circleWH / 2), mouseY - (circleWH / 2), circleWH, circleWH);
		Area circleArea = new Area(circle);
		mat = new AffineTransform();
		g2d.setColor( Color.white );
		g2d.draw(  mat.createTransformedShape(circle)  );
		
		circleArea.intersect(generalPathArea);
		if (! circleArea.isEmpty()) {
			g2d.setColor(Color.pink);
			g2d.fill(circleArea);
			
			// ------------------------------------------------------------------------------------------------------
			Area area = circleArea; // The value is set elsewhere in the code    
			ArrayList<double[]> areaPoints = new ArrayList<double[]>();
			ArrayList<Line2D.Double> areaSegments = new ArrayList<Line2D.Double>();
			ArrayList<Line2D.Double> tempAreaSegments = new ArrayList<Line2D.Double>();
			double[] coords = new double[6];

			for (PathIterator pi = area.getPathIterator(null); !pi.isDone(); pi.next()) {
			    // The type will be SEG_LINETO, SEG_MOVETO, or SEG_CLOSE
			    // Because the Area is composed of straight lines
			    int type = pi.currentSegment(coords);
			    // We record a double array of {segment type, x coord, y coord}
			    double[] pathIteratorCoords = {type, coords[0], coords[1]};
			    areaPoints.add(pathIteratorCoords);
			}

			double[] start = new double[3]; // To record where each polygon starts

			for (int i = 0; i < areaPoints.size(); i++) {
			    // If we're not on the last point, return a line from this point to the next
			    double[] currentElement = areaPoints.get(i);

			    // We need a default value in case we've reached the end of the ArrayList
			    double[] nextElement = {-1, -1, -1};
			    if (i < areaPoints.size() - 1) {
			        nextElement = areaPoints.get(i + 1);
			    }

			    // Make the lines
			    if (currentElement[0] == PathIterator.SEG_MOVETO) {
			        start = currentElement; // Record where the polygon started to close it later
			    } 

			    if (nextElement[0] == PathIterator.SEG_LINETO) {
			        areaSegments.add(
			                new Line2D.Double(
			                    currentElement[1], currentElement[2],
			                    nextElement[1], nextElement[2]
			                )
			            );
			    } else if (nextElement[0] == PathIterator.SEG_CLOSE) {
			        areaSegments.add(
			                new Line2D.Double(
			                    currentElement[1], currentElement[2],
			                    start[1], start[2]
			                )
			            );
			    }
			}
			
			// Add segments between segments to connect them all
			Line2D.Double previousSegment = areaSegments.get(areaSegments.size() - 1);
			for (Line2D.Double segment : areaSegments) {
				tempAreaSegments.add( new Line2D.Double(previousSegment.getX2(), previousSegment.getY2(), segment.getX1(), segment.getY1()));
				previousSegment = segment;
			}
			areaSegments.addAll(tempAreaSegments);

			// areaSegments now contains all the line segments
			// ------------------------------------------------------------------------------------------------------
			Point2D.Double insidePoint = new Point2D.Double(mouseX, mouseY);
			Point2D.Double closestPoint = new Point2D.Double(-1, -1);
		    Point2D.Double bestPoint = new Point2D.Double(-1, -1);
		    Line2D.Double bestLine = new Line2D.Double();
		    ArrayList<Point2D.Double> closestPointList = new ArrayList<Point2D.Double>();
			
			// Calculate the nearest point on the edge
	        for (Line2D.Double line : areaSegments) {

	            // From: https://stackoverflow.com/questions/6176227
	        	double u = 
	                    ((insidePoint.getX() - line.x1) * (line.x2 - line.x1) + (insidePoint.getY() - line.y1) * (line.y2 - line.y1))
	                  / ((line.x2 - line.x1) * (line.x2 - line.x1) + (line.y2 - line.y1) * (line.y2 - line.y1));

	            double xu = line.x1 + u * (line.x2 - line.x1);
	            double yu = line.y1 + u * (line.y2 - line.y1);

	            if (u < 0) {
	                closestPoint.setLocation(line.getP1());
	            } else if (u > 1) {
	                closestPoint.setLocation(line.getP2());
	            } else {
	                closestPoint.setLocation(xu, yu);
	            }

	            closestPointList.add((Point2D.Double) closestPoint.clone());

	            if (closestPoint.distance(insidePoint) < bestPoint.distance(insidePoint)) {
	                bestPoint.setLocation(closestPoint);
	                bestLine = line;
	            }
	        }
	        int bestPointCircleWH = 5;
	        Ellipse2D.Double bestPointCircle = new Ellipse2D.Double(bestPoint.getX() - (bestPointCircleWH / 2), bestPoint.getY() - (bestPointCircleWH / 2), bestPointCircleWH, bestPointCircleWH);
			mat = new AffineTransform();
			g2d.setColor( Color.gray );
			g2d.draw(  mat.createTransformedShape(bestPointCircle)  );
			
			g2d.setColor(Color.black);
			for (Line2D.Double segment : areaSegments) {
				g2d.draw(segment);
			}
			
			g2d.setColor(Color.cyan);
			g2d.draw(bestLine);
//			double bestLineAngle = Math.atan2(bestLine.getY2() - bestLine.getY1(), bestLine.getX2() - bestLine.getX1());
			double bestLineAngle = Math.atan2(bestLine.getY2() - bestLine.getY1(), bestLine.getX2() - bestLine.getX1()) + ((3 * Math.PI) / 2);
			System.out.println("Second Angle: " + bestLineAngle);
			double afterCollisionAngle = (2 * Math.PI) -  (2 * bestLineAngle) - vectorAngle;
			int radius = 50;
			
			Line2D.Double afterCollisionVector = new Line2D.Double(mouseX, mouseY, mouseX + (radius * Math.cos(afterCollisionAngle)), mouseY + (radius * Math.sin(afterCollisionAngle)));
			mat = new AffineTransform();
			g2d.setColor( Color.black );
			g2d.draw(  mat.createTransformedShape(afterCollisionVector)  );
			System.out.println("Final Angle: " + afterCollisionAngle);
		}
		
		collision = 0;
	}//fin paintComponent
	
	public void collectingMapInfo(int PosX, int PosY) {
		int limit = (int) Math.floor(size/2);
		for (int adjustmentX = -limit; adjustmentX <= limit; adjustmentX++) {
			for (int adjustmentY = -limit; adjustmentY <= limit; adjustmentY++) {
				Point2D.Double collisionPoint = new Point2D.Double(PosX + adjustmentX, PosY + adjustmentY);
				if (generalPath.contains(collisionPoint)) {
					System.out.print("X ");
				} else {
					System.out.print("O ");
				}
			}
			System.out.println();
		}
		System.out.println("####################################");
	}
	
	public void initializeDictShape() {
		String binaryString;
		for (int index = 0; index < Math.pow(2, Math.pow(size, 2)); index++) { // for 3^2 bit there are 2^3^2 numbers
			binaryString = Integer.toBinaryString(index);
			dictShape.put(binaryString, null);
		}	
	}
}
