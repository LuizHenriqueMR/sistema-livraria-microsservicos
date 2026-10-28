package br.com.livraria.book.model;
import jakarta.persistence.*; import com.fasterxml.jackson.annotation.JsonIgnoreProperties; import java.time.LocalDate;
@Entity @Table(name="tb_book") @JsonIgnoreProperties({"hibernateLazyInitializer","handler"}) public class Book {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false,length=180) private String author;
 @Column(nullable=false,length=250) private String title;
 @Column(name="launch_date",nullable=false) private LocalDate launchDate;
 @Column(nullable=false) private Double price;
 @ManyToOne(fetch=FetchType.EAGER,optional=false) @JoinColumn(name="categoria_id",nullable=false) private Categoria categoria;
 @Transient private String currency; @Transient private String environment;
 public Book(){} public Long getId(){return id;} public void setId(Long id){this.id=id;} public String getAuthor(){return author;} public void setAuthor(String v){author=v;} public String getTitle(){return title;} public void setTitle(String v){title=v;} public LocalDate getLaunchDate(){return launchDate;} public void setLaunchDate(LocalDate v){launchDate=v;} public Double getPrice(){return price;} public void setPrice(Double v){price=v;} public Categoria getCategoria(){return categoria;} public void setCategoria(Categoria v){categoria=v;} public String getCurrency(){return currency;} public void setCurrency(String v){currency=v;} public String getEnvironment(){return environment;} public void setEnvironment(String v){environment=v;}
}
