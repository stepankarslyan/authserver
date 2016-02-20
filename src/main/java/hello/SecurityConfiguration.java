package hello;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	/*@Value("${spring.datasource.driverClassName}")
	private String className;
	
	@Value("${spring.datasource.url}")
	private String dataSourceUrl;
	
	@Value("${spring.datasource.username}")
	private String dataSourceUsername;
	
	@Value("${spring.datasource.password}")
	private String dataSourcePassword;*/
	
	@Bean(name = "dataSource")
    public DriverManagerDataSource dataSource() {
    	DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
    	driverManagerDataSource.setDriverClassName("org.postgresql.Driver");
    	driverManagerDataSource.setUrl("jdbc:postgresql://localhost:5432/postgres");
    	driverManagerDataSource.setUsername("postgres");
    	driverManagerDataSource.setPassword("123");
    		return driverManagerDataSource;
    }
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.
			jdbcAuthentication().dataSource(dataSource())
			.usersByUsernameQuery("select user_name,password, enabled from users where user_name=?")
			.authoritiesByUsernameQuery("select username, role from roles where username=?");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
		  .httpBasic().and()
		  .authorizeRequests()
		    .antMatchers(HttpMethod.POST, "/api/users/**").permitAll()
		    .antMatchers("/api/**").hasRole("USER").and()
		  .csrf().disable();
		
		http
		.logout()                                                                
			.logoutUrl("/api/logout")                                                 
			.logoutSuccessUrl("/api/logged/")                                                                         
			.invalidateHttpSession(true)                                                                                      
			.deleteCookies("Cookie").clearAuthentication(true);   
		
	}
	
}
