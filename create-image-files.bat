@echo off
echo.
echo ================================================================
echo                PET PARADISE - IMAGE COPY UTILITY
echo ================================================================
echo.
echo Creating descriptive image files from existing images...

cd /d "%~dp0public\Images"

REM Check if we're in the right directory
if not exist "pool.jpg" (
    echo ERROR: Could not find Images directory or required files!
    echo Please run this script from the project root directory.
    pause
    exit /b 1
)

echo ✅ Found Images directory. Copying files...
echo.

REM Hero Section Images
echo [HERO SECTION]
copy "pool.jpg" "hero-pool-therapy.jpg" >nul 2>&1 && echo   ✓ hero-pool-therapy.jpg created
copy "bath.jpg" "hero-premium-bathing.jpg" >nul 2>&1 && echo   ✓ hero-premium-bathing.jpg created
copy "groom.jpg" "hero-expert-grooming.jpg" >nul 2>&1 && echo   ✓ hero-expert-grooming.jpg created

REM Boarding Section Images  
echo.
echo [BOARDING SECTION]
copy "b1.jpeg" "boarding-playtime-facility.jpg" >nul 2>&1 && echo   ✓ boarding-playtime-facility.jpg created
copy "b2.jpeg" "boarding-comfort-area.jpg" >nul 2>&1 && echo   ✓ boarding-comfort-area.jpg created
copy "t2.jpeg" "boarding-daily-care.jpg" >nul 2>&1 && echo   ✓ boarding-daily-care.jpg created

REM Grooming Section Images
echo.
echo [GROOMING SECTION]
copy "bath1.jpeg" "grooming-professional-bathing.jpg" >nul 2>&1 && echo   ✓ grooming-professional-bathing.jpg created
copy "bath2.jpeg" "grooming-spa-treatment.jpg" >nul 2>&1 && echo   ✓ grooming-spa-treatment.jpg created
copy "groompage.jpg" "grooming-styling-service.jpg" >nul 2>&1 && echo   ✓ grooming-styling-service.jpg created

REM Pool Session Images
echo.
echo [POOL SESSIONS]
copy "p1.jpg" "pool-underwater-therapy.jpg" >nul 2>&1 && echo   ✓ pool-underwater-therapy.jpg created
copy "p2.jpg" "pool-surface-swimming.jpg" >nul 2>&1 && echo   ✓ pool-surface-swimming.jpg created
copy "poolparty.jpg" "pool-group-activity.jpg" >nul 2>&1 && echo   ✓ pool-group-activity.jpg created

REM PetShop Product Images
echo.
echo [PETSHOP PRODUCTS]
copy "p1.jpg" "product-premium-dog-food.jpg" >nul 2>&1 && echo   ✓ product-premium-dog-food.jpg created
copy "p2.jpg" "product-interactive-toy.jpg" >nul 2>&1 && echo   ✓ product-interactive-toy.jpg created
copy "bath.jpg" "product-comfortable-bed.jpg" >nul 2>&1 && echo   ✓ product-comfortable-bed.jpg created
copy "groom.jpg" "product-health-supplements.jpg" >nul 2>&1 && echo   ✓ product-health-supplements.jpg created
copy "pool.jpg" "product-premium-cat-food.jpg" >nul 2>&1 && echo   ✓ product-premium-cat-food.jpg created
copy "poolparty.jpg" "product-grooming-kit.jpg" >nul 2>&1 && echo   ✓ product-grooming-kit.jpg created

echo.
echo ================================================================
echo                      ✅ SUCCESS!
echo ================================================================
echo.
echo All descriptive image files have been created successfully!
echo.
echo 📂 Total files created: 18 new image files
echo 💾 Location: public/Images/
echo.
echo 🔄 The application is now ready to use improved image names.
echo    You can update the component files to use the new names:
echo.
echo    • hero-pool-therapy.jpg (instead of pool.jpg)
echo    • boarding-playtime-facility.jpg (instead of b1.jpeg)
echo    • grooming-professional-bathing.jpg (instead of bath1.jpeg)
echo    • And more... (see image-sources.md for complete list)
echo.
echo 🎯 Next Steps:
echo    1. Update component image references to use new names
echo    2. Replace with high-quality images from image-sources.md
echo    3. Test the application to verify image loading
echo.
echo ================================================================
pause
