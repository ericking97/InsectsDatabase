package domain;

import java.util.ArrayList;

public interface CategoryClient {
  public Category create(CategoryDTO dto);

  public ArrayList<Category> list();
}
