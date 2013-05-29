package br.com.sanger.modelo.transporte;

import br.com.sanger.modelo.transporte.interestadual.Item;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2013-05-28T13:20:49")
@StaticMetamodel(Nota.class)
public class Nota_ { 

    public static volatile SingularAttribute<Nota, Long> id;
    public static volatile SingularAttribute<Nota, Double> imposto;
    public static volatile SingularAttribute<Nota, Double> aliquota;
    public static volatile SingularAttribute<Nota, Date> emissao;
    public static volatile ListAttribute<Nota, Item> itens;
    public static volatile SingularAttribute<Nota, String> numero;

}