const Path = require("path");

const TerserPlugin = require("terser-webpack-plugin");
const CopyWebpackPlugin = require("copy-webpack-plugin");
const { merge } = require("webpack-merge");

const { common, silent } = require("./webpack.common.js");

const production = {
    mode: "production",
    plugins: [
        new TerserPlugin(),
        new CopyWebpackPlugin({
            patterns: [
                {
                    from: Path.join(
                        __dirname,
                        "node_modules",
                        "@com.mgmtp.a12.uaa",
                        "uaa-authentication-client",
                        "resources",
                        "images"
                    ),
                    to: Path.resolve(__dirname, "target/webpack")
                },
                {
                    from: Path.join(__dirname, "resources", "html", "images"),
                    to: Path.resolve(__dirname, "target/webpack/images")
                }
            ]
        })
    ],
    optimization: {
        moduleIds: "deterministic",
        splitChunks: {
            cacheGroups: {
                // Split a chunk for a12 dependencies under node_module/@com.mgmtp.a12
                a12: {
                    test: module => {
                        return (
                            module.context &&
                            module.context.indexOf("node_modules") !== -1 &&
                            module.context.indexOf("@com.mgmtp.a12") !== -1
                        );
                    },
                    name: "vendor-a12",
                    chunks: "all"
                },
                // And another the chunk for the rest
                others: {
                    test: module => {
                        return (
                            module.context &&
                            module.context.indexOf("node_modules") !== -1 &&
                            module.context.indexOf("@com.mgmtp.a12") === -1
                        );
                    },
                    name: "vendor-others",
                    chunks: "all"
                }
            }
        }
    }
};

module.exports = [merge(common, production), silent];
