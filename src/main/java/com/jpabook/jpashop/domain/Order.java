package com.jpabook.jpashop.domain;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Orders")
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue
    @Column(name = "ORDER_ID")
    private Long id;

    @ManyToOne // Order 입장에선 여러 주문을 회원 하나가 할 수 있으니
    @JoinColumn(name = "MEMBER_ID")
    // FK를 가지
    private Member member;


    @OneToMany(mappedBy = "ORDER_ID") // 현재 ORDER_ID에 맞는 놈이 갖고 있는 ITEM 목록들이니
    // ORDER_ID로 매핑된 이라는 의미로써 사용 가능할 듯.
    private List<OrderItem> orderItems = new ArrayList<>();
    private LocalDateTime OrderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }
}
