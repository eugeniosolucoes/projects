package br.com.sanger.modelo.transporte;

import br.com.sanger.modelo.Cliente;
import br.com.sanger.modelo.transporte.Destinatario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2013-05-29T18:14:12")
@StaticMetamodel(Transporte.class)
public abstract class Transporte_ { 

    public static volatile SingularAttribute<Transporte, Long> id;
    public static volatile SingularAttribute<Transporte, Date> realizado;
    public static volatile SingularAttribute<Transporte, Cliente> cliente;
    public static volatile SingularAttribute<Transporte, Destinatario> destinatario;

}