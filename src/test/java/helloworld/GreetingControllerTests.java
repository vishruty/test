package helloworld;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(value = GreetingController.class, secure = false)
public class GreetingControllerTests {
	@Autowired
	private MockMvc mockMvc;

	@Test
    public void noParamGreetingShouldReturnDefaultMessage() throws Exception {
		this.mockMvc.perform(get("/api/v1/greeting")).andDo(print()).andExpect(status().isOk())
        .andExpect(jsonPath("$.content").value("Hello, World!"));
	}
	
	@Test
    public void paramGreetingShouldReturnTailoredMessage() throws Exception {
        this.mockMvc.perform(get("/api/v1/greeting").param("name", "Spring Community"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.content").value("Hello, Spring Community!"));
    }
}
