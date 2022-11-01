<template>
  <div class="map_wrap">
    <div
      id="ceo-schedule-map"
      style="width: 100%; height: 100%; position: relative; overflow: hidden"
    ></div>
    <div class="hAddr">
      <!-- <span class="title">지도중심기준 행정동 주소정보</span> -->
      <span id="centerAddr"></span>
    </div>
  </div>
</template>

<script>
import { useKakaoStore } from "@/stores/kakao.js";
import { onMounted } from "vue";
export default {
  setup() {
    const store = useKakaoStore();
    /* global kakao */
    onMounted(() => {
      if (window.kakao && window.kakao.maps) {
        initMap();
      } else {
        const script = document.createElement("script");
        script.onload = () => kakao.maps.load(initMap);
        script.src =
          "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=44e203a985e2bc845fbbde8390a4fc5b&libraries=clusterer&libraries=services";
        document.head.appendChild(script);
      }
    });
    const initMap = () => {
      const container = document.getElementById("ceo-schedule-map");

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

      // 주소-좌표 변환 객체를 생성합니다
      var geocoder = new kakao.maps.services.Geocoder();

      var marker = new kakao.maps.Marker(), // 클릭한 위치를 표시할 마커입니다
        infowindow = new kakao.maps.InfoWindow({ zindex: 1 }); // 클릭한 위치에 대한 주소를 표시할 인포윈도우입니다

      // 현재 지도 중심좌표로 주소를 검색해서 지도 좌측 상단에 표시합니다
      searchAddrFromCoords(initMap.map.getCenter(), displayCenterInfo);

      // 지도를 클릭했을 때 클릭 위치 좌표에 대한 주소정보를 표시하도록 이벤트를 등록합니다
      kakao.maps.event.addListener(initMap.map, "click", function (mouseEvent) {
        searchDetailAddrFromCoords(
          mouseEvent.latLng,
          function (result, status) {
            if (status === kakao.maps.services.Status.OK) {
              var detailAddr = result[0].road_address
                ? "<div>도로명주소 : " +
                  result[0].road_address.address_name +
                  "</div>"
                : "";
              detailAddr +=
                "<div>지번 주소 : " + result[0].address.address_name + "</div>";

              var content =
                '<div class="bAddr">' +
                '<span class="title">법정동 주소정보</span>' +
                detailAddr +
                "</div>";

              // 마커를 클릭한 위치에 표시합니다
              console.log(result);
              if (result[0].road_address !== null) {
                store.scheduleData.address = result[0].road_address.address_name;
              } else if (result[0].address !== null) {
                store.scheduleData.address = result[0].address.address_name;
              } else {
                store.scheduleData.address = '확인불가'
              }

              marker.setPosition(mouseEvent.latLng);
              marker.setMap(initMap.map);

              // 인포윈도우에 클릭한 위치에 대한 법정동 상세 주소정보를 표시합니다
              infowindow.setContent(content);
              infowindow.open(initMap.map, marker);
            }
          }
        );
      });

      // 중심 좌표나 확대 수준이 변경됐을 때 지도 중심 좌표에 대한 주소 정보를 표시하도록 이벤트를 등록합니다
      kakao.maps.event.addListener(initMap.map, "idle", function () {
        searchAddrFromCoords(initMap.map.getCenter(), displayCenterInfo);
      });

      function searchAddrFromCoords(coords, callback) {
        // 좌표로 행정동 주소 정보를 요청합니다
        geocoder.coord2RegionCode(coords.getLng(), coords.getLat(), callback);
      }

      function searchDetailAddrFromCoords(coords, callback) {
        // 좌표로 법정동 상세 주소 정보를 요청합니다
        geocoder.coord2Address(coords.getLng(), coords.getLat(), callback);
      }

      // 지도 좌측상단에 지도 중심좌표에 대한 주소정보를 표출하는 함수입니다
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
    return {
      store,
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
.map_wrap {
  position: relative;
  width: 100%;
  height: 350px;
}
.title {
  font-weight: bold;
  display: block;
}
.hAddr {
  position: absolute;
  left: 10px;
  top: 10px;
  border-radius: 2px;
  background: #fff;
  background: rgba(255, 255, 255, 0.8);
  z-index: 1;
  padding: 5px;
}
#centerAddr {
  display: block;
  margin-top: 1px;
  font-weight: normal;
  font-size: 0.5rem;
}
.bAddr {
  padding: 5px;
  text-overflow: ellipsis;
  overflow: hidden;
  white-space: nowrap;
}
</style>