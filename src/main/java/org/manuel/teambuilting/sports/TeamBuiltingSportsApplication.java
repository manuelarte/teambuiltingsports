package org.manuel.teambuilting.sports;

import org.manuel.teambuilting.core.config.CoreConfig;
import org.manuel.teambuilting.core.config.EnableCoreFunctionalities;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@EnableAspectJAutoProxy
@EnableCoreFunctionalities
@Import({CoreConfig.class})
public class TeamBuiltingSportsApplication {

	public static void main(final String[] args) {
		SpringApplication.run(TeamBuiltingSportsApplication.class, args);
	}

}
