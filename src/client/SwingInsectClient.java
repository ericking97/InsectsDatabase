package client;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTextField;

import domain.Category;
import domain.CategoryDTO;
import domain.Insect;
import domain.InsectDTO;
import domain.InsectClient;
import domain.Image;
import domain.ImageDTO;

import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;

import java.util.ArrayList;

class SwingInsectClient extends RESTModule<Insect, InsectDTO> implements InsectClient {

  SwingInsectClient() {
    super("Insecto");
  }

  protected void loadCreateView() {
    ArrayList<Category> categories = new ArrayList<Category>();
    ArrayList<Image> images = new ArrayList<Image>();

    JLabel commonLabel = new JLabel("Nombre común");
    commonLabel.setBounds(10, 20, 80, 25);
    CreatePanel.add(commonLabel);

    JTextField commonText = new JTextField();
    commonText.setBounds(100, 20, 165, 25);
    CreatePanel.add(commonText);

    JLabel scientificLabel = new JLabel("Nombre cientifico");
    scientificLabel.setBounds(10, 50, 80, 25);
    CreatePanel.add(scientificLabel);

    JTextField scientificText = new JTextField();
    scientificText.setBounds(100, 50, 165, 25);
    CreatePanel.add(scientificText);

    JLabel locationLabel = new JLabel("Estados");
    locationLabel.setBounds(10, 80, 80, 25);
    CreatePanel.add(locationLabel);

    JTextField locationText = new JTextField();
    locationText.setBounds(100, 80, 165, 25);
    CreatePanel.add(locationText);

    // Category submodule
    JLabel categoryLabel = new JLabel("Categoria");
    categoryLabel.setBounds(10, 110, 80, 25);
    CreatePanel.add(categoryLabel);

    JTextField categoryText = new JTextField();
    categoryText.setBounds(100, 110, 165, 25);
    CreatePanel.add(categoryText);

    JButton addCategory = new JButton("Añadir");
    addCategory.setBounds(280, 110, 80, 25);
    addCategory.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String name = categoryText.getText();
        CategoryDTO dto = new CategoryDTO(name);
        categories.add(dto.create());
        categoryText.setText("");
      }
    });
    CreatePanel.add(addCategory);

    // Image submodule
    JLabel descriptionLabel = new JLabel("Descripción");
    descriptionLabel.setBounds(10, 140, 80, 25);
    CreatePanel.add(descriptionLabel);

    JTextField descriptionText = new JTextField();
    descriptionText.setBounds(100, 140, 165, 25);
    CreatePanel.add(descriptionText);

    JTextField pathText = new JTextField();

    JButton chooseFile = new JButton("Cargar imagen");
    chooseFile.setBounds(10, 170, 165, 25);
    chooseFile.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        pathText.setText("");

        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File file = chooser.getSelectedFile();
        String filename = file.getAbsolutePath();

        pathText.setText(filename);
      }
    });
    CreatePanel.add(chooseFile);

    JButton addImage = new JButton("Añadir");
    addImage.setBounds(180, 170, 80, 25);
    addImage.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String description = descriptionText.getText();
        String path = pathText.getText();

        ImageDTO dto = new ImageDTO(description, path);
        images.add(dto.create());

        descriptionText.setText("");
        pathText.setText("");
      }
    });
    CreatePanel.add(addImage);

    JButton submit = new JButton("Crear");
    submit.setBounds(10, 200, 80, 25);
    submit.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String name = commonText.getText();
        String scientific = scientificText.getText();
        String location = locationText.getText();

        InsectDTO dto = new InsectDTO(name, scientific, location);
        dto.categories = new ArrayList<Category>(categories);
        dto.images = new ArrayList<Image>(images);

        commonText.setText("");
        scientificText.setText("");
        locationText.setText("");

        categories.clear();
        images.clear();

        items.add(create(dto));
      }
    });
    CreatePanel.add(submit);
  }

  @Override
  protected void loadListView() {
    ArrayList<Insect> insects = list();

    int i = 0;
    for (Insect insect : insects) {
      int vertical = 10 + (i * 200);
      JLabel info = new JLabel(String.format("%s", insect));
      info.setBounds(10, vertical, 500, 100);
      ListPanel.add(info);

      try {
        int j = 0;
        for (Image image : insect.images) {
          JLabel description = new JLabel(String.format("%s", image.description));
          description.setBounds(10 + (j * 200), vertical, 200, 200);
          ImageIcon img = new ImageIcon(SwingImageClient.scaleImage(200, 200, image.buffer));
          description.setIcon(img);
          ListPanel.add(description);
          j++;
        }
      } catch (Exception err) {
        System.out.println("Cannot show image");
      }

      i++;
    }

    if (i == 0) {
      JLabel label = new JLabel("No se han encontrado registros");
      label.setBounds(10, 10, 500, 25);
      ListPanel.add(label);
    }
  }

  public Insect create(InsectDTO dto) {
    return dto.create();
  }

  public ArrayList<Insect> list() {
    return items;
  }
}