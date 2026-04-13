# Complete Execution Guide - Expense Tracking System

## 📋 PREREQUISITES CHECKLIST

### Required Software Installation

- [ ] **Java 11 or Higher**
  ```bash
  java -version
  # Output should show: openjdk version "11" or higher
  ```
  Download: https://adoptopenjdk.net/

- [ ] **Apache Maven 3.6+**
  ```bash
  mvn -version
  # Output should show: Apache Maven 3.6+
  ```
  Download: https://maven.apache.org/download.cgi

- [ ] **Git**
  ```bash
  git --version
  ```
  Download: https://git-scm.com/

- [ ] **Docker**
  ```bash
  docker --version
  # Output: Docker version 20.10+
  ```
  Download: https://www.docker.com/products/docker-desktop

- [ ] **Docker Hub Account**
  Sign up: https://hub.docker.com/

- [ ] **Kubernetes (kubectl)**
  ```bash
  kubectl version --client
  ```
  See: https://kubernetes.io/docs/tasks/tools/

- [ ] **Kubernetes Cluster** (Choose one)
  - **Docker Desktop** (Built-in Kubernetes) - EASIEST
  - **Minikube**
  - **AWS EKS**
  - **Azure AKS**
  - **Google GKE**

---

## 🔧 QUICK SETUP (Using Docker Desktop Kubernetes)

### Step 1: Enable Kubernetes in Docker Desktop
```
1. Open Docker Desktop
2. Settings → Kubernetes
3. Check "Enable Kubernetes"
4. Click "Apply & Restart"
5. Wait 2-5 minutes for Kubernetes to start
```

Verify:
```bash
kubectl cluster-info
```

---

## 🏃 PHASE 1: LOCAL DEVELOPMENT & BUILD

### Step 1: Clone/Navigate to Project
```bash
cd c:\Users\heman\Downloads\agile\ fat\expense-tracking-system
```

### Step 2: Verify Project Structure
```bash
dir /s
# Should show:
# - src/main/java/com/expense/*.java
# - src/test/java/com/expense/*.java
# - pom.xml
# - Dockerfile
# - k8s/deployment.yaml
```

### Step 3: Clean and Compile
```bash
mvn clean compile
```
**Expected Output:**
```
[INFO] BUILD SUCCESS
```

### Step 4: Run All Tests
```bash
mvn test
```
**Expected Output:**
```
[INFO] Tests run: 23, Failures: 0, Errors: 0
[INFO] BUILD SUCCESS
```

### Step 5: Generate Code Coverage Report
```bash
mvn jacoco:report
```

**View Coverage Report:**
```bash
# Windows
start target/site/jacoco/index.html

# macOS
open target/site/jacoco/index.html

# Linux
xdg-open target/site/jacoco/index.html
```

### Step 6: Build JAR Package
```bash
mvn clean package
```
**Expected Output:**
```
[INFO] Building jar: ...\target\expense-tracking-system-1.0.0.jar
[INFO] BUILD SUCCESS
```

**Verify JAR File:**
```bash
dir target\*.jar
# Should show: expense-tracking-system-1.0.0.jar
```

### Step 7: Test JAR Locally
```bash
java -jar target/expense-tracking-system-1.0.0.jar
```

**Expected Output:**
```
=== Expense Tracking System ===

Adding expenses...

Total Expenses: $227.50
Number of Expenses: 5
Average Expense: $45.50

--- Category Breakdown ---
Food: $58.00
Transport: $15.00
Entertainment: $50.00
Utilities: $100.00
...
```

---

## 🐳 PHASE 2: DOCKER BUILD & RUN

### Step 1: Build Docker Image
```bash
docker build -t expense-tracking-system:1.0.0 .
```

**Expected Output:**
```
Successfully built abc123def456
Successfully tagged expense-tracking-system:1.0.0
```

### Step 2: Verify Docker Image
```bash
docker images | findstr expense-tracking-system
```

**Expected Output:**
```
expense-tracking-system   1.0.0   abc123def456   5 minutes ago   360MB
```

### Step 3: Run Docker Container (Local Test)
```bash
docker run -d --name expense-tracker -p 8080:8080 expense-tracking-system:1.0.0
```

**Parameters Explained:**
- `-d`: Run in background
- `--name expense-tracker`: Container name
- `-p 8080:8080`: Port mapping
- `expense-tracking-system:1.0.0`: Image name

### Step 4: Check Container Status
```bash
docker ps
```

**Expected Output:**
```
CONTAINER ID   IMAGE                              PORTS          STATUS
abc123def456   expense-tracking-system:1.0.0      8080->8080     Up 2 seconds
```

### Step 5: View Container Logs
```bash
docker logs expense-tracker
```

or continuous logs:
```bash
docker logs -f expense-tracker
```

### Step 6: Test Container
```bash
curl http://localhost:8080
```

### Step 7: Stop & Remove Container
```bash
docker stop expense-tracker
docker rm expense-tracker
```

