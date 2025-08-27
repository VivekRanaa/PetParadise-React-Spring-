@echo off
echo.
echo ================================================================
echo    🐾 Java PATH Configuration Helper 🐾
echo ================================================================
echo.

echo 🔍 Searching for Java installation...

REM Common Java installation paths
set "JAVA_PATHS=C:\Program Files\Eclipse Adoptium;C:\Program Files\Java;C:\Program Files\OpenJDK;C:\Program Files (x86)\Eclipse Adoptium;C:\Program Files (x86)\Java;C:\Program Files (x86)\OpenJDK"

set "FOUND_JAVA="
for %%p in (%JAVA_PATHS%) do (
    if exist "%%p" (
        echo 📂 Checking: %%p
        for /d %%d in ("%%p\jdk*") do (
            if exist "%%d\bin\java.exe" (
                echo ✅ Found Java at: %%d\bin
                set "FOUND_JAVA=%%d\bin"
                goto found
            )
        )
    )
)

:found
if "%FOUND_JAVA%"=="" (
    echo ❌ Java installation not found in common locations.
    echo.
    echo Please manually locate your Java installation and add it to PATH:
    echo 1. Find your Java installation directory
    echo 2. Look for the 'bin' folder inside it
    echo 3. Add the full path to that 'bin' folder to your PATH
    echo.
    echo Common locations to check:
    echo - C:\Program Files\Eclipse Adoptium\jdk-*\bin
    echo - C:\Program Files\Java\jdk-*\bin
    echo - C:\Program Files\OpenJDK\jdk-*\bin
    echo.
    pause
    exit /b 1
)

echo.
echo 🎯 Java found at: %FOUND_JAVA%
echo.

REM Check if already in PATH
echo %PATH% | findstr /i "%FOUND_JAVA%" >nul
if %errorlevel% equ 0 (
    echo ✅ Java is already in your PATH!
    echo.
    echo Testing Java...
    java -version
    echo.
    echo 🚀 You can now run the backend server!
    echo Run: .\start-backend.bat
    pause
    exit /b 0
)

echo ⚙️ Java not in PATH. Adding it now...
echo.

REM Add to PATH for current session
set "PATH=%FOUND_JAVA%;%PATH%"

REM Add to system PATH permanently
echo 💾 Adding Java to system PATH permanently...
setx PATH "%FOUND_JAVA%;%PATH%" /M 2>nul
if %errorlevel% neq 0 (
    echo ⚠️ Could not add to system PATH (requires admin rights).
    echo Adding to user PATH instead...
    setx PATH "%FOUND_JAVA%;%PATH%" 2>nul
)

echo.
echo ✅ Java added to PATH!
echo.
echo 🧪 Testing Java installation...
java -version
if %errorlevel% equ 0 (
    echo.
    echo 🎉 SUCCESS! Java is now properly configured!
    echo.
    echo 🚀 Ready to start the backend server!
    echo You can now run: .\start-backend.bat
    echo.
    echo 📝 Note: If java -version doesn't work in other windows,
    echo         restart your command prompt or computer.
) else (
    echo.
    echo ⚠️ Java PATH configuration may need a restart.
    echo Please restart your command prompt and try again.
    echo.
    echo If problems persist:
    echo 1. Restart your computer
    echo 2. Open a new command prompt
    echo 3. Run: java -version
)

echo.
pause
