// eslint-disable-next-line @typescript-eslint/no-var-requires
const {readFileSync} = require("fs");

module.exports = plop  => {
  plop.setGenerator('component', {
    description: 'Create a component',
    // User input prompts provided as arguments to the template
    prompts: [
      {
        type: 'list',
        name: 'type',
        message: 'Select the template types:',
        choices: [
          'component',
          'component-container',
          'page',
          'page-container',
          'hook',
          'service'
        ],
        default: 0
      },
      {
        // Raw text input
        type: 'input',
        // Variable name for this input
        name: 'name',
        // Prompt to display on command line
        message: 'What is your component name?',
      }
    ],
    actions: ({type}) =>{
      const typeDir = type.split('-')[0];
      const contextDir = `${ typeDir }s`;
      const seedIndex = (dir) => ({
        type: 'add',
        path: `src/${ dir }/index.ts`,
        template: '// EXPORTS\n',
        skipIfExists: true,
      });
      const appendIndex = (dir, importPath) => {
        const _export = `export * from "${ importPath }";`;
        const path = `src/${ dir }/index.ts`;
        return {
          type: 'modify',
          pattern: /(\/\/ EXPORTS)/g,
          skip: (data) => {
            const content = readFileSync(path, 'utf8');
            const renderedExport = plop.renderString(_export, data);
            const skip = content.includes(renderedExport);
            if (skip) {
              return `Skipping append ${ dir } export.`;
            }
          },
          path,
          template: `${_export}\n$1`,
        };
      };
      // Action types:
      // State => create data type
      // Component => functional component, index & test without state
      // Service => static json data, service & test file with state;
      // Hook => hook & hook test, + whatever state + whatever service create
      // Container => component + hook
      const stateActions = [
        {
          // Add a new file
          type: 'add',
          // Path for the new file
          path: 'src/types/{{pascalCase name}}State.ts',
          // Handlebars template used to generate content of new file
          templateFile: 'plop-templates/types/ReactState.ts.hbs',
          skipIfExists: true,
        },
        seedIndex("types"),
        appendIndex("types", "./{{pascalCase name}}State"),
      ];
      const componentActions = [
        {
          type: 'add',
          path: `src/${ contextDir }/{{pascalCase name}}/{{pascalCase name}}.tsx`,
          templateFile: `plop-templates/${ contextDir }/${ plop.getHelper("pascalCase")(typeDir) }.tsx.hbs`,
          skipIfExists: true,
        },
        {
          type: 'add',
          path: `src/${ contextDir }/{{pascalCase name}}/__tests__/{{pascalCase name}}.test.jsx`,
          templateFile: `plop-templates/${ contextDir }/__tests__/${ plop.getHelper("pascalCase")(typeDir) }.test.jsx.hbs`,
          skipIfExists: true,
        },
        {
          type: 'add',
          path: `src/${ contextDir }/{{pascalCase name}}/index.ts`,
          templateFile: `plop-templates/${ contextDir }/index.ts.hbs`,
          skipIfExists: true,
        },
      ];
      const serviceActions = [
        {
          type: 'add',
          path: 'public/assets/data/{{pascalCase name}}.json',
          templateFile: 'plop-templates/content/content.json',
          skipIfExists: true,
        },
        {
          type: 'add',
          path: 'src/services/{{pascalCase name}}Service.ts',
          templateFile: 'plop-templates/services/ComponentService.ts.hbs',
          skipIfExists: true,
        },
        ...stateActions,
        {
          type: 'add',
          path: 'src/services/__tests__/{{pascalCase name}}Service.test.js',
          templateFile: 'plop-templates/services/__tests__/ComponentService.test.js.hbs',
          skipIfExists: true,
        },
        seedIndex("services"),
        appendIndex("services", "./{{pascalCase name}}Service"),
      ];
      const hookActions = [
        {
          type: 'add',
          path: 'src/hooks/use{{pascalCase name}}.ts',
          templateFile: 'plop-templates/hooks/ReactHooks.ts.hbs',
          skipIfExists: true,
        },
        {
          type: 'add',
          path: 'src/hooks/__tests__/use{{pascalCase name}}.test.js',
          templateFile: 'plop-templates/hooks/__tests__/ReactHooks.test.js.hbs',
          skipIfExists: true,
        },
        seedIndex("hooks"),
        appendIndex("hooks", "./use{{pascalCase name}}"),
        ...serviceActions,
        ...stateActions,
      ];
      if (type === 'hook') {
        return hookActions;
      }
      if (type === 'service') {
        return serviceActions;
      }

      if (type === 'component' || type === 'page') {
        return componentActions;
      }

      if (type.endsWith('container')) {
        return [
          ...componentActions,
          ...hookActions,
          {
            type: 'add',
            path: `src/${ contextDir }/{{pascalCase name}}/{{pascalCase name}}Container.tsx`,
            templateFile: 'plop-templates/containers/Container.tsx.hbs',
          },
          {
            type: 'add',
            path: `src/${ contextDir }/{{pascalCase name}}/__tests__/{{pascalCase name}}Container.test.jsx`,
            templateFile: 'plop-templates/containers/__tests__/Container.test.jsx.hbs',
          },
          {
            type: 'add',
            path: `src/${ contextDir }/{{pascalCase name}}/index.ts`,
            templateFile: 'plop-templates/containers/index.ts.hbs',
            force: true,
          },
        ];
      }

      return [];
    },
  });
}