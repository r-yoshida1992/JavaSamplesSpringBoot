package com.example.javasamplesspringboot.controller;

import com.example.javasamplesspringboot.JavaSamplesSpringBootApplication;
import com.example.javasamplesspringboot.entity.Users;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.sql.DataSource;

@AutoConfigureMockMvc
@SpringBootTest(classes = JavaSamplesSpringBootApplication.class)
class IndexControllerTest {

    /**
     * データベースの初期化
     */
    @BeforeAll
    public static void initDb(@Autowired DataSource dataSource) {
        ResourceDatabasePopulator p = new ResourceDatabasePopulator();
        p.addScript(new ClassPathResource("data.sql"));
        p.execute(dataSource);
    }

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void helloWorld_1() throws Exception {
        // "/"でgetリクエストした場合、以下を満たすこと
        // ・ステータスが200であること
        // ・コンテンツの中身が"Hello world"であること
        this.mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("hello world"));
    }

    @Test
    void searchUser_1() throws Exception {
        // "/searchUser?id=abc"でgetリクエストした場合、以下を満たすこと
        // ・ステータスが200であること
        // ・jsonの中身が設定されていないこと
        this.mockMvc.perform(MockMvcRequestBuilders.get("/searchUser?id=abc"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(""));
    }

    @Test
    void searchUser_2() throws Exception {
        // "/searchUser?id="でgetリクエストした場合、以下を満たすこと
        // ・ステータスが200であること
        // ・jsonの中身が設定されていないこと
        this.mockMvc.perform(MockMvcRequestBuilders.get("/searchUser?id="))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(""));
    }

    @Test
    void searchUser_3() throws Exception {
        // 期待値の設定
        Users user = new Users();
        user.setId(1L);
        user.setName("田中　太郎");
        String expected = objectMapper.writeValueAsString(user);

        // "/searchUser?id=1"でgetリクエストした場合、以下を満たすこと
        // ・ステータスが200であること
        // ・jsonの中身が期待値通りであること
        this.mockMvc.perform(MockMvcRequestBuilders.get("/searchUser?id=1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(expected));
    }

    @Test
    void searchUser_4() throws Exception {
        // "/searchUser"でgetリクエストした場合、以下を満たすこと
        // ・ステータスが400であること(パラメータ設定がない為)
        this.mockMvc.perform(MockMvcRequestBuilders.get("/searchUser"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}