/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servico;

import java.util.List;
import modelo.jpa.Militar;
import modelo.jpa.Praca;

/**
 *
 * @author eugenio
 */
public interface MilitarServico {

    List<Militar> listar();

    Militar retornar( Object id );
}
