package ru.sberbank.kuzin19190813.crudwithkafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import ru.sberbank.kuzin19190813.crudwithkafka.dto.ReviewDTO;
import ru.sberbank.kuzin19190813.crudwithkafka.entities.Client;
import ru.sberbank.kuzin19190813.crudwithkafka.kafka.KafkaConsumerConfig;
import ru.sberbank.kuzin19190813.crudwithkafka.kafka.KafkaProducerConfig;
import ru.sberbank.kuzin19190813.crudwithkafka.kafka.KafkaTopicConfig;

import javax.annotation.PostConstruct;

import static org.modelmapper.config.Configuration.AccessLevel.PRIVATE;

@SpringBootApplication
public class App extends SpringBootServletInitializer {
    //ClientRepository clientRepository;
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

//    @KafkaListener(topics="msg")
//    public void msgListener(String msg){
//        System.out.println(msg);
//    }

    @PostConstruct
    public void post() {
        System.out.println("post");
        //kafkaTemplate.send("msg", "1", new ReviewDTO());
//        Client client = new Client();
//        client.setClientName("Jorge");
//        clientRepository.save(client);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(App.class, KafkaProducerConfig.class, KafkaConsumerConfig.class, KafkaTopicConfig.class);
    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration();
                //.setMatchingStrategy(MatchingStrategies.STRICT)
                //.setFieldMatchingEnabled(true)
                //.setSkipNullEnabled(true)
                //.setCollectionsMergeEnabled(true)
                //.setFieldAccessLevel(PRIVATE);
        return modelMapper;
    }

//    @Bean
//    public SuperMapper superMapper() {
//        return new SuperMapper();
//    }

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        return objectMapper;
    }

}
