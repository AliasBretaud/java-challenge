package jp.co.axa.apidemo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.MockMvcBuilderCustomizer;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.ConfigurableMockMvcBuilder;

/**
 * Whenever a mockmvc object is autoconfigured, this customizer should be picked up, and a default, usable, working, valid api key is set as
 * default authorization header to be applied on all tests if not overwritten.
 *
 */
@Component
public class ApiKeyHeaderMockMvcBuilderCustomizer implements MockMvcBuilderCustomizer {
	
	@Value("${api.auth-header-name}")
	private String apiKeyHeader;
	
	@Value("${api.http.auth-token}")
	private String apiKeyValue;

    @Override
    public void customize(ConfigurableMockMvcBuilder<?> builder) {
        RequestBuilder apiKeyRequestBuilder = MockMvcRequestBuilders.get("/")
            .header(apiKeyHeader, apiKeyValue);
        builder.defaultRequest(apiKeyRequestBuilder);
    }

}