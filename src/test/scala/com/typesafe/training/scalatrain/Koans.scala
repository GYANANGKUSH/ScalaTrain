package com.typesafe.training.scalatrain

import org.scalatest.{Sequential, Suite}

import scala.collection.immutable

//this class basically contains the list of all the instances of the Koan-style specs transformed into a indexed seq.
//this will run all the specs at once:> testOnly com.typesafe.training.scalatrain.Koans
class Koans extends Sequential {
  override val nestedSuites: immutable.IndexedSeq[Suite]
  = List(new TimeSpecKoan, new TrainSpecKoan, new JourneyPlannerSpecKoan).toIndexedSeq
}
