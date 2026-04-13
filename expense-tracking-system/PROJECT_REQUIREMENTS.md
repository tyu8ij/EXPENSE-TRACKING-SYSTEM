# PROJECT REQUIREMENTS & EXECUTION ROADMAP

## 📌 WHAT NEEDS TO BE DONE - COMPLETE CHECKLIST

---

## 🔴 PHASE 0: PRE-REQUISITES (INSTALL TOOLS)

**What needs to be done:** Make sure your computer has all the tools needed

### ✅ To Do List:
- [ ] **Install Java 11+**
  - Download from: https://adoptopenjdk.net/
  - Verify: Run `java -version` in command prompt
  - ✅ Done when: Shows "openjdk version 11" or higher

- [ ] **Install Maven 3.6+**
  - Download from: https://maven.apache.org/download.cgi
  - Add to PATH environment variable
  - Verify: Run `mvn -version`
  - ✅ Done when: Shows "Apache Maven 3.6.x"

- [ ] **Install Git**
  - Download from: https://git-scm.com/
  - Verify: Run `git --version`
  - ✅ Done when: Shows git version

- [ ] **Install Docker Desktop**
  - Download from: https://www.docker.com/products/docker-desktop
  - Create Docker Hub account: https://hub.docker.com
  - Verify: Run `docker --version`
  - ✅ Done when: Shows Docker version and docker runs

- [ ] **Enable Kubernetes in Docker Desktop**
  - Open Docker Desktop
  - Settings → Kubernetes tab
  - Check "Enable Kubernetes"
  - Wait 3-5 minutes
  - Verify: Run `kubectl cluster-info`
  - ✅ Done when: Shows "Kubernetes master is running"

**📊 STATUS: 0/5 COMPLETED**

---

## 🟡 PHASE 1: LOCAL DEVELOPMENT & BUILD

**What needs to be done:** Get code working on your computer with Maven + JUnit tests

### Location:
```
c:\Users\heman\Downloads\agile fat\expense-tracking-system
```

### ✅ To Do List:

1. [ ] **Clean Project**
   ```bash
   mvn clean
   ```
   - Expected: `[INFO] BUILD SUCCESS`
   - ⏱️ Time: 30 seconds
   - Purpose: Remove old build files

2. [ ] **Compile Code**
   ```bash
   mvn compile
   ```
   - Expected: `[INFO] BUILD SUCCESS`
   - ⏱️ Time: 20 seconds
   - Purpose: Check if code has syntax errors

3. [ ] **Run All 23 JUnit Tests**
   ```bash
   mvn test
   ```
   - Expected: `Tests run: 23, Failures: 0, Errors: 0`
   - ⏱️ Time: 1-2 minutes
   - Purpose: Verify all features work correctly
   - Tests included:
     - 10 tests for Expense class
     - 13 tests for ExpenseTracker class
   - ✅ All tests must PASS

4. [ ] **Generate Code Coverage Report**
   ```bash
   mvn jacoco:report
   ```
   - Expected: Report created in `target/site/jacoco/`
   - ⏱️ Time: 30 seconds
   - Purpose: Show how much code is tested

5. [ ] **View Coverage Report**
   ```bash
   # Windows
   start target/site/jacoco/index.html
   ```
   - Expected: See test coverage percentage
   - ✅ Should see >70% coverage

6. [ ] **Package Code into JAR**
   ```bash
   mvn package
   ```
   - Expected: `Building jar: ...expense-tracking-system-1.0.0.jar`
   - ⏱️ Time: 2-3 minutes
   - Purpose: Create executable JAR file

7. [ ] **Verify JAR File Exists**
   ```bash
   dir target\*.jar
   ```
   - Expected: See `expense-tracking-system-1.0.0.jar`
   - ✅ File should be ~10-15 MB

8. [ ] **Test JAR File**
   ```bash
   java -jar target/expense-tracking-system-1.0.0.jar
   ```
   - Expected: See output with expense details
   - ⏱️ Time: 2-3 seconds
   - ✅ Done when: No errors, shows results

**📊 STATUS: 0/8 COMPLETED**

**✅ PHASE 1 COMPLETE WHEN:** JAR file runs successfully without errors

---

