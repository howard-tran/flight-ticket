const { createProxyMiddleware } = require('http-proxy-middleware');

module.exports = function (app) {
    //golang backend
    app.use(
        '/go/*',
        createProxyMiddleware({
            target: 'http://localhost:8181',
            changeOrigin: true,
            pathRewrite: {
                '^/go/': '/'
             },
        })
    );

    //Java backend api
    app.use(
        '/java/*',
        createProxyMiddleware({
            ws: true,
            target: 'http://localhost:8185',
            changeOrigin: true,
            pathRewrite: {
                '^/java/': '/'
            },
        })
    );

    //cdn backend api
    app.use(
        '/cdn/*',
        createProxyMiddleware({
            target: 'http://localhost:8182',
            changeOrigin: true,
            pathRewrite: {
                '^/cdn/': '/'
            },
        })
    );

    //nodejs backend
    app.use(
        '/node/*',
        createProxyMiddleware({
            target: 'http://localhost:8186',
            changeOrigin: true,
            pathRewrite: {
                '^/node/': '/'
            },
        })
    );
};