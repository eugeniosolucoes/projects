package br.com.sanger.modelo;

import br.com.sanger.modelo.apoio.Endereco;
import br.com.sanger.modelo.transporte.local.TransporteLocal;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2013-05-28T13:20:49")
@StaticMetamodel(Cliente.class)
public abstract class Cliente_ extends Pessoa_ {

    public static volatile ListAttribute<Cliente, TransporteLocal> servicos;
    public static volatile SingularAttribute<Cliente, Endereco> endereco;

}