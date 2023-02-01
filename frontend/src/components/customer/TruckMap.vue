<template>
  <div class="map_wrap">
    <div id="customer-truck-map" style="width: 100%; height: 100%; position: relative; overflow: hidden"></div>
    <div class="hAddr">
      <span id="centerAddr"></span>
    </div>
  </div>
</template>

<script>
// import { useKakaoStore } from "@/stores/kakao.js";
import hotdog from "@/assets/hotdog.svg";
import coffee from "@/assets/coffee.svg";
import hamburger from "@/assets/hamburger.svg";
import sweetpotato from "@/assets/sweetpotato.svg";
import icecream from "@/assets/icecream.svg";
import waffle from "@/assets/waffle.svg";
import steak from "@/assets/steak.svg";
import sandwich from "@/assets/sandwich.svg";
import skeweredfood from "@/assets/skeweredfood.svg";
import ramen from "@/assets/ramen.svg";
import pizza from "@/assets/pizza.svg";
import drink from "@/assets/drink.svg";
import xIcon from "@/assets/xicon.svg";
import { useStoreDetail } from "@/stores/customer/menu/storeDetail.js";
import { onMounted } from "vue";
export default {
  setup() {
    // const store = useKakaoStore();
    /* global kakao */

    const nearStore = useStoreDetail();

    onMounted(() => {
      if (window.kakao && window.kakao.maps) {
        initMap();
      } else {
        const script = document.createElement("script");
        script.onload = () => kakao.maps.load(initMap);
        script.src =
          "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=44e203a985e2bc845fbbde8390a4fc5b&libraries=services";
        document.head.appendChild(script);
      }
    });
    const categoryImgList = [
      hamburger,
      hotdog,
      coffee,
      sweetpotato,
      icecream,
      waffle,
      steak,
      sandwich,
      skeweredfood,
      ramen,
      pizza,
      drink,
      xIcon,
    ];
    const categoryList = [
      "햄버거",
      "핫도그",
      "카페",
      "고구마",
      "아이스크림",
      "와플",
      "스테이크",
      "샌드위치",
      "꼬치",
      "라면",
      "피자",
      "음료수",
    ];
    const initMap = () => {
      const container = document.getElementById("customer-truck-map");
      const options = {
        // center: new kakao.maps.LatLng(36.36880618678187, 127.37618869404398),
        center: new kakao.maps.LatLng(
          sessionStorage.getItem("latitude"),
          sessionStorage.getItem("longitude")
        ),
        level: 5,
      };
      if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function (position) {
          var moveLatLng = new kakao.maps.LatLng(
            position.coords.latitude,
            position.coords.longitude
          );
          var imageSize = new kakao.maps.Size(64, 69); // 마커이미지의 크기입니다
          var imageSrc = null;
          // 마커의 이미지정보를 가지고 있는 마커이미지를 생성합니다
          var markerImage = null;

          var nearDataLength = nearStore.nearData.length;
          var categoryListLength = categoryList.length;

          for (let m_i = 0; m_i < nearDataLength; m_i++) {
            var markerPosition = new kakao.maps.LatLng(
              nearStore.nearData[m_i].latitude,
              nearStore.nearData[m_i].longitude
            );

            for (let c_i = 0; c_i < categoryListLength + 1; c_i++) {
              if (m_i === categoryListLength) {
                imageSrc = categoryImgList[categoryListLength];
                markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);
                break;
              } else if (
                nearStore.nearData[m_i].category === categoryList[c_i]
              ) {
                imageSrc = categoryImgList[m_i];
                markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);
                break;
              }
            }

            var marker = new kakao.maps.Marker({
              image: markerImage, // 마커이미지 설정
              position: markerPosition,
              clickable: true, // 마커를 클릭했을 때 지도의 클릭 이벤트가 발생하지 않도록 설정합니다
            });

            marker.setMap(initMap.map);

            var iwContent =
              '<div style="padding:5px;"><span>' + nearStore.nearData[m_i].name + ' ⭐ ' + nearStore.nearData[m_i].grade + '<br /><span>' + nearStore.nearData[m_i].category + '<br />' + nearStore.nearData[m_i].address + '</span></span>' +
              "</div>", // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
              iwRemoveable = true; // removeable 속성을 ture 로 설정하면 인포윈도우를 닫을 수 있는 x버튼이 표시됩니다


            // var iwContent = [
            //   '<div style="padding:5px;"><span>' + nearStore.nearData[m_i].name + ' ⭐ ' + nearStore.nearData[m_i].grade + '<br /><span>' + nearStore.nearData[m_i].category + '<br />' + nearStore.nearData[m_i].address + '</span></span>' +
            //   "</div>"]; // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다

            // var iwRemoveable = true; // removeable 속성을 ture 로 설정하면 인포윈도우를 닫을 수 있는 x버튼이 표시됩니다

            // // 인포윈도우를 생성합니다

            // console.log(iwContent[m_i]);

            var infowindow = new kakao.maps.InfoWindow({
              content: iwContent,
              removable: iwRemoveable,
            });

            // 마커에 클릭이벤트를 등록합니다
            kakao.maps.event.addListener(marker, "click", function () {
              // 마커 위에 인포윈도우를 표시합니다
              infowindow.open(initMap.map, marker);
            });

            // var content =
            //   '<div class="contentBox"><div class="bAddr">' +
            //   "{{nearStore.nearData[m_i].name}}" + //푸드트럭 이름 +
            //   "</div></div>";

            // var customOverlay = new kakao.maps.CustomOverlay({
            //   content: content,
            //   position: markerPosition,
            // });

            // customOverlay.setContent(content);
            // customOverlay.setPosition(markerPosition);
          }

          // 마커가 지도 위에 표시되도록 설정합니다

          initMap.map.panTo(moveLatLng);
          searchAddrFromCoords(initMap.map.getCenter(), displayCenterInfo);
        });
      }

      initMap.map = new kakao.maps.Map(container, options);

      var geocoder = new kakao.maps.services.Geocoder();
      function searchAddrFromCoords(coords, callback) {
        // 좌표로 행정동 주소 정보를 요청합니다
        geocoder.coord2RegionCode(coords.getLng(), coords.getLat(), callback);
      }

      kakao.maps.event.addListener(initMap.map, "dragend", function () {
        searchAddrFromCoords(initMap.map.getCenter(), displayCenterInfo);
      });

      function displayCenterInfo(result, status) {
        if (status === kakao.maps.services.Status.OK) {
          var infoDiv = document.getElementById("centerAddr");

          for (var i = 0; i < result.length; i++) {
            // 행정동의 region_type 값은 'H' 이므로
            if (result[i].region_type === "H") {
              infoDiv.innerHTML = result[i].address_name;
              break;
            }
          }
        }
      }

      return { nearStore };
    };
  },
};
</script>

