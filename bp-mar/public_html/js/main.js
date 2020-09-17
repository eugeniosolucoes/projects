let soldos;
let adicional_habilitacao;

$(document).ready(function () {

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
    let formDados = new Object();
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
    
    let rendimentoBruto = formDados.posto.soldo 
            + ( formDados.posto.soldo * (formDados.adCompDisp/100) ) 
            + ( formDados.posto.soldo * (formDados.adMilitar/100) )
            + ( formDados.posto.soldo * (formDados.adHab/100) );
    
    formDados.rendimentoBruto = rendimentoBruto.toFixed(2);
    
    let descontos = ( formDados.rendimentoBruto * (formDados.percPensao/100) ) 
            + ( formDados.rendimentoBruto * ( formDados.fusma/100 ) )
            + ( formDados.rendimentoBruto * ( (formDados.fusmaDep * formDados.qtdDep) / 100) );
    
    formDados.descontos = descontos.toFixed(2);
    console.log(formDados);
    
    console.log(formDados.rendimentoBruto);
    console.log(formDados.descontos);
}
