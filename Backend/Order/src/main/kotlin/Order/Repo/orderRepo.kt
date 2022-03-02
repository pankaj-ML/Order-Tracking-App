package Order.Repo

import Order.Model.order
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface orderRepo: MongoRepository<order,Long>
{
    @Query(value="{'trackingID' : ?0}")
    fun findbyTrackingID(trackingID: String): Any
}