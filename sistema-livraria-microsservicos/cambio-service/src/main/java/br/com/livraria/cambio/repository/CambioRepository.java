package br.com.livraria.cambio.repository;
import br.com.livraria.cambio.model.Cambio;
import org.springframework.data.jpa.repository.JpaRepository;
public interface CambioRepository extends JpaRepository<Cambio,Long> { Cambio findByFromAndTo(String from, String to); }
