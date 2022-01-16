package com.takeaway.menusrv;

import com.takeaway.menusrv.controller.OrderController;
import com.takeaway.menusrv.controller.RestaurantController;
import com.takeaway.menusrv.service.OrderService;
import com.takeaway.menusrv.service.RestaurantService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderTests {
	public static final String API_PATH = "/menusrv/api";

    @Autowired
    private OrderController orderRestEndpoint;

    @Autowired
    private OrderService orderService;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(orderRestEndpoint)
                .build();
    }

    @Test
    public void testCreateOrder() {
        try {

            String json = "{\n" +
                    "    \"restaurantId\": \"61d99a7574b6a67f4cc5972f\",\n" +
                    "    \"foodItems\": [\n" +
                    "        {\n" +
                    "            \"name\": \"Pizza\",\n" +
                    "            \"price\": 11,\n" +
                    "            \"quantity\": 2\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"name\": \"Hakka Noodles\",\n" +
                    "            \"price\": 12,\n" +
                    "            \"quantity\": 3\n" +
                    "        }\n" +
                    "    ],\n" +
                    "    \"totalPrice\": 58,\n" +
                    "    \"orderTime\": 1493428243933,\n" +
                    "    \"specialNote\": \"\",\n" +
                    "    \"deliveryTime\": 1493486808730,\n" +
                    "    \"paymentId\": \"\",\n" +
                    "    \"userInfo\": {\n" +
                    "        \"id\": \"\",\n" +
                    "        \"firstName\": \"first1\",\n" +
                    "        \"lastName\": \"last1\",\n" +
                    "        \"phone\": \"14081234567\",\n" +
                    "        \"address\": \"123 stree1 ave, San Jose, CA 95123\"\n" +
                    "    },\n" +
                    "    \"orderStatus\": 0\n" +
                    "}";
            mockMvc.perform(MockMvcRequestBuilders.post(API_PATH + "/restaurants/61d99a7574b6a67f4cc5972f/orders").contentType(MediaType.APPLICATION_JSON).content(json))
                    .andExpect(MockMvcResultMatchers.status().isCreated());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testMarkAsCompleteOrder() {
        try {

            String json = "{\n" +
                    "    \"restaurantId\": \"61d99a7574b6a67f4cc5972f\",\n" +
                    "    \"foodItems\": [\n" +
                    "        {\n" +
                    "            \"name\": \"Pizza\",\n" +
                    "            \"price\": 11,\n" +
                    "            \"quantity\": 2\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"name\": \"Hakka Noodles\",\n" +
                    "            \"price\": 12,\n" +
                    "            \"quantity\": 3\n" +
                    "        }\n" +
                    "    ],\n" +
                    "    \"totalPrice\": 58,\n" +
                    "    \"orderTime\": 1493428243933,\n" +
                    "    \"specialNote\": \"\",\n" +
                    "    \"deliveryTime\": 1493486808730,\n" +
                    "    \"paymentId\": \"\",\n" +
                    "    \"userInfo\": {\n" +
                    "        \"id\": \"\",\n" +
                    "        \"firstName\": \"first1\",\n" +
                    "        \"lastName\": \"last1\",\n" +
                    "        \"phone\": \"14081234567\",\n" +
                    "        \"address\": \"123 stree1 ave, San Jose, CA 95123\"\n" +
                    "    },\n" +
                    "    \"orderStatus\": 4\n" +
                    "}";
            mockMvc.perform(MockMvcRequestBuilders.post(API_PATH + "/restaurants/61d99a7574b6a67f4cc5972f/61de203c8d4bf429110420d7/update").contentType(MediaType.APPLICATION_JSON).content(json))
                    .andExpect(MockMvcResultMatchers.status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
