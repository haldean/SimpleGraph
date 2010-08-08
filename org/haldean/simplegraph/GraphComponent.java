package org.haldean.simplegraph;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.LinkedList;
import javax.swing.JComponent;

/**
 *  A class which draws a graph based on a series of given result sets.
 *  The graph automatically scales along the Y axis and can be manually
 *  scaled along the X axis.
 *  @author William Brown
 */
public class GraphComponent<E extends Number> extends Component {
	private LinkedList<E> series;
	private double maximum = 1;
	private double minimum = -1;

	private int width;
	private int height;

	/* Area left at the top and bottom to ensure the
	 * graph never quite touches the edge */
	private double margin = 1;

	/* The number of data points visible on the graph */
	private int sampleCount = 100;

	/* The index of the last added sample */
	private int lastSampleIndex = 0;

	/* The configuration object that holds the color, font and name of
	 * the graph */
	private GraphConfiguration config;

	/**
	 * Create a new {@link GraphComponent} with the default
	 * {@link GraphConfiguration}.
	 */
	public GraphComponent() {
		this(new GraphConfiguration());
	}

	/**
	 * Create a new {@link GraphComponent} with the specified
	 * {@link GraphConfiguration}.
	 *
	 * @param The {@link GraphConfiguration} to use.
	 */
	public GraphComponent(GraphConfiguration gc) {
		series = new LinkedList<E>();
		config = gc;
		addComponentListener(new ComponentAdapter() {
				public void componentResized(ComponentEvent e) {
					Dimension size = getSize();
					width = (int) size.getWidth();
					height = (int) size.getHeight();
				}
			});
	}

	/**
	 * Manually set the maximum. Note that if a data point larger than this
	 * maximum is added, the graph will automatically scale. This sets a lower
	 * bound on the actual maximum of the graph.
	 *
	 * @param max The lower bound for the maximum
	 */
	public void setMaximum(double max) {
		maximum = max;
		repaint();
	}

	/**
	 * Manually set the minimum. Note that if a data point smaller than this
	 * minimum is added, the graph will automatically scale. This sets an upper
	 * bound on the actual minimum of the graph.
	 *
	 * @param min The upper bound for the minimum
	 */
	public void setMinimum(double min) {
		minimum = min;
		repaint();
	}

	/**
	 * Set the {@link GraphConfiguration} of the graph.
	 *
	 * @param config The configuration to assign.
	 */
	public void setGraphConfiguration(GraphConfiguration gc) {
		config = gc;
	}

	/**
	 * Get the {@link GraphConfiguration} of this component.
	 */
	public GraphConfiguration getGraphConfiguration() {
		return config;
	}

	/**
	 * Set the scale along the X axis.
	 *
	 * @param sampleCount The number of samples shown along the
	 * axis. The graph will automatically adjust to show the most
	 * recent sampleCount samples.
	 */
	public void setSampleCount(int newSampleCount) {
		sampleCount = newSampleCount;
		checkSeriesSize();
		repaint();
	}

	/**
	 * Add a value to the data series.
	 *
	 * @param p The value to add
	 */
	public void addValue(E value) {
		/* Create a lock on the series list */
		synchronized (series) {
			series.add(value);
		}

		lastSampleIndex++;
		checkSeriesSize();

		double doubleValue = value.doubleValue();
		/* Adjust the bounds if necessary */
		if (doubleValue > (maximum - margin))
			maximum = doubleValue + margin;
		if (doubleValue < (minimum + margin))
			minimum = doubleValue - margin;

		repaint();
	}

	/**
	 * Trims the data if we have more points then necessary
	 */
	private void checkSeriesSize() {
		synchronized (series) {
			while (series.size() > sampleCount) {
				series.removeFirst();
			}
		}
	}		

	/**
	 * Convert a value to a canvas pixel location.
	 *
	 * @param p The sample value
	 * @return The number of pixels between the top of the graph and the
	 * horizontal line representing the given sample value
	 */
	private int pointToY(E p) {
		return (int) (((maximum - p.doubleValue()) / (maximum - minimum)) * getSize().getHeight());
	}

	/**
	 * Get the pixel location of the x-axis
	 *
	 * @return Pixels between the top of the graph and the X-axis
	 */
	private int zeroToY() {
		return (int) ((maximum / (maximum - minimum)) * getSize().getHeight());
	}

	/**
	 * Convert a time-index to a canvas pixel location
	 *
	 * @param x The time index
	 * @return The pixel X corresponding to that time index
	 */
	private int pointToX(int x) {
		return (int) (((float) x / (float) sampleCount) * getSize().getWidth());
	}

	/**
	 * Paints the graph onto the provided graphics object
	 *
	 * @param g The graphics object to paint onto
	 */
	public void paint(Graphics canvas) {
		/* The Y component of the X axis can shift based on scaling, so
		 * we calculate it once to save computation */
		int y0 = zeroToY();

		/* Background */
		canvas.setColor(config.getBackgroundColor());
		canvas.fillRect(0, 0, width, height);

		/* Border */
		canvas.setColor(config.getBorderColor());
		canvas.drawRect(0, 0, width, height);

		/* Horizontal axis */
		canvas.setColor(config.getAxisColor());
		canvas.drawLine(0, y0, width, y0);

		if (config.getTickDistance() != 0) {
			int firstDisplayedIndex = Math.max(0, lastSampleIndex - sampleCount);
			int tickLocation = config.getTickDistance() - 
				(firstDisplayedIndex % config.getTickDistance());

			while (tickLocation < width) {
				int tickPixel = pointToX(tickLocation);
				canvas.drawLine(tickPixel, y0, tickPixel, y0 + 2);
				tickLocation += config.getTickDistance();
			}
		}

		/* Graph label */
		canvas.setFont(config.getLabelFont());
		canvas.drawString(config.getLabelValue(), 1, y0 - 2);

		canvas.setColor(config.getLineColor());

		/* Create a lock on the series list so that the series
		 * cannot be updated while we are drawing */
		synchronized (series) {
			if (series.size() > 0) {
				int lastY = pointToY(series.get(0));
				int lastX = pointToX(0);

				/* Loop through the points in X, connecting them as we go. */
				for (int i = 0; i < series.size(); i++) {
					int y = pointToY(series.get(i));
					int x = pointToX(i);

					canvas.drawLine(lastX, lastY, x, y);

					lastY = y;
					lastX = x;
				}
			}
		}
	}
}