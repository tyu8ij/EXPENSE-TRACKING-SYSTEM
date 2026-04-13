# STEP-BY-STEP: How to Run Everything

## 🎯 YOUR EXACT CHECKLIST TO RUN THE PROJECT

Complete these steps in order. Each section depends on the previous one.

---

## PART 1️⃣: INSTALL WHAT YOU NEED (5-10 mins)

### Check if you have Java
Open Command Prompt/PowerShell and type:
```
java -version
```

❌ **If you see "java is not recognized"** → Download Java 11:
https://adoptopenjdk.net/
- Click "LTS Release"
- Download and install
- Restart your computer
- Test: `java -version`

✅ **If you see "openjdk version 11" or higher** → You're good!

---

### Check if you have Maven
```
mvn -version
```

❌ **If you see "mvn is not recognized"**:
1. Download Maven: https://maven.apache.org/download.cgi
2. Extract somewhere (example: `C:\apache-maven-3.9.0`)
3. Add to PATH:
   - Right-click "This PC" → Properties
   - Advanced system settings → Environment Variables
   - New Variable: `MAVEN_HOME` = `C:\apache-maven-3.9.0`
   - Edit PATH, add: `%MAVEN_HOME%\bin`
   - Restart command prompt
4. Test: `mvn -version`

✅ **If you see "Apache Maven 3.6.x or higher"** → You're good!

---

### Check if you have Git
```
git --version
```

❌ **If not installed** → https://git-scm.com/download/win

✅ **If you see version number** → You're good!

---

### Check if you have Docker
```
docker --version
```

❌ **If not installed**:
1. Download Docker Desktop: https://www.docker.com/products/docker-desktop
2. Install and restart
3. Sign up for Docker Hub: https://hub.docker.com/signup
4. Test: `docker --version`

✅ **If you see Docker version** → You're good!

---

### Check if you have Kubernetes
```
kubectl version --client
```

❌ **If not installed** → Docker Desktop includes it!
- Open Docker Desktop
- Settings → Kubernetes
- Check "Enable Kubernetes"
- Wait 3-5 minutes
- Test: `kubectl cluster-info`

✅ **If you see Kubernetes info** → You're good!

---

## PART 2️⃣: NAVIGATE TO YOUR PROJECT (1 min)

Open Command Prompt and navigate to the project:
```
cd c:\Users\heman\Downloads\agile fat\expense-tracking-system
```

Verify you're in the right place:
```
dir
```

You should see:
```
src/               (folder)
pom.xml           (file)
README.md         (file)
Dockerfile        (file)
k8s/              (folder)
```

---

## PART 3️⃣: BUILD & TEST THE PROJECT (3-5 mins)

### Step A: Clean everything
```
mvn clean
```

Wait for it to finish. You'll see:
```
[INFO] BUILD SUCCESS
```

### Step B: Compile the code
```
mvn compile
```

Should show:
```
[INFO] BUILD SUCCESS
```

### Step C: Run all 23 tests
```
mvn test
```

Should show:
```
[INFO] Tests run: 23, Failures: 0, Errors: 0
[INFO] BUILD SUCCESS
```

✅ **All 23 tests pass!**

### Step D: Create the JAR file
```
mvn package
```

Should show:
```
[INFO] Building jar: ...\target\expense-tracking-system-1.0.0.jar
[INFO] BUILD SUCCESS
```

Verify the JAR exists:
```
dir target\*.jar
```

You should see: `expense-tracking-system-1.0.0.jar`

### Step E: Test the JAR
```
java -jar target/expense-tracking-system-1.0.0.jar
```

You should see output like:
```
=== Expense Tracking System ===

Adding expenses...

Total Expenses: $227.50
Number of Expenses: 5
Average Expense: $45.50

--- Category Breakdown ---
...
```

✅ **Java program works!**

---

## PART 4️⃣: BUILD & RUN DOCKER (3-5 mins)

Still in the project folder, run:

### Step A: Build Docker image
```
docker build -t expense-tracking-system:1.0.0 .
```

Wait for completion. Last line should be:
```
Successfully tagged expense-tracking-system:1.0.0
```

### Step B: Verify image was created
```
docker images | findstr expense-tracking-system
```

You should see:
```
expense-tracking-system   1.0.0   abc123def456   2 minutes ago   360MB
```

### Step C: Run the container
```
docker run -d --name expense-tracker -p 8080:8080 expense-tracking-system:1.0.0
```

You'll get back a container ID like: `abc123def456xyz`

### Step D: Check if container is running
```
docker ps
```

You should see:
```
CONTAINER ID   IMAGE                              STATUS
abc123def456   expense-tracking-system:1.0.0      Up 2 seconds
```

✅ **Docker container is running!**

### Step E: View logs
```
docker logs expense-tracker
```

Should show the application output.

### Step F: Stop the container
```
docker stop expense-tracker
```

### Step G: Remove the container
```
docker rm expense-tracker
```

---

## PART 5️⃣: DEPLOY TO KUBERNETES (5-10 mins)

### IMPORTANT: Make sure Kubernetes is running!

Open Docker Desktop:
- Click Settings (gear icon)
- Go to Kubernetes
- Click "Enable Kubernetes"
- Wait 3-5 minutes for it to start
- You'll see green checkmark when ready

### Step A: Check Kubernetes is ready
```
kubectl cluster-info
```

Should show:
```
Kubernetes master is running at https://127.0.0.1:6443
```

### Step B: Check nodes
```
kubectl get nodes
```

