package br.com.fiap.cardbatch;

import br.com.fiap.cardbatch.model.Student;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.Range;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;

import javax.sql.DataSource;

@SpringBootApplication
@EnableBatchProcessing
public class CardBatchApplication {

	public static void main(String[] args) {
		SpringApplication.run(CardBatchApplication.class, args);
	}

	@Bean
	public ItemReader<Student> itemReader(@Value("${file.resource}") Resource resource) {
		return new FlatFileItemReaderBuilder<Student>()
				.name("User item reader")
				.fixedLength()
				.columns(new Range(1,41), new Range(42,55))
				.names("name", "document")
				.resource(resource)
				.targetType(Student.class)
				.build();
	}

	@Bean
	public ItemProcessor<Student, Student> itemProcessor() {

		return student -> {
			student.setName(student.getName().toUpperCase());
			student.setDocument(student.getDocument().replaceAll("[^\\d]", ""));
			return student;
		};
	}

	@Bean
	public ItemWriter<Student> itemWriter(DataSource dataSource){
		return new JdbcBatchItemWriterBuilder<Student>()
				.dataSource(dataSource)
				.beanMapped()
				.sql("insert into TB_STUDENT(name,document) values (:name, :document)")
				.build();
	}

	@Bean
	public Step step(StepBuilderFactory stepBuilderFactory,
					 ItemReader<Student> itemReader,
					 ItemProcessor<Student, Student> itemProcessor,
					 ItemWriter<Student> itemWriter){
		return stepBuilderFactory.get("csv to database step")
				.<Student, Student>chunk(100)
				.reader(itemReader)
				.processor(itemProcessor)
				.writer(itemWriter)
				.allowStartIfComplete(true)
				.build();
	}

	@Bean
	public Job job(JobBuilderFactory jobBuilderFactory,
				   Step step){
		return jobBuilderFactory.get("csv2db job")
				.start(step)
				.build();
	}
}
