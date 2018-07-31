package com.ceiba.induccion.vehiculos.integracion;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.SerializationUtils;
import org.springframework.web.context.WebApplicationContext;

import com.ceiba.induccion.ParqueaderoApplication;
import com.ceiba.induccion.vehiculos.VehiculoEntidad;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ParqueaderoApplication.class)
@WebAppConfiguration
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class VehiculoControladorTest {
	
	//TODO: remove 
	
    private MockMvc mockMvc;    
    private HttpMessageConverter mappingJackson2HttpMessageConverter;
    
    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));    
    
    @Autowired
    private WebApplicationContext webApplicationContext;
    
    @Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {  // mapping to jackson dependency

        this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
            .filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
            .findAny()
            .orElse(null);

        assertNotNull("the JSON message converter must not be null", this.mappingJackson2HttpMessageConverter);
    }
    
    @Before
    public void setup() throws Exception {
    	this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();          
    }
    
    @Test
    public void insertarVehiculo() throws Exception {
    	
    	// TODO: fix assertion
    	
    	String placa = "WMQ756";
    	String tipo = "CARRO";
    	VehiculoEntidad vehiculo = new VehiculoEntidad(placa, tipo);
    	        						
		mockMvc.perform(post("/api/1.0/parqueadero/vehiculos")
				.content(this.json(vehiculo))
				.contentType(contentType))
				.andExpect(status().is(202));
    }
    
    protected String json(Object object) throws IOException {
    	
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(object, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }
}
