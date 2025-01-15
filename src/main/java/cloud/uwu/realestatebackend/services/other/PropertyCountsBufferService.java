package cloud.uwu.realestatebackend.services.other;

import cloud.uwu.realestatebackend.entities.property.statistics.PropertyCounts;
import cloud.uwu.realestatebackend.repositories.property.statistics.PropertyCountsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class PropertyCountsBufferService {
    private final PropertyCountsRepository propertyCountsRepository;
    private final ConcurrentHashMap<UUID, PropertyCounts> propertyCountsBuffer = new ConcurrentHashMap<>();

    public void updatePropertyCountsInMemory(UUID id, int visits, int likes, int dislikes) {
        propertyCountsBuffer.merge(id, new PropertyCounts(id, 0, visits, likes, dislikes, null, null, null),
                (existing, update) -> {
                    existing.setVisits(existing.getVisits() + visits);
                    existing.setLikes(existing.getLikes() + likes);
                    existing.setDislikes(existing.getDislikes() + dislikes);

                    return existing;
                });

        System.out.println("Updated in memory: " + id);
    }

    @Scheduled(fixedRate = 60000)
    public void flushBufferedCountsToDatabase() {
        System.out.println("Flushing buffered counts to DB...");

        propertyCountsBuffer.forEach((id, counts) -> {
            if (counts.getVisits() > 0 || counts.getLikes() > 0 || counts.getDislikes() > 0) {
                propertyCountsRepository.findById(id).ifPresent(existing -> {
                    existing.setVisits(existing.getVisits() + counts.getVisits());
                    existing.setLikes(existing.getLikes() + counts.getLikes());
                    existing.setDislikes(existing.getDislikes() + counts.getDislikes());

                    propertyCountsRepository.saveAndFlush(existing);
                    System.out.println("Flushed to DB: " + id);
                });
            }
        });
        propertyCountsBuffer.clear();
        System.out.println("Buffer cleared.");
    }
}