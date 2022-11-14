//package com.ssafy.foodtruck.controller;
//
//import com.ssafy.foodtruck.TestUtil;
//import com.ssafy.foodtruck.constant.OrdersErrorMessage;
//import com.ssafy.foodtruck.db.entity.Message;
//import com.ssafy.foodtruck.dto.RegisterOrdersReq;
//import com.ssafy.foodtruck.model.service.OrdersService;
//import com.ssafy.foodtruck.util.JwtTokenUtil;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.ResultActions;
//
//import java.util.Collections;
//
//import static org.mockito.BDDMockito.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@WebMvcTest(OrdersController.class)
//public class OrdersControllerTest extends TestUtil {
//
//    private static final String AUTHORIZATION = "Authorization";
//    private static final String BEARER = "Bearer ";
//    private static final String BEARER_TOKEN = "여기에 토큰값이 들어가야 되는데 문제 해결을 못함";
//    private static final String ACCESS_TOKEN = JwtTokenUtil.getEmailFromBearerToken(BEARER_TOKEN);
//    private static final int ID = 1;
//
//    private RegisterOrdersReq registerOrdersReq;
//
//    @MockBean
//    private OrdersService ordersService;
//
//    @BeforeEach
//    void setUp() {
//        registerOrdersReq = RegisterOrdersReq.builder()
//                .foodtruckId(ID)
//                .menuId(Collections.singletonList(ID).toString())
//                .build();
//    }
//
//    @Test
//    @DisplayName("주문내역 등록 - 성공")
//    void register_orders_success() throws Exception {
//        // given
////        willDoNothing().given(ordersService).registerOrders(any(User.class), any(RegisterOrdersRequest.class));
//        willDoNothing().given(ordersService).registerOrders(anyInt(), any(RegisterOrdersReq.class));
//        // when
//        ResultActions resultActions = 주문내역_등록_요청(registerOrdersReq);
//        // then
//        주문내역_등록_성공(resultActions);
//    }
//
//    @DisplayName("주문내역 등록 - 실패")
//    @Test
//    void register_orders_fail() throws Exception {
//        // given
//        willThrow(new RuntimeException()).given(ordersService).registerOrders(anyInt(), any(RegisterOrdersReq.class));
//        // when
//        ResultActions resultActions = 주문내역_등록_요청(registerOrdersReq);
//        // then
//        주문내역_등록_실패(resultActions, new Message(OrdersErrorMessage.FAIL_TO_REGISTER));
//    }
//
//    private ResultActions 주문내역_등록_요청(RegisterOrdersReq registerOrdersReq) throws Exception {
//        return mockMvc.perform(post("/api/orders/customer")
//                .header(AUTHORIZATION, BEARER + ACCESS_TOKEN)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(toJson(registerOrdersReq)));
//    }
//
//    private void 주문내역_등록_성공(ResultActions resultActions) throws Exception {
//        resultActions.andExpect(status().isOk());
//    }
//
//    private void 주문내역_등록_실패(ResultActions resultActions, Message message) throws Exception {
//        resultActions.andExpect(status().isInternalServerError())
//                .andExpect(content().json(toJson(message)));
//    }
//}
