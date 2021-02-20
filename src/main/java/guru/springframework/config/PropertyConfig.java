package guru.springframework.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;

import guru.springframework.examplebeans.FakeDataSource;
import guru.springframework.examplebeans.FakeJmsBroker;

@Configuration
//@PropertySource({ "classpath:datasource.properties", "classpath:jms.properties" })
@PropertySources({
	@PropertySource("classpath:datasource.properties"),
	@PropertySource("classpath:jms.properties")
})
public class PropertyConfig {

	@Autowired
	Environment env;

	@Value("${guru.username}")
	String user;

	@Value("${guru.password}")
	String password;

	@Value("${guru.dburl}")
	String url;

	@Value("${guru.jms.username}")
	String jsmUsername;

	@Value("${guru.jms.password}")
	String jmsPassword;

	@Value("${guru.jms.dburl}")
	String jmsUrl;

	@Bean
	public FakeDataSource fakeDataSource() {
		FakeDataSource fakeDataSource = new FakeDataSource();
		fakeDataSource.setUser(env.getProperty("USERNAME"));
		fakeDataSource.setPassword(password);
		fakeDataSource.setUrl(url);
		return fakeDataSource;
	}

	@Bean
	public FakeJmsBroker fakeJmsBroker() {
		FakeJmsBroker broker = new FakeJmsBroker();
		broker.setUsername(jsmUsername);
		broker.setPassword(jmsPassword);
		broker.setUrl(jmsUrl);
		return broker;
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer properties() {
		PropertySourcesPlaceholderConfigurer sourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
		return sourcesPlaceholderConfigurer;
	}

}
