module.exports = {
    root: true,
    ignorePatterns: ["target/", "lib/"],
    env: {
        browser: true,
        node: true
    },
    parser: "@typescript-eslint/parser",
    extends: [
        "eslint:recommended",
        "plugin:@typescript-eslint/eslint-recommended",
        "plugin:@typescript-eslint/recommended",
        "plugin:import/typescript",
        "plugin:react/recommended",
        "plugin:react-hooks/recommended",
        "prettier"
    ],
    settings: {
        "import/internal-regex": "^@com.mgmtp.a12",
        react: {
            version: "detect"
        }
    },
    plugins: ["@typescript-eslint", "import"],
    rules: {
        "import/order": [
            "error",
            {
                groups: ["builtin", "external", "internal", "parent", "sibling", "index"],
                pathGroups: [
                    {
                        pattern: "../**",
                        group: "parent",
                        position: "after"
                    }
                ],
                "newlines-between": "always"
            }
        ],
        "import/newline-after-import": ["error", { count: 1 }],
        curly: "error"
    },
    overrides: [
        {
            files: ["*.js"],
            rules: {
                "@typescript-eslint/no-require-imports": "off",
                "@typescript-eslint/no-var-requires": "off"
            }
        }
    ]
};
