javac -classpath pa2-120311.jar;. *.java

:: java -ea -cp pa2-120311.jar;. main OS pa1-empty.dsk
:: java -ea -cp pa2-120311.jar;. main OS pa1-solo.dsk
:: java -ea -cp pa2-120311.jar;. main OS pa1-bad.dsk
java -ea -cp pa2-120311.jar;. main OS pa1-execs.dsk
:: java -ea -cp pa2-120311.jar;. main OS pa1-dcs.dsk
:: java -ea -cp pa2-120311.jar;. main OS pa1-ydcs.dsk
:: java -ea -cp pa2-120311.jar;. main OS pa1-tree.dsk
:: java -ea -cp pa2-120311.jar;. main OS pa1-ytree.dsk

:: java -ea -cp pa2-120311.jar;. main OS pa2-diskio.dsk 
:: java -ea -cp pa2-120311.jar;. main OS pa2-3dio.dsk
:: java -ea -cp pa2-120311.jar;. main OS pa2-bad.dsk pa2-bad.tty
:: java -ea -cp pa2-120311.jar;. main OS pa2-ttycopy.dsk pa2-ttycopy.tty

pause

