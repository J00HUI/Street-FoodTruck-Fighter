<template>
  <div id="customer-location-map">
    <div class="hAddr">
      <span id="centerAddr"></span>
    </div>
  </div>
</template>

<script>
// import { useKakaoStore } from "@/stores/kakao.js";
import { onMounted } from "vue";
export default {
  setup() {
    // const store = useKakaoStore();
    /* global kakao */

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
    const initMap = () => {
      const container = document.getElementById("customer-location-map");
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
          
          var marker = new kakao.maps.Marker({
            position: moveLatLng,
          });

          // 마커가 지도 위에 표시되도록 설정합니다
          marker.setMap(initMap.map);
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

      return {};
    };
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
#customer-location-map {
  width: 90%;
  height: 600px;
}
</style>