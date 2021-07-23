package client;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.*;

interface Module {
  /**
   * Creates the components and interface to interact with the module
   *
   * @param menubar
   */
  public void loadModule(JMenuBar menubar, Client cli);
}

public class Client extends JFrame {

  public Client() {
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setLayout(new BorderLayout());
  }

  private void loadContent(Client cli, ArrayList<Module> modules) {
    JMenuBar menubar = new JMenuBar();

    for (Module module : modules) {
      module.loadModule(menubar, cli);
    }

    setJMenuBar(menubar);
  }

  public static Client init(int x, int y, int width, int height) {
    ArrayList<Module> modules = new ArrayList<Module>() {
      {
        add(new SwingInsectClient());
        add(new SwingImageClient());
        add(new SwingCategoryClient());
      }
    };

    Client client = new Client();
    client.loadContent(client, modules);

    client.setBounds(x, y, width, height);
    client.setVisible(true);

    return client;
  }

  public void changePanel(JPanel panel) {
    getContentPane().removeAll();
    getContentPane().add(panel, BorderLayout.CENTER);
    getContentPane().doLayout();
    update(getGraphics());
  }
}
