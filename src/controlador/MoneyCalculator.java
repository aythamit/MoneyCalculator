
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Currency;
import modelo.CurrencyList;
import modelo.ExchangeRate;
import vista.VentanaPrincipal;

public class MoneyCalculator {
    CurrencyList currencyList;
    VentanaPrincipal ventana;
    ExchangeRate ext;
    public MoneyCalculator() {
        currencyList = new CurrencyList();
        ventana = new VentanaPrincipal(currencyList);
       
        ventana.getPanel().getBtnCalcular().addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                String keyFrom = ventana.getPanel().getcomboFrom().getSelectedItem().toString();
                String keyTo = ventana.getPanel().getcomboFrom().getSelectedItem().toString();
                try {
                    ext=getExchangeRate( currencyList.getCurrencies().get(keyFrom),
                            currencyList.getCurrencies().get(keyTo));
                } catch (Exception ex) {
                    Logger.getLogger(MoneyCalculator.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
            }
          });
    }

     private static ExchangeRate getExchangeRate(Currency from, Currency to) throws Exception{
      URL url = new URL("http://api.fixer.io/latest?base=" + from.getCode() + "&symbols=" + to.getCode());
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
      connection.setRequestMethod("GET");
      InputStreamReader input = new InputStreamReader(connection.getInputStream());
      try (BufferedReader reader = new BufferedReader(input)){
        String line = reader.readLine();
        line = line.substring(line.indexOf(to.getCode())+5, line.indexOf("}"));
        return new ExchangeRate(from, to, new Date(), Double.parseDouble(line));
      }
    }
    public static void main(String[] args) {
        MoneyCalculator MCalc = new MoneyCalculator();
    }
    
}
