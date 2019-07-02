package com.onlinetutorialspoint.controller;

import com.onlinetutorialspoint.model.Item;
import com.onlinetutorialspoint.repo.ItemRepository;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class ItemControllerTest {

    private MockMvc mockMvc;
    @InjectMocks
    ItemController itemController;
    @Mock
    ItemRepository itemRepository;

    @Before
    public void setUp() throws Exception{
        mockMvc = MockMvcBuilders.standaloneSetup(itemController)
                .build();
    }
    @Test
    public void getAllItems() throws Exception {
        List<Item> items = Arrays.asList(new Item(1,"iPhoneX","Mobiles"));
        Mockito.when(itemRepository.getAllItems()).thenReturn(items);
        mockMvc.perform(MockMvcRequestBuilders.get("/getAllItems"))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    public void getItem() throws Exception{
        Item item = new Item(1,"iPhoneX","Mobiles");
        Mockito.when(itemRepository.getItem(1)).thenReturn(item);
        mockMvc.perform(MockMvcRequestBuilders.get("/item/1")
        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name",Matchers.is("iPhoneX")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.category",Matchers.is("Mobiles")));
        Mockito.verify(itemRepository).getItem(1);
    }

    @Test
    public void addItem() throws Exception {
        String jsonString = "{\n" +
                "\"id\":1,\n" +
                "\"name\":\"iPhoneX\",\n" +
                "\"category\":\"Mobiles\"\n" +
                "}";
        Item item = new Item(1,"iPhoneX","Mobiles");
        mockMvc.perform(MockMvcRequestBuilders.post("/addItem")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonString))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name",Matchers.is("iPhoneX")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.category",Matchers.is("Mobiles")));
    }

    @Test
    public void updateItem() throws Exception {
        String jsonString = "{\n" +
                "\"id\":1,\n" +
                "\"name\":\"iPhoneX\",\n" +
                "\"category\":\"Mobiles\"\n" +
                "}";
        Item item = new Item(1,"iPhoneX","Mobiles");
        mockMvc.perform(MockMvcRequestBuilders.put("/updateItem")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonString))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name",Matchers.is("iPhoneX")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.category",Matchers.is("Mobiles")));
    }

    @Test
    public void deleteItem() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.delete("/delete/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isAccepted());
    }
}