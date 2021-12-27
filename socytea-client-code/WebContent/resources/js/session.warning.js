//Load jQuery First
//How frequently to check for session expiration in milliseconds
var sess_pollInterval = 60000;
//How many minutes the session is valid for
var sess_expirationMinutes = 12;
//How many minutes before the warning prompt
var sess_warningMinutes = 10;
var sess_intervalID;
var sess_lastActivity;
function initSessionMonitor() {
    sess_lastActivity = new Date();
    sessSetInterval();
    $(document).bind('keypress.session', function (ed, e) {
        sessKeyPressed(ed, e);
    });
    $(document).bind('mousemove', function (ed, e) {
        sessKeyPressed(ed, e);
    });
}
function sessSetInterval() {
    sess_intervalID = setInterval('sessInterval()', sess_pollInterval);
}
function sessClearInterval() {
    clearInterval(sess_intervalID);
}
function sessKeyPressed(ed, e) {
    sess_lastActivity = new Date();
}
function sessPingServer() {
    //Call an AJAX function to keep-alive your session.
    someAJAXFunction();
}
function sessLogOut() {
    window.location.href = "sessionExpires";
}

function sessInterval() {
    var now = new Date();
    var diff = now - sess_lastActivity;
    var diffMins = (diff / 1000 / 60);
    if (diffMins >= sess_warningMinutes) {
        //wran before expiring
        //stop the timer
       // sessClearInterval();
        //promt for attention
        sessLogOut();
    } else {
      
       // sessPingServer();
    }
}