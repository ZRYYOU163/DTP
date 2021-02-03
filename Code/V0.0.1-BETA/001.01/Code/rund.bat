@echo off
color F0
javac %1.java
@echo on
java %1 -d %2
pause