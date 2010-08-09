package org.haldean.swing;

import org.haldean.simplegraph.GraphFactory;
import org.haldean.simplegraph.StaticGraphComponent;

import java.awt.Dimension;
import java.io.IOException;
import java.util.LinkedList;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ComponentImagerTest {
  public static void main(String args[]) {
    LinkedList<Double> values = new LinkedList<Double>();
    for (int i=0; i<360; i++) {
      values.add(Math.sin(Math.toRadians(i)));
    }

    JFrame frame = new JFrame();
    JPanel panel = new JPanel();
    StaticGraphComponent<Double> graph = GraphFactory.forList(values);
    graph.setPreferredSize(new Dimension(720, 480));
    panel.add(graph);
    frame.add(panel);

    frame.pack();
    frame.setVisible(true);

    try {
      ComponentImager.componentToImageFile(panel, "test.png");
    } catch (IOException e) {
      System.err.println("Could not write to file");
      e.printStackTrace();
    }
  }
}