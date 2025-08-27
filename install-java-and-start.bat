@echo off
echo.
echo ================================================================
echo    🐾 Pet Paradise - Quick Java Setup 🐾
echo ================================================================
echo.

REM Check if Java is already installed
java -version >nul 2>&1
if %errorlevel% equ 0 (
    echo ✅ Java is already installed!
    java -version
    echo.
    echo 🚀 Starting backend server...
    goto startserver
)

echo ❌ Java not found. Let's install it quickly!
echo.
echo 📥 Downloading and installing Java 17 automatically...
echo This will download and install Eclipse Temurin OpenJDK 17...
echo.

REM Create temp directory
if not exist "%TEMP%\petparadise" mkdir "%TEMP%\petparadise"
cd /d "%TEMP%\petparadise"

echo 🌐 Downloading Java 17 installer...
powershell -Command "& {Invoke-WebRequest -Uri 'https://github.com/adoptium/temurin17-binaries/releases/download/jdk-17.0.8.1%%2B1/OpenJDK17U-jdk_x64_windows_hotspot_17.0.8.1_1.msi' -OutFile 'java17.msi'}"

if not exist "java17.msi" (
    echo ❌ Failed to download Java installer.
    echo Please manually download and install Java 17 from: https://adoptium.net/
    pause
    exit /b 1
)

echo 🔧 Installing Java 17...
echo This may take a few minutes...
msiexec /i java17.msi /quiet /norestart

echo ⏳ Waiting for installation to complete...
timeout /t 30 /nobreak >nul

echo 🔄 Refreshing environment variables...
REM Refresh PATH for current session
for /f "tokens=2*" %%a in ('reg query "HKLM\System\CurrentControlSet\Control\Session Manager\Environment" /v PATH') do set "newpath=%%b"
set "PATH=%newpath%"

REM Check if Java is now available
java -version >nul 2>&1
if %errorlevel% neq 0 (
    echo ⚠️  Java installation completed but not yet in PATH.
    echo Please restart your command prompt and run this script again.
    echo Or manually add Java to your PATH.
    pause
    exit /b 1
)

echo ✅ Java installed successfully!
java -version

:startserver
echo.
echo 🚀 Starting Pet Paradise Backend Server...
echo 📂 Navigating to backend directory...

cd /d "e:\Personal Projects\PetParadise\PetParadise-React-Spring-\backend"

echo 🔧 Using Maven Wrapper to start server...
echo This may take a few minutes for the first run...
echo.

.\mvnw.cmd clean spring-boot:run

echo.
echo 🎉 Server should now be running!
echo Frontend can now submit forms to: http://localhost:8080/api/v1/Forms
pause
