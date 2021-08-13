package com.example.batch_samples.batch2.config;

import com.example.batch_samples.batch2.model.Person;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import javax.sql.DataSource;

@Configuration
public class Step1Config {

    @Bean(name = "input")
    public FlatFileItemReader<Person> fileReader(@Value("${input}") Resource resource) {
        return new FlatFileItemReaderBuilder<Person>()
                .name("file-reader")
                .resource(resource)
                .targetType(Person.class)
                .delimited()
                .delimiter(",").names("age", "firstName", "email")
                .build();
    }


    @Bean
    public JdbcBatchItemWriter jdbcWriter(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Person>()
                .dataSource(dataSource)
                .sql("INSERT INTO people(age,first_name,email) values (:age,:firstName,:email)")
                .beanMapped()
                .build();
    }


}