let soldos;
let aliquotas;
let adicional_habilitacao;
let formDados = new Object();

$(document).ready(function () {

    $.getJSON("dados/aliquotas.json", function (data) {
        aliquotas = data;
    });

    $.getJSON("dados/soldos.json", function (data) {
        soldos = data;
        $("#patentes").empty();
        $.each(soldos.soldos, function (key, item) {
            $('#patentes')
                    .append($("<option></option>")
                            .attr("value", item.sigla)
                            .text(item.descricao));
        });
        $("#ad_comp_disp").empty();
        $.each(soldos.soldos, function (key, item) {
            $('#ad_comp_disp')
                    .append($("<option></option>")
                            .attr("value", item.sigla)
                            .text(item.sigla + ' (' + item.ad_comp_disp + '%)'));
        });
    });
    $.getJSON("dados/adicional_habilitacao.json", function (data) {
        adicional_habilitacao = data;
        $("#ad_habilitacao_tipo").empty();
        $.each(adicional_habilitacao.habilitacoes, function (key, item) {
            $('#ad_habilitacao_tipo')
                    .append($("<option></option>")
                            .attr("value", item.tipo)
                            .text(item.tipo));
        });
        $("#ad_habilitacao_perc").empty();
        $.each(adicional_habilitacao.habilitacoes[0].percentuais, function (key, item) {
            $('#ad_habilitacao_perc')
                    .append($("<option></option>")
                            .attr("value", item.percentual)
                            .text(item.data + ' (' + item.percentual + '%)'));
        });
    });

    $("#patentes").change(function () {
        $("#ad_comp_disp").val($("#patentes").val());
    });

    $("#ad_habilitacao_tipo").change(function () {
        var i = $("#ad_habilitacao_tipo option:selected").index();
        $("#ad_habilitacao_perc").empty();
        $.each(adicional_habilitacao.habilitacoes[i].percentuais, function (key, item) {
            $('#ad_habilitacao_perc')
                    .append($("<option></option>")
                            .attr("value", item.percentual)
                            .text(item.data + ' (' + item.percentual + '%)'));
        });
    });

    $("#btn_calcular").click(function () {
        calcular();
    });

});

function calcular() {
    let indexPosto = $('#patentes option:selected').index();
    let indexAdCompDisp = $('#ad_comp_disp option:selected').index();
    
    formDados.posto = soldos.soldos[indexPosto];
    formDados.adCompDisp = soldos.soldos[indexAdCompDisp].ad_comp_disp;
    formDados.adMilitar = soldos.soldos[indexPosto].ad_militar;
    formDados.adHab = parseInt( $('#ad_habilitacao_perc').val() );
    formDados.fusma = soldos.fusma;
    formDados.fusmaDep = soldos.fusma_dep;
    formDados.percPensao = parseFloat( $('#perc_pensao').val() );
    formDados.qtdDep = parseInt( $('#qtd_dep').val() );
    formDados.deducaoDependentes = formDados.qtdDep * aliquotas.deducao_dependente;
    
    let rendimentoBruto = formDados.posto.soldo 
            + ( formDados.posto.soldo * (formDados.adCompDisp/100) ) 
            + ( formDados.posto.soldo * (formDados.adMilitar/100) )
            + ( formDados.posto.soldo * (formDados.adHab/100) );
    
    formDados.rendimentoBruto = rendimentoBruto.toFixed(2);
    
    formDados.pensaoMilitar = parseFloat( formDados.rendimentoBruto * (formDados.percPensao/100) ).toFixed(2);
    formDados.fusmaCalculado = parseFloat( formDados.rendimentoBruto * ( formDados.fusma/100 ) ).toFixed(2);
    formDados.fusmaCalculadoDep = parseFloat( formDados.rendimentoBruto * ( (formDados.fusmaDep * formDados.qtdDep) / 100) ).toFixed(2);
    
    let descontos = parseFloat ( formDados.pensaoMilitar ) + parseFloat( formDados.fusmaCalculado ) + parseFloat( formDados.fusmaCalculadoDep );
    
    console.log(descontos);
    formDados.descontos = parseFloat( descontos ).toFixed(2);
    
    console.log(formDados.rendimentoBruto);
    console.log(formDados.descontos);
    console.log(formDados.deducaoDependentes);
    
    formDados.baseCalculo = formDados.rendimentoBruto - formDados.descontos - formDados.deducaoDependentes;
    
    let imposto = 0;
    let faixa = 0;
    for ( i = 0; i < aliquotas.aliquotas.length; i++ ) {
        var aliquota = aliquotas.aliquotas[i];
        var proxima = (i+1) == aliquotas.aliquotas.length ? formDados.baseCalculo : aliquotas.aliquotas[i+1].valor;
        if ( formDados.baseCalculo >= aliquota.valor ) {
            faixa = ( proxima - aliquota.valor ).toFixed( 2 );
            imposto += faixa * aliquota.indice / 100;
        }
    }
    formDados.impostoRenda = imposto.toFixed(2);
    
    formDados.salarioLiquido = formDados.rendimentoBruto - formDados.descontos - formDados.impostoRenda;
    
    console.log(formDados);
    
    $('#salario_bruto').html(formDados.rendimentoBruto);
    $('#pensao_militar').html(formDados.pensaoMilitar);
    $('#fusma').html(formDados.fusmaCalculado);
    $('#fusma_dep').html(formDados.fusmaCalculadoDep);
    $('#imposto_renda').html(formDados.impostoRenda);
    $('#salario_liquido').html(formDados.salarioLiquido);
}
