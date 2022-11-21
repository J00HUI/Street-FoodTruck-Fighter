<template>
  <div id="ceo-survey-map">
    <div class="hAddr">
      <span id="centerAddr"></span>
    </div>
  </div>
</template>

<script>
import { useKakaoStore } from "@/stores/kakao.js";
import { onMounted } from "vue";
import $ from "jquery";

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
export default {
  setup() {
    const store = useKakaoStore();
    /* global kakao */
    store.getSurvey();
    onMounted(() => {
      if (window.kakao && window.kakao.maps) {
        initMap();
      } else {
        const script = document.createElement("script");
        script.onload = () => kakao.maps.load(initMap);
        script.src =
          "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=44e203a985e2bc845fbbde8390a4fc5b&libraries=services,clusterer";
        document.head.appendChild(script);
      }
    });
    const initMap = () => {
      const container = document.getElementById("ceo-survey-map");
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
      const categoryLength = categoryList.length;
      const options = {
        center: new kakao.maps.LatLng(36.36880618678187, 127.37618869404398),
        level: 5,
      };
      if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function (position) {
          var moveLatLng = new kakao.maps.LatLng(
            position.coords.latitude,
            position.coords.longitude
          );
          initMap.map.panTo(moveLatLng);
        });
      }

      initMap.map = new kakao.maps.Map(container, options);
      kakao.maps.event.addListener(initMap.map, "dragend", function () {
        store.mapCenter["latitude"] = initMap.map.getCenter()["Ma"];
        store.mapCenter["longitude"] = initMap.map.getCenter()["La"];
        store.getSurvey();
        
      });
      var mapTypeControl = new kakao.maps.MapTypeControl();

      // 지도에 컨트롤을 추가해야 지도위에 표시됩니다
      // kakao.maps.ControlPosition은 컨트롤이 표시될 위치를 정의하는데 TOPRIGHT는 오른쪽 위를 의미합니다
      initMap.map.addControl(
        mapTypeControl,
        kakao.maps.ControlPosition.TOPRIGHT
      );

      // 지도 확대 축소를 제어할 수 있는  줌 컨트롤을 생성합니다
      var zoomControl = new kakao.maps.ZoomControl();
      initMap.map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);
      var clusterer = new kakao.maps.MarkerClusterer({
          map: initMap.map, // 마커들을 클러스터로 관리하고 표시할 지도 객체
          averageCenter: true, // 클러스터에 포함된 마커들의 평균 위치를 클러스터 마커 위치로 설정
          minLevel: 2, // 클러스터 할 최소 지도 레벨
          disableClickZoom: true, // 클러스터 마커를 클릭했을 때 지도가 확대되지 않도록 설정한다
        });


        var imageSrc = null;
        var markers = $(store.surveyData).map(function (i, item) {
          for (let m_i = 0; m_i < categoryLength + 1; m_i++) {
            if (m_i === categoryLength) {
              imageSrc = categoryImgList[categoryLength];
            }
            if (item["category"] === categoryList[m_i]) {
              imageSrc = categoryImgList[m_i];
              break;
            }
          }

          var imageSize = new kakao.maps.Size(48, 62); // 마커이미지의 크기입니다

          // 마커의 이미지정보를 가지고 있는 마커이미지를 생성합니다

          var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);

          return new kakao.maps.Marker({
            position: new kakao.maps.LatLng(
              item["latitude"],
              item["longtitudes"]
            ),
            image: markerImage,
          });
        });


        clusterer.addMarkers(markers);

        // 마커 클러스터러에 클릭이벤트를 등록합니다
        // 마커 클러스터러를 생성할 때 disableClickZoom을 true로 설정하지 않은 경우
        // 이벤트 헨들러로 cluster 객체가 넘어오지 않을 수도 있습니다
        kakao.maps.event.addListener(
          clusterer,
          "clusterclick",
          function (cluster) {
            console.log(cluster.getMarkers());
            // 현재 지도 레벨에서 1레벨 확대한 레벨
            var level = initMap.map.getLevel() - 1;

            // 지도를 클릭된 클러스터의 마커의 위치를 기준으로 확대합니다
            initMap.map.setLevel(level, { anchor: cluster.getCenter() });
          }
        );

      var geocoder = new kakao.maps.services.Geocoder();
      function searchAddrFromCoords(coords, callback) {
        // 좌표로 행정동 주소 정보를 요청합니다
        geocoder.coord2RegionCode(coords.getLng(), coords.getLat(), callback);
      }

      kakao.maps.event.addListener(initMap.map, "idle", function () {
        searchAddrFromCoords(initMap.map.getCenter(), displayCenterInfo);
      });
      // 지도 좌측상단에 지도 중심좌표에 대한 주소정보를 표출하는 함수입니다
      searchAddrFromCoords(initMap.map.getCenter(), displayCenterInfo);
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
    };
    return {};
  },
};
</script>

<style scoped>
#ceo-survey-map {
  box-sizing: border-box;
  width: 90%;
  height: 76%;
  margin: 0px;
  border-radius: 1rem;
  margin: 5%;
}
.hAddr {
  position: absolute;
  left: 4px;
  top: 4px;
  border-radius: 2px;
  background: #fff;
  background: rgba(255, 255, 255, 0.8);
  z-index: 100;
  padding: 5px;
}
#centerAddr {
  display: block;
  margin-top: 1px;
  font-weight: normal;
  font-size: 1rem;
}
</style>