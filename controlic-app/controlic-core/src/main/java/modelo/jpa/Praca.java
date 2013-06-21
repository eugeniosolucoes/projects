/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 *
 * @author eugenio
 */
@Entity
public class Praca extends Militar {

    private static final long serialVersionUID = 4751250794426339313L;

    @Column( name = "grad" )
    private Graduacao graduacao;

    protected Praca() {
        super();
        graduacao = Graduacao.SUBOFICIAL;
    }

    public Praca( String nip ) {
        this.nip = nip;
    }

    public Praca( Graduacao graduacao, String nip, String nome, String nomeDeGuerra ) {
        this.graduacao = graduacao;
        this.nip = nip;
        this.nome = nome;
        this.nomeDeGuerra = nomeDeGuerra;
    }

    public Graduacao getGraduacao() {
        return graduacao;
    }

    public void setGraduacao( Graduacao graduacao ) {
        this.graduacao = graduacao;
    }

    @Override
    public String getLoginNome() {
        return String.format( "%s %s", this.graduacao.getSigla(), this.nomeDeGuerra );
    }

    
    
    public static enum Graduacao {

        SUBOFICIAL,
        PRIMEIRO_SARGENTO,
        SEGUNDO_SARGENTO,
        TERCEIRO_SARGENTO,
        CABO,
        MARINHEIRO;
        
        private static final int INDEX_SIGLA = 0;
        private static final int INDEX_DESCRICAO = 0;

        private static final String[][] graduacoes = {
            { "SO", "Suboficial" },
            { "1SG", "Primeiro-Sargento" },
            { "2SG", "Segundo-Sargento" },
            { "3SG", "Terceiro-Sargento" },
            { "CB", "Cabo" },
            { "MN", "Manheiro" }
        };

        public String getDescricao() {
            return graduacoes[this.ordinal()][INDEX_DESCRICAO];
        }

        public String getSigla() {
            return graduacoes[this.ordinal()][INDEX_SIGLA];
        }
    }
}
