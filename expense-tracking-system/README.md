# Expense Tracking System

A comprehensive expense tracking system built with Java, featuring automated testing, CI/CD pipeline, Docker containerization, and Kubernetes deployment.

## 📋 Project Overview

This project implements a complete expense tracking solution with the following components:

- **Java Program Development** (15 marks)
  - Core business logic for expense management
  - Expense model and ExpenseTracker manager class
  - Comprehensive calculation methods (total, category-wise, monthly, average, etc.)

- **JUnit Testing** (15 marks)
  - Unit tests for Expense class (10 test cases)
  - Comprehensive tests for ExpenseTracker (13 test cases)
  - Test coverage for edge cases and error conditions

- **CI/CD Pipeline Creation** (10 marks)
  - GitHub Actions workflow for automated testing
  - Maven-based build process
  - Automated unit test execution
  - Code coverage reports with JaCoCo

- **Docker and Kubernetes Deployment** (10 marks)
  - Dockerfile for containerizing the application
  - Kubernetes Deployment with 3 replicas
  - Kubernetes Service for load balancing
  - Horizontal Pod Autoscaler (HPA)

## 🏗️ Project Structure

```
expense-tracking-system/
├── src/
│   ├── main/java/com/expense/
│   │   ├── Expense.java           # Expense model class
│   │   ├── ExpenseTracker.java    # Main tracker logic
│   │   └── Main.java              # Demo application
│   └── test/java/com/expense/
│       ├── ExpenseTest.java       # Tests for Expense class
│       └── ExpenseTrackerTest.java # Tests for ExpenseTracker
├── pom.xml                        # Maven configuration
├── Dockerfile                     # Docker image configuration
├── k8s/
│   └── deployment.yaml           # Kubernetes manifests
├── .github/workflows/
│   └── ci-cd-pipeline.yml        # GitHub Actions CI/CD
└── README.md                      # This file
```

## 🚀 Getting Started

### Prerequisites
- Java 11 or higher
- Apache Maven 3.6+
- Docker
- Kubernetes cluster (kubectl)
- Git

### Local Setup

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd expense-tracking-system
   ```

2. **Build the project**
   ```bash
   mvn clean compile
   ```

3. **Run tests**
   ```bash
   mvn test
   ```

4. **Package the application**
   ```bash
   mvn package
   ```

5. **Run the application**
   ```bash
   java -jar target/expense-tracking-system-1.0.0.jar
   ```

## 📊 Features

### Expense Management
- Add, remove, and update expenses
- Categorize expenses
- Track expense dates and descriptions

### Calculations
- **Total Expenses**: Sum of all expenses
- **Category Breakdown**: Total expenses by category
- **Date Range Query**: Filter expenses by date range
- **Monthly Summary**: Total expenses for specific months
- **Average Expense**: Calculate average expense amount
- **Highest/Lowest Expense**: Find minimum and maximum expenses

### Tracking
- Unique ID generation for each expense
- Complete CRUD operations
- Data validation and error handling

## 🧪 Testing

### Test Coverage
- **Expense Tests**: 10 test cases covering model validation
- **ExpenseTracker Tests**: 13 test cases covering all operations
- Total: 23 comprehensive unit tests

### Running Tests
```bash
# Run all tests
mvn test

# Run specific test class
mvn test -Dtest=ExpenseTrackerTest

# Generate coverage report
mvn test jacoco:report
```

Coverage report available at: `target/site/jacoco/index.html`

## 🐳 Docker

### Build Docker Image
```bash
mvn clean package
docker build -t expense-tracking-system:1.0.0 .
```

### Run Container
```bash
docker run -d -p 8080:8080 --name expense-tracker expense-tracking-system:1.0.0
```

### Push to Registry
```bash
docker tag expense-tracking-system:1.0.0 <registry>/expense-tracking-system:latest
docker push <registry>/expense-tracking-system:latest
```

## ☸️ Kubernetes Deployment

### Prerequisites
- Active Kubernetes cluster
- kubectl configured
- Docker image pushed to accessible registry

### Deploy to Kubernetes
```bash
# Apply deployment configuration
kubectl apply -f k8s/deployment.yaml

