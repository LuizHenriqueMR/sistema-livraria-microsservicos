package br.com.livraria.cambio.model;
import jakarta.persistence.*;
import java.io.Serializable;
@Entity
@Table(name="tb_cambio", uniqueConstraints=@UniqueConstraint(columnNames={"from_currency","to_currency"}))
public class Cambio implements Serializable {
 private static final long serialVersionUID=1L;
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(name="from_currency",nullable=false,length=3) private String from;
 @Column(name="to_currency",nullable=false,length=3) private String to;
 @Column(name="conversion_factor",nullable=false) private Double conversionFactor;
 @Transient private Double convertedValue;
 @Transient private String environment;
 public Cambio() {}
 public Long getId(){return id;} public void setId(Long id){this.id=id;}
 public String getFrom(){return from;} public void setFrom(String from){this.from=from;}
 public String getTo(){return to;} public void setTo(String to){this.to=to;}
 public Double getConversionFactor(){return conversionFactor;} public void setConversionFactor(Double v){this.conversionFactor=v;}
 public Double getConvertedValue(){return convertedValue;} public void setConvertedValue(Double v){this.convertedValue=v;}
 public String getEnvironment(){return environment;} public void setEnvironment(String v){this.environment=v;}
}
