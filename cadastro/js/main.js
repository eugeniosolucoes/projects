/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function carregar_cursos() {
    var dados = new Object();
    dados.comando = 'cursos';
    load_select( 'cadastro.php', dados, 'cursos' );
}

function load_select( surl, dados, id ) {
    $.ajax( {
        url: surl,
        data: dados,
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function ( response ) {
            $( '#' + id ).get( 0 ).options.length = 0;
            $.each( response, function ( key, item ) {
                if ( item.selected ) {
                    $( '#' + id )
                            .append( $( "<option></option>" )
                                    .attr( "value", item.id )
                                    .attr( "selected", item.selected )
                                    .text( item.descricao ) );
                } else {
                    $( '#' + id )
                            .append( $( "<option></option>" )
                                    .attr( "value", item.id )
                                    .text( item.descricao ) );
                }
            } );
        },
        error: function () {
            alert( "Erro ao recuperar os dados!" );
        }
    } );
}

function search_cep( fcep ) {
    if ( $.trim( fcep ) !== '' && fcep.length === 8 ) {
        $( '#pesquisando_cep' ).css( 'display', 'block' );
        $.ajax( {
            url: 'https://viacep.com.br/ws/' + fcep + '/json/',
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function ( cep ) {
                $( '#bairro' ).val( cep.bairro );
                $( '#cidade' ).val( cep.localidade );
                $( '#estado' ).val( cep.uf );
                $( '#endereco' ).val( cep.logradouro );
            },
            error: function () {
                alert( "Erro ao recuperar os dados!" );
            }
        } );
        $( '#pesquisando_cep' ).css( 'display', 'none' );
    } else {
        $( '#bairro' ).val( '' );
        $( '#cidade' ).val( '' );
        $( '#estado' ).val( '' );
        $( '#endereco' ).val( '' );
    }
}

function preview_image() {
    //var preview = document.querySelector( 'img' ); //selects the query named img
    var preview = document.querySelector( '#foto_candidato' );
    var file = document.querySelector( 'input[type=file]' ).files[0]; //sames as here
    var reader = new FileReader();
    reader.onloadend = function () {
        preview.src = reader.result;
        resize(preview);
    };
    if ( file ) {
        reader.readAsDataURL( file ); //reads the data as a URL
    } else {
        preview.src = "";
    }
}

function show_image(){
    var canvas = document.querySelector( 'canvas' );
    var dataUrl = canvas.toDataURL();
    var image = document.querySelector( '#foto_candidato' );
    image.src = dataUrl;
    $('canvas').css('display', 'none');
    $('#foto_candidato').css('display', 'block');
}

function resize(img) {
    var canvas = document.querySelector( 'canvas' );
    var ctx = canvas.getContext( "2d" );
    var MAX_WIDTH = 354;
    var MAX_HEIGHT = 472;
    var width = img.width;
    var height = img.height;

    if ( width > height ) {
        if ( width > MAX_WIDTH ) {
            height *= MAX_WIDTH / width;
            width = MAX_WIDTH;
        }
    } else {
        if ( height > MAX_HEIGHT ) {
            width *= MAX_HEIGHT / height;
            height = MAX_HEIGHT;
        }
    }
    canvas.width = width;
    canvas.height = height;
    setTimeout( function() { ctx.drawImage( img, 0, 0, width, height ); }, 300 );
    setTimeout( function() { show_image(); }, 300 );
}

