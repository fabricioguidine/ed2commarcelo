# How to Push to GitHub Using Token

## Secure Push Method (Recommended)

Use the `push_with_token.ps1` script which securely handles your token:

### Step 1: Run the script
```powershell
$token = Read-Host -AsSecureString -Prompt "Enter your GitHub token"
.\push_with_token.ps1 -Token $token
```

The script will:
- Securely read your token (masked input)
- Show how many commits will be pushed
- Push to the repository
- Immediately clear the token from memory

### Step 2: Generate a GitHub Token

If you don't have a token yet:

1. Go to GitHub → Settings
2. Developer settings → Personal access tokens → Tokens (classic)
3. Click "Generate new token (classic)"
4. Name it (e.g., "Cursor Push Token")
5. Select the `repo` scope (full control of private repositories)
6. Click "Generate token"
7. **Copy the token immediately** (you won't see it again!)

## Alternative Methods

### Method 1: Direct Push (Less Secure)
```powershell
git push https://YOUR_TOKEN@github.com/fabricioguidine/ed2commarcelo.git master
```

### Method 2: Using Environment Variable
```powershell
$env:GITHUB_TOKEN = "YOUR_TOKEN"
git push https://${env:GITHUB_TOKEN}@github.com/fabricioguidine/ed2commarcelo.git master
$env:GITHUB_TOKEN = $null  # Clear immediately
```

### Method 3: Simple Script (push.ps1)
```powershell
.\push.ps1 YOUR_TOKEN
```

## Security Notes

⚠️ **Important:**
- Never commit your token to the repository
- After using a token, consider revoking it and creating a new one if shared
- The secure script automatically clears the token from memory after use
- Use environment variables or secure string input when possible

## Current Status

To check what will be pushed:
```powershell
git log --oneline origin/master..master
```

