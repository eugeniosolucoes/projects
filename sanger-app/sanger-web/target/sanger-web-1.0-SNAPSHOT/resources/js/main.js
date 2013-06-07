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


function chk_excluir() {
    var itens = document.getElementsByName("chk_item");
    var total = itens.length;
    var cont = 0;
    for (var i = 0; i < itens.length; i++) {
        var child = itens.item(i);
        if (child.type !== undefined) {
            var tipo = child.type.toLowerCase();
            if (tipo === "checkbox") {
                if (child.checked) {
                    cont++;
                }
            }
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

function cleanMask(val) {
    var strCheck = "'[](){}<>=+-*/_|\~`!?@#$%^&:;,.";
    var aux = "";
    var i;

    for (i = 0; i < val.length; i++) {
        if (strCheck.indexOf(val.charAt(i)) === -1) {
            aux += val.charAt(i);
        }
    }
    return aux;
}

function maskCurrency(val, milSep, decSep) {
    var aux = "";
    var aux2 = "";

    var i, j;

    len = val.length;
    if (len === 0) {
        aux = '';
    } else if (len === 1) {
        aux = '0' + decSep + '0' + val;
    } else if (len === 2) {
        aux = '0' + decSep + val;
    } else if (len > 2) {
        aux2 = '';

        for (j = 0, i = len - 3; i >= 0; i--) {
            if (j === 3) {
                aux2 += milSep;
                j = 0;
            }
            aux2 += val.charAt(i);
            j++;
        }
        aux = '';
        len2 = aux2.length;
        for (i = len2 - 1; i >= 0; i--) {
            aux += aux2.charAt(i);
        }
        aux += decSep + val.substr(len - 2, len);
    }
    return aux;
}
function mask(_mask, val) {
    var i, mki;
    var aux = "";

    for (i = mki = 0; i < val.length; i++, mki++) {
        if (_mask.charAt(mki) === '' || _mask.charAt(mki) === '#' || _mask.charAt(i) === val.charAt(i)) {
            aux += val.charAt(i);
        } else {
            aux += _mask.charAt(mki) + val.charAt(i);
            mki++;
        }
    }
    return aux;
}

function maskEvent(field, _mask, event) {
    var key = '';
    var aux = '';
    var len = 0;
    var i = 0;
    var strCheck = '0123456789';
    var rcode = (window.Event) ? event.which : event.keyCode;

    if (rcode === 8) {
        return true;
    }

    if (rcode === 13 || rcode === 0 || field.value.length === _mask.length) {
        //Enter
        key = String.fromCharCode(rcode);

        if (rcode !== 13 && rcode !== 0) {
            return false;
        }

        return true;
    }

    //Get key value from key code
    key = String.fromCharCode(rcode);

    if (strCheck.indexOf(key) === -1) {
        //Not a valid key
        return false;
    }

    aux = field.value + key;
    //window.alert(aux);
    aux = mask(_mask, aux);
    //window.alert(aux);
    field.value = aux;
    return false;
}

function currencyFormat(fld, milSep, decSep, e) {
    var sep = 0;
    var key = '';
    var i = j = 0;
    var len = len2 = 0;
    var strCheck = '0123456789';
    var aux = aux2 = '';
    var whichCode = (window.Event) ? e.which : e.keyCode;

    if (whichCode === 13 || whichCode === 0) {		//Enter
        return true;
    }
    key = String.fromCharCode(whichCode);  // Get key value from key code
    if (strCheck.indexOf(key) === -1) {
        return false;  // Not a valid key
    }
    len = fld.value.length;
    for (i = 0; i < len; i++) {
        if ((fld.value.charAt(i) !== '0') && (fld.value.charAt(i) !== decSep)) {
            break;
        }
    }
    aux = '';
    for (; i < len; i++) {
        if (strCheck.indexOf(fld.value.charAt(i)) !== -1) {
            aux += fld.value.charAt(i);
        }
    }
    aux += key;

    len = aux.length;
    if (len === 0) {
        fld.value = '';
    } else if (len === 1) {
        fld.value = '0' + decSep + '0' + aux;
    } else if (len === 2) {
        fld.value = '0' + decSep + aux;
    } else if (len > 2) {
        aux2 = '';

        for (j = 0, i = len - 3; i >= 0; i--) {
            if (j === 3) {
                aux2 += milSep;
                j = 0;
            }
            aux2 += aux.charAt(i);
            j++;
        }
        fld.value = '';
        len2 = aux2.length;
        for (i = len2 - 1; i >= 0; i--) {
            fld.value += aux2.charAt(i);
        }
        fld.value += decSep + aux.substr(len - 2, len);
    }
    return false;
}

function excluir(msg, acao) {
    $("#dialog:ui-dialog").dialog("destroy");
    $("#dialog-confirm").dialog({
        open: function() {
            $(this).siblings('.ui-dialog-buttonpane').find('button:eq(1)').focus();
        },
        resizable: false,
        modal: true,
        title: 'Sanger-Rio Trasnportes Ltda',
        buttons: {
            "Sim": function() {
                window.location.href = acao;
            },
            "N\xE3o": function() {
                $(this).dialog("close");
            }
        }
    });
    $("#dialog-confirm").text("Deseja realmente excluir " + msg + "?");
}

function show_message(type, message) {
    if (message !== '') {
        //$().toastmessage({sticky: true});
        $().toastmessage(type, message);
    }
}