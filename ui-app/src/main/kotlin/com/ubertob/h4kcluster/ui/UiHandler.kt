package com.ubertob.h4kcluster.ui

import com.ubertob.h4kcluster.domain.UiHub
import org.http4k.core.HttpHandler
import org.http4k.core.Method
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status.Companion.OK
import org.http4k.core.body.form
import org.http4k.routing.bind
import org.http4k.routing.routes


class UiHandler(hub: UiHub) : HttpHandler {

    val routes = routes(
            "/" bind Method.GET to { _ ->
                Response(OK).body(landingPageHtml())
            },
            "/submit" bind Method.POST to {req ->

                val text = req.form("words").orEmpty()

                val wordNo = hub.countWords(text)
                Response(OK).body(showResultHtml(wordNo))
            }
    )

    override fun invoke(req: Request): Response = routes(req)

}

private fun landingPageHtml(): String = """
<!doctype html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

    <title>Count the words cluster</title>
  </head>
  <body>

<div class="container">
    <h1>Hello to the H4k Cluster example!</h1>
<form action="/submit" method="post">    
        <label for="textarea">Insert a text here and then click the button to count the number of words</label>
        <textarea class="form-control" id="textarea" name="words" rows="10"></textarea>
        <button type="submit" class="btn btn-primary">Count the words</button>
</form>

</div>

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
  </body>
</html>


""".trimIndent()


fun showResultHtml(res: Int): String = """
<!doctype html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

    <title>Count the words cluster</title>
  </head>
  <body>

    <h1>The number of words is $res</h1>
  </body>
  </html>
""".trimIndent()