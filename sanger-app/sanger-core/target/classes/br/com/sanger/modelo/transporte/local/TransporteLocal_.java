package br.com.sanger.modelo.transporte.local;

import br.com.sanger.modelo.funcionarios.Autonomo;
import br.com.sanger.modelo.funcionarios.Motorista;
import br.com.sanger.modelo.transporte.Transporte_;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2013-05-28T13:20:49")
@StaticMetamodel(TransporteLocal.class)
public class TransporteLocal_ extends Transporte_ {

    public static volatile SingularAttribute<TransporteLocal, Motorista> motorista;
    public static volatile SingularAttribute<TransporteLocal, Autonomo> inventariante;
    public static volatile ListAttribute<TransporteLocal, Autonomo> ajudantes;

}