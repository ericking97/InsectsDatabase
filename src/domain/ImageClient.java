package domain;

import java.util.ArrayList;

public interface ImageClient {
  public Image create(ImageDTO dto);

  public ArrayList<Image> list();
}
