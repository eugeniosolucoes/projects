package br.com.sanger.modelo.funcionarios;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2013-05-28T13:20:49")
@StaticMetamodel(Autonomo.class)
public class Autonomo_ extends Funcionario_ {

    public static volatile SingularAttribute<Autonomo, Date> demissao;
    public static volatile SingularAttribute<Autonomo, Date> admissao;
    public static volatile SingularAttribute<Autonomo, String> cpf;
    public static volatile SingularAttribute<Autonomo, String> descricao;

}