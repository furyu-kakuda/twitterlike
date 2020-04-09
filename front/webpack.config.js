const path = require('path');
const resolve = (dir) => {
    return path.resolve(__dirname, '..', dir)
}

const VueLoaderPlugin = require('vue-loader/lib/plugin')
const HtmlWebpackPlugin = require('html-webpack-plugin')

module.exports = {
    entry: "./src/main.ts",
    output: {
        path: resolve("target/web/public/main/javascripts"),
        publicPath: '/assets/javascripts/',
        filename: "main.min.js"
    },
    plugins: [
        new VueLoaderPlugin(),
        new HtmlWebpackPlugin({
            filename: '../conf/build/hoge.ssp',
            template: '../conf/views/index.ssp',
            inject: 'body',
            minify: true
        })
    ],
    resolve: {
        alias: {
            "@": resolve('src')
        }
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
