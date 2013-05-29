/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sanger.modelo.apoio;

/**
 *
 * @author eugenio
 */
public enum Estado {

    AC, AL, AP, AM, BA, CE, DF,
    GO, ES, MA, MT, MS, MG, PA,
    PB, PR, PE, PI, RJ, RN, RS,
    RO, RR, SP, SC, SE, TO;

    static String[] uf = { "Acre",
        "Alagoas",
        "Amapá",
        "Amazonas",
        "Bahia",
        "Ceará",
        "Distrito Federal",
        "Goiás",
        "Espírito Santo",
        "Maranhão",
        "Mato Grosso",
        "Mato Grosso do Sul",
        "Minas Gerais",
        "Pará",
        "Paraiba",
        "Paraná",
        "Pernambuco",
        "Piauí",
        "Rio de Janeiro",
        "Rio Grande do Norte",
        "Rio Grande do Sul",
        "Rondônia",
        "Rorâima",
        "São Paulo",
        "Santa Catarina",
        "Sergipe",
        "Tocantins" };

    public String getDescricao() {
        return uf[this.ordinal()];
    }
}
