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
        sugerir_habilitacao();
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
        sugerir_pensao();
    });

    $("#ad_habilitacao_perc").change(function () {
        sugerir_pensao();
    });

    $("#btn_calcular").click(function () {
        calcular();
    });

    $('#btn_reset').click(function () {
        $("select").prop('selectedIndex', 0);
        $("#qtd_ad_permanencia").val(1);
        $("#qtd_dep").val(0);
        $(".resultado").html("0.0");
        $("#ad_habilitacao_tipo").change();
    });

});

function calcular() {

    let indexPosto = $('#patentes option:selected').index();
    let indexAdCompDisp = $('#ad_comp_disp option:selected').index();

    formDados.posto = soldos.soldos[indexPosto];
    formDados.adCompDisp = soldos.soldos[indexAdCompDisp].ad_comp_disp;
    formDados.adMilitar = soldos.soldos[indexPosto].ad_militar;
    formDados.adHab = parseInt($('#ad_habilitacao_perc').val());
    formDados.gratRep = parseInt($('#grat_rep').val());
    formDados.gratLocEsp = parseInt($('#grat_loc_esp').val());
    formDados.adCompOrg = parseInt($('#ad_comp_org').val());
    formDados.adPermanencia = parseInt($('#ad_permanencia').val()) * parseInt($('#qtd_ad_permanencia').val());
    formDados.fusma = soldos.fusma;
    formDados.fusmaDep = soldos.fusma_dep;
    formDados.percPensao = parseFloat($('#perc_pensao').val());
    formDados.qtdDep = parseInt($('#qtd_dep').val());
    formDados.deducaoDependentes = formDados.qtdDep * aliquotas.deducao_dependente;

    let rendimentoBruto = formDados.posto.soldo
            + (formDados.posto.soldo * (formDados.adCompDisp / 100))
            + (formDados.posto.soldo * (formDados.adMilitar / 100))
            + (formDados.posto.soldo * (formDados.adHab / 100))
            + (formDados.posto.soldo * (formDados.gratRep / 100))
            + (formDados.posto.soldo * (formDados.gratLocEsp / 100))
            + (formDados.posto.soldo * (formDados.adCompOrg / 100))
            + (formDados.posto.soldo * (formDados.adPermanencia / 100));

    formDados.rendimentoBruto = rendimentoBruto.toFixed(2);

    formDados.pensaoMilitar = parseFloat(formDados.rendimentoBruto * (formDados.percPensao / 100)).toFixed(2);
    formDados.fusmaCalculado = parseFloat(formDados.rendimentoBruto * (formDados.fusma / 100)).toFixed(2);
    formDados.fusmaCalculadoDep = parseFloat(formDados.rendimentoBruto * ((formDados.fusmaDep * formDados.qtdDep) / 100)).toFixed(2);
    formDados.mntlp = $('#chkMntlp').is(":checked") ? parseFloat(formDados.rendimentoBruto * ((1.5) / 100)).toFixed(2) : 0;

    let descontos = parseFloat(formDados.pensaoMilitar)
            + parseFloat(formDados.fusmaCalculado)
            + parseFloat(formDados.fusmaCalculadoDep)
            + parseFloat(formDados.mntlp);

    formDados.descontos = parseFloat(descontos).toFixed(2);

    formDados.baseCalculo = formDados.rendimentoBruto - formDados.descontos - formDados.deducaoDependentes;

    let imposto = 0;
    let faixa = 0;
    for (i = 0; i < aliquotas.aliquotas.length; i++) {
        var aliquota = aliquotas.aliquotas[i];
        var proxima = (i + 1) == aliquotas.aliquotas.length ? formDados.baseCalculo : aliquotas.aliquotas[i + 1].valor;
        if (formDados.baseCalculo >= aliquota.valor) {
            faixa = (proxima - aliquota.valor).toFixed(2);
            imposto += faixa * aliquota.indice / 100;
        }
    }
    formDados.impostoRenda = imposto.toFixed(2);

    formDados.salarioLiquido = parseFloat(formDados.rendimentoBruto - formDados.descontos - formDados.impostoRenda).toFixed(2);

    $('#salario_bruto').html(formDados.rendimentoBruto);
    $('#pensao_militar').html(formDados.pensaoMilitar);
    $('#fusma').html(formDados.fusmaCalculado);
    $('#fusma_dep').html(formDados.fusmaCalculadoDep);
    $('#mntlp').html(formDados.mntlp);
    $('#imposto_renda').html(formDados.impostoRenda);
    $('#salario_liquido').html(formDados.salarioLiquido);
}

function sugerir_pensao() {
    var ind = $("#ad_habilitacao_perc option:selected").index();
    $("#perc_pensao").prop('selectedIndex', ind > 0 ? 1 : 0);
}

function sugerir_habilitacao() {
    var ind = $('#patentes option:selected').index();
    var value = 0;
    switch( soldos.soldos[ind].sigla ){
        case 'CT':
        case '1SG':
        case '2SG':
            value= 1;
            break;
        case '1T':
        case '2T':
        case '3SG':
            value= 2;
            break;
        case '2SG(QESM)':
        case '3SG(QESM)':
        case 'CB':
            value= 3;
            break;
        case 'MN':
            value= 4;
            break;
    }
    $("#ad_habilitacao_tipo").prop('selectedIndex', value);
    $("#ad_habilitacao_tipo").change();
}