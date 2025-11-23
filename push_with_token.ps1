# PowerShell script to push to GitHub using a token
# Usage: .\push_with_token.ps1 <your_github_token>

param(
    [Parameter(Mandatory=$true)]
    [string]$Token
)

$RemoteUrl = "https://${Token}@github.com/fabricioguidine/performance-analysis-4-ds-and-algo.git"

Write-Host "Setting remote URL with token..."
git remote set-url origin $RemoteUrl

Write-Host "Pushing to origin/master..."
git push origin master

Write-Host "Push complete!"

