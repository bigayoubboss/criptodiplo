function RandomNumber() {
    var today = new Date();
    var num= Math.abs(Math.sin(today.getTime()/1000));
    return num;
}

function RotativosAvisos() 
{
    var x = RandomNumber();

    if (x > .80){
        document.write("<img src=images/collage_superior.jpg alt=Facultad_Ciencias border=0 align=center>");
        return; 
    }
    if (x > .40){
        document.write("<img src=images/collage_superior2.jpg alt=Facultad_Ciencias border=0 align=center>");
        return; 
    }
    if (x > .0){
        document.write("<img src=images/collage_superior3.jpg alt=Facultad_Ciencias border=0 align=center>");
        return; 
    }
}