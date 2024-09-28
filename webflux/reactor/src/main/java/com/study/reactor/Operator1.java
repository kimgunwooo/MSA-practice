package com.study.reactor;

import reactor.core.publisher.Flux;

import java.time.Duration;

public class Operator1 {

    //map
    public Flux<Integer> fluxMap() {
        return Flux.range(1,10)
                .map(i -> i * 2)
                .log();
    }

    //filter
    public Flux<Integer> fluxFilter() {
        return Flux.range(1,10)
                .filter(i -> i > 5)
                .log();
    }

    //take
    public Flux<Integer> fluxTake() {
        return Flux.range(1,10)
                .filter(i -> i > 5)
                .take(3)
                .log();
    }

    //flatmap
    public Flux<Integer> fluxFlatMap() {
        return Flux.range(1,10)
                .flatMap(i -> Flux.range(i*10,10)
                        .delayElements(Duration.ofMillis(100))) // 비동기이며, 순서가 보장되지 않음
                .log();
    }
}
