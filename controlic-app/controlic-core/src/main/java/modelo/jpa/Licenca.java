/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.jpa;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author eugenio
 */
@Entity
@Table( uniqueConstraints =
        @UniqueConstraint( columnNames = { "militar_id", "data_licenca" } ) )
public class Licenca implements Serializable {

    private static final long serialVersionUID = -8270133587654183760L;

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    @Temporal( javax.persistence.TemporalType.DATE )
    @Column( name = "data_licenca" )
    private Date dataLicenca;

    private String motivo;

    private TipoLicenca tipo;

    @Column( name = "publicada_pd" )
    private boolean publicadaPD;

    @ManyToOne
    @JoinColumn( nullable = false )
    private Militar militar;

    public Licenca() {
        this.tipo = TipoLicenca.INTEGRAL;
        this.dataLicenca = new Date();
        this.publicadaPD = true;
    }

    public Licenca( String motivo ) {
        this();
        this.motivo = motivo;
    }

    public Licenca( Date dataLicenca, String motivo, TipoLicenca tipo,
            boolean publicadaPD, Militar militar ) {
        this.dataLicenca = dataLicenca;
        this.motivo = motivo;
        this.tipo = tipo;
        this.publicadaPD = publicadaPD;
        this.militar = militar;
    }

    public Date getDataLicenca() {
        return dataLicenca;
    }

    public void setDataLicenca( Date dataLicenca ) {
        this.dataLicenca = dataLicenca;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo( String motivo ) {
        this.motivo = motivo;
    }

    public TipoLicenca getTipo() {
        return tipo;
    }

    public void setTipo( TipoLicenca tipo ) {
        this.tipo = tipo;
    }

    public boolean isPublicadaPD() {
        return publicadaPD;
    }

    public void setPublicadaPD( boolean publicadaPD ) {
        this.publicadaPD = publicadaPD;
    }

    public Militar getMilitar() {
        return militar;
    }

    public void setMilitar( Militar militar ) {
        this.militar = militar;
    }

    public static enum TipoLicenca {

        INTEGRAL,
        PRIMEIRO_TEMPO,
        SEGUNDO_TEMPO;

        public String getDescricao() {
            return this.equals( PRIMEIRO_TEMPO ) ? "1o TEMPO"
                    : this.equals( SEGUNDO_TEMPO ) ? "2o TEMPO"
                    : INTEGRAL.name();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId( Long id ) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + ( this.id != null ? this.id.hashCode() : 0 );
        return hash;
    }

    @Override
    public boolean equals( Object obj ) {
        if ( obj == null ) {
            return false;
        }
        if ( getClass() != obj.getClass() ) {
            return false;
        }
        final Licenca other = (Licenca) obj;
        if ( this.id != other.id && ( this.id == null || !this.id.equals( other.id ) ) ) {
            return false;
        }
        return true;
    }
}
