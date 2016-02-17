package hello;

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
	
	@Bean(name = "dataSource")
    public DriverManagerDataSource dataSource() {
    	DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
    	driverManagerDataSource.setDriverClassName("com.mysql.jdbc.Driver");
    	driverManagerDataSource.setUrl("jdbc:mysql://localhost:3306/android");
    	driverManagerDataSource.setUsername("root");
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
		    .antMatchers(HttpMethod.GET, "/api/places/**").permitAll()
		    .antMatchers(HttpMethod.GET, "/logged/**").permitAll()
		    .antMatchers("/api/login/**").hasRole("USER").and()
		  .csrf().disable();
		
		http
		.logout()                                                                
			.logoutUrl("/my/logout")                                                 
			.logoutSuccessUrl("/api/logged/")                                                                         
			.invalidateHttpSession(true)                                                                                      
			.deleteCookies("Cookie").clearAuthentication(true);   
		
	}
	
}