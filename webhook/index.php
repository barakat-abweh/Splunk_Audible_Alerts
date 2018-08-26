<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <meta charset="UTF-8">
        <title></title>
    </head>
    <body>
        <div id="music">
            <p>test</p>            
        </div>
        <script type="text/javascript">
            var initial_file_size=0;
            var latest_file_size=0;
            setInterval(function(){
                var xhr=new XMLHttpRequest();
                xhr.open("POST","request.php",true);
                xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                xhr.send("latest_file_size="+latest_file_size); 
                //xhr.send();
                xhr.onreadystatechange=function(){
                    if(this.readyState==4&&this.status==200){
                        var temp=this.responseText;
                        temp=temp.split(",");
                        latest_file_size =temp[0];
                        if(latest_file_size>initial_file_size){
                            initial_file_size=latest_file_size;
                            var music=document.getElementById("music");
                            for(var i=1;i<temp.length;i++){
                                var node=document.createElement("div");
                                node.innerHTML="<audio controls autoplay> <source src=\""+temp[i]+"\" type=\"audio/mpeg\"></audio>";
                                music.appendChild(node);
                            }
                        }          
                    }
                }
            },1000);
        </script>
    </body>
</html>
