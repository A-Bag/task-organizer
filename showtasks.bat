call runcrud.bat
if "%ERRORLEVEL%" == "0" goto showtasks
echo.
echo runcrud.bat has errors - breaking work
goto fail

:showtasks
start "link" "http://localhost:7070/crud/v1/task/getTasks"
goto end

:fail
echo.
echo There were errors

:end
echo.
echo Work is finished.