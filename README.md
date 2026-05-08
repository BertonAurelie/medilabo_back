# Medilabo

## Project Setup

### Build and Run the Application

Run the following commands to build and start the project:

```bash
docker compose build
docker compose up
```

The application will be available at:

```text
http://127.0.0.1/
```

---

## Login Information

To access the patient list page, use the following credentials:

```text
Username: orga
Password: orga
```

---

## Docker Compose Compatibility Issue

Some recent Docker Compose versions may cause issues with HTTP path handling because of Bake support.

If the project does not start correctly, install Docker Compose version `2.30.3`.

### Install Docker Compose 2.30.3 (Windows PowerShell)

```powershell
mkdir "$env:USERPROFILE\.docker\cli-plugins" -Force

Invoke-WebRequest `
 -Uri "https://github.com/docker/compose/releases/download/v2.30.3/docker-compose-windows-x86_64.exe" `
 -OutFile "$env:USERPROFILE\.docker\cli-plugins\docker-compose.exe"
```

### Remove the Installed Docker Compose Version

```powershell
Remove-Item "$env:USERPROFILE\.docker\cli-plugins\docker-compose.exe"
```
