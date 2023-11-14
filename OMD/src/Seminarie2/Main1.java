package Seminarie2;

import java.util.*;

public class Main1 {
    
}

/**
 * Template Pattern! Först deklareras en abstrakt klass, sedan implementerar var mobilabonnemang sin egen
 * lösning. Vad är problemet med att byta typ? Telefonlistan försvinner?
 * 
 * Svar: Vilket mönster använda istället? Strategy!
 * Delegerar uppgifter till någon annan.
 */

interface Call {
}

interface Money {
}

class Money2 implements Money {

}

interface PhonePlan {
    Money debit(Call call);
}

abstract class PhoneContract {

    private String phoneNumber;
    protected List<Call> call = new LinkedList<>();
    protected PhonePlan plan;

    public PhoneContract (String phoneNumber) {
        this.phoneNumber = phoneNumber;
        usePlan(plan);
    }

    public void usePlan (PhonePlan plan) {
        this.plan = plan;
    }

    public Money totalFee() {
        //... plan.debit(call) ...

        return new Money2();
    }

    // ... övriga metoder ...
}

class CashCard implements PhonePlan {

    public Money debit(Call call) {
        // ...
        return new Money2();
    }
}

class KnockOut implements PhonePlan {

    public Money debit(Call call) {
        // ...
        return new Money2();
    }
}
