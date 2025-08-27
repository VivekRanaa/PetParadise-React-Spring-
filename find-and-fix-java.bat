@echo off
setlocal enabledelayedexpansion
echo.
echo ================================================================
echo    🔍 Finding Your Newly Installed Java 🔍
echo ================================================================
echo.

echo 🔎 Searching all possible Java locations...
echo.

REM Extended search paths for Java installations
set "SEARCH_PATHS=C:\Program Files\Eclipse Adoptium;C:\Program Files\Java;C:\Program Files\OpenJDK;C:\Program Files (x86)\Eclipse Adoptium;C:\Program Files (x86)\Java;C:\Program Files (x86)\OpenJDK;C:\Program Files\Microsoft\jdk;C:\Program Files\Amazon Corretto;C:\Program Files\Zulu;C:\Users\%USERNAME%\AppData\Local\Programs\Eclipse Adoptium"

set "FOUND_JAVA="
set "FOUND_COUNT=0"

for %%p in (%SEARCH_PATHS%) do (
    if exist "%%p" (
        echo 📂 Checking: %%p
        for /d %%d in ("%%p\*") do (
            if exist "%%d\bin\java.exe" (
                echo    ✅ Found Java at: %%d\bin
                if "!FOUND_JAVA!"=="" set "FOUND_JAVA=%%d\bin"
                set /a FOUND_COUNT+=1
            )
        )
    )
)

REM Also check registry for Java installations
echo.
echo 🔍 Checking Windows Registry for Java...

for /f "tokens=2*" %%a in ('reg query "HKLM\SOFTWARE\Eclipse Adoptium" /s /f "Path" 2^>nul ^| findstr "REG_SZ"') do (
    if exist "%%b\bin\java.exe" (
        echo    ✅ Found Java in Registry: %%b\bin
        if "%FOUND_JAVA%"=="" set "FOUND_JAVA=%%b\bin"
        set /a FOUND_COUNT+=1
    )
)

REM Check common 64-bit registry paths
for /f "tokens=2*" %%a in ('reg query "HKLM\SOFTWARE\JavaSoft\JDK" /s /f "JavaHome" 2^>nul ^| findstr "REG_SZ"') do (
    if exist "%%b\bin\java.exe" (
        echo    ✅ Found Java in Registry: %%b\bin
        if "%FOUND_JAVA%"=="" set "FOUND_JAVA=%%b\bin"
        set /a FOUND_COUNT+=1
    )
)

echo.
echo 📊 Search Results:
if %FOUND_COUNT% equ 0 (
    echo ❌ No Java installations found.
    echo.
    echo Let's try a different approach...
    echo.
    echo 🔍 Manual search - please check these locations:
    echo 1. Open File Explorer
    echo 2. Navigate to C:\Program Files\
    echo 3. Look for folders containing "java", "jdk", "adoptium", or "eclipse"
    echo 4. Inside any Java folder, look for a "bin" subfolder
    echo 5. If you find java.exe in the bin folder, that's your Java path!
    echo.
    echo Common folder names to look for:
    echo - Eclipse Adoptium
    echo - Java
    echo - OpenJDK
    echo - jdk-17.*
    echo.
    pause
    exit /b 1
) else (
    echo ✅ Found %FOUND_COUNT% Java installation(s)
    echo 🎯 Using: %FOUND_JAVA%
)

echo.
echo 🔧 Adding Java to PATH...

REM Add to PATH for current session
set "PATH=%FOUND_JAVA%;%PATH%"

echo 💾 Adding to system PATH permanently...
REM Try system-wide first
setx PATH "%FOUND_JAVA%;%PATH%" /M 2>nul
if %errorlevel% neq 0 (
    echo ⚠️ Admin rights needed for system PATH. Adding to user PATH...
    setx PATH "%FOUND_JAVA%;%PATH%" 2>nul
)

echo.
echo 🧪 Testing Java...
java -version
if %errorlevel% equ 0 (
    echo.
    echo 🎉 SUCCESS! Java is now working!
    echo.
    echo 🚀 Starting Pet Paradise Backend...
    echo.
    
    cd /d "e:\Personal Projects\PetParadise\PetParadise-React-Spring-\backend"
    echo 📂 Current directory: %CD%
    echo.
    echo 🔧 Starting Spring Boot application...
    .\mvnw.cmd clean spring-boot:run
) else (
    echo.
    echo ⚠️ Java still not accessible. Manual PATH setup needed.
    echo.
    echo Please do the following:
    echo 1. Copy this path: %FOUND_JAVA%
    echo 2. Right-click "This PC" → Properties → Advanced system settings
    echo 3. Click "Environment Variables"
    echo 4. Under "System variables", find "Path" and click "Edit"
    echo 5. Click "New" and paste: %FOUND_JAVA%
    echo 6. Click OK on all dialogs
    echo 7. Restart your command prompt
    echo.
)

pause