## 🟠 PHASE 2: DOCKER CONTAINERIZATION

**What needs to be done:** Package the Java app inside Docker container

### ✅ To Do List:

1. [ ] **Build Docker Image**
   ```bash
   docker build -t expense-tracking-system:1.0.0 .
   ```
   - Expected: `Successfully tagged expense-tracking-system:1.0.0`
   - ⏱️ Time: 2-3 minutes (first time slower)
   - Purpose: Create container image from Dockerfile
   - ✅ Image contains: Java runtime + JAR file

2. [ ] **Verify Image Exists**
   ```bash
   docker images | findstr expense-tracking-system
   ```
   - Expected: Shows `expense-tracking-system 1.0.0 ...`
   - Image size: ~360-400 MB

3. [ ] **Run Container Locally**
   ```bash
   docker run -d --name expense-tracker -p 8080:8080 expense-tracking-system:1.0.0
   ```
   - Expected: Container ID returned (32-char hex)
   - Purpose: Start the containerized application
   - `-d`: Run in background
   - `-p 8080:8080`: Map port 8080
   - ✅ Container should start

4. [ ] **Check Container Status**
   ```bash
   docker ps
   ```
   - Expected: See container with status "Up X seconds"
   - Container name: `expense-tracker`
   - ✅ Status must be "Running"

5. [ ] **View Container Logs**
   ```bash
   docker logs -f expense-tracker
   ```
   - Expected: See application output
   - Purpose: Verify container is working
   - ✅ No error messages

6. [ ] **Test Container Works**
   ```bash
   curl http://localhost:8080
   ```
   - Note: Working API would respond here
   - ⏱️ Time: 1 second
   - ✅ Container is alive

7. [ ] **Stop Container**
   ```bash
   docker stop expense-tracker
   ```
   - Expected: No output (silent success)
   - ⏱️ Time: 5-10 seconds

8. [ ] **Remove Container**
   ```bash
   docker rm expense-tracker
   ```
   - Expected: No output (silent success)
   - Purpose: Clean up before Kubernetes

9. [ ] **Tag Image for Docker Hub** (Optional)
   ```bash
   docker tag expense-tracking-system:1.0.0 YOUR_USERNAME/expense-tracking-system:latest
   ```
   - Replace `YOUR_USERNAME` with your Docker Hub username
   - Purpose: Prepare for pushing to registry

10. [ ] **Push to Docker Hub** (Optional)
    ```bash
    docker login
    docker push YOUR_USERNAME/expense-tracking-system:latest
    ```
    - Expected: Layers uploaded to Docker Hub
    - ⏱️ Time: 2-5 minutes
    - Purpose: Make image available globally

**📊 STATUS: 0/10 COMPLETED**

**✅ PHASE 2 COMPLETE WHEN:** Container runs, shows output, stops cleanly

---

## 🔵 PHASE 3: KUBERNETES DEPLOYMENT

**What needs to be done:** Deploy Docker container to Kubernetes cluster with 3 replicas, auto-scaling, and load balancing

### Prerequisites:
- ✅ Kubernetes enabled in Docker Desktop
- ✅ Docker image built (from Phase 2)

### ✅ To Do List:

1. [ ] **Verify Kubernetes Master**
   ```bash
   kubectl cluster-info
   ```
   - Expected: `Kubernetes master is running at https://127.0.0.1:6443`
   - ⏱️ Time: 1 second
   - Purpose: Verify Kubernetes is accessible

2. [ ] **Check Available Nodes**
   ```bash
   kubectl get nodes
   ```
   - Expected: Shows `docker-desktop` node with status `Ready`
   - Number of nodes: At least 1
   - ✅ All nodes must be Ready

3. [ ] **Create Namespace** (Optional)
   ```bash
   kubectl create namespace expense-app
   ```
   - Expected: `namespace/expense-app created`
   - Purpose: Isolate application resources

4. [ ] **Deploy Application to Kubernetes**
   ```bash
   kubectl apply -f k8s/deployment.yaml
   ```
   - Expected: 3 resources created:
     - `deployment.apps/expense-tracker-deployment created`
     - `service/expense-tracker-service created`
     - `horizontalpodautoscaler.autoscaling/expense-tracker-hpa created`
   - ⏱️ Time: 5-10 seconds
   - Purpose: Create all Kubernetes resources

