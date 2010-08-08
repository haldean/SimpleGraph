package org.haldean.simplegraph;

import java.awt.Color;
import java.awt.Font;

public class GraphConfiguration {
  private Color backgroundColor = new Color(28, 25, 20);
  private Color borderColor = Color.DARK_GRAY;
  private Color lineColor = Color.WHITE;
  private Color axesColor = Color.LIGHT_GRAY;

  private Font labelFont = new Font(Font.SANS_SERIF, Font.PLAIN, 10);
  private String labelValue = "";

  private int tickDistance = 50;
  private boolean enableTickLabels = true;

  /**
   * Create a {@link GraphConfiguration} with the default colors
   * and no label
   */
  public GraphConfiguration() {
	;
  }

  /**
   * Create a {@link GraphConfiguration} with a label
   *
   * @param label The label for the graph
   */
  public GraphConfiguration(String label) {
	labelValue = label;
  }

  /**
   * Create a {@link GraphConfiguration} by doing a shallow copy on
   * a different {@link GraphConfiguration}
   *
   * @param gc The {@link GraphConfiguration} to copy.
   */
  public GraphConfiguration(GraphConfiguration gc) {
	backgroundColor = gc.backgroundColor;
	borderColor = gc.borderColor;
	lineColor = gc.lineColor;
	axesColor = gc.axesColor;
	labelFont = gc.labelFont;
	labelValue = gc.labelValue;
	tickDistance = gc.tickDistance;
  }

  /**
   * Set the background color of the graph.
   */
  public void setBackgroundColor(Color bg) {
	backgroundColor = bg;
  }

  /**
   * Get the background color of the graph.
   */
  public Color getBackgroundColor() {
	return backgroundColor;
  }

  /**
   * Set the color of the graph's border. These will be
   * drawn on the four outside edges of the graph.
   */
  public void setBorderColor(Color border) {
	borderColor = border;
  }

  /**
   * Get the color of the graph's border.
   */
  public Color getBorderColor() {
	return borderColor;
  }

  /**
   * Set the color of the data series's line.
   */
  public void setLineColor(Color line) {
	lineColor = line;
  }

  /**
   * Get the color of the data series's line.
   */
  public Color getLineColor() {
	return lineColor;
  }

  /**
   * Set the color of the graph's axes.
   */
  public void setAxisColor(Color axis) {
	axesColor = axis;
  }

  /**
   * Get the color of the graph's axes.
   */
  public Color getAxisColor() {
	return axesColor;
  }

  /**
   * Set the font used to draw the label.
   */
  public void setLabelFont(Font font) {
	labelFont = font;
  }

  /**
   * Get the font used to draw the label.
   */
  public Font getLabelFont() {
	return labelFont;
  }

  /**
   * Set the text of the label.
   */
  public void setLabelValue(String text) {
	labelValue = text;
  }

  /**
   * Get the text of the label.
   */
  public String getLabelValue() {
	return labelValue;
  }

  /**
   * Set the number of units between ticks. Set to zero to disable
   * ticks altogether.
   */
  public void setTickDistance(int distance) {
	tickDistance = distance;
  }

  /**
   * Get the number of units between ticks
   */
  public int getTickDistance() {
	return tickDistance;
  }

  /**
   * Set to true to show labels under ticks
   */
  public void setEnableTickLabels(boolean enable) {
	enableTickLabels = enable;
  }

  /**
   * Returns true if labels are shown under ticks
   */
  public boolean getEnableTickLabels() {
	return enableTickLabels;
  }
}