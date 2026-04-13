# MASTER CHECKLIST - What to Run & What to Do

## 🎯 EVERYTHING YOU NEED TO DO IN ONE PLACE

---

## ✅ PREREQUISITE CHECK (Do this FIRST!)

Run these commands to verify your setup:

```bash
java -version              # Should show Java 11+
mvn -version              # Should show Maven 3.6+
git --version             # Should show git version
docker --version          # Should show Docker version
kubectl cluster-info      # Should show Kubernetes running
```

If ANY shows an error → Go to STEP BY STEP.md for installation guide

---

## 🚀 THEN RUN THIS SEQUENCE (Copy & Paste each command)

### COMMAND SET 1: BUILD & TEST (3-5 minutes)
```bash
cd c:\Users\heman\Downloads\agile fat\expense-tracking-system
mvn clean
mvn compile
mvn test
mvn package
java -jar target/expense-tracking-system-1.0.0.jar
```

✅ **When it works:** JAR runs and shows expense output

**Expected Output:**
```
=== Expense Tracking System ===
Total Expenses: $227.50
Number of Expenses: 5
```

---

### COMMAND SET 2: DOCKER (3-5 minutes)

```bash
docker build -t expense-tracking-system:1.0.0 .
docker run -d --name expense-tracker -p 8080:8080 expense-tracking-system:1.0.0
docker ps
docker logs expense-tracker
docker stop expense-tracker
docker rm expense-tracker
```

✅ **When it works:** Docker container runs and stops cleanly

**Expected Output:**
```
CONTAINER ID   STATUS
abc123def456   Up 2 seconds
```

---

### COMMAND SET 3: KUBERNETES (5-10 minutes)

**IMPORTANT:** Make sure Kubernetes is enabled in Docker Desktop first!

```bash
kubectl cluster-info
kubectl get nodes
kubectl apply -f k8s/deployment.yaml
kubectl get deployments
kubectl get pods
kubectl get svc
kubectl get hpa
kubectl get all
kubectl delete -f k8s/deployment.yaml
```

✅ **When it works:** All resources deploy and show in `kubectl get all`

**Expected Output:**
```
deployment.apps/expense-tracker-deployment       3/3
3 pods with status "Running"
service/expense-tracker-service
horizontalpodautoscaler/expense-tracker-hpa
```

---

### COMMAND SET 4: GITHUB & CI/CD (Optional, 15 minutes)

```bash
git init
git config user.name "Your Name"
git config user.email "your.email@example.com"
git add .
git commit -m "Initial commit: Expense Tracking System"
```

Then:
1. Create repo on GitHub: https://github.com/new
2. Name: `expense-tracking-system`
3. Copy the URL

Then run:
```bash
git remote add origin YOUR_GITHUB_URL
git branch -M main
git push -u origin main
```

✅ **When it works:** Files appear on GitHub.com

---

## 📋 MASTER CHECKLIST

- [ ] **Java installed & working** (`java -version` shows 11+)
- [ ] **Maven installed & working** (`mvn -version`)
- [ ] **Git installed** (`git --version`)
- [ ] **Docker installed** (`docker --version`)
- [ ] **Kubernetes enabled** (`kubectl cluster-info` works)

- [ ] **Compile code** (`mvn compile` succeeds)
- [ ] **Tests pass** (`mvn test` shows 23 tests, 0 failures)
- [ ] **JAR created** (`target/expense-tracking-system-1.0.0.jar` exists)
- [ ] **JAR runs** (`java -jar target/expense-tracking-system-1.0.0.jar` shows output)

- [ ] **Docker image built** (`docker images` shows expense-tracking-system)
- [ ] **Container runs** (`docker run` starts container)
- [ ] **Container stops** (`docker stop` works)

- [ ] **Kubernetes deployment** (`kubectl apply -f k8s/deployment.yaml` succeeds)
- [ ] **3 pods running** (`kubectl get pods` shows 3 with "Running" status)
- [ ] **Service created** (`kubectl get svc` shows expense-tracker-service)
- [ ] **HPA configured** (`kubectl get hpa` shows expense-tracker-hpa)

- [ ] **Git initialized** (`git init` works)
- [ ] **Code committed** (All files staged and committed)
- [ ] **Pushed to GitHub** (`git push` succeeds)
- [ ] **Docker credentials added to GitHub** (Settings → Secrets)

---

## 🔍 HOW TO CHECK IF EACH PHASE IS WORKING

### ✅ Java/Maven Phase Works When:
```
✓ mvn clean compile → BUILD SUCCESS
✓ mvn test → Tests run: 23, Failures: 0, Errors: 0
✓ mvn package → expense-tracking-system-1.0.0.jar created
✓ java -jar → Runs and shows "=== Expense Tracking System ==="
```

