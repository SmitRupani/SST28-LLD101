
import com.example.payments.PaymentGateway;

public class FastPayAdapter implements PaymentGateway{
    
    PaymentGateway client;

    public FastPayAdapter(PaymentGateway client){
        this.client=client;
    }

    @Override
    public String charge(String customerId, int amountCents){
        return this.client.charge(customerId, amountCents);
    }
}
