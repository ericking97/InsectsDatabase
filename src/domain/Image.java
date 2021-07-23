package domain;

import java.awt.image.BufferedImage;

/**
 * Image contains a small description allowside with a buffer to the image
 */
public class Image {
  public String description;
  public BufferedImage buffer;

  Image(ImageDTO dto, BufferedImage buffer) {
    this.description = dto.description;
    this.buffer = buffer;
  }

}