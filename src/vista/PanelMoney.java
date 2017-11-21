
package vista;

import java.awt.Color;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import modelo.Currency;
import modelo.CurrencyList;

public class PanelMoney extends JPanel {
    
    private JTextField inMoneda;
    private JLabel  lblInMoneda;
    private JComboBox comboFrom;
    private JLabel  lblToMoneda;
    private JComboBox comboTo;
    private JButton btnCalcular;
    private JLabel  lblResultado;
    private JTextField resultado;
    public PanelMoney(CurrencyList currencyList){
        inicializarComponentes(currencyList);   
        aniadirComponentes();
    }
    private void inicializarComponentes(CurrencyList currencyList){
        btnCalcular = new JButton("Calcular");
        lblInMoneda = new JLabel("Cantidad: ");
        lblToMoneda = new JLabel("pasarlo a ");
        lblResultado = new JLabel("Resultado: ");
        inMoneda = new JTextField(10);
        resultado = new JTextField(10);
        comboFrom = new JComboBox();
        comboTo = new JComboBox();
        
        iniciarCombo(comboFrom,currencyList);
        iniciarCombo(comboTo,currencyList);
    }

    public JTextField getInMoneda() {
        return inMoneda;
    }

    public JLabel getLblInMoneda() {
        return lblInMoneda;
    }

    public JLabel getLblToMoneda() {
        return lblToMoneda;
    }

    public JLabel getLblResultado() {
        return lblResultado;
    }

    public JTextField getResultado() {
        return resultado;
    }

    public JComboBox getcomboFrom() {
        return comboFrom;
    }

    public JComboBox getcomboTo() {
        return comboTo;
    }

    public JButton getBtnCalcular() {
        return btnCalcular;
    }
    private void iniciarCombo(JComboBox combo, CurrencyList currencyList){
        
        for (Map.Entry<String, Currency> entry : currencyList.getCurrencies().entrySet()) {
            combo.addItem(entry.getKey());
        }
    }

    private void aniadirComponentes() {
        add(lblInMoneda);
        add(inMoneda);
        add(comboFrom);
        add(lblToMoneda);
        add(comboTo);
        add(btnCalcular);
        add(lblResultado);
        add(resultado);
    }   
}
