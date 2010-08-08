package org.haldean.simplegraph;

import java.util.List;

/**
 * A class to create graphs of various types.
 *
 * @author Will Brown (will.h.brown@gmail.com)
 */
public class GraphFactory {
  /**
   * Get a graph for streaming data that uses the default
   * configuration.
   */
  public static <E extends Number> StreamingGraphComponent<E> forStreamingData() {
    return forStreamingData(new GraphConfiguration());
  }

  /**
   * Get a graph for streaming data with arbitrary, inferred
   * precision.
   *
   * @param config The configuration to use.
   */
  public static <E extends Number> StreamingGraphComponent<E> forStreamingData(GraphConfiguration config) {
    return new StreamingGraphComponent<E>(config);
  }

  /**
   * Get a graph to represent a list of numbers with the default
   * configuration.
   *
   * @param values The values to graph.
   */
  public static <E extends Number> StaticGraphComponent<E> forList(List<E> values) {
    return forList(values, new GraphConfiguration());
  }

  /**
   * Get a graph to represent a list of numbers.
   *
   * @param values The values to graph.
   * @param config The configuration to use.
   */
  public static <E extends Number> StaticGraphComponent<E> forList(List<E> values, GraphConfiguration config) {
    StaticGraphComponent<E> graph = new StaticGraphComponent<E>(config);
    graph.addValueList(values);
    return graph;
  }
}