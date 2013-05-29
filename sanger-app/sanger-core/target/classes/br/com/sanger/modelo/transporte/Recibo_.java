package br.com.sanger.modelo.transporte;

import br.com.sanger.modelo.Cliente;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2013-05-29T18:14:12")
@StaticMetamodel(Recibo.class)
public class Recibo_ { 

    public static volatile SingularAttribute<Recibo, Long> id;
    public static volatile SingularAttribute<Recibo, String> formaDePagamento;
    public static volatile SingularAttribute<Recibo, String> referente;
    public static volatile SingularAttribute<Recibo, Cliente> cliente;
    public static volatile SingularAttribute<Recibo, Double> quantia;
    public static volatile SingularAttribute<Recibo, String> quantiaPorExtenso;
    public static volatile SingularAttribute<Recibo, Date> emissao;

}