@ECHO OFF
title �����sango3��
SET APP=nettyStudy

rem JDK�汾
echo [INFO] ------------------------------------------------------------------------
echo [INFO] JDK�汾��
echo [INFO] ------------------------------------------------------------------------
echo.
java -version
echo.

echo [INFO] ɾ���ɰ��ļ���·����%CD%\dist\%APP%
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