package net.piotrl.music.api.summary;

import net.piotrl.music.api.summary.activities.dto.MultitaskingOnProductivity;
import net.piotrl.music.api.summary.activities.ActivitiesStatsRepository;
import net.piotrl.music.api.summary.activities.dto.Productivity;
import net.piotrl.music.api.summary.activities.dto.SpentTimeAndTasksCorrelationScatterChart;
import net.piotrl.music.api.summary.artists.ArtistsRepository;
import net.piotrl.music.api.summary.artists.ArtistsSummary;
import net.piotrl.music.api.summary.dto.ArtistProductivity;
import net.piotrl.music.api.summary.dto.MostPopularArtistsProductivity;
import net.piotrl.music.api.summary.dto.MusicActivitySalienceSummary;
import net.piotrl.music.api.summary.dto.ProductivityValue;
import net.piotrl.music.api.summary.tags.TagSummary;
import net.piotrl.music.api.summary.tags.TagsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StatsService {

    private final StatsRepository statsRepository;
    private final TagsRepository tagsRepository;
    private final ArtistsRepository artistsRepository;
    private final ActivitiesStatsRepository activitiesStatsRepository;

    @Autowired
    public StatsService(StatsRepository statsRepository, TagsRepository tagsRepository, ArtistsRepository artistsRepository, ActivitiesStatsRepository activitiesStatsRepository) {
        this.statsRepository = statsRepository;
        this.tagsRepository = tagsRepository;
        this.artistsRepository = artistsRepository;
        this.activitiesStatsRepository = activitiesStatsRepository;
    }

    public MostPopularArtistsProductivity topArtistsProductivity(int year, int month, long accountId) {
        LocalDate firstDayOfMonth = LocalDate.of(year, month, 1);
        LocalDate lastDayOfMonth = firstDayOfMonth.withDayOfMonth(firstDayOfMonth.lengthOfMonth());

        List<ArtistProductivity> artistsProductivity = statsRepository.mostPopularArtistsProductivityStats(firstDayOfMonth, lastDayOfMonth, accountId);
        ProductivityValue<Double> averageProductivityForMusic = statsRepository.averageProductivityForMusic(firstDayOfMonth, lastDayOfMonth, accountId);

        MostPopularArtistsProductivity mostPopularArtistsProductivity = new MostPopularArtistsProductivity();
        mostPopularArtistsProductivity.setArtists(artistsProductivity);
        mostPopularArtistsProductivity.setAverageHoursActivity(averageProductivityForMusic);
        return mostPopularArtistsProductivity;
    }

    List<MusicActivitySalienceSummary> musicProductivitySalienceMonthly(int year, int month, long userId) {
        LocalDate firstDayOfMonth = LocalDate.of(year, month, 1);
        LocalDate lastDayOfMonth = firstDayOfMonth.withDayOfMonth(firstDayOfMonth.lengthOfMonth());
        return statsRepository.musicProductivitySalienceMonthly(firstDayOfMonth, lastDayOfMonth, userId);
    }

    List<MusicActivitySalienceSummary> musicPlayedDuringActivities(int year, int month, long userId) {
        LocalDate firstDayOfMonth = LocalDate.of(year, month, 1);
        LocalDate lastDayOfMonth = firstDayOfMonth.withDayOfMonth(firstDayOfMonth.lengthOfMonth());
        return statsRepository.musicPlayedDuringActivities(firstDayOfMonth, lastDayOfMonth, userId);
    }

    List<TagSummary> mostPopularTags(int year, int month, long accountId) {
        LocalDate monthDate = LocalDate.of(year, month, 1);
        return tagsRepository.mostPopularTagsInMonth(monthDate, accountId);
    }

    List<ArtistsSummary> mostPopularArtists(int year, int month, long accountId) {
        LocalDate firstDayOfMonth = LocalDate.of(year, month, 1);
        LocalDate lastDayOfMonth = firstDayOfMonth.withDayOfMonth(firstDayOfMonth.lengthOfMonth());
        return artistsRepository.mostPopularArtists(firstDayOfMonth, lastDayOfMonth, accountId);
    }

    List<MultitaskingOnProductivity> activitiesMultitaskingOnProductivity(int year, int month, int day, long accountId) {
        LocalDateTime startOfDay = LocalDate.of(year, month, day).atStartOfDay();
        LocalDateTime endOfDay = startOfDay.toLocalDate().atTime(LocalTime.MAX);
        return activitiesStatsRepository.activitiesFrequencyAndProductivity(startOfDay, endOfDay, accountId);
    }

    Productivity<List<SpentTimeAndTasksCorrelationScatterChart>> spentTimeAndTasksCorrelationScatterChart(
            int year, int month, int day, long accountId) {
        LocalDateTime startOfDay = LocalDate.of(year, month, day).atStartOfDay();
        LocalDateTime endOfDay = startOfDay.toLocalDate().atTime(LocalTime.MAX);
        List<SpentTimeAndTasksCorrelationScatterChart> list = activitiesStatsRepository.spentTimeAndTasksCorrelationScatterChart(startOfDay, endOfDay, accountId);

        return new Productivity<>(
                list.stream().filter(i -> i.getProductivity() > 0).collect(Collectors.toList()),
                list.stream().filter(i -> i.getProductivity() < 0).collect(Collectors.toList()),
                list.stream().filter(i -> i.getProductivity() == 0).collect(Collectors.toList())
        );
    }

    Productivity<List<SpentTimeAndTasksCorrelationScatterChart>> spentTimeAndTasksCorrelationScatterChart(
            int year, int month, long accountId) {
        LocalDateTime firstDayOfMonth = LocalDate.of(year, month, 1).atStartOfDay();
        LocalDateTime lastDayOfMonth = firstDayOfMonth.withDayOfMonth(
                firstDayOfMonth.toLocalDate().lengthOfMonth()
        ).toLocalDate().atTime(LocalTime.MAX);

        List<SpentTimeAndTasksCorrelationScatterChart> list = activitiesStatsRepository.spentTimeAndTasksCorrelationScatterChart(firstDayOfMonth, lastDayOfMonth, accountId);
        return new Productivity<>(
                list.stream().filter(i -> i.getProductivity() > 0).collect(Collectors.toList()),
                list.stream().filter(i -> i.getProductivity() < 0).collect(Collectors.toList()),
                list.stream().filter(i -> i.getProductivity() == 0).collect(Collectors.toList())
        );
    }
}
