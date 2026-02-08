
# Student Details – Spring Boot (Java 8) with Gradle, JaCoCo & SonarQube

Minimal Spring Boot service to manage students, configured with:
- Java 8
- Spring Boot 2.7.18
- Gradle build
- JaCoCo test coverage
- SonarQube analysis
- H2 in-memory DB (for instant run & tests)

## Run locally
```bash
./gradlew clean bootRun   # macOS/Linux
# or
gradlew.bat clean bootRun # Windows
```

The app starts on http://localhost:8080

## APIs
- `POST /api/students` – create a student
- `GET  /api/students` – list students

Example POST body:
```json
{ "name": "Harini", "email": "harini@example.com", "age": 23 }
```

## Tests & Coverage (JaCoCo)
```bash
./gradlew clean test jacocoTestReport
# HTML report: build/reports/jacoco/test/html/index.html
```

## SonarQube Analysis
1. Start SonarQube (http://localhost:9000) and create a token.
2. Run analysis (replace `YOUR_TOKEN`):
```bash
./gradlew sonar -Dsonar.host.url=http://localhost:9000 -Dsonar.token=YOUR_TOKEN
```

> The build.gradle already points Sonar to the JaCoCo XML report.

## Notes
- Uses H2 in-memory DB by default (no config needed).
- To use PostgreSQL, add dependency `org.postgresql:postgresql` and set datasource properties.
