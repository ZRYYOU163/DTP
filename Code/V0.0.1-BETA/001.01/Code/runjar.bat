@echo off
color F0
del MANIFEST.MF
echo Manifest-Version: 1.0 >> MANIFEST.MF
echo Created-By: 1.8.0_251 (Oracle Corporation) >> MANIFEST.MF
echo Main-Class: %1 >> MANIFEST.MF
javac %1.java
jar cvfm %2.jar MANIFEST.MF %1.class 
del MANIFEST.MF
@echo on
java -jar %2.jar
pause