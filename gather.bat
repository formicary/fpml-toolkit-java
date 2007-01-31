RMDIR /Q /S dist
MKDIR dist
MKDIR dist\files
MKDIR dist\doc
XCOPY /S /I files dist\files
XCOPY /S /I doc dist\doc
CD bin
C:\j2sdk1.4.2_09\bin\jar cvf ..\dist\handcoded.jar *
CD ..
XCOPY /S /I lib dist\lib
XCOPY /S /I manual dist\manual
ERASE dist\manual\manual.htm*
COPY misc\*.* dist
COPY license.txt dist
COPY readme.htm dist
pause