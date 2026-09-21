package com.back.boundedContext.cash.in;

import com.back.boundedContext.cash.app.CashFacade;
import com.back.shared.cash.event.CashMemberCreatedEvent;
import com.back.shared.market.event.MarketOrderPaymentRequestedEvent;
import com.back.shared.member.event.MemberJoinedEvent;
import com.back.shared.member.event.MemberModifiedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

import static org.springframework.transaction.annotation.Propagation.REQUIRES_NEW;
import static org.springframework.transaction.event.TransactionPhase.AFTER_COMMIT;

@Component
@RequiredArgsConstructor
public class CashEventListener {
    private final CashFacade cashFacade;

    @TransactionalEventListener(phase = AFTER_COMMIT)
    @Transactional(propagation = REQUIRES_NEW)
    public void handle(MemberJoinedEvent event) {
        cashFacade.syncMember(event.getMember());
    }

    @TransactionalEventListener(phase = AFTER_COMMIT)
    @Transactional(propagation = REQUIRES_NEW)
    public void handle(MemberModifiedEvent event) {
        cashFacade.syncMember(event.getMember());
    }

    @TransactionalEventListener(phase = AFTER_COMMIT)
    @Transactional(propagation = REQUIRES_NEW)
    public void handle(CashMemberCreatedEvent event) { // STUDY: 이건 무조건 cashFacade의 syncMember에서 호출해야 하는데 따로 그걸로만 제한할 수 있는 방법이 있을까?
        cashFacade.createWallet(event.getMember());
    }

    @TransactionalEventListener(phase = AFTER_COMMIT)
    @Transactional(propagation = REQUIRES_NEW)
    public void handle(MarketOrderPaymentRequestedEvent event) {
        cashFacade.handle(event);
    }
}