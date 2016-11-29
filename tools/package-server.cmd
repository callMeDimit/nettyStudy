@ECHO OFF
title 【打包sango3】
SET APP=nettyStudy

rem JDK版本
echo [INFO] ------------------------------------------------------------------------
echo [INFO] JDK版本号
echo [INFO] ------------------------------------------------------------------------
echo.
java -version
echo.

echo [INFO] 删除旧版文件，路径：%CD%\dist\%APP%
rmdir /s/q %CD%\dist\%APP%
echo.
CD %APP%

call mvn clean
call mvn -Dmaven.test.skip=true -e package

echo.
pause
echo.

xcopy /y/i/s target\resources ..\dist\%APP%\resource
xcopy /y/i target\*.jar ..\dist\%APP%
xcopy /y/i target\lib ..\dist\%APP%\lib

echo.
pause