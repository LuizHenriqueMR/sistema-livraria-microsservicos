package br.com.livraria.cambio.service;
import java.math.BigDecimal;
import java.math.RoundingMode;
public class ConversorCurrency {
 public static Double convertToCurrency(double value,double factor){ return BigDecimal.valueOf(value*factor).setScale(2,RoundingMode.HALF_UP).doubleValue(); }
}
