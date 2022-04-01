package igor.escalaspring.config;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
public class SwaggerConfig {
	
	@Bean
	public Docket escalaApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("igor.escalaspring"))
				.paths(PathSelectors.regex("/v1.*"))
				.build()
				.apiInfo(metaInfo());
	}
	
	private ApiInfo metaInfo() {
		ApiInfo apiInfo = new ApiInfo(
				"Escala API",
				"API REST de gerenciamento de escalas.",
				"1.0",
				"Terms of Service",
				new Contact("Igor Nascimento", "https://www.instagram.com/ig0rnasciment0/",
						"igornascimentodesouza@gmail.com"),
				"Apache License Version 2.0",
				"https://www.apache.org/licenses/", new ArrayList<VendorExtension>()
		);
		
		return apiInfo;
	}

}
