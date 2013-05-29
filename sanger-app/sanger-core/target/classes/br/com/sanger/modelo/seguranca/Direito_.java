package br.com.sanger.modelo.seguranca;

import br.com.sanger.modelo.seguranca.Perfil;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2013-05-29T18:14:12")
@StaticMetamodel(Direito.class)
public class Direito_ { 

    public static volatile SingularAttribute<Direito, Integer> id;
    public static volatile ListAttribute<Direito, Perfil> perfis;
    public static volatile SingularAttribute<Direito, String> descricao;

}