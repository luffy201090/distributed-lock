package com.example.distributedlock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.support.locks.LockRegistry;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.*;

@Service
public class OrderService {
    @Autowired
    OrderRepo orderRepo;

    @Autowired
    LockRegistry lockRegistry;

    public Order updateOrder(Long orderId, Order body) throws InterruptedException {
        Lock lock = lockRegistry.obtain(String.valueOf(orderId));

        Order order = orderRepo.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
        boolean isAcquired = lock.tryLock(1, TimeUnit.SECONDS);
        if(isAcquired) {
            try {
                order.setName(body.getName());
                orderRepo.save(order);
                return order;
            } finally {
                lock.unlock();
            }

        } else {
            return updateOrder(orderId, body);
        }
    }
}
