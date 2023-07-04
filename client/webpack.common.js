const Path = require("path");

const HtmlWebpackPlugin = require("html-webpack-plugin");
const ForkTsCheckerWebpackPlugin = require("fork-ts-checker-webpack-plugin");
const MiniCssExtractPlugin = require("mini-css-extract-plugin");
const { merge } = require("webpack-merge");

const baseConfig = {
    context: Path.join(__dirname),
    entry: [Path.join(__dirname, "src", "index.tsx")],
    module: {
        rules: [
            {
                test: /\.tsx?$/,
                use: [
                    {
                        loader: "ts-loader",
                        options: {
                            transpileOnly: true
                        }
                    }
                ],
                exclude: /node_modules/
            },
            {
                test: /\.js$/,
                enforce: "pre",
                use: ["source-map-loader"]
            },
            {
                test: /\.css$/,
                use: ["style-loader", "css-loader"]
            },
            {
                test: /\.(png|jpe?g|gif|svg|woff|woff2)$/i,
                // More information here https://webpack.js.org/guides/asset-modules/
                type: "asset",
                generator: {
                    filename: "static/media/[hash][ext][query]"
                }
            }
        ]
    },
    output: {
        path: Path.join(__dirname, "target", "webpack"),
        filename: "[name].bundle.[contenthash:8].js",
        chunkFilename: "[name].chunk.[chunkhash:8].js"
    },
    plugins: [
        // Typescript type checking
        new ForkTsCheckerWebpackPlugin(),
        // minify
        new MiniCssExtractPlugin({
            filename: "training.frontend.[contenthash].css"
        }),
        new HtmlWebpackPlugin({
            hash: true,
            template: "./resources/html/index.html"
        })
    ],
    resolve: {
        extensions: [".tsx", ".ts", ".js"]
    }
};

const silent = merge(baseConfig, {
    mode: "development",
    devtool: "eval-source-map",
    entry: {
        silent_renew: Path.resolve(
            __dirname,
            "node_modules",
            "@com.mgmtp.a12.uaa",
            "uaa-authentication-client",
            "resources",
            "silent_renew.js"
        )
    },
    plugins: [
        new HtmlWebpackPlugin({
            minify: true,
            filename: "silent_renew.html"
        })
    ]
});

module.exports = {
    common: baseConfig,
    silent
};
