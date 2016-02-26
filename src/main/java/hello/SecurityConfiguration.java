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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Value("${spring.datasource.driverClassName}")
	private String className;
	
	@Value("${spring.datasource.url}")
	private String dataSourceUrl;
	
	@Value("${spring.datasource.username}")
	private String dataSourceUsername;
	
	@Value("${spring.datasource.password}")
	private String dataSourcePassword;
	
	
	@Bean(name = "dataSource")
    public DriverManagerDataSource dataSource() {
    	DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
    	driverManagerDataSource.setDriverClassName(className);
    	driverManagerDataSource.setUrl(dataSourceUrl);
    	driverManagerDataSource.setUsername(dataSourceUsername);
    	driverManagerDataSource.setPassword(dataSourcePassword);
    		return driverManagerDataSource;
    }
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	
		auth.
			jdbcAuthentication().dataSource(dataSource())
			.usersByUsernameQuery("select email,password, enabled from users where email=?")
			.authoritiesByUsernameQuery("select email, role from roles where email=?");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
		  .httpBasic().and().csrf().disable()
		  .authorizeRequests()
		    .antMatchers(HttpMethod.POST, "/api/register/**").permitAll()			 
		    .anyRequest().hasRole("USER");
		
		http
		.logout()                                                                
			.logoutUrl("/api/logout")                                                 
			.logoutSuccessUrl("/api/logged/")                                                                         
			.invalidateHttpSession(true)                                                                                      
			.deleteCookies("Cookie").clearAuthentication(true);   
		
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new StandardPasswordEncoder();
	}
	
}
