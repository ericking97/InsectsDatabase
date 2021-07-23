package domain;

import java.util.ArrayList;

public interface InsectClient {
  public Insect create(InsectDTO dto);

  public ArrayList<Insect> list();

}
