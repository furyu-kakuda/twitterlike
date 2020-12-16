const path = require('path');
const resolve = (dir) => {
    return path.resolve(__dirname, '..', dir)
}

const VueLoaderPlugin = require('vue-loader/lib/plugin')
const HtmlWebpackPlugin = require('html-webpack-plugin')
const BundleAnalyzerPlugin = require('webpack-bundle-analyzer').BundleAnalyzerPlugin

module.exports = {
    entry: {'hoge': './src/main.ts'},
    output: {
        path: resolve("public/javascripts"),
        publicPath: '/assets/javascripts/',
        filename: '[name]-[hash].bundle.js'
    },
    plugins: [
        new VueLoaderPlugin(),
        new BundleAnalyzerPlugin({
            "analyzerMode": "static",
            "openAnalyzer": false
        }),
        new HtmlWebpackPlugin({
            filename: '../conf/build/hoge.ssp',
            template: '../conf/views/index.ssp',
            inject: true,
            chunks:['hoge']
        })
    ],
    resolve: {
        alias: {
            "@": resolve('src')
        },
        extensions: [".js",".ts",".vue"]
    },
    module: {
        rules: [
            {
                test: /\.vue$/,
                exclude: /node_modules/,
                loader: 'vue-loader',
                options: {
                    loaders: {
                        // .vue内のscriptに対してbabel-loaderを動かすが.babelrcを見ているという……
                        js: 'babel-loader',
                        ts: 'ts-loader',
                        scss: 'vue-style-loader!css-loader!sass-loader'
                    }
                }
            },
            {
                test: /\.js?$/,
                exclude: /node_modules/,
                loader: 'babel-loader'
            },
            {
                test: /\.ts?$/,
                exclude: /node_modules/,
                loader: 'ts-loader',
                options: {
                    appendTsSuffixTo: [/\.vue$/]
                }
            },
            {
                test: /\.s(a|c)ss$/,
                use: [
                    {
                        loader: 'style-loader'
                    },
                    {
                        loader: 'css-loader'
                    },
                    {
                        loader: "postcss-loader",
                        options: {
                            ident: 'postcss',
                            plugins: [
                                require('tailwindcss'),
                                require('autoprefixer')
                            ]
                        }
                    },
                    {
                        loader: 'sass-loader'
                    }
                ]
            }
        ]
    },
    devtool: 'inline-source-map',
    mode: 'development'

};
