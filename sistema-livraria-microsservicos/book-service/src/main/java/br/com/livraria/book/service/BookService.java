package br.com.livraria.book.service;
import br.com.livraria.book.model.Book; import br.com.livraria.book.model.Categoria; import br.com.livraria.book.repository.BookRepository; import br.com.livraria.book.repository.CategoriaRepository; import br.com.livraria.book.exception.RecursoNaoEncontradoException;
import org.springframework.beans.factory.annotation.Value; import org.springframework.stereotype.Service; import org.springframework.web.client.RestTemplate; import org.springframework.web.client.RestClientException; import java.util.*;
@Service public class BookService {
 private final BookRepository books; private final CategoriaRepository categorias; private final RestTemplate restTemplate;
 @Value("${cambio.service.url}") private String cambioUrl;
 public BookService(BookRepository books,CategoriaRepository categorias,RestTemplate restTemplate){this.books=books;this.categorias=categorias;this.restTemplate=restTemplate;}
 public List<Book> listar(){return books.findAll();} public Book buscar(Long id){return books.findById(id).orElseThrow(()->new RecursoNaoEncontradoException("Livro não encontrado: "+id));}
 public Book salvar(Book book,Long categoriaId){Categoria categoria=categorias.findById(categoriaId).orElseThrow(()->new RecursoNaoEncontradoException("Categoria não encontrada: "+categoriaId));book.setCategoria(categoria);return books.save(book);}
 public Book atualizar(Long id,Book dados,Long categoriaId){Book book=buscar(id);Categoria categoria=categorias.findById(categoriaId).orElseThrow(()->new RecursoNaoEncontradoException("Categoria não encontrada: "+categoriaId));book.setAuthor(dados.getAuthor());book.setTitle(dados.getTitle());book.setLaunchDate(dados.getLaunchDate());book.setPrice(dados.getPrice());book.setCategoria(categoria);return books.save(book);}
 public void excluir(Long id){books.delete(buscar(id));}
 public Book buscarConvertido(Long id,String currency){Book book=buscar(id);try{String url=cambioUrl+"/cambio-service/{amount}/{from}/{to}"; CambioResposta cambio=restTemplate.getForObject(url,CambioResposta.class,book.getPrice(),"USD",currency.toUpperCase()); if(cambio==null||cambio.convertedValue==null) throw new RecursoNaoEncontradoException("Resposta inválida do serviço de câmbio");book.setPrice(cambio.convertedValue);book.setCurrency(currency.toUpperCase());return book;}catch(RestClientException ex){throw new RecursoNaoEncontradoException("Serviço de câmbio indisponível ou moeda não suportada");}}
 public static class CambioResposta { public Double convertedValue; public String from; public String to; public Double conversionFactor; public String environment; public CambioResposta(){} }
}
