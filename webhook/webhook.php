<?php

/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
        if(htmlspecialchars($_SERVER['REQUEST_METHOD']=="POST")){
            $my_file = 'file.txt';
            $handle = fopen($my_file, 'a+') or die('Cannot open file:  '.$my_file);
            $data = file_get_contents("php://input");
            $data= addslashes(trim(htmlspecialchars($data)));
            fwrite($handle, $data."\n");
            fclose($handle);
        }?>