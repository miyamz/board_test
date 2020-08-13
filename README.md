## Spring Boot (임시)게시판 제작
---------------------------

#### 개발 환경
* Java 1.8
* Eclipse IDE(2019-09 R)
* H2 Database
* Maven
* Mybatis
* BootStrap templete(SB2 Admin)
* Spring Boot 3 Starter
* thymeleaf
* tomcat 9(내장)
* CKEditor4

#### 페이지 요청
	+ /login.do : 로그인 페이지. 시작페이지로 모든 유저 접근. 로그인 세션을 잃어버리거나 페이지 권한이 없는경우 로그인페이지로 이동한다.
	+ /register.do: 회원 가입페이지.
	+ /index.do: 메인페이지 화면(빈화면)
	+ /boardList.do: 게시판 리스트 조회
	+ /boardView.do: 게시글 보기
	+ /boardWrite.do: 게시글 쓰기
	+ /logout.do: 로그아웃
	+ /boardDelete.do: 게시글 삭제(글쓴이가 아니면 안됨)
	+ /fbLogin.do : 페이스북 로그인
	+ /fbRegister.do: 페이스북 가입페이지
	+ /fbLogout.do: 페이스북 로그아웃

#### 간략한 개발 설명
Spring MVC개발 경험을 바탕으로 Spring Boot MVC형태로 프로젝트 생성
Url Mapping Route를 이용한 웹호출을 구현했습니다.
기존에는 JSP로 프론트 템플릿을 구성했었는데 이번에는 thymeleaf를 사용하여 구현하였습니다.
이외에 세션저장, interceptor를 이용한 보안처리 등의 로직을 구현하였습니다.

#### 실행 방법
1. (Eclipse IDE를 이용하는 경우) 프로젝트 추가를 우선 합니다. - [File] > [Open Projects from File System]
2. 저같은 경우엔 maven repository를 별도 지정하였습니다.
	+ maven 별도 다운로드
	+ 압축을 풀면 conf라는 경로 안에 Settings.xml이라는 파일이 나옴
	+ 항목 중에 repository의 경로를 임의로 수정(좋아하는 장소로..)
	+ [Window] > [Preferences] > "Maven" > User Settings
	+ 선택 후 나타나는 항목 중에 User Settings의 경로를 설치하고 변경한 conf/Settings.xml 경로로 변경
3. 프로젝트의 pom.xml을 우클릭 후에 debug as..나 run as..쪽에 나오는 서브메뉴에서 'Maven install'을 눌러 dependency 빌드 시도.
4. 빌드가 성공하면 프로젝트 우클릭 debug as..혹은 run as..쪽에 나오는 서브메뉴중에 'Spring Boot App'이라는 메뉴를 선택하면 웹어플리케이션이 내장 톰캣을 이용하여 실행됩니다.