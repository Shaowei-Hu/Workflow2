package com.shaowei.workflow.web.rest;

import com.shaowei.workflow.Workflow2App;

import com.shaowei.workflow.domain.Client;
import com.shaowei.workflow.repository.ClientRepository;
import com.shaowei.workflow.service.ClientService;
import com.shaowei.workflow.service.dto.ClientDTO;
import com.shaowei.workflow.service.mapper.ClientMapper;
import com.shaowei.workflow.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.ArrayList;

import static com.shaowei.workflow.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the ClientResource REST controller.
 *
 * @see ClientResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Workflow2App.class)
public class ClientResourceIntTest {

    private static final Long DEFAULT_CLIENT_CODE = 1L;
    private static final Long UPDATED_CLIENT_CODE = 2L;

    private static final String DEFAULT_COMPANY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_COMPANY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_LOCATION = "AAAAAAAAAA";
    private static final String UPDATED_LOCATION = "BBBBBBBBBB";

    private static final String DEFAULT_MANAGER = "AAAAAAAAAA";
    private static final String UPDATED_MANAGER = "BBBBBBBBBB";

    private static final String DEFAULT_DIAGNOSTIC = "AAAAAAAAAA";
    private static final String UPDATED_DIAGNOSTIC = "BBBBBBBBBB";

    private static final String DEFAULT_COMPANY_NANME = "AAAAAAAAAA";
    private static final String UPDATED_COMPANY_NANME = "BBBBBBBBBB";

    private static final String DEFAULT_ALERT = "AAAAAAAAAA";
    private static final String UPDATED_ALERT = "BBBBBBBBBB";

    private static final Integer DEFAULT_SCORE = 1;
    private static final Integer UPDATED_SCORE = 2;

    private static final String DEFAULT_BUSINESS_SIRTERIA_1 = "AAAAAAAAAA";
    private static final String UPDATED_BUSINESS_SIRTERIA_1 = "BBBBBBBBBB";

    private static final String DEFAULT_BUSINESS_SIRTERIA_2 = "AAAAAAAAAA";
    private static final String UPDATED_BUSINESS_SIRTERIA_2 = "BBBBBBBBBB";

    private static final String DEFAULT_BUSINESS_DATA = "AAAAAAAAAA";
    private static final String UPDATED_BUSINESS_DATA = "BBBBBBBBBB";

    @Autowired
    private ClientRepository clientRepository;


    @Autowired
    private ClientMapper clientMapper;
    

    @Autowired
    private ClientService clientService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restClientMockMvc;

    private Client client;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ClientResource clientResource = new ClientResource(clientService);
        this.restClientMockMvc = MockMvcBuilders.standaloneSetup(clientResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Client createEntity(EntityManager em) {
        Client client = new Client()
            .clientCode(DEFAULT_CLIENT_CODE)
            .companyName(DEFAULT_COMPANY_NAME)
            .location(DEFAULT_LOCATION)
            .manager(DEFAULT_MANAGER)
            .diagnostic(DEFAULT_DIAGNOSTIC)
            .companyNanme(DEFAULT_COMPANY_NANME)
            .alert(DEFAULT_ALERT)
            .score(DEFAULT_SCORE)
            .businessSirteria1(DEFAULT_BUSINESS_SIRTERIA_1)
            .businessSirteria2(DEFAULT_BUSINESS_SIRTERIA_2)
            .businessData(DEFAULT_BUSINESS_DATA);
        return client;
    }

    @Before
    public void initTest() {
        client = createEntity(em);
    }

    @Test
    @Transactional
    public void createClient() throws Exception {
        int databaseSizeBeforeCreate = clientRepository.findAll().size();

        // Create the Client
        ClientDTO clientDTO = clientMapper.toDto(client);
        restClientMockMvc.perform(post("/api/clients")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(clientDTO)))
            .andExpect(status().isCreated());

        // Validate the Client in the database
        List<Client> clientList = clientRepository.findAll();
        assertThat(clientList).hasSize(databaseSizeBeforeCreate + 1);
        Client testClient = clientList.get(clientList.size() - 1);
        assertThat(testClient.getClientCode()).isEqualTo(DEFAULT_CLIENT_CODE);
        assertThat(testClient.getCompanyName()).isEqualTo(DEFAULT_COMPANY_NAME);
        assertThat(testClient.getLocation()).isEqualTo(DEFAULT_LOCATION);
        assertThat(testClient.getManager()).isEqualTo(DEFAULT_MANAGER);
        assertThat(testClient.getDiagnostic()).isEqualTo(DEFAULT_DIAGNOSTIC);
        assertThat(testClient.getCompanyNanme()).isEqualTo(DEFAULT_COMPANY_NANME);
        assertThat(testClient.getAlert()).isEqualTo(DEFAULT_ALERT);
        assertThat(testClient.getScore()).isEqualTo(DEFAULT_SCORE);
        assertThat(testClient.getBusinessSirteria1()).isEqualTo(DEFAULT_BUSINESS_SIRTERIA_1);
        assertThat(testClient.getBusinessSirteria2()).isEqualTo(DEFAULT_BUSINESS_SIRTERIA_2);
        assertThat(testClient.getBusinessData()).isEqualTo(DEFAULT_BUSINESS_DATA);
    }

