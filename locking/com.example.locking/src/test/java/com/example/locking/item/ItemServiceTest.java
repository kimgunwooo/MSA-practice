package com.example.locking.item;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ItemServiceTest {

    private static final Logger logger = LoggerFactory.getLogger(ItemServiceTest.class);

    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void testPessimisticLocking() throws InterruptedException {
        // 초기 데이터 설정
        logger.info("초기 아이템 데이터를 설정합니다.");
        Item item = new Item();
        item.setName("Item 1");
        item.setQuantity(10);
        itemRepository.save(item);

        // 첫 번째 트랜잭션: 아이템 수량을 20으로 업데이트
        Thread thread1 = new Thread(() -> {
            logger.info("스레드 1: 아이템 수량 업데이트를 시도합니다.");
            itemService.updateItemQuantity(item.getId(), 20);
            logger.info("스레드 1: 아이템 수량 업데이트 완료.");
        });

        // 두 번째 트랜잭션: 아이템 수량을 30으로 업데이트
        Thread thread2 = new Thread(() -> {
            logger.info("스레드 2: 아이템 수량 업데이트를 시도합니다.");
            itemService.updateItemQuantity(item.getId(), 30);
            logger.info("스레드 2: 아이템 수량 업데이트 완료.");
        });

        // 두 스레드를 동시에 실행
        thread2.start();
        thread1.start();


        // 두 스레드가 종료될 때까지 대기
        thread1.join();
        thread2.join();

        // 최종 결과를 확인합니다.
        Item updatedItem = itemService.findItemById(item.getId());
        logger.info("최종 아이템 수량: {}", updatedItem.getQuantity());
    }
}