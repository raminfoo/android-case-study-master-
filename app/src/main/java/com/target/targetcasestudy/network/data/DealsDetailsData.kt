package com.target.targetcasestudy.network.data

class DealsDetailsData(
    var aisle: String = "", // b2
    var description: String = "", // minim ad et minim ipsum duis irure pariatur deserunt eu cillum anim ipsum velit tempor eu pariatur sunt mollit tempor ut tempor exercitation occaecat ad et veniam et excepteur velit esse eu et ut ipsum consectetur aliquip do quis voluptate cupidatat eu ut consequat adipisicing occaecat adipisicing proident laborum laboris deserunt in laborum est anim ad non
    var id: Int = 0, // 0
    var image_url: String = "", // https://picsum.photos/id/0/300/300
    var regular_price: RegularPrice? = null,
    var sale_price: SalePrice? = null,
    var title: String = "" // non mollit veniam ex
) {
    class RegularPrice(
        var amount_in_cents: Int = 0, // 18406
        var currency_symbol: String = "", // $
        var display_string: String = "" // $184.06
    )
    class SalePrice(
        var amount_in_cents: Int = 0, // 734
        var currency_symbol: String = "", // $
        var display_string: String = "" // $7.34
    )
}