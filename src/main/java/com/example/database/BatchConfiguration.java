package com.example.database;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
public class BatchConfiguration {

	
	private final JobBuilderFactory jobFactory;
	
	private final StepBuilderFactory stepFactory;
	
	private final DetailsRepository repo;
	
	
	@Bean 
	public FlatFileItemReader<Details> reader() {
		
		FlatFileItemReader<Details> reader = new FlatFileItemReader<>();
		reader.setResource(new FileSystemResource("src/main/resources/SampleBatch.csv"));
		reader.setLinesToSkip(1);
		reader.setLineMapper(lineMapper());
		return reader;
	
		
	}
	
	public LineMapper<Details> lineMapper() {
		
		DefaultLineMapper<Details> line  = new DefaultLineMapper<>();
		
		DelimitedLineTokenizer token = new DelimitedLineTokenizer();
		
		token.setDelimiter(",");
		token.setNames("period","series_reference","region_name","filled_jobs");
		
		BeanWrapperFieldSetMapper<Details> mapper  = new BeanWrapperFieldSetMapper<>();
		
		mapper.setTargetType(Details.class);
		
		
		line.setLineTokenizer(token);
		line.setFieldSetMapper(mapper);
		
		return line;
		
	}
	
	@Bean
	public DetailsProcessor processor() {
		
		return new DetailsProcessor();
	}
	
	
	@Bean
	public RepositoryItemWriter<Details> writer() {
		
		
		RepositoryItemWriter<Details> writer  = new RepositoryItemWriter<>();
		
		writer.setRepository(repo);
		
		writer.setMethodName("save");
		return writer;
	}
	
	@Bean
	public TaskExecutor exec() {
		
		SimpleAsyncTaskExecutor exec= new SimpleAsyncTaskExecutor();
		
		exec.setConcurrencyLimit(10);
		return exec;
		
	}
	
	@Bean
	public Step step() {
		
		return stepFactory.get("detailsStep").<Details, Details>chunk(10).reader(reader()).processor(processor())
				.writer(writer()).taskExecutor(exec()).build();
		
	}
	
	@Bean
	public Job testJob() {
		return jobFactory.get("detailsJob").flow(step()).end().build();
		
	}
}
