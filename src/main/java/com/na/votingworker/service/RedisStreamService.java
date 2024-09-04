package com.na.votingworker.service;

import com.na.votingworker.dao.VoteResultDao;
import com.na.votingworker.entity.VoteResult;
import com.na.votingworker.enums.AnimalEnum;
import com.na.votingworker.repository.VoteResultMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.stream.*;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
public class RedisStreamService {

    private final VoteResultDao voteResultDao;
    private final ReactiveRedisTemplate<String, String> redisTemplate;
    @Autowired
    private VoteResultMongoRepository voteResultMongoRepository;



    @Autowired
    public RedisStreamService(VoteResultDao voteResultDao, ReactiveRedisTemplate<String, String> redisTemplate) {
        this.voteResultDao = voteResultDao;
        this.redisTemplate = redisTemplate;
    }

    public void startProcessing() {
        String groupName = "voting-group";
        String consumerName = "voting-consumer";
        StreamOffset<String> votingstream = StreamOffset.fromStart("votingstream");
//        StreamOffset<String> streamOffset = StreamOffset.create("votingstream", ReadOffset.latest());
        Flux<ObjectRecord<String, String>> stream = redisTemplate
                .opsForStream()
                .read(String.class,
                        votingstream);

        stream.flatMap(record -> {
                    String id = record.getId().getValue();
                    System.out.println("Processing record ID: " + id);
                    String value = record.getValue();
                    AnimalEnum animalEnum = AnimalEnum.valueOf(value.toUpperCase());
                    voteResultDao.increaseVoteResultByAnimal(animalEnum);

                    // Acknowledge the message after processing
                    return redisTemplate
                            .opsForStream()
                            .delete(record)
                            .doOnSuccess(success -> System.out.println("deleted record ID: " + id));
                })
                .subscribe(
                        success -> System.out.println("Successfully processed records"),
                        error -> System.err.println("Error during stream processing: " + error)
                );

    }

    public void listenToStream(String streamKey) {
//        StreamOffset<String> streamOffset = StreamOffset.create("votingstream", ReadOffset.latest());
        StreamOffset<String> votingstream = StreamOffset.fromStart("votingstream");
        Flux<ObjectRecord<String, String>> stream = redisTemplate
                .opsForStream()
                .read(String.class, votingstream)
                .repeat();

        stream.flatMap(record -> {
                    String id = record.getId().getValue();
                    System.out.println("Processing record ID: " + id);
                    String value = record.getValue();
                    AnimalEnum animalEnum = AnimalEnum.valueOf(value.toUpperCase());
                    voteResultDao.increaseVoteResultByAnimal(animalEnum);

                    // Acknowledge the message after processing
                    return redisTemplate
                            .opsForStream()
                            .delete(record)
                            .doOnSuccess(success -> System.out.println("deleted record ID: " + id));
                })
                .subscribe(
                        success -> System.out.println("Successfully processed records"),
                        error -> System.err.println("Error during stream processing: " + error)
                );
    }
}