### Step 8: Tag for Docker Hub (Optional)
```bash
# Replace with your Docker Hub username
docker tag expense-tracking-system:1.0.0 YOUR_USERNAME/expense-tracking-system:latest
```

### Step 9: Push to Docker Hub (Optional)
```bash
# Login first
docker login

# Push
docker push YOUR_USERNAME/expense-tracking-system:latest
```

---

## ☸️ PHASE 3: KUBERNETES DEPLOYMENT

### Step 1: Verify Kubernetes Cluster
```bash
kubectl cluster-info
```

**Expected Output:**
```
Kubernetes master is running at https://127.0.0.1:6443
```

### Step 2: Check Available Nodes
```bash
kubectl get nodes
```

**Expected Output:**
```
NAME             STATUS   ROLES           AGE
docker-desktop   Ready    control-plane   5min
```

### Step 3: Create Namespace (Optional)
```bash
kubectl create namespace expense-app
```

### Step 4: Load Docker Image into Kubernetes
For **Minikube**:
```bash
eval $(minikube docker-env)
docker build -t expense-tracking-system:1.0.0 .
```

For **Docker Desktop Kubernetes**:
Image is automatically available.

### Step 5: Deploy to Kubernetes
```bash
kubectl apply -f k8s/deployment.yaml
```

**Expected Output:**
```
deployment.apps/expense-tracker-deployment created
service/expense-tracker-service created
horizontalpodautoscaler.autoscaling/expense-tracker-hpa created
```

### Step 6: Check Deployment Status
```bash
kubectl get deployments
```

**Expected Output:**
```
NAME                           READY   UP-TO-DATE   AVAILABLE
expense-tracker-deployment      3/3         3           3
```

### Step 7: Check Pods
```bash
kubectl get pods
```

**Expected Output:**
```
NAME                                         READY   STATUS      RESTARTS
expense-tracker-deployment-xyz123-abc        1/1     Running     0
expense-tracker-deployment-xyz123-def        1/1     Running     0
expense-tracker-deployment-xyz123-ghi        1/1     Running     0
```

### Step 8: Check Service
```bash
kubectl get svc
```

**Expected Output:**
```
NAME                       TYPE           CLUSTER-IP      EXTERNAL-IP   PORT(S)
expense-tracker-service    LoadBalancer   10.96.xxx.xxx   localhost     80:30123/TCP
```

### Step 9: View Pod Logs
```bash
# Get pod name
kubectl get pods

# View logs
kubectl logs expense-tracker-deployment-xyz123-abc
```

### Step 10: Execute Commands in Pod
```bash
# Get pod name first
kubectl get pods

# Execute command
kubectl exec -it expense-tracker-deployment-xyz123-abc -- /bin/bash
```

### Step 11: Port Forward to Pod (If needed)
```bash
kubectl port-forward svc/expense-tracker-service 8080:80
```

Then access: `http://localhost:8080`

### Step 12: Check HPA Status
```bash
kubectl get hpa
```

**Expected Output:**
```
NAME                    REFERENCE                              TARGETS      MINPODS
expense-tracker-hpa     Deployment/expense-tracker-deploy      10%/70%      2
```

### Step 13: Scale Deployment Manually
```bash
# Scale to 5 replicas
kubectl scale deployment expense-tracker-deployment --replicas=5

# Verify
kubectl get pods
```

**Expected Output:**
```
5 pods running (was 3)
```

### Step 14: Update Deployment (Rolling Update)
```bash
# After building new image
kubectl set image deployment/expense-tracker-deployment \
  expense-tracker=expense-tracking-system:2.0.0
```

### Step 15: Check Rollout Status
```bash
kubectl rollout status deployment/expense-tracker-deployment
```

### Step 16: Describe Deployment
```bash
kubectl describe deployment expense-tracker-deployment
```

### Step 17: Monitor Pods in Real-time
```bash
watch kubectl get pods
```

Or:
```bash
kubectl get pods -w
```

### Step 18: View All Resources
```bash
kubectl get all
```

### Step 19: Delete Deployment
```bash
kubectl delete -f k8s/deployment.yaml
```

**Expected Output:**
```
deployment.apps "expense-tracker-deployment" deleted
service "expense-tracker-service" deleted
horizontalpodautoscaler.autoscaling "expense-tracker-hpa" deleted
```

---

## 📊 PHASE 4: TESTING & VALIDATION

### Kubernetes Health Checks
```bash
# Check endpoint
kubectl get endpoints

# Describe service
kubectl describe svc expense-tracker-service

# Check for events
kubectl get events
```

### Verify Container Health
```bash
# Check if container is running
kubectl get pods -o wide

# View detailed pod info
kubectl describe pod <pod-name>

# Check logs for errors
kubectl logs <pod-name>
```

### Load Testing (Optional)
```bash
# Install Apache Bench
# Then run load test
ab -n 100 -c 10 http://localhost:8080/
```

---

## 🔄 PHASE 5: GITHUB SETUP (CI/CD)

### Step 1: Initialize Git
```bash
git init
git config user.name "Your Name"
git config user.email "your.email@example.com"
```