5. [ ] **Check Deployment Status**
   ```bash
   kubectl get deployments
   ```
   - Expected: `READY: 3/3` (3 replicas running)
   - Wait if it shows `0/3` or `1/3` - pods are starting (30-60 seconds)
   - ✅ Must show 3/3 Ready

6. [ ] **Check Pods Status**
   ```bash
   kubectl get pods
   ```
   - Expected: 3 pods all with status `Running`
   - Pod names: `expense-tracker-deployment-xxxx-yyyy`
   - ✅ All 3 must show "Running" and "1/1"

7. [ ] **Check Service**
   ```bash
   kubectl get svc
   ```
   - Expected: `expense-tracker-service` with type `LoadBalancer`
   - External-IP: `localhost` (Docker Desktop) or assigned IP
   - Port: `80:30xxx/TCP`
   - ✅ Service must exist and be accessible

8. [ ] **View Pod Logs (Pod 1)**
   ```bash
   # Get pod name first
   kubectl get pods
   
   # View logs (replace with actual pod name)
   kubectl logs <pod-name-1>
   ```
   - Expected: Application output, no errors
   - Purpose: Verify container is working inside pod

9. [ ] **View Pod Logs (Pod 2 & 3)**
   ```bash
   kubectl logs <pod-name-2>
   kubectl logs <pod-name-3>
   ```
   - Expected: Same successful output as Pod 1
   - Purpose: Verify all pods working identically

10. [ ] **Check Service Endpoints**
    ```bash
    kubectl get endpoints
    ```
    - Expected: 3 endpoints listed (one per pod)
    - Format: `10.1.x.x:8080, 10.1.x.x:8080, 10.1.x.x:8080`
    - ✅ Must show all 3 pods

11. [ ] **Check HPA Configuration**
    ```bash
    kubectl get hpa
    ```
    - Expected: `expense-tracker-hpa` with:
      - MIN REPLICAS: 2
      - MAX REPLICAS: 5
      - TARGETS: CPU/Memory utilization
    - Purpose: Auto-scaling is configured

12. [ ] **Describe Deployment Details**
    ```bash
    kubectl describe deployment expense-tracker-deployment
    ```
    - Expected: Full deployment info including:
      - Replicas: 3/3
      - Pod template
      - Events (no errors)

13. [ ] **Port Forward to Service** (If testing)
    ```bash
    kubectl port-forward svc/expense-tracker-service 8080:80
    ```
    - Expected: `Forwarding from 127.0.0.1:8080 -> 8080`
    - Purpose: Access app from localhost
    - ⏱️ Run continuously until Ctrl+C

14. [ ] **Test Service Health**
    ```bash
    curl http://localhost:8080
    ```
    - Expected: Response from service
    - Purpose: Verify service is responding

15. [ ] **Scale Up Manually** (Optional)
    ```bash
    kubectl scale deployment expense-tracker-deployment --replicas=5
    ```
    - Expected: Now 5 pods running (from HPA max)
    - Command: `kubectl get pods -w` to watch

16. [ ] **Scale Back Down**
    ```bash
    kubectl scale deployment expense-tracker-deployment --replicas=3
    ```
    - Expected: Back to 3 pods

17. [ ] **Watch Pods in Real-time** (Optional)
    ```bash
    kubectl get pods -w
    ```
    - Expected: Live updates as pod status changes
    - Stop: Press Ctrl+C

18. [ ] **View All Kubernetes Resources**
    ```bash
    kubectl get all
    ```
    - Expected: All deployments, pods, services, HPA visible
    - Purpose: Complete overview of deployed app

19. [ ] **Delete All Resources** (When done testing)
    ```bash
    kubectl delete -f k8s/deployment.yaml
    ```
    - Expected:
      - `deployment.apps "expense-tracker-deployment" deleted`
      - `service "expense-tracker-service" deleted`
      - `horizontalpodautoscaler.autoscaling "expense-tracker-hpa" deleted`
    - Purpose: Clean up resources

20. [ ] **Verify All Deleted**
    ```bash
    kubectl get all
    ```
    - Expected: Empty (nothing except defaults)

**📊 STATUS: 0/20 COMPLETED**

