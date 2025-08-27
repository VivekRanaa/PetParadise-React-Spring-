@echo off
echo Starting Pet Paradise Backend Server...
echo Checking Java installation...
java -version
if %errorlevel% neq 0 (
    echo ERROR: Java is not installed or not in PATH!
    echo Please install Java 17 or higher from: https://adoptium.net/
    pause
    exit /b 1
)
echo.
echo Navigating to backend directory...
cd backend
echo.
echo Starting Spring Boot application with Maven Wrapper...
echo This may take a few minutes for the first run...
echo.
.\mvnw.cmd clean spring-boot:run
pause
