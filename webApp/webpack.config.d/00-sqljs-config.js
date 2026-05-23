config.resolve = config.resolve || {};
config.resolve.fallback = {
    ...config.resolve.fallback,
    "path": require.resolve("path-browserify"),
    "fs": false,
    "crypto": false,
    "buffer": false,
    "stream": false,
    "events": false,
    "vm": false,
    "assert": false,
    "util": false,
    "process": false,
}

const CopyPlugin = require("copy-webpack-plugin")
const path = require("path")

config.plugins.push(
    new CopyPlugin({
        patterns: [
            {
                from: path.resolve(
                    __dirname,
                    "../../../wasm/node_modules/sql.js/dist/sql-wasm.wasm"
                ),
                to: "sql-wasm.wasm",
            },
        ],
    })
)