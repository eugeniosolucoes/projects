if ('serviceWorker' in navigator && (window.location.protocol === 'https:' || window.location.hostname === 'localhost' || window.location.hostname === '192.168.0.12')) {
  
  navigator.serviceWorker.register('service-worker.js', {
    scope: './'
  }).then(function(registration) {
    if (typeof registration.update == 'function') {
      registration.update();
      console.log('ServiceWorker registration successful with scope: ', registration.scope);
    }
  }).catch(function(e) {
    console.error('Error during service worker registration:', e);
  });
  
}