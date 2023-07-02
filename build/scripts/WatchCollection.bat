@rem
@rem Copyright 2015 the original author or authors.
@rem
@rem Licensed under the Apache License, Version 2.0 (the "License");
@rem you may not use this file except in compliance with the License.
@rem You may obtain a copy of the License at
@rem
@rem      https://www.apache.org/licenses/LICENSE-2.0
@rem
@rem Unless required by applicable law or agreed to in writing, software
@rem distributed under the License is distributed on an "AS IS" BASIS,
@rem WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
@rem See the License for the specific language governing permissions and
@rem limitations under the License.
@rem

@if "%DEBUG%"=="" @echo off
@rem ##########################################################################
@rem
@rem  WatchCollection startup script for Windows
@rem
@rem ##########################################################################

@rem Set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" setlocal

set DIRNAME=%~dp0
if "%DIRNAME%"=="" set DIRNAME=.
@rem This is normally unused
set APP_BASE_NAME=%~n0
set APP_HOME=%DIRNAME%..

@rem Resolve any "." and ".." in APP_HOME to make it shorter.
for %%i in ("%APP_HOME%") do set APP_HOME=%%~fi

@rem Add default JVM options here. You can also use JAVA_OPTS and WATCH_COLLECTION_OPTS to pass JVM options to this script.
set DEFAULT_JVM_OPTS=

@rem Find java.exe
if defined JAVA_HOME goto findJavaFromJavaHome

set JAVA_EXE=java.exe
%JAVA_EXE% -version >NUL 2>&1
if %ERRORLEVEL% equ 0 goto execute

echo.
echo ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:findJavaFromJavaHome
set JAVA_HOME=%JAVA_HOME:"=%
set JAVA_EXE=%JAVA_HOME%/bin/java.exe

if exist "%JAVA_EXE%" goto execute

echo.
echo ERROR: JAVA_HOME is set to an invalid directory: %JAVA_HOME%
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:execute
@rem Setup the command line

