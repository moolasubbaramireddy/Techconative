package com.TechConative.Test;
import com.TechConative.Test.entity.Task;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class TaskControllerIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	public void testAddTask() throws Exception {
		Task task = new Task("New Task", "Task description", false);

		ResultActions result = mockMvc.perform(post("/tasks")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(task)));

		result.andExpect(status().isCreated())
				.andExpect(jsonPath("$.id").isNumber())
				.andExpect(jsonPath("$.title").value("New Task"))
				.andExpect(jsonPath("$.description").value("Task description"))
				.andExpect(jsonPath("$.completed").value(false));
	}

	@Test
	public void testGetAllTasks() throws Exception {
		mockMvc.perform(get("/tasks"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(2)));
	}

	@Test
	public void testGetTaskById() throws Exception {
		mockMvc.perform(get("/tasks/1"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(1))
				.andExpect(jsonPath("$.title").value("Sample Task 1"));
	}

	@Test
	public void testDeleteTask() throws Exception {
		mockMvc.perform(delete("/tasks/1"))
				.andExpect(status().isNoContent());
	}
}
