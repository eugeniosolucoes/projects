/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function rest_call(method, path) {

    method = method || "post";
    var form = document.createElement("form");
    form.setAttribute("method", method);
    form.setAttribute("action", path);

    document.body.appendChild(form);    // Not entirely sure if this is necessary
    form.submit();
}

function excluir(uri) {
    var result = confirm("Deseja excluir o registro selecionado?");
    if (result) {
        rest_call('post', uri);
    }
}

function show_message(type_msg, message) {
    if (message !== '') {
        var sticky_type = type_msg === 'success' ? false : true;
        $().toastmessage('showToast', {
            text: message,
            sticky: sticky_type,
            position: 'middle-center',
            type: type_msg,
            close: function() {
                console.log("toast is closed ...");
            }
        });
    }
}