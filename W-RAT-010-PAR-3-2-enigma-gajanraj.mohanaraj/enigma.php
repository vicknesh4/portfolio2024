<?php
function engima($cle, $message)
{
    if (!is_int($cle)) {
        echo ('la clé passer en paramaetre nest pas un chiffre');
    }
    if ($cle < 1 || $cle > 26) {
        echo ('merci de bien choisir un nombre entre 1 et 26');
    }
    if (is_int($cle) && $message != null) {
        //J''ai mis le double de lettres pour eviter les problemes d'index
        $alphabet_array = ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',];
        $tab = str_split($message);
        $full_phrase = "";
        foreach ($tab as $m) {
    
            if($m == " " || $m == "'"){
                // var_dump($m);
                $full_phrase = $full_phrase . $m;
            }
            for ($i = 0; $i <26; $i++) {
         
                if ($m == $alphabet_array[$i]) {
                    $key =  $i + $cle;
                    $full_phrase = $full_phrase . $alphabet_array[$key];
                }
            }
        }
      
    }
    var_dump($message);
    var_dump($full_phrase);
}
engima(23, "dey ennai per");