<?php

class seguranca {

	private static $KEY = 'uKEJ2GQLu9oibc7gb/sltRndYKc='; 
	private static $IV = 'AZyNpDjJgu4=';
        
        public static $HOST = 'HrxWGamTdjgf';
        public static $DATABASE = 'EbVY';
        public static $USER = 'ALxaDA==';
        public static $PASSWD = 'ALZRHamUeioH';
	
	function cifrar($senha_decrypt) {
		return @base64_encode(mcrypt_ofb(MCRYPT_DES, self::$KEY, $senha_decrypt, MCRYPT_ENCRYPT, base64_decode(self::$IV)));
	} 
	
	function decifrar($senha_crypt) {
		return @mcrypt_ofb(MCRYPT_DES, self::$KEY, base64_decode($senha_crypt), MCRYPT_DECRYPT, base64_decode(self::$IV));
	}
}

?>