# 🚀 Application Spring Boot.

Application Spring Boot développée entièrement dans un conteneur Docker avec VS Code Dev Containers.
Aucune installation de Java ou PostgreSQL requise sur votre machine !

## 📋 Prérequis

- [Docker Desktop](https://www.docker.com/products/docker-desktop/) installé et en cours d'exécution
- [Visual Studio Code](https://code.visualstudio.com/) installé
- Extension VS Code : [Dev Containers](https://marketplace.visualstudio.com/items?itemName=ms-vscode-remote.remote-containers)

## 📦 Structure du projet

```
.
├── .devcontainer/
│   ├── devcontainer.json       # Configuration du Dev Container
│   ├── docker-compose.yml      # Services Docker (application + database)
│   └── Dockerfile              # Image Java avec Maven
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/demo/
│   │   │       ├── DemoApplication.java
│   │   │       ├── entity/
│   │   │       ├── repository/
│   │   │       └── controller/
│   │   └── resources/
│   │       └── application.yml  # Configuration Spring Boot
│   └── test/
├── pom.xml                      # Dépendances Maven
└── README.md
```

## 🚀 Démarrage rapide

### 1️⃣ Cloner le projet

```bash
git clone 
cd 
```

### 2️⃣ Ouvrir dans VS Code

```bash
code .
```

### 3️⃣ Lancer le Dev Container

VS Code détectera automatiquement la configuration Dev Container :

1. Une notification apparaît en bas à droite : **"Reopen in Container"**
2. Cliquez dessus (ou `F1` → `Dev Containers: Reopen in Container`)
3. Attendez que les conteneurs se construisent (3-5 minutes la première fois)

### 4️⃣ Lancer l'application

Une fois dans le conteneur, ouvrez un terminal (`Ctrl+Shift+ù`) :

```bash
# Lancer l'application
./mvnw spring-boot:run
```

Vous devriez voir :
```
  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::                (v4.0.2)

Started DemoApplication in 2.5 seconds
```

## Quand redémarrer manuellement ?
Seulement si vous ajoutez des dépendances :
``` bash
    # 1. Modifier pom.xml (ajouter une dépendance)
    # 2. Arrêter le serveur (Ctrl+C)
    # 3. Recharger les dépendances
    ./mvnw clean install
    # 4. Relancer
    ./mvnw spring-boot:run
```