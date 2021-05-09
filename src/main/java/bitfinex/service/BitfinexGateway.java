package bitfinex.service;

import bitfinex.service.message.MessageSender;
import bitfinex.service.message.domain.Wallet;
import bitfinex.service.message.domain.WithdrawalRequestDto;
import domain.Currency;

import java.math.BigDecimal;
import java.util.UUID;

public class BitfinexGateway {

    private MessageSender messageSender = new MessageSender();

    public String retrieveWallets() {
        String apiPath = "v2/auth/r/wallets";
        return messageSender.sendMessage(apiPath);
    }

    public String submitWithdrawalRequest(Currency currency, BigDecimal amount, String destinationAddress) {
        String apiPath = "v2/auth/w/withdraw";

        String paymentId = UUID.randomUUID().toString();

        WithdrawalRequestDto withdrawalRequestDto = new WithdrawalRequestDto(Wallet.exchange, currency.name(), amount,
                destinationAddress, paymentId);

        return messageSender.sendMessage(apiPath, withdrawalRequestDto);
    }

    public static void main(final String... args) {
        BitfinexGateway bitfinexGateway = new BitfinexGateway();
        try {
            Currency currency = Currency.valueOf(args[0]);
            BigDecimal amount = new BigDecimal(args[1]);
            String destinationaddress = args[2];

            System.out.println("currency:"+currency);
            System.out.println("amount:"+amount);
            System.out.println("destinationaddress:"+destinationaddress);

            System.out.println(bitfinexGateway.retrieveWallets());
            System.out.println(bitfinexGateway.submitWithdrawalRequest(currency, amount, destinationaddress));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
