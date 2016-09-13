/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eugeniosolucoes.nfse.util;

import java.text.Normalizer;

/**
 *
 * @author eugenio
 */
public enum MunicipioRJ {

    ANGRA_DOS_REIS( "ANGRA DOS REIS", 3300100 ),
    APERIBE( "APERIBE", 3300159 ),
    ARARUAMA( "ARARUAMA", 3300209 ),
    AREAL( "AREAL", 3300225 ),
    ARMACAO_DOS_BUZIOS( "ARMACAO DOS BUZIOS", 3300233 ),
    ARRAIAL_DO_CABO( "ARRAIAL DO CABO", 3300258 ),
    BARRA_DO_PIRAI( "BARRA DO PIRAI", 3300308 ),
    BARRA_MANSA( "BARRA MANSA", 3300407 ),
    BELFORD_ROXO( "BELFORD ROXO", 3300456 ),
    BOM_JARDIM( "BOM JARDIM", 3300506 ),
    BOM_JESUS_DO_ITABAPOANA( "BOM JESUS DO ITABAPOANA", 3300605 ),
    CABO_FRIO( "CABO FRIO", 3300704 ),
    CACHOEIRAS_DE_MACACU( "CACHOEIRAS DE MACACU", 3300803 ),
    CAMBUCI( "CAMBUCI", 3300902 ),
    CARAPEBUS( "CARAPEBUS", 3300936 ),
    CAMPOS_DOS_GOYTACAZES( "CAMPOS DOS GOYTACAZES", 3301009 ),
    COMENDADOR_LEVY_GASPARIAN( "COMENDADOR LEVY GASPARIAN", 3300951 ),
    CANTAGALO( "CANTAGALO", 3301108 ),
    CARDOSO_MOREIRA( "CARDOSO MOREIRA", 3301157 ),
    CARMO( "CARMO", 3301207 ),
    CASIMIRO_DE_ABREU( "CASIMIRO DE ABREU", 3301306 ),
    CONCEICAO_DE_MACABU( "CONCEICAO DE MACABU", 3301405 ),
    CORDEIRO( "CORDEIRO", 3301504 ),
    DUAS_BARRAS( "DUAS BARRAS", 3301603 ),
    DUQUE_DE_CAXIAS( "DUQUE DE CAXIAS", 3301702 ),
    ENGENHEIRO_PAULO_DE_FRONTIN( "ENGENHEIRO PAULO DE FRONTIN", 3301801 ),
    GUAPIMIRIM( "GUAPIMIRIM", 3301850 ),
    IGUABA_GRANDE( "IGUABA GRANDE", 3301876 ),
    ITABORAI( "ITABORAI", 3301900 ),
    ITAGUAI( "ITAGUAI", 3302007 ),
    ITALVA( "ITALVA", 3302056 ),
    ITAOCARA( "ITAOCARA", 3302106 ),
    ITAPERUNA( "ITAPERUNA", 3302205 ),
    ITATIAIA( "ITATIAIA", 3302254 ),
    JAPERI( "JAPERI", 3302270 ),
    LAJE_DO_MURIAE( "LAJE DO MURIAE", 3302304 ),
    MACAE( "MACAE", 3302403 ),
    MACUCO( "MACUCO", 3302452 ),
    MAGE( "MAGE", 3302502 ),
    MANGARATIBA( "MANGARATIBA", 3302601 ),
    MARICA( "MARICA", 3302700 ),
    MENDES( "MENDES", 3302809 ),
    MESQUITA( "MESQUITA", 3302858 ),
    MIGUEL_PEREIRA( "MIGUEL PEREIRA", 3302908 ),
    MIRACEMA( "MIRACEMA", 3303005 ),
    NATIVIDADE( "NATIVIDADE", 3303104 ),
    NILOPOLIS( "NILOPOLIS", 3303203 ),
    NITEROI( "NITEROI", 3303302 ),
    NOVA_FRIBURGO( "NOVA FRIBURGO", 3303401 ),
    NOVA_IGUACU( "NOVA IGUACU", 3303500 ),
    PARACAMBI( "PARACAMBI", 3303609 ),
    PARAIBA_DO_SUL( "PARAIBA DO SUL", 3303708 ),
    PARATI( "PARATI", 3303807 ),
    PATY_DO_ALFERES( "PATY DO ALFERES", 3303856 ),
    PETROPOLIS( "PETROPOLIS", 3303906 ),
    PINHEIRAL( "PINHEIRAL", 3303955 ),
    PIRAI( "PIRAI", 3304003 ),
    PORCIUNCULA( "PORCIUNCULA", 3304102 ),
    PORTO_REAL( "PORTO REAL", 3304110 ),
    QUATIS( "QUATIS", 3304128 ),
    QUEIMADOS( "QUEIMADOS", 3304144 ),
    QUISSAMA( "QUISSAMA", 3304151 ),
    RESENDE( "RESENDE", 3304201 ),
    RIO_BONITO( "RIO BONITO", 3304300 ),
    RIO_CLARO( "RIO CLARO", 3304409 ),
    RIO_DAS_FLORES( "RIO DAS FLORES", 3304508 ),
    RIO_DAS_OSTRAS( "RIO DAS OSTRAS", 3304524 ),
    RIO_DE_JANEIRO( "RIO DE JANEIRO", 3304557 ),
    SANTA_MARIA_MADALENA( "SANTA MARIA MADALENA", 3304607 ),
    SANTO_ANTONIO_DE_PADUA( "SANTO ANTONIO DE PADUA", 3304706 ),
    SAO_FRANCISCO_DE_ITABAPOANA( "SAO FRANCISCO DE ITABAPOANA", 3304755 ),
    SAO_FIDELIS( "SAO FIDELIS", 3304805 ),
    SAO_GONCALO( "SAO GONCALO", 3304904 ),
    SAO_JOAO_DA_BARRA( "SAO JOAO DA BARRA", 3305000 ),
    SAO_JOAO_DE_MERITI( "SAO JOAO DE MERITI", 3305109 ),
    SAO_JOSE_DE_UBA( "SAO JOSE DE UBA", 3305133 ),
    SAO_JOSE_DO_VALE_DO_RIO_PRETO( "SAO JOSE DO VALE DO RIO PRETO", 3305158 ),
    SAO_PEDRO_DA_ALDEIA( "SAO PEDRO DA ALDEIA", 3305208 ),
    SAO_SEBASTIAO_DO_ALTO( "SAO SEBASTIAO DO ALTO", 3305307 ),
    SAPUCAIA( "SAPUCAIA", 3305406 ),
    SAQUAREMA( "SAQUAREMA", 3305505 ),
    SEROPEDICA( "SEROPEDICA", 3305554 ),
    SILVA_JARDIM( "SILVA JARDIM", 3305604 ),
    SUMIDOURO( "SUMIDOURO", 3305703 ),
    TANGUA( "TANGUA", 3305752 ),
    TERESOPOLIS( "TERESOPOLIS", 3305802 ),
    TRAJANO_DE_MORAIS( "TRAJANO DE MORAIS", 3305901 ),
    TRES_RIOS( "TRES RIOS", 3306008 ),
    VALENCA( "VALENCA", 3306107 ),
    VARRESAI( "VARRESAI", 3306156 ),
    VASSOURAS( "VASSOURAS", 3306206 ),
    VOLTA_REDONDA( "VOLTA REDONDA", 3306305 );

    private final String descricao;

    private final int codigo;

    MunicipioRJ( String municipio, int codigo ) {
        this.descricao = municipio;
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public static MunicipioRJ getMunicipio( String municipio ) {
        if ( municipio != null ) {
            String str = Normalizer.normalize( municipio, Normalizer.Form.NFD ).replaceAll( "[^\\p{ASCII}]", "" );
            for ( MunicipioRJ municipioRJ : MunicipioRJ.values() ) {
                if ( str.equalsIgnoreCase( municipioRJ.getDescricao() ) ) {
                    return municipioRJ;
                }
            }
        }
        return RIO_DE_JANEIRO;
    }

}