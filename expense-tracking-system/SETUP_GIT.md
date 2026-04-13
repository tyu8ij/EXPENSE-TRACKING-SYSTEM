# Setting up GitHub Repository

## Steps to Initialize Git Repository

### 1. Initialize Git
```bash
cd expense-tracking-system
git init
git config user.name "Your Name"
git config user.email "your.email@example.com"
```

### 2. Add All Files
```bash
git add .
git status  # Review changes
```

### 3. Create Initial Commit
```bash
git commit -m "Initial commit: Complete Expense Tracking System

- Java program development with expense tracking logic
- JUnit tests for all components (23 test cases)
- Maven build configuration
- Docker containerization
- Kubernetes deployment manifests
- GitHub Actions CI/CD pipeline
- Complete documentation"
```

### 4. Add Remote Repository
```bash
git remote add origin https://github.com/YOUR_USERNAME/expense-tracking-system.git
git branch -M main
git push -u origin main
```

## GitHub Configuration

### 1. Create Repository on GitHub
- Go to https://github.com/new
- Repository name: `expense-tracking-system`
- Description: "A comprehensive expense tracking system with testing and deployment capabilities"
- Initialize with: No

### 2. Set Up Protected Branch
- Settings → Branches
- Add rule for `main` branch
- Require pull request reviews
- Require status checks to pass

### 3. Add Secrets for CI/CD
- Settings → Secrets and variables → Actions
- Add:
  - `DOCKER_USERNAME`: Your Docker Hub username
  - `DOCKER_PASSWORD`: Your Docker Hub password
  - `K8S_URL`: Your Kubernetes API URL
  - `K8S_SECRET`: Your Kubernetes service account secret

### 4. Enable Actions
- Actions → General
- Allow all actions and reusable workflows

### 5. Configure Codeowners
Create `.github/CODEOWNERS`:
```
* @YOUR_USERNAME
```

## Workflow

### Development
```bash
# Create feature branch
git checkout -b feature/new-feature

# Make changes and commit
git add .
git commit -m "Add new feature"

# Push to remote
git push origin feature/new-feature

# Create Pull Request on GitHub
```

### Merging to Main
1. Create Pull Request
2. GitHub Actions will run CI/CD pipeline
3. Wait for all checks to pass
4. Review and merge
5. Automatic deployment to production (Kubernetes)

## Monitoring CI/CD

### GitHub Actions Dashboard
- Go to Actions tab in GitHub
- Monitor workflow runs
- Check logs for each job
- View artifacts (JAR files, reports)

### Docker Hub
- Monitor image builds
- Track image versions and tags
- Set up webhooks if needed

### Kubernetes Monitoring
```bash
# Watch deployment
kubectl get deployments -w

# View pod status
kubectl get pods

# Check service
kubectl get svc

# View logs
kubectl logs -f deployment/expense-tracker-deployment
```

## Troubleshooting

### Pipeline Failures
1. Check GitHub Actions logs
2. Verify Maven build locally: `mvn clean package`
3. Check Docker build: `docker build .`
4. Validate Kubernetes manifests: `kubectl apply -f k8s/deployment.yaml --dry-run=client`

### Docker Push Failed
```bash
# Login to Docker Hub
docker login

# Verify credentials in GitHub Secrets
# Allow 2FA if enabled
```

### Kubernetes Deployment Issues
```bash
# Check pod logs
kubectl logs pod-name

# Describe pod
kubectl describe pod pod-name

# Check service endpoints
kubectl get endpoints
```

## Best Practices

1. **Always write tests first** - Follow TDD
2. **Code review** - Use pull requests
3. **Semantic commits** - Use meaningful commit messages
4. **Monitor deployments** - Check logs and metrics
5. **Keep dependencies updated** - Run `mvn dependency:update-check`

