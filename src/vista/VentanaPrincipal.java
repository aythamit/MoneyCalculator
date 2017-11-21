
package vista;

import javax.swing.JFrame;
import modelo.CurrencyList;

public class VentanaPrincipal extends JFrame {
    private final int WIDTH = 500;
    private final int HEIGTH = 100;
    private final String TITULO = "Money Calculator";
    PanelMoney panel;

    public PanelMoney getPanel() {
        return panel;
    }
    public VentanaPrincipal(CurrencyList currencyList){
        iniciarVentana();
        panel = new PanelMoney(currencyList);
        add(panel);
        
        
        setVisible(true);
    }

    private void iniciarVentana() {
            setSize(WIDTH,HEIGTH);
            setTitle(TITULO);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setLocationRelativeTo(null);
            
    
    }
}