### ✅ Docker Phase Works When:
```
✓ docker build → Successfully tagged expense-tracking-system:1.0.0
✓ docker run → CONTAINER ID returned
✓ docker ps → Shows container with status "Up X seconds"
✓ docker logs → Shows application output
✓ docker stop → No errors
```

### ✅ Kubernetes Phase Works When:
```
✓ kubectl cluster-info → Shows "Kubernetes master is running"
✓ kubectl apply → All 3 resources created
✓ kubectl get pods → Shows 3 with "Running" and "1/1"
✓ kubectl get svc → Shows expense-tracker-service
✓ kubectl get all → Shows all resources
```

### ✅ GitHub Phase Works When:
```
✓ git commit → X files changed, Y insertions
✓ git push → Successfully pushed to origin
✓ GitHub.com → Shows all files in repository
✓ CI/CD runs → Actions tab shows executing workflow
```

---

## 🆘 QUICK TROUBLESHOOTING

### Maven test failed?
```bash
mvn clean
mvn test
```

### Docker build failed?
```bash
# Make sure JAR exists first
dir target\*.jar
# If missing, run:
mvn clean package
# Then:
docker build -t expense-tracking-system:1.0.0 .
```

### Kubernetes deploy stuck?
```bash
# Check pod details
kubectl describe pod <pod-name>
# View logs
kubectl logs <pod-name>
# Delete and retry
kubectl delete -f k8s/deployment.yaml
kubectl apply -f k8s/deployment.yaml
```

### Git push failed?
```bash
# Make sure remote is added
git remote -v
# If not, add it (replace YOUR_USERNAME):
git remote add origin https://github.com/YOUR_USERNAME/expense-tracking-system.git
# Try again
git push -u origin main
```

---

## ⏱️ TIME ESTIMATES

| Phase | Time | Status |
|-------|------|--------|
| Prerequisites | 10 min | ⏳ Setup |
| Java Build & Test | 5 min | 🚀 Run |
| Docker Build & Run | 5 min | 🚀 Run |
| Kubernetes Deploy | 10 min | 🚀 Run |
| GitHub & CI/CD | 15 min | 🚀 Run |
| **TOTAL** | **~45 min** | **Complete** |

---

## 📊 MARKS EARNED WHEN COMPLETE

| What | Marks | How to Verify |
|-----|-------|---------------|
| Java Program Development | 15 | `mvn test` shows 23 tests pass |
| JUnit Testing | 15 | `mvn test` shows all tests green |
| CI/CD Pipeline | 10 | GitHub Actions workflow completes |
| Docker & Kubernetes | 10 | `kubectl get all` shows 3 pods running |
| **TOTAL** | **50** | **All phases complete** |

---

## 🎯 SUCCESS INDICATORS

When you see these, you're doing great:

| Command | Success = |
|---------|-----------|
| `mvn test` | `Tests run: 23, Failures: 0` |
| `docker build` | `Successfully tagged...` |
| `docker ps` | Container shows "Up X seconds" |
| `kubectl get pods` | All 3 show `Running` and `1/1` |
| `kubectl get all` | Shows deployment, 3 pods, service, HPA |
| `git push` | No errors, "Branch 'main' set up to track" |

---

## 🎓 WHAT YOU'RE CREATING

✅ **Expense Tracker Application**
- Java code for tracking personal expenses
- Add, remove, update, calculate, analyze expenses
- Fully tested with 23 JUnit test cases

✅ **Docker Container**
- Packaged application that runs anywhere
- Includes Java runtime + your JAR
- ~360 MB image size

✅ **Kubernetes Deployment**
- 3 running pod replicas (high availability)
- Auto-scaling HPA (handles traffic spikes)
- LoadBalancer service (accessible from outside)
- Health checks (automatic pod recovery)

✅ **CI/CD Pipeline**
- Automated Maven build on each push
- Automatic testing
- Docker image creation
- Kubernetes deployment
- All on GitHub Actions

---

## 🚀 YOU'RE READY TO START!

Pick the command set above and start running them in order.

**Next Step:** Run COMMAND SET 1 (Maven build & test)

When that works → Run COMMAND SET 2 (Docker)

When that works → Run COMMAND SET 3 (Kubernetes)

When that works → Run COMMAND SET 4 (GitHub)

---

**Questions?** See:
- STEP_BY_STEP.md - Detailed walkthrough with explanations
- EXECUTION_GUIDE.md - Complete reference with all options
- PROJECT_REQUIREMENTS.md - Full checklist with detailed requirements

**Final Status: READY TO EXECUTE** ✅

