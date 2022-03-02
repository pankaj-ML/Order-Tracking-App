package Order.Model

import org.springframework.data.mongodb.core.mapping.Document

@Document("order_records")
data class order
    (
    var trackingID: String? = null,
    var name: String? = null,
    var city: String? = null,
    var shipcity: String? = null,
    var state: String? = null,
    var shipdate: String? = null,
    var lastdate: String? = null
)