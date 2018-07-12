package in.co.musify.fileStorage;

import in.co.musify.fileStorage.Initializers.DataStoreInitializer;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * Created by aparna.n on 27/01/16.
 */

@Component
public class SpringContextListener implements ApplicationListener<ContextRefreshedEvent> {
    public static final Logger logger = Logger.getLogger(SpringContextListener.class);

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        logger.info("Spring Context Listener's onApplicationEvent");
        DataStoreInitializer.getInstance();
    }
}
