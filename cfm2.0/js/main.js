
function excluir(params) {
    var resultado = confirm('Deseja realmente EXCLUIR o(s) registro(s) selecionado(s)?');
    params.comando = 'excluir';
    if (resultado) {
        post_to_url('list.php', params, 'post');
    }
    return resultado;
}

function excluir_lancamentos() {
    var lancamentos = new Array();
    $(".chk_item").each(function (index) {
        if ($(this).is(':checked')) {
            lancamentos[index] = $(this).val();
        }
    });
    // compactar o array
    lancamentos = $.grep(lancamentos, function (n) {
        return(n);
    });
    var params = new Array();
    var resultado = confirm('Deseja realmente EXCLUIR o(s) registro(s) selecionado(s)?');
    params['comando'] = 'excluir';
    params['lancamentos'] = lancamentos;
    params['mes'] = $('#mes').val();
    params['ano'] = $('#ano').val();
    if (resultado) {
        post_to_url('list.php', params, 'post');
    }
    return resultado;

}

function copiar() {
    var lancamentos = new Array();
    $(".chk_item").each(function (index) {
        if ($(this).is(':checked')) {
            lancamentos[index] = $(this).val();
        }
    });
    // compactar o array
    lancamentos = $.grep(lancamentos, function (n) {
        return(n);
    });
    var params = new Array();
    var resultado = confirm('Deseja realmente COPIAR o(s) lançamento(s) selecionado(s) para o PERÍODO selecionado?');
    params['comando'] = 'copiar';
    params['lancamentos'] = lancamentos;
    params['mes'] = $('#mes').val();
    params['ano'] = $('#ano').val();
    if (resultado) {
        post_to_url('list.php', params, 'post');
    }
    return resultado;
}


function post_to_url(path, params, method) {
    method = method || "post"; // Set method to post by default, if not specified.

    // The rest of this code assumes you are not using a library.
    // It can be made less wordy if you use one.
    var form = document.createElement("form");
    form.setAttribute("method", method);
    form.setAttribute("action", path);

    for (var key in params) {
        var hiddenField = document.createElement("input");
        hiddenField.setAttribute("type", "hidden");
        hiddenField.setAttribute("name", key);
        hiddenField.setAttribute("value", params[key]);

        form.appendChild(hiddenField);
    }

    document.body.appendChild(form);    // Not entirely sure if this is necessary
    form.submit();
}

function retornar_categorias_json(categoria, lancamento) {
    $.ajax({
        url: 'form.php',
        data: {
            comando: 'retornar_categorias_json',
            id: lancamento,
            tipo: categoria
        },
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (data) {
            $("#categoria").get(0).options.length = 0;
            $.each(data, function (key, item) {
                if (item.selected) {
                    $('#categoria')
                            .append($("<option></option>")
                                    .attr("value", item.id)
                                    .attr("selected", item.selected)
                                    .text(item.descricao));
                } else {
                    $('#categoria')
                            .append($("<option></option>")
                                    .attr("value", item.id)
                                    .text(item.descricao));
                }
            });
        },
        error: function () {
            alert("Erro ao recuperar os dados!");
        }
    });
}

function listar_periodo(url) {
    var data = $("#inclusao").val().split("/");
    var acao = $("#btn-acao-listar").text() == 'Listar' ? true : false;
    window.location.href = url + '&ano=' + data[2] + '&mes=' + data[1] + '&acao=' + acao;
}

function chk_lancamentos(obj) {
    $('.chk_item').prop("checked", obj.checked);
}

function chk_excluir() {
    var itens = document.getElementsByName("chk_item");
    var cont = 0;
    for (var i = 0; i < itens.length; i++) {
        var child = itens.item(i);
        if (child.checked) {
            cont++;
        }
    }
    if (cont < total) {
        $('#chk_all').attr("checked", false);
    } else {
        $('#chk_all').attr("checked", true);
    }
    if (cont > 0) {
        $('#btn-excluir').removeAttr('disabled');
        $('#btn-copiar').removeAttr('disabled');
    } else {
        $('#btn-excluir').attr("disabled", "disabled");
        $('#btn-copiar').attr("disabled", "disabled");
    }
}

function calcular() {
    var creditos = 0;
    var debitos = 0;
    $("tr.item").each(function () {
        $this = $(this)
        var valor_credito = $this.find("span.lancamento_credito").html();
        if (!isNaN(valor_credito)) {
            creditos += new Number(valor_credito);
        }
        var value_debito = $this.find("span.lancamento_debito").html();
        if (!isNaN(value_debito)) {
            debitos += new Number(value_debito);
        }
    });
    var resultado = creditos - debitos;
    $('#total').html(Math.abs(resultado).toFixed(2));
    if (resultado >= 0) {
        $('#total').removeClass('total_negativo');
        $('#total').addClass('total_positivo');
    } else {
        $('#total').removeClass('total_positivo');
        $('#total').addClass('total_negativo');
    }

}