set CLASSPATH=%APP_HOME%\lib\WatchCollection-1.0-SNAPSHOT.jar;%APP_HOME%\lib\javafx-fxml-20-win.jar;%APP_HOME%\lib\javafx-controls-20-win.jar;%APP_HOME%\lib\javafx-controls-20.jar;%APP_HOME%\lib\javafx-graphics-20-win.jar;%APP_HOME%\lib\javafx-graphics-20.jar;%APP_HOME%\lib\javafx-base-20-win.jar;%APP_HOME%\lib\javafx-base-20.jar;%APP_HOME%\lib\slf4j-simple-1.7.25.jar;%APP_HOME%\lib\openllet-jena-2.6.5.jar;%APP_HOME%\lib\jena-arq-4.8.0.jar;%APP_HOME%\lib\openllet-owlapi-2.6.5.jar;%APP_HOME%\lib\owlapi-distribution-5.5.0.jar;%APP_HOME%\lib\owlapi-compatibility-5.5.0.jar;%APP_HOME%\lib\owlapi-apibinding-5.5.0.jar;%APP_HOME%\lib\owlapi-rio-5.5.0.jar;%APP_HOME%\lib\rdf4j-rio-jsonld-4.2.0.jar;%APP_HOME%\lib\rdf4j-rio-binary-4.2.0.jar;%APP_HOME%\lib\rdf4j-rio-n3-4.2.0.jar;%APP_HOME%\lib\rdf4j-rio-nquads-4.2.0.jar;%APP_HOME%\lib\rdf4j-rio-ntriples-4.2.0.jar;%APP_HOME%\lib\rdf4j-rio-rdfjson-4.2.0.jar;%APP_HOME%\lib\rdf4j-rio-rdfxml-4.2.0.jar;%APP_HOME%\lib\rdf4j-rio-trix-4.2.0.jar;%APP_HOME%\lib\rdf4j-rio-trig-4.2.0.jar;%APP_HOME%\lib\rdf4j-rio-turtle-4.2.0.jar;%APP_HOME%\lib\rdf4j-rio-languages-4.2.0.jar;%APP_HOME%\lib\rdf4j-rio-datatypes-4.2.0.jar;%APP_HOME%\lib\rdf4j-rio-hdt-4.2.0.jar;%APP_HOME%\lib\rdf4j-rio-api-4.2.0.jar;%APP_HOME%\lib\jsonld-java-0.13.4.jar;%APP_HOME%\lib\jcl-over-slf4j-1.7.36.jar;%APP_HOME%\lib\jena-core-4.8.0.jar;%APP_HOME%\lib\jena-base-4.8.0.jar;%APP_HOME%\lib\openllet-query-2.6.5.jar;%APP_HOME%\lib\openllet-core-2.6.5.jar;%APP_HOME%\lib\openllet-functions-2.6.5.jar;%APP_HOME%\lib\rdf4j-model-4.2.0.jar;%APP_HOME%\lib\rdf4j-common-io-4.2.0.jar;%APP_HOME%\lib\rdf4j-common-util-4.2.0.jar;%APP_HOME%\lib\rdf4j-common-text-4.2.0.jar;%APP_HOME%\lib\rdf4j-common-iterator-4.2.0.jar;%APP_HOME%\lib\rdf4j-common-xml-4.2.0.jar;%APP_HOME%\lib\owlapi-impl-5.5.0.jar;%APP_HOME%\lib\owlapi-oboformat-5.5.0.jar;%APP_HOME%\lib\owlapi-parsers-5.5.0.jar;%APP_HOME%\lib\owlapi-tools-5.5.0.jar;%APP_HOME%\lib\owlapi-api-5.5.0.jar;%APP_HOME%\lib\slf4j-api-1.7.36.jar;%APP_HOME%\lib\gson-2.10.1.jar;%APP_HOME%\lib\httpclient-osgi-4.5.13.jar;%APP_HOME%\lib\httpclient-cache-4.5.14.jar;%APP_HOME%\lib\httpmime-4.5.13.jar;%APP_HOME%\lib\fluent-hc-4.5.13.jar;%APP_HOME%\lib\httpclient-4.5.14.jar;%APP_HOME%\lib\jackson-annotations-2.14.2.jar;%APP_HOME%\lib\jackson-databind-2.14.2.jar;%APP_HOME%\lib\jackson-core-2.14.2.jar;%APP_HOME%\lib\titanium-json-ld-1.3.2.jar;%APP_HOME%\lib\jakarta.json-2.0.1.jar;%APP_HOME%\lib\protobuf-java-3.22.2.jar;%APP_HOME%\lib\libthrift-0.18.1.jar;%APP_HOME%\lib\jgrapht-ext-1.1.0.jar;%APP_HOME%\lib\jgrapht-io-1.1.0.jar;%APP_HOME%\lib\commons-lang3-3.12.0.jar;%APP_HOME%\lib\antlr-runtime-3.5.3.jar;%APP_HOME%\lib\jaxb-api-2.3.0.jar;%APP_HOME%\lib\jena-iri-4.8.0.jar;%APP_HOME%\lib\commons-cli-1.5.0.jar;%APP_HOME%\lib\httpcore-osgi-4.4.14.jar;%APP_HOME%\lib\httpcore-nio-4.4.14.jar;%APP_HOME%\lib\httpcore-4.4.16.jar;%APP_HOME%\lib\commons-rdf-api-0.5.0.jar;%APP_HOME%\lib\xz-1.9.jar;%APP_HOME%\lib\rdf4j-model-vocabulary-4.2.0.jar;%APP_HOME%\lib\rdf4j-common-exception-4.2.0.jar;%APP_HOME%\lib\rdf4j-model-api-4.2.0.jar;%APP_HOME%\lib\hppcrt-0.7.5.jar;%APP_HOME%\lib\caffeine-3.1.1.jar;%APP_HOME%\lib\guava-31.1-jre.jar;%APP_HOME%\lib\jsr305-3.0.2.jar;%APP_HOME%\lib\commons-io-2.11.0.jar;%APP_HOME%\lib\jena-shaded-guava-4.8.0.jar;%APP_HOME%\lib\commons-csv-1.10.0.jar;%APP_HOME%\lib\commons-codec-1.15.jar;%APP_HOME%\lib\commons-compress-1.23.0.jar;%APP_HOME%\lib\collection-0.7.jar;%APP_HOME%\lib\jgrapht-core-1.1.0.jar;%APP_HOME%\lib\jgraphx-2.0.0.1.jar;%APP_HOME%\lib\jgraph-5.13.0.0.jar;%APP_HOME%\lib\rdf4j-common-annotation-4.2.0.jar;%APP_HOME%\lib\checker-qual-3.22.0.jar;%APP_HOME%\lib\error_prone_annotations-2.14.0.jar;%APP_HOME%\lib\failureaccess-1.0.1.jar;%APP_HOME%\lib\listenablefuture-9999.0-empty-to-avoid-conflict-with-guava.jar;%APP_HOME%\lib\j2objc-annotations-1.3.jar;%APP_HOME%\lib\antlr4-runtime-4.6.jar;%APP_HOME%\lib\jakarta.xml.bind-api-2.3.3.jar;%APP_HOME%\lib\javax.inject-1.jar;%APP_HOME%\lib\jakarta.activation-api-1.2.2.jar


@rem Execute WatchCollection
"%JAVA_EXE%" %DEFAULT_JVM_OPTS% %JAVA_OPTS% %WATCH_COLLECTION_OPTS%  -classpath "%CLASSPATH%" it.unicam.mgc.watchcollection.App %*

:end
@rem End local scope for the variables with windows NT shell
if %ERRORLEVEL% equ 0 goto mainEnd

:fail
rem Set variable WATCH_COLLECTION_EXIT_CONSOLE if you need the _script_ return code instead of
rem the _cmd.exe /c_ return code!
set EXIT_CODE=%ERRORLEVEL%
if %EXIT_CODE% equ 0 set EXIT_CODE=1
if not ""=="%WATCH_COLLECTION_EXIT_CONSOLE%" exit %EXIT_CODE%
exit /b %EXIT_CODE%

:mainEnd
if "%OS%"=="Windows_NT" endlocal

:omega
