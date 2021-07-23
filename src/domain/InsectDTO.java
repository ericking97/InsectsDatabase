package domain;

import java.util.ArrayList;

public class InsectDTO {
  public String name, scientific, location;

  public ArrayList<Category> categories;
  public ArrayList<Image> images;

  public InsectDTO(String name, String scientific, String location) {
    this.name = name;
    this.scientific = scientific;
    this.location = location;
  }

  public Insect create() {
    return new Insect(this);
  }

  public void addCategory(CategoryDTO dto) {
    categories.add(dto.create());
  }

  public void addImage(ImageDTO dto) {
    images.add(dto.create());
  }
}
