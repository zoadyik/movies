package com.wongnai.interview.movie.sync;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("movieDatabaseInitializer")
public class MovieDatabaseInitializer implements InitializingBean {
	@Autowired
	private MovieDataSynchronizer movieDataSynchronizer;

	@Override
	public void afterPropertiesSet() throws Exception {
		//run sync while server is starting
		movieDataSynchronizer.forceSync();
	}
}
