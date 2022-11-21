import { defineStore } from "pinia";
import RF from "@/api/RF";
import axios from "axios";
import emptyMarker from "@/assets/ceo/myEmptyMarkerIcon.svg"
export const useKakaoStore = defineStore("Kakao", {
  state: () => {
    const ceoMyData = {
      date: "",
      openTime: "",
      closeTime: "",
      address: "",
      latitude: 0,
      longtitudes: 0,
    };
    const searchTypeData = {
      iconType:  emptyMarker,
      searchType: "click", //'click'과 'input'존재
      goBack: false,
      is_map:false,
    };
    const mapCenter = {
      latitude: 0,
      longitude: 0,
      address: null,
    };
    const surveyData = [{
    }

    ];
    return {
      s_markers_info: [],
      surveyData,
      is_survey_update: false,
      currentAddress: "",
      ceoMyData,
      searchTypeData,
      mapCenter,
    };
  },
  actions: {
    /* global kakao */
    getSurvey() {
      const token = localStorage.getItem("accessToken");
      axios({
        url: RF.survey.surveyFind(),
        method: "post",
        headers: { Authorization: "Bearer " + token },
        data: this.mapCenter,
      })
        .then((res) => {
          console.log(res.data);
          this.surveyData = res.data
          this.is_survey_update = true
          this.searchTypeData.is_map = true

        })
        .catch(() => {
         alert('정보를 불러오지 못했습니다')
        });
    },
    setHeaderAddress() {
      let mapCenter = this.mapCenter;
      const initPosition = () => {
        if (navigator.geolocation) {
          navigator.geolocation.getCurrentPosition(function (position) {
            var moveLatLng = new kakao.maps.LatLng(
              position.coords.latitude,
              position.coords.longitude
            );
            var geocoder = new kakao.maps.services.Geocoder();

            searchDetailAddrFromCoords(moveLatLng, function (result, status) {
              if (status === kakao.maps.services.Status.OK) {
                if (result[0].road_address !== null) {
                  mapCenter.address = result[0].road_address.address_name;
                } else if (result[0].address !== null) {
                  mapCenter.address = result[0].address.address_name;
                } else {
                  mapCenter.address = "확인불가";
                }
              }
            });
            function searchDetailAddrFromCoords(coords, callback) {
              // 좌표로 법정동 상세 주소 정보를 요청합니다
              geocoder.coord2Address(
                coords.getLng(),
                coords.getLat(),
                callback
              );
            }
          });
        }
      };
      if (window.kakao && window.kakao.maps) {
        initPosition();
      } else {
        const script = document.createElement("script");
        script.onload = () => kakao.maps.load(initPosition);
        script.src =
          "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=44e203a985e2bc845fbbde8390a4fc5b&libraries=services,clusterer";
        document.head.appendChild(script);
      }
    },
  },
});
