package br.com.sanger.modelo.seguranca;

import br.com.sanger.modelo.seguranca.Direito;
import br.com.sanger.modelo.seguranca.Usuario;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2013-05-29T15:26:32")
@StaticMetamodel(Perfil.class)
public class Perfil_ { 

    public static volatile SingularAttribute<Perfil, Integer> id;
    public static volatile SingularAttribute<Perfil, String> descricao;
    public static volatile ListAttribute<Perfil, Direito> direitos;
    public static volatile ListAttribute<Perfil, Usuario> usuarios;

}