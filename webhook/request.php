<?php
    
/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$soundfile = 'sounds.txt';
$handle = fopen($soundfile, 'r') or die('Cannot open file:  '.$my_file);
$sounds=fread($handle, filesize($soundfile));
fclose($handle);
$soundsarr= explode("\n", $sounds);
$soundsarray=array();
for($i=0;$i<count($soundsarr)-1;$i++){
    // array_push($soundsarray,array($soundsarr[$i].""=>$soundsarr[$i]));     
    $soundsarr1=explode(" ", $soundsarr[$i]);
    $soundsarray+=[$soundsarr1[0].""=>$soundsarr1[1]];
    }
    if(htmlspecialchars($_SERVER['REQUEST_METHOD'])=="POST"){
        if(isset($_POST['latest_file_size'])){
            $latest_file_size=trim(addslashes(htmlspecialchars($_POST['latest_file_size'])));
            if(is_numeric($latest_file_size)){
                $myfile="file.txt";
                $handle= fopen($myfile, "r");
                fseek($handle, $latest_file_size);
                $data=str_replace("{", "",fread($handle, filesize($myfile)-$latest_file_size));
                $data=str_replace("\"", "",$data);
                $array= explode("\n",$data);
                $file_size= filesize($myfile);
                $tempresult=array();
                foreach ($array as $key=>$value){
                    $value= str_replace("{", "", $value);
                    $value= str_replace("}", "", $value);
                    $array2= explode(",", $value);
                    foreach ($array2 as $key2=>$value2){
                        $value2=trim($value2);
                        $array3= explode(":", $value2);
                        if(strpos($array3[0],"search_name")!=false){
                            $str=str_replace("&quot;", "", $array3[1]);
                            array_push($tempresult, $str);
                            }
                            }
                            }
                            $result= array_unique($tempresult);
                            $result1 = array_values($result);
                            echo $file_size;
                            for($i=0;$i<count($result);$i++){
                                echo ",".$soundsarray[trim($result1[$i])];
                                }
                                }
                                }
                                }
                                ?>