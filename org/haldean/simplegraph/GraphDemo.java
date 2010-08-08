package org.haldean.simplegraph;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.LinkedList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class GraphDemo {
  /**
   * Test code: draw an amplifying sine wave. Run this. It's mesmerizing.
   *
   * @param args All command line arguments are ignored.
   */
  public static void main(String args[]) {
    JFrame f = new JFrame();

    GraphConfiguration staticConfig = new GraphConfiguration("Amplifying Sine: Static");
    staticConfig.setTickDistance(45);
    final StaticGraphComponent<Double> staticGraph = GraphFactory.forList(getValues(), staticConfig);
    staticGraph.setPreferredSize(new Dimension(700, 400));

    GraphConfiguration streamingConfig = new GraphConfiguration(staticConfig);
    streamingConfig.setLabelValue("Amplifying Sine: Streaming");
    final StreamingGraphComponent<Double> streamingGraph = GraphFactory.forStreamingData(streamingConfig);
    streamingGraph.setSampleCount(720);

    JPanel panel = new JPanel(new GridLayout(2,1));
    panel.add(staticGraph);
    panel.add(streamingGraph);

    f.add(panel);
    f.pack();
    f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    f.setVisible(true);

    streamToGraph(streamingGraph);
  }

  private static LinkedList<Double> getValues() {
    LinkedList<Double> list = new LinkedList<Double>();
    double j=1.1;
    for (int i=0; i<=360 * 10; i++) {
      j *= 1.001;
      list.add((double) (j * Math.sin(Math.toRadians(i))));
    }

    return list;
  }

  private static void streamToGraph(StreamingGraphComponent<Double> streamingGraph) {
    new GraphStreamThread(streamingGraph).start();
  }

  private static class GraphStreamThread extends Thread {
    private final StreamingGraphComponent<Double> graph;

    public GraphStreamThread(StreamingGraphComponent<Double> streamingGraph) {
      graph = streamingGraph;
    }

    public void run() {
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
}