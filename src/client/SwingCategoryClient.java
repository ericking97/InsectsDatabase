package client;

import domain.Category;
import domain.CategoryDTO;
import domain.CategoryClient;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.util.ArrayList;

class SwingCategoryClient extends RESTModule<Category, CategoryDTO> implements CategoryClient {

  SwingCategoryClient() {
    super("Categoria");
  }

  protected void loadCreateView() {
    JLabel nameLabel = new JLabel("Nombre");
    nameLabel.setBounds(10, 20, 80, 25);
    CreatePanel.add(nameLabel);

    JTextField nameText = new JTextField();
    nameText.setBounds(100, 20, 165, 25);
    CreatePanel.add(nameText);

    JButton submit = new JButton("Crear");
    submit.setBounds(10, 60, 80, 25);
    submit.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String name = nameText.getText();

        CategoryDTO dto = new CategoryDTO(name);

        nameText.setText("");
        items.add(create(dto));
      }
    });
    CreatePanel.add(submit);
  }

  @Override
  public Category create(CategoryDTO dto) {
    return dto.create();
  }

  @Override
  public ArrayList<Category> list() {
    return items;
  }
}
