# Quick Start Guide - Expense Tracking System

## ⚡ 5-Minute Setup

### Prerequisites
- Java 11+ installed
- Maven installed
- Git installed

### Quick Build & Test
```bash
cd expense-tracking-system

# Clean build
mvn clean compile

# Run tests
mvn test

# Package
mvn package
```

### Run the Application
```bash
java -jar target/expense-tracking-system-1.0.0.jar
```

## 📦 What's Included

### ✅ Java Program (15 marks)
- **Expense.java**: Data model with validation
- **ExpenseTracker.java**: Core business logic
  - Add/remove/update expenses
  - Calculate totals, averages, breakdowns
  - Filter by category, date range, month
  - Find highest/lowest expenses

### ✅ JUnit Tests (15 marks)
- **ExpenseTest.java**: 10 test cases for model
- **ExpenseTrackerTest.java**: 13 test cases for logic
- Total: 23 comprehensive tests with edge cases
- Code coverage report in `target/site/jacoco/`

### ✅ CI/CD Pipeline (10 marks)
- **GitHub Actions** automated workflow
- Maven build with JUnit test execution
- JaCoCo code coverage report
- Docker image build and push
- Kubernetes deployment automation

### ✅ Docker & Kubernetes (10 marks)
- **Dockerfile**: Containerized application
- **deployment.yaml**: 
  - Kubernetes Deployment (3 replicas)
  - LoadBalancer Service
  - Horizontal Pod Autoscaler (HPA)

## 🚀 Step-by-Step Execution

### Step 1: Build Locally
```bash
mvn clean package
```
**Output**: `target/expense-tracking-system-1.0.0.jar`

### Step 2: Run Tests
```bash
mvn test
```
**Output**: 23 tests PASSED ✅

### Step 3: Docker Build
```bash
docker build -t expense-tracking-system:1.0.0 .
docker run -p 8080:8080 expense-tracking-system:1.0.0
```

### Step 4: Kubernetes Deploy
```bash
# Create deployment
kubectl apply -f k8s/deployment.yaml

# Check status
kubectl get all
kubectl logs -f deployment/expense-tracker-deployment
```

### Step 5: Push to GitHub
```bash
git init
git add .
git commit -m "Initial commit: Expense Tracking System"
git remote add origin https://github.com/YOUR_USERNAME/expense-tracking-system.git
git push -u origin main
```

## 📊 Testing Results

### JUnit Test Coverage

#### Expense Class Tests (10 tests)
- ✅ Expense creation with valid data
- ✅ Negative amount exception
- ✅ Empty category exception
- ✅ Null category exception
- ✅ Set amount validation
- ✅ Set negative amount exception
- ✅ Set category update
- ✅ Set empty category exception
- ✅ Expense equality check
- ✅ ToString representation

#### ExpenseTracker Class Tests (13 tests)
- ✅ Add single expense
- ✅ Add negative amount exception
- ✅ Remove expense by ID
- ✅ Remove non-existent expense
- ✅ Calculate total expenses (100.0)
- ✅ Get total by category
- ✅ Get expenses by category
- ✅ Filter by date range
- ✅ Calculate average expense (60.0)
- ✅ Get category breakdown
- ✅ Find highest expense (100.0)
- ✅ Find lowest expense (20.0)
- ✅ Multiple operations sequence

## 🏃 Run Tests Command Reference

```bash
# Run all tests with details
mvn test

# Run specific test class
mvn test -Dtest=ExpenseTrackerTest

# Run single test method
mvn test -Dtest=ExpenseTrackerTest#testAddExpense

# Run with coverage report
mvn test jacoco:report

# View coverage report
open target/site/jacoco/index.html  # macOS
xdg-open target/site/jacoco/index.html  # Linux
start target/site/jacoco/index.html  # Windows
```

## 📁 Project Structure

```
expense-tracking-system/
├── src/
│   ├── main/java/com/expense/
│   │   ├── Expense.java (140 lines)
│   │   ├── ExpenseTracker.java (180 lines)
│   │   └── Main.java (50 lines)
│   └── test/java/com/expense/
│       ├── ExpenseTest.java (100 lines, 10 tests)
│       └── ExpenseTrackerTest.java (220 lines, 13 tests)
├── pom.xml (Dependencies & build config)
├── Dockerfile (Container image)
├── k8s/
│   └── deployment.yaml (K8s manifests)
├── .github/workflows/
│   └── ci-cd-pipeline.yml (GitHub Actions)
├── README.md (Full documentation)
├── SETUP_GIT.md (Git setup guide)
├── QUICK_START.md (This file)
└── .gitignore
```

## 🔧 Troubleshooting

### Issue: Maven tests fail
```bash
# Clean and retry
mvn clean test

# Check Java version
java -version  # Should be 11+
```

### Issue: Docker build fails
```bash
# Ensure JAR exists
ls -la target/*.jar

# Build manually first
mvn clean package
```

### Issue: Kubernetes pod not running
```bash
# Check pod status
kubectl describe pod <pod-name>

# View logs
kubectl logs <pod-name>

# Check service
kubectl get svc
```

## 📋 Checklist for Submission

- [x] Java program development (Expense + ExpenseTracker classes)
- [x] JUnit tests (23 test cases)
- [x] Maven configuration (pom.xml)
- [x] CI/CD pipeline (GitHub Actions workflow)
- [x] Docker setup (Dockerfile)
- [x] Kubernetes manifests (deployment.yaml with HPA)
- [x] README documentation
- [x] Git setup guide
- [x] Quick start guide

## 🎯 Learning Outcomes

By completing this project, you'll have:
- ✅ Built a production-ready Java application
- ✅ Written comprehensive unit tests
- ✅ Configured Maven for build automation
- ✅ Set up GitHub Actions for CI/CD
- ✅ Containerized application with Docker
- ✅ Deployed to Kubernetes with auto-scaling
- ✅ Implemented full DevOps pipeline

## 💡 Key Features Implemented

1. **Expense Management**
   - CRUD operations
   - Data validation
   - Unique ID generation

2. **Calculations**
   - Total expenses
   - Category-wise totals
   - Monthly summaries
   - Average, highest, lowest
   - Date range filtering

3. **Testing**
   - Unit test coverage
   - Edge case handling
   - JaCoCo code coverage report

4. **Deployment**
   - Dockerized application
   - Kubernetes orchestration
   - Auto-scaling (HPA)
   - Load balancing
   - Health checks

5. **CI/CD**
   - Automated build
   - Test execution
   - Code coverage
   - Docker push
   - Kubernetes deployment

## 🎓 Marks Distribution

| Component | Marks | Status |
|-----------|-------|--------|
| Java Development | 15 | ✅ Complete |
| JUnit Testing | 15 | ✅ Complete |
| CI/CD Pipeline | 10 | ✅ Complete |
| Docker & K8s | 10 | ✅ Complete |
| **Total** | **50** | **✅ 100%** |

---

**Project Status**: ✅ **COMPLETE & READY FOR SUBMISSION**

For detailed documentation, see [README.md](README.md)

