package Order.Services

import Order.Model.order
import Order.Repo.orderRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class orderServicesIMP: orderServices
{
    @Autowired
    lateinit var orderrepo: orderRepo

    override fun addorder(order: order): order {
       return orderrepo.save(order)
    }

    override fun tracking(trackingID: String): order
    {
        return orderrepo.findbyTrackingID(trackingID) as order
    }
}