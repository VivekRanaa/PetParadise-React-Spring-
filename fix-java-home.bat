@echo off
echo.
echo ================================================================
echo    🔧 Fixing JAVA_HOME and PATH Variables 🔧
echo ================================================================
echo.

set "JAVA_DIR=C:\Program Files\Java\jdk-17"
set "JAVA_BIN=%JAVA_DIR%\bin"

echo 🔍 Checking if Java directory exists...
if not exist "%JAVA_DIR%\bin\java.exe" (
    echo ❌ Java not found at: %JAVA_DIR%
    echo.
    echo Please check if Java is installed at this location:
    dir "C:\Program Files\Java\" 2>nul
    echo.
    echo If you see a different folder name (like jdk-17.0.8), 
    echo please update this script with the correct path.
    pause
    exit /b 1
)

echo ✅ Java found at: %JAVA_DIR%
echo.

echo 🔧 Setting JAVA_HOME environment variable...
setx JAVA_HOME "%JAVA_DIR%" /M 2>nul
if %errorlevel% neq 0 (
    echo ⚠️  Admin rights needed for system variables. Setting user variable...
    setx JAVA_HOME "%JAVA_DIR%" 2>nul
)

echo 🔧 Setting PATH to include Java...
REM Get current PATH
for /f "tokens=2*" %%a in ('reg query "HKLM\System\CurrentControlSet\Control\Session Manager\Environment" /v PATH 2^>nul') do set "CURRENT_PATH=%%b"

REM Check if Java bin is already in PATH
echo %CURRENT_PATH% | findstr /i "%JAVA_BIN%" >nul
if %errorlevel% equ 0 (
    echo ✅ Java bin already in PATH
) else (
    echo 🔧 Adding Java bin to PATH...
    setx PATH "%JAVA_BIN%;%CURRENT_PATH%" /M 2>nul
    if %errorlevel% neq 0 (
        echo ⚠️  Admin rights needed. Adding to user PATH...
        setx PATH "%JAVA_BIN%;%PATH%" 2>nul
    )
)

echo.
echo 🔄 Setting variables for current session...
set "JAVA_HOME=%JAVA_DIR%"
set "PATH=%JAVA_BIN%;%PATH%"

echo.
echo 🧪 Testing Java configuration...
echo JAVA_HOME = %JAVA_HOME%
echo.

java -version
if %errorlevel% equ 0 (
    echo.
    echo 🎉 SUCCESS! Java is now properly configured!
    echo.
    echo 🚀 Starting Pet Paradise Backend...
    echo.
    
    cd /d "e:\Personal Projects\PetParadise\PetParadise-React-Spring-\backend"
    echo 📂 Current directory: %CD%
    echo.
    
    echo 🔧 Starting Spring Boot with Maven Wrapper...
    .\mvnw.cmd clean spring-boot:run
    
) else (
    echo.
    echo ⚠️  Java configuration needs a restart.
    echo.
    echo Please:
    echo 1. Restart your computer
    echo 2. Open a new command prompt
    echo 3. Run: java -version
    echo 4. If working, run: .\start-backend.bat
    echo.
)

pause
