package kbitfinex.message.domain

/**
 * Parameters for Bitfinex withdraw https://docs.bitfinex.com/reference#rest-auth-withdraw
 */
class BitfinexWithdrawalRequest(val wallet: BitfinexWallet, val method: String, val amount: String, val address: String, val paymentId: String)