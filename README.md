# 🚚 Street Foodtruck Fighter
푸드트럭 사용자와 사장님을 위한 테이블링 앱, Street FoodTruck Fighter
</br>

<img src="https://user-images.githubusercontent.com/99133426/202908973-43099654-321b-499e-b24d-fb48b61a23ea.png" width="50%">

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
</br>

## 💻 주요 기능
## [사용자]
### 로그인 및 회원가입
* 카카오 API 를 활용하여 카카오 간편 로그인이 가능합니다.
* 네이버 SENS 를 사용하여 회원가입 시 문자인증을 하도록 구현하였습니다.

| 로그인 | 회원가입 |
|:-------:|:-------:|
| <img src="https://user-images.githubusercontent.com/83942393/216916770-5813d369-f9ce-48ea-879f-250338b8cf94.png" width="70%"> | <img src="https://user-images.githubusercontent.com/83942393/216920654-39912b46-69ab-480b-97c5-ad30245569d4.png" width="70%"> |
</br>

### 메인화면
* GeoLocation API 를 사용하여 사용자의 현재 위치 정보를 받아옵니다.
* 현재 사용자가 주문한 내역을 확인할 수 있습니다.
* 메뉴 카테고리를 클릭하여 푸드트럭 조회 페이지로 이동합니다.

| <img src="https://user-images.githubusercontent.com/99133426/202927894-b803b711-c51c-43f9-8d16-c4febd878597.png" width="70%"> |
|:-------:|
</br>

### 푸드트럭 조회
* 지도와 목록탭으로 구분됩니다.
* 메뉴 카테고리별로 사용자 위치 주변의 푸드트럭 정보를 제공합니다.
* 검색 기능을 제공합니다.

| 지도 | 목록 |
|:-------:|:-------:|
| <img src="https://user-images.githubusercontent.com/99133426/202927991-9b4bbc10-6aa7-4c85-936e-4f4a597a41b2.png" width="70%"> |<img src="https://user-images.githubusercontent.com/99133426/202928825-7c6fe4b0-a652-4481-b1f2-a8da07f6a188.png" width="70%"> |
</br>

### 푸드트럭 상세 조회
* 푸드트럭의 별점을 제공합니다.
* 푸드트럭의 메뉴, 상세정보, 리뷰를 제공합니다.

| 메뉴 | 상세 정보 | 리뷰 | 
|:-------:|:-------:|:-------:|
| <img src="https://user-images.githubusercontent.com/83942393/217159293-3e563f49-b935-4931-9ae4-e6e415c1c8db.png" width="70%"> |<img src="https://user-images.githubusercontent.com/83942393/216922357-c27b9318-217f-4477-9beb-d6bacbdf2453.png" width="70%"> |<img src="https://user-images.githubusercontent.com/83942393/216921325-045d278a-b366-4442-a160-643022eb6586.png" width="70%"> |
</br>

### 주문 및 결제
* 원하는 메뉴를 장바구니에 담을 수 있습니다.
* 카카오 Pay API 를 활용하여 결제시스템을 구현하였습니다.

| 장바구니 |  결제 |  결제 완료 |
|:-------:|:-------:|:-------:|
|<img src="https://user-images.githubusercontent.com/99133426/202929101-8162f86e-2ec9-48bf-9469-4932db2471ec.png" width="70%"> |<img src="https://user-images.githubusercontent.com/99133426/202929099-feb40dc9-abba-4833-a056-363541b47d81.png" width="70%"> |<img src="https://user-images.githubusercontent.com/99133426/202929098-56fb4d2d-e366-41a2-ac8b-f15756ac5f20.png" width="70%"> |
</br>

### 원해요, 푸드트럭
* 설문조사를 통해 우리 동네에 원하는 푸드트럭 설문조사를 진행합니다.
* 설문조사 결과는 푸드트럭 사장님들께 수요가 많은 메뉴와 위치를 확인하실 수 있도록 제공됩니다.

| 설문조사 | 
|:-------:|
|<img src="https://user-images.githubusercontent.com/99133426/202928023-74266b7f-847c-4409-9a5d-8b076059113b.png" width="70%"> |
</br>

### 마이페이지
* 지난 주문 내역을 확인할 수 있습니다.
* 해당 내역에 관한 리뷰를 등록할 수 있습니다.

| 지난 주문 내역 | 리뷰 등록 | 
|:-------:|:-------:|
|<img src="https://user-images.githubusercontent.com/83942393/217159854-c475719c-d27b-49df-9cb3-b44e52d2d3d7.png" width="70%"> | <img src="https://user-images.githubusercontent.com/83942393/217166365-79522567-59cc-4a4d-97da-2ae526751fef.png" width="70%"> |
</br>

## [CEO]
### 회원가입 및 마이푸드트럭 등록
* 일반 사용자와 동일하게 회원가입 시 문자인증을 하도록 구현하였습니다.
* 회원가입 후 마이푸드트럭 등록을 진행합니다.

