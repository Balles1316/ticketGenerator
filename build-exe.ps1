param(
    [string]$JdkBin = "C:\Program Files\Java\jdk-21.0.11\bin",
    [string]$Name = "TicketGenerator"
)

$ErrorActionPreference = "Stop"
$root = Split-Path -Parent $MyInvocation.MyCommand.Path
Set-Location $root

$buildOut = Join-Path $root "build_out"
$fatJarBuild = Join-Path $root "fatjar_build"
$distInput = Join-Path $root "dist_input"
$dist = Join-Path $root "dist"
$appJar = Join-Path $root "app.jar"
$manifest = Join-Path $root "manifest.txt"

foreach ($dir in @($buildOut, $fatJarBuild, $distInput, $dist)) {
    if (Test-Path $dir) { Remove-Item -Recurse -Force $dir }
}
New-Item -ItemType Directory -Force -Path $buildOut, $fatJarBuild, $distInput | Out-Null

Write-Host "Compilando fuentes..."
$sources = Get-ChildItem -Recurse -Filter *.java -Path (Join-Path $root "src") | ForEach-Object { $_.FullName }
& "$JdkBin\javac.exe" -d $buildOut -cp "$root\jar\*" -sourcepath (Join-Path $root "src") -encoding UTF-8 $sources
if ($LASTEXITCODE -ne 0) { throw "Fallo la compilacion" }

Write-Host "Empaquetando dependencias en un jar ejecutable..."
Copy-Item -Recurse "$buildOut\*" $fatJarBuild

Push-Location $fatJarBuild
try {
    foreach ($jar in Get-ChildItem "$root\jar\*.jar") {
        & "$JdkBin\jar.exe" xf $jar.FullName
    }
    Remove-Item -Recurse -Force "META-INF\MANIFEST.MF", "META-INF\*.SF", "META-INF\*.DSA", "META-INF\*.RSA", "META-INF\versions", "META-INF\maven" -ErrorAction SilentlyContinue
} finally {
    Pop-Location
}

"Main-Class: Main.Main" | Out-File -Encoding ascii $manifest
if (Test-Path $appJar) { Remove-Item $appJar }
& "$JdkBin\jar.exe" cfm $appJar $manifest -C $fatJarBuild .
if ($LASTEXITCODE -ne 0) { throw "Fallo la creacion del jar" }

Copy-Item $appJar $distInput

Write-Host "Generando el ejecutable con jpackage..."
& "$JdkBin\jpackage.exe" `
    --type app-image `
    --input $distInput `
    --dest $dist `
    --name $Name `
    --main-jar app.jar `
    --main-class Main.Main `
    --app-version 1.0.0 `
    --vendor "Parabeus" `
    --description "Parabeus Ticket Generator"
if ($LASTEXITCODE -ne 0) { throw "Fallo jpackage" }

Copy-Item (Join-Path $root "PARABEUS") (Join-Path $dist "$Name\PARABEUS")

Remove-Item -Recurse -Force $buildOut, $fatJarBuild, $distInput, $appJar, $manifest

Write-Host "Listo: dist\$Name\$Name.exe"
