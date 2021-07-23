package domain;

public class Category {
  private String name;

  public String toString() {
    return String.format("%s", name);
  }

  Category(CategoryDTO dto) {
    this.name = dto.name;
  }
}