**✅ PHASE 3 COMPLETE WHEN:** All 3 pods running, service accessible, HPA configured

---

## 🟣 PHASE 4: GITHUB & CI/CD (OPTIONAL)

**What needs to be done:** Push code to GitHub and set up automated CI/CD pipeline

### Prerequisites:
- GitHub account created
- Docker Hub account created

### ✅ To Do List:

1. [ ] **Initialize Git Repository**
   ```bash
   git init
   git config user.name "Your Name"
   git config user.email "your.email@example.com"
   ```
   - Expected: No output (silent success)
   - Purpose: Prepare for Git version control

2. [ ] **Add All Files to Git**
   ```bash
   git add .
   ```
   - Expected: No output
   - Purpose: Stage all files for commit

3. [ ] **Check Staging Status**
   ```bash
   git status
   ```
   - Expected: List of "new file" entries showing all project files
   - ✅ All files should be green/staged

4. [ ] **Create Initial Commit**
   ```bash
   git commit -m "Initial commit: Expense Tracking System with Docker and Kubernetes"
   ```
   - Expected: `X files changed, Y insertions`
   - ⏱️ Time: 1-2 seconds
   - Purpose: Save current state to Git

5. [ ] **Create Repository on GitHub**
   1. Go to https://github.com/new
   2. Repository name: `expense-tracking-system`
   3. Description: "Complete expense tracking system with Java, Docker, Kubernetes"
   4. Initialize with: **Nothing** (we already have files)
   5. Click "Create repository"
   - Expected: Redirected to new repo page
   - Copy the HTTPS URL

6. [ ] **Add Remote Repository**
   ```bash
   git remote add origin https://github.com/YOUR_USERNAME/expense-tracking-system.git
   ```
   - Replace: `YOUR_USERNAME` with your GitHub username
   - Expected: No output (silent success)
   - Purpose: Connect local Git to GitHub

7. [ ] **Verify Remote Added**
   ```bash
   git remote -v
   ```
   - Expected: Shows `origin` URL for fetch and push
   - ✅ Must show both fetch and push URLs

8. [ ] **Rename Branch to Main**
   ```bash
   git branch -M main
   ```
   - Expected: No output
   - Purpose: Use `main` as default branch (GitHub standard)

9. [ ] **Push Code to GitHub**
   ```bash
   git push -u origin main
   ```
   - Expected: Multiple lines showing files being pushed
   - ⏱️ Time: 10-30 seconds
   - Purpose: Upload code to GitHub
   - ✅ Last line should say: "Branch 'main' set up to track..."

10. [ ] **Verify on GitHub**
    1. Go to your GitHub repo URL: `https://github.com/YOUR_USERNAME/expense-tracking-system`
    2. Verify you see all files and folders
    3. Look for green checkmark next to commit
    - Expected: See all project files listed
    - ✅ Repository successfully synced

11. [ ] **Add Docker Hub Credentials**
    1. Go to GitHub Repo Settings
    2. Left menu → "Secrets and variables" → "Actions"
    3. Click: "New repository secret"
    4. Name: `DOCKER_USERNAME`
    5. Value: Your Docker Hub username
    6. Click "Add secret"
    - Expected: Secret added (shows as ●●●●●)

12. [ ] **Add Docker Hub Password**
    1. Click "New repository secret" again
    2. Name: `DOCKER_PASSWORD`
    3. Value: Your Docker Hub password (or token)
    4. Click "Add secret"
    - Expected: Secret added securely

13. [ ] **Watch CI/CD Pipeline Run**
    1. Go to repo → "Actions" tab
    2. You should see workflow "CI/CD Pipeline" running
    3. Jobs to complete:
       - `build-and-test` (Maven build + tests) - 5 mins
       - `docker-build` (Build Docker image) - 3 mins
       - `deploy-to-kubernetes` (K8s deploy) - 2 mins
       - `validate` (Final check) - 1 min
    - Expected: All jobs complete with green ✅
    - ⏱️ Total time: 10-15 minutes

14. [ ] **Check Build Job Results**
    1. Click on `build-and-test` job
    2. Expand steps to see details
    3. Verify:
       - ✅ `Maven clean compile` succeeded
       - ✅ `Run JUnit Tests` shows 23 tests passed
       - ✅ `Package JAR` succeeded
    - Expected: Green checkmarks next to each step

