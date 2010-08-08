package org.haldean.simplegraph;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class GraphDemo {
  /**
   * Test code: draw an amplifying sine wave. Run this. It's mesmerizing.
   *
   * @param args All command line arguments are ignored.
   */
  public static void main(String args[]) {
    JFrame f = new JFrame();

    GraphConfiguration gc = new GraphConfiguration("Amplifying Sine");
    gc.setTickDistance(45);

    final GraphComponent<Double> graph = new GraphComponent<Double>(gc);
    graph.setPreferredSize(new Dimension(700, 400));
    graph.setSampleCount(720);

    f.add(graph);
    f.pack();
    f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    f.setVisible(true);
	
    try {
      double j=1.1;
      for (int i=0; i<=360; i++) {
	j *= 1.001;
	graph.addValue((double) (j * Math.sin(Math.toRadians(i))));
	Thread.sleep(10);
	if (i==360)
	  i = 0;
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}