package com.rahim.ticketbooking.security;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SecurityAuthorizationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Public endpoints allow unauthenticated access")
    void testPublicEndpointsAccess() throws Exception {
        mockMvc.perform(get("/api/v1/auth/hello"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/v1/trips/search")
                        .param("source", "Dhaka")
                        .param("destination", "Chittagong")
                        .param("date", "2026-08-10"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Protected endpoint returns 401 Unauthorized without JWT Token")
    void testProtectedEndpointRequiresAuth() throws Exception {
        mockMvc.perform(get("/api/v1/operators"))
                .andExpect(status().isUnauthorized());

        mockMvc.perform(get("/api/v1/buses"))
                .andExpect(status().isUnauthorized());

        mockMvc.perform(get("/api/v1/routes"))
                .andExpect(status().isUnauthorized());

        mockMvc.perform(get("/api/v1/auth/me"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(username = "user@example.com", roles = {"USER"})
    @DisplayName("USER role gets 403 Forbidden when attempting ADMIN-only mutation endpoints")
    void testUserRoleForbiddenOnAdminEndpoints() throws Exception {
        mockMvc.perform(post("/api/v1/operators")
                        .contentType("application/json")
                        .content("{\"name\":\"Test Operator\"}"))
                .andExpect(status().isForbidden());

        mockMvc.perform(post("/api/v1/buses")
                        .contentType("application/json")
                        .content("{\"busNumber\":\"BUS-100\"}"))
                .andExpect(status().isForbidden());

        mockMvc.perform(post("/api/v1/routes")
                        .contentType("application/json")
                        .content("{\"source\":\"A\",\"destination\":\"B\"}"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "admin@example.com", roles = {"ADMIN"})
    @DisplayName("ADMIN role is authorized to access protected resources")
    void testAdminRoleAccess() throws Exception {
        mockMvc.perform(get("/api/v1/operators"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/v1/buses"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/v1/routes"))
                .andExpect(status().isOk());
    }
}
