package at.shockbytes.coins.currency.price

import at.shockbytes.coins.currency.CryptoCurrency
import at.shockbytes.coins.currency.RealCurrency
import io.reactivex.Observable

/**
 * @author Martin Macheiner
 * Date: 14.06.2017.
 */

interface PriceManager {

    val info: PriceSource

    var isEnabled: Boolean

    fun getSpotPrice(from: CryptoCurrency, to: RealCurrency): Observable<PriceConversion>

    fun getBuyPrice(from: CryptoCurrency, to: RealCurrency): Observable<PriceConversion>

    fun getSellPrice(from: CryptoCurrency, to: RealCurrency): Observable<PriceConversion>

    fun supportsCurrencyConversion(currency: CryptoCurrency): Boolean

}
