import com.ubertob.h4kcluster.domain.CountWordsRequest
import com.ubertob.kondor.json.*

object JCountWordsRequest : JAny<CountWordsRequest>() {
    private val id by num(CountWordsRequest::id)

    private val lines by array(JString, CountWordsRequest::lines)

    private val useDictionary by bool(CountWordsRequest::useDictionary)

    private val user by str(CountWordsRequest::user)

    public override fun JsonNodeObject.deserializeOrThrow(): CountWordsRequest =
        CountWordsRequest(
            id = +id,
            lines = +lines,
            useDictionary = +useDictionary,
            user = +user
        )
}

/*
public object JCountWordsRequest : JAny<CountWordsRequest> {
  private val id: String = str(CountWordsRequest::id)

  private val lines: String = str(CountWordsRequest::lines)

  private val useDictionary: String = str(CountWordsRequest::useDictionary)

  private val user: String = str(CountWordsRequest::user)

  public override fun JsonNodeObject.deserializeOrThrow(): CountWordsRequest =
      CountWordsRequest(
        id = +id,
        lines = +lines,
        useDictionary = +useDictionary,
        user = +user
      )
}
 */