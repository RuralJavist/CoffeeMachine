package com.coffeemachine.service.utils;

import com.coffeemachine.entities.RatingMetadata;
import com.coffeemachine.repositories.RatingMetaRepository;
import com.coffeemachine.repositories.RatingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
@Slf4j
public class StatisticCleaner {

    @Value("${machine.api.statistic.clean-time}")
    private int cleanTimeYears;

    private final RatingRepository ratingRepository;
    private final RatingMetaRepository ratingMetaRepository;

    @Scheduled(fixedDelayString = "${machine.api.cleaner.delay}", timeUnit = TimeUnit.HOURS)
    @Transactional
    public void cleanStatistic(){
        RatingMetadata ratingMetadata = ratingMetaRepository.findAll().get(0);
        LocalDateTime start = LocalDateTime.ofInstant(ratingMetadata.getCleanDate(), TimeZone.getDefault().toZoneId());
        LocalDateTime end = start.plusYears(cleanTimeYears);
        if (end.isBefore(LocalDateTime.now())) {
            log.info("Cleaner started!");
            ratingRepository.deleteRatingCounts();
            ratingMetadata.setCleanDate(Instant.now());
            ratingMetaRepository.save(ratingMetadata);
        }
    }

}
