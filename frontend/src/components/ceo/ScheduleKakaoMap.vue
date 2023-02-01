<template>
  <div class="map_wrap">
    <div
      id="ceo-schedule-map"
      style="width: 100%; height: 100%; position: relative; overflow: hidden"
    ></div>
    <div class="hAddr">
      <span id="centerAddr"></span>
    </div>
  </div>
</template>

<script>
import { useKakaoStore } from "@/stores/kakao.js";
import { useCeoScheduleStore } from "@/stores/ceo/schedule";
import { onMounted, watch } from "vue";
import truckMarker from "@/assets/ceo/mapTruck.svg";
import emptyMarker from "@/assets/ceo/myEmptyMarkerIcon.svg";
import addressIcon from "@/assets/ceo/addressIcon.svg";
import addressXIcon from "@/assets/ceo/addressXIcon.svg";
export default {
  setup() {
    const store = useKakaoStore();
    const scheduleStore = useCeoScheduleStore();
    const iconType = [emptyMarker, addressIcon, addressXIcon];
 
    let dataCase =  scheduleStore.scheduleAddForm;

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
      if (dataCase.address !== null || dataCase.address !== null) {
        geocoder.addressSearch(dataCase.address, function (result, status) {
          // 정상적으로 검색이 완료됐으면
          if (status === kakao.maps.services.Status.OK) {
            store.searchTypeData.iconType = iconType[1];
            var coords = new kakao.maps.LatLng(
              dataCase.latitude,
              dataCase.longitude
            );
            marker.setPosition(coords);
            marker.setMap(initMap.map);
            var content =
              '<div class="contentBox"><div class="bAddr">' +
              dataCase.address +
              "</div></div> ";
            customOverlay.setContent(content);
            customOverlay.setPosition(
              new kakao.maps.LatLng(dataCase.latitude, dataCase.longitude)
            );

            // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
            initMap.map.setCenter(coords);
          } else {
            store.searchTypeData.iconType = iconType[2];
          }
        });
      } else store.searchTypeData.geocoder = geocoder;
      store.searchTypeData.map = initMap.map;

      var imageSrc = truckMarker;
      var imageSize = new kakao.maps.Size(64, 69); // 마커이미지의 크기입니다

      // 마커의 이미지정보를 가지고 있는 마커이미지를 생성합니다
      var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);

      var marker = new kakao.maps.Marker({
        image: markerImage, // 마커이미지 설정
      });
      var customOverlay = new kakao.maps.CustomOverlay({
        content: "",
        position: null,
      });

      customOverlay.setMap(initMap.map);
      // 현재 지도 중심좌표로 주소를 검색해서 지도 좌측 상단에 표시합니다
      searchAddrFromCoords(initMap.map.getCenter(), displayCenterInfo);
      // 일반 지도와 스카이뷰로 지도 타입을 전환할 수 있는 지도타입 컨트롤을 생성합니다
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
      // 지도를 클릭했을 때 클릭 위치 좌표에 대한 주소정보를 표시하도록 이벤트를 등록합니다
      kakao.maps.event.addListener(initMap.map, "click", function (mouseEvent) {
        store.searchTypeData.searchType = "click";
        store.searchTypeData.iconType = iconType[0];
        searchDetailAddrFromCoords(
          mouseEvent.latLng,
          function (result, status) {
            if (status === kakao.maps.services.Status.OK) {
              var detailAddr =
                "<div>" + result[0].address.address_name + "</div>";
              dataCase.latitude = mouseEvent.latLng["Ma"];
              dataCase.longitude = mouseEvent.latLng["La"];

              var content =
                '<div class="contentBox"><div class="bAddr">' +
                detailAddr +
                "</div></div>";

              customOverlay.setContent(content);
              customOverlay.setPosition(mouseEvent.latLng);
              // 커스텀 오버레이를 지도에 표시합니다
              customOverlay.setMap(initMap.map, marker);
              marker.setPosition(mouseEvent.latLng);
              marker.setMap(initMap.map);
              if (result[0].road_address !== null) {
                dataCase.address = result[0].road_address.address_name;
              } else if (result[0].address !== null) {
                dataCase.address = result[0].address.address_name;
              } else {
                dataCase.address = "확인불가";
              }
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
      watch(
        () => dataCase.address,
        (address) => {
          if (store.searchTypeData.searchType === "input") {
            geocoder.addressSearch(address, function (result, status) {
              // 정상적으로 검색이 완료됐으면
              if (status === kakao.maps.services.Status.OK) {
                store.searchTypeData.iconType = iconType[1];
                var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
                dataCase.latitude = result[0].y;
                dataCase.longitude = result[0].x;
                marker.setPosition(coords);
                marker.setMap(initMap.map);
                var content =
                  '<div class="contentBox"><div class="bAddr">' +
                  dataCase.address +
                  "</div></div> ";
                customOverlay.setContent(content);
                customOverlay.setPosition(
                  new kakao.maps.LatLng(result[0].y, result[0].x)
                );

                // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
                initMap.map.setCenter(coords);
              } else {
                store.searchTypeData.iconType = iconType[2];
              }
            });
          }
          store.searchTypeData.searchType = "input";
        }
      );
    };

    return {
      store,
    };
  },
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
  left: 4px;
  top: 4px;
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