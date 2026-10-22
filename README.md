# Warehouse Work Manager

A learning application built one checkpoint at a time with Spring Boot and React with TypeScript.

The current checkpoint contains a backend application information endpoint, automated MVC tests, and a React TypeScript frontend generated with Vite. Both applications run independently; connecting the frontend to the backend is the next checkpoint.

## Requirements

- JDK 17. Configure both the IntelliJ project SDK and Gradle JVM to use Java 17.
- IntelliJ IDEA with Java and Gradle support, or a terminal for the optional commands below.
- Git for version control.
- Node.js and npm for the frontend. Setup was verified with Node.js 24.21.0 and npm 11.19.0.
- Internet access for the first Gradle import and npm installation to download build tools and dependencies.

The backend uses Spring Boot 4.1.1 and the Gradle 9.7.1 wrapper. A separate Gradle installation is not required. This checkpoint does not require a database or credentials.

## Repository layout

- `backend/` - Spring Boot application, Gradle wrapper, configuration, and tests.
- `frontend/` - React TypeScript application, Vite configuration, and npm package files. See the [frontend setup instructions](frontend/README.md#warehouse-work-manager).
- `.gitignore` - shared ignore rules for both applications.
- `README.md` - setup, verification, and learning notes.

## Start and stop the frontend

For prerequisites, verified versions, and detailed instructions, see the [frontend README](frontend/README.md#warehouse-work-manager).

From the repository root, open a terminal and run:

```powershell
cd frontend
npm install
npm run dev
```

Open the Local URL printed by Vite, normally [http://localhost:5173](http://localhost:5173). If the port is occupied, Vite may select a different one; use the printed URL. Keep the terminal running, and press Ctrl+C in that terminal to stop the frontend. The backend can remain stopped during this checkpoint.

From the `frontend` folder, run `npm run lint` for ESLint checks and `npm run build` for TypeScript checking and the production build. Generated output is written to `frontend/dist/`, relative to the repository root, and is ignored by Git.

## Start and stop the backend in IntelliJ

1. Open the `backend` folder as a Gradle project and allow import to finish.
2. Set the project SDK and Gradle JVM to Java 17. Select the project's Gradle wrapper rather than a separately installed Gradle version.
3. Open `WarehouseWorkManagerApplication` in the `com.example.warehouse` package.
4. Use the Run action beside its `main` method.
5. Wait for the successful application startup message and confirm the server's listening port in the console. The current configuration uses the default port, 8080.
6. Open http://localhost:8080/api/info in a browser.
7. Stop the application using IntelliJ's Stop button in the Run window.

If port 8080 is already occupied, stop the other process or intentionally configure a different `server.port` and use that port in the URL.

## API checkpoint

Request: `GET /api/info`

Expected status: `200 OK`.

Expected response content type: `application/json`.

Expected response body:

```json
{
  "applicationName": "Warehouse Work Manager",
  "message": "Hello from Warehouse Work Manager application"
}
```

There is no endpoint at `/`; a 404 at http://localhost:8080/ is expected.

When inspecting a direct browser navigation, open DevTools, select Network and All (or Doc), and reload the page. Direct navigations appear as document requests, so a Fetch/XHR filter hides them.

## Run tests in IntelliJ

1. In Settings > Build, Execution, Deployment > Build Tools > Gradle, select Gradle for Run tests using.
2. Run `InfoControllerTest` using its gutter Run action to execute the three controller tests.
3. Run all tests from the test source folder to include `WarehouseWorkManagerApplicationTests`.
4. Check that all four test methods pass with no failures, errors, or skipped tests.

The application does not need to be running for these tests. The default startup test and the MVC tests initialize their own Spring contexts without starting a listening HTTP server.

The tests cover:

- Spring application context initialization.
- GET `/api/info`: status 200, JSON content type, and the expected application name and message.
- GET `/api/does-not-exist`: status 404.
- POST `/api/info`: status 405.

## Optional PowerShell commands

Run these commands from the `backend` folder:

```powershell
# Start the application; press Ctrl+C to stop it.
.\gradlew.bat bootRun

# Run the full test suite.
.\gradlew.bat test

# Run only the controller tests.
.\gradlew.bat test --tests "com.example.warehouse.info.InfoControllerTest"
```

Gradle's HTML test report is generated at `backend/build/reports/tests/test/index.html`, relative to the repository root.

## Configuration and version control

Shared configuration lives in `backend/src/main/resources/application.properties`; it currently sets only the Spring application name.

Commit source code, tests, shared configuration, build definitions, the Gradle wrapper (including its JAR), and repository documentation. Generated build output, caches, local IDE settings, logs, and local environment files are ignored.

Keep future credentials in environment variables or deliberately ignored local configuration. Any committed `.env.example` must contain placeholder values only. Ignore rules do not make Spring Boot load `.env` files automatically.

## Next learning checkpoint

Configure the frontend development proxy for `/api` to target the backend, then display the `/api/info` response with loading and error states.
