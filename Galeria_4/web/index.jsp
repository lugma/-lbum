<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Log-in</title>
    </head>

    <body>
        <center><h2>Galería</h2></center>
        <br>
        
        <script language="JavaScript">
            function go(){
                if(document.form.txtpsw.value=='123' && document.form.txtusuario.value=='admin'){
                    document.form.submit();
                }
                else{
                    alert("Ingresar Usuario y Contraseña correctos");
                }
            }
        </script>
        
        <form name="form" action="subir.html"> 
            <center>
                <input type="text" placeholder="usuario" name="txtusuario"/>
                <br>
                <br>
                <input type="password" placeholder="contraseña" name="txtpsw"/>
                <br>
                <br>
                <input onClick="go()" type="button" value="Ingresar"/>
            </center>
        </form>  
    </body>
</html>

