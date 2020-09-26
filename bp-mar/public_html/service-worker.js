var files = [
  "index.html",
  "manifest.json",
  "css/bootstrap.min.css",
  "css/bootstrap-theme.min.css",
  "css/main.css",
  "images/icons-192.png",
  "js/vendor/bootstrap.js",
  "js/vendor/bootstrap.min.js",
  "js/vendor/jquery-1.11.2.min.js",
  "js/vendor/modernizr-2.8.3-respond-1.4.2.min.js",
  "js/install.js",
  "js/main.js",
  "dados/soldos.json",
  "dados/aliquotas.json",
  "dados/adicional_habilitacao.json"
];
// dev only
if (typeof files == 'undefined') {
  var files = [];
} else {
  files.push('./');
}

var CACHE_NAME = 'bpmar-v1.1';

self.addEventListener('activate', function(event) {
  console.log('[SW] Activate');
  event.waitUntil(
    caches.keys().then(function(cacheNames) {
      return Promise.all(
        cacheNames.map(function(cacheName) {
          if (CACHE_NAME.indexOf(cacheName) == -1) {
            console.log('[SW] Delete cache:', cacheName);
            return caches.delete(cacheName);
          }
        })
      );
    })
  );
});

self.addEventListener('install', function(event){
  console.log('[SW] Install');
  event.waitUntil(
    caches.open(CACHE_NAME).then(function(cache) {
      return Promise.all(
      	files.map(function(file){
      		return cache.add(file);
      	})
      );
    })
  );
});

self.addEventListener('fetch', function(event) {
  console.log('[SW] fetch ' + event.request.url);
  event.respondWith(
    caches.match(event.request).then(function(response){
      return response || fetch(event.request.clone());
    })
  );
});

self.addEventListener('notificationclick', function(event) {
  console.log('On notification click: ', event);
  clients.openWindow('/');
});
