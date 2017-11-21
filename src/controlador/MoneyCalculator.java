
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Currency;
import modelo.CurrencyList;
import modelo.ExchangeRate;
import modelo.Money;
import vista.VentanaPrincipal;

public class MoneyCalculator {
    DecimalFormat dc = new DecimalFormat("0.00");
    CurrencyList currencyList;
    VentanaPrincipal ventana;
    ExchangeRate exchangeRate;
    Money moneyFrom;
    Money moneyTo;
    public MoneyCalculator() {
        currencyList = new CurrencyList();
        ventana = new VentanaPrincipal(currencyList);
       
        ventana.getPanel().getBtnCalcular().addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                input();
                process();
                output();
            }

            private void input() {
                double amount;
                try  
                    {  
                        amount = Double.parseDouble(ventana.getPanel().getInMoneda().getText()); 
                        
                        String keyFrom = ventana.getPanel().getcomboFrom().getSelectedItem().toString();
                        moneyFrom = new Money(amount, currencyList.getCurrencies().get(keyFrom));
                        
                    }  
                    catch(NumberFormatException nfe)  
                    {  
                        System.out.println("Debe pasar un numero.");
                    }
                
                
            }

            private void process() {
                        String keyFrom = ventana.getPanel().getcomboFrom().getSelectedItem().toString();
                        String keyTo = ventana.getPanel().getcomboTo().getSelectedItem().toString();
                     try {
                            exchangeRate=getExchangeRate( currencyList.getCurrencies().get(keyFrom),
                                    currencyList.getCurrencies().get(keyTo));
                        } catch (Exception ex) {
                            Logger.getLogger(MoneyCalculator.class.getName()).log(Level.SEVERE, null, ex);
                    }
                     double amountF = moneyFrom.getAmount()* exchangeRate.getRate();
                     moneyTo = new Money(amountF,currencyList.getCurrencies().get(keyTo));
            }

            private void output() {
               ventana.getPanel().getResultado().setText(dc.format(moneyTo.getAmount()));
            }
          });
    }

     private static ExchangeRate getExchangeRate(Currency from, Currency to) throws Exception{
         if(from.equals(to)){
             
             return new ExchangeRate(from, to, new Date(), 1.0);
         }
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
