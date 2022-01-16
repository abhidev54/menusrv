package com.takeaway.menusrv;

import com.takeaway.menusrv.controller.RestaurantController;
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
public class RestaurantTests {
    public static final String API_PATH = "/menusrv/api";

    @Autowired
    private RestaurantController restaurantRestEndpoint;

    @Autowired
    private RestaurantService restaurantService;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(restaurantRestEndpoint)
                .build();
    }

    @Test
    public void testHealth() {
        try {
            mockMvc.perform(MockMvcRequestBuilders.get(API_PATH + "/health"))
                    .andExpect(MockMvcResultMatchers.status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCreateRestaurant() {
        try {
            String json = "{\n" +
                    "  \"name\": \"Punjab Grill Restaurant\",\n" +
                    "  \"address\": \"Punjab Grill Restaurant, Limassol - 4000\"\n" +
                    "}";
            mockMvc.perform(MockMvcRequestBuilders.post(API_PATH + "/restaurants").contentType(MediaType.APPLICATION_JSON).content(json))
                    .andExpect(MockMvcResultMatchers.status().isCreated());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCreateRestaurantWithInvalidRequest() {
        try {
            String json = "{jsdjsjhd\n" +
                    "  \"name\": \"Punjab Grill Restaurant\",\n" +
                    "  \"address\": \"Punjab Grill Restaurant, Limassol - 4000\"\n" +
                    "}";
            mockMvc.perform(MockMvcRequestBuilders.post(API_PATH + "/restaurants").contentType(MediaType.APPLICATION_JSON).content(json))
                    .andExpect(MockMvcResultMatchers.status().isBadRequest());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSyncBulkMenu() {
        try {
            String json = "[\n" +
                    "    {\n" +
                    "        \"restaurantId\": \"61d99a7574b6a67f4cc5972f\",\n" +
                    "        \"categoryId\" : \"61dacdd0b29e6c332c2e8a8d\",\n" +
                    "        \"name\": \"menuItem 1\",\n" +
                    "        \"description\": \"description 1\",\n" +
                    "        \"price\": \"11\",\n" +
                    "        \"stock\": \"15\",\n" +
                    "        \"stockAvailability\" : 0\n" +
                    "    },\n" +
                    "    {\n" +
                    "        \"restaurantId\": \"61d99a7574b6a67f4cc5972f\",\n" +
                    "        \"categoryId\" : \"61dacdd0b29e6c332c2e8a8d\",\n" +
                    "        \"name\": \"menuItem 2\",\n" +
                    "        \"description\": \"description 2\",\n" +
                    "        \"price\": \"12\",\n" +
                    "        \"stock\": \"15\",\n" +
                    "        \"stockAvailability\" : 0\n" +
                    "    },\n" +
                    "    {\n" +
                    "        \"restaurantId\": \"61d99a7574b6a67f4cc5972f\",\n" +
                    "        \"categoryId\" : \"61dacdd0b29e6c332c2e8a8d\",\n" +
                    "        \"name\": \"menuItem 3\",\n" +
                    "        \"description\": \"description 3\",\n" +
                    "        \"price\": \"13\",\n" +
                    "        \"stock\": \"15\",\n" +
                    "        \"stockAvailability\" : 0\n" +
                    "    },\n" +
                    "    {\n" +
                    "        \"restaurantId\": \"61d99a7574b6a67f4cc5972f\",\n" +
                    "        \"categoryId\" : \"61dacdd0b29e6c332c2e8a8d\",\n" +
                    "        \"name\": \"menuItem 4\",\n" +
                    "        \"description\": \"description 4\",\n" +
                    "        \"price\": \"14\",\n" +
                    "        \"stock\": \"15\",\n" +
                    "        \"stockAvailability\" : 0\n" +
                    "    },\n" +
                    "    {\n" +
                    "        \"restaurantId\": \"61d99a7574b6a67f4cc5972f\",\n" +
                    "        \"categoryId\" : \"61dacdd0b29e6c332c2e8a8d\",\n" +
                    "        \"name\": \"menuItem 5\",\n" +
                    "        \"description\": \"description 5\",\n" +
                    "        \"price\": \"15\",\n" +
                    "        \"stock\": \"15\",\n" +
                    "        \"stockAvailability\" : 0\n" +
                    "    }\n" +
                    "]";
            mockMvc.perform(MockMvcRequestBuilders.post(API_PATH + "/restaurants/bulk/menuItem").contentType(MediaType.APPLICATION_JSON).content(json))
                    .andExpect(MockMvcResultMatchers.status().isCreated());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
