package br.com.demo.adapter;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.data.web.SortHandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SpringBootDemoAdapter implements WebMvcConfigurer {
	
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argResolvers) {
		PageableHandlerMethodArgumentResolver phmar = new PageableHandlerMethodArgumentResolver();
		phmar.setFallbackPageable(PageRequest.of(0, 5));
		argResolvers.add(phmar);
		
		//não bombou
		SortHandlerMethodArgumentResolver shmar = new SortHandlerMethodArgumentResolver();
		shmar.setFallbackSort(new Sort(Direction.ASC, "name"));
		argResolvers.add(shmar);
	}

}
