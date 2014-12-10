package controllers

import models.{Truck, Car, Vehicle}
import models.Vehicle._
import play.api._
import play.api.libs.json.{Writes, Json}
import play.api.mvc._
import play.api.Play.current

object Api extends Controller {

  def index = Action {
		Ok("This is a test method")
  }

	def ex0 = Action {
		val testJson = Json.obj("key" -> "value")
		Ok(testJson)
	}

	/**
	 * This example uses an implicit writes for the car case class which is defined right above where it is used
	 * @return
	 */
	def ex1 = Action {
		val car = Car("Nissan", "Maxima")
		implicit val carWrites = new Writes[Car] {
			def writes(car:Car) = Json.obj(
				"make" -> car.make,
			  "model" -> car.model

			)
		}
    Ok(Json.toJson(car))
  }

	/**
	 * This example uses an implicit writes which is defined in the Vehicle object which is imported
	 * @return
	 */
	def ex2 = Action {
		val truck = Truck("Ford", "F150")
    Ok(Json.toJson(truck))
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
