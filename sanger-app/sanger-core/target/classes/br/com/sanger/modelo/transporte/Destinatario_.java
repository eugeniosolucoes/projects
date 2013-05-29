package br.com.sanger.modelo.transporte;

import br.com.sanger.modelo.apoio.Endereco;
import br.com.sanger.modelo.transporte.local.TransporteLocal;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2013-05-28T13:20:49")
@StaticMetamodel(Destinatario.class)
public class Destinatario_ { 

    public static volatile SingularAttribute<Destinatario, Long> id;
    public static volatile SingularAttribute<Destinatario, String> telefoneResidencial;
    public static volatile SingularAttribute<Destinatario, String> telefoneComercial;
    public static volatile ListAttribute<Destinatario, TransporteLocal> servicos;
    public static volatile SingularAttribute<Destinatario, String> nome;
    public static volatile SingularAttribute<Destinatario, String> telefoneMovel;
    public static volatile SingularAttribute<Destinatario, Endereco> endereco;

}