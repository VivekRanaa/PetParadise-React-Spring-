@echo off
echo.
echo ================================================================
echo    🐾 Pet Paradise - Docker Setup 🐾
echo ================================================================
echo    Starting Pet Paradise with Docker...
echo    This will automatically install and run:
echo    - Java 17 + Maven (Backend)
echo    - Node.js + Yarn (Frontend) 
echo    - H2 Database
echo    - All dependencies
echo.
echo    Frontend: http://localhost:5173
echo    Backend API: http://localhost:8080/api/v1
echo    API Docs: http://localhost:8080/api/v1/swagger-ui.html
echo    Database: http://localhost:8080/api/v1/h2-console
echo ================================================================
echo.

REM Check if Docker is installed
docker --version >nul 2>&1
if %errorlevel% neq 0 (
    echo ❌ ERROR: Docker is not installed or not running!
    echo.
    echo Please install Docker Desktop from:
    echo https://www.docker.com/products/docker-desktop
    echo.
    echo After installation:
    echo 1. Start Docker Desktop
    echo 2. Wait for it to fully start
    echo 3. Run this script again
    echo.
    pause
    exit /b 1
)

echo ✅ Docker is installed and running!
echo.

REM Check if Docker Compose is available
docker compose version >nul 2>&1
if %errorlevel% neq 0 (
    echo ⚠️  Using legacy docker-compose command...
    docker-compose --version >nul 2>&1
    if %errorlevel% neq 0 (
        echo ❌ ERROR: Docker Compose is not available!
        echo Please make sure Docker Desktop is properly installed.
        pause
        exit /b 1
    )
    echo 🚀 Building and starting Pet Paradise...
    docker-compose up --build
) else (
    echo 🚀 Building and starting Pet Paradise...
    docker compose up --build
)

echo.
echo 🎉 Pet Paradise is now running!
echo Frontend: http://localhost:5173
echo Backend: http://localhost:8080/api/v1
echo.
pause
