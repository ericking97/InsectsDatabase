package domain;

public class CategoryDTO {
  String name;

  public CategoryDTO(String name) {
    this.name = name;
  }

  public Category create() {
    return new Category(this);
  }
}
