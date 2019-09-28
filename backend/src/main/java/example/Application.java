package example;

import com.corundumstudio.socketio.*;
import com.corundumstudio.socketio.listener.DataListener;
import example.Config.JwtFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

/**
 * Created by FateXRebirth on 25/09/2017.
 */

@SpringBootApplication
public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    @Bean
    public FilterRegistrationBean jwtFilter() {
        final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new JwtFilter());
        registrationBean.addUrlPatterns("/users/*", "/cars/*");

        return registrationBean;
    }

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);

        Configuration config = new Configuration();
        config.setHostname("localhost");
        config.setPort(9092);

        final SocketIOServer server = new SocketIOServer(config);
        server.addEventListener("server", String.class, new DataListener<String>() {
            @Override
            public void onData(SocketIOClient client, String data, AckRequest ackRequest) {
                System.out.println(data);
                client.sendEvent("client", data);
                server.getBroadcastOperations().sendEvent("clients", data);
            }
        });
        server.start();

    }

    @Bean
    public CommandLineRunner demo() {
        return (args) -> {
            log.info("-------You Can Test Here-------");
        };
    }

}
