package domain;

import java.util.ArrayList;

/**
 * Insect contains information about an insect
 */
public class Insect {
  private String commonName;
  private String scientificName;
  private String location;

  private ArrayList<Category> categories;
  public ArrayList<Image> images;

  Insect(InsectDTO dto) {
    this.commonName = dto.name;
    this.scientificName = dto.scientific;
    this.location = dto.location;
    this.categories = dto.categories;
    this.images = dto.images;
  }

  public String toString() {
    String categoryString = "";
    for (Category category : this.categories) {
      categoryString += String.format("%s,", category.toString());
    }

    return String.format("Nombre: %s %n Científico: %s %n Estados: %s %n Categorias: %s", commonName, scientificName,
        location, categoryString);
  }
}
