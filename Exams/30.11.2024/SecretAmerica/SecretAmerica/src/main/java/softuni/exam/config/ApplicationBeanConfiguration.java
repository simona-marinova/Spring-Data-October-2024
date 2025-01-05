package softuni.exam.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationBeanConfiguration {

    @Bean
    public Gson gson() {

        return new GsonBuilder().setPrettyPrinting()
                .excludeFieldsWithoutExposeAnnotation()
                .create();
    }


    @Bean
    public ModelMapper modelMapper() {

        ModelMapper modelMapper = new ModelMapper();
      //  PropertyMap<CustomerDTO, Customer> myMap = new PropertyMap<CustomerDTO, Customer> ()
       // {
        //    protected void configure ()
         //   {
          //      using((Converter<String, LocalDate>) mappingContext ->
                     //   LocalDate.parse(mappingContext.getSource().split("T")[0]))
                     //   .map(source.getBirthDate()).setBirthDate(null);
         //   }
     //   };
     //   modelMapper.addMappings(myMap);
        //modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper;

    }

}


