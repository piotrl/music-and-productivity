package net.piotrl.music.modules.lastfm.aggregation;

import net.piotrl.music.mocks.AggregationPropertiesMock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

@SpringBootTest
@RunWith(SpringRunner.class)
public class LastfmAggregationServiceTest {

    @Autowired
    private LastfmAggregationService lastfmAggregationService;

    @Test
    public void startAggregation() throws Exception {
        LocalDate recentDate = LocalDate.of(2017, 3, 7);
        lastfmAggregationService.startAggregation(AggregationPropertiesMock.globalContext(), recentDate);
    }
}