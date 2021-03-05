package learningtests.streams;

import java.util.*;
import java.util.stream.*;

import org.junit.jupiter.api.*;


public class StreamsTest
{
    @Test
    public void givenNullCollection_whenUsingStreams_thenGetEmptyStream()
    {
        // given
        HashSet<String> strings = null;

        // when
        final var list = collectionToStream(strings).map(s->s.toUpperCase()).collect(Collectors.toList());

        // then
        Assertions.assertTrue(list.isEmpty());
    }

    private Stream<String> collectionToStream(Collection<String> collection) {
        return Optional.ofNullable(collection)
                .map(Collection::stream)
                .orElseGet(Stream::empty);
    }
}
