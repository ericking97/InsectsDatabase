package domain;

import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import errors.UserError;

public class ImageDTO {
  String description, location;

  public ImageDTO(String description, String location) {
    this.description = description;
    this.location = location;
  }

  public Image create() {
    BufferedImage buffer;

    try {
      buffer = load(location);
    } catch (IOException e) {
      throw new UserError("Invalid location");
    }

    return new Image(this, buffer);
  }

  private static BufferedImage load(String location) throws IOException {
    File file = new File(location);
    BufferedImage buffer = ImageIO.read(file);

    return buffer;
  }
}
