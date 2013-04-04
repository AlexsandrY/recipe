package org.recipe

import pro.savant.circumflex._, core._, web._, freemarker._
import java.util.Date

class Main extends Router {
  val log = new Logger("org.recipe")

  'currentDate := new Date

  get("/") = ftl("/index.ftl")

  get("/add") = ftl("/add.ftl")
  get("/list") = ftl("/list.ftl")

  post("/?") = {
    try {
      val r = new Recipe
      r.dishName:= param("n").trim
      r.nationalAttach:= param("na").trim
      r.numberOfPerson:= param("p").trim
      r.specification:= param("s").trim
      r.wayCooking:= param("w").trim
      r.timeCooking:= param("t").trim
      r.caloricValue:= param("v").trim
      r.complexity:= param("c").trim
      r.save()
      sendRedirect("/" + r.id())
    } catch {
      case e: ValidationException =>
        notices.addErrors(e.errors)
        sendRedirect("/add")
    }
  }

  sub("/:id") = {
    get("/?") = ftl("/view.ftl")
    get("/~edit") = ftl("/edit.ftl")
    get("/~delete") = ftl("/delete.ftl")
  }

}