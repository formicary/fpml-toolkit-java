RMDIR /Q /S distweb
MKDIR distweb
MKDIR distweb\jar
MKDIR distweb\jar\files
MKDIR distweb\jar\files\data
MKDIR distweb\jar\files\schemas
MKDIR distweb\doc
MKDIR distweb\files\examples\fpml4-7
COPY files\*.* distweb\jar\files
XCOPY /S /I files\data distweb\jar\files\data
XCOPY /S /I files\schemas distweb\jar\files\schemas
XCOPY /S /I bin\*.* distweb\jar
XCOPY /S /I doc distweb\doc
XCOPY /S /I files\examples\fpml4-7 distweb\files\examples\fpml4-7
CD distweb\jar
"C:\Program Files\Java\jdk1.6.0_11\bin\jar" cvf ..\handcoded.jar *
CD ..\..
RMDIR /Q /S distweb\jar
XCOPY /S /I lib distweb\lib
XCOPY /S /I manual distweb\manual
COPY misc-web\*.* distweb
COPY license.txt distweb
COPY readme.htm distweb
pause