# Check deployment status
kubectl get deployments
kubectl get pods
kubectl get services

# View logs
kubectl logs -f deployment/expense-tracker-deployment

# Scale deployment
kubectl scale deployment expense-tracker-deployment --replicas=5
```

### Kubernetes Resources Deployed
1. **Deployment**: 3 replicas with health checks
2. **Service**: LoadBalancer type for external access
3. **HPA**: Auto-scaling based on CPU and memory metrics

## 🔄 CI/CD Pipeline

The GitHub Actions workflow includes:

### Build and Test Job
- Checkout code
- Setup Java 11
- Compile code
- Run JUnit tests
- Generate code coverage
- Package JAR file
- Upload artifacts

### Docker Build Job
- Download artifacts
- Build Docker image
- Push to Docker Hub
- Cache Docker layers

### Kubernetes Deploy Job (Main branch only)
- Set up Kubernetes context
- Deploy/update resources
- Verify deployment status
- Display service information

### Validation Job
- Check all previous jobs
- Send notifications

## 📝 Configuration

### Environment Variables
In `k8s/deployment.yaml`:
- `APP_NAME`: Application name
- `LOG_LEVEL`: Logging level (INFO, DEBUG, ERROR)

### GitHub Secrets Required
For CI/CD pipeline:
- `DOCKER_USERNAME`: Docker Hub username
- `DOCKER_PASSWORD`: Docker Hub password
- `K8S_URL`: Kubernetes API URL
- `K8S_SECRET`: Kubernetes service account secret

## 📈 Performance Metrics

### Resource Limits (Kubernetes)
- **Memory Request**: 256Mi
- **Memory Limit**: 512Mi
- **CPU Request**: 250m
- **CPU Limit**: 500m

### Auto-scaling Configuration
- **Min Replicas**: 2
- **Max Replicas**: 5
- **CPU Target**: 70% utilization
- **Memory Target**: 80% utilization

## 🛠️ Development

### Adding New Features
1. Create feature branch
2. Write tests first (TDD approach)
3. Implement feature
4. Run all tests: `mvn test`
5. Commit changes
6. Create pull request

### Code Style
- Follow Java naming conventions
- Use meaningful variable names
- Add JavaDoc comments for public methods
- Keep methods focused and small

## 📚 API Examples

### Creating ExpenseTracker
```java
ExpenseTracker tracker = new ExpenseTracker();
```

### Adding an Expense
```java
tracker.addExpense("Food", 50.0, "Lunch", LocalDate.now());
```

### Getting Total Expenses
```java
double total = tracker.getTotalExpenses();
```

### Category Breakdown
```java
Map<String, Double> breakdown = tracker.getCategoryBreakdown();
```

### Date Range Query
```java
List<Expense> expenses = tracker.getExpensesByDateRange(
    LocalDate.of(2024, 1, 1),
    LocalDate.of(2024, 1, 31)
);
```

## 🐛 Troubleshooting

### Maven Build Issues
```bash
# Clear Maven cache
mvn clean

# Update dependencies
mvn dependency:resolve
```

### Docker Issues
```bash
# Check image
docker images | grep expense

# View logs
docker logs expense-tracker

# Remove container
docker rm -f expense-tracker
```

### Kubernetes Issues
```bash
# Check pod logs
kubectl logs pod-name

# Describe pod for events
kubectl describe pod pod-name

# Check service endpoints
kubectl get endpoints
```

## 📝 License

This project is created for educational purposes.

## ✅ Marks Distribution

| Component | Marks |
|-----------|-------|
| Java Program Development | 15 |
| JUnit Testing | 15 |
| CI/CD Pipeline | 10 |
| Docker & Kubernetes | 10 |
| **Total** | **50** |

## 📞 Support

For questions or issues, please review:
- Test cases for usage examples
- JavaDoc comments in source code
- GitHub Actions workflows for pipeline details
- Kubernetes manifests for deployment configuration

---

**Status**: ✅ Complete and Ready for Production

