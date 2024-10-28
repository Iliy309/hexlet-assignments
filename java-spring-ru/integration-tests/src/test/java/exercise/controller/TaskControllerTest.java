package exercise.controller;

import org.junit.jupiter.api.Test;
import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static org.assertj.core.api.Assertions.assertThat;
import org.instancio.Instancio;
import org.instancio.Select;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import java.util.HashMap;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.datafaker.Faker;
import exercise.repository.TaskRepository;
import exercise.model.Task;

// BEGIN
@SpringBootTest
@AutoConfigureMockMvc
// END
class ApplicationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Faker faker;

    @Autowired
    private ObjectMapper om;

    @Autowired
    private TaskRepository taskRepository;


    @Test
    public void testWelcomePage() throws Exception {
        var result = mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andReturn();

        var body = result.getResponse().getContentAsString();
        assertThat(body).contains("Welcome to Spring!");
    }

    @Test
    public void testIndex() throws Exception {
        var result = mockMvc.perform(get("/tasks"))
                .andExpect(status().isOk())
                .andReturn();

        var body = result.getResponse().getContentAsString();
        assertThatJson(body).isArray();
    }


    // BEGIN
    @Test
    public void testShow() throws Exception {
        var task = new Task();
        task.setTitle(faker.lorem().word());
        task.setDescription(faker.lorem().paragraph());
        var save = taskRepository.save(task);

        var result = mockMvc.perform(get("/tasks/" + save.getId()))
                .andExpect(status().isOk())
                .andReturn();

        var responce = result.getResponse().getContentAsString();
        assertThatJson(responce).and(
                body -> body.node("title").isEqualTo(task.getTitle()),
                body -> body.node("description").isEqualTo(task.getDescription())
        );
    }

    @Test
    public void testCreate() throws Exception {
        var task = new Task();
        task.setTitle(faker.lorem().word());
        task.setDescription(faker.lorem().paragraph());
        taskRepository.save(task);

        var result = mockMvc.perform(post("/tasks")
                                            .contentType(MediaType.APPLICATION_JSON)
                                            .content(om.writeValueAsString(task)))
                .andExpect(status().isCreated())
                .andReturn();

        var responce = result.getResponse().getContentAsString();
        assertThatJson(responce).and(
                body -> body.node("title").isEqualTo(task.getTitle()),
                body -> body.node("description").isEqualTo(task.getDescription())
        );
    }

    @Test
    public void testDelete() throws Exception {
        var task = new Task();
        task.setTitle(faker.lorem().word());
        task.setDescription(faker.lorem().paragraph());
        var save = taskRepository.save(task);

        var result  = mockMvc.perform(delete("/tasks/" + save.getId()))
                .andExpect(status().isOk()).andReturn();


        var optTask = taskRepository.findById(1l);
        assertThat(!optTask.isPresent()).isTrue();
    }
    // END
}
