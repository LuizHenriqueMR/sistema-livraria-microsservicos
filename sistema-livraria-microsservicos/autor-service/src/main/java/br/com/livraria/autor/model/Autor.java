package br.com.livraria.autor.model;
import jakarta.persistence.*;
@Entity @Table(name="tb_autor") public class Autor {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false,length=150) private String nome;
 @Column(length=80) private String nacionalidade;
 public Autor(){} public Autor(String nome,String nacionalidade){this.nome=nome;this.nacionalidade=nacionalidade;}
 public Long getId(){return id;} public void setId(Long id){this.id=id;} public String getNome(){return nome;} public void setNome(String nome){this.nome=nome;} public String getNacionalidade(){return nacionalidade;} public void setNacionalidade(String nacionalidade){this.nacionalidade=nacionalidade;}
}
