package com.ssc.jooq;

import org.jooq.lambda.Seq;
import org.jooq.lambda.tuple.Tuple2;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.jooq.lambda.tuple.Tuple.tuple;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

@SpringBootTest
public class MapsUsingJooq {

    static Map<Integer, String> map = new LinkedHashMap<>();

    static {
        map.put(1, "a");
        map.put(2, "b");
        map.put(3, "c");
    }

    @Test
    public void mapUsingJooq() {

        assertEquals(
                Arrays.asList(
                        tuple(1, "a"),
                        tuple(2, "b"),
                        tuple(3, "c")
                ),
                Seq.seq(map).toList()
        );

    }

    @Test
    public void swapMapUsingJooq() {

        Map<Object, Object> map1 = Seq.seq(map)
                .map(Tuple2::swap)
                .toMap(Tuple2::v1, Tuple2::v2);

        Map<Object, Object> map2 = Seq.seq(map)
                .toMap(Tuple2::v2, Tuple2::v1);

        assertEquals(map1, map2);

    }

}
