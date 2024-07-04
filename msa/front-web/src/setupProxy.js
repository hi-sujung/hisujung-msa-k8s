const { createProxyMiddleware } = require('http-proxy-middleware');

module.exports = function(app) {
  app.use(
    '/hisujung',
    createProxyMiddleware({
      // target: 'http://34.97.29.41:80', // 인그레스 IP 주소 추가
      target: 'http://abc.hisujung.com', // 도메인명 추가
      changeOrigin: true,
    })
  );
};

