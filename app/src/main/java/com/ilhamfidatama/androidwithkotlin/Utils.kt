package com.ilhamfidatama.androidwithkotlin

import com.ilhamfidatama.androidwithkotlin.crypto.Cryptocurrency

object Utils {

    val cryptocurrencies: MutableList<Cryptocurrency> = mutableListOf(
        Cryptocurrency(name = "Bitcoin", lastPrice = 825561000.0, imagePath = "https://indodax.com/v2/logo/png/color/btc.png"),
        Cryptocurrency(name = "Dogecoin", lastPrice = 6000.0, imagePath = "https://indodax.com/v2/logo/png/color/doge.png"),
        Cryptocurrency(name = "Ethereum", lastPrice = 82796.0, imagePath = "https://indodax.com/v2/logo/png/color/eth.png"),
        Cryptocurrency(name = "Litecoin", lastPrice = 118368.0, imagePath = "https://indodax.com/v2/logo/png/color/ltc.png"),
        Cryptocurrency(name = "Wrapped Bitcoin", lastPrice = 93844.0, imagePath = "https://indodax.com/v2/logo/png/color/wbtc.png"),
        Cryptocurrency(name = "BitShares", lastPrice = 14532.0, imagePath = "https://indodax.com/v2/logo/png/color/bts.png"),
        Cryptocurrency(name = "Coti", lastPrice = 93844.0, imagePath = "https://indodax.com/v2/logo/png/color/coti.png")
    )

    val images = mutableListOf<String>(
            "https://indodax.com/v2/logo/png/color/ten.png",
            "https://indodax.com/v2/logo/png/color/act.png",
            "https://indodax.com/v2/logo/png/color/ada.png",
            "https://indodax.com/v2/logo/png/color/bnb.png",
            "https://indodax.com/v2/logo/png/color/btt.png",
            "https://indodax.com/v2/logo/png/color/firo.png",
            "https://indodax.com/v2/logo/png/color/ignis.png",
            "https://indodax.com/v2/logo/png/color/nxt.png",
            "https://indodax.com/v2/logo/png/color/zil.png",
    )
}