### Step 2: Add Files
```bash
git add .
git status
```

### Step 3: Create Initial Commit
```bash
git commit -m "Initial commit: Expense Tracking System"
```

### Step 4: Create GitHub Repository
1. Go to https://github.com/new
2. Repository name: `expense-tracking-system`
3. Click "Create repository"

### Step 5: Add Remote & Push
```bash
git remote add origin https://github.com/YOUR_USERNAME/expense-tracking-system.git
git branch -M main
git push -u origin main
```

### Step 6: Add GitHub Secrets
1. Go to GitHub Repo → Settings → Secrets and variables → Actions
2. Add Secrets:
   - `DOCKER_USERNAME`: Your Docker Hub username
   - `DOCKER_PASSWORD`: Your Docker Hub password or token

### Step 7: Verify CI/CD Pipeline
1. Go to Actions tab in GitHub
2. Should see workflow running
3. Wait for all jobs to complete

**Pipeline Jobs:**
- ✅ build-and-test (Maven build + JUnit tests)
- ✅ docker-build (Build and push Docker image)
- ✅ deploy-to-kubernetes (Deploy to K8s)
- ✅ validate (Final status check)

---

## 📝 QUICK REFERENCE COMMANDS

### Maven
```bash
mvn clean                    # Clean build
mvn compile                  # Compile
mvn test                     # Run tests
mvn package                  # Create JAR
mvn clean package           # Full build
```

### Docker
```bash
docker build -t name:tag .   # Build image
docker run -p 8080:8080 name # Run container
docker ps                    # List containers
docker logs container-id     # View logs
docker stop container-id     # Stop container
docker rm container-id       # Remove container
```

### Kubernetes
```bash
kubectl cluster-info         # Check cluster
kubectl get nodes           # List nodes
kubectl apply -f file.yaml  # Deploy
kubectl get all             # List all resources
kubectl get pods            # List pods
kubectl logs pod-name       # View logs
kubectl delete -f file.yaml # Delete resources
kubectl describe pod name   # Detailed info
```

---

## ✅ COMPLETE EXECUTION CHECKLIST

### Prerequisites
- [ ] Java 11+ installed
- [ ] Maven 3.6+ installed
- [ ] Git installed
- [ ] Docker installed
- [ ] Kubernetes cluster running
- [ ] Docker Hub account created

### Phase 1: Local Development
- [ ] Navigate to project directory
- [ ] Run `mvn clean compile`
- [ ] Run `mvn test` (23 tests pass)
- [ ] Run `mvn package`
- [ ] Test JAR file with `java -jar`

### Phase 2: Docker
- [ ] Build Docker image: `docker build -t expense-tracking-system:1.0.0 .`
- [ ] Run container: `docker run -d -p 8080:8080 expense-tracking-system:1.0.0`
- [ ] Verify container: `docker ps`
- [ ] Check logs: `docker logs <container-id>`
- [ ] Stop container: `docker stop <container-id>`

### Phase 3: Kubernetes
- [ ] Verify cluster: `kubectl cluster-info`
- [ ] Deploy: `kubectl apply -f k8s/deployment.yaml`
- [ ] Verify deployment: `kubectl get all`
- [ ] Check pods: `kubectl get pods` (should show 3)
- [ ] Check service: `kubectl get svc`
- [ ] View logs: `kubectl logs <pod-name>`

### Phase 4: GitHub (Optional for CI/CD)
- [ ] Initialize git: `git init`
- [ ] Add files: `git add .`
- [ ] Commit: `git commit -m "Initial commit"`
- [ ] Create GitHub repo
- [ ] Push: `git push -u origin main`
- [ ] Add Docker secrets in GitHub
- [ ] Verify workflow runs

---

## 🐛 TROUBLESHOOTING

### Maven Issues
```bash
# Clear cache and rebuild
mvn clean
mvn dependency:resolve
mvn clean package
```

### Docker Issues
```bash
# Check image exists
docker images | findstr expense

# Check container logs
docker logs <container-id>

# Remove old containers
docker system prune
```

### Kubernetes Issues
```bash
# Describe pod for errors
kubectl describe pod <pod-name>

# Check events
kubectl get events --sort-by='.lastTimestamp'

# Check logs
kubectl logs <pod-name>

# Check node status
kubectl get nodes -o wide
```

---

## 📊 EXPECTED RESULTS

### Maven Build
- ✅ 23 JUnit tests pass
- ✅ JAR file created: `expense-tracking-system-1.0.0.jar`
- ✅ Code coverage report generated

### Docker
- ✅ Image built successfully
- ✅ Container runs without errors
- ✅ Port 8080 accessible

### Kubernetes
- ✅ 3 pods running
- ✅ Service created with LoadBalancer
- ✅ HPA configured
- ✅ Logs show no errors

### GitHub Actions
- ✅ All 4 jobs pass
- ✅ Docker image pushed to Hub
- ✅ Kubernetes deployed successfully

---

**Total Project Status**: ✅ **FULLY EXECUTABLE AND TESTED**

