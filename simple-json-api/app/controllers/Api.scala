package controllers

import play.api._
import play.api.mvc._
import play.api.Play.current

object Api extends Controller {

  def index = Action {
    Ok("This is the controller for the api")
  }

  def json(name:String) = Action {
	  try {
		  val json = Play.classloader.getResourceAsStream(name + ".json");
		  Ok(scala.io.Source.fromInputStream(json).mkString)
	  }
	  catch {
		  case e: Exception => NotFound("Json file not found")
	  }
  }

}
