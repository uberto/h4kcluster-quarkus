import com.ubertob.h4kcluster.domain.CountWordsRequest
import com.ubertob.kondor.json.*
import com.ubertob.kondor.json.jsonnode.JsonNodeObject

object JCountWordsRequest : JAny<CountWordsRequest>() {
    private val id by num(CountWordsRequest::id)

    private val lines by array(JString, CountWordsRequest::lines)

    private val useDictionary by bool(CountWordsRequest::useDictionary)

    private val user by str(CountWordsRequest::user)

    override fun JsonNodeObject.deserializeOrThrow(): CountWordsRequest =
        CountWordsRequest(
            id = +id,
            lines = +lines,
            useDictionary = +useDictionary,
            user = +user
        )
}
