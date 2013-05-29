/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sanger.modelo.transporte.automovel;

import br.com.sanger.modelo.IEntidade;
import br.com.sanger.modelo.transporte.Automovel;
import br.com.sanger.modelo.transporte.Transporte;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

/**
 *
 * @author eugenio
 */
@Entity
public class TransporteAutomovel extends Transporte implements IEntidade<Long>, Serializable {


    @OneToOne
    private Automovel automovel;

    public Automovel getAutomovel() {
        return automovel;
    }

    public void setAutomovel( Automovel automovel ) {
        this.automovel = automovel;
    }

}
