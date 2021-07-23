package client;

import javax.swing.JPanel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.util.ArrayList;

abstract class RESTModule<T, dto> implements Module {
  private String moduleName;
  protected ArrayList<T> items = new ArrayList<T>();

  protected JPanel CreatePanel = new JPanel();
  protected JPanel ListPanel = new JPanel();

  RESTModule(String name) {
    moduleName = name;

    loadCreateView();
    loadListView();
  }

  public void loadModule(JMenuBar menubar, Client cli) {
    JMenu menu = new JMenu(moduleName);

    JMenuItem create = new JMenuItem(String.format("Crear %s", moduleName));
    create.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        cli.changePanel(CreatePanel);
      }
    });

    JMenuItem list = new JMenuItem(String.format("Listar %ss", moduleName));
    list.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        ListPanel.removeAll();
        loadListView();
        cli.changePanel(ListPanel);
      }
    });

    menu.add(create);
    menu.add(list);

    menubar.add(menu);
  }

  protected void loadListView() {
    ArrayList<T> items = list();

    int i = 0;
    for (T item : items) {
      JLabel label = new JLabel(String.format("%s", item));
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

  protected abstract void loadCreateView();

  public abstract T create(dto obj);

  public abstract ArrayList<T> list();
}
