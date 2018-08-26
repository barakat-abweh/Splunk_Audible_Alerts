<?php

/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$myfile="file.txt";
$handle= fopen($myfile, "r");
$data=str_replace("{", "",fread($handle, filesize($myfile)));
$data=str_replace("\"", "",$data);
$array= explode("\n",$data);
foreach ($array as $key=>$value){
    $value= str_replace("{", "", $value);
    $value= str_replace("}", "", $value);
     $array2= explode(",", $value);
     foreach ($array2 as $key2=>$value2){
         $array3= explode(":", $value2);
         foreach ($array3 as $key3=>$value3){
             if(strpos($array3[0],"search_name")!=false){
                 }
             }
                 }
                 echo "<br><br><br><br>";
                 }
                 ?>