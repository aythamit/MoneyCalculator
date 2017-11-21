/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.HashMap;
import java.util.Map;


public class CurrencyList {
    private Map<String, Currency> currencies;

    public Map<String, Currency> getCurrencies() {
        return currencies;
    }
    
    public CurrencyList(){
        currencies = new HashMap<>();
        currencies.put("USD", new Currency("USD","Dolar americano","$"));
        currencies.put("EUR", new Currency("EUR","Euro","€"));
        currencies.put("JPY", new Currency("JPY","Yen japonés","¥"));
        currencies.put("GBP", new Currency("GBP","Libra Esterlina","£"));  
        currencies.put("CHF", new Currency("CHF","Franco suizo","Fr"));  
        currencies.put("AUD", new Currency("AUD","Dólar australiano","A$"));  
        currencies.put("CAD", new Currency("CAD","Dólar canadiense","C$"));  
        currencies.put("HKD", new Currency("HKD","Dólar de Hong Kong","HK$"));  
        currencies.put("NOK", new Currency("NOK","Corona Noruega","kr"));   

    }
}
