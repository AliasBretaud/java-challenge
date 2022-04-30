package jp.co.axa.apidemo;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import jp.co.axa.apidemo.business.model.Employee;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Value("${api.auth-header-name}")
	private String apiKeyHeader;
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
	@Test
	public void getEmployeesTestOk() throws Exception {
		
		mockMvc.perform(get("/api/v1/employees"))
        	.andExpect(status().isOk())
        	.andExpect(jsonPath("$[0].id", is(notNullValue())))
        	.andExpect(jsonPath("$[0].name", is(notNullValue())))
			.andExpect(jsonPath("$[0].salary", is(notNullValue())));
	}
	
	@Test
	public void getEmployeeTestOk() throws Exception {
		
		mockMvc.perform(get("/api/v1/employees/1"))
    		.andExpect(status().isOk())
    		.andExpect(jsonPath("$.id", is(1)))
    		.andExpect(jsonPath("$.name", is("Bob")))
    		.andExpect(jsonPath("$.salary", is(15000.0)))
    		.andExpect(jsonPath("$.department.id", is(1)))
    		.andExpect(jsonPath("$.department.label", is("Accounting")));
	}
	
	@Test
	public void getEmployeeTestKo_employeeNotFound() throws Exception {
		
		mockMvc.perform(get("/api/v1/employees/-1"))
			.andExpect(status().isNotFound())
			.andExpect(jsonPath("$.status", is(404)))
			.andExpect(jsonPath("$.error", is("jp.co.axa.apidemo.business.exceptions.EmployeeNotFoundException")))
			.andExpect(jsonPath("$.message", is("No employee found with ID -1")));
	}
	
	@Test
	public void saveEmployeeTestOk() throws Exception {
		
		final Employee emp = new Employee();
		emp.setName("Test");
		emp.setSalary(1525.50F);
		
		mockMvc.perform(post("/api/v1/employees")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(emp)))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.id", is(notNullValue())))
    		.andExpect(jsonPath("$.name", is("Test")))
    		.andExpect(jsonPath("$.salary", is(1525.5)));
			
	}
	
	@Test
	public void saveEmployeeTestKo_no_name() throws Exception {
		
		final Employee emp = new Employee();
		emp.setName(null);
		emp.setSalary(1525.50F);
		
		mockMvc.perform(post("/api/v1/employees")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(emp)))
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.error", is("javax.validation.ConstraintViolationException")));
	}
	
	@Test
	public void saveEmployeeTestKo_salary_negative() throws Exception {
		
		final Employee emp = new Employee();
		emp.setName("Test");
		emp.setSalary(-3F);
		
		mockMvc.perform(post("/api/v1/employees")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(emp)))
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.error", is("javax.validation.ConstraintViolationException")));
	}
	
	@Test
	public void saveEmployeeTestKo_message_not_readable() throws Exception {
		
		mockMvc.perform(post("/api/v1/employees")
				.contentType(MediaType.APPLICATION_JSON)
				.content("!{sdg#"))
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.error", is("org.springframework.http.converter.HttpMessageNotReadableException")));
	}
	
	@Test
	public void saveEmployeeTestKo_unsupported_media_type() throws Exception {
		
		final Employee emp = new Employee();
		emp.setName("Test");
		emp.setSalary(1500F);
		
		mockMvc.perform(post("/api/v1/employees")
				.contentType(MediaType.APPLICATION_OCTET_STREAM)
				.content(objectMapper.writeValueAsString(emp)))
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.error", is("org.springframework.web.HttpMediaTypeNotSupportedException")));
	}
	
	@Test
	public void updateEmployeeTestOk() throws Exception {
		
		mockMvc.perform(get("/api/v1/employees/2"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.id", is(2)))
			.andExpect(jsonPath("$.name", is("Ted")))
			.andExpect(jsonPath("$.salary", is(25000.0)));
		
		final Employee emp = new Employee();
		emp.setId(2L);
		emp.setName("Test");
		emp.setSalary(1500F);
		
		mockMvc.perform(put("/api/v1/employees/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(emp)))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.id", is(2)))
    		.andExpect(jsonPath("$.name", is("Test")))
    		.andExpect(jsonPath("$.salary", is(1500.0)));
		
	}
	
	@Test
	public void deleteEmployeeTestOk() throws Exception {
		
		mockMvc.perform(delete("/api/v1/employees/3"))
    		.andExpect(status().isOk());
	}
	
	@Test
	public void badCredentialsTestKo() throws Exception {
		
		mockMvc.perform(get("/api/v1/employees").header(apiKeyHeader, ""))
			.andExpect(status().isForbidden());
	}
}
