package com.shaowei.workflow.web.rest;

import com.shaowei.workflow.Workflow2App;

import com.shaowei.workflow.domain.Trader;
import com.shaowei.workflow.repository.TraderRepository;
import com.shaowei.workflow.service.TraderService;
import com.shaowei.workflow.service.dto.TraderDTO;
import com.shaowei.workflow.service.mapper.TraderMapper;
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
 * Test class for the TraderResource REST controller.
 *
 * @see TraderResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Workflow2App.class)
public class TraderResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    @Autowired
    private TraderRepository traderRepository;


    @Autowired
    private TraderMapper traderMapper;
    

    @Autowired
    private TraderService traderService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restTraderMockMvc;

    private Trader trader;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TraderResource traderResource = new TraderResource(traderService);
        this.restTraderMockMvc = MockMvcBuilders.standaloneSetup(traderResource)
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
    public static Trader createEntity(EntityManager em) {
        Trader trader = new Trader()
            .name(DEFAULT_NAME);
        return trader;
    }

    @Before
    public void initTest() {
        trader = createEntity(em);
    }

    @Test
    @Transactional
    public void createTrader() throws Exception {
        int databaseSizeBeforeCreate = traderRepository.findAll().size();

        // Create the Trader
        TraderDTO traderDTO = traderMapper.toDto(trader);
        restTraderMockMvc.perform(post("/api/traders")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(traderDTO)))
            .andExpect(status().isCreated());

        // Validate the Trader in the database
        List<Trader> traderList = traderRepository.findAll();
        assertThat(traderList).hasSize(databaseSizeBeforeCreate + 1);
        Trader testTrader = traderList.get(traderList.size() - 1);
        assertThat(testTrader.getName()).isEqualTo(DEFAULT_NAME);
    }

    @Test
    @Transactional
    public void createTraderWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = traderRepository.findAll().size();

        // Create the Trader with an existing ID
        trader.setId(1L);
        TraderDTO traderDTO = traderMapper.toDto(trader);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTraderMockMvc.perform(post("/api/traders")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(traderDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Trader in the database
        List<Trader> traderList = traderRepository.findAll();
        assertThat(traderList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllTraders() throws Exception {
        // Initialize the database
        traderRepository.saveAndFlush(trader);

        // Get all the traderList
        restTraderMockMvc.perform(get("/api/traders?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(trader.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())));
    }
    

    @Test
    @Transactional
    public void getTrader() throws Exception {
        // Initialize the database
        traderRepository.saveAndFlush(trader);

        // Get the trader
        restTraderMockMvc.perform(get("/api/traders/{id}", trader.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(trader.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingTrader() throws Exception {
        // Get the trader
        restTraderMockMvc.perform(get("/api/traders/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTrader() throws Exception {
        // Initialize the database
        traderRepository.saveAndFlush(trader);

        int databaseSizeBeforeUpdate = traderRepository.findAll().size();

        // Update the trader
        Trader updatedTrader = traderRepository.findById(trader.getId()).get();
        // Disconnect from session so that the updates on updatedTrader are not directly saved in db
        em.detach(updatedTrader);
        updatedTrader
            .name(UPDATED_NAME);
        TraderDTO traderDTO = traderMapper.toDto(updatedTrader);

        restTraderMockMvc.perform(put("/api/traders")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(traderDTO)))
            .andExpect(status().isOk());

        // Validate the Trader in the database
        List<Trader> traderList = traderRepository.findAll();
        assertThat(traderList).hasSize(databaseSizeBeforeUpdate);
        Trader testTrader = traderList.get(traderList.size() - 1);
        assertThat(testTrader.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingTrader() throws Exception {
        int databaseSizeBeforeUpdate = traderRepository.findAll().size();

        // Create the Trader
        TraderDTO traderDTO = traderMapper.toDto(trader);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restTraderMockMvc.perform(put("/api/traders")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(traderDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Trader in the database
        List<Trader> traderList = traderRepository.findAll();
        assertThat(traderList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTrader() throws Exception {
        // Initialize the database
        traderRepository.saveAndFlush(trader);

        int databaseSizeBeforeDelete = traderRepository.findAll().size();

        // Get the trader
        restTraderMockMvc.perform(delete("/api/traders/{id}", trader.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Trader> traderList = traderRepository.findAll();
        assertThat(traderList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Trader.class);
        Trader trader1 = new Trader();
        trader1.setId(1L);
        Trader trader2 = new Trader();
        trader2.setId(trader1.getId());
        assertThat(trader1).isEqualTo(trader2);
        trader2.setId(2L);
        assertThat(trader1).isNotEqualTo(trader2);
        trader1.setId(null);
        assertThat(trader1).isNotEqualTo(trader2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TraderDTO.class);
        TraderDTO traderDTO1 = new TraderDTO();
        traderDTO1.setId(1L);
        TraderDTO traderDTO2 = new TraderDTO();
        assertThat(traderDTO1).isNotEqualTo(traderDTO2);
        traderDTO2.setId(traderDTO1.getId());
        assertThat(traderDTO1).isEqualTo(traderDTO2);
        traderDTO2.setId(2L);
        assertThat(traderDTO1).isNotEqualTo(traderDTO2);
        traderDTO1.setId(null);
        assertThat(traderDTO1).isNotEqualTo(traderDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(traderMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(traderMapper.fromId(null)).isNull();
    }
}
