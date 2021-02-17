package com.typesafe.training.scalatrain.support

import org.scalatest.events.{Event, TestFailed, TestPending, TestSucceeded}
import org.scalatest.{Args, CancelAfterFailure, FailedStatus, FunSuite, Matchers, Reporter, Status, SucceededStatus}

//this class will return a note which will show the pending/failed test position
//this class will validate if all the parameters are available
trait KoanSuite extends FunSuite with CancelAfterFailure with Matchers {
  override def runTests(testName: Option[String], args: Args): Status = {
    if(testName == null) throw new NullPointerException("testName was null")
    if(args.reporter == null) throw new NullPointerException("reporter was null")
    if(args.stopper == null) throw new NullPointerException("stopper was null")
    if(args.filter == null) throw new NullPointerException("filter was null")
    if(args.configMap == null) throw new NullPointerException("configMap was null")
    if(args.distributor == null) throw new NullPointerException("distributor was null")
    if(args.tracker == null) throw new NullPointerException("tracker was null")

    class KoanReporter(wrappedReporter: Reporter) extends Reporter {
      var succeeded = false

      type HasTestAndName = {
      val testText: String
      val suiteName: String
      }

      def stopTests(e: HasTestAndName): Unit = {
        note("")
        note("***************************************************************")
        note("")
        note(s"***** INSIDE THE SUITE: ${e.suiteName}.scala *****")
        note("")
        note(s"Pending/failed test condition: ${e.testText}")
        note("")
        note("***************************************************************")
        note("")
        args.stopper.requestStop()
      }

      override def apply(ev: Event): Unit = {
        ev match {
          case e: TestSucceeded => succeeded = true
          case e: TestFailed => stopTests(e.asInstanceOf[HasTestAndName])
          case e: TestPending => stopTests(e.asInstanceOf[HasTestAndName])
          case _ =>
        }
        wrappedReporter(ev)
      }
    }

    testName match {
      case Some(tn) => runTest(tn, args)
      case None =>
        val tests = testNames.iterator
        var failed = false
        for(test <- tests) {
          if(!failed){
            val koanReporter = new KoanReporter(args.reporter)
            runTest(test, args.copy(reporter = koanReporter))
            failed = !koanReporter.succeeded
          }
        }
        if(failed) FailedStatus else SucceededStatus
    }
  }

  def koan(name: String)(fun: => Unit): Unit = test(name.stripMargin)(fun)
}
