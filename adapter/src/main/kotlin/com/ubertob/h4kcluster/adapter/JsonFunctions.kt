package com.ubertob.h4kcluster.adapter

fun htmlResultPage(num: Int): String = """
    <html>
    <body>
    <h1>The result of the sum of two numbers is</h1>
    <p>$num<p>
    </body>
    </html>
""".trimIndent()