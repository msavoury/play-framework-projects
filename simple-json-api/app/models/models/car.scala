package models

import play.api.libs.json.{Json, Writes}

/**
 * Created by msavoury on 12/9/14.
 */
case class Car (make: String, model: String)


case class Truck (make: String, model: String)

  object Vehicle {
    implicit val truckWrites = new Writes[Truck] {
      def writes(truck: Truck) = {
        Json.obj(
          "make" -> truck.make,
          "model" -> truck.model
        )
      }
    }
  }