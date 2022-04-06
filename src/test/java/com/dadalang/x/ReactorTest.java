package com.dadalang.x;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import reactor.util.function.Tuple2;
import redis.clients.jedis.Tuple;

import java.time.Duration;
import java.util.List;
import java.util.stream.Stream;

/**
 * @version 1.0
 * @Author lj
 * @date 2021/8/24 3:52 下午
 * @desc
 */
@SpringBootTest
public class ReactorTest {

    @Test
    public void createAFlux_just() {
        Flux<String> fruitFlux = Flux.just("apple", "orange", "grape", "banna", "starwberry");

        fruitFlux.subscribe(f -> System.out.println("Here's some fruit: " + f));

        StepVerifier.create(fruitFlux)
                .expectNext("apple")
                .expectNext("orange")
                .expectNext("grape")
                .expectNext("banna")
                .expectNext("starwberry")
                .verifyComplete();
    }

    @Test
    public void createAFlux_fromArray() {
        String[] fruits = new String[] {"apple", "orange", "grape", "banna", "starwberry"};
        Flux<String> fruitFlux = Flux.fromArray(fruits);
        StepVerifier.create(fruitFlux)
                .expectNext("apple")
                .expectNext("orange")
                .expectNext("grape")
                .expectNext("banna")
                .expectNext("starwberry")
                .verifyComplete();
    }

    @Test
    public void createAFlux_fromStream() {
        Stream<String> fruitStream = Stream.of("apple", "orange", "grape", "banna", "starwberry");
        Flux<String> fruitFlux = Flux.fromStream(fruitStream);
        StepVerifier.create(fruitFlux)
                .expectNext("apple")
                .expectNext("orange")
                .expectNext("grape")
                .expectNext("banna")
                .expectNext("starwberry")
                .verifyComplete();
    }

    // 组合反应式类型
    @Test
    public void mergeFluxs() {
        Flux<String> fruitFlux = Flux
                .just("apple", "orange", "grape", "banna", "starwberry")
                .delayElements(Duration.ofMillis(500));                                 // 每隔500毫秒发发布一次订阅
        Flux<String> colorFlux = Flux.just("red", "orange", "purple","yellow","red")
                .delaySubscription(Duration.ofMillis(250))                              // 第一次发布时等待250毫秒
                .delayElements(Duration.ofMillis(500));
        Flux<String> mergedFlux = fruitFlux.mergeWith(colorFlux);
        StepVerifier.create(mergedFlux)
                .expectNext("apple")
                .expectNext("red")
                .expectNext("orange")
                .expectNext("orange")
                .expectNext("grape")
                .expectNext("purple")
                .expectNext("banna")
                .expectNext("yellow")
                .expectNext("starwberry")
                .expectNext("red")
                .verifyComplete();
    }

    // zip：当两个Flux对象压缩在一起的时候，他将会昌盛一个新的发布元祖的Flux,其中的每个元祖都包含了来自每个源的Flux数据项。
    @Test
    public void zipFluxes() {
        Flux<String> fruitFlux = Flux.just("apple", "orange", "grape", "banna", "starwberry");
        Flux<String> colorFlux = Flux.just("red", "orange", "purple","yellow","red");
        Flux<Tuple2<String, String>> zippedFlux = Flux.zip(fruitFlux, colorFlux);

        StepVerifier.create(zippedFlux)
                .expectNextMatches(p -> p.getT1().equals("apple") && p.getT2().equals("red"))
                .expectNextMatches(p -> p.getT1().equals("orange") && p.getT2().equals("red"))
                .expectNextMatches(p -> p.getT1().equals("grap") && p.getT2().equals("red"))
                .expectNextMatches(p -> p.getT1().equals("banna") && p.getT2().equals("red"))
                .expectNextMatches(p -> p.getT1().equals("starwberry") && p.getT2().equals("red"))
                .expectComplete();
    }
    // 为zip()方法提供一个合并函数来生成你想要的任何对象
    @Test
    public void zipFluxesToObject() {
        Flux<String> fruitFlux = Flux.just("apple", "orange", "grape", "banna", "starwberry");
        Flux<String> colorFlux = Flux.just("red", "orange", "purple","yellow","red");
        Flux<String> zippedFlux = Flux.zip(fruitFlux, colorFlux, (f, c) -> f + "is" + c);

        StepVerifier.create(zippedFlux)
                .expectNext("apple is red")
                .expectNext("orange is orange")
                .expectNext("grape is purple")
                .expectNext("banna is yellow")
                .expectNext("starwberry is red")
                .expectComplete();
    }

    // 从第一个产生值的Flux中发布值,就是发布最早产生结果的Flux
    @Test
    public void firstFlux() {
        Flux<String> fruitFlux = Flux.just("apple", "orange", "grape", "banna", "starwberry")
                .delaySubscription(Duration.ofMillis(500));                                     // 延迟500毫秒发送订阅，晚于colorFlux，被丢弃
        Flux<String> colorFlux = Flux.just("red", "orange", "purple","yellow","red");

        Flux<String> first = Flux.first(fruitFlux, colorFlux);                                  // 方法已过期
        StepVerifier.create(first)
                .expectNext("red")
                .expectNext("orange")
                .expectNext("purple")
                .expectNext("yellow")
                .expectNext("rd")
                .expectComplete();
    }
    // 截取反应式流
    @Test
    public void skipFlux() {
        Flux<String> fruitFlux = Flux.just("apple", "orange", "grape", "banna", "starwberry");
        Flux<String> resultFlux = fruitFlux.delayElements(Duration.ofSeconds(1))    // 每隔一秒发送一次订阅
                .skip(Duration.ofSeconds(4));

        StepVerifier.create(fruitFlux.skip(Duration.ofSeconds(4)))                  // 过滤前面四秒的内容
                .expectNext("starwberry")
                .expectComplete();

        StepVerifier.create(fruitFlux.take(1))                                      // 只截取前面一次订阅
                .expectNext("apple")
                .expectComplete();
    }

    @Test
    public void filterFlux() {
        Flux<String> fruitFlux = Flux.just("apple", "orange", "grape", "banna", "starwberry");
        Flux<String> colorFlux = Flux.just("red", "orange", "purple", "yellow", "red");

    }




}
