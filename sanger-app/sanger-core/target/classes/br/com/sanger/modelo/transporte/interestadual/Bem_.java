package br.com.sanger.modelo.transporte.interestadual;

import br.com.sanger.modelo.transporte.apoio.Localizacao;
import br.com.sanger.modelo.transporte.apoio.Simbolo;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2013-05-28T13:20:49")
@StaticMetamodel(Bem.class)
public class Bem_ { 

    public static volatile SingularAttribute<Bem, Long> id;
    public static volatile SingularAttribute<Bem, Double> quantidade;
    public static volatile SingularAttribute<Bem, Double> seguro;
    public static volatile SingularAttribute<Bem, Simbolo> simbolo;
    public static volatile SingularAttribute<Bem, String> descricao;
    public static volatile SingularAttribute<Bem, Localizacao> localizacao;

}