<style scoped>
#customer-truck-map {
  box-sizing: border-box;
  width: 90%;
  height: 76%;
  margin: 0px;
  border-radius: 1rem;
  margin: 5%;
}

.map_wrap {
  position: relative;
  width: 91%;
  height: 30.625rem;
}

.title {
  font-weight: bold;
  display: block;
}

.hAddr {
  position: absolute;
  left: 25px;
  top: 10px;
  border-radius: 2px;
  background: #fff;
  background: rgba(255, 255, 255, 0.8);
  z-index: 1;
  padding: 5px;
  max-width: 60%;
}

#centerAddr {
  display: block;
  margin-top: 1px;
  font-weight: normal;
  font-size: 1rem;
}

.contentBox {
  height: 200px;
}

.bAddr {
  font: 1rem "SCoreDream";
  color: black;
  background-color: rgba(255, 255, 255, 0.8);
  border-radius: 5px;
  padding: 4px;
}

.label {
  margin-bottom: 96px;
}

.label * {
  display: inline-block;
  vertical-align: top;
}

.label .left {
  background: url("https://t1.daumcdn.net/localimg/localimages/07/2011/map/storeview/tip_l.png") no-repeat;
  display: inline-block;
  height: 24px;
  overflow: hidden;
  vertical-align: top;
  width: 7px;
}

.label .center {
  background: url(https://t1.daumcdn.net/localimg/localimages/07/2011/map/storeview/tip_bg.png) repeat-x;
  display: inline-block;
  height: 24px;
  font-size: 12px;
  line-height: 24px;
}

.label .right {
  background: url("https://t1.daumcdn.net/localimg/localimages/07/2011/map/storeview/tip_r.png") -1px 0 no-repeat;
  display: inline-block;
  height: 24px;
  overflow: hidden;
  width: 6px;
}
</style>