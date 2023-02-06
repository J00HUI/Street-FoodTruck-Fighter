# 🚚 Street Foodtruck Fighter
푸드트럭 사용자와 사장님을 위한 테이블링 앱, Street FoodTruck Fighter
</br>

<img src="https://user-images.githubusercontent.com/99133426/202908973-43099654-321b-499e-b24d-fb48b61a23ea.png" width="50%">
</br>

## 👨🏻‍🍳 서비스 소개
푸드트럭의 위치를 지도에 표시해 접근률을 높이고, 간편한 메뉴 확인, 긴 웨이팅 시간을 줄이기 위한 테이블링 및 결제 시스템, 푸드 트럭 사장님을 위한 주문 내역 확인, 매출 정산, 푸드트럭 수요 조사 등의 기능을 포함한 푸드트럭 사용자와 사장님을 위한 웹앱 서비스입니다.
</br>
</br>
</br>

## 📅 개발 기간
> 2022.10.11 ~ 2022.11.21 </br>

|기획 및 설계| 10.11 ~ 10.19 |
|:------|:------|
| **개발** | **10.20 ~ 11.14** |
| **버그 수정 및 산출물 정리** | **11.15 ~ 11.21** |
</br>
</br>

## 📄 프로젝트 설계

🖥️ [와이어 프레임](https://www.figma.com/file/5wfH0Qpq21ki0rBAyIyi5A/%EC%8A%A4%ED%8A%B8%EB%A6%AC%ED%8A%B8-%ED%91%B8%EB%93%9C%ED%8A%B8%EB%9F%AD-%ED%8C%8C%EC%9D%B4%ED%84%B0_B206_%EC%9E%90%EC%9C%A8-%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8?node-id=0%3A1&t=PTcmkGYpTGvPth2h-0)  /  📝 [기능명세서](https://www.notion.so/3a665dc16e5942219d083fd0e053c76e)  /  🌐 [ERD](https://www.erdcloud.com/d/wkHDpQmGCtkvGzMZQ) /  🍏 [API 명세서](https://www.notion.so/API-Docs-dcac15915bd94e2bad86aa0a08221463)
</br>
</br>
</br>

## ⚒️기술 스택
### Front-End
- Vue `3.2.13`
- JavaScript 
- jQuery `3.6.1`
- Axios `1.1.3`
- Pinia `2.0.23`
- Fullcalender `5.11.2`
- Chart.js `3.9.1`
- Splide.js `0.6.12`

### Back-End
- Java `1.8`
- Spring Boot `2.7.4`
- Spring Date JPA `2.7.4`
- Spring Security `2.7.4`
- Swagger2 `3.0.0`
- MySQL `8.0.31`
- Redis `5.0.7`

### Server
- AWS EC2 ubuntu `20.04 LTS`
- Docker `20.10.20`
- Jenkins image : jenkins/jenkins:lts
- NGINX image : stable-alpine

### Tool
* 형상 관리 : gitlab
* 이슈 관리 : Jira
* 커뮤니케이션 : Mattermost, Webex, Notion
* 디자인 : Figma

### IDE
* Intellij
* Mysql Workbench
* VSCode
</br>

## 💡 시스템 아키텍처
<img src="https://user-images.githubusercontent.com/99133426/202916867-defbf547-6ac6-48cc-91dc-f075727933b0.png" width="70%">

## 💻 주요 기능
## [사용자]
* 회원가입 및 로그인

| 회원가입 | 로그인 |
|:-------:|:-------:|
| <img src="https://user-images.githubusercontent.com/83942393/216920654-39912b46-69ab-480b-97c5-ad30245569d4.png" width="70%"> |<img src="https://user-images.githubusercontent.com/83942393/216916770-5813d369-f9ce-48ea-879f-250338b8cf94.png" width="70%"> |
</br>

* 메인화면

| 메인화면 | 
|:-------:|
| <img src="https://user-images.githubusercontent.com/83942393/216921152-5bb7de1f-20df-4ad6-b733-b8e97fe9aa89.png" width="70%"> |
</br>

* 메뉴별 푸드트럭 위치 및 정보 확인

| 지도 | 목록 |
|:-------:|:-------:|
| <img src="https://user-images.githubusercontent.com/83942393/216921356-73ee0fc3-30bd-489a-986f-cfaadb94a2e4.png" width="70%"> |<img src="https://user-images.githubusercontent.com/83942393/216921130-49464ca4-0b47-41d7-85cd-22a61ff4034a.png" width="70%"> |
</br>

* 푸드트럭 상세 정보
* 리뷰

| 메뉴 | 상세 정보 | 리뷰 | 
|:-------:|:-------:|:-------:|
| <img src="https://user-images.githubusercontent.com/83942393/216921325-045d278a-b366-4442-a160-643022eb6586.png" width="70%"> |<img src="https://user-images.githubusercontent.com/83942393/216922357-c27b9318-217f-4477-9beb-d6bacbdf2453.png" width="70%"> |<img src="https://user-images.githubusercontent.com/83942393/216921325-045d278a-b366-4442-a160-643022eb6586.png" width="70%"> |
</br>

* 주문 및 결제

| 메뉴 | 상세 정보 |  상세 정보 |  상세 정보 |
|:-------:|:-------:|:-------:|:-------:|
| <img src="https://user-images.githubusercontent.com/99133426/202929102-61fc011f-b08c-4073-a2e7-8cad9c194497.png" width="70%"> |<img src="https://user-images.githubusercontent.com/99133426/202929101-8162f86e-2ec9-48bf-9469-4932db2471ec.png" width="70%"> |<img src="https://user-images.githubusercontent.com/99133426/202929099-feb40dc9-abba-4833-a056-363541b47d81.png" width="70%"> |<img src="https://user-images.githubusercontent.com/99133426/202929098-56fb4d2d-e366-41a2-ac8b-f15756ac5f20.png" width="70%"> |
</br>

![survey](https://user-images.githubusercontent.com/83942393/216921229-65a5c3d8-5d94-4fcd-8319-4b4ee464ca80.png)


### [CEO]
* 회원가입 및 로그인

| 회원가입 | 로그인 |
|:-------:|:-------:|
| <img src="https://user-images.githubusercontent.com/83942393/216920969-aeba939d-e203-4c05-b0e1-2a374cd0fb5a.png" width="70%"> |<img src="https://user-images.githubusercontent.com/83942393/216916770-5813d369-f9ce-48ea-879f-250338b8cf94.png" width="70%"> |
</br>
</br>

![surveycheck](https://user-images.githubusercontent.com/83942393/216921187-0cbce3db-28ba-4af8-9ec4-472744b472b9.png)
![start](https://user-images.githubusercontent.com/83942393/216921262-2515789f-27f9-418f-aaa1-0c132765d977.png)
![signup](https://user-images.githubusercontent.com/83942393/216921289-33e05630-7a99-4f24-a088-5e116d7df77b.png)
![ceomain](https://user-images.githubusercontent.com/83942393/216921376-b92e5cbd-7b67-47dc-bc1a-0235d8730dec.png)



## 🧑🏻‍💻 개발 멤버 소개
<table>
  <tr>
    <td height="140px" align="center"><a href="https://github.com/SmileJune">
      <img src="https://avatars.githubusercontent.com/SmileJune" width="140px"/> <br><br> 윤일준 <br>(Back-End) </a> <br></td>
    <td height="140px" align="center"><a href="https://github.com/ssafypark">
      <img src="https://avatars.githubusercontent.com/ssafypark" width="140px"/> <br><br> 박범수 <br>(Back-End) </a> <br></td>
    <td height="140px" align="center"><a href="https://github.com/J00HUI">
      <img src="https://avatars.githubusercontent.com/J00HUI" width="140px"/> <br><br> 이주희 <br>(Back-End) </a> <br></td>
    <td height="140px" align="center"><a href="https://github.com/ggpp200">
      <img src="https://avatars.githubusercontent.com/ggpp200" width="140px"/> <br><br> 박승주 <br>(Front-End) </a> <br></td>
    <td height="140px" align="center"><a href="https://github.com/Sung-Hoon-Lee">
      <img src="https://avatars.githubusercontent.com/Sung-Hoon-Lee" width="140px"/> <br><br> 이성훈 <br>(Front-End) </a> <br></td>
    <td height="140px" align="center"><a href="https://github.com/atj14">
      <img src="https://avatars.githubusercontent.com/atj14" width="140px"/> <br><br> 안태환 <br>(Front-End) </a> <br></td>
  </tr>
  <tr>
      <td align="center">👑<br/>CI/CD<br/>Rest API</td>
      <td align="center">REST API</td>
      <td align="center">UI/UX<br/>REST API</td>
      <td align="center">UI/UX<br/>Vue</td>
      <td align="center">UI/UX<br/>Vue</td>
      <td align="center">UI/UX<br/>Vue</td>
  </tr>
</table>
</br>

