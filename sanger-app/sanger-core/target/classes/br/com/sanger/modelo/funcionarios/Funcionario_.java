package br.com.sanger.modelo.funcionarios;

import br.com.sanger.modelo.Pessoa_;
import br.com.sanger.modelo.apoio.Endereco;
import br.com.sanger.modelo.transporte.local.TransporteLocal;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2013-05-29T15:26:32")
@StaticMetamodel(Funcionario.class)
public abstract class Funcionario_ extends Pessoa_ {

    public static volatile ListAttribute<Funcionario, TransporteLocal> servicos;
    public static volatile SingularAttribute<Funcionario, Endereco> endereco;
    public static volatile SingularAttribute<Funcionario, String> identidade;

}