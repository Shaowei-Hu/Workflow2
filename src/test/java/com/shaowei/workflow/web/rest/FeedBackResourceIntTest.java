package com.shaowei.workflow.web.rest;

import com.shaowei.workflow.Workflow2App;

import com.shaowei.workflow.domain.FeedBack;
import com.shaowei.workflow.repository.FeedBackRepository;
import com.shaowei.workflow.service.FeedBackService;
import com.shaowei.workflow.service.dto.FeedBackDTO;
import com.shaowei.workflow.service.mapper.FeedBackMapper;
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
 * Test class for the FeedBackResource REST controller.
 *
 * @see FeedBackResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Workflow2App.class)
public class FeedBackResourceIntTest {

    private static final String DEFAULT_QUESTION_1 = "AAAAAAAAAA";
    private static final String UPDATED_QUESTION_1 = "BBBBBBBBBB";

    private static final String DEFAULT_QUESTION_2 = "AAAAAAAAAA";
    private static final String UPDATED_QUESTION_2 = "BBBBBBBBBB";

    private static final String DEFAULT_QUESTION_3 = "AAAAAAAAAA";
    private static final String UPDATED_QUESTION_3 = "BBBBBBBBBB";

    private static final String DEFAULT_QUESTION_4 = "AAAAAAAAAA";
    private static final String UPDATED_QUESTION_4 = "BBBBBBBBBB";

    @Autowired
    private FeedBackRepository feedBackRepository;


    @Autowired
    private FeedBackMapper feedBackMapper;
    

    @Autowired
    private FeedBackService feedBackService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restFeedBackMockMvc;

    private FeedBack feedBack;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final FeedBackResource feedBackResource = new FeedBackResource(feedBackService);
        this.restFeedBackMockMvc = MockMvcBuilders.standaloneSetup(feedBackResource)
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
    public static FeedBack createEntity(EntityManager em) {
        FeedBack feedBack = new FeedBack()
            .question1(DEFAULT_QUESTION_1)
            .question2(DEFAULT_QUESTION_2)
            .question3(DEFAULT_QUESTION_3)
            .question4(DEFAULT_QUESTION_4);
        return feedBack;
    }

    @Before
    public void initTest() {
        feedBack = createEntity(em);
    }

