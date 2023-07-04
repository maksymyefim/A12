const Fs = require("fs");
const Path = require("path");
const dns = require("dns");

const Webpack = require("webpack");
const { merge } = require("webpack-merge");

const { common, silent } = require("./webpack.common.js");

const packageJson = JSON.parse(Fs.readFileSync(Path.join(__dirname, "package.json")));

// Fix localhost resolving in Node 17+
dns.setDefaultResultOrder("ipv4first");

const development = {
    mode: "development",
    devtool: "eval-source-map",
    devServer: {
        hot: true,
        port: 8081,
        static: [
            {
                directory: Path.join(__dirname, "resources", "html"),
                watch: true
            },
            {
                directory: Path.join(
                    __dirname,
                    "node_modules",
                    "@com.mgmtp.a12.uaa",
                    "uaa-authentication-client",
                    "resources",
                    "images"
                )
            }
        ],
        devMiddleware: {
            publicPath: ""
        },
        proxy: [
            {
                context: ["/api"],
                target: "http://localhost:8082",
                secure: false,
                changeOrigin: true,
                logLevel: "debug"
            }
        ]
    },
    plugins: [
        // Variables injected into the application
        // Reg. JSON.stringify, see https://stackoverflow.com/questions/39564802
        new Webpack.DefinePlugin({
            __VERSION__: JSON.stringify(
                "client: " +
                    packageJson.version +
                    ", server: " +
                    packageJson.devDependencies["@com.mgmtp.a12/server-connector"]
            ),
            __SHORT_VERSION__: JSON.stringify(packageJson.version),
            // Styled components build flag
            SC_DISABLE_SPEEDY: false
        })
    ]
};

module.exports = [merge(common, development), silent];
