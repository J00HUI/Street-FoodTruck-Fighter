<template>
  <div style="display: flex; height: 844px; flex-direction: column">
    <CeoHeader></CeoHeader>
    <NewOrder></NewOrder>
    <Order class="orderScroll"></Order>
    <button type="button" id="btnSend">버튼</button>
  </div>
</template>

<script>
import * as SockJs from "sockjs-client";
import CeoHeader from "@/components/ceo/CeoHeader.vue";
import NewOrder from "@/components/ceo/StartNewOrder.vue";
import Order from "@/components/ceo/StartOrder.vue";
import $ from "jquery";
export default {
  components: {
    CeoHeader,
    NewOrder,
    Order
  },
  setup() {
    $(document).ready(function() {
      connectStomp();
      $("#btnSend").on("click", function(evt) {
        evt.preventDefault();
        if (socket.readyState !== 1) return;
        let msg = $("#input#msg").val();
        console.log("mmmmmmmmm>>", msg);
        socket.send(msg);
      });
    });
    var socket = null;
    function connectStomp() {
      var Stomp = require("stompjs");
      var sock = new SockJs("/stompTest");
      var client = Stomp.over(sock);
      client.connect({}, function() {
        console.log("Connected stompTest!");
        client.send("/TTT", {}, "msg:Haha~~~");
        client.subscribe("/topic/message", function(event) {
          console.log("!!!!!!!!event>>", event);
        });
      });
    }

    return {};
  }
};
</script>

<style scoped>
.orderScroll {
  overflow-y: scroll;
  height: auto;
  min-height: 10px;

  -ms-overflow-style: none; /* IE and Edge */
  scrollbar-width: none; /* Firefox */
}
.orderScroll::-webkit-scrollbar {
  display: none; /* Chrome, Safari, Opera*/
}
</style>