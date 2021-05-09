package bitfinex.service.message.domain;

import java.math.BigDecimal;

/**
 * Parameters for Bitfinex withdraw https://docs.bitfinex.com/reference#rest-auth-withdraw
 */
public class WithdrawalRequestDto {

    // Select the wallet from which to transfer (exchange, margin, funding
    // (can also use the old labels which are exchange, trading and deposit respectively))
    private Wallet wallet;

    // aka currency - Method of withdrawal. For an up-to-date mapping of methods and their respective currencies
    private String method;

    // Amount of Withdrawal
    private BigDecimal amount;

    // Destination address
    private String destinationaddress;

    // Specify a tag/memo/etc
    private String payment_id;

    public WithdrawalRequestDto(Wallet wallet, String method, BigDecimal amount, String destinationaddress, String payment_id) {
        this.wallet = wallet;
        this.method = method;
        this.amount = amount;
        this.destinationaddress = destinationaddress;
        this.payment_id = payment_id;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public String getMethod() {
        return method;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getDestinationaddress() {
        return destinationaddress;
    }

    public String getPayment_id() {
        return payment_id;
    }
}
