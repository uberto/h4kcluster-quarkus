package com.ubertob.h4kcluster.ui

import com.ubertob.h4kcluster.adapter.toOkResponse
import com.ubertob.h4kcluster.domain.UiHub
import org.http4k.core.HttpHandler
import org.http4k.core.Method
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status.Companion.OK
import org.http4k.core.body.form
import org.http4k.routing.bind
import org.http4k.routing.routes


class UiHandler(val hub: UiHub) : HttpHandler {

    val routes = routes(
        "/" bind Method.GET to {  Response(OK).body(landingPageHtml()) },
        "/submit" bind Method.POST to ::showResult
    )

    private fun showResult(req: Request) =
        req.form("words")
            .orEmpty()
            .let(hub::countWords)
            .let(::showResultHtml)
            .let(::toOkResponse)

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

    <div class="container">
        <h2>The number of words is $res</h2>
    
        <p><a href="/">try another text</a></p>
    </div>
  </body>
  </html>
""".trimIndent()