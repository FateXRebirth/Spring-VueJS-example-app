// Initialize the Firebase app by passing in the messagingSenderId
var config = {
  apiKey: "AIzaSyCaQnAY13Kt6aQJBD-QkOm2hymfwow85IM",
  authDomain: "side-project-f8d62.firebaseapp.com",
  databaseURL: "https://side-project-f8d62.firebaseio.com",
  projectId: "side-project-f8d62",
  storageBucket: "side-project-f8d62.appspot.com",
  messagingSenderId: "618554667717",
  serverKey: "AAAAkAS7MsU:APA91bGlsMmWgzcoQubTT2rARJZaNpqHGtfVID5M1luv02G0n6MkyPP4J946RxX1BAVwYqEQXeSQid9_v7a5cGFOtmJrgMTZgAlYVyw-UV0wP6dDcUUUk2VHFWWb33bSpAZ2U4R339sl"
};
firebase.initializeApp(config);

$storage = firebase.storage().ref();

$messaging = firebase.messaging()
$messaging.usePublicVapidKey('BHUTBF3t8_VV49A8IziDb-2JFkj0S2sLNmrMcH3kECVl7WdYS_PbrWrtoRgpQrr51F-abvQSKUAn6Rr7BQWlfYs')

// Store Server Key
$messaging.getServerKey = function() {
  return 'AAAAkAS7MsU:APA91bGlsMmWgzcoQubTT2rARJZaNpqHGtfVID5M1luv02G0n6MkyPP4J946RxX1BAVwYqEQXeSQid9_v7a5cGFOtmJrgMTZgAlYVyw-UV0wP6dDcUUUk2VHFWWb33bSpAZ2U4R339sl';
}

navigator.serviceWorker.register('firebase-messaging-sw.js')
  .then(function (registration) {
    $messaging.useServiceWorker(registration);

    // Request for permission
    $messaging.requestPermission()
      .then(function () {
        console.log('Notification permission granted.');
        // TODO(developer): Retrieve an Instance ID token for use with FCM.
        $messaging.getToken()
          .then(function (currentToken) {
            if (currentToken) {

              // Store Token in Global
              window.RegistrationToken = currentToken;
              // console.log(`Global Token: ${window.RegistrationToken}`)

              sendTokenToServer(currentToken);
            } else {
              console.log('No Instance ID token available. Request permission to generate one.');
              setTokenSentToServer(false);
            }
          })
          .catch(function (err) {
            console.log('An error occurred while retrieving token. ', err);
            setTokenSentToServer(false);
          });
      })
      .catch(function (err) {
        console.log('Unable to get permission to notify.', err);
      });
  });

// Handle incoming messages
// $messaging.onMessage(function (payload) {
//   console.log("Notification received: ", payload);
// });

// Callback fired if Instance ID token is updated.
$messaging.onTokenRefresh(function () {
  $messaging.getToken()
    .then(function (refreshedToken) {
      console.log('Token refreshed.');
      // Indicate that the new Instance ID token has not yet been sent
      // to the app server.
      setTokenSentToServer(false);
      // Send Instance ID token to app server.
      sendTokenToServer(refreshedToken);
    })
    .catch(function (err) {
      console.log('Unable to retrieve refreshed token ', err);
    });
});

// Send the Instance ID token your application server, so that it can:
// - send messages back to this app
// - subscribe/unsubscribe the token from topics
function sendTokenToServer(currentToken) {
  if (!isTokenSentToServer()) {
    console.log('Sending token to server...');
    // TODO(developer): Send the current token to your server.
    setTokenSentToServer(true);
  } else {
    console.log('Token already sent to server so won\'t send it again ' +
      'unless it changes');
  }
}

function isTokenSentToServer() {
  return window.localStorage.getItem('sentToServer') == 1;
}

function setTokenSentToServer(sent) {
  window.localStorage.setItem('sentToServer', sent ? 1 : 0);
}
