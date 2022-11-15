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
import { useCeoScheduleStore } from "@/stores/ceo/schedule";
import { useCeoMyStore } from "@/stores/ceo/my";
import { onMounted, watch } from "vue";

export default {
  setup() {
    const store = useKakaoStore();
    const scheduleStore = useCeoScheduleStore();
    const myStore = useCeoMyStore();
    let dataCase = null;
    if (store.searchTypeData.viewType === "schedule") {
      dataCase = scheduleStore.scheduleAddForm;
    } else if (store.searchTypeData.viewType === "my") {
      dataCase = myStore.positionData;
    }
    /* global kakao */
    onMounted(() => {
      if (window.kakao && window.kakao.maps) {
        initMap();
      } else {
        const script = document.createElement("script");
        script.onload = () => kakao.maps.load(initMap);
        script.src =
          "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=44e203a985e2bc845fbbde8390a4fc5b&libraries=clusterer,services";
        document.head.appendChild(script);
      }
    });
    const initMap = () => {
      const container = document.getElementById("ceo-schedule-map");

      const options = {
        center: new kakao.maps.LatLng(36.36880618678187, 127.37618869404398),
        level: 5
      };
      if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function(position) {
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
      store.searchTypeData.geocoder = geocoder;
      store.searchTypeData.map = initMap.map;

      var marker = new kakao.maps.Marker();
      var customOverlay = new kakao.maps.CustomOverlay({
        content: "",
        position: null
      });

      customOverlay.setMap(initMap.map);
      // 현재 지도 중심좌표로 주소를 검색해서 지도 좌측 상단에 표시합니다
      searchAddrFromCoords(initMap.map.getCenter(), displayCenterInfo);

      // 지도를 클릭했을 때 클릭 위치 좌표에 대한 주소정보를 표시하도록 이벤트를 등록합니다
      kakao.maps.event.addListener(initMap.map, "click", function(mouseEvent) {
        store.searchTypeData.searchType = "click";
        searchDetailAddrFromCoords(mouseEvent.latLng, function(result, status) {
          if (status === kakao.maps.services.Status.OK) {
            var detailAddr = 
              "<div>" + result[0].address.address_name + "</div>";
            dataCase.latitude = mouseEvent.latLng["La"];
            dataCase.longitude = mouseEvent.latLng["Ma"];

            var content =
              '<div class="bAddr">' +
              detailAddr +
              "</div>";

            customOverlay.setContent(content);
            customOverlay.setPosition(mouseEvent.latLng);
            // 커스텀 오버레이를 지도에 표시합니다
            customOverlay.setMap(initMap.map, marker);
            if (result[0].road_address !== null) {
              dataCase.address = result[0].road_address.address_name;
            } else if (result[0].address !== null) {
              dataCase.address = result[0].address.address_name;
            } else {
              dataCase.address = "확인불가";
            }
          }
        });
      });

      // 중심 좌표나 확대 수준이 변경됐을 때 지도 중심 좌표에 대한 주소 정보를 표시하도록 이벤트를 등록합니다
      kakao.maps.event.addListener(initMap.map, "idle", function() {
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
      watch(
        () => dataCase.address,
        address => {
          if (store.searchTypeData.searchType === "input") {
            geocoder.addressSearch(address, function(result, status) {
              // 정상적으로 검색이 완료됐으면
              if (status === kakao.maps.services.Status.OK) {
                store.searchTypeData.iconType = true;
                var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
                dataCase.latitude = result[0].y;
                dataCase.longitude = result[0].x;
                marker.setPosition(coords);
                marker.setMap(initMap.map);
                var content =
                  '<div class="bAddr">' +
                  dataCase.address +
                  "</div>";
                customOverlay.setContent(content);
                customOverlay.setPosition(new kakao.maps.LatLng(result[0].y, result[0].x));

                // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
                initMap.map.setCenter(coords);
              } else {
                store.searchTypeData.iconType = false;
              }
            });
          }
          store.searchTypeData.searchType = "input";
        }
      );
    };

    return {
      store
    };
  }
};
</script>

<style>
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
  font: 1rem "SCoreDream";
  color: black;
  background-color: rgba(255, 255, 255, 0.2s);
  border-radius: 5px;
}
.label {
  margin-bottom: 96px;
}
.label * {
  display: inline-block;
  vertical-align: top;
}
.label .left {
  background: url("https://t1.daumcdn.net/localimg/localimages/07/2011/map/storeview/tip_l.png")
    no-repeat;
  display: inline-block;
  height: 24px;
  overflow: hidden;
  vertical-align: top;
  width: 7px;
}
.label .center {
  background: url(https://t1.daumcdn.net/localimg/localimages/07/2011/map/storeview/tip_bg.png)
    repeat-x;
  display: inline-block;
  height: 24px;
  font-size: 12px;
  line-height: 24px;
}
.label .right {
  background: url("https://t1.daumcdn.net/localimg/localimages/07/2011/map/storeview/tip_r.png") -1px
    0 no-repeat;
  display: inline-block;
  height: 24px;
  overflow: hidden;
  width: 6px;
}
</style>