| 회원가입 | 마이푸드트럭 등록 |
|:-------:|:-------:|
| <img src="https://user-images.githubusercontent.com/83942393/216921289-33e05630-7a99-4f24-a088-5e116d7df77b.png" width="70%"> |<img src="https://user-images.githubusercontent.com/83942393/216920969-aeba939d-e203-4c05-b0e1-2a374cd0fb5a.png" width="70%"> |
</br>
</br>

### 메인화면
* 일반 사용자와 동일하게 GeoLocation API 를 사용하여 현재 위치를 받아옵니다.
* 푸드트럭 운영 스케줄을 확인할 수 있습니다.
* 영업 시작, 영업 종료 버튼을 통해 간편하게 영업을 시작 및 종료할 수 있습니다.
* 매출 통계, 스케줄, 수요조사, 마이푸드트럭 버튼을 통해 각각의 페이지로 이동합니다.

| <img src="https://user-images.githubusercontent.com/99133426/202927863-a5bae8cc-50c4-4ea9-bbda-1510756ab91d.png" width="70%"> |
|:-------:|
</br>

### 영업 시작
* 수락되지 않은 주문 내역과 수락된 주문 내역을 확인할 수 있습니다.
* 뱃지를 통해 수락되지 않은 주문내역의 갯수를 확인할 수 있도록 구현하였습니다.

| <img src="https://user-images.githubusercontent.com/83942393/216921262-2515789f-27f9-418f-aaa1-0c132765d977.png" width="70%"> |
|:-------:|
</br>

### 수요 조사 확인
* 원해요, 푸드트럭에서 진행한 설문조사의 결과를 확인할 수 있습니다.
* 수요가 많은 메뉴와 위치를 제공하여 사장님께선 더 많은 수익을, 푸드트럭 손님들께선 원하는 메뉴를 원하는 위치에서 만날 수 있는 장점을 제공합니다.

| <img src="https://user-images.githubusercontent.com/99133426/202934910-482b85b5-99a3-426b-b882-c67c6376aea2.png" width="70%"> |
|:-------:|
</br>
</br>

### 매출 통계
* Chart.js 를 사용하였습니다.
* 매출 통계를 간편하게 파악할 수 있습니다. 

| <img src="https://user-images.githubusercontent.com/99133426/202928074-d5e50674-8f73-406c-8958-8beb51f29414.png" width="70%"> |
|:-------:|
</br>
</br>

### 스케쥴
* 스케줄을 손쉽게 변경 및 관리할 수 있습니다.

| 캘린더 | 스케줄 확인 및 변경 | 
|:-------:|:-------:|
| <img src="https://user-images.githubusercontent.com/99133426/202934934-fbf3f415-8dbe-48f5-be30-46ad826753ef.png" width="60%"> | <img src="https://user-images.githubusercontent.com/99133426/202928106-9e6dd738-54d7-4f5e-9293-98b69c3f3021.png" width="70%"> |
</br>
</br>

## 🤙🏻협업 (Jira, Notion)
| Jira 번다운 차트 |
|:-------:|
| <img src="https://user-images.githubusercontent.com/83942393/218046711-49d9c8f7-0104-489e-8931-333b213f63a7.png" width="60%"> |
</br>

## 🧑🏻‍💻 개발 멤버 소개
<table>
  <tr>
    <td height="140px" align="center"><a href="https://github.com/SmileJune">
      <img src="https://avatars.githubusercontent.com/SmileJune" width="140px"/> <br><br> 윤일준 <br>(Back-End) </a> <br></td>
    <td height="140px" align="center"><a href="https://github.com/ssafypark">
      <img src="https://avatars.githubusercontent.com/ssafypark" width="140px"/> <br><br> 박범수 <br>(Back-End) </a> <br></td>
    <td height="140px" align="center"><a href="https://github.com/joohee56">
      <img src="https://avatars.githubusercontent.com/joohee56" width="140px"/> <br><br> 이주희 <br>(Back-End) </a> <br></td>
    <td height="140px" align="center"><a href="https://github.com/ggpp200">
      <img src="https://avatars.githubusercontent.com/ggpp200" width="140px"/> <br><br> 박승주 <br>(Front-End) </a> <br></td>
    <td height="140px" align="center"><a href="https://github.com/Sung-Hoon-Lee">
      <img src="https://avatars.githubusercontent.com/Sung-Hoon-Lee" width="140px"/> <br><br> 이성훈 <br>(Front-End) </a> <br></td>
    <td height="140px" align="center"><a href="https://github.com/atj14">
      <img src="https://avatars.githubusercontent.com/atj14" width="140px"/> <br><br> 안태환 <br>(Front-End) </a> <br></td>
  </tr>
  <tr>
      <td align="center">👑<br/>CI/CD<br/>REST API</td>
      <td align="center">REST API</td>
      <td align="center">Design<br/>REST API</td>
      <td align="center">UI/UX<br/>Vue</td>
      <td align="center">UI/UX<br/>Vue</td>
      <td align="center">UI/UX<br/>Vue</td>
  </tr>
</table>
</br>

