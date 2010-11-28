mkdir /tmp/Criptex
cp -f CriptexM"$@".exe /tmp/Criptex/Criptex.rar
cd /tmp/Criptex
unrar x -inul -o+ Criptex.rar
rm Criptex.rar
clear
echo "Por favor no cierre esta ventana mientras la aplicación se encuentra abierta."
echo "Buena suerte!!!"
java -jar criptex.jar
