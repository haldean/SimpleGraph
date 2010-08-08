package org.haldean.simplegraph;

import java.awt.Color;
import java.awt.Font;

/**
 * A data object that contains configuration parameters
 * for {@link GraphComponent}
 *
 * @author Will Brown (will.h.brown@gmail.com)
 */
public class GraphConfiguration {
  private Color backgroundColor = Color.DARK_GRAY;
  private Color borderColor = Color.DARK_GRAY;
  private Color lineColor = Color.WHITE;
  private Color axesColor = Color.LIGHT_GRAY;
  private Color inspectorColor = Color.RED;

  private Font labelFont = new Font(Font.SANS_SERIF, Font.PLAIN, 10);
  private String labelValue = "";

  private int tickDistance = 50;
  private int tickSkip = 4;
  private boolean enableTickLabels = true;

  private boolean enableInspector = true;

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
	inspectorColor = gc.inspectorColor;
	labelFont = gc.labelFont;
	labelValue = gc.labelValue;
	tickDistance = gc.tickDistance;
	tickSkip = gc.tickSkip;
	enableTickLabels = gc.enableTickLabels;
	enableInspector = gc.enableInspector;
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
   * Set the inspector color of the graph.
   */
  public void setInspectorColor(Color bg) {
	inspectorColor = bg;
  }

  /**
   * Get the inspector color of the graph.
   */
  public Color getInspectorColor() {
	return inspectorColor;
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

  /**
   * Set the number of ticks to skip between ticks with labels. For
   * example, setting this to two means there will be a label on every
   * third tick, or setting it to zero puts a label on every tick.
   */
  public void setTickSkip(int skip) {
	tickSkip = skip;
  }

  /*
   * Get the number of ticks to skip between labels
   */
  public int getTickSkip() {
	return tickSkip;
  }

  boolean isTickLabelLocation(double value) {
	return getEnableTickLabels() &&
	  value % (tickDistance * tickSkip) == 0;
  }

  /**
   * Set to true to show the inspector.
   */
  public void setEnableInspector(boolean enable) {
	enableInspector = enable;
  }

  /**
   * Returns true if the inspector is shown.
   */
  public boolean getEnableInspector() {
	return enableInspector;
  }
}