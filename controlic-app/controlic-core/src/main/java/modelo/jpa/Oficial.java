/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.jpa;

import java.io.Serializable;
import javax.persistence.Entity;

/**
 *
 * @author cpo-202
 */
@Entity
public class Oficial extends Militar implements Serializable {

    private static final long serialVersionUID = -6309898631662719233L;

    private Oficial.Posto posto;

    protected Oficial() {
        super();
        posto = Oficial.Posto.SEGUNDO_TENENTE;
    }

    public Oficial( String nip ) {
        this.nip = nip;
    }

    public Oficial( Oficial.Posto posto, String nip, String nome, String nomeDeGuerra ) {
        this.posto = posto;
        this.nip = nip;
        this.nome = nome;
        this.nomeDeGuerra = nomeDeGuerra;
    }

    public Oficial.Posto getPosto() {
        return posto;
    }

    public void setPosto( Oficial.Posto posto ) {
        this.posto = posto;
    }

    @Override
    public String getLoginNome() {
        return String.format( "%s %s", this.posto.getSigla(), this.nomeDeGuerra );
    }

    public static enum Posto {

        ALMIRANTE_ESQUADRA,
        VICE_ALMIRANTE,
        CONTRA_ALMIRANTE,
        CAPITAO_MAR_GUERRA,
        CAPITAO_FRAGATA,
        CAPITAO_CORVETA,
        CAPITAO_TENENTE,
        PRIMEIRO_TENENTE,
        SEGUNDO_TENENTE;

        private static final int INDEX_SIGLA = 0;

        private static final int INDEX_DESCRICAO = 1;

        private static final String[][] postos = {
            { "AE", "Almirante-de-Esquadra" },
            { "VA", "Vice-Almirante" },
            { "CA", "Contra-Almirante" },
            { "CMG", "Capitão-de-Mar-e-Guerra" },
            { "CF", "Capitão-de-Fragata" },
            { "CC", "Capitão-de-Corveta" },
            { "CT", "Capitão-Tenente" },
            { "1T", "Primeiro-Tenente" },
            { "2T", "Segundo-Tenente" }
        };

        public String getDescricao() {
            return postos[this.ordinal()][INDEX_DESCRICAO];
        }

        public String getSigla() {
            return postos[this.ordinal()][INDEX_SIGLA];
        }
    }
}
