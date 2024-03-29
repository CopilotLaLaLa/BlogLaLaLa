const webpack = require('webpack')

module.exports = {
    publicPath: './',
    assetsDir: 'static',
    productionSourceMap: false,
    configureWebpack: {
        plugins: [
            new webpack.ProvidePlugin({
                $: "jquery",
                jQuery: "jquery",
                "windows.jQuery": "jquery"
            })
        ]
    },
    devServer: {
        port: '8080',
        proxy: {
            '/api': {
                pathRewrite: {
                    '^/api': ''
                },
                target: 'http://localhost:8081/',
                changeOrigin: true,
            }
        }
    },
};