<?php

require_once './curso.php';

$comando = filter_input( INPUT_GET, 'comando' );

switch ( $comando ) {
    case 'cursos':
        carregar_cursos();
        break;
}

function carregar_cursos() {
    $cursos[] = new curso( '0', '' );
    $cursos[] = new curso( '1', 'Administração' );
    $cursos[] = new curso( '2', 'Contabilidade' );
    $cursos[] = new curso( '3', 'Direito' );
    $cursos[] = new curso( '4', 'Informática' );
    $cursos[] = new curso( '5', 'Pedagogia' );

    header( 'Content-Type: application/json' );
    echo json_encode( $cursos );
}
