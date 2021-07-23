package client;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;

import java.util.ArrayList;

import domain.Image;
import domain.ImageClient;
import domain.ImageDTO;

class SwingImageClient extends RESTModule<Image, ImageDTO> implements ImageClient {

  SwingImageClient() {
    super("Imagen");
  }

  protected void loadCreateView() {
    JLabel descriptionLabel = new JLabel("Descripción");
    descriptionLabel.setBounds(10, 20, 80, 25);
    CreatePanel.add(descriptionLabel);

    JTextField descriptionText = new JTextField();
    descriptionText.setBounds(100, 20, 165, 25);
    CreatePanel.add(descriptionText);

    JTextField locationText = new JTextField();

    JButton chooseFile = new JButton("Cargar imagen");
    chooseFile.setBounds(10, 60, 165, 25);
    chooseFile.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        locationText.setText("");

        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File file = chooser.getSelectedFile();
        String filename = file.getAbsolutePath();

        locationText.setText(filename);
      }
    });
    CreatePanel.add(chooseFile);

    JButton submit = new JButton("Crear");
    submit.setBounds(10, 90, 80, 25);
    submit.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String description = descriptionText.getText();
        String location = locationText.getText();

        ImageDTO dto = new ImageDTO(description, location);

        descriptionText.setText("");
        locationText.setText("");
        items.add(create(dto));
      }
    });
    CreatePanel.add(submit);
  }

  @Override
  protected void loadListView() {
    ArrayList<Image> images = list();

    int i = 0;
    for (Image image : images) {
      JLabel label = new JLabel(String.format("%s", image.description));
      try {
        ImageIcon img = new ImageIcon(scaleImage(200, 200, image.buffer));
        label.setIcon(img);
      } catch (Exception err) {
        System.out.println("Cannot show image");
      }
      label.setBounds(10, 10 + (i * 10), 500, 25);
      ListPanel.add(label);
      i++;
    }

    if (i == 0) {
      JLabel label = new JLabel("No se han encontrado registros");
      label.setBounds(10, 10, 500, 25);
      ListPanel.add(label);
    }
  }

  public static BufferedImage scaleImage(int w, int h, BufferedImage img) throws Exception {
    BufferedImage bi;
    bi = new BufferedImage(w, h, BufferedImage.TRANSLUCENT);
    Graphics2D g2d = (Graphics2D) bi.createGraphics();
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
    g2d.drawImage(img, 0, 0, w, h, null);
    g2d.dispose();
    return bi;
  }

  @Override
  public Image create(ImageDTO dto) {
    return dto.create();
  }

  @Override
  public ArrayList<Image> list() {
    return items;
  }
}
