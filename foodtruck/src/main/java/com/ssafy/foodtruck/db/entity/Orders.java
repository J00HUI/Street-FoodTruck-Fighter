package com.ssafy.foodtruck.db.entity;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@DynamicInsert
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Orders extends BaseEntity {

    @ColumnDefault("false")
    private Boolean isDone;

    @ColumnDefault("false")
    private Boolean isAccepted;

    @ColumnDefault("false")
    private Boolean isCanceled;

	@ColumnDefault("false")
	private Boolean isPaid;

    private LocalDateTime doneDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "foodtruck_id")
    private FoodTruck foodTruck;

    @Builder.Default
    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL)
    private List<OrdersMenu> ordersMenuList = new ArrayList<>();

//	@Builder.Default
//	@OneToOne(mappedBy = "orders", cascade = CascadeType.ALL)
//	private Review review;

    public void setIsAccepted(boolean isAccepted, LocalDateTime doneDate) {
        this.isAccepted = isAccepted;
		this.doneDate = doneDate;
    }

    public void setIsCanceled(boolean isCanceled) {
		this.isCanceled = isCanceled;
    }

	public void setIsDone(boolean isDone) {
		this.isDone = isDone;
	}

	public void setIsPaid(boolean isPaid) {
		this.isPaid = isPaid;
	}
}
