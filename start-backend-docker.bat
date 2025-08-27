@echo off
echo.
echo ================================================================
echo    🐾 Pet Paradise Backend - Docker Setup 🐾
echo ================================================================
echo    Starting ONLY the backend with Docker...
echo    This will automatically install and run:
echo    - Java 17 + Maven
echo    - H2 Database
echo    - All backend dependencies
echo.
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
    pause
    exit /b 1
)

echo ✅ Docker is installed and running!
echo.

echo 🔨 Building backend Docker image...
cd backend
docker build -t petparadise-backend .

if %errorlevel% neq 0 (
    echo ❌ Failed to build Docker image!
    pause
    exit /b 1
)

echo.
echo 🚀 Starting Pet Paradise Backend...
docker run -p 8080:8080 --name petparadise-backend-container petparadise-backend

echo.
echo 🎉 Pet Paradise Backend is now running!
echo Backend: http://localhost:8080/api/v1
echo.
pause
