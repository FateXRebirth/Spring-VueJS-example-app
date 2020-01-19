// Give the service worker access to Firebase Messaging.
importScripts('https://www.gstatic.com/firebasejs/6.0.2/firebase-app.js')
importScripts('https://www.gstatic.com/firebasejs/6.0.2/firebase-messaging.js')

// Initialize the Firebase app in the service worker by passing in the messagingSenderId.
var config = {
  messagingSenderId: "618554667717"
};
firebase.initializeApp(config);

// Retrieve an instance of Firebase Data Messaging so that it can handle background messages.
$messaging = firebase.messaging()
$messaging.setBackgroundMessageHandler(function(payload) {
  const notificationTitle = 'Notification';
  const notificationOptions = {
    body: "You have an new message!",
    icon: 'logo.png'
  };

  return self.registration.showNotification(notificationTitle,
      notificationOptions);
});
