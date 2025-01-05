package softuni.exam.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import softuni.exam.models.dto.CompanyDTO;
import softuni.exam.models.entity.Company;
import java.time.LocalDate;

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
         PropertyMap<CompanyDTO, Company> myMap = new PropertyMap<CompanyDTO, Company>()
         {
            protected void configure ()
           {
              using((Converter<String, LocalDate >) mappingContext ->
           LocalDate.parse(mappingContext.getSource().split("T")[0]))
           .map(source.getDateEstablished()).setDateEstablished(null);
           }
          };
           modelMapper.addMappings(myMap);
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper;
    }

}



