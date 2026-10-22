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

The React TypeScript frontend for Warehouse Work Manager, generated with Vite. This checkpoint displays the starter UI independently; backend integration comes next.

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

1. Open a terminal in the `frontend` folder, beside `backend` in this repository.
2. Run `npm install` to install dependencies.
3. Run `npm run dev` to start Vite.
4. Open the Local URL printed in the terminal, normally [http://localhost:5173](http://localhost:5173). If that port is occupied, use the alternative URL Vite prints.
5. Confirm the heading and browser tab title display **Warehouse Work Manager**, and check that clicking the counter increases its value.

The backend can remain stopped. Keep the terminal running while using the frontend.

## Stop the app

Press **Ctrl+C** in the terminal where `npm run dev` is running. Restart from the `frontend` folder with `npm run dev`.

## Lint and build

Run these commands from the `frontend` folder:

```powershell
npm run lint
npm run build
```

- `npm run lint` checks source code with ESLint.
- `npm run build` runs TypeScript checking, then creates a production build in `dist`.

The starter currently has no automated frontend test suite. Lint and build checks complement manually checking the rendered page and counter.

## Version control

Commit source files, public assets, configuration, documentation, `package.json`, and `package-lock.json`. Dependencies in `node_modules`, generated output in `dist`, and local IDE settings are ignored.

See the [root README](../README.md) for backend startup, backend tests, and the next learning checkpoint.