    @Test
    @Transactional
    public void createClientWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = clientRepository.findAll().size();

        // Create the Client with an existing ID
        client.setId(1L);
        ClientDTO clientDTO = clientMapper.toDto(client);

        // An entity with an existing ID cannot be created, so this API call must fail
        restClientMockMvc.perform(post("/api/clients")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(clientDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Client in the database
        List<Client> clientList = clientRepository.findAll();
        assertThat(clientList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllClients() throws Exception {
        // Initialize the database
        clientRepository.saveAndFlush(client);

        // Get all the clientList
        restClientMockMvc.perform(get("/api/clients?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(client.getId().intValue())))
            .andExpect(jsonPath("$.[*].clientCode").value(hasItem(DEFAULT_CLIENT_CODE.intValue())))
            .andExpect(jsonPath("$.[*].companyName").value(hasItem(DEFAULT_COMPANY_NAME.toString())))
            .andExpect(jsonPath("$.[*].location").value(hasItem(DEFAULT_LOCATION.toString())))
            .andExpect(jsonPath("$.[*].manager").value(hasItem(DEFAULT_MANAGER.toString())))
            .andExpect(jsonPath("$.[*].diagnostic").value(hasItem(DEFAULT_DIAGNOSTIC.toString())))
            .andExpect(jsonPath("$.[*].companyNanme").value(hasItem(DEFAULT_COMPANY_NANME.toString())))
            .andExpect(jsonPath("$.[*].alert").value(hasItem(DEFAULT_ALERT.toString())))
            .andExpect(jsonPath("$.[*].score").value(hasItem(DEFAULT_SCORE)))
            .andExpect(jsonPath("$.[*].businessSirteria1").value(hasItem(DEFAULT_BUSINESS_SIRTERIA_1.toString())))
            .andExpect(jsonPath("$.[*].businessSirteria2").value(hasItem(DEFAULT_BUSINESS_SIRTERIA_2.toString())))
            .andExpect(jsonPath("$.[*].businessData").value(hasItem(DEFAULT_BUSINESS_DATA.toString())));
    }
    

    @Test
    @Transactional
    public void getClient() throws Exception {
        // Initialize the database
        clientRepository.saveAndFlush(client);

        // Get the client
        restClientMockMvc.perform(get("/api/clients/{id}", client.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(client.getId().intValue()))
            .andExpect(jsonPath("$.clientCode").value(DEFAULT_CLIENT_CODE.intValue()))
            .andExpect(jsonPath("$.companyName").value(DEFAULT_COMPANY_NAME.toString()))
            .andExpect(jsonPath("$.location").value(DEFAULT_LOCATION.toString()))
            .andExpect(jsonPath("$.manager").value(DEFAULT_MANAGER.toString()))
            .andExpect(jsonPath("$.diagnostic").value(DEFAULT_DIAGNOSTIC.toString()))
            .andExpect(jsonPath("$.companyNanme").value(DEFAULT_COMPANY_NANME.toString()))
            .andExpect(jsonPath("$.alert").value(DEFAULT_ALERT.toString()))
            .andExpect(jsonPath("$.score").value(DEFAULT_SCORE))
            .andExpect(jsonPath("$.businessSirteria1").value(DEFAULT_BUSINESS_SIRTERIA_1.toString()))
            .andExpect(jsonPath("$.businessSirteria2").value(DEFAULT_BUSINESS_SIRTERIA_2.toString()))
            .andExpect(jsonPath("$.businessData").value(DEFAULT_BUSINESS_DATA.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingClient() throws Exception {
        // Get the client
        restClientMockMvc.perform(get("/api/clients/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateClient() throws Exception {
        // Initialize the database
        clientRepository.saveAndFlush(client);

        int databaseSizeBeforeUpdate = clientRepository.findAll().size();

        // Update the client
        Client updatedClient = clientRepository.findById(client.getId()).get();
        // Disconnect from session so that the updates on updatedClient are not directly saved in db
        em.detach(updatedClient);
        updatedClient
            .clientCode(UPDATED_CLIENT_CODE)
            .companyName(UPDATED_COMPANY_NAME)
            .location(UPDATED_LOCATION)
            .manager(UPDATED_MANAGER)
            .diagnostic(UPDATED_DIAGNOSTIC)
            .companyNanme(UPDATED_COMPANY_NANME)
            .alert(UPDATED_ALERT)
            .score(UPDATED_SCORE)
            .businessSirteria1(UPDATED_BUSINESS_SIRTERIA_1)
            .businessSirteria2(UPDATED_BUSINESS_SIRTERIA_2)
            .businessData(UPDATED_BUSINESS_DATA);
        ClientDTO clientDTO = clientMapper.toDto(updatedClient);

        restClientMockMvc.perform(put("/api/clients")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(clientDTO)))
            .andExpect(status().isOk());

        // Validate the Client in the database
        List<Client> clientList = clientRepository.findAll();
        assertThat(clientList).hasSize(databaseSizeBeforeUpdate);
        Client testClient = clientList.get(clientList.size() - 1);
        assertThat(testClient.getClientCode()).isEqualTo(UPDATED_CLIENT_CODE);
        assertThat(testClient.getCompanyName()).isEqualTo(UPDATED_COMPANY_NAME);
        assertThat(testClient.getLocation()).isEqualTo(UPDATED_LOCATION);
        assertThat(testClient.getManager()).isEqualTo(UPDATED_MANAGER);
        assertThat(testClient.getDiagnostic()).isEqualTo(UPDATED_DIAGNOSTIC);
        assertThat(testClient.getCompanyNanme()).isEqualTo(UPDATED_COMPANY_NANME);
        assertThat(testClient.getAlert()).isEqualTo(UPDATED_ALERT);
        assertThat(testClient.getScore()).isEqualTo(UPDATED_SCORE);
        assertThat(testClient.getBusinessSirteria1()).isEqualTo(UPDATED_BUSINESS_SIRTERIA_1);
        assertThat(testClient.getBusinessSirteria2()).isEqualTo(UPDATED_BUSINESS_SIRTERIA_2);
        assertThat(testClient.getBusinessData()).isEqualTo(UPDATED_BUSINESS_DATA);
    }

    @Test
    @Transactional
    public void updateNonExistingClient() throws Exception {
        int databaseSizeBeforeUpdate = clientRepository.findAll().size();

        // Create the Client
        ClientDTO clientDTO = clientMapper.toDto(client);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restClientMockMvc.perform(put("/api/clients")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(clientDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Client in the database
        List<Client> clientList = clientRepository.findAll();
        assertThat(clientList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteClient() throws Exception {
        // Initialize the database
        clientRepository.saveAndFlush(client);

        int databaseSizeBeforeDelete = clientRepository.findAll().size();

        // Get the client
        restClientMockMvc.perform(delete("/api/clients/{id}", client.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Client> clientList = clientRepository.findAll();
        assertThat(clientList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Client.class);
        Client client1 = new Client();
        client1.setId(1L);
        Client client2 = new Client();
        client2.setId(client1.getId());
        assertThat(client1).isEqualTo(client2);
        client2.setId(2L);
        assertThat(client1).isNotEqualTo(client2);
        client1.setId(null);
        assertThat(client1).isNotEqualTo(client2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ClientDTO.class);
        ClientDTO clientDTO1 = new ClientDTO();
        clientDTO1.setId(1L);
        ClientDTO clientDTO2 = new ClientDTO();
        assertThat(clientDTO1).isNotEqualTo(clientDTO2);
        clientDTO2.setId(clientDTO1.getId());
        assertThat(clientDTO1).isEqualTo(clientDTO2);
        clientDTO2.setId(2L);
        assertThat(clientDTO1).isNotEqualTo(clientDTO2);
        clientDTO1.setId(null);
        assertThat(clientDTO1).isNotEqualTo(clientDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(clientMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(clientMapper.fromId(null)).isNull();
    }
}
