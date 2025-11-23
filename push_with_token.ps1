# push_with_token.ps1
# Secure script to push to GitHub using a token
# This script securely reads your token and pushes to the repository
# Usage: .\push_with_token.ps1

param(
    [Parameter(Mandatory=$true)]
    [SecureString]$Token
)

# Convert secure string to plain text (only in memory)
$BSTR = [System.Runtime.InteropServices.Marshal]::SecureStringToBSTR($Token)
$plainToken = [System.Runtime.InteropServices.Marshal]::PtrToStringAuto($BSTR)

try {
    $remoteUrl = "https://${plainToken}@github.com/fabricioguidine/ed2commarcelo.git"
    
    Write-Host "Pushing to GitHub..." -ForegroundColor Green
    Write-Host "Repository: fabricioguidine/ed2commarcelo" -ForegroundColor Cyan
    
    # Show commits that will be pushed
    $commitCount = (git log --oneline origin/master..master | Measure-Object -Line).Lines
    Write-Host "Commits to push: $commitCount" -ForegroundColor Yellow
    
    if ($commitCount -gt 0) {
        Write-Host "`nRecent commits:" -ForegroundColor Yellow
        git log --oneline origin/master..master | Select-Object -First 5
        
        Write-Host "`nPushing..." -ForegroundColor Green
        git push $remoteUrl master
        
        if ($LASTEXITCODE -eq 0) {
            Write-Host "`n✓ Push completed successfully!" -ForegroundColor Green
        } else {
            Write-Host "`n✗ Push failed. Please check your token and try again." -ForegroundColor Red
            exit 1
        }
    } else {
        Write-Host "No commits to push. Everything is up to date." -ForegroundColor Yellow
    }
}
finally {
    # Clear the token from memory immediately
    [System.Runtime.InteropServices.Marshal]::ZeroFreeBSTR($BSTR)
    $plainToken = $null
    [System.GC]::Collect()
}

