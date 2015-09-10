@echo off
cls
call env.bat
cd %PROJECT%
call mvn clean test
cmd /k ..\..\env.bat