15. [ ] **Check Docker Job Results**
    1. Click on `docker-build` job
    2. Verify:
       - ✅ Docker image built
       - ✅ Image pushed to Docker Hub
    - Go to Docker Hub and verify image is there

16. [ ] **Check Kubernetes Deploy Job** (If applicable)
    1. Click on `deploy-to-kubernetes` job
    2. Verify deployment steps (if configured)
    - Note: May skip without K8s credentials in GitHub

17. [ ] **View Workflow Summary**
    - Click on the workflow run
    - See total time taken
    - See number of jobs: 4
    - Status: All ✅ or some ⏭️ (skipped)

**📊 STATUS: 0/17 COMPLETED**

**✅ PHASE 4 COMPLETE WHEN:** Code pushed to GitHub, pipeline runs, artifact built

---

## 📊 OVERALL PROJECT STATUS

### Marks Distribution (Total: 50)

| Component | Marks | Phase | Status |
|-----------|-------|-------|--------|
| **Java Development** | 15 | Phase 1 | ⏳ To Do |
| **JUnit Testing** | 15 | Phase 1 | ⏳ To Do |
| **CI/CD Pipeline** | 10 | Phase 2-4 | ⏳ To Do |
| **Docker & K8s** | 10 | Phase 2-3 | ⏳ To Do |
| **TOTAL** | **50** | **All** | **⏳ Not Started** |

---

## 🎯 EXECUTION ORDER (RECOMMENDED)

```
Phase 0 (5-10 min)    → Install prerequisites
        ↓
Phase 1 (5-10 min)    → Build & test locally
        ↓
Phase 2 (5-10 min)    → Build & test Docker
        ↓
Phase 3 (10-20 min)   → Deploy to Kubernetes
        ↓
Phase 4 (15-20 min)   → Push to GitHub
        ↓
      DONE! 🎉
```

**⏱️ Total Time Required: 45-70 minutes (depending on internet speed)**

---

## 🔄 CONTINUOUS IMPROVEMENT

After completing all phases:

1. **Make Code Changes**
   ```bash
   # Edit source files
   git add .
   git commit -m "Describe your change"
   git push origin main
   ```
   CI/CD pipeline automatically runs

2. **Monitor Pipeline**
   - GitHub Actions runs automatically on push
   - All tests run
   - Docker image rebuilt
   - Pod updated on Kubernetes

3. **Monitor Deployment**
   ```bash
   kubectl get all -w
   ```
   Watch pods rolling update to new version

---

## ✅ CHECKLIST SUMMARY

**PHASE 0: TOOLS** (5 items)
- [ ] Java 11+
- [ ] Maven 3.6+
- [ ] Git
- [ ] Docker Desktop
- [ ] Kubernetes enabled

**PHASE 1: BUILD** (8 items)
- [ ] Clean
- [ ] Compile
- [ ] Test (23 tests pass)
- [ ] Coverage report
- [ ] Package JAR
- [ ] Test JAR
- [ ] (2 more steps)

**PHASE 2: DOCKER** (10 items)
- [ ] Build image
- [ ] Run container
- [ ] Check status
- [ ] View logs
- [ ] Stop container
- [ ] (5 more steps)

**PHASE 3: KUBERNETES** (20 items)
- [ ] Verify K8s
- [ ] Deploy
- [ ] Check deployment
- [ ] Check pods
- [ ] Check service
- [ ] (15 more steps)

**PHASE 4: GITHUB** (17 items)
- [ ] Git init
- [ ] Git add/commit
- [ ] Create GitHub repo
- [ ] Push to GitHub
- [ ] Add secrets
- [ ] Monitor CI/CD
- [ ] (11 more steps)

**GRAND TOTAL: 60 items to complete**

---

When you've completed all these steps, your project will have:
✅ Complete Java application with expense tracking logic
✅ 23 JUnit tests (100% passing)
✅ Dockerized application  
✅ Kubernetes deployment with auto-scaling
✅ GitHub repository
✅ Automated CI/CD pipeline
✅ Production-ready setup

**Project Status: READY FOR PRODUCTION** 🚀

