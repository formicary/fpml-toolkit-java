RMDIR /Q /S dist
MKDIR dist
MKDIR dist\files
MKDIR dist\doc
XCOPY /S /I files dist\files
XCOPY /S /I doc dist\doc
CD bin
"C:\Program Files\Java\jdk1.6.0_01\bin\jar" cvf ..\dist\handcoded.jar *
CD ..
XCOPY /S /I lib dist\lib
XCOPY /S /I manual dist\manual
COPY misc\*.* dist
COPY license.txt dist
COPY readme.htm dist
pause