<template>
  <div id="ceo-survey-map"></div>
</template>

<script>
import hotdog from "@/assets/ceo/Icon/hotdog.svg";
import coffee from "@/assets/ceo/Icon/coffee.svg";
import hamburger from "@/assets/ceo/Icon/hamburger.svg"
import sweetpotato from "@/assets/ceo/Icon/sweetpotato.svg";
import icecream from "@/assets/ceo/Icon/icecream.svg";
import waffle from "@/assets/ceo/Icon/waffle.svg";
import steak from "@/assets/ceo/Icon/steak.svg";
import sandwich from "@/assets/ceo/Icon/sandwich.svg";
import skeweredfood from "@/assets/ceo/Icon/skeweredfood.svg";
import ramen from "@/assets/ceo/Icon/ramen.svg";
import pizza from "@/assets/ceo/Icon/pizza.svg";
import drink from "@/assets/ceo/Icon/drink.svg";
import { useKakaoStore } from "@/stores/kakao.js";
import { onMounted } from "vue";
import $ from "jquery";
// import hotdog from "@/assets/hotdog.svg";
// import coffee from "@/assets/coffee.svg";
// import hamburger from "@/assets/hamburger.svg"
// import sweetpotato from "@/assets/sweetpotato.svg";
// import icecream from "@/assets/icecream.svg";
// import waffle from "@/assets/waffle.svg";
// import steak from "@/assets/steak.svg";
// import sandwich from "@/assets/sandwich.svg";
// import skeweredfood from "@/assets/skeweredfood.svg";
// import ramen from "@/assets/ramen.svg";
// import pizza from "@/assets/pizza.svg";
// import drink from "@/assets/drink.svg";
import xIcon from "@/assets/xicon.svg";
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
          "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=44e203a985e2bc845fbbde8390a4fc5b&libraries=clusterer";
        document.head.appendChild(script);
      }
    });
    const initMap = () => {
      const container = document.getElementById("ceo-survey-map");

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
      var clusterer = new kakao.maps.MarkerClusterer({
        map: initMap.map, // 마커들을 클러스터로 관리하고 표시할 지도 객체
        averageCenter: true, // 클러스터에 포함된 마커들의 평균 위치를 클러스터 마커 위치로 설정
        minLevel: 2, // 클러스터 할 최소 지도 레벨
        disableClickZoom: true // 클러스터 마커를 클릭했을 때 지도가 확대되지 않도록 설정한다
      });
      var imageSrc = null;

      var markers = $(store.surveyData).map(function(i, item) {
        if (item["category"] === "hotdog") {
          imageSrc = hotdog; // 마커이미지의 주소입니다
        } else if (item["category"] === "coffee") {
          imageSrc = coffee; // 마커이미지의 주소입니다
        } else if (item["category"] === "hamburger") {
          imageSrc = hamburger; // 마커이미지의 주소입니다
        } else if (item["category"] === "sweetpotato") {
          imageSrc = sweetpotato; // 마커이미지의 주소입니다
        } else if (item["category"] === "icecream") {
          imageSrc = icecream; // 마커이미지의 주소입니다
        } else if (item["category"] === "waffle") {
          imageSrc = waffle;
        } else if (item["category"] === "steak") {
          imageSrc = steak;
        } else if (item["category"] === "sandwich") {
          imageSrc = sandwich;
        } else if (item["category"] === "skeweredfood") {
          imageSrc = skeweredfood;
        } else if (item["category"] === "ramen") {
          imageSrc = ramen;
        } else if (item["category"] === "pizza") {
          imageSrc = pizza;
        } else if (item["category"] === "drink") {
          imageSrc = drink;
        } else {
          imageSrc = xIcon;
        }

        var imageSize = new kakao.maps.Size(32, 48); // 마커이미지의 크기입니다

        // 마커의 이미지정보를 가지고 있는 마커이미지를 생성합니다

        var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);

        return new kakao.maps.Marker({
          position: new kakao.maps.LatLng(
            item["latitude"],
            item["longtitudes"]
          ),
          image: markerImage
        });
      });
      clusterer.addMarkers(markers);

      // 마커 클러스터러에 클릭이벤트를 등록합니다
      // 마커 클러스터러를 생성할 때 disableClickZoom을 true로 설정하지 않은 경우
      // 이벤트 헨들러로 cluster 객체가 넘어오지 않을 수도 있습니다
      kakao.maps.event.addListener(clusterer, "clusterclick", function(
        cluster
      ) {
        console.log(cluster.getMarkers());
        // 현재 지도 레벨에서 1레벨 확대한 레벨
        var level = initMap.map.getLevel() - 1;

        // 지도를 클릭된 클러스터의 마커의 위치를 기준으로 확대합니다
        initMap.map.setLevel(level, { anchor: cluster.getCenter() });
      });
    };
    return {};
  }
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
</style>