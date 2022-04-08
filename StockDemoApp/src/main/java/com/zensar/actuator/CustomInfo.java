package com.zensar.actuator;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health.Builder;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class CustomInfo<UserRepository> extends AbstractHealthIndicator implements InfoContributor {

    @Override
    protected void doHealthCheck(Builder builder) throws Exception {
	// TODO Auto-generated method stub
	Random random = new Random();
	int randomNo = random.nextInt(100);
	if (randomNo % 2 == 0)
	    builder.up();
	else
	    builder.down();
    }

    @Autowired
    private ApplicationContext ctx;

    @Override
    public void contribute(Info.Builder builder) {
	Map<String, Object> details = new HashMap<>();
	details.put("bean-definition-count", ctx.getBeanDefinitionCount());
	details.put("startup-date", ctx.getStartupDate());

	builder.withDetail("context", details);

    }

}