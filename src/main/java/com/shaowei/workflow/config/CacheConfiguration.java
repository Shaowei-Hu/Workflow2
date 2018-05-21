package com.shaowei.workflow.config;

import java.time.Duration;

import org.ehcache.config.builders.*;
import org.ehcache.jsr107.Eh107Configuration;

import io.github.jhipster.config.JHipsterProperties;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;

@Configuration
@EnableCaching
@AutoConfigureAfter(value = { MetricsConfiguration.class })
@AutoConfigureBefore(value = { WebConfigurer.class, DatabaseConfiguration.class })
public class CacheConfiguration {

    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        JHipsterProperties.Cache.Ehcache ehcache =
            jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
            CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class,
                ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ehcache.getTimeToLiveSeconds())))
                .build());
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            cm.createCache(com.shaowei.workflow.repository.UserRepository.USERS_BY_LOGIN_CACHE, jcacheConfiguration);
            cm.createCache(com.shaowei.workflow.repository.UserRepository.USERS_BY_EMAIL_CACHE, jcacheConfiguration);
            cm.createCache(com.shaowei.workflow.domain.User.class.getName(), jcacheConfiguration);
            cm.createCache(com.shaowei.workflow.domain.Authority.class.getName(), jcacheConfiguration);
            cm.createCache(com.shaowei.workflow.domain.User.class.getName() + ".authorities", jcacheConfiguration);
            cm.createCache(com.shaowei.workflow.domain.Trader.class.getName(), jcacheConfiguration);
            cm.createCache(com.shaowei.workflow.domain.Trader.class.getName() + ".teams", jcacheConfiguration);
            cm.createCache(com.shaowei.workflow.domain.Team.class.getName(), jcacheConfiguration);
            cm.createCache(com.shaowei.workflow.domain.Team.class.getName() + ".traders", jcacheConfiguration);
            cm.createCache(com.shaowei.workflow.domain.Client.class.getName(), jcacheConfiguration);
            cm.createCache(com.shaowei.workflow.domain.Action.class.getName(), jcacheConfiguration);
            cm.createCache(com.shaowei.workflow.domain.FeedBack.class.getName(), jcacheConfiguration);
            cm.createCache(com.shaowei.workflow.domain.Region.class.getName(), jcacheConfiguration);
            cm.createCache(com.shaowei.workflow.domain.Type.class.getName(), jcacheConfiguration);
            // jhipster-needle-ehcache-add-entry
        };
    }
}
