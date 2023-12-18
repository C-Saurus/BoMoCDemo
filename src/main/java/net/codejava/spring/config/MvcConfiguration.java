package net.codejava.spring.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import net.codejava.spring.dao.CartDAO;
import net.codejava.spring.dao.CartDAOImpl;
import net.codejava.spring.dao.OrderDAO;
import net.codejava.spring.dao.OrderDAOImpl;
import net.codejava.spring.dao.PayDAO;
import net.codejava.spring.dao.PayDAOImpl;
import net.codejava.spring.dao.PayMethodDAO;
import net.codejava.spring.dao.PayMethodDAOImpl;
import net.codejava.spring.dao.ProductDAO;
import net.codejava.spring.dao.ProductDAOImpl;
import net.codejava.spring.dao.ShipDAO;
import net.codejava.spring.dao.ShipDAOImpl;
import net.codejava.spring.dao.ShipMethodDAO;
import net.codejava.spring.dao.ShipMethodDAOImpl;
import net.codejava.spring.dao.UserDAO;
import net.codejava.spring.dao.UserDAOImpl;

@Configuration
@ComponentScan(basePackages="net.codejava.spring")
@EnableWebMvc
@EnableTransactionManagement
public class MvcConfiguration extends WebMvcConfigurerAdapter{
//	@Override
//	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) { 
//	        configurer.enable();
//	}

	@Bean
	public ViewResolver getViewResolver(){
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}

	@Bean
	public DataSource getDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/moboc");
		dataSource.setUsername("root");
		dataSource.setPassword("12345");
		
		return dataSource;
	}
	
	@Bean
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(getDataSource());
    }
	
	@Bean
	public UserDAO getUserDAO() {
		return new UserDAOImpl(getDataSource());
	}
	
	@Bean
	public ProductDAO getProductDAO() {
		return new ProductDAOImpl(getDataSource());
	}
	
	@Bean
	public CartDAO getCartDAO() {
		return new CartDAOImpl(getDataSource());
	}
	
	@Bean
	public ShipDAO getShipDAO() {
		return new ShipDAOImpl(getDataSource());
	}
	
	@Bean
	public ShipMethodDAO getShipMethodDAO() {
		return new ShipMethodDAOImpl(getDataSource());
	}
	
	@Bean
	public OrderDAO getOrderDAO() {
		return new OrderDAOImpl(getDataSource());
	}
	
	@Bean
	public PayMethodDAO getPayMethodDAO() {
		return new PayMethodDAOImpl(getDataSource());
	}
	
	@Bean
	public PayDAO getPayDAO() {
		return new PayDAOImpl(getDataSource());
	}
}
