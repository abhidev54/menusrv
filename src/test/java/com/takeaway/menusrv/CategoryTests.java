package com.takeaway.menusrv;

import com.takeaway.menusrv.controller.CategoryController;
import com.takeaway.menusrv.controller.RestaurantController;
import com.takeaway.menusrv.service.CategoryService;
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
public class CategoryTests {
    public static final String API_PATH = "/menusrv/api";

    @Autowired
    private CategoryController categoryRestEndpoint;

    @Autowired
    private CategoryService categoryService;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(categoryRestEndpoint)
                .build();
    }

    @Test
    public void testCreateCategory() {
        try {
            String json = "{\n" +
                    "  \"name\": \"Vegetarian\"\n" +
                    "}";
            mockMvc.perform(MockMvcRequestBuilders.post(API_PATH + "/category").contentType(MediaType.APPLICATION_JSON).content(json))
                    .andExpect(MockMvcResultMatchers.status().isCreated());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCreateRestaurantWithInvalidRequest() {
        try {
            String json = "jksjkfd{\n" +
                    "  \"name\": \"Vegetarian\",\n" +
                    "}";
            mockMvc.perform(MockMvcRequestBuilders.post(API_PATH+"/category").contentType(MediaType.APPLICATION_JSON).content(json))
                    .andExpect(MockMvcResultMatchers.status().isBadRequest());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
