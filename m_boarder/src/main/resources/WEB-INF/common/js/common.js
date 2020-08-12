function logout() {
	if (confirm("정말 로그아웃하시겠습니까?") == true) {
		$.ajax({
			type: "POST",
			url: contextUrl + "logout.do",
			dataType: "json",
			success: function(data, status, xhr) {
				if (data.logout == "1") {
					location.href = contextUrl + "login.do"
				}
			},
			error: function(request, status, error) {
				alert("비동기처리error: " + request.responseText);
			}
		});
	}
}

function getCookie(c_name)
{
	var i, x, y, ARRcookies = document.cookie.split(";");
	for (i = 0; i < ARRcookies.length; i++)
	{
		x = ARRcookies[i].substr(0, ARRcookies[i].indexOf("="));
		y = ARRcookies[i].substr(ARRcookies[i].indexOf("=") + 1);
		x = x.replace(/^\s+|\s+$/g, "");
		if (x == c_name)
		{
			return unescape(y);
		}
	}
}

function get_query(){
    var url = document.location.href;
    var qs = url.substring(url.indexOf('?') + 1).split('&');
    for(var i = 0, result = {}; i < qs.length; i++){
        qs[i] = qs[i].split('=');
        result[qs[i][0]] = decodeURIComponent(qs[i][1]);
    }
    return result;
}

window.fbAsyncInit = function() {
	FB.init({
		appId : '4007950125888991',
		cookie : true,
		xfbml : true,
		version : 'v8.0'
	});

	FB.AppEvents.logPageView();

};

(function(d, s, id) {
	var js, fjs = d.getElementsByTagName(s)[0];
	if (d.getElementById(id)) {
		return;
	}
	js = d.createElement(s);
	js.id = id;
	js.src = "https://connect.facebook.net/en_US/sdk.js";
	fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));


function checkLoginState() {
	FB.getLoginStatus(function(response) {
		statusChangeCallback(response);
	});
}