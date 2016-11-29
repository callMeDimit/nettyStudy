@ECHO OFF & SETLOCAL EnableDelayedExpansion
title ¡¾GAME jettyStudy-0.0.1-SNAPSHOT¡¿
SET APP=nettyTest
cd %APP%
SET CP=%CD%\resource;%CD%\jettyStudy-0.0.1-SNAPSHOT.jar
FOR /R lib %%a IN (*.jar) DO (
	SET CP=!CP!;%%a
)
REM echo %CP%
java -Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address="9898" -cp %CP% com.dimit.netty.time.client.TimeClient
PAUSE