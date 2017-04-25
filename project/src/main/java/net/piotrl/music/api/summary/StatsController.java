package net.piotrl.music.api.summary;

import net.piotrl.music.api.summary.activities.dto.MultitaskingOnProductivity;
import net.piotrl.music.api.summary.activities.dto.Productivity;
import net.piotrl.music.api.summary.activities.dto.SpentTimeAndTasksCorrelationScatterChart;
import net.piotrl.music.api.summary.artists.ArtistsSummary;
import net.piotrl.music.api.summary.dto.MostPopularArtistsProductivity;
import net.piotrl.music.api.summary.dto.MusicActivitySalienceSummary;
import net.piotrl.music.api.summary.tags.TagSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("api/stats")
public class StatsController {

    private final StatsService statsService;

    @Autowired
    public StatsController(StatsService statsService) {
        this.statsService = statsService;
    }

    @RequestMapping("music/year/{year}/month/{month}/summary")
    public List<MusicActivitySalienceSummary> musicProductivitySalienceMonthly(@PathVariable int year,
                                                                               @PathVariable int month,
                                                                               Principal principal) {
//        Assert.notNull(principal);
        return statsService.musicProductivitySalienceMonthly(year, month, 1l);
    }

    @RequestMapping("music/year/{year}/month/{month}/musicPlayedDuringActivities")
    public List<MusicActivitySalienceSummary> musicPlayedDuringActivities(@PathVariable int year,
                                                                          @PathVariable int month,
                                                                          Principal principal) {
//        Assert.notNull(principal);
        return statsService.musicPlayedDuringActivities(year, month, 1l);
    }

    @RequestMapping("music/year/{year}/month/{month}/popular")
    public MostPopularArtistsProductivity topArtistsProductivity(@PathVariable int year,
                                                                 @PathVariable int month,
                                                                 Principal principal) {
//        Assert.notNull(principal);
        return statsService.topArtistsProductivity(year, month, 1l);
    }

    @RequestMapping("tags/year/{year}/month/{month}/popular")
    public List<TagSummary> mostPopularTags(@PathVariable int year,
                                            @PathVariable int month,
                                            Principal principal) {
//        Assert.notNull(principal);
        return statsService.mostPopularTags(year, month, 1l);
    }

    @RequestMapping("artists/year/{year}/month/{month}/popular")
    public List<ArtistsSummary> topArtists(@PathVariable int year,
                                           @PathVariable int month,
                                           Principal principal) {
//        Assert.notNull(principal);
        return statsService.mostPopularArtists(year, month, 1l);
    }

    @RequestMapping("activities/year/{year}/month/{month}/day/{day}/multitasking")
    public List<MultitaskingOnProductivity> activitiesMultitaskingOnProductivity(@PathVariable int year,
                                                                                 @PathVariable int month,
                                                                                 @PathVariable int day,
                                                                                 Principal principal) {
//        Assert.notNull(principal);
        return statsService.activitiesMultitaskingOnProductivity(year, month, day, 1l);
    }

    @RequestMapping("activities/year/{year}/month/{month}/day/{day}/spentTimeTasksScatter")
    public Productivity<List<SpentTimeAndTasksCorrelationScatterChart>> spentTimeAndTasksCorrelationScatterChart(@PathVariable int year,
                                                                                                                 @PathVariable int month,
                                                                                                                 @PathVariable int day,
                                                                                                                 Principal principal) {
//        Assert.notNull(principal);
        return statsService.spentTimeAndTasksCorrelationScatterChart(year, month, day, 1l);
    }

    @RequestMapping("activities/year/{year}/month/{month}/spentTimeTasksScatter")
    public Productivity<List<SpentTimeAndTasksCorrelationScatterChart>> spentTimeAndTasksCorrelationScatterChart(@PathVariable int year,
                                                                                                                 @PathVariable int month,
                                                                                                                 Principal principal) {
//        Assert.notNull(principal);
        return statsService.spentTimeAndTasksCorrelationScatterChart(year, month, 1l);
    }
}
