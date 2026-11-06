@echo off
setlocal
set "GRADLE_VERSION=9.2.0"
set "BOOTSTRAP_DIR=%USERPROFILE%\.gradle\bootstrap"
set "GRADLE_HOME=%BOOTSTRAP_DIR%\gradle-%GRADLE_VERSION%"
if not exist "%GRADLE_HOME%\bin\gradle.bat" (
  echo Gradle %GRADLE_VERSION% chua co. Dang tai ve...
  if not exist "%BOOTSTRAP_DIR%" mkdir "%BOOTSTRAP_DIR%"
  powershell -NoProfile -ExecutionPolicy Bypass -Command "$ProgressPreference='SilentlyContinue'; $zip=Join-Path $env:TEMP 'gradle-%GRADLE_VERSION%-bin.zip'; Invoke-WebRequest -Uri 'https://services.gradle.org/distributions/gradle-%GRADLE_VERSION%-bin.zip' -OutFile $zip; Expand-Archive -LiteralPath $zip -DestinationPath '%BOOTSTRAP_DIR%' -Force; Remove-Item $zip -Force"
  if errorlevel 1 (
    echo Khong tai duoc Gradle. Kiem tra ket noi Internet roi thu lai.
    exit /b 1
  )
)
call "%GRADLE_HOME%\bin\gradle.bat" %*
exit /b %ERRORLEVEL%
