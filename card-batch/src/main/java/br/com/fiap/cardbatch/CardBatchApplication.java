package br.com.fiap.cardbatch;

import br.com.fiap.cardbatch.model.Card;
import br.com.fiap.cardbatch.model.Student;
import br.com.fiap.cardbatch.model.Transaction;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
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

	//JOB
	@Bean
	public Job job(JobBuilderFactory jobBuilderFactory,
				   Step stepStudent,Step stepCard, Step stepTransaction){
		return jobBuilderFactory.get("csv2db job")
				.start(stepStudent)
				.next(stepCard)
				.next(stepTransaction)
				.build();
	}

	//ALUNOS
	@Bean
	public ItemReader<Student> itemReaderStudent(@Value("${file.resource-aluno}") Resource resource) {
		return new FlatFileItemReaderBuilder<Student>()
				.name("Student item reader")
				.fixedLength()
				.columns(new Range(1,41), new Range(42,55))
				.strict(false)
				.names("name", "document")
				.resource(resource)
				.targetType(Student.class)
				.build();
	}

	@Bean
	public ItemProcessor<Student, Student> itemProcessorStudent() {
		return student -> {
			if(student == null || student.getName().contains("-") || student.getName().length()==0){
				return null;
			}
			student.setName(student.getName().toUpperCase());
			student.setDocument(student.getDocument().replaceAll("[^\\d]", ""));
			return student;
		};
	}

	@Bean
	public ItemWriter<Student> itemWriterStudent(DataSource dataSource){
		return new JdbcBatchItemWriterBuilder<Student>()
				.dataSource(dataSource)
				.beanMapped()
				.sql("insert into TB_STUDENT(name,document) values (:name, :document)")
				.build();
	}

	@Bean
	public Step stepStudent(StepBuilderFactory stepBuilderFactory,
					 ItemReader<Student> itemReader,
					 ItemProcessor<Student, Student> itemProcessor,
					 ItemWriter<Student> itemWriter){
		return stepBuilderFactory.get("txt student to database step")
				.<Student, Student>chunk(100)
				.reader(itemReader)
				.processor(itemProcessor)
				.writer(itemWriter)
				.allowStartIfComplete(true)
				.build();
	}


	//CARTOES
	@Bean
	public ItemReader<Card> itemReaderCard(@Value("${file.resource-cartao}") Resource resource) {
		return new FlatFileItemReaderBuilder<Card>()
				.name("Card item reader")
				.fixedLength()
				.columns(new Range(1,21), new Range(22,29), new Range(30,31), new Range(32,35), new Range(36,39), new Range(40,51), new Range(52,52))
				.strict(false)
				.names("label", "number", "expMonth", "expYear", "cvv", "brand", "student_id")
				.resource(resource)
				.targetType(Card.class)
				.build();
	}

	@Bean
	public ItemProcessor<Card, Card> itemProcessorCard() {
		return card -> {
			return card;
		};
	}

	@Bean
	public ItemWriter<Card> itemWriterCard(DataSource dataSource){
		return new JdbcBatchItemWriterBuilder<Card>()
				.dataSource(dataSource)
				.beanMapped()
				.sql("insert into TB_CARD(label,number,exp_Month,exp_Year, cvv, brand, student_id) values (:label,:number,:expMonth,:expYear,:cvv,:brand,:student_id)")
				.build();
	}

	@Bean
	public Step stepCard(StepBuilderFactory stepBuilderFactory,
						 ItemReader<Card> itemReader,
						 ItemProcessor<Card, Card> itemProcessor,
						 ItemWriter<Card> itemWriter){
		return stepBuilderFactory.get("txt to database step")
				.<Card, Card>chunk(100)
				.reader(itemReader)
				.processor(itemProcessor)
				.writer(itemWriter)
				.allowStartIfComplete(true)
				.build();
	}

	//TRANSACOES
	@Bean
	public ItemReader<Transaction> itemReaderTransaction(@Value("${file.resource-transacao}") Resource resource) {
		return new FlatFileItemReaderBuilder<Transaction>()
				.name("Transaction item reader")
				.fixedLength()
				.columns(new Range(1,2), new Range(3,49), new Range(50,57))
				//.strict(false)
				.names("cardId", "description", "amount")
				.resource(resource)
				.targetType(Transaction.class)
				.build();
	}

	@Bean
	public ItemProcessor<Transaction, Transaction> itemProcessorTransaction() {
		return transaction -> {
			return transaction;
		};
	}

	@Bean
	public ItemWriter<Transaction> itemWriterTransaction(DataSource dataSource){
		return new JdbcBatchItemWriterBuilder<Transaction>()
				.dataSource(dataSource)
				.beanMapped()
				.sql("insert into TB_TRANSACTION (card_id,student_id,description,amount)  values (:cardId,(SELECT MAX(student_id) FROM TB_CARD WHERE id = :cardId),:description,:amount)")
				.build();
	}

	@Bean
	public Step stepTransaction(StepBuilderFactory stepBuilderFactory,
						 ItemReader<Transaction> itemReader,
						 ItemProcessor<Transaction, Transaction> itemProcessor,
						 ItemWriter<Transaction> itemWriter){
		return stepBuilderFactory.get("txt to database transaction")
				.<Transaction, Transaction>chunk(100)
				.reader(itemReader)
				.processor(itemProcessor)
				.writer(itemWriter)
				.allowStartIfComplete(true)
				.build();
	}

}
