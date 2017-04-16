package org.manuel.teambuilting.sports;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class TeamBuiltingSportsApplication {

	public static void main(final String[] args) {
		SpringApplication.run(TeamBuiltingSportsApplication.class, args);
	}

}