    @Test
    @Transactional
    public void createFeedBack() throws Exception {
        int databaseSizeBeforeCreate = feedBackRepository.findAll().size();

        // Create the FeedBack
        FeedBackDTO feedBackDTO = feedBackMapper.toDto(feedBack);
        restFeedBackMockMvc.perform(post("/api/feed-backs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(feedBackDTO)))
            .andExpect(status().isCreated());

        // Validate the FeedBack in the database
        List<FeedBack> feedBackList = feedBackRepository.findAll();
        assertThat(feedBackList).hasSize(databaseSizeBeforeCreate + 1);
        FeedBack testFeedBack = feedBackList.get(feedBackList.size() - 1);
        assertThat(testFeedBack.getQuestion1()).isEqualTo(DEFAULT_QUESTION_1);
        assertThat(testFeedBack.getQuestion2()).isEqualTo(DEFAULT_QUESTION_2);
        assertThat(testFeedBack.getQuestion3()).isEqualTo(DEFAULT_QUESTION_3);
        assertThat(testFeedBack.getQuestion4()).isEqualTo(DEFAULT_QUESTION_4);
    }

    @Test
    @Transactional
    public void createFeedBackWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = feedBackRepository.findAll().size();

        // Create the FeedBack with an existing ID
        feedBack.setId(1L);
        FeedBackDTO feedBackDTO = feedBackMapper.toDto(feedBack);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFeedBackMockMvc.perform(post("/api/feed-backs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(feedBackDTO)))
            .andExpect(status().isBadRequest());

        // Validate the FeedBack in the database
        List<FeedBack> feedBackList = feedBackRepository.findAll();
        assertThat(feedBackList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllFeedBacks() throws Exception {
        // Initialize the database
        feedBackRepository.saveAndFlush(feedBack);

        // Get all the feedBackList
        restFeedBackMockMvc.perform(get("/api/feed-backs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(feedBack.getId().intValue())))
            .andExpect(jsonPath("$.[*].question1").value(hasItem(DEFAULT_QUESTION_1.toString())))
            .andExpect(jsonPath("$.[*].question2").value(hasItem(DEFAULT_QUESTION_2.toString())))
            .andExpect(jsonPath("$.[*].question3").value(hasItem(DEFAULT_QUESTION_3.toString())))
            .andExpect(jsonPath("$.[*].question4").value(hasItem(DEFAULT_QUESTION_4.toString())));
    }
    

    @Test
    @Transactional
    public void getFeedBack() throws Exception {
        // Initialize the database
        feedBackRepository.saveAndFlush(feedBack);

        // Get the feedBack
        restFeedBackMockMvc.perform(get("/api/feed-backs/{id}", feedBack.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(feedBack.getId().intValue()))
            .andExpect(jsonPath("$.question1").value(DEFAULT_QUESTION_1.toString()))
            .andExpect(jsonPath("$.question2").value(DEFAULT_QUESTION_2.toString()))
            .andExpect(jsonPath("$.question3").value(DEFAULT_QUESTION_3.toString()))
            .andExpect(jsonPath("$.question4").value(DEFAULT_QUESTION_4.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingFeedBack() throws Exception {
        // Get the feedBack
        restFeedBackMockMvc.perform(get("/api/feed-backs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFeedBack() throws Exception {
        // Initialize the database
        feedBackRepository.saveAndFlush(feedBack);

        int databaseSizeBeforeUpdate = feedBackRepository.findAll().size();

        // Update the feedBack
        FeedBack updatedFeedBack = feedBackRepository.findById(feedBack.getId()).get();
        // Disconnect from session so that the updates on updatedFeedBack are not directly saved in db
        em.detach(updatedFeedBack);
        updatedFeedBack
            .question1(UPDATED_QUESTION_1)
            .question2(UPDATED_QUESTION_2)
            .question3(UPDATED_QUESTION_3)
            .question4(UPDATED_QUESTION_4);
        FeedBackDTO feedBackDTO = feedBackMapper.toDto(updatedFeedBack);

        restFeedBackMockMvc.perform(put("/api/feed-backs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(feedBackDTO)))
            .andExpect(status().isOk());

        // Validate the FeedBack in the database
        List<FeedBack> feedBackList = feedBackRepository.findAll();
        assertThat(feedBackList).hasSize(databaseSizeBeforeUpdate);
        FeedBack testFeedBack = feedBackList.get(feedBackList.size() - 1);
        assertThat(testFeedBack.getQuestion1()).isEqualTo(UPDATED_QUESTION_1);
        assertThat(testFeedBack.getQuestion2()).isEqualTo(UPDATED_QUESTION_2);
        assertThat(testFeedBack.getQuestion3()).isEqualTo(UPDATED_QUESTION_3);
        assertThat(testFeedBack.getQuestion4()).isEqualTo(UPDATED_QUESTION_4);
    }

    @Test
    @Transactional
    public void updateNonExistingFeedBack() throws Exception {
        int databaseSizeBeforeUpdate = feedBackRepository.findAll().size();

        // Create the FeedBack
        FeedBackDTO feedBackDTO = feedBackMapper.toDto(feedBack);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restFeedBackMockMvc.perform(put("/api/feed-backs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(feedBackDTO)))
            .andExpect(status().isBadRequest());

        // Validate the FeedBack in the database
        List<FeedBack> feedBackList = feedBackRepository.findAll();
        assertThat(feedBackList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteFeedBack() throws Exception {
        // Initialize the database
        feedBackRepository.saveAndFlush(feedBack);

        int databaseSizeBeforeDelete = feedBackRepository.findAll().size();

        // Get the feedBack
        restFeedBackMockMvc.perform(delete("/api/feed-backs/{id}", feedBack.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<FeedBack> feedBackList = feedBackRepository.findAll();
        assertThat(feedBackList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FeedBack.class);
        FeedBack feedBack1 = new FeedBack();
        feedBack1.setId(1L);
        FeedBack feedBack2 = new FeedBack();
        feedBack2.setId(feedBack1.getId());
        assertThat(feedBack1).isEqualTo(feedBack2);
        feedBack2.setId(2L);
        assertThat(feedBack1).isNotEqualTo(feedBack2);
        feedBack1.setId(null);
        assertThat(feedBack1).isNotEqualTo(feedBack2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FeedBackDTO.class);
        FeedBackDTO feedBackDTO1 = new FeedBackDTO();
        feedBackDTO1.setId(1L);
        FeedBackDTO feedBackDTO2 = new FeedBackDTO();
        assertThat(feedBackDTO1).isNotEqualTo(feedBackDTO2);
        feedBackDTO2.setId(feedBackDTO1.getId());
        assertThat(feedBackDTO1).isEqualTo(feedBackDTO2);
        feedBackDTO2.setId(2L);
        assertThat(feedBackDTO1).isNotEqualTo(feedBackDTO2);
        feedBackDTO1.setId(null);
        assertThat(feedBackDTO1).isNotEqualTo(feedBackDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(feedBackMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(feedBackMapper.fromId(null)).isNull();
    }
}
