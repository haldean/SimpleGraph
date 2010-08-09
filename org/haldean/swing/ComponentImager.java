package org.haldean.swing;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JComponent;

public class ComponentImager {
  public static BufferedImage componentToImage(Component component) {
    Dimension componentSize = component.getSize();
    BufferedImage image = new BufferedImage((int) componentSize.getWidth(),
                                            (int) componentSize.getHeight(),
                                            BufferedImage.TYPE_INT_RGB);
    component.paint(image.createGraphics());
    return image;
  }

  public static void componentToImageFile(Component component, String filePath) throws IOException {
    BufferedImage componentImage = componentToImage(component);
    ImageIO.write(componentImage, "png", new File(filePath));
  }
}