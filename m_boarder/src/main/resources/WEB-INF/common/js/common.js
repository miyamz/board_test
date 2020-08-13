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
	js.src = "https://connect.facebook.net/ko_KR/sdk.js";
	fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));

function fbLogoutAction() {
	// 위 로그아웃 함수 말고 이거 하나로 해결됨
	if (confirm("정말 로그아웃하시겠습니까?") == true) {
		FB.getLoginStatus(function(response) {
			if (response.status === 'connected') {
				// 페이스북 로그인 상태
				FB.logout(function(response) {
					// ajax로 로그인 비동기 처리
					$.ajax({
						type: "POST",
						url: contextUrl + "fbLogout.do",
						contentType: "application/json; charset=UTF-8",
						success: function(data, status, xhr) {
							if (data.logout == "1") {
								location.href = contextUrl + "login.do"
							}
						},
						error: function(request, status, error) {
							alert("페이스북 로그아웃 실패: " + request.responseText);
						}
					});
				}, {scope: 'public_profile, email', return_scopes: true})
			} else {
				// 일반 로그인 상태
				$.ajax({
					type: "POST",
					url: contextUrl + "logout.do",
					contentType: "application/json; charset=UTF-8",
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
		});
	}
}
