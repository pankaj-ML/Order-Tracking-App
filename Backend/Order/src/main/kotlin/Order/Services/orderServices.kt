package Order.Services

import Order.Model.order

interface orderServices
{
    fun addorder(order: order): order
    fun tracking(trackingID: String): order
}