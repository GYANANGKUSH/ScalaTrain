package com.typesafe.training.scalatrain.support

import org.scalatest.exceptions.TestPendingException
import org.scalatest.matchers.Matcher

//this class throws a TestPendingException of encoutering the '__' in its sub-classes
object BlankValues {
  class ReplaceWithCorrectException extends Exception

  def __ : Matcher[Any] = throw new TestPendingException

  class ___ extends ReplaceWithCorrectException{
    override def toString = "___"
  }
}

object Blankout {
  def blank[T](t:T): T = t
}