function processar_anos() {
    var ano = new Date().getFullYear();
    $.ajax({
        url: 'index.php?comando=periodos'
    }).done(function (resposta) {
        var anos = resposta.anos;
        var sel = $("#list-anos");
        sel.empty();
        for (var i = 0; i < anos.length; i++) {
            var selecionado = ano == anos[i] ? 'selected' : '';
            sel.append('<option value="' + anos[i] + '"' + selecionado + ' >' + anos[i] + '</option>');
        }
    });
}

function processar_periodo(vano){
    var ano = vano || new Date().getFullYear();
    $('#list-anos').val(ano);
    $.ajax({
        url: 'index.php?comando=periodos&ano=' + ano
    }).done(function (resposta) {
        var periodos = resposta.periodos;
        var tbl = $('#tbl-periodo > tbody');
        tbl.empty();
        for (var i = 0; i < periodos.length; i++) {
            tbl.append('<tr class="row-periodo" onclick="document.location=\'view/lancamento/list.php?comando=listar&ano='+ano+'&mes='+periodos[i].mes+'\';">' +
                    '<td>' + periodos[i].mes + '</td>' +
                    '<td>' + new Number(periodos[i].creditos).toFixed(2) + '</td>' +
                    '<td>' + new Number(periodos[i].debitos).toFixed(2) + '</td>' +
                    '<td>' + new Number(periodos[i].balanco).toFixed(2) + '</td>' +
                    '</tr>');
        }
        $('#div-periodos').show();
    });
}


/**
 * JQUERY FIX CLONE FUNCTION 
 * Autor: Eugenio em 2016-07-08 
 * Para:  Resolver problemas de clonagem de tags select e textarea que nao copiam selecoes.
 * 
 * @param {type} original
 * @returns {undefined}
 */
(function (original) {
  jQuery.fn.clone = function () {
    var result           = original.apply(this, arguments),
        my_textareas     = this.find('textarea').add(this.filter('textarea')),
        result_textareas = result.find('textarea').add(result.filter('textarea')),
        my_selects       = this.find('select').add(this.filter('select')),
        result_selects   = result.find('select').add(result.filter('select'));

    for (var i = 0, l = my_textareas.length; i < l; ++i) $(result_textareas[i]).val($(my_textareas[i]).val());
    for (var i = 0, l = my_selects.length;   i < l; ++i) result_selects[i].selectedIndex = my_selects[i].selectedIndex;

    return result;
  };
}) (jQuery.fn.clone);

/**
 * Autor: Eugenio em 2016-07-11 
 * Preenche um form com os campos serializada pela funcao serialize do JQuery.
 * 
 * @param {type} jQuery
 * @returns {undefined}
 */
(function (jQuery) {
    jQuery.fn.deserialize = function (data) {
        var f = jQuery(this),
                map = {},
                find = function (selector) {
                    return f.is("form") ? f.find(selector) : f.filter(selector);
                };
        //Get map of values
        jQuery.each(data.split("&"), function () {
            var nv = this.split("="),
                    n = decodeURIComponent(nv[0]),
                    v = nv.length > 1 ? decodeURIComponent(nv[1]) : null;
            if (!(n in map)) {
                map[n] = [];
            }
            map[n].push(v);
        })
        //Set values for all form elements in the data
        jQuery.each(map, function (n, v) {
            find("[name='" + n + "']").val(v);
        })
        //Clear all form elements not in form data
        find("input:text,select,textarea").each(function () {
            if (!(jQuery(this).attr("name") in map)) {
                jQuery(this).val("");
            }
        })
        find("input:checkbox:checked,input:radio:checked").each(function () {
            if (!(jQuery(this).attr("name") in map)) {
                this.checked = false;
            }
        })
        return this;
    };
})(jQuery);


/**
 * Carrega form salvo por meio de cookie e executa uma acao opcional caso necessario.
 * @param {type} form_id - Id do form que serah carregado (sem #).
 * @param {type} acao - Opcional - Acao a ser executada apos o form carregado.
 * @returns {undefined}
 */
function carrega_form_salvo(form_id, acao) {
    var cookie_name = 'cache_'+form_id;
    var cache = Cookies.get(cookie_name);
    try {
        $('#'+form_id).deserialize(cache);
    } catch (e) {
        console.log(e);
    }
    eval(acao);
}

/**
 * Salvar form serializado como cookie.
 * O nome do cookie serah cache_[form_id] (sem #).
 * @param {type} form_id - Id do form que serah salvo (sem #).
 * @returns {undefined}
 */
function salvar_form(form_id){
    Cookies.set('cache_'+ form_id, $('#'+form_id).serialize(), {path: '/'});
}

/**
 * Apaga cookie salvo com dados do form.
 * @param {type} form_id - Id do form salvo (sem #).
 * @returns {undefined}
 */
function apagar_form_salvo(form_id){
    Cookies.remove('cache_'+form_id, { path: '/' });
}


