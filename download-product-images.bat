@echo off
echo Downloading product images from Pexels...

echo Downloading dog food image...
powershell -Command "Invoke-WebRequest -Uri 'https://images.pexels.com/photos/8434641/pexels-photo-8434641.jpeg?cs=srgb&dl=pexels-mart-production-8434641.jpg&fm=jpg' -OutFile 'public\Images\product-dog-food.jpg'"

echo Downloading dog toy image...
powershell -Command "Invoke-WebRequest -Uri 'https://images.pexels.com/photos/1739093/pexels-photo-1739093.jpeg?cs=srgb&dl=pexels-joshsorenson-1739093.jpg&fm=jpg' -OutFile 'public\Images\product-dog-toy.jpg'"

echo Downloading pet bed image...
powershell -Command "Invoke-WebRequest -Uri 'https://images.pexels.com/photos/2467239/pexels-photo-2467239.jpeg?cs=srgb&dl=pexels-didsss-2467239.jpg&fm=jpg' -OutFile 'public\Images\product-pet-bed.jpg'"

echo Downloading supplements image...
powershell -Command "Invoke-WebRequest -Uri 'https://images.pexels.com/photos/7781984/pexels-photo-7781984.jpeg?cs=srgb&dl=pexels-rdne-7781984.jpg&fm=jpg' -OutFile 'public\Images\product-supplements.jpg'"

echo Downloading cat food image...
powershell -Command "Invoke-WebRequest -Uri 'https://images.pexels.com/photos/1846389/pexels-photo-1846389.jpeg?cs=srgb&dl=pexels-sheep-556180-1846389.jpg&fm=jpg' -OutFile 'public\Images\product-cat-food.jpg'"

echo Downloading grooming kit image...
powershell -Command "Invoke-WebRequest -Uri 'https://images.pexels.com/photos/3981763/pexels-photo-3981763.jpeg?cs=srgb&dl=pexels-thishanabee-3981763.jpg&fm=jpg' -OutFile 'public\Images\product-grooming-kit.jpg'"

echo All product images downloaded successfully!
echo Images are now available in public\Images\ directory
pause
