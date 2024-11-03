<?php
 $array = [1.3432, 'fre',2,3, 'grfejf', "fref"];
 $doubleArray = [43,['bzaga', 'greg', 23],['bzaga', 'greg', 23], "fre", ['bzaga', 'greg', 23]];

function my_vardump($any){
    if(is_string($any)){
        $nb_caractere = strlen($any);
        echo("string"."($nb_caractere)"." "."\"$any\"\n");
    }
    if(is_int($any)){
        echo("int"."($any)\n");
    }
    if(is_float($any)){
        echo("float"."($any)\n");
    }
    is_tableau_vick($any);
    }


function is_tableau_vick($any, $rec = false, $i=null){

    if($rec!= true){
        $nb_elements = count($any);
        echo("array[$nb_elements] {\n"); 

    }
    if($rec!=false){
        static $nb_appel_fonction = 0;
        $nb_appel_fonction++;

        $nb_cara = count($any);
        echo("  "."[$i]=>\n  array($nb_cara) {\n");    
    }
    if(is_array($any) && $rec!=false){
       
          $nb_elements = count($any);
        for($i=0; $i<count($any); $i++){
            
            if(is_int($any[$i])){
               echo(
                "  "."  [$i]"."=>\n"."  "."  int($any[$i])\n"
            );
            }
            if(is_float($any[$i])){
                echo(
                 "  "."  [$i]"."=>\n"."  "."  float($any[$i])\n"
             );
             }
            if(is_string($any[$i])){
         $nb_caractere = strlen($any[$i]);
                echo(
                        "  "."  [$i]"."=>\n"."  "."  string($nb_caractere)\"$any[$i]\"\n"
                );
            }
            if(is_array($any[$i])){
                is_tableau_vick($any[$i], $rec = true, $i);
            }
        }
            echo("  }\n");   

    }
    elseif(is_array($any)&& $rec !=true){
        $nb_elements = count($any);
        for($i=0; $i<count($any); $i++){
            
            if(is_int($any[$i])){
               echo(
                "  "."[$i]"."=>\n"."  "."int($any[$i])\n"
            );
            }
            if(is_float($any[$i])){
                echo(
                 "  "."[$i]"."=>\n"."  "."float($any[$i])\n"
             );
             }
            if(is_string($any[$i])){
         $nb_caractere = strlen($any[$i]);
                echo(
                        "  "."[$i]"."=>\n"."  "."string($nb_caractere)\"$any[$i]\"\n"
                );
            }
            if(is_array($any[$i])){
                is_tableau_vick($any[$i], $rec = true, $i);
            }
        }
            echo("}");     
        }
}
my_vardump($doubleArray);
echo("\n----------------------------------------------------------\n");
 var_dump($doubleArray);
?>