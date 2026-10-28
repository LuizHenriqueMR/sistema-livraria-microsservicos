package br.com.livraria.cambio.controller;
import br.com.livraria.cambio.model.Cambio; import br.com.livraria.cambio.repository.CambioRepository; import br.com.livraria.cambio.service.ConversorCurrency; import br.com.livraria.cambio.exception.RecursoNaoEncontradoException;
import org.springframework.core.env.Environment; import org.springframework.web.bind.annotation.*; import java.util.List;
@RestController @RequestMapping("cambio-service")
public class CambioController {
 private final CambioRepository repository; private final Environment environment;
 public CambioController(CambioRepository repository,Environment environment){this.repository=repository;this.environment=environment;}
 @GetMapping("/{amount}/{from}/{to}") public Cambio getCambio(@PathVariable Double amount,@PathVariable String from,@PathVariable String to){
  Cambio cambio=repository.findByFromAndTo(from.toUpperCase(),to.toUpperCase()); if(cambio==null) throw new RecursoNaoEncontradoException("Moeda não suportada: "+from+" para "+to);
  cambio.setConvertedValue(ConversorCurrency.convertToCurrency(amount,cambio.getConversionFactor())); cambio.setEnvironment(environment.getProperty("local.server.port")); return cambio;
 }
 @GetMapping public List<Cambio> listar(){return repository.findAll();}
}
