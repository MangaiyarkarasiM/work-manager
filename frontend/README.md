# React + TypeScript + Vite

This template provides a minimal setup to get React working in Vite with HMR and some ESLint rules.

Currently, two official plugins are available:

- [@vitejs/plugin-react](https://github.com/vitejs/vite-plugin-react/blob/main/packages/plugin-react) uses [Oxc](https://oxc.rs)
- [@vitejs/plugin-react-swc](https://github.com/vitejs/vite-plugin-react/blob/main/packages/plugin-react-swc) uses [SWC](https://swc.rs/)

## React Compiler

The React Compiler is not enabled on this template because of its impact on dev & build performances. To add it, see [this documentation](https://react.dev/learn/react-compiler/installation).

## Expanding the ESLint configuration

If you are developing a production application, we recommend updating the configuration to enable type-aware lint rules:

```js
export default defineConfig([
  globalIgnores(['dist']),
  {
    files: ['**/*.{ts,tsx}'],
    extends: [
      // Other configs...

      // Remove tseslint.configs.recommended and replace with this
      tseslint.configs.recommendedTypeChecked,
      // Alternatively, use this for stricter rules
      tseslint.configs.strictTypeChecked,
      // Optionally, add this for stylistic rules
      tseslint.configs.stylisticTypeChecked,

      // Other configs...
    ],
    languageOptions: {
      parserOptions: {
        project: ['./tsconfig.node.json', './tsconfig.app.json'],
        tsconfigRootDir: import.meta.dirname,
      },
      // other options...
    },
  },
])

```

You can also install [eslint-plugin-react-x](https://npmx.dev/package/eslint-plugin-react-x) and [eslint-plugin-react-dom](https://npmx.dev/package/eslint-plugin-react-dom) for React-specific lint rules:

```js
// eslint.config.js
import reactX from 'eslint-plugin-react-x'
import reactDom from 'eslint-plugin-react-dom'

export default defineConfig([
  globalIgnores(['dist']),
  {
    files: ['**/*.{ts,tsx}'],
    extends: [
      // Other configs...
      // Enable lint rules for React
      reactX.configs['recommended-typescript'],
      // Enable lint rules for React DOM
      reactDom.configs.recommended,
    ],
    languageOptions: {
      parserOptions: {
        project: ['./tsconfig.node.json', './tsconfig.app.json'],
        tsconfigRootDir: import.meta.dirname,
      },
      // other options...
    },
  },
])

```

# Warehouse Work Manager

The React TypeScript frontend for Warehouse Work Manager, generated with Vite. Its application information panel loads data from Spring Boot through the Vite development proxy and handles loading, errors, and request cancellation.

## Prerequisites and verified versions

Node.js and npm are required. This setup was verified with Node.js **24.21.0** and npm **11.19.0**; these are the tested versions rather than a declaration of minimum supported versions.

The current lockfile records:

| Tool | Version |
|---|---|
| React and React DOM | 19.3.1 |
| Vite | 8.3.5 |
| TypeScript | 6.0.3 |
| ESLint | 10.13.0 |

`package.json` declares dependency ranges, while `package-lock.json` records exact resolved versions. For example, the declared Vite range `^8.3.4` allows the locked version 8.3.5. Commit both package files.

## Start the app

1. Start the Spring Boot backend in IntelliJ and verify [http://localhost:8080/api/info](http://localhost:8080/api/info). See the [backend startup instructions](../README.md#start-and-stop-the-backend-in-intellij).
2. Open a terminal in the `frontend` folder, beside `backend` in this repository.
3. Run `npm install` to install dependencies.
4. Run `npm run dev` to start Vite.
5. Open the Local URL printed in the terminal, normally [http://localhost:5173](http://localhost:5173). If that port is occupied, use the alternative URL Vite prints.
6. Confirm the heading and browser tab title display **Warehouse Work Manager**, the information panel displays the backend name and message, and clicking the counter increases its value.

Keep the backend and Vite running to load application information. If the backend is stopped, the frontend still renders and displays a friendly API error after the request fails.

## Stop the app

Press **Ctrl+C** in the terminal where `npm run dev` is running. Restart from the `frontend` folder with `npm run dev`.

## API access and development proxy

- [infoApi.ts](src/features/info/infoApi.ts) defines `InfoResponse` with the string fields `applicationName` and `message`, requests `/api/info` using `fetch`, checks the HTTP status, and parses JSON. It accepts an `AbortSignal` from the component.
- [InfoPanel.tsx](src/features/info/InfoPanel.tsx) owns the loading, data, and error states. It starts the request on mount, cancels it during Effect cleanup, and checks the signal before updating state. Cancelled requests do not update the error or finish loading for the active request.
- [vite.config.ts](vite.config.ts) forwards `/api` requests to `http://localhost:8080` without removing the `/api` prefix. Restart Vite after changing its proxy configuration.

Use `/api/info` as the browser request path so Vite can forward it. To isolate proxy issues, compare [the direct backend response](http://localhost:8080/api/info) with [the proxied response](http://localhost:5173/api/info), substituting the actual Vite port if needed.

The development proxy is not embedded in the production JavaScript bundle. Production API routing will be configured in a later checkpoint.

## Verify the integration

Open DevTools before reloading. Select Fetch/XHR for requests initiated by React, or All/Doc for direct address-bar navigation. The React request URL shows Vite's port because forwarding happens on the server.

| Scenario | Action | Expected result |
|---|---|---|
| Success | Run both applications and reload | The information panel displays `Warehouse Work Manager` and the backend message |
| Loading | Apply network throttling and reload | Loading text appears while the request is pending, followed by data |
| Backend unavailable | Stop Spring Boot and reload the frontend | A friendly error appears and loading finishes; Vite may log a connection-refused error |
| Recovery | Restart Spring Boot and reload the frontend | Information appears again; this version recovers by making a new request on reload |
| Cancellation | Reload with Strict Mode enabled | Cleanup may cancel the first development request; cancellation does not display a failure or end loading for the active request |
| Counter interaction | Click the counter after information loads | The counter changes without starting another information request |

Restore normal network throttling after testing. React Strict Mode intentionally performs an extra Effect setup/cleanup cycle during development, so a cancelled request and a `Fetch aborted` console message can be expected. Genuine request failures are logged to the developer console and shown as a friendly message in the UI.

## Lint and build

Run these commands from the `frontend` folder:

```powershell
npm run lint
npm run build
```

- `npm run lint` checks source code with ESLint.
- `npm run build` runs TypeScript checking, then creates a production build in `dist`.

There is currently no automated frontend test suite. Run lint and build, then use the manual integration scenarios above to check loading, success, failure, recovery, and cancellation.

## Version control

Commit source files, public assets, configuration, documentation, `package.json`, and `package-lock.json`. Dependencies in `node_modules`, generated output in `dist`, and local IDE settings are ignored.

See the [root README](../README.md) for backend startup, backend tests, and the next learning checkpoint.