Should show:
```
NAME             STATUS   ROLES
docker-desktop   Ready    control-plane
```

### Step C: Deploy the application
```
kubectl apply -f k8s/deployment.yaml
```

Should show:
```
deployment.apps/expense-tracker-deployment created
service/expense-tracker-service created
horizontalpodautoscaler.autoscaling/expense-tracker-hpa created
```

### Step D: Check deployment status
```
kubectl get deployments
```

Should show:
```
NAME                           READY
expense-tracker-deployment      3/3
```

Wait if it says "0/3" - pods are starting. Run again in a few seconds.

### Step E: Check pods
```
kubectl get pods
```

Should show 3 pods:
```
NAME                                        READY   STATUS
expense-tracker-deployment-xyz-abc         1/1     Running
expense-tracker-deployment-xyz-def         1/1     Running
expense-tracker-deployment-xyz-ghi         1/1     Running
```

### Step F: Check service
```
kubectl get svc
```

Should show:
```
NAME                      TYPE          EXTERNAL-IP
expense-tracker-service   LoadBalancer  localhost
```

### Step G: View pod logs
```
kubectl logs expense-tracker-deployment-xyz-abc
```

(Replace `xyz-abc` with actual pod name from Step E)

### Step H: Check HPA (auto-scaling)
```
kubectl get hpa
```

Should show:
```
NAME                    REFERENCE
expense-tracker-hpa     Deployment/expense-tracker-deployment
```

### Step I: See everything deployed
```
kubectl get all
```

Should show deployments, pods, services, HPA.

✅ **Application is running on Kubernetes!**

### Step J: To test (if app had API)
```
kubectl port-forward svc/expense-tracker-service 8080:80
```

Then open browser: `http://localhost:8080`

### Step K: To delete everything
```
kubectl delete -f k8s/deployment.yaml
```

---

## PART 6️⃣: PUSH TO GITHUB (OPTIONAL - for CI/CD) (5 mins)

### Step A: Initialize Git
```
git init
git config user.name "Your Name"
git config user.email "your.email@example.com"
```

### Step B: Add all files
```
git add .
```

### Step C: Check what will be committed
```
git status
```

You should see all files marked as "new file"

### Step D: Create first commit
```
git commit -m "Initial commit: Expense Tracking System with Docker and Kubernetes"
```

### Step E: Create GitHub repository
1. Go to https://github.com/new
2. Repository name: `expense-tracking-system`
3. Description: "Expense tracking system with Java, Docker, Kubernetes"
4. Click "Create repository"

### Step F: Connect to GitHub
Replace `YOUR_USERNAME` with your GitHub username:
```
git remote add origin https://github.com/YOUR_USERNAME/expense-tracking-system.git
git branch -M main
git push -u origin main
```

### Step G: Add Docker credentials (for CI/CD)
1. Go to your GitHub repo
2. Settings → Secrets and variables → Actions
3. Click "New repository secret"
4. Name: `DOCKER_USERNAME` → Value: Your Docker Hub username
5. Click "Add secret"
6. Repeat for `DOCKER_PASSWORD` → Your Docker password

### Step H: Watch CI/CD run
1. Go to Actions tab in GitHub
2. You should see a workflow running
3. Wait for all jobs to complete (5-10 minutes)
4. All should turn green with ✅

---

## 📋 QUICK REFERENCE - WHAT DOES WHAT?

| Component | What it does | Command |
|-----------|-------------|---------|
| **Maven** | Compiles code, runs tests | `mvn test` |
| **JUnit** | Tests the code | `mvn test` shows results |
| **JAR** | Packaged Java application | `java -jar target/*.jar` |
| **Docker** | Containerizes app | `docker build -t name .` |
| **Container** | Runs the JAR | `docker run image-name` |
| **Kubernetes** | Runs many containers | `kubectl apply -f file.yaml` |
| **Pods** | Individual running containers | `kubectl get pods` |
| **Service** | Makes pods accessible | `kubectl get svc` |
| **HPA** | Auto-scales pods | `kubectl get hpa` |

---

## ⚡ SUPER QUICK VERSION (If everything is already installed)

```bash
# 1. Build and test (3 mins)
mvn clean package

# 2. Build Docker image (2 mins)
docker build -t expense-tracking-system:1.0.0 .

# 3. Run Docker (1 min)
docker run -d -p 8080:8080 expense-tracking-system:1.0.0

# 4. Deploy to Kubernetes (2 mins)
kubectl apply -f k8s/deployment.yaml

# 5. Check it's running
kubectl get all
```

Done! 🎉

---

## 🆘 SOMETHING WENT WRONG?

### Maven test failed
```
mvn clean
mvn test
```

### Docker image won't build
```
# Make sure JAR exists first
dir target\*.jar

# If not, run:
mvn clean package

# Then try Docker again
docker build -t expense-tracking-system:1.0.0 .
```

### Kubernetes pod won't start
```
# Check what's wrong
kubectl describe pod <pod-name>

# View logs
kubectl logs <pod-name>

# Delete and redeploy
kubectl delete -f k8s/deployment.yaml
kubectl apply -f k8s/deployment.yaml
```

### Container uses too much disk
```
docker system prune -a
```

---

## ✅ HOW TO KNOW IT'S WORKING

### Maven
```
mvn test
# Look for: Tests run: 23, Failures: 0, Errors: 0
```

### Docker
```
docker ps
# Should show your container running
```

### Kubernetes
```
kubectl get pods
# Should show 3 pods with status "Running"
```

---

**You're all set! Follow these steps in order and everything will work.** 🚀

