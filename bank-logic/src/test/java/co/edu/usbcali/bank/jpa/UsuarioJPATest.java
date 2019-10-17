package co.edu.usbcali.bank.jpa;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.edu.usbcali.bank.domain.Cliente;
import co.edu.usbcali.bank.domain.TipoDocumento;
import co.edu.usbcali.bank.domain.TipoUsuario;
import co.edu.usbcali.bank.domain.Usuario;

class UsuarioJpaTest {
	
	private final static String usuUsuario="MAXPOWER";

	EntityManagerFactory entityManagerFactory=null;
	EntityManager entityManager=null;
	
	@BeforeEach
	void beforeEach(){
		entityManagerFactory=Persistence.createEntityManagerFactory("bank-logic");
		entityManager=entityManagerFactory.createEntityManager();
	}
	
	@AfterEach
	void after(){
	 entityManager.close();
	 entityManagerFactory.close();		
	}
	
	Timestamp fechaActual;
	
	@Test
	@DisplayName("save")
	void aTest() {
		
	   assertNotNull(entityManager,"El entityManager es nulo");
	   Usuario usuario=entityManager.find(Usuario.class, usuUsuario);
	   assertNull(usuario,"El usuario con id:"+usuUsuario+"Ya existe");
	   
	   usuario= new Usuario();
	   usuario.setActivo("S");
	   usuario.setClave("admin123");
	   usuario.setIdentificacion(new BigDecimal(8875));
	   usuario.setNombre("Max Power");
	   TipoUsuario tipoUsuario=entityManager.find(TipoUsuario.class,1L);   
	   usuario.setTipoUsuario(tipoUsuario);	  
	   usuario.setFechaCreacion(fechaActual);
	   usuario.setUsuUsuario(usuUsuario);
	   
	   
	   entityManager.getTransaction().begin();
	   entityManager.persist(usuario);
	   entityManager.getTransaction().commit();
	   
	}
	
	@Test
	@DisplayName("findById")
	void bTest() {
	   assertNotNull(entityManager,"El entityManager es nulo");
	   Usuario usuario=entityManager.find(Usuario.class, usuUsuario);
	   assertNotNull(usuario,"El cliente con id:"+usuUsuario+"No existe");   
	}	
	
	@Test
	@DisplayName("update")
	void cTest() {
	   assertNotNull(entityManager,"El entityManager es nulo");
	   Usuario usuario=entityManager.find(Usuario.class, usuUsuario);
	   assertNotNull(usuario,"El cliente con id:"+usuUsuario+"No existe");
	   
	   usuario.setActivo("N");
	   usuario.setFechaModificacion(fechaActual);
	   
	   entityManager.getTransaction().begin();
	   entityManager.merge(usuario);
	   entityManager.getTransaction().commit();	   
	}	
	
	@Test
	@DisplayName("delete")
	void dTest() {
	   assertNotNull(entityManager,"El entityManager es nulo");
	   Usuario usuario=entityManager.find(Usuario.class, usuUsuario);
	   assertNotNull(usuario,"El cliente con id:"+usuUsuario+"No existe");
	   
	   entityManager.getTransaction().begin();
	   entityManager.remove(usuario);
	   entityManager.getTransaction().commit();	   
	}	
	
	private final static Logger log=LoggerFactory.getLogger(ClienteJpaTest.class);
	
	@Test
	@DisplayName("findAll")
	void eTest() {
	   assertNotNull(entityManager,"El entityManager es nulo");
       String jpql="SELECT usu FROM Usuario usu";
       Query query=entityManager.createQuery(jpql);
       List<Usuario> Usuarios=query.getResultList();
       assertNotNull(Usuarios);
       assertFalse(Usuarios.isEmpty());
       
       Usuarios.forEach(usuario->{
         log.info(usuario.getNombre());
       });
	}	
		
	

}
