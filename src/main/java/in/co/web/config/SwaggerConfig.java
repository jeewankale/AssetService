package in.co.web.config;

import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicate;

import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static com.google.common.base.Predicates.or;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket postsApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("AssetService-api")
				.apiInfo(apiInfo()).select()
				.apis(apiPakages())
				.paths(paths())
				.build();
	}

	private Predicate<RequestHandler> apiPakages() {
		return RequestHandlerSelectors.basePackage("in.co.web.controller");
		
		//USE THIS CODE FOR MULTIPLE PACKAGES
		  //return or(RequestHandlerSelectors.basePackage("in.co.web.controller"),
		  //RequestHandlerSelectors.basePackage("in.co.web.controller"));
		 
	}
	
	private Predicate<String> paths() {
		return regex("/.*");
		
		/* USE THIS CODE FOR MULTIPLE PATHS
		 * return or(regex("/api/adopt/.*"), regex("/api/aaip*")); */
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("ITAsset APIs")
				.description("ITAsset APIs").build();
	}

}