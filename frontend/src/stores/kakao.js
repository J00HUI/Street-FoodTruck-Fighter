import { defineStore } from "pinia";
import RF from "@/api/RF";
import axios from "axios";

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
      iconType: false,
      viewType: null, // schedule과 my있음
      searchType: "click", //'click'과 'input'존재
    };
    const mapCenter = {
      latitude: 0,
      longitude: 0,
      address: null,
    };
    const surveyData = [
      { address: "집", category: "hotdog", latitude: 0, longtitudes: 0 },
      {
        address: "집",
        category: "hotdog",
        latitude: 36.36880618678187,
        longtitudes: 127.37618869404398,
      },
      {
        address: "집",
        category: "coffee",
        latitude: 36.371081876305944,
        longtitudes: 127.36300597228991,
      },
      {
        address: "집",
        category: "hamburger",
        latitude: 36.37105797918127,
        longtitudes: 127.34678120029335,
      },
      {
        address: "집",
        category: "sweetpotato",
        latitude: 36.35475430283077,
        longtitudes: 127.35036311468708,
      },
      {
        address: "집",
        category: "icecream",
        latitude: 36.3663566167986,
        longtitudes: 127.32661443116633,
      },
      {
        address: "집",
        category: "waffle",
        latitude: 36.387704400721304,
        longtitudes: 127.34926257984003,
      },
      {
        address: "집",
        category: "waffle",
        latitude: 36.379838042140285,
        longtitudes: 127.37570687933562,
      },
      {
        address: "집",
        category: "steak",
        latitude: 36.35463108316967,
        longtitudes: 127.36738596128184,
      },
      {
        address: "집",
        category: "sandwich",
        latitude: 36.35961466250694,
        longtitudes: 127.33995628313765,
      },
      {
        address: "집",
        category: "skeweredfood",
        latitude: 36.354286844772105,
        longtitudes: 127.33743754789877,
      },
      {
        address: "집",
        category: "ramen",
        latitude: 36.380344124582635,
        longtitudes: 127.32613796760278,
      },
      {
        address: "집",
        category: "ramen",
        latitude: 36.3590214384901,
        longtitudes: 127.31980968379185,
      },
      {
        address: "집",
        category: "pizza",
        latitude: 36.34771471725568,
        longtitudes: 127.34150870343409,
      },
      {
        address: "집",
        category: "pizza",
        latitude: 36.34883310673394,
        longtitudes: 127.37716204023228,
      },
      {
        address: "집",
        category: "drink",
        latitude: 36.3538976370704,
        longtitudes: 127.39394231325642,
      },
      {
        address: "집",
        category: "drink",
        latitude: 36.375325209657895,
        longtitudes: 127.38914701779225,
      },
      {
        address: "집",
        category: "drink",
        latitude: 36.388875952062065,
        longtitudes: 127.36727951717582,
      },
    ];
    return {
      s_markers_info: [],
      surveyData,
      currentAddress: "",
      ceoMyData,
      searchTypeData,
      mapCenter,
    };
  },
  actions: {
    setSurvey() {
      const token = localStorage.getItem("accessToken");
      axios({
        url: RF.survey.survey(),
        method: "post",
        headers: { Authorization: "Bearer " + token },
      })
        .then((res) => {
          alert(res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    getSurvey() {
      const token = localStorage.getItem("accessToken");
      axios({
        url: RF.survey.surveyFind(),
        method: "post",
        headers: { Authorization: "Bearer " + token },
        data: this.mapCenter,
      })
        .then((res) => {
          console.log(res);
          // this.surveyData = res.data
        })
        .catch((err) => {
          console.log(err);
        });
    },
    setHeaderAddress() {
      let mapCenter = this.mapCenter;
      const initPosition = () => {
        if (navigator.geolocation) {
          /* global kakao */
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
