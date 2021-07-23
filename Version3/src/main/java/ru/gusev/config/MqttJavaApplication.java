package ru.gusev.config;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import jdk.nashorn.internal.parser.JSONParser;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import ru.gusev.dao.DevDAO;
import ru.gusev.models.JsonDeserialization;
import ru.gusev.models.Values;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;

@SpringBootApplication
public class MqttJavaApplication {

    public static void main(String[] args) {
        SpringApplicationBuilder springApplicationBuilder = new SpringApplicationBuilder(MqttJavaApplication.class);
        springApplicationBuilder.web(WebApplicationType.NONE);
        springApplicationBuilder.run(args);
    }

    @Bean
    public MessageChannel mqttInputChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageProducer inbound() {
        MqttPahoMessageDrivenChannelAdapter adapter =
                new MqttPahoMessageDrivenChannelAdapter("tcp://localhost:1883", "komp",
                        "esp32", "#", "/esp32");
        adapter.setCompletionTimeout(5000);
        adapter.setConverter(new DefaultPahoMessageConverter());
        adapter.setQos(1);
        adapter.setOutputChannel(mqttInputChannel());
        return adapter;
    }







    @Bean
    @ServiceActivator(inputChannel = "mqttInputChannel")
    public MessageHandler handler() {
        return new MessageHandler() {

            @Override
            public void handleMessage(Message<?> message) throws MessagingException {
                String s = message.getPayload().toString();
                System.out.println(s);
                try(FileWriter fw = new FileWriter("C:\\Users\\Ignat\\Downloads\\projinfo.txt", false)){
                    fw.write(s);
                    fw.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Gson gson = new Gson();
                JsonDeserialization jsonDeserialization = gson.fromJson(s, JsonDeserialization.class);
                System.out.println(jsonDeserialization.dev1.get(0).ts);
                System.out.println(jsonDeserialization.dev1.get(0).values.pressure);
                System.out.println(jsonDeserialization.dev1.get(0).values.temperature);
                System.out.println(jsonDeserialization.dev1.get(0).values.humidity);
                try {
                    DevDAO.rec((int) jsonDeserialization.dev1.get(0).ts, jsonDeserialization.dev1.get(0).values.pressure,
                            jsonDeserialization.dev1.get(0).values.temperature,
                            jsonDeserialization.dev1.get(0).values.humidity);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }


            }
        